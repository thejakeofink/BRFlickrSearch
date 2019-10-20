package com.thejakeofink.bleacherreportflickr

import android.util.Log
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.thejakeofink.bleacherreportflickr.model.PhotoModel
import com.thejakeofink.bleacherreportflickr.model.PhotosModel
import com.thejakeofink.bleacherreportflickr.net.ApiHelper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainPresenter @Inject constructor(apiHelper: ApiHelper,
                                        compositeDisposable: CompositeDisposable) : Presenter {

    private val stateRelay = BehaviorRelay.create<ViewState>()
    private val viewRelay = PublishRelay.create<ViewEvent>()
    private val searchRelay = PublishRelay.create<String>()

    sealed class Event {
        data class FromView(val action: ViewEvent) : Event()
        data class FromNetwork(val result: PhotosModel) : Event()
    }

    data class State(val photos: List<PhotoModel>,
                     val debounceNetworkDisposable: Disposable? = null)

    init {

        val viewObservable = viewRelay
            .map<Event> { Event.FromView(it) }

        val networkObservable = searchRelay
            .observeOn(Schedulers.io())
            .flatMapSingle {
                apiHelper.getPhotosForSearchTerm(it)
            }
            .map<Event> { Event.FromNetwork(it) }

        val logicStream = Observable.merge(
            viewObservable,
            networkObservable
        )
            .scan(State(emptyList())) { state, event ->
                when (event) {
                    is Event.FromView -> {
                        when (event.action) {
                            is ViewEvent.SearchTextChange -> {
                                state.debounceNetworkDisposable?.dispose()
                                if (event.action.searchTerm.isNotEmpty() && event.action.searchTerm.isNotBlank()) {
                                    val networkSend = Observable.timer(2, TimeUnit.SECONDS)
                                        .subscribeOn(Schedulers.io())
                                        .subscribe {
                                            searchRelay.accept(event.action.searchTerm)
                                        }
                                    state.copy(debounceNetworkDisposable = networkSend)
                                } else {
                                    state.copy(debounceNetworkDisposable = null)
                                }
                            }
                        }
                    }
                    is Event.FromNetwork -> {
                        state.copy(photos = event.result.photo)
                    }
                }
            }
            .map { ViewState(it.photos) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(stateRelay::accept,
                { error ->
                    Log.e("tag", "error in presenter", error)
                }, {
                    Log.v("tag", "completed")
                })
        compositeDisposable.add(logicStream)
    }




    override fun sendEvent(event: ViewEvent) {
        viewRelay.accept(event)
    }

    override fun stateUpdates(): Observable<ViewState> {
        return stateRelay.distinctUntilChanged().hide()
    }
}

interface Presenter {

    fun sendEvent(event: ViewEvent)

    fun stateUpdates(): Observable<ViewState>

}