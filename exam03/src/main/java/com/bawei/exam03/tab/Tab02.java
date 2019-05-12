package com.bawei.exam03.tab;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bawei.exam03.R;
import com.bawei.exam03.bean.Banners;
import com.bawei.exam03.http.HttpUntil;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public class Tab02 extends Fragment {
    private String str2 = "http://172.17.8.100/small/commodity/v1/bannerShow";
    private Banner banner;
    private HttpUntil httpUntil;
    private WebView web;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab02, container, false);
        banner = view.findViewById(R.id.banner);
        web = view.findViewById(R.id.web);
        httpUntil = HttpUntil.getHttpUntil();
        httpUntil.AsyncTask(str2, new HttpUntil.CallBackPostExecute() {
            @Override
            public void getPostExecute(String s) {
                try { ArrayList<Banners> blist = new ArrayList<>();
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray result = jsonObject.getJSONArray("result");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject obj = (JSONObject) result.get(i);
                        String imageUrl = obj.getString("imageUrl");
                        blist.add(new Banners(imageUrl));
                    }
                    banner.setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            Banners ban = (Banners) path;
                            Glide.with(getContext()).load(ban.getImageUrl()).into(imageView);
                        }
                    });
                    banner.setImages(blist);
                    banner.isAutoPlay(true);
                    banner.setDelayTime(1000);
                    banner.start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        //===================webview===========
        web.loadUrl("file:///android_asset/w.html");
        //web.loadUrl("file:///android_asset/w.html");
        web.setWebViewClient(new WebViewClient());
        return view;
    }
}
