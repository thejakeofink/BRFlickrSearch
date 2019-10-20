package com.thejakeofink.bleacherreportflickr.di

import android.app.Application
import com.thejakeofink.bleacherreportflickr.App
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module(
    includes = [AppModule.Bindings::class,
        ApiModule::class,
        PolyAdapterConfigModule::class]
)
class AppModule(private val app: App) {

    @Module
    abstract class Bindings {
        @Binds
        abstract fun application(app: App): Application
    }

    @Provides
    fun provideApp(): App {
        return app
    }

    @Provides
    @Singleton
    @JvmSuppressWildcards
    fun provideBaseOkClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

}