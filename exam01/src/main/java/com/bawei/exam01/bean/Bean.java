package com.bawei.exam01.bean;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public class Bean {
    //imageUrl": "http://172.17.8.100/images/movie/stills/ws/ws1.jpg",
      //  "name": "无双",
    private String summary;
    private String name;
    private String imageUrl;

    public Bean() {
    }

    public Bean(String summary, String name, String imageUrl) {
        this.summary = summary;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "summary='" + summary + '\'' +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
