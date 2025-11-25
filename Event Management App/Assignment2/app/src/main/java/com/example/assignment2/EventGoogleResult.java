package com.example.assignment2;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EventGoogleResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.event_google_result_app_bar_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbarGoogle = findViewById(R.id.toolbar_event_google_result);
        toolbarGoogle.setTitle("Google Search Result");
        setSupportActionBar(toolbarGoogle);


        WebView webView = findViewById(R.id.webView);

        String eventName = getIntent().getStringExtra(KeyStore.KEY_EVENT_NAME);

        String googlePageURL = "https://www.google.com/search?q=" + eventName;

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(googlePageURL);
    }
}