package com.bawei.exam01.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public abstract class BaseTabFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(tabLayout(), container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tabView();
        tabData();
        tabEvent();
    }

    public abstract int tabLayout();

    public abstract void tabView();

    public abstract void tabData();

    public abstract void tabEvent();

    public <T extends View> T tabId(int id) {
        return getActivity().findViewById(id);
    }
}
