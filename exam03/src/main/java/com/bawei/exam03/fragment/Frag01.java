package com.bawei.exam03.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.exam03.R;
import com.bawei.exam03.tab.Tab01;
import com.bawei.exam03.tab.Tab02;

import java.util.ArrayList;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public class Frag01 extends Fragment {

    private TabLayout tab;
    private ViewPager pager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag01, container, false);
        //找控件
        tab = view.findViewById(R.id.tab);
        pager = view.findViewById(R.id.pager);
        final ArrayList<Fragment> flist = new ArrayList<>();
        final ArrayList<String> tlist = new ArrayList<>();
       tlist.add("首页");
       tlist.add("我的");
       tab.addTab(tab.newTab().setText(tlist.get(0)));
       tab.addTab(tab.newTab().setText(tlist.get(1)));
       flist.add(new Tab01());
       flist.add(new Tab02());
       pager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
           @Override
           public Fragment getItem(int i) {
               return flist.get(i);
           }

           @Override
           public int getCount() {
               return flist.size();
           }

           @Nullable
           @Override
           public CharSequence getPageTitle(int position) {
               return tlist.get(position);
           }
       });
       tab.setupWithViewPager(pager);
        return view;
    }
}
