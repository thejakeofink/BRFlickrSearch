package com.thejakeofink.bleacherreportflickr.di

import dagger.android.DispatchingAndroidInjector
import okhttp3.OkHttpClient

interface BleacherReportAndroidInjectorComponent {
    fun activityInjector(): DispatchingAndroidInjector<Any>

    fun baseClient(): OkHttpClient
}