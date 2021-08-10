package com.manojbisht.input_order.explore;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.manojbisht.input_order.R;

public class villages extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_villages);

        webView = findViewById(R.id.villageWebView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://emanojbisht.github.io/default/villages.html");
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else {
            super.onBackPressed();
        }
    }
}
