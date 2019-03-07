package com.example.dell.jingze20190304.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.dell.jingze20190304.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class H5Activity extends AppCompatActivity {
    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h5_main);
        ButterKnife.bind(this);
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        webview.loadUrl("http://www.baidu.com");


    }
}
