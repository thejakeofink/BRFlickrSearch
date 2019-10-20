package com.thejakeofink.bleacherreportflickr.di

import com.thejakeofink.bleacherreportflickr.MainActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import polyadapter.PolyAdapter
import polyadapter.provider.RxListProvider

@Module
abstract class MainActivityComponent {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [MainActivityModule::class,
            ProviderModule::class]
    )
    abstract fun contributesInjector(): MainActivity
}

@Module(includes = [MainActivityModule.Bindings::class])
object MainActivityModule {

    @Module
    abstract class Bindings {

        @Binds
        abstract fun listProvider(impl: RxListProvider):
                PolyAdapter.ItemProvider
    }
}

@Module
class ProviderModule {
    @ActivityScope
    @Provides
    fun rxProvider() = RxListProvider()
}