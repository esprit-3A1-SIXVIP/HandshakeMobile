/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.myApp.Utils.Statics;
import PIDEV.Entities.Refuge;
import com.codename1.ui.Dialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hp
 */
public class ServiceRefuge {

    public ArrayList<Refuge> Refuges;
    public boolean resultOK;
    public static ServiceRefuge instance;
    private final ConnectionRequest req;

    public ServiceRefuge() {
        req = new ConnectionRequest();

    }

    public static ServiceRefuge getInstance() {
        if (instance == null) {
            instance = new ServiceRefuge();
        }
        return instance;
    }

    public boolean addRefuge(Refuge t) {
        String url = Statics.BASE_URL + "new/don?rueRefuge=" + t.getRueRefuge() + "&villeRefuge=" + t.getVilleRefuge() + "&paysRefuge=" + t.getPaysRefuge() + "&disponibiliteRefuge=" + t.getDisponibiliteRefuge() + "&capaciteRefuge=" + t.getCapaciteRefuge() + "&dateDebutRefuge=" + t.getDate_debut() + "&dateFinRefuge=" + t.getDate_fin() + "&latitude=" + t.getLatitude() + "&longitude=" + t.getLongitude();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                String str = new String(req.getResponseData());//Récupération de la réponse du serveur
                System.out.println(str);//Affichage de la réponse serveur sur la console
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
//public ArrayList<Refuge> parseTasks(String jsonText)
//       {
//        try {
//            Refuges=new ArrayList<>();
//            JSONParser j=new JSONParser();
//            Map<String,Object> tasksListJson=j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            List <Map<String,Object>> list =(List<Map<String,Object>>)tasksListJson.get("root");
//            for(Map<String,Object> obj:list)
//            {
//                Refuge t=new Refuge();  
//                float id =Float.parseFloat(obj.get("donid").toString());
//                t.setDonId((int)id);
//                t.setCibleDon((obj.get("cibleDon").toString()));
//                t.setRueRefuge(obj.get("rueRefuge").toString());
//                tasks.add(t);
//                
//            }
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//       
//       return tasks;
//       
//               }
    public ArrayList<Refuge> parseRefuges(String jsonText) {

      
        try {
            Refuges= new ArrayList<>();
          //  if (!jsonText.equals("null")) {
                JSONParser j = new JSONParser();
                Map<String, Object> RefugeListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                List<Map<String, Object>> list = (List<Map<String, Object>>) RefugeListJson.get("root");
                for (Map<String, Object> obj : list) {
                    Refuge t = new Refuge();
                    t.setDonId((int) Float.parseFloat(obj.get("donid").toString()));
                   
                    t.setRueRefuge((String)obj.get("ruerefuge").toString());
                     
                    t.setVilleRefuge(obj.get("villerefuge").toString());
                    t.setPaysRefuge(obj.get("paysrefuge").toString());
                    float dis = Float.parseFloat(obj.get("disponibiliterefuge").toString());
                    t.setDisponibiliteRefuge((int) dis);
                    t.setCapaciteRefuge((int) Float.parseFloat(obj.get("capaciterefuge").toString()));
                    
//                    Map<String, Object> date_deb = (Map<String, Object>) obj.get("datedebutrefuge");
//                    float date = Float.parseFloat(date_deb.get("timestamp").toString());
//                    Date d = new Date((long) (date - 3600) * 1000);
//                    
//                    Map<String, Object> date_fin = (Map<String, Object>) obj.get("datefinrefuge");
//                    float date2 = Float.parseFloat(date_fin.get("timestamp").toString());
//                    Date d2 = new Date((long) (date2 - 3600) * 1000);
//                    t.setDate_debut(d);
//                    
//                    t.setDate_fin(d2);
                    t.setLatitude((Double) Double.parseDouble(obj.get("latitude").toString()));
                    t.setLongitude((Double) Double.parseDouble(obj.get("longitude").toString()));
                    System.out.println(t);
                    Refuges.add(t);

                }

//            } else {
//                Dialog.show("Error", "Wrong pasword / username", "Ok", "Cancel");
//            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return Refuges;

    }
    

    public ArrayList<Refuge> getAllRefuge() {
        String url = Statics.BASE_URL + "don/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Refuges = parseRefuges(new String(req.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Refuges;
    }

    public ArrayList<Refuge> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/HandshakeWebSym-Samyra/web/app_dev.php/don/all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceRefuge ser = new ServiceRefuge();
                Refuges = ser.parseRefuges(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return Refuges;
    }
    public boolean editAide(Refuge t) {
        String url = Statics.BASE_URL + "/don/edit_don?donid="+t.getDonId()+"&paysRefuge=" + t.getPaysRefuge()+ "&villeRefuge="+ t.getVilleRefuge()+ "&longitude=" + t.getLongitude()+"&latitude="+t.getLatitude()+"&disponibilite="+t.getDisponibiliteRefuge(); //création de l'URL
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
    public boolean deleteRefuge(Refuge t) {
        String url = Statics.BASE_URL + "don/delete_don/" + t.getDonId();
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

}
