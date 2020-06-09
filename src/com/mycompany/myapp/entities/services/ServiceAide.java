/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Aide;
import com.mycompany.myapp.entities.User;

import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SIHEM
 */
public class ServiceAide {

    public ArrayList<Aide> aides;
    
    public static ServiceAide instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ServiceUser su = new ServiceUser();
    public ServiceAide() {
         req = new ConnectionRequest();
    }

    public static ServiceAide getInstance() {
        if (instance == null) {
            instance = new ServiceAide();
        }
        return instance;
    }
    


    public boolean addAide(Aide t) {
        String url = Statics.BASE_URL + "/aides/add?iduser="+t.getIduser()+"&DescriptionAide=" + t.getDescriptionAide()+ "&LocalisationAide="+ t.getLocalisationAide()+ "&CategorieAide=" + t.getCategorieAide()+ "&StatuAide="+t.getStatutAide(); //création de l'URL
               req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public boolean editAide(Aide t) {
        String url = Statics.BASE_URL + "/aide/edit?id="+t.getAideId()+"&DescriptionAide=" + t.getDescriptionAide()+ "&LocalisationAide="+ t.getLocalisationAide()+ "&CategorieAide=" + t.getCategorieAide(); //création de l'URL
               req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public boolean deleteAide(Aide t) {
        String url = Statics.BASE_URL + "/aide/del?id=" + t.getAideId();
               req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Aide> parseAides(String jsonText){
                try {
            aides=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Aide a = new Aide();
                float id = Float.parseFloat(obj.get("id").toString());
                a.setAideId((int)id);
                String test = obj.get("user").toString();
                float userid = Float.parseFloat(test.substring((test).indexOf("=")+1 ,(test).indexOf(",")));
                a.setIduser((int) userid);                
                a.setDescriptionAide(obj.get("descriptionAide").toString());
                a.setLocalisationAide(obj.get("localisationAide").toString());
                a.setCategorieAide(obj.get("categorieAide").toString());
                float statuts = Float.parseFloat(obj.get("statutAide").toString());
                a.setStatutAide((int) statuts);
                ArrayList <User> user = new ArrayList();
                user=su.getUser(a.getIduser());
                for (User u : user)
                {a.setUser(u);}
                aides.add(a);
                           //     t.setDateabsence((Date) obj.get("dateabsence"));

            }
            
            
        } catch (IOException ex) {
            
        }
        return aides;
    }
              
                    

    
    public ArrayList<Aide> getAllAides(){
        String url = Statics.BASE_URL+"/affAideMob";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                aides = parseAides(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return aides;
    }

    public ArrayList<Aide> getAide(int id){
        String url = Statics.BASE_URL+"/aides/findAide/?id="+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                aides = parseAides(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return aides;
    }    
    public ArrayList<Aide> getMyAide(int id){
        String url = Statics.BASE_URL+"/aides/findmyAide/?id="+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                aides = parseAides(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return aides;
    }
    
            public ArrayList<Aide> getbyCat(String cat){
        String url = Statics.BASE_URL+"/findAideCatMob/?cat="+cat;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                aides = parseAides(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return aides;
    }
}
