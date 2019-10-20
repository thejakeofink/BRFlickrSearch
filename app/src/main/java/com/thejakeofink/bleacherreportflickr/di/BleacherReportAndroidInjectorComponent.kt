package com.thejakeofink.bleacherreportflickr.di

import android.app.Activity
import android.app.Service
import android.content.BroadcastReceiver
import androidx.fragment.app.Fragment
import dagger.android.DispatchingAndroidInjector
import okhttp3.OkHttpClient

interface BleacherReportAndroidInjectorComponent {
    fun activityInjector(): DispatchingAndroidInjector<Activity>
    fun broadcastReceiverInjector(): DispatchingAndroidInjector<BroadcastReceiver>
    fun serviceInjector(): DispatchingAndroidInjector<Service>
    fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment>

    fun baseClient(): OkHttpClient
}