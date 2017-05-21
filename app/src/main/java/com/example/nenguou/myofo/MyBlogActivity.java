package com.example.nenguou.myofo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyBlogActivity extends AppCompatActivity {

    private WebView myBlogWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_blog);
        myBlogWebView = (WebView) findViewById(R.id.myBlog_webview);
        myBlogWebView.getSettings().setJavaScriptEnabled(true);
        myBlogWebView.setWebViewClient(new WebViewClient());
        myBlogWebView.loadUrl("http://binguner.com");

    }
}
