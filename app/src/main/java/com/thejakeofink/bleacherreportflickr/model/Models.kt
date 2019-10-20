package com.thejakeofink.bleacherreportflickr.model

import java.io.Serializable

data class SearchModel(val stat: String,
                       val photos: PhotosModel)

data class PhotosModel(val page: Int,
                       val pages: Int,
                       val perpage: Int,
                       val total: Int,
                       val photo: List<PhotoModel>)

data class PhotoModel(val id: String,
                      val owner: String,
                      val secret: String,
                      val server: String,
                      val farm: Int,
                      val title: String,
                      val ispublic: Int,
                      val isfriend: Int,
                      val isfamily: Int) : Serializable {

    fun thumbnailUrl(): String {
        return createPhotoURL()
            .plus("_t")
    }

    fun genericUrl(): String {
        return createPhotoURL()
    }

    fun largeUrl(): String {
        return createPhotoURL()
            .plus("_b")
    }

    private fun createPhotoURL(): String {
        return FLICKR_PHOTO_URL
            .replace("{farm-id}", farm.toString())
            .replace("{server-id}", server)
            .replace("{id}", id)
            .replace("{secret}", secret)
    }

    companion object {
        const val FLICKR_PHOTO_URL = "https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg"
    }
}
