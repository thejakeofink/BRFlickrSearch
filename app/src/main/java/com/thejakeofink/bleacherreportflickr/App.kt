package com.thejakeofink.bleacherreportflickr

import android.app.Application
import com.thejakeofink.bleacherreportflickr.di.DependencyGraphProvider
import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector

class App : Application(), HasAndroidInjector {

    override fun onCreate() {
        super.onCreate()

        DependencyGraphProvider.setupAppComponent(this)
    }


    override fun androidInjector(): AndroidInjector<Any> {
        return DependencyGraphProvider.getBestAndriodInjector()
    }
}