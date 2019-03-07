package com.example.a0227.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.a0227.R;


public class ZhuFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_zhu,container,false);

        WebView webView = view.findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        webView.loadUrl("https://wapbaike.baidu.com/item/%E8%85%BE%E8%AE%AF/112204");

       /* webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);


                String javascript = "javascript:function hideOther() {" +
                        "document.getElementsByTagName('body')[0].innerHTML;" +
                        "document.getElementsByTagName('div')[0].style.display='none';" +
                        "document.getElementsByTagName('div')[3].style.display='none';" +
                        "document.getElementsByClassName('dropdown')[0].style.display='none';" +
                        "document.getElementsByClassName('min')[0].remove();" +
                        "var divs = document.getElementsByTagName('div');" +
                        "var lastDiv = divs[divs.length-1];" +
                        "lastDiv.remove();" +
                        "document.getElementsByClassName('showme')[0].remove();" //+
                        //  "document.getElementsByClassName('nei-t3')[1].remove();}"
                        ;

                //创建方法
                view.loadUrl(javascript);

                //加载方法
                view.loadUrl("javascript:hideOther();");
            }
        });
*/
        return view;
    }
}

