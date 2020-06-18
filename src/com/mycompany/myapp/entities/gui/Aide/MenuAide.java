/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.gui.Aide;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.gui.Beneficiaire.MenuBen;
import com.mycompany.myapp.entities.gui.MenuPrincipal;

/**
 *
 * @author wajih
 */
public class MenuAide extends Form{
    Form current;

    public MenuAide(User u) {
                current=this;
        setTitle("Gestions des Aides");
        setLayout(BoxLayout.y());
        
        //BUTTONS
        add(new Label("Choose an option"));
        Button btnAjoutAide = new Button("Ajouter Aide");
        Button btnListAides = new Button("Liste des Aides");
        Button btnListAidesApp = new Button("Liste des Aides Approuvée");
        Button btnMyAide= new Button("Mes Aides");
        Button btnStat= new Button("Stat");
        
        btnAjoutAide.addActionListener(e-> new AjoutAide(current,u).show());
        btnListAides.addActionListener(e-> new ListAide(u).show());
        btnListAidesApp.addActionListener(e-> new ListAideApp(u).show());
        btnMyAide.addActionListener(e-> new MyAide(u).show());
        btnStat.addActionListener(e-> new StatAide(u).show());
        
        //Tool Bar
        getToolbar().addCommandToSideMenu("Home", null, e -> new MenuPrincipal());
        getToolbar().addCommandToSideMenu("Gestions des Aides", null, e -> new MenuAide(u).show());
        getToolbar().addCommandToSideMenu("Gestions des Beneficiaires", null, e -> new MenuBen(u).show());

        addAll(btnAjoutAide,btnListAides,btnListAidesApp,btnMyAide,btnStat);

    }

}

