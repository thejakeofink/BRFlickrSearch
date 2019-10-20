package com.thejakeofink.bleacherreportflickr;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.thejakeofink.bleacherreportflickr.model.PhotoModel;

public class PhotoViewActivity extends AppCompatActivity {

    private static String PHOTO = "----photo----";

    private PhotoModel photo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        photo = (PhotoModel) getIntent().getSerializableExtra(PHOTO);

        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setDisplayShowHomeEnabled(true);
            bar.setHomeButtonEnabled(true);
        }

        ImageView image = findViewById(R.id.large_image);

        RequestBuilder<Drawable> thumbnail = Glide
                .with(this)
                .load(photo.thumbnailUrl());

        RequestBuilder<Drawable> generic = Glide
                .with(this)
                .load(photo.genericUrl());

        Glide.with(this)
                .load(photo.largeUrl())
                .thumbnail(thumbnail)
                .error(generic)
                .into(image);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    static Intent getIntent(Context context, PhotoModel photo) {
        Intent intent = new Intent(context, PhotoViewActivity.class);
        intent.putExtra(PHOTO, photo);
        return intent;
    }
}
