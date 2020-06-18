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
public class CommentaireArticle implements Comparable<CommentaireArticle> {

private int id;
private String description; 
private Article article;
private Date datePublication ;

    public CommentaireArticle(int id, String description, Article article) {
        this.id = id;
        this.description = description;
        this.article = article;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public CommentaireArticle(int id, String description, Article article, Date datePublication) {
        this.id = id;
        this.description = description;
        this.article = article;
        this.datePublication = datePublication;
    }

    public CommentaireArticle() {
          }

    @Override
    public String toString() {
        return "CommentaireArticle{" + "id=" + id + ", description=" + description + ", article=" + article.getId()  + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public int compareTo(CommentaireArticle o) {
       long diff = this.datePublication.getTime()-o.datePublication.getTime();
        int datediff=(int)diff;
     return (datediff);
    }

}