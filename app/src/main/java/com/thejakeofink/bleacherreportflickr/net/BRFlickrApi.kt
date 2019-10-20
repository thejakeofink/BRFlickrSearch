package com.thejakeofink.bleacherreportflickr.net

import com.thejakeofink.bleacherreportflickr.model.SearchModel
import io.reactivex.Single
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface BRFlickrApi {

    @POST("")
    @FormUrlEncoded
    fun getPhotos(
        @FieldMap params: Map<String, String>): Single<SearchModel>


}