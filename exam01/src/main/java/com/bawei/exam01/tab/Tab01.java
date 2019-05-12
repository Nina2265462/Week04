package com.bawei.exam01.tab;

import android.content.Context;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;

import com.bawei.exam01.R;
import com.bawei.exam01.adapter.MyAdapter;
import com.bawei.exam01.bean.Bean;
import com.bawei.exam01.bean.Slideshow;
import com.bawei.exam01.base.BaseTabFragment;
import com.bawei.exam01.http.HttpUntil;
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
public class Tab01 extends BaseTabFragment {

    private PullToRefreshScrollView psv;
    private Banner banner;
    private ListView lv;
    private String str1 = "http://172.17.8.100/small/commodity/v1/bannerShow";
    private String str2 = "http://172.17.8.100/movieApi/movie/v1/findHotMovieList?count=3&page=";
    private int page = 1;
    private ArrayList<Bean> list = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    public int tabLayout() {
        return R.layout.tab01;
    }

    @Override
    public void tabView() {
        psv = tabId(R.id.psv);
        banner = tabId(R.id.baner);
        lv = tabId(R.id.mlv);
    }

    @Override
    public void tabData() {
        //轮播图
        HttpUntil.AsyncTask(str1, new HttpUntil.CallBackPostExecute() {
            @Override
            public void getPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    JSONArray result = object.getJSONArray("result");
                    ArrayList<Slideshow> slist = new ArrayList<>();
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject obj = (JSONObject) result.get(i);
                        String imageUrl = obj.getString("imageUrl");
                        slist.add(new Slideshow(imageUrl));
                    }
                    //图片加载器
                    banner.setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            Slideshow slideshow = (Slideshow) path;
                            Glide.with(getContext()).load(slideshow.getImageUrl()).into(imageView);
                        }
                    });
                    banner.setImages(slist);
                    banner.isAutoPlay(true);
                    banner.setDelayTime(3000);
                    banner.start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        //==========================================================================================
        psv.setMode(PullToRefreshBase.Mode.BOTH);
        psv.setPullToRefreshOverScrollEnabled(true);
        //适配器
        getData();


    }

    private void getData() {
        HttpUntil.AsyncTask(str2 + page, new HttpUntil.CallBackPostExecute() {
            @Override
            public void getPostExecute(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    JSONArray result = object.getJSONArray("result");
                    ArrayList<Bean> arr = new ArrayList<>();
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject obj = (JSONObject) result.get(i);
                        String summary = obj.getString("summary");
                        String name = obj.getString("name");
                        String imageUrl = obj.getString("imageUrl");
                        arr.add(new Bean(summary, name, imageUrl));
                    }
                    if (page == 1) {
                        list.clear();
                    }
                    list.addAll(arr);
                    //适配器刷新
                    adapter.notifyDataSetChanged();
                    psv.onRefreshComplete();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void tabEvent() {
        //设置监听
        psv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {
                //刷新
                page = 1;
                getData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {
                //加载
                page++;
                getData();
            }
        });
        adapter = new MyAdapter(list, getContext());
        lv.setAdapter(adapter);
    }
}
