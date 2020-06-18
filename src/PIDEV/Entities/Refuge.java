/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Entities;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author amisa
 */
public class Refuge extends Dons {

    private String rueRefuge;
    private String villeRefuge;
    private String paysRefuge;
    private int disponibiliteRefuge;
    private int capaciteRefuge;    
    private Date date_debut;
    private Date date_fin;   
    private Double longitude;
    private Double latitude;

    public Refuge() {
        super();
    }

    public Refuge(int donId) {
        super(donId);
    }

    public Refuge(String rueRefuge, String villeRefuge, String paysRefuge, int disponibiliteRefuge, int capaciteRefuge, Date date_debut, Date date_fin, Double longitude, Double latitude) {
        this.rueRefuge = rueRefuge;
        this.villeRefuge = villeRefuge;
        this.paysRefuge = paysRefuge;
        this.disponibiliteRefuge = disponibiliteRefuge;
        this.capaciteRefuge = capaciteRefuge;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Refuge(String rueRefuge, String villeRefuge, String paysRefuge, int disponibiliteRefuge, int capaciteRefuge, String typeDon, int id, String cibleDon, int donId, Double longitude, Double latitude) {
        super(donId, typeDon, cibleDon, id);

        this.rueRefuge = rueRefuge;
        this.villeRefuge = villeRefuge;
        this.paysRefuge = paysRefuge;
        this.disponibiliteRefuge = disponibiliteRefuge;
        this.capaciteRefuge = capaciteRefuge;
    }

    public Refuge(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Refuge(String rueRefuge, String villeRefuge, String paysRefuge, int disponibiliteRefuge, int capaciteRefuge, Double longitude, Double latitude) {

        this.rueRefuge = rueRefuge;
        this.villeRefuge = villeRefuge;
        this.paysRefuge = paysRefuge;
        this.disponibiliteRefuge = disponibiliteRefuge;
        this.capaciteRefuge = capaciteRefuge;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Refuge(String rueRefuge, String villeRefuge, String paysRefuge, int capaciteRefuge, Date date_debut, Date date_fin, Double longitude, Double latitude, int donId, String typeDon, int userI) {
        super(donId, typeDon, userI);
        this.rueRefuge = rueRefuge;
        this.villeRefuge = villeRefuge;
        this.paysRefuge = paysRefuge;
        this.capaciteRefuge = capaciteRefuge;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Refuge(String rueRefuge, String villeRefuge, String paysRefuge, int disponibiliteRefuge, int capaciteRefuge, Date date_debut, Date date_fin, Double longitude, Double latitude, int donId, String typeDon, String cibleDon, int id) {
        super(donId, typeDon, cibleDon, id);
        this.rueRefuge = rueRefuge;
        this.villeRefuge = villeRefuge;
        this.paysRefuge = paysRefuge;
        this.disponibiliteRefuge = disponibiliteRefuge;
        this.capaciteRefuge = capaciteRefuge;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getDat() {
        return dat;
    }

    public void setDat(String dat) {
        this.dat = dat;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getRueRefuge() {
        return rueRefuge;
    }

    public void setRueRefuge(String rueRefuge) {
        this.rueRefuge = rueRefuge;
    }

    public String getVilleRefuge() {
        return villeRefuge;
    }

    public void setVilleRefuge(String villeRefuge) {
        this.villeRefuge = villeRefuge;
    }

    public String getPaysRefuge() {
        return paysRefuge;
    }

    public void setPaysRefuge(String paysRefuge) {
        this.paysRefuge = paysRefuge;
    }

    public int getDisponibiliteRefuge() {
        return disponibiliteRefuge;
    }

    public void setDisponibiliteRefuge(int disponibiliteRefuge) {
        this.disponibiliteRefuge = disponibiliteRefuge;
    }

    public int getCapaciteRefuge() {
        return capaciteRefuge;
    }

    public void setCapaciteRefuge(int capaciteRefuge) {
        this.capaciteRefuge = capaciteRefuge;
    }

    @Override
    public String toString() {
        return "\nRefuge{" + super.toString() + "rueRefuge=" + rueRefuge + ", villeRefuge=" + villeRefuge + ", paysRefuge=" + paysRefuge + ", disponibiliteRefuge=" + disponibiliteRefuge + ", capaciteRefuge=" + capaciteRefuge + '}';
    }
}
