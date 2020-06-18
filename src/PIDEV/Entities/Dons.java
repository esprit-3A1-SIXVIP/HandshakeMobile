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
public class Dons {
     private int donId;
    private String cibleDon;
    private String typeDon; 
    private Date dateDon = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String dat = dateFormat.format(dateDon);
    private int userI;

    public Dons() 
    {
    }
 
    public Dons(int donId) {
        this.donId = donId;
    }

    public Dons(int donId, String typeDon, int userI) {
        this.donId = donId;
        this.typeDon = typeDon;
        this.userI = userI;
    }

    public Dons(int donId, String typeDon, String cibleDon ,int id) {
        this.donId = donId;
        this.typeDon = typeDon;
        this.cibleDon = cibleDon;
        
        this.userI = id;
    }
    
     public Dons( String cibleDon,int id) {
        
        this.cibleDon = cibleDon;
        
        this.userI = id;
    }
     
      public Dons( String cibleDon) {
        
        this.cibleDon = cibleDon;
       
    }
     

    public int getDonId() {
        return donId;
    }

    public void setDonId(int donId) {
        this.donId = donId;
    }

    public String getTypeDon() {
        return typeDon;
    }

    public void setTypeDon(String typeDon) {
        this.typeDon = typeDon;
    }

    public String getCibleDon() {
        return cibleDon;
    }

    public void setCibleDon(String cibleDon) {
        this.cibleDon = cibleDon;
    }

    public Date getDateDon() {
        return dateDon;
    }

    public void setDateDon(Date dateDon) {
        this.dateDon = dateDon;
    }

    public int getUserId() {
       
        return userI;
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

    public int getUserI() {
        return userI;
    }

    public void setUserI(int userI) {
        this.userI = userI;
    }

    
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.donId;
        return hash;
    }

   

    @Override
    public String toString() {
        return " idDon = " + donId +", userId ="+userI+ ", type = " + typeDon +", Cible = " + cibleDon+ ", Date = "+dateDon;
    }
    
    
}
