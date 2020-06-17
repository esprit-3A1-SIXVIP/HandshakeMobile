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
import com.mycompany.myapp.entities.Beneficiaire;
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
public class ServiceBeneficiaire {

    public ArrayList<Beneficiaire> bens;
    
    public static ServiceBeneficiaire instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ServiceUser su = new ServiceUser();
    public ServiceBeneficiaire() {
         req = new ConnectionRequest();
    }

    public static ServiceBeneficiaire getInstance() {
        if (instance == null) {
            instance = new ServiceBeneficiaire();
        }
        return instance;
    }
    


    public boolean addBen(Beneficiaire t) {
        String url = Statics.BASE_URL + "/ben/add?iduser="+t.getUserId()+ "&aide="+t.getAideId()+"&nom=" + t.getNomBeneficiaire()+ "&pre="+ t.getPrenomBeneficiaire()+ "&email=" + t.getEmail()+ "&datenaiss="+t.getDateNaissance()+ "&ville="+t.getVille()+ "&tel="+t.getTelephone()+ "&adress="+t.getAdresseGPS()+ "&role="+t.getRole()+ "&nat="+t.getNationalite()+ "&besoin="+t.getBesoin(); //création de l'URL
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
    public boolean editBen(Beneficiaire t) {
        String url = Statics.BASE_URL + "/ben/edit?id="+t.getBeneficiaireId()+"&iduser="+t.getUserId()+ "&aide="+t.getAideId()+"&nom="+ t.getNomBeneficiaire()+ "&pre="+ t.getPrenomBeneficiaire()+ "&email=" + t.getEmail()+ "&datenaiss="+t.getDateNaissance()+ "&ville="+t.getVille()+ "&tel="+t.getTelephone()+ "&adress="+t.getAdresseGPS()+ "&role="+t.getRole()+ "&nat="+t.getNationalite()+ "&besoin="+t.getBesoin(); //création de l'URL
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
    public boolean deleteBen(Beneficiaire t) {
        String url = Statics.BASE_URL + "/ben/del?id=" + t.getBeneficiaireId();
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

    public ArrayList<Beneficiaire> parseBen(String jsonText){
                try {
            bens=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Beneficiaire a = new Beneficiaire();
                float id = Float.parseFloat(obj.get("beneficiaireid").toString());
                a.setBeneficiaireId((int)id);
                String test = obj.get("user").toString();                
                float userid = Float.parseFloat(test.substring((test).indexOf("=")+1 ,(test).indexOf(",")));
                a.setUserId((int) userid); 
                test = obj.get("aide").toString();
                test=test.substring(test.indexOf("id=", test.indexOf("id=") + 1));
                float aideid = Float.parseFloat(test.substring((test).indexOf("id=")+3 ,(test).indexOf(", descriptionAide")));
                a.setAideId((int) aideid);                
                a.setNomBeneficiaire(obj.get("nombeneficiaire").toString());
                a.setPrenomBeneficiaire(obj.get("prenombeneficiaire").toString());
                float tel = Float.parseFloat(obj.get("telephone").toString());
                a.setTelephone((int)tel+1);
                a.setVille(obj.get("ville").toString());
                a.setEmail(obj.get("email").toString());
                a.setAdresseGPS(obj.get("adressegps").toString());
                a.setRole(obj.get("role").toString());
                a.setBesoin(obj.get("besoin").toString());
                a.setNationalite(obj.get("nationalite").toString());
                ArrayList <User> user = new ArrayList();
                user=su.getUser(a.getUserId());
                for (User u : user)
                {a.setUser(u);}
                      Map<String, Object> date = null;
                       date = (Map<String, Object>) obj.get("datenaissance");
                       try {

                          Date longdate = new Date((long) Float.parseFloat(date.get("timestamp").toString()) * 1000);
                             

                            System.out.println("***********" + longdate);
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

                            String d = formatter.format(longdate);
                            a.setDateNaissance(longdate);
                            System.out.println("");
                        } catch (NumberFormatException ex) {

                        }

                bens.add(a);
                           //     t.setDateabsence((Date) obj.get("dateabsence"));

            }
            
            
        } catch (IOException ex) {
            
        }
        return bens;
    }
              
                    

    
    public ArrayList<Beneficiaire> getAllBens(){
        String url = Statics.BASE_URL+"/affBenMob";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                bens = parseBen(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return bens;
    }

    public ArrayList<Beneficiaire> getBen(int id){
        String url = Statics.BASE_URL+"/ben/findBen/?id="+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                bens = parseBen(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return bens;
    }    
    public ArrayList<Beneficiaire> getMyBen(int id){
        String url = Statics.BASE_URL+"/ben/findmyBen/?id="+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                bens = parseBen(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        com.codename1.io.NetworkManager.getInstance().addToQueueAndWait(req);
        return bens;
    }
    
        public void sendMail() {

        Message m = new Message("<html><body>Check out <a href=\"https://www.codenameone.com/\">Codename One</a></body></html>");
        m.setMimeType(Message.MIME_HTML);

// notice that we provide a plain text alternative as well in the send method
        boolean success = m.sendMessageViaCloudSync("Codename One", "br.rassil@gmail.com", "Name Of User", "Message Subject",
                "Check out Codename One at https://www.codenameone.com/");
        System.out.println("success: " + success);
    }
        
}
