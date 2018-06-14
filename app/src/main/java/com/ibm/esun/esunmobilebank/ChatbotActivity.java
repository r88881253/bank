package com.ibm.esun.esunmobilebank;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;


public class ChatbotActivity extends AppCompatActivity {
    private WebView chatbotWebview;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);
        initToolbar();
        initWebView();
    }

    private void initToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView custService = (TextView) findViewById(R.id.toolbar_custService);
        custService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something here
                Intent i = new Intent(ChatbotActivity.this, CustServiceActivity.class);
                startActivity(i);
            }
        });
    }

    private void initWebView(){
        chatbotWebview = (WebView) findViewById(R.id.chatbotWebview);
        // Configure related browser settings
        chatbotWebview.getSettings().setLoadsImagesAutomatically(true);
        chatbotWebview.getSettings().setJavaScriptEnabled(true);
        chatbotWebview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        // Configure the client to use when opening URLs
        chatbotWebview.setWebViewClient(new MyBrowser());
        // Load the initial URL
//        chatbotWebview.loadUrl("https://www.google.com");
        chatbotWebview.loadUrl("https://robot.esunbank.com.tw/index.php?eservice=ehome&qaCategory=general");
    }

    // Manages the behavior when URLs are loaded
    private class MyBrowser extends WebViewClient {
        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }
    }
}
