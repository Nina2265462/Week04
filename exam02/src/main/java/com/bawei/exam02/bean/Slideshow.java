package com.bawei.exam02.bean;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public class Slideshow {
    private String imageUrl;

    public Slideshow() {
    }

    public Slideshow(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Slideshow{" +
                "imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
