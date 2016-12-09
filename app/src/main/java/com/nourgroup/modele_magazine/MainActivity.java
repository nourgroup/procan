package com.nourgroup.modele_magazine;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.materialviewpager.MaterialViewPager;

public class MainActivity extends AppCompatActivity {

    MaterialViewPager materialViewPager;
    View headerLogo;
    ImageView headerLogoContent;
    Fragment a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        headerLogo = findViewById(R.id.headerLogo);
        headerLogoContent = (ImageView) findViewById(R.id.headerLogoContent);
        this.materialViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        this.materialViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                //je créé pour chaque onglet un RecyclerView Fragment
                getPageTitle(position);
                switch (position){
                    case 0:
                        return Accueil.newInstance(position);
                    case 1:
                        return pagefacebook.newInstance(position);
                    case 2:
                        return publier.newInstance(position);
                    case 3:
                        return apropos.newInstance(position);
                }

                return Accueil.newInstance(position);
            }

            @Override
            public int getCount() {
                return 4;
            }

            //le titre à afficher pour chaque page
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return getResources().getString(R.string.one);
                    case 1:
                        return getResources().getString(R.string.two);
                    case 2:
                        return getResources().getString(R.string.three);
                    case 3:
                        return getResources().getString(R.string.foor);
                    default:
                        return "Page " + position;
                }
            }
            /******/
            int oldItemPosition = -1;

            @Override
            public void setPrimaryItem(ViewGroup container, int position, Object object) {
                super.setPrimaryItem(container, position, object);

//seulement si la page est différente
                if (oldItemPosition != position) {
                    oldItemPosition = position;

                    //définir la nouvelle couleur et les nouvelles images
                    String imageUrl = null;
                    int color = Color.BLACK;
                    Drawable newDrawable = null;

                    switch (position) {
                        case 0:
                            imageUrl = "http://dev-android.esy.es/procan.png";
                            color = getResources().getColor(R.color.purple);
                            newDrawable = getResources().getDrawable(R.drawable.mediathequee);
                            break;
                        case 1:
                            imageUrl = "http://dev-android.esy.es/procan.png";
                            color = getResources().getColor(R.color.orange);
                            newDrawable = getResources().getDrawable(R.drawable.mediathequee);
                            break;
                        case 2:
                            imageUrl = "http://dev-android.esy.es/procan.png";
                            color = getResources().getColor(R.color.cyan);
                            newDrawable = getResources().getDrawable(R.drawable.mediathequee);
                            break;
                        case 3:
                            imageUrl = "http://dev-android.esy.es/procan.png";
                            color = getResources().getColor(R.color.green);
                            newDrawable = getResources().getDrawable(R.drawable.mediathequee);
                            break;
                    }

                    //puis modifier les images/couleurs
                    int fadeDuration = 400;
                    materialViewPager.setColor(color, fadeDuration);
                    materialViewPager.setImageUrl(imageUrl, fadeDuration);
                    toggleLogo(newDrawable,color,fadeDuration);
                }}
            /*****/
            private void toggleLogo(final Drawable newLogo, final int newColor, int duration){

                //animation de disparition
                final AnimatorSet animatorSetDisappear = new AnimatorSet();
                animatorSetDisappear.setDuration(duration);
                animatorSetDisappear.playTogether(
                        ObjectAnimator.ofFloat(headerLogo, "scaleX", 0),
                        ObjectAnimator.ofFloat(headerLogo, "scaleY", 0)
                );

                //animation d'apparition
                final AnimatorSet animatorSetAppear = new AnimatorSet();
                animatorSetAppear.setDuration(duration);
                animatorSetAppear.playTogether(
                        ObjectAnimator.ofFloat(headerLogo, "scaleX", 1),
                        ObjectAnimator.ofFloat(headerLogo, "scaleY", 1)
                );

                //après la disparition
                animatorSetDisappear.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        //modifie la couleur du cercle
                        ((GradientDrawable) headerLogo.getBackground()).setColor(newColor);

                        //modifie l'image contenue dans le cercle
                        headerLogoContent.setImageDrawable(newLogo);

                        //démarre l'animation d'apparition
                        animatorSetAppear.start();
                    }
                });

                //démarre l'animation de disparition
                animatorSetDisappear.start();
            }
        });
//permet au viewPager de garder 4 pages en mémoire (à ne pas utiliser sur plus de 4 pages !)
        this.materialViewPager.getViewPager().setOffscreenPageLimit(4);
        //relie les tabs au viewpager
        this.materialViewPager.getPagerTitleStrip().setViewPager(this.materialViewPager.getViewPager());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */

    @Override
    public void onBackPressed() {

        //if(getSupportFragmentManager().findFragmentById(R.id.rl1) instanceof webview){
            pagef f= new pagef();
            if(f.canGoBack())
            {f.goBack();}
            else{
                super.onBackPressed();
            }
        //}


    }

}
