package com.bawei.exam01.frag01;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.bawei.exam01.R;
import com.bawei.exam01.base.BaseFragment;
import com.bawei.exam01.tab.Tab01;
import com.bawei.exam01.tab.Tab02;

import java.util.ArrayList;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public class Frag01 extends BaseFragment {

    private TabLayout tab;
    private ViewPager pager;

    @Override
    public int initLayout() {
        return R.layout.frag01;
    }

    @Override
    public void initView() {
        tab = initId(R.id.tab);
        pager = initId(R.id.pager);
    }

    @Override
    public void initData() {
        final ArrayList<Fragment> flist = new ArrayList<>();//页面
        final ArrayList<String> tlist = new ArrayList<>();//文字
        for (int i = 0; i <2 ; i++) {
            tlist.add("页面"+1);
            if (i==0){
                flist.add(new Tab01());
            }else {
                flist.add(new Tab02());
            }
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
            //联动
            tab.setupWithViewPager(pager);
        }
    }

    @Override
    public void initEvent() {

    }
}
