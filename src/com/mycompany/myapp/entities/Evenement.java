/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

//import java.sql.Time;
//import java.time.LocalDate;
//import java.time.LocalTime;
import java.util.Date;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author Soreilla
 */
public class Evenement {
   private int evenementId;
   private String title;
   private String lieuEvenement;
     private Date start;
   private Date end;

   private String porteeEvenement;
   private int prixEvenement;
   private int userId;
   private String image;
   private String code;


   private FileUploader file;
   String fileNameInServer;
   private String imgPath;
  

    public Evenement() {
    }

    public Evenement(int evenementId, String title, String lieuEvenement, Date start, Date end, String porteeEvenement, int prixEvenement, String image, String code) {
        this.evenementId = evenementId;
        this.title = title;
        this.lieuEvenement = lieuEvenement;
         this.start = start;
        this.end = end;
        this.porteeEvenement = porteeEvenement;
        this.prixEvenement = prixEvenement;
        this.image = image;
        this.code = code;
      
      
      
    }

    public Evenement(String title, String lieuEvenement, String porteeEvenement, int prixEvenement,  String image, String code) {
           this.title = title;
        this.lieuEvenement = lieuEvenement;
        this.porteeEvenement = porteeEvenement;
        this.prixEvenement = prixEvenement;
        this.userId = userId;
        this.image = image;
        this.code = code;
        this.end = end;
        this.start = start;
     
    }

  

    public Evenement(String title, String lieuEvenement, Date start, Date end, String porteeEvenement, int prixEvenement, int userId, String image, String code) {
        this.title = title;
        this.lieuEvenement = lieuEvenement;
      
        this.porteeEvenement = porteeEvenement;
        this.prixEvenement = prixEvenement;
        this.userId = userId;
        this.image = image;
        this.code = code;
        this.end = end;
        this.start = start;
       
    }

   
    public Evenement(String title, String lieuEvenement, Date start, Date end, String porteeEvenement, int prixEvenement,  String image, String code) {
        this.title = title;
        this.lieuEvenement = lieuEvenement;
      
        this.porteeEvenement = porteeEvenement;
        this.prixEvenement = prixEvenement;
        this.userId = userId;
        this.image = image;
        this.code = code;
        this.end = end;
        this.start = start;
       
    }

   
    
    

    public int getEvenementId() {
        return evenementId;
    }

 
    public String getLieuEvenement() {
        return lieuEvenement;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public String getPorteeEvenement() {
        return porteeEvenement;
    }

    public int getPrixEvenement() {
        return prixEvenement;
    }

    public String getImage() {
        return image;
    }

    public String getCode() {
        return code;
    }

  

    public String getTitle() {
        return title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public void setEvenementId(int evenementId) {
        this.evenementId = evenementId;
    }

   

    public void setLieuEvenement(String lieuEvenement) {
        this.lieuEvenement = lieuEvenement;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setHeureEvenement(Date end) {
        this.end = end;
    }

    public void setPorteeEvenement(String porteeEvenement) {
        this.porteeEvenement = porteeEvenement;
    }

    public void setPrixEvenement(int prixEvenement) {
        this.prixEvenement = prixEvenement;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCode(String code) {
        this.code = code;
    }

  

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Evenement{" +  "Titre =" + title + ", lieu=" + lieuEvenement + ", date de début=" + start + ", date de fin=" + end + ", portée de l'évènement=" + porteeEvenement + ", prix=" + prixEvenement +  ", image=" + image + ", code=" + code +  '}';
    }

   
    
   
   
            
    
}
