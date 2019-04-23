package com.example.asus.intent_url;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebView extends AppCompatActivity {
    private WebView webView;
    private  String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myweb);
        initView();
        setWebView();
    }
    private void initView(){
        webView=(WebView)findViewById(R.id.web_view);
        Intent intent=getIntent();
        url=intent.getData().toString();
    }

    private void setWebView(){
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);

        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                webView.loadUrl(url);
                return true;
            }
        });
    }
}
