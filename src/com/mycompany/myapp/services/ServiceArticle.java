/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Map;





public class ServiceArticle {

    public ArrayList<Article> articles;
    public ArrayList<Article> articles1;
    public static ServiceArticle instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
     private ConnectionRequest req1;

    private ServiceArticle() {
         req = new ConnectionRequest();
         req1 = new ConnectionRequest();
    }

    public static ServiceArticle getInstance() {
        if (instance == null) {
            instance = new ServiceArticle();
        }
        return instance;
    }

    public boolean addArticle(Article a) {
        String url = Statics.BASE_URL + "/article/mob/new" + a.getAuteur() + "/" + a.getDescription()+ "/" + a.getTitre();
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

    public ArrayList<Article> parseArticles(String jsonText) {
        try {
            articles=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Article A = new Article();
                float id = Float.parseFloat(obj.get("id").toString());
                A.setId((int)id);
             if (obj.get("myfile")!=null) { A.setPhoto(obj.get("myfile").toString());}
                A.setAuteur(obj.get("auteur").toString());
                A.setDescription(obj.get("description").toString());
                A.setTitre(obj.get("titre").toString());
                DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
Date date = null;
                try {
                    date = format.parse(obj.get("datePublication").toString());
                } catch (ParseException ex) {
                    System.out.println("st");        
                }

                      A.setDatePublication(date);
               
              
                
                articles.add(A);
            }
            
            
        } catch (IOException ex) {
            
        }
        return articles;
    }
    
    public ArrayList<Article> getAllArticles(){
        String url = Statics.BASE_URL+"/article/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                articles = parseArticles(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return articles;
    }

    public ArrayList<Article> getSimilarArticles(String text) {
      
String url = Statics.BASE_URL+"/article/similar";
        req1.setUrl(url);
        req1.setPost(true);
        req1.addArgument("mot", text);
        req1.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                articles1 = parseArticles(new String(req1.getResponseData()));
                req1.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req1);
        return articles1;
        
    }
}
