package com.bawei.exam03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bawei.exam03.bean.Bean;

import java.util.ArrayList;

public class WebActivity extends AppCompatActivity {

    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        web = findViewById(R.id.web);
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        web.loadUrl("https://www.baidu.com/s?wd=" + key);
        web.setWebViewClient(new WebViewClient());
    }
}
