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
import com.mycompany.myapp.services.*;
import com.mycompany.myapp.entities.*;
import java.io.*;

import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import static com.codename1.ui.TextArea.URL;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;



/**
 *
 * @author Soreilla
 */
public class ListEvenementsForm extends Form{
    public ListEvenementsForm(Form previous) throws IOException{
    
         
      //  SpanLabel sp = new SpanLabel();
      //  sp.setText(ServiceEvenement.getInstance().getAllEvenements().toString());
       // add(sp); 
       //
 
        //
      
  
          
        Button creer = new Button("creer mes evenements");
        for( Evenement e : ServiceEvenement.getInstance().getAllEvenements()){
            
            
           
               Container cp= new Container(BoxLayout.y());
                      Container btn =new Container(BoxLayout.x());
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
                        
                        
                        
                        
                        
                      
                        cp.add(container_image);
                          cp.add(iv);
                             cp.add(container_description);
                         cp.add(container_lieu);
                          cp.add(container_portee);
                        cp.add(container_date);
                        cp.add(container_end);
                        if(ServiceUser.getInstance().verification(4, e.getEvenementId()))
               {
                   Slider osdk=createStarRankSlider();
                         cp.add(FlowLayout.encloseCenter(osdk));
                      int vvv  = osdk.getScrollX();
                      System.out.println(vvv);
                      
                      
                        
                        
               }else{              Button participer = new Button("partciper");
                         btn.add(participer);
                            cp.add(btn);
                            
                                 participer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent l) {
                    ServiceEvenement.getInstance().participer(e,4);
                  
                        btn.remove();
                        Dialog dlg = new Dialog("Félicitaions");
dlg.setLayout(new BorderLayout());
dlg.addComponent(BorderLayout.EAST, new SpanLabel("Vous participez maintenant à cet évènement", "DialogBody"));
// span label accepts the text and the UIID for the dialog body

int h = Display.getInstance().getDisplayHeight();
dlg.setDisposeWhenPointerOutOfBounds(true);
dlg.show(h /8 * 3, 0, 0, 0);
                    
                }
                
               
            });
                     }
                        add(cp);
                        }
           
               getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evv-> previous.showBack());
     
           
            
       }
      
                  
     
            
      
   
       
    
    
          private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
}

private Slider createStarRankSlider() {
    Slider starRank = new Slider();
    starRank.setEditable(true);
    starRank.setMinValue(0);
    starRank.setMaxValue(10);
    Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
            derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
    Style s = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    s.setOpacity(100);
    s.setFgColor(0);
    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
    return starRank;
}
    
}
