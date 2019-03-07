package com.example.a0227.wuyong;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.a0227.R;

public class NoTitleActivity extends AppCompatActivity {

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_title);

        WebView mWebView = (WebView) findViewById(R.id.webview);
        url = "http://www.baidu.com";

        WebSettings setting = mWebView.getSettings();
        setting.setJavaScriptEnabled(true);//让webview支持javascript
        setting.setDefaultTextEncodingName("utf-8");//设置字符集
        //setting.setBlockNetworkImage(true);//设置不能访问网络图片

        // setting.setSupportZoom(true);//开启网页的缩放
        // setting.setBuiltInZoomControls(true);

        setting.setLoadWithOverviewMode(true);//设置网页缩放至手机大小
        setting.setUseWideViewPort(true);

        mWebView.getSettings().setJavaScriptEnabled(true);//让webview支持javascript
        mWebView.setWebChromeClient(new WebChromeClient());//支持特殊javascript


        mWebView.loadUrl(url);

        mWebView.setWebViewClient(new NoTitleActivity.MyWebViewClient());


    }


    private class MyWebViewClient extends WebViewClient {
        @Override
        // 在WebView中而不在默认浏览器中显示页面
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

    }
}

