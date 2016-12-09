package com.nourgroup.modele_magazine;

/**
 * Created by HP on 04/08/2016.
 */
public class Information {
    String texte,titre,url, image;

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTexte() {

        return texte;
    }

    public String getTitre() {
        return titre;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }

    public Information(String titre, String texte, String url, String image) {

        this.texte = texte;
        this.titre = titre;
        this.url = url;
        this.image = image;
    }
}
