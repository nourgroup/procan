package com.nourgroup.modele_magazine;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    SwipeRefreshLayout mSwipeRefreshLayout;
    private static final String ARG_SECTION_NUMBER = "section_number";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    Document doc1,doc2;Elements slot1,slot2;String name;
    ArrayList<Information> info = new ArrayList<Information>();
    public static Fragment newInstance(int sectionNumber) {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View Re = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView) Re.findViewById(R.id.recyclerView);

        //permet un affichage sous forme liste verticale
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mSwipeRefreshLayout = (SwipeRefreshLayout) Re.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        new HtmlText().execute();

        return Re;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //new HtmlText().execute();
        //penser à passer notre Adapter (ici : TestRecyclerViewAdapter) à un RecyclerViewMaterialAdapter
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                //avertie le SwipeRefreshLayout que la mise à jour a été effectuée
                new HtmlText().execute();
            }
        },2000);// appellé après 2000 ms
    }

    /***********************/
    private class HtmlText extends AsyncTask<URL, Integer, String> {
        protected String doInBackground(URL... urls) {
//http://www.press-directory.com/presse-magazine/magazine-sport.html

            /*** le code est separer car on le regroupant ne fonctionne pas
             * certainement a cause de l'exception qui s'eleves (pas sure)
             */

            /*try{
                doc1  = Jsoup.connect("http://www.azaghare24.com/?cat=6").get();
                slot1 = doc1.select("div.cat-slider-item");
                for(int i=0;i<slot1.size();i++){
                        name = slot1.select("img").get(i).attr("src");

                }
            }catch(Exception s){}*/

            /*********************************/
            try{
                String url = "http://www.azaghare24.com/?cat=6";
                doc1 = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);//ISO-8859-1
                slot1 = doc1.select("div.cat-slider-item");
                for(int i=0;i<slot1.size();i++){
                    name = slot1.select("img").get(i).attr("src");
                    info.add(new Information("a",slot1.select("h2.mob-title").get(i).text(),slot1.select("a").get(i).absUrl("href"),name));
                }
            }catch(Exception s){}
            return name;
        }
        protected void onPostExecute(String result){
            //name=result;
            mSwipeRefreshLayout.setRefreshing(false);
            mAdapter = new RecyclerViewMaterialAdapter(new TestRecyclerViewAdapter(info,getActivity()));
            mRecyclerView.setAdapter(mAdapter);
            //notifier le MaterialViewPager qu'on va utiliser une RecyclerView
            MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);

        }
    }
}
