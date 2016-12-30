package com.techpenta.camera;

import android.graphics.Bitmap;

/**
 * Created by NgocTri on 10/22/2016.
 */

public class Product {
    private Bitmap imageId;
    private String title;


    public Product(Bitmap imageId,String title) {
        this.imageId = imageId;
        this.title=title;

    }

    public Bitmap getImageId() {
        return imageId;
    }

    public void setImageId(Bitmap imageId) {
        this.imageId = imageId;
    }

    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }
}
