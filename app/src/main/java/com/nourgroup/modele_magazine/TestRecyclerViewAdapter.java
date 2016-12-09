package com.nourgroup.modele_magazine;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

public class TestRecyclerViewAdapter extends RecyclerView.Adapter<TestRecyclerViewAdapter.ContactViewHolder> {

    List<Information> contents=new ArrayList<Information>();
    Activity activity;
    public TestRecyclerViewAdapter(List<Information> contents,Activity activity) {
        this.contents = contents;
        this.activity = activity;
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public TestRecyclerViewAdapter.ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_card, parent, false);
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(activity).defaultDisplayImageOptions(defaultOptions).build();
        ImageLoader.getInstance().init(config); // Do it on Application start
        return new TestRecyclerViewAdapter.ContactViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {

        Information ci = contents.get(position);
        holder.titre.setText(ci.getTitre());
        holder.texte.setText(ci.getTexte());
        ImageLoader.getInstance().displayImage(ci.getImage(), holder.image, new ImageLoadingListener() {

            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {

            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });

    }
    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        public TextView texte;
        public TextView titre;
        public ImageView image;

        public View container;
        public ContactViewHolder(View v) {
            super(v);
            texte =  (TextView) v.findViewById(R.id.TvTexte);
            titre = (TextView)  v.findViewById(R.id.TvTitre);
            image = (ImageView) v.findViewById(R.id.IvActualite);

            this.container = v;
        }
    }

}
