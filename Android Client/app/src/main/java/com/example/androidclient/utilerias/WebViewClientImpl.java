package com.example.androidclient.utilerias;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class WebViewClientImpl extends WebViewClient {

    private Activity activity = null;
    private String urlServidor;

    public WebViewClientImpl(Activity activity, String urlServidor) {
        this.activity = activity;
        this.urlServidor = urlServidor.replace("http://","");
        String[] domain = this.urlServidor.split("/");
        this.urlServidor = domain[0];
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        if(url.indexOf(urlServidor) > -1 ) return false;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(intent);
        return true;
    }

}