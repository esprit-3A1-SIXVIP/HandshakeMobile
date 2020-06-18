/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import static com.codename1.io.Log.p;
import com.codename1.io.URL;
import com.codename1.io.URL.URLConnection;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
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
import static com.codename1.ui.TextArea.URL;
import com.codename1.ui.TextField;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Soreilla
 */
public class gestEventsForm extends Form {
    public gestEventsForm(Form previous) throws IOException{
         setTitle("Gestion des évenements");
         
          SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        Style s = UIManager.getInstance().getComponentStyle("Button");
FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT,s);
        EncodedImage placeholder = EncodedImage.createFromImage(p.scaled(p.getWidth() * 3, p.getHeight() * 4), false);
        
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        for(Evenement e: ServiceEvenement.getInstance().getAllEvenements()){
        
            data.add(createListEntry(e.getTitle(), formater.format(e.getStart()), "http://localhost/hdtest1.1/web/uploads/"+ e.getImage(),e.getImage(), Integer.toString(e.getEvenementId())));
            
        }
        
        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
MultiList ml = new MultiList(model);

ml.getUnselectedButton().setIconName("icon_URLImage");
ml.getSelectedButton().setIconName("icon_URLImage");
ml.getUnselectedButton().setIcon(placeholder);
ml.getSelectedButton().setIcon(placeholder);


ml.addActionListener(l->{
Form f =new Form("B");


Map<String, Object> value = (Map<String, Object>)ml.getSelectedItem();
        System.out.println( value.get("Line4"));
       String a = (String) value.get("Line4");
        System.out.println(a);
      
for (Evenement e:ServiceEvenement.getInstance().getAllEvenements()){
    if( e.getEvenementId()== Integer.parseInt(a))
    {
        Button update = new Button("Modifier");
          Button delete = new Button("Supprimer");
               TextField tftitle = new TextField(e.getTitle(),"Titre de l'évènement");
        TextField tflieuevenement = new TextField(e.getLieuEvenement(),"Lieu de l'évènement");
           Picker tfstart = new Picker();
           tfstart.setDate(new Date());
       tfstart.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        Picker tfend = new Picker();
         tfend.setType(Display.PICKER_TYPE_DATE_AND_TIME);
         
        tfend.setDate(new Date());
        TextField tfporteeevenement = new TextField(e.getPorteeEvenement(),"Portée de l'évènement");
        TextField tfprixevenement = new TextField(Integer.toString(e.getPrixEvenement()),"prix de l'évènement");
       // TextField tfuserid = new TextField ("","userId");
        TextField tfimage = new TextField(e.getImage(),"Affiche de l'évènement");
        TextField tfcode = new TextField(e.getCode(),"code de l'évènement");
        
        delete.addActionListener(z->{
            ServiceEvenement.getInstance().deleteEvenement(e);
                
        });
        
        update.addActionListener(v->{
            
            e.setTitle(tftitle.getText());
            e.setLieuEvenement(tflieuevenement.getText());
            e.setPrixEvenement(Integer.parseInt(tfprixevenement.getText()));
            e.setStart(tfstart.getDate());
            e.setHeureEvenement(tfend.getDate());
            e.setPorteeEvenement(tfporteeevenement.getText());
            e.setCode(tfcode.getText());
            e.setImage(tfimage.getText());
              ServiceEvenement.getInstance().updateEvenement(e);
            
            
        });
                        
                        f.add(tftitle);
                        f.add(tfstart);
                        f.add(tflieuevenement);
                        f.add(tfend);
                        f.add(tfporteeevenement);
                        f.add(tfprixevenement);
                        f.add(tfimage);
                        f.add(tfcode);
                        f.add(update);
                        f.add(delete);
    }
   
    
}
f.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
f.show();
});


add( ml);
getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
        
        
        
        
        
    }

   private Map<String, Object> createListEntry(String name, String date, String coverURL,String nameImage,String id) {
    Map<String, Object> entry = new HashMap<>();
    entry.put("Line1", name);
    entry.put("Line2", date);
    entry.put("icon_URLImage", coverURL);
    entry.put("icon_URLImageName", nameImage);
    entry.put("Line4", id);
 
    return entry;
    
      
      
}
    
}
