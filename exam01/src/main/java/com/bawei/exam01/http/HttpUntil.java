package com.bawei.exam01.http;

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
    public static String getString(String str) {
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
    public static void AsyncTask(String surl, final CallBackPostExecute post) {
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
