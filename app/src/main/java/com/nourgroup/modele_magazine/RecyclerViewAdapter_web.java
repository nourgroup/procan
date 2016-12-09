package com.nourgroup.modele_magazine;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter_web extends RecyclerView.Adapter<RecyclerViewAdapter_web.ContactViewHolder> {
    ContactViewHolder holder1;
    List<webtext> contents=new ArrayList<webtext>();
    Activity activity;
    public RecyclerViewAdapter_web(List<webtext> contents, Activity activity) {
        this.contents = contents;
        this.activity = activity;
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public RecyclerViewAdapter_web.ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.webview, parent, false);
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(activity).defaultDisplayImageOptions(defaultOptions).build();
        ImageLoader.getInstance().init(config); // Do it on Application start
        return new RecyclerViewAdapter_web.ContactViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder1=holder;
        //webtext ci = contents.get(position);
        holder.webview.setWebViewClient(new Client());
        holder.webview.getSettings().setJavaScriptEnabled(true);
        holder.webview.loadUrl("https://www.facebook.com/Master-Production-de-Contenus-Audiovisuels-et-Numériques-Procan-1053479871370280");//,"text/html", "utf-8"
    }
    public static class ContactViewHolder extends RecyclerView.ViewHolder{
        public WebView webview;

        public View container;
        public ContactViewHolder(View v) {
            super(v);
            webview =  (WebView) v.findViewById(R.id.webview);

            this.container = v;
        }
    }
    class Client extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            //mProgressBar.setVisibility(View.GONE);
            super.onPageFinished(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //mProgressBar.setVisibility(View.VISIBLE);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            holder1.webview.loadUrl(url);
            return true;//super.shouldOverrideUrlLoading(view, url);
        }
    }
}
