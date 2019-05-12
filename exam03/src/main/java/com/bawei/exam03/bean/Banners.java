package com.bawei.exam03.bean;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public class Banners {
    private String imageUrl;

    public Banners() {
    }

    public Banners(String imageUrl) {
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
        return "Banners{" +
                "imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
