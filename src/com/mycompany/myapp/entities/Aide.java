/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author SBS
 */
public class Aide {
    private int aideId;
    private String descriptionAide;
    private String localisationAide;
    private String categorieAide;
    private int statutAide;
    private User user;
    private int iduser;
    private int Latitude;
    private int Longitude;

    public Aide() {
    }

    public Aide(int aideId, String descriptionAide, String localisationAide, String categorieAide, int statutAide, User user, int Latitude, int Longitude) {
        this.aideId = aideId;
        this.descriptionAide = descriptionAide;
        this.localisationAide = localisationAide;
        this.categorieAide = categorieAide;
        this.statutAide = statutAide;
        this.user = user;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
    }

    public Aide(String descriptionAide, String localisationAide, String categorieAide, int statutAide, User user, int Latitude, int Longitude) {
        this.descriptionAide = descriptionAide;
        this.localisationAide = localisationAide;
        this.categorieAide = categorieAide;
        this.statutAide = statutAide;
        this.user = user;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
    }

    public Aide(String descriptionAide, String localisationAide, String categorieAide, int statutAide, int iduser) {
        this.descriptionAide = descriptionAide;
        this.localisationAide = localisationAide;
        this.categorieAide = categorieAide;
        this.statutAide = statutAide;
        this.iduser = iduser;
    }
    public Aide(int aideId, String descriptionAide, String localisationAide, String categorieAide) {
        this.aideId = aideId;
        this.descriptionAide = descriptionAide;
        this.localisationAide = localisationAide;
        this.categorieAide = categorieAide;
    }

    public Aide(int aideId) {
        this.aideId = aideId;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getAideId() {
        return aideId;
    }

    public void setAideId(int aideId) {
        this.aideId = aideId;
    }

    public String getDescriptionAide() {
        return descriptionAide;
    }

    public void setDescriptionAide(String descriptionAide) {
        this.descriptionAide = descriptionAide;
    }

    public String getLocalisationAide() {
        return localisationAide;
    }

    public void setLocalisationAide(String localisationAide) {
        this.localisationAide = localisationAide;
    }

    public String getCategorieAide() {
        return categorieAide;
    }

    public void setCategorieAide(String categorieAide) {
        this.categorieAide = categorieAide;
    }

    public int getStatutAide() {
        return statutAide;
    }

    public void setStatutAide(int statutAide) {
        this.statutAide = statutAide;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getLatitude() {
        return Latitude;
    }

    public void setLatitude(int Latitude) {
        this.Latitude = Latitude;
    }

    public int getLongitude() {
        return Longitude;
    }

    public void setLongitude(int Longitude) {
        this.Longitude = Longitude;
    }

    @Override
    public String toString() {
        return "Aide{" + "aideId=" + aideId + ", descriptionAide=" + descriptionAide + ", localisationAide=" + localisationAide + ", categorieAide=" + categorieAide + ", statutAide=" + statutAide + ", user=" + user + ", Latitude=" + Latitude + ", Longitude=" + Longitude + '}';
    }
    
}
