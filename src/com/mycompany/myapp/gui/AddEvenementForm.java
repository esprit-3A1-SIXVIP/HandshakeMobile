/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.ServiceEvenement;
//import java.time.LocalDate;
//import java.time.LocalTime;
import java.util.Date;

 

//import java.util.logging.Level;
//import java.util.logging.Logger;
import rest.file.uploader.tn.FileUploader;

//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author Soreilla
 */
public class AddEvenementForm extends Form{
    
    private FileUploader file;
   String fileNameInServer;
   private String imgPath;
   
    public AddEvenementForm(Form previous){
        setTitle("Créer un évènement");
        setLayout(BoxLayout.y());


              TextField tftitle = new TextField("","Titre de l'évènement");
        TextField tflieuevenement = new TextField("","Lieu de l'évènement");
        Picker tfstart = new Picker();
       tfstart.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        Picker tfend = new Picker();
         tfend.setType(Display.PICKER_TYPE_DATE_AND_TIME);
         
        tfend.setDate(new Date());
        TextField tfporteeevenement = new TextField("","Portée de l'évènement");
        TextField tfprixevenement = new TextField("","prix de l'évènement");
       // TextField tfuserid = new TextField ("","userId");
        TextField tfimage = new TextField("","Affiche de l'évènement");
        TextField tfcode = new TextField("","code de l'évènement");

  
        Button picture = new Button("parcourir");
        picture.setMaterialIcon(FontImage.MATERIAL_CLOUD_UPLOAD);
        picture.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {

                try{
                    imgPath = Capture.capturePhoto();
                    System.out.println(imgPath);
                    String link = imgPath.toString();
                    int pod = link.indexOf("/",2); 
                    String news = link.substring(pod +2, link.length());
                    System.out.println(""+ news);
                    
                    
                 FileUploader fu = new FileUploader("http://localhost/hdtest1.1/web");
                  //  FileUploader fu = new FileUploader("C:/Users/Soreilla/Documents/NetBeansProjects/handshakemobile/src");
               //C:\Users\Soreilla\Documents\NetBeansProjects\handshakemobile\src
                   
                            //   FileUploader fu = new FileUploader("/");
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                            }



            }
        
        });
        Button btnValider = new Button("Creer evenement");
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (
                            (tftitle.getText().length()==0 ) || 
                        (tflieuevenement.getText().length()==0) ||
                     
                       (tfporteeevenement.getText().length()==0) ||
                        (tfprixevenement.getText().length()==0) ||
                    
                       //  (tfimage.getText().length()==0) ||
                        (tfcode.getText().length()== 0) 
                       
                      //  (tftitle.getText().length()==0 )
                        )
                    
                      Dialog.show("Alert", "", new Command("OK"));
                else
                {
                
                                          
                  try{
                      
                  
                      
                     Evenement t; 
                      t = new Evenement(
                              tftitle.getText(),
                              tflieuevenement.getText(),
                            
                             tfstart.getDate(),
                              tfend.getDate(),
                              tfporteeevenement.getText(),
                              Integer.parseInt(tfprixevenement.getText()),
                              
                              tfimage.getText(),
                              tfcode.getText()
                              
                              
                              
                      );
                     
                      if(ServiceEvenement.getInstance().addEvenement(t))
                             //Dialog.show("Success", null, new Command("OK"));
                             Dialog.show("alert", " evenement ajouté", "", "ok"); 
                      else
                             Dialog.show("ERROR", "", new Command("OK"));
                      
                  }  catch(NumberFormatException e){
                      
                 
                  }
                }
            }
            });
           
        addAll(tftitle,tfstart,tfend,tfprixevenement,tfporteeevenement,tflieuevenement,tfcode,tfimage,picture,btnValider);
       
               getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

    }
    
}
