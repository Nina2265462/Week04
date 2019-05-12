package com.bawei.exam01;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bawei.exam01.base.BaseActivity;
import com.bawei.exam01.frag01.Frag01;
import com.bawei.exam01.frag01.Frag02;
import com.bawei.exam01.frag01.Frag03;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

public class MainActivity extends BaseActivity {

    private RadioGroup group;
    private FragmentManager manager;
    private Frag01 frag01;
    private Frag02 frag02;
    private Frag03 frag03;
    private ImageView image;


    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void bindView() {
        group = findViewById(R.id.group);
        image = findViewById(R.id.header);
    }

    @Override
    public void bindData() {
        frag01 = new Frag01();
        frag02 = new Frag02();
        frag03 = new Frag03();
        //通过事务进行隐藏和显示
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame, frag01).show(frag01)
                .add(R.id.frame, frag02).hide(frag02)
                .add(R.id.frame, frag03).hide(frag03).commit();
    }

    @Override
    public void bindEvent() {
//页面切换
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = manager.beginTransaction();
                switch (checkedId) {
                    case R.id.radio01:
                        transaction.show(frag01).hide(frag02).hide(frag03);
                        break;
                    case R.id.radio02:
                        transaction.show(frag02).hide(frag01).hide(frag03);
                        break;
                    case R.id.radio03:
                        transaction.show(frag03).hide(frag02).hide(frag01);
                        break;
                }
                transaction.commit();
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        Glide.with(MainActivity.this).load(uri).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(image);
    }
}
