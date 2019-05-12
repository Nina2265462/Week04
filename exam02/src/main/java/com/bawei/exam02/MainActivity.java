package com.bawei.exam02;

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

import com.bawei.exam02.fragment.Frag01;
import com.bawei.exam02.fragment.Frag02;
import com.bawei.exam02.fragment.Frag03;
import com.bumptech.glide.Glide;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewg;
    private ImageView img;
    private RadioGroup group;
    private FragmentManager manager;
    private Frag01 frag01;
    private Frag02 frag02;
    private Frag03 frag03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.img);
        group = findViewById(R.id.group);
        frag01 = new Frag01();
        frag02 = new Frag02();
        frag03 = new Frag03();
        //============================================================
        manager = getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame, frag01).show(frag01)
                .add(R.id.frame, frag02).show(frag02)
                .add(R.id.frame, frag03).show(frag03).commit();
        //============================================================
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction1 = manager.beginTransaction();
                switch (checkedId) {
                    case R.id.radio01:
                        transaction1.show(frag01).hide(frag02).hide(frag03);
                        break;
                    case R.id.radio02:
                        transaction1.show(frag02).hide(frag01).hide(frag03);
                        break;
                    case R.id.radio03:
                        transaction1.show(frag03).hide(frag02).hide(frag01);
                        break;
                }
                transaction1.commit();
            }
        });
        //===========================================
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
        Glide.with(MainActivity.this).load(uri).into(img);
    }
}
