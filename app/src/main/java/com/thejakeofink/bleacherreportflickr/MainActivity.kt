package com.thejakeofink.bleacherreportflickr

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding2.widget.textChanges
import com.thejakeofink.bleacherreportflickr.model.PhotoModel
import com.thejakeofink.bleacherreportflickr.net.ApiHelper
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import polyadapter.PolyAdapter
import polyadapter.provider.RxListProvider
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var apiHelper: ApiHelper

    @Inject
    lateinit var polyAdapter: PolyAdapter

    @Inject
    lateinit var itemProvider: RxListProvider

    var presenter: Presenter? = null

    private var searchText: EditText? = null
    private var imageList: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchText = findViewById(R.id.search_text)
        imageList = findViewById(R.id.image_list)

        imageList?.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = polyAdapter
        }

        presenter = MainPresenter(apiHelper, compositeDisposable)

        presenter?.stateUpdates()
            ?.map { it.images }
            ?.compose(itemProvider)
            ?.subscribe { applyNewData ->
                applyNewData()
            }?.let {
                compositeDisposable.add(it)
            }

        searchText?.textChanges()
            ?.subscribe {
                presenter?.sendEvent(ViewEvent.SearchTextChange(it.toString()))
            }?.let {
                compositeDisposable.add(it)
            }
    }


    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}

sealed class ViewEvent {
    data class SearchTextChange(val searchTerm: String) : ViewEvent()
}

data class ViewState(val images: List<PhotoModel>)
