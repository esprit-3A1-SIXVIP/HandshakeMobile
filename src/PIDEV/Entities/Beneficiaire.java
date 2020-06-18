/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Entities;

import java.util.Date;

/**
 *
 * @author amisa
 */
public class Beneficiaire {

    private int beneficiaireId;
    private int aideId;
    private String nomBeneficiaire;
    private String prenomBeneficiaire;
    private String email;
    private String Besoin;
    private Date dateNaissance;
    private String ville;
    private int telephone;
    String adresseGPS;
    private String role;

    public Beneficiaire(String nomBeneficiaire, String prenomBeneficiaire, String email, Date dateNaissance, String ville, int telephone,   String adresseGPS, String role) {
        this.nomBeneficiaire = nomBeneficiaire;
        this.prenomBeneficiaire = prenomBeneficiaire;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.ville = ville;
        this.telephone = telephone;
        this.adresseGPS=adresseGPS;
         this.role = role;
    }

    public Beneficiaire(int beneficiaireId, int aideId, String nomBeneficiaire, String prenomBeneficiaire, String email,Date dateNaissance, String ville, int telephone,   String adresseGPS, String role) {
        this.beneficiaireId = beneficiaireId;
        this.aideId = aideId;
        this.nomBeneficiaire = nomBeneficiaire;
        this.prenomBeneficiaire = prenomBeneficiaire;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.ville = ville;
        this.telephone = telephone;
         this.adresseGPS=adresseGPS;
        this.role = role;
    }

    public Beneficiaire(int aideId, String nomBeneficiaire, String prenomBeneficiaire, String email, Date dateNaissance, String ville, int telephone, String adresseGPS, String role) {
        this.aideId = aideId;
        this.nomBeneficiaire = nomBeneficiaire;
        this.prenomBeneficiaire = prenomBeneficiaire;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.ville = ville;
        this.telephone = telephone;
         this.adresseGPS=adresseGPS;
        this.role = role;
    }

    public Beneficiaire() {
    }

    public int getBeneficiaireId() {
        return beneficiaireId;
    }

    public int getAideId() {
        return aideId;
    }

    public String getNomBeneficiaire() {
        return nomBeneficiaire;
    }

    public String getPrenomBeneficiaire() {
        return prenomBeneficiaire;
    }

    public String getEmail() {
        return email;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getVille() {
        return ville;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getBesoin() {
        return Besoin;
    }

    public void setBesoin(String Besoin) {
        this.Besoin = Besoin;
    }

    public String getAdresseGPS() {
        return adresseGPS;
    }

    public void setAdresseGPS(String adresseGPS) {
        this.adresseGPS = adresseGPS;
    }

 


 

    public String getRole() {
        return role;
    }

    public void setBeneficiaireId(int beneficiaireId) {
        this.beneficiaireId = beneficiaireId;
    }

    public void setAideId(int aideId) {
        this.aideId = aideId;
    }

    public void setNomBeneficiaire(String nomBeneficiaire) {
        this.nomBeneficiaire = nomBeneficiaire;
    }

    public void setPrenomBeneficiaire(String prenomBeneficiaire) {
        this.prenomBeneficiaire = prenomBeneficiaire;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }


    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Beneficiaire{" + "beneficiaireId=" + beneficiaireId + ", aideId=" + aideId + ", nomBeneficiaire=" + nomBeneficiaire + ", prenomBeneficiaire=" + prenomBeneficiaire + ", email=" + email + ", Besoin=" + Besoin + ", dateNaissance=" + dateNaissance + ", ville=" + ville + ", telephone=" + telephone + ", adresseGPS=" + adresseGPS + ", role=" + role + '}';
    }

  

   

}
