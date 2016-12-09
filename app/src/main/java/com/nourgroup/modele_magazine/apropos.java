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

public class apropos extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    Document doc1,doc2;Elements slot1,slot2;String name;
    ArrayList<Information> info = new ArrayList<Information>();
    ProgressBar mProgressBar;
    public static Fragment newInstance(int sectionNumber) {
        apropos fragment = new apropos();
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
            /*********************************/
            //info.add(new Information("titre","t","href","texte"));
            info.add(new Information("PROCAN", "\n" +

                    "L’Institut Supérieur de l’Information et de la Communication vient d’être accrédité pour ouvrir un Master spécialisé en \"Production des Contenus Audiovisuels et Numériques\" pour l’année universitaire 2015-2016.\n" +
                    "\n" +
                    "OBJECTIFS DE LA FORMATION\n" +
                    "\n" +
                    "Ce master permettra d’acquérir une formation opérationnelle, complète et approfondie en journalisme et communication audiovisuelle et numérique.\n" +
                    "Il se fixe donc pour objectif de :\n" +
                    "-Dispenser un enseignement supérieur spécialisé en communication audiovisuelle et numérique et de former des professionnels de l’information et de la communication\n" +
                    "-Donner aux futurs acteurs de la communication audiovisuelle les moyens d’orienter ou de réorienter leur pratique professionnelle à partir d’une meilleure compréhension des contextes et des enjeux d'émergence des cultures numériques, outiller ces futures acteurs par une formation solide, conceptuelle, analytique, méthodologique et pratique étendue dans le domaine du rédactionnel, de l’habillage, de la communication plurimédia et de la recherche scientifique en sciences de l’information et de la communication.","lien","FranceFootball.fr"));//slot.select("p.paragraph").get(i).text().substring(0, 80)
            /*try {
                Document doc  = Jsoup.connect("http://www.francefootball.fr").get();
                Elements slot = doc.select("div.slot.slot--newrow.slot--wide.coleader.js-linking");
                for(int i=0;i<slot.size();i++){
                    if(slot.get(i).select("img.js-lazy").get(0).absUrl("data-src").toString().contains(".jpg") ||slot.get(i).select("img.js-lazy").get(0).absUrl("data-src").toString().contains("jpeg")) {
                        //info.add(new information(slot.get(i).select("img.js-lazy").get(0).absUrl("data-src").toString(), slot.get(i).select("h2.heading a.js-linking-target").get(0).text(),slot.get(i).select("h2.heading a").get(0).absUrl("href"),"FranceFootball.fr"));//slot.select("p.paragraph").get(i).text().substring(0, 80)
                        info.add(new Information( slot.get(i).select("h2.heading a.js-linking-target").get(0).text(),slot.get(i).select("p").get(0).text(),slot.get(i).select("img.js-lazy").get(0).absUrl("data-src").toString(),slot.get(i).select("h2.heading a").get(0).absUrl("href")));//slot.select("p.paragraph").get(i).text().substring(0, 80)
                    }
                }
            } catch (Exception e){

            }*/
            //information(String titre, String texte, String numero, String href, String site)

            /*********************************/
            return name;
        }
        protected void onPostExecute(String result){
            mProgressBar.setVisibility(View.GONE);
            mAdapter = new RecyclerViewMaterialAdapter(new RecyclerViewAdapter_apropos(info,getActivity()));
            mRecyclerView.setAdapter(mAdapter);
            //notifier le MaterialViewPager qu'on va utiliser une RecyclerView
            MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);
        }
    }
}
