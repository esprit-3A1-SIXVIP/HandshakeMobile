/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author med ali
 */
public class Article implements Comparable<Article>  {

 private int id;
 private String titre;
 private String description;
 private String auteur;
 private String photo;
 private Date datePublication ;
 private int nbrlike;


public Article (){


}

    public Article(int id, String titre, String description, String auteur,String photo, Date datePublication, int nbrlike) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.auteur = auteur;
        this.datePublication = datePublication;
        this.nbrlike = nbrlike;
        this.photo= photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Article(String titre, String description, String auteur) {
        this.titre = titre;
        this.description = description;
        this.auteur = auteur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public int getNbrlike() {
        return nbrlike;
    }

    public void setNbrlike(int nbrlike) {
        this.nbrlike = nbrlike;
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", auteur=" + auteur + ", datePublication=" + datePublication + ", nbrlike=" + nbrlike + '}';
    }

    

    @Override
    public int compareTo(Article o) {
        long diff = o.datePublication.getTime()-this.datePublication.getTime();
        int datediff=(int)diff;
     return (datediff);
    }


}