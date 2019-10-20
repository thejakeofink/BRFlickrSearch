package com.thejakeofink.bleacherreportflickr.model

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
                      val ispublic: Boolean,
                      val isfriend: Boolean,
                      val isfamily: Boolean)