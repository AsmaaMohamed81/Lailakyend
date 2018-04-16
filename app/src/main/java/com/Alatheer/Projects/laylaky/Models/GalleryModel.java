package com.Alatheer.Projects.laylaky.Models;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.Serializable;

/**
 * Created by Emad on 2018-04-14.
 */

public class GalleryModel implements Serializable {
    private Uri uri;

    public GalleryModel(Uri uri) {
        this.uri = uri;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
