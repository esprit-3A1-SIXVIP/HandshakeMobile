/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.URL;
import com.codename1.io.URL.URLConnection;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.services.ServiceEvenement;
import com.mycompany.myapp.entities.Evenement;
import java.io.*;

import com.codename1.ui.EncodedImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import static com.codename1.ui.TextArea.URL;



/**
 *
 * @author Soreilla
 */
public class ListEvenementsForm extends Form{
    public ListEvenementsForm(Form previous) throws IOException{
        setTitle("List evenements");
             Form f =new Form("B");
      //  SpanLabel sp = new SpanLabel();
      //  sp.setText(ServiceEvenement.getInstance().getAllEvenements().toString());
       // add(sp); 
        
      
        
        Button creer = new Button("creer mes evenements");
        for( Evenement e : ServiceEvenement.getInstance().getAllEvenements()){
                     Button participer = new Button("partciper");

              Container container_description= new Container(BoxLayout.x());
                Container container_lieu = new Container(BoxLayout.x());
                 Container container_date = new Container(BoxLayout.x());
                     Container container_end = new Container(BoxLayout.x());
                 // Container container_prix = new Container(BoxLayout.x());
                      Container container_portee = new Container(BoxLayout.x());
                        Container container_image = new Container(BoxLayout.x());
                          
                            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
              container_description.add(e.getTitle());
                  container_lieu.add(e.getLieuEvenement());
                    container_date.add(formater.format(e.getStart()));
                   container_end.add(formater.format(e.getEnd()));
                      //  container_prix.add(e.getPrixEvenement());
                        container_portee.add(e.getPorteeEvenement());
                        //String url ="http://localhost/hdtest1.1/web/uploads/f_5eb195d353254.png";
                        
int deviceWidth = Display.getInstance().getDisplayWidth();
Image placeholder = Image.createImage(deviceWidth / 3, deviceWidth / 3, 0xbfc9d2); //square image set to 10% of screen width
EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                  
  //Image i = URLImage.createToStorage( placeholder, "fileNameInStorage", "http://localhost/hdtest1.1/web/uploads/f_5eb195d353254.png", URLImage.RESIZE_SCALE); 
                        
                       
                        
                        Image a =URLImage.createToStorage(encImage, e.getImage(), "http://localhost/hdtest1.1/web/uploads/"+ e.getImage(),URLImage.RESIZE_SCALE);
                        ImageViewer iv = new ImageViewer(a);
                        
                        
                        
                        
                        
                      
                          add(container_image);
                          add(iv);
                              add(container_description);
                         add(container_lieu);
                          add(container_portee);
                        add(container_date);
                         add(container_end);
                    add(participer);
                    participer.addActionListener(l->{
                  
                       f.add(iv);
                        Label bb = new Label("Vous participer mtn à cet évènements");
                        f.add(bb);
                      
                      });
            
        }
        add(creer);
      
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        add(f);
          f.show();
       
    }
    
}
