package com.thejakeofink.bleacherreportflickr.di

import com.thejakeofink.bleacherreportflickr.App
import dagger.android.AndroidInjector

object DependencyGraphProvider {

    private lateinit var appComponent: AppComponent

    private val apiComponent by lazy {
        appComponent.apiComponent()
            .build()
    }

    fun setupAppComponent(app: App) {
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(app))
            .build()
    }

    fun getBestAndriodInjector(): AndroidInjector<Any> {
        return apiComponent.activityInjector()
    }
}