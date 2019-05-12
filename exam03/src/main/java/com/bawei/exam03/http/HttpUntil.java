package com.bawei.exam03.http;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public class HttpUntil {
    //懒汉模式
    private static HttpUntil until;

    private HttpUntil() {
    }

    public static HttpUntil getHttpUntil() {
        if (until == null) {
            until = new HttpUntil();
        }
        return until;
    }
    //判断是否有网络
    public boolean isNetWork(Context context){
       ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info!=null){
            return info.isAvailable();
        }
        return false;
    }

    public String getString(String str) {
        try {
            URL url = new URL(str);
            //1.HttpUrlConnection进行数据请求、图片请求；
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            if (connection.getResponseCode() == 200) {
                //读取数据
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                //拼接字符串
                StringBuffer buffer = new StringBuffer();
                String s = "";
                while ((s = reader.readLine()) != null) {
                    buffer.append(s);
                }
                //关闭
                connection.disconnect();
                reader.close();
                //返回
                return buffer.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //创建接口
    public interface CallBackPostExecute {
        void getPostExecute(String s);
    }

    //Asynctask进行异步处理；
    @SuppressLint("StaticFieldLeak")
    public void AsyncTask(String surl, final CallBackPostExecute post) {
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... strings) {
                return getString(strings[0]);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                post.getPostExecute(s);
            }
        }.execute(surl);
    }
}
