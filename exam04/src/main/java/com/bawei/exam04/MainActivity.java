package com.bawei.exam04;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bawei.exam04.tab.Tab01;
import com.bawei.exam04.tab.Tab02;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        tab = findViewById(R.id.tab);
        pager = findViewById(R.id.pager);
        //数据
        final ArrayList<Fragment> flist = new ArrayList<>();
        final ArrayList<String> tlist = new ArrayList<>();
        tlist.add("首页");
        tlist.add("我的");
        tab.addTab(tab.newTab().setText(tlist.get(0)));
        tab.addTab(tab.newTab().setText(tlist.get(1)));
        flist.add(new Tab01());
        flist.add(new Tab02());
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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


    }
}
