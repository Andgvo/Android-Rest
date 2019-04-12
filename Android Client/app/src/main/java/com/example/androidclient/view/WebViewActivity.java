package com.example.androidclient.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.androidclient.R;
import com.example.androidclient.utilerias.WebViewClientImpl;

public class WebViewActivity extends AppCompatActivity {
    private WebView webView = null;
    String urlServidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        this.webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        urlServidor = getIntent().getExtras().getString("urlServidor");
        WebViewClientImpl webViewClient = new WebViewClientImpl(this, urlServidor);
        webView.setWebViewClient(webViewClient);
        webView.loadUrl(urlServidor);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
