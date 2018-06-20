package com.a7.showbord;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private static final int REFRESH_TIME = 0x0001;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.web_view);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.loadUrl("http://120.79.106.248:3000/gameinfo");
//        webView.loadUrl("http://127.0.0.1:3000/gameinfo");
        handler.sendEmptyMessageDelayed(REFRESH_TIME, 3 * 1000);
    }

    private RefreshHandler handler = new RefreshHandler(new WeakReference<>(this));

    static class RefreshHandler extends Handler {
        WeakReference<MainActivity> weakReference;
        MainActivity activity;

        public RefreshHandler(WeakReference<MainActivity> weakReference) {
            this.weakReference = weakReference;
            activity = weakReference.get();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            activity.webView.reload();
            activity.handler.sendEmptyMessageDelayed(REFRESH_TIME, 10 * 1000);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
