/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp.entities;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 *
 * @author steph
 */
public class User {

    long userId;
    String login;
    String password;
    String nomUser;
    String prenomUser;
    String email;
    int telephone;
    String profil;
    String ville;
    String rue;
    String pays;
    String role;
    int accesShakeHub;
    String nomOrganisation;
    String domaine;
    String res;
    private int id;
    private String username;

    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(String email, String nomOrganisation, String domaine, String ville, String pays) {
        this.email = email;
        this.nomOrganisation = nomOrganisation;
        this.domaine = domaine;
        this.ville = ville;
        this.pays = pays;
    }

    public User(long userId, String login, String password, String email, String role) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User(long userId, String login, String password, String email, String role, int accesShakeHub) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.accesShakeHub = accesShakeHub;
    }

    public User(String email, String ville, String pays) {

        this.email = email;
        this.ville = ville;
        this.pays = pays;
    }

    public User(long userId, String login, String password, String nomUser, String prenomUser, String email, int telephone, String ville, String rue, String pays, String role) {
        this.userId = userId;
        this.login = login;
        this.login = login;
        this.password = password;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.email = email;
        this.telephone = telephone;
        this.ville = ville;
        this.rue = rue;
        this.pays = pays;
        this.role = role;
    }

    public User(String login, String password, String nomUser, String prenomUser, String email, int telephone, String ville, String rue, String pays, String profil) {

        this.login = login;
        this.password = password;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.email = email;
        this.telephone = telephone;
        this.ville = ville;
        this.rue = rue;
        this.pays = pays;
        this.profil = profil;
    }

    public User(long userId, String login, String password, String nomUser, String prenomUser, String email, int telephone, String ville, String rue, String pays, String role, String profil) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.email = email;
        this.telephone = telephone;
        this.ville = ville;
        this.rue = rue;
        this.pays = pays;
        this.role = role;
        this.profil = profil;
    }

    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public User(User U) {
        this.userId = U.getUserId();
        this.login = U.getLogin();
        this.password = U.getPassword();
        this.nomUser = U.getNomUser();
        this.prenomUser = U.getPrenomUser();
        this.email = U.getEmail();
        this.telephone = U.getTelephone();
        this.profil = U.getProfil();
        this.ville = U.getVille();
        this.rue = U.getRue();
        this.pays = U.getPays();
        this.role = U.getRole();
        this.accesShakeHub = U.isAccesShakeHub();
        this.nomOrganisation = U.getNomOrganisation();
        this.domaine = U.getDomaine();
    }

    public String getNomOrganisation() {
        return nomOrganisation;
    }

    public String getDomaine() {
        return domaine;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPrenomUser() {
        return prenomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", login=" + login + ", password=" + password + ", nomUser=" + nomUser + ", prenomUser=" + prenomUser + ", email=" + email + ", telephone=" + telephone + ", profil=" + profil + ", ville=" + ville + ", rue=" + rue + ", pays=" + pays + ", role=" + role + ", accesShakeHub=" + accesShakeHub + ", nomOrganisation=" + nomOrganisation + ", domaine=" + domaine + ", res=" + res + '}';
    }

    

    public int isAccesShakeHub() {
        return accesShakeHub;
    }

    public void setAccesShakeHub(int accesShakeHub) {
        this.accesShakeHub = accesShakeHub;
    }

    public void parse(String s) {
        this.userId = Integer.parseInt(s.substring(s.indexOf("userId=") + "userId=".length(), s.indexOf(",login=")));
        this.login = s.substring(s.indexOf("login=") + "login=".length(), s.indexOf(",password="));
        this.password = s.substring(s.indexOf("password=") + "password=".length(), s.indexOf(",nomUser="));
        this.nomUser = s.substring(s.indexOf("nomUser=") + "nomUser=".length(), s.indexOf(",prenomUser="));
        this.prenomUser = s.substring(s.indexOf("prenomUser=") + "prenomUser=".length(), s.indexOf(",email="));
        this.email = s.substring(s.indexOf("email=") + "email=".length(), s.indexOf(",telephone="));
        this.telephone = Integer.parseInt(s.substring(s.indexOf("telephone=") + "telephone=".length(), s.indexOf(",ville=")));
        this.ville = s.substring(s.indexOf("ville=") + "ville=".length(), s.indexOf(",rue="));
        this.rue = s.substring(s.indexOf("rue=") + "rue=".length(), s.indexOf(",pays="));
        this.pays = s.substring(s.indexOf("pays=") + "pays=".length(), s.indexOf(",role="));
        this.role = s.substring(s.indexOf("role=") + "role=".length(), s.indexOf(",profil="));
        this.profil= s.substring(s.indexOf("profil=")+ "profil=".length(),s.indexOf(",accesShakeHub="));
        this.accesShakeHub = Integer.parseInt(s.substring(s.indexOf("accesShakeHub=") + "accesShakeHub=".length(), s.length()));
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        return true;
    }

}