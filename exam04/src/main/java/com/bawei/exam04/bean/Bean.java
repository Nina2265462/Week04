package com.bawei.exam04.bean;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public class Bean {
    private String imageUrl;
    private String name;

    public Bean() {
    }

    public Bean(String imageUrl, String name) {
        this.imageUrl = imageUrl;
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "imageUrl='" + imageUrl + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
