package com.thejakeofink.bleacherreportflickr.net;

import com.thejakeofink.bleacherreportflickr.BuildConfig;
import com.thejakeofink.bleacherreportflickr.model.PhotosModel;
import com.thejakeofink.bleacherreportflickr.model.SearchModel;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

public class FlickrApiHelper implements ApiHelper {

    private BRFlickrApi api;

    @Inject
    FlickrApiHelper(BRFlickrApi api) {
        this.api = api;
    }

    @NotNull
    @Override
    public Single<PhotosModel> getPhotosForSearchTerm(@NotNull final String term) {
        Map<String, String> params = new HashMap<String, String>() {{
           put("api_key", BuildConfig.FLICKR_API_KEY);
           put("tags", term);
           put("method", "flickr.photos.search");
           put("format", "json");
           put("nojsoncallback", "1");
           put("sign_call", "none");
           put("per_page", "25");
        }};

        return api.getPhotos(params)
                .map(SearchModel::getPhotos);
    }
}
