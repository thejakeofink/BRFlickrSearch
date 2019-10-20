package com.thejakeofink.bleacherreportflickr

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thejakeofink.bleacherreportflickr.model.PhotoModel
import polyadapter.PolyAdapter
import polyadapter.equalityItemCallback
import javax.inject.Inject

class PhotoDelegate @Inject constructor(): PolyAdapter.BindingDelegate<PhotoModel, PhotoItemHolder> {

    override val dataType = PhotoModel::class.java
    override val itemCallback = equalityItemCallback<PhotoModel> { this.id }
    override val layoutId = PhotoItemHolder.LAYOUT

    override fun bindView(holder: PhotoItemHolder, item: PhotoModel) {
        holder.bindPhoto(item)
    }

    override fun createViewHolder(itemView: View) = PhotoItemHolder(itemView)


}

class PhotoItemHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val image by lazy {
        itemView.findViewById<ImageView>(R.id.image)
    }

    private val title by lazy {
        itemView.findViewById<TextView>(R.id.title)
    }

    fun bindPhoto(photo: PhotoModel) {
        val thumbnailRequest = Glide.
            with(itemView)
            .load(photo.thumbnailUrl())

        Glide.with(itemView)
            .load(photo.genericUrl())
            .thumbnail(thumbnailRequest)
            .into(image)
        title.text = photo.title

        itemView.setOnClickListener {
            val intent = PhotoViewActivity.getIntent(itemView.context, photo)
            itemView.context.startActivity(intent)
        }
    }


    companion object {
        @LayoutRes
        const val LAYOUT = R.layout.photo_item
    }
}