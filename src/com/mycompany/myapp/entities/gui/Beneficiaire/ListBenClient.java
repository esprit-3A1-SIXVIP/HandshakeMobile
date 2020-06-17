/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.gui.Beneficiaire;

import com.mycompany.myapp.entities.gui.Aide.*;
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
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Aide;
import com.mycompany.myapp.entities.Beneficiaire;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.gui.MenuPrincipal;
import com.mycompany.myapp.entities.services.ServiceAide;
import com.mycompany.myapp.entities.services.ServiceBeneficiaire;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author wajih
 */
public class ListBenClient extends Form{
        Form current;

        public ListBenClient(User u) {
        setTitle("Liste des Beneficiaires");
        

          Container co=new Container(BoxLayout.xCenter());;
                    ArrayList <Beneficiaire> bens = new ArrayList();
                        ServiceBeneficiaire sa =new ServiceBeneficiaire();
                    bens=sa.getAllBens();
                             for (Beneficiaire fi : bens) {
                            Container ct = new Container(BoxLayout.y());
                            Label l = new Label("ID : "+fi.getBeneficiaireId());
                            Label l2 = new Label("Nom Prenom : "+fi.getNomBeneficiaire()+" "+fi.getPrenomBeneficiaire(),"SmallLabel");
                            DateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
                            Date datecreation = new Date(System.currentTimeMillis());
                            String date= (String) fi.getDateNaissance().toString();

                            Label l3 = new Label("Date Naissance : "+date,"RedLabel");
                            Label l4 = new Label("Ville : "+fi.getVille(),"RedLabel");
                            Label l5 = new Label("Adresse : "+fi.getAdresseGPS(),"RedLabel");
                            Label l6 = new Label("Telephone : "+fi.getTelephone(),"RedLabel");
                            Label l7 = new Label("Role : "+fi.getRole(),"RedLabel");
                            Label l8;
                            if(fi.getRole().equals("besoin"))
                                 l8= new Label("Besoin : "+fi.getBesoin(),"RedLabel");
                            else
                            {
                                 l8= new Label("nationalite : "+fi.getNationalite(),"RedLabel");

                            }

                            l2.getAllStyles().setFgColor(0xf15f5f);
                            ct.add(l);
                            ct.add(l2);
                            ct.add(l3);
                            ct.add(l4);
                            ct.add(l5);
                            ct.add(l6);
                            ct.add(l7);
                            ct.add(l8);
                            Label separator = new Label("","Separator");
                            ct.add(separator);
                            add(ct);
                             }
        //Tool Bar
        getToolbar().addCommandToSideMenu("Home", null, e -> new MenuPrincipal().show());
        getToolbar().addCommandToSideMenu("Gestions des Aides", null, e -> new MenuAide(u).show());
        getToolbar().addCommandToSideMenu("Gestions des Beneficiaires", null, e -> new MenuBen(u).show());
    }

    
}
