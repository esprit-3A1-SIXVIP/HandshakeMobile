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
import com.mycompany.myapp.entities.CommentaireArticle;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ServiceCommentaireArticle {

    public ArrayList<CommentaireArticle> CommentaireArticles;
    
    public static ServiceCommentaireArticle instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCommentaireArticle() {
         req = new ConnectionRequest();
    }

    public static ServiceCommentaireArticle getInstance() {
        if (instance == null) {
            instance = new ServiceCommentaireArticle();
        }
        return instance;
    }

    public boolean addCommentaire(CommentaireArticle a) {
        String url = Statics.BASE_URL + "/article/mob/new" + a.getArticle()+ "/" + a.getDescription();
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

    public ArrayList<CommentaireArticle> parseCommentairesArticles(String jsonText){
        try {
            CommentaireArticles=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                CommentaireArticle A = new CommentaireArticle();
                float id = Float.parseFloat(obj.get("id").toString());
                A.setId((int)id);
              
             
                A.setDescription(obj.get("description").toString());
               Map<String,Object> list1 = (Map<String,Object>)obj.get("articleId");
              
                //
              Article A1 = new Article();
                float id1 = Float.parseFloat(list1.get("id").toString());
                A1.setId((int)id1);
            
                A1.setAuteur(list1.get("auteur").toString());
                A1.setDescription(list1.get("description").toString());
                A1.setTitre(list1.get("titre").toString());
                DateFormat format1 = new SimpleDateFormat("yyyy-mm-dd");
Date date1 = null;
                try {
                    date1 = format1.parse(list1.get("datePublication").toString());
                } catch (ParseException ex) {
                    System.out.println("st");        
                }

                      A1.setDatePublication(date1);
               
                
                A.setArticle(A1);
DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
Date date = null;
                try {
                    date = format.parse(obj.get("datePublication").toString());
                } catch (ParseException ex) {
                    System.out.println("st");        
                }

                      A.setDatePublication(date);
                
                CommentaireArticles.add(A);
            }
            
            
        } catch (IOException ex) {
            
        }
        return CommentaireArticles;
    }
    
    public ArrayList<CommentaireArticle> getAllCommentaireArticles(int id){
        String url = Statics.BASE_URL+"/article/mob/all";
        req.setUrl(url);
        req.setPost(false);
        req.addArgument("idArt", id+"");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CommentaireArticles = parseCommentairesArticles(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return CommentaireArticles;
    }
}
