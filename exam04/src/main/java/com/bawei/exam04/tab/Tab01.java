package com.bawei.exam04.tab;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bawei.exam04.R;
import com.bawei.exam04.adapter.MyAdapter;
import com.bawei.exam04.adapter.MyGVAdapter;
import com.bawei.exam04.bean.Bean;
import com.bawei.exam04.bean.Shang;
import com.bawei.exam04.bean.Zhong;
import com.bawei.exam04.http.HttpUntil;
import com.bawei.exam04.view.MyListView;
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

    private PullToRefreshScrollView psv;
    private Banner banner;
    private MyListView mlv;
    private GridView gv;
    private String str1 = "http://172.17.8.100/small/commodity/v1/bannerShow";
    private String str2 = "http://www.duans.top/freeApi/api.php?act=getJoke&page=1&count=10";
    private String str3 = "http://172.17.8.100/movieApi/movie/v1/findHotMovieList?count=2&page=";
    private int page = 1;
    private HttpUntil until;
    private ArrayList<Zhong> zlist;
    private ArrayList<Bean> list;
    private MyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab01, container, false);
        //找控件
        psv = view.findViewById(R.id.psv);
        banner = view.findViewById(R.id.banner);
        gv = view.findViewById(R.id.gv);
        mlv = view.findViewById(R.id.mlv);
        until = HttpUntil.getHttpUntil();

        //判断是否有网络
        if (until.isNetWork(getContext())) {
            Toast.makeText(getContext(), "有网络", Toast.LENGTH_LONG).show();
        }
        //========轮播图=======================
        until.AsyncTask(str1, new HttpUntil.CallBackPostExecute() {
            @Override
            public void getPostExecute(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray result = jsonObject.getJSONArray("result");
                    ArrayList<Shang> slist = new ArrayList<>();
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject obj = (JSONObject) result.get(i);
                        String imageUrl = obj.getString("imageUrl");
                        slist.add(new Shang(imageUrl));
                    }
                    banner.setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            Shang shang = (Shang) path;
                            Glide.with(getContext()).load(shang.getImageUrl()).into(imageView);
                        }
                    });
                    banner.setImages(slist);
                    banner.isAutoPlay(true);
                    banner.setDelayTime(2000);
                    banner.start();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        //===============九宫格==================
        until.AsyncTask(str2, new HttpUntil.CallBackPostExecute() {
            @Override
            public void getPostExecute(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray result = jsonObject.getJSONArray("result");
                    zlist = new ArrayList<>();
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject obj = (JSONObject) result.get(i);
                        String header = obj.getString("header");
                        zlist.add(new Zhong(header));
                    }
                    MyGVAdapter myGVAdapter = new MyGVAdapter(zlist, getContext());
                    gv.setAdapter(myGVAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        //==============数据解析=================
        psv.setPullToRefreshOverScrollEnabled(true);
        psv.setMode(PullToRefreshBase.Mode.BOTH);
        list = new ArrayList<>();
        adapter = new MyAdapter(list, getContext());
        mlv.setAdapter(adapter);
        getData();
        psv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                page = 1;
                getData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                page++;
                getData();
            }
        });


        return view;
    }

    private void getData() {
        until.AsyncTask(str3 + page, new HttpUntil.CallBackPostExecute() {
            @Override
            public void getPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    JSONArray result = object.getJSONArray("result");
                    ArrayList<Bean> arrayList = new ArrayList<>();
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject obj = (JSONObject) result.get(i);
                        String name = obj.getString("name");
                        String imageUrl = obj.getString("imageUrl");
                        arrayList.add(new Bean(imageUrl, name));
                    }
                    if (page == 1) {
                        list.clear();
                    }
                    list.addAll(arrayList);
                    adapter.notifyDataSetChanged();
                    psv.onRefreshComplete();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
