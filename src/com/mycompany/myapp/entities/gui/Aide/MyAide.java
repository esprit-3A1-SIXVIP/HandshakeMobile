/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.gui.Aide;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.mycompany.myapp.entities.gui.Aide.MenuAide;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Aide;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.gui.Beneficiaire.MenuBen;
import com.mycompany.myapp.entities.gui.MenuPrincipal;
import com.mycompany.myapp.entities.services.ServiceAide;
import java.util.ArrayList;

/**
 *
 * @author wajih
 */
public class MyAide extends Form{
        Form current;

        public MyAide(User u) {
        setTitle("List Aide");
        

          Container co;
                       //search
             Toolbar.setGlobalToolbar(true);
             add(new InfiniteProgress());
             
                Display.getInstance().scheduleBackgroundTask(()-> {
                    // this will take a while...
                    Display.getInstance().callSerially(() -> {
                    removeAll();
                    ArrayList <Aide> aides = new ArrayList();
                    ServiceAide sa =new ServiceAide();
                    aides=sa.getMyAide(u.getId());
                             for (Aide fi : aides) {
                            MultiButton m = new MultiButton();
                            m.setTextLine1("ID : "+String.valueOf(fi.getAideId()+" Categorie : "+fi.getCategorieAide()));
                            if(fi.getStatutAide()==0)
                            m.setTextLine2("Non Approuvé");
                            else{
                            m.setTextLine2("Approuvé");
                            }
                            m.setTextLine3("Localisation : "+fi.getLocalisationAide());
                            m.setTextLine4("DescriptionAide : "+fi.getDescriptionAide());
                            m.addPointerReleasedListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {               
                if (Dialog.show("Confirmation", "Voulez vous Supprimer cette Aide ?", "Supprimer", "Annuler")) {
                Aide t = new Aide(fi.getAideId());
                        if( ServiceAide.getInstance().deleteAide(t)){
                            {
                                Dialog.show("Success","supprimer",new Command("OK"));
                                new MyAide(u).show();
                            }
                   
                }
            }    
            }
        });
            m.addLongPressListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {               
                if (Dialog.show("Confirmation", "Voulez vous Modifier cette Aide ?", "Modifier ", "Annuler")) {
                    new EditAide(current, fi,u).show();
                }    
            }
        });

                            add(m);
                        }
                        revalidate();
                    });
                });
    getToolbar().addSearchCommand(e -> {
    String text = (String)e.getSource();
    if(text == null || text.length() == 0) {
        // clear search
        for(Component cmp : getContentPane()) {
            cmp.setHidden(false);
            cmp.setVisible(true);
        }
        getContentPane().animateLayout(150);
    } else {
        text = text.toLowerCase();
        for(Component cmp : getContentPane()) {
            MultiButton mb = (MultiButton)cmp;
            String line1 = mb.getTextLine1();
            String line2 = mb.getTextLine2();
            boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
            line2 != null && line2.toLowerCase().indexOf(text) > -1;
            mb.setHidden(!show);
            mb.setVisible(show);
        }
        getContentPane().animateLayout(150);
    }
}, 4);
        //Tool Bar
        getToolbar().addCommandToSideMenu("Home", null, e -> new MenuPrincipal());
        getToolbar().addCommandToSideMenu("Gestions des Aides", null, e -> new MenuAide(u).show());
        getToolbar().addCommandToSideMenu("Gestions des Beneficiaires", null, e -> new MenuBen(u).show());
    }

    
}
