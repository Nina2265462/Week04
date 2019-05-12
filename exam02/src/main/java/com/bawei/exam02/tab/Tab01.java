package com.bawei.exam02.tab;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;

import com.bawei.exam02.R;
import com.bawei.exam02.adapter.MyAdapter;
import com.bawei.exam02.bean.Bean;
import com.bawei.exam02.bean.Slideshow;
import com.bawei.exam02.http.HttpUntil;
import com.bumptech.glide.Glide;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
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
public class Tab01 extends Fragment {

    private Banner banner;
    private ListView lv;
    private PullToRefreshScrollView psv;
    private HttpUntil until;
    private int page = 1;
    private String str1 = "http://172.17.8.100/movieApi/movie/v1/findHotMovieList?count=3&page=";
    private String str2 = "http://172.17.8.100/small/commodity/v1/bannerShow";
    private ArrayList<Bean> list;
    private MyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab01, container, false);
        psv = view.findViewById(R.id.psv);
        banner = view.findViewById(R.id.banner);
        lv = view.findViewById(R.id.lv);
        until = HttpUntil.getHttpUntil();
        //轮播图
        until.AsyncTask(str2, new HttpUntil.CallBackPostExecute() {
            @Override
            public void getPostExecute(String s) {
                try {
                    ArrayList<Slideshow> slist = new ArrayList<>();
                    JSONObject object = new JSONObject(s);
                    JSONArray result = object.getJSONArray("result");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject obj = (JSONObject) result.get(i);
                        String imageUrl = obj.getString("imageUrl");
                        slist.add(new Slideshow(imageUrl));
                    }
                    banner.setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            Slideshow slideshow = (Slideshow) path;
                            Glide.with(getContext()).load(slideshow).into(imageView);
                        }
                    });
                    banner.setImages(slist);
                    banner.isAutoPlay(true);
                    banner.setDelayTime(1000);
                    banner.start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }
}
