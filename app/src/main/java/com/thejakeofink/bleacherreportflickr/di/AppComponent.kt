package com.thejakeofink.bleacherreportflickr.di

import com.thejakeofink.bleacherreportflickr.App
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class]
)
interface AppComponent : AndroidInjector<App> {
    fun apiComponent(): ApiComponent.Builder
    fun httpClient(): OkHttpClient
}