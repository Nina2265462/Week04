package com.bawei.exam04.bean;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public class Zhong {
    private String header;

    public Zhong() {
    }

    public Zhong(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return "Zhong{" +
                "header='" + header + '\'' +
                '}';
    }
}
