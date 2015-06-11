package com.kejiwen.architecture.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kejiwen.architecture.R;

public class CommonWebViewActivity extends BaseActivity {
    private final static String TAG = "CommonWebViewActivity";

    private WebView mWebView;
    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.common_webview);
        super.onCreate(savedInstanceState);
        mTitleBar.setBackButton(R.mipmap.titlebar_back_arrow, this);

        mUrl = getIntent().getStringExtra("url");
        mWebView = (WebView) findViewById(R.id.webview);
        if (!TextUtils.isEmpty(mUrl)) mWebView.loadUrl(mUrl);
        mWebView.getSettings().setJavaScriptEnabled(true);// 设置使用够执行JS脚本
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mTitleBar.setTitle(view.getTitle());
                view.loadUrl(url);// 使用当前WebView处理跳转
                return true;// true表示此事件在此处被处理，不需要再广播
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mTitleBar.setTitle(view.getTitle());
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mTitleBar.setTitle(view.getTitle());
            }

            @Override
            // 转向错误时的处理
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
            }
        });
    }

    // 默认点回退键，会退出Activity，需监听按键操作，使回退在WebView内发生
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackStack() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            finish();
        }

    }

    @Override
    protected int getTitleBarRes() {
        return R.id.titlebar;
    }
}
