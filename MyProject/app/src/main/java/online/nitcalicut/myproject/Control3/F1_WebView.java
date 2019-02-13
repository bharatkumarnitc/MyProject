package online.nitcalicut.myproject.Control3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import online.nitcalicut.myproject.R;

public class F1_WebView extends AppCompatActivity {
    WebView w1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f1__web_view);

        w1 = (WebView) findViewById(R.id.F1_WebView);


        w1.getSettings().setLoadsImagesAutomatically(true);
        w1.getSettings().setJavaScriptEnabled(true);
        w1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        w1.getSettings().setBuiltInZoomControls(true);
        w1.getSettings().setDisplayZoomControls(false);

        w1.setWebViewClient(new MyBrowser());

        w1.loadUrl("file:///android_asset/privacypolicy.htm");


    }
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}

