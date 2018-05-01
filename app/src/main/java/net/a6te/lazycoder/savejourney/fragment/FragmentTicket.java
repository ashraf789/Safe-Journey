package net.a6te.lazycoder.savejourney.fragment;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.a6te.lazycoder.savejourney.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTicket extends Fragment {

    private View v;
    private WebView mWebView;
    private ProgressBar progressBar;
    private TextView loadingTv;
    private final String WEB_URL = "http://busbd.com.bd/";


    public FragmentTicket() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_fragment_ticket, container, false);
        mWebView = (WebView) v.findViewById(R.id.activity_main_webview);

        progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);
        loadingTv = (TextView) v.findViewById(R.id.LoadingText);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl(WEB_URL);
        mWebView.setWebViewClient(new HelloWebViewClient());
    }



    private class HelloWebViewClient extends WebViewClient {


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url)
        {
            webView.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            loadingTv.setVisibility(view.GONE);
            progressBar.setVisibility(view.GONE);
        }

    }


}
