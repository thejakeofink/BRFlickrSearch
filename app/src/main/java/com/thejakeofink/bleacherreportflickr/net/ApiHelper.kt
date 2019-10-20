package com.thejakeofink.bleacherreportflickr.net

import com.thejakeofink.bleacherreportflickr.model.PhotosModel
import io.reactivex.Single

interface ApiHelper {

    fun getPhotosForSearchTerm(term: String): Single<PhotosModel>
}