/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.gui;

import com.mycompany.myapp.entities.gui.Aide.MenuAide;
import com.mycompany.myapp.entities.gui.Beneficiaire.MenuBen;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.User;

/**
 *
 * @author wajih
 */
public class MenuAideBen extends Form{
    Form current;

    public MenuAideBen() {
                current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
                User u = new User(2, "junior");

        //BUTTONS
        add(new Label("Choisissez une option"));
        Button btnAide = new Button("Gestions des Aides");
        Button btnBen = new Button("Gestions des Beneficiaires");
        
        btnAide.addActionListener(e-> new MenuAide(u).show());
        btnBen.addActionListener(e-> new MenuBen(u).show());
        
        //Tool Bar
        getToolbar().addCommandToSideMenu("Home", null, e -> new MenuAideBen().show());
        getToolbar().addCommandToSideMenu("Gestions des Aides", null, e -> new MenuAide(u).show());
        getToolbar().addCommandToSideMenu("Gestions des Beneficiaires", null, e -> new MenuBen(u).show());

        addAll(btnAide,btnBen);

    }

}

