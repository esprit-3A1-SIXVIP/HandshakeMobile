/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.JSONParser;

import com.codename1.ui.CN;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;


        
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Soreilla
 */
public class ServiceEvenement {
    public ArrayList<Evenement> evenements;
    
    public static ServiceEvenement instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    
    private ServiceEvenement(){
        req = new ConnectionRequest();
    }
    public static ServiceEvenement getInstance(){
        if(instance ==null){
            instance = new ServiceEvenement();
        }
        return instance;
    }
  public boolean addEvenement(Evenement t){
      String url = Statics.BASE_URL + "?title=" + t.getTitle() + "&lieuEvenement=" + t.getLieuEvenement()+"&start="+t.getStart()+"&end="+t.getEnd()+"&porteeEvenement=" +t.getPorteeEvenement()+"&prixEvenement="+t.getPrixEvenement()+"&image=" +t.getImage()+"&code=" +t.getCode();
              req.setUrl(url);
                   /* +t.getDateEvenement()+"/"
              +t.getHeureEvenement()+"/"
                    // +t.getUserId()+"/*/
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
    public ArrayList<Evenement> parseEvenements(String jsonText){
        
        try {
            evenements=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> evenementsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println("evenements:"+evenementsListJson);
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)evenementsListJson.get("root");
             for(Map<String,Object> obj : list){
                Evenement  t = new Evenement();
               
                float id = Float.parseFloat(obj.get("evenementId").toString());
                t.setEvenementId((int)id);
                 System.out.println(id);
                t.setTitle(obj.get("title").toString());
                
                  
                       t.setLieuEvenement("lieuEvenement");
                        Map<String, Object> date_deb  = (Map<String, Object>) obj.get("start");
                float date = Float.parseFloat(date_deb.get("timestamp").toString());
                Date d = new Date((long)(date-3600 )*1000);
                         Map<String, Object> heure_deb  = (Map<String, Object>) obj.get("end");
                float heure = Float.parseFloat(heure_deb.get("timestamp").toString());
                Date h = new Date((long)(date-3600 )*1000);  
                                        t.setPorteeEvenement(obj.get("porteeEvenement").toString());
                     t.setPrixEvenement(((int)Float.parseFloat(obj.get("prixEvenement").toString())));        
                       // t.setUserId(((int)Float.parseFloat(obj.get("userId").toString())));
                   t.setImage(obj.get("image").toString());
                    t.setCode(obj.get("code").toString());
                  
                 
                     
                       
              //continues ici
                evenements.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return evenements;
    }
  
     public ArrayList<Evenement> getAllEvenements(){
        String url = Statics.SHOW_URL;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                evenements = parseEvenements(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return evenements;
    }
      
    
     public void deleteEvenement(Evenement t){
           String url = Statics.DELETE_URL +  t.getEvenementId() ;
           req.setUrl(url);
           req.addResponseListener((a)->{
               String str = new String(req.getResponseData());
               System.out.println(str);
               System.out.println("L'identifiant est "+t.getEvenementId());
         
     });
      NetworkManager.getInstance().addToQueueAndWait(req);
     }
     public void updateEvenement(Evenement t){
        String url = Statics.UPDATE_URL + "?evenementId=" +t.getEvenementId()+"&title=" + t.getTitle() + "&lieuEvenement=" + t.getLieuEvenement()+"&start="+t.getStart()+"&end="+t.getEnd()+"&porteeEvenement=" +t.getPorteeEvenement()+"&prixEvenement="+t.getPrixEvenement()+"&image=" +t.getImage()+"&code=" +t.getCode()+"";
        req.setUrl(url);
          req.addResponseListener((a)-> {
           String str = new String(req.getResponseData());
             System.out.println(str);//Affichage de la réponse serveur sur la console
            System.out.println("id modif est"+t.getEvenementId());
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
     
        
     }
     public boolean participer(Evenement t,int id){
            String url = Statics.PARTICIPATION_URL +  "?evenementId=" +t.getEvenementId()+"&id="+id ;
             req.setUrl(url);
         req.addResponseListener((a)-> {
           String str = new String(req.getResponseData());
             System.out.println(str);//Affichage de la réponse serveur sur la console
            System.out.println("id modif est"+t.getEvenementId());
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
            
         
     }
    
     
}