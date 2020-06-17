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
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Soreilla
 */
public class ListEventsForm extends Form {
    public ListEventsForm(Form previous) throws IOException{
        Button creer= new Button("Créer mes évènements");
        Button participer= new Button("Participer");
          SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        Style s = UIManager.getInstance().getComponentStyle("Button");
FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT,s);
        EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 3, p.getHeight() * 4), false);
        
        ArrayList<Map<String, Object>> data = new ArrayList<>();
           for( Evenement e : ServiceEvenement.getInstance().getAllEvenements()){
               
                        data.add(createListEntry(e.getTitle(), formater.format(e.getStart()), "http://localhost/hdtest1.1/web/uploads/"+ e.getImage(),e.getImage(),participer));
                        
                        participer.addActionListener( l->{
                        Form f =new Form("B");
                        Label a = new Label("Vous participer mtn à cet évènements");
                        f.add(a);
                        f.show();
                        
                        });
   
               
           }
           
              DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
MultiList ml = new MultiList(model);

ml.getUnselectedButton().setIconName("icon_URLImage");
ml.getSelectedButton().setIconName("icon_URLImage");
ml.getUnselectedButton().setIcon(placeholder);
ml.getSelectedButton().setIcon(placeholder);   
           
add( ml);
getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
      
      
}
    

   private Map<String, Object> createListEntry(String name, String date, String coverURL,String nameImage,Button p) {
    Map<String, Object> entry = new HashMap<>();
    entry.put("Line1", name);
    entry.put("Line2", date);
    entry.put("icon_URLImage", coverURL);
    entry.put("icon_URLImageName", nameImage);
    entry.put("Line4", p);
 
    return entry;
   }
}

