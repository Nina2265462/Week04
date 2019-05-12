package com.bawei.exam04.tab;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bawei.exam04.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public class Tab02 extends Fragment {

    private ImageView img;
    private WebView web;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab02, container, false);
        img = view.findViewById(R.id.img);
        web = view.findViewById(R.id.web);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 0);
            }
        });
        web.loadUrl("file:///android_asset/w.html");
        web.setWebViewClient(new WebViewClient());
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        Glide.with(getContext()).load(uri).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(img);
    }
}
