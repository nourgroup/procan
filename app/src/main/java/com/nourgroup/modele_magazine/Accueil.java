package com.nourgroup.modele_magazine;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.ArrayList;

public class Accueil extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    Document doc1,doc2;Elements slot1,slot2;String name;
    ArrayList<Information> info = new ArrayList<Information>();
    ProgressBar mProgressBar;
    public static Fragment newInstance(int sectionNumber) {
        Accueil fragment = new Accueil();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View Re = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView) Re.findViewById(R.id.recyclerView);
        mProgressBar = (ProgressBar) Re.findViewById(R.id.charge);
        //permet un affichage sous forme liste verticale
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        //info.add(new Information("a","a","a","a"));
        new HtmlText().execute();

        return Re;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //new HtmlText().execute();

        //penser à passer notre Adapter (ici : TestRecyclerViewAdapter) à un RecyclerViewMaterialAdapter

    }
    /***********************/
    private class HtmlText extends AsyncTask<URL, Integer, String> {
        protected String doInBackground(URL... urls) {
            info.add(new Information("Master Pro de Contenus Audiovisuels et Numériques - Procan",
                    "t","href","filiere Production de Contenus Audiovisuels et Numériques - Procan"));
            info.add(new Information("","Si vous les avez reconnus c'est parce qu'ils font partie des 10 logos les plus connus mondialement. ","","https://scontent-mrs1-1.xx.fbcdn.net/v/t1.0-9/15181145_1328270600557871_1931414823468934593_n.png?oh=b95b0806834729ccd0bf5cc71256b7dc&oe=58B4A93A"));
            info.add(new Information("","Fans de jeux vidéo ? Vous avez entre les mains les #10 dates clés qui ont marqué l'histoire du jeu vidéo. ","href","https://scontent-mrs1-1.xx.fbcdn.net/v/t1.0-0/p240x240/15171316_1330649320319999_8748013273588987241_n.png?oh=ba090f88059ce43063931629eeee5149&oe=58AE9C5E"));



            return name;
        }
        protected void onPostExecute(String result){
            mProgressBar.setVisibility(View.GONE);
            mAdapter = new RecyclerViewMaterialAdapter(new RecyclerViewAdapter(info,getActivity()));
            mRecyclerView.setAdapter(mAdapter);
            //notifier le MaterialViewPager qu'on va utiliser une RecyclerView
            MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);
        }
    }
}
