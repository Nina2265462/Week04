package com.bawei.exam04.bean;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public class Shang {
private String imageUrl;

    public Shang() {
    }

    public Shang(String imageUrl) {
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
        return "Shang{" +
                "imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
