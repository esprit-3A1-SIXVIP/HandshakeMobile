/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.gui.Aide;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Aide;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.services.ServiceAide;
import java.util.Date;

/**
 *
 * @author wajih
 */
public class AjoutAide extends Form{
    
        public AjoutAide(Form previous,User u) {
        setTitle("Ajouter une Aide");
        setLayout(BoxLayout.y());
        
        TextComponent tfDescription= new TextComponent().label("Description");
        TextComponent tfCategorie= new TextComponent().label("Categorie");
        TextComponent tfLocalisation= new TextComponent().label("Localisation");
       
        Button btnValider = new Button("Ajouter Aide");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfDescription.getText().length()==0)||(tfCategorie.getText().length()==0)||(tfLocalisation.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Aide e = new Aide(tfDescription.getText(),tfLocalisation.getText(),tfCategorie.getText(),0,u.getId());
                        if( ServiceAide.getInstance().addAide(e))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfDescription,tfCategorie,tfLocalisation,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }

   
}
