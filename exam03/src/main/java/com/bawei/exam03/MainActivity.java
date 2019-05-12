package com.bawei.exam03;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.bawei.exam03.base.BaseActivity;
import com.bawei.exam03.fragment.Frag01;
import com.bawei.exam03.fragment.Frag02;
import com.bawei.exam03.fragment.Frag03;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

public class MainActivity extends BaseActivity {

    private ImageView img;
    private RadioGroup group;
    private Frag01 frag01;
    private Frag02 frag02;
    private Frag03 frag03;
    private FragmentManager manager;

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void bindView() {
//找控件
        img = findViewById(R.id.img);
        group = findViewById(R.id.group);
    }

    @Override
    public void bindData() {
        //数据
        frag01 = new Frag01();
        frag02 = new Frag02();
        frag03 = new Frag03();
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.frame, frag01).show(frag01)
                .add(R.id.frame, frag02).hide(frag02)
                .add(R.id.frame, frag03).hide(frag03).commit();
    }

    @Override
    public void bindEvent() {
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction tra = manager.beginTransaction();
                switch (checkedId) {
                    case R.id.radio01:
                        tra.show(frag01).hide(frag02).hide(frag03);
                        break;
                    case R.id.radio02:
                        tra.show(frag02).hide(frag01).hide(frag03);
                        break;
                    case R.id.radio03:
                        tra.show(frag03).hide(frag02).hide(frag01);
                        break;
                }
                tra.commit();
            }
        });
        //侧滑  头像
        img.setOnClickListener(new View.OnClickListener() {
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
        Glide.with(MainActivity.this).load(uri).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(img);
    }

}
