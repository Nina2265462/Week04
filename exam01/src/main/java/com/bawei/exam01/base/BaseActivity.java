package com.bawei.exam01.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bawei.exam01.R;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());
        bindView();
        bindData();
        bindEvent();
    }

    public abstract int bindLayout();

    public abstract void bindView();

    public abstract void bindData();

    public abstract void bindEvent();
}