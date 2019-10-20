package com.thejakeofink.bleacherreportflickr.di

import com.squareup.moshi.Moshi
import com.thejakeofink.bleacherreportflickr.net.BRFlickrApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module(
    includes = [ApiModule.Bindings::class,
        MainActivityComponent::class]
)
class ApiModule {
    @Module
    abstract class Bindings

    @Provides
    fun api(okHttpClient: OkHttpClient): BRFlickrApi {
        val moshi = Moshi.Builder()

        return Retrofit.Builder()
            .baseUrl("https://www.flickr.com/services/rest/")
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(BRFlickrApi::class.java)
    }
}