package com.bawei.exam02.fragment;

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

import com.bawei.exam02.R;
import com.bawei.exam02.tab.Tab01;
import com.bawei.exam02.tab.Tab02;
import com.bawei.exam02.tab.Tab03;

import java.util.ArrayList;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public class Frag01 extends Fragment {

    private ViewPager pager;
    private TabLayout tab;
    private ArrayList<String> tlist;
    private ArrayList<Fragment> flist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag01,container,false);
        pager = view.findViewById(R.id.pager);
        tab = view.findViewById(R.id.tab);
        tlist = new ArrayList<>();
        flist = new ArrayList<>();
        tlist.add("页面一");
        tlist.add("页面二");
        tlist.add("页面三");
        tab.addTab(tab.newTab().setText(tlist.get(0)));
        tab.addTab(tab.newTab().setText(tlist.get(1)));
        tab.addTab(tab.newTab().setText(tlist.get(2)));
        flist.add(new Tab01());
        flist.add(new Tab02());
        flist.add(new Tab03());
        //适配器
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
