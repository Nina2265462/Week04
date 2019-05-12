package com.bawei.exam03.tab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bawei.exam03.WebActivity;
import com.bawei.exam03.adapter.MyAdapter;
import com.bawei.exam03.R;
import com.bawei.exam03.bean.Banners;
import com.bawei.exam03.bean.Bean;
import com.bawei.exam03.http.HttpUntil;
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
    private String str = "http://172.17.8.100/movieApi/movie/v1/findHotMovieList?count=2&page=";
    private int page = 1;
    private PullToRefreshScrollView psv;
    private ListView lv;
    private HttpUntil until;
    private ArrayList<Bean> list;
    private MyAdapter adapter;
    private String str2 = "http://172.17.8.100/small/commodity/v1/bannerShow";
    private Banner banner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab01, container, false);
        psv = view.findViewById(R.id.psv);
        lv = view.findViewById(R.id.lv);
        banner = view.findViewById(R.id.banner);
        until = HttpUntil.getHttpUntil();
        if (until.isNetWork(getContext())) {
            Toast.makeText(getActivity(), "有网", Toast.LENGTH_LONG).show();
        }
        until.AsyncTask(str2, new HttpUntil.CallBackPostExecute() {
            @Override
            public void getPostExecute(String s) {
                try {
                    ArrayList<Banners> blist = new ArrayList<>();
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
        //================================================================
        psv.setPullToRefreshOverScrollEnabled(true);
        psv.setMode(PullToRefreshBase.Mode.BOTH);
        list = new ArrayList<>();
        adapter = new MyAdapter(list, getContext());
        lv.setAdapter(adapter);
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
        //============点击跳转WebView======================
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("key",list.get(position).getName());//"https://www.baidu.com"
                startActivity(intent);
            }
        });
        return view;
    }

    private void getData() {
        until.AsyncTask(str + page, new HttpUntil.CallBackPostExecute() {
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
