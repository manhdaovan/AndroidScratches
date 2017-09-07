package com.manhdaovan.block07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {

    NumberPicker pages;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView);
        pages = (NumberPicker) findViewById(R.id.numberPicker);
        String[] pageTexts = {"Google Search", "Tinh Te", "Github", "Java Dev Notes"};

        pages.setDisplayedValues(pageTexts);
        pages.setMinValue(0);
        pages.setMaxValue(pageTexts.length - 1);
    }

    public void openWebpage(View v) {
        int selectedPage = pages.getValue();
        String pageUrl = "";

        switch (selectedPage) {
            case 0:
                pageUrl = "https://google.com";
                break;
            case 1:
                pageUrl = "https://tinhte.vn";
                break;
            case 2:
                pageUrl = "https://github.com/manhdaovan";
                break;
            case 3:
                pageUrl = "file:///android_asset/java_array_int.html";
                break;
        }
        if (pageUrl.length() > 0) {
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(pageUrl);
        }
    }
}
