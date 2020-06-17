/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author SBS
 */
public class Beneficiaire {
    private int beneficiaireId;
    private String nomBeneficiaire;
    private String prenomBeneficiaire;
    private String email;
    private Date dateNaissance;
    private String ville;
    private int telephone;
    private String adresseGPS;
    private String role;
    private String besoin;
    private String nationalite;
    private int userId;
    private User user;
    private int aideId;
    private Aide aide;
    public Beneficiaire() {
    }

    public Beneficiaire(String nomBeneficiaire, String prenomBeneficiaire, String email, Date dateNaissance, String ville, int telephone, String adresseGPS, String role, int userId) {
        this.nomBeneficiaire = nomBeneficiaire;
        this.prenomBeneficiaire = prenomBeneficiaire;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.ville = ville;
        this.telephone = telephone;
        this.adresseGPS = adresseGPS;
        this.role = role;
        this.userId = userId;
     }
    
    public Beneficiaire(int beneficiaireId, String nomBeneficiaire, String prenomBeneficiaire, String email, Date dateNaissance, String ville, int telephone, String adresseGPS, String role, String besoin, String nationalite, User user, Aide aide) {
        this.beneficiaireId = beneficiaireId;
        this.nomBeneficiaire = nomBeneficiaire;
        this.prenomBeneficiaire = prenomBeneficiaire;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.ville = ville;
        this.telephone = telephone;
        this.adresseGPS = adresseGPS;
        this.role = role;
        this.besoin = besoin;
        this.nationalite = nationalite;
        this.user = user;
    }

    public Beneficiaire(int beneficiaireId) {
        this.beneficiaireId = beneficiaireId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Aide getAide() {
        return aide;
    }

    public void setAide(Aide aide) {
        this.aide = aide;
    }

    public int getBeneficiaireId() {
        return beneficiaireId;
    }

    public void setBeneficiaireId(int beneficiaireId) {
        this.beneficiaireId = beneficiaireId;
    }

    public String getNomBeneficiaire() {
        return nomBeneficiaire;
    }

    public void setNomBeneficiaire(String nomBeneficiaire) {
        this.nomBeneficiaire = nomBeneficiaire;
    }

    public String getPrenomBeneficiaire() {
        return prenomBeneficiaire;
    }

    public void setPrenomBeneficiaire(String prenomBeneficiaire) {
        this.prenomBeneficiaire = prenomBeneficiaire;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
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

    public void setRole(String role) {
        this.role = role;
    }

    public String getBesoin() {
        return besoin;
    }

    public void setBesoin(String besoin) {
        this.besoin = besoin;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAideId() {
        return aideId;
    }

    public void setAideId(int aideId) {
        this.aideId = aideId;
    }

    @Override
    public String toString() {
        return "Beneficiaire{" + "beneficiaireId=" + beneficiaireId + ", nomBeneficiaire=" + nomBeneficiaire + ", prenomBeneficiaire=" + prenomBeneficiaire + ", email=" + email + ", dateNaissance=" + dateNaissance + ", ville=" + ville + ", telephone=" + telephone + ", adresseGPS=" + adresseGPS + ", role=" + role + ", besoin=" + besoin + ", nationalite=" + nationalite + ", userId=" + userId + ", aideId=" + aideId + '}';
    }
    
    
}
