/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.gui.Beneficiaire;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.gui.Aide.MenuAide;
import com.mycompany.myapp.entities.gui.MenuPrincipal;

/**
 *
 * @author wajih
 */
public class MenuBen extends Form{
    Form current;

    public MenuBen(User u) {
                current=this;
        setTitle("Gestions des Beneficiaires");
        setLayout(BoxLayout.y());
        
        //BUTTONS
        add(new Label("Choose an option"));
        Button btnAjoutBen = new Button("Ajouter Beneficiaires");
        Button btnListBen = new Button("Liste des Beneficiaires");
        Button btnListBenClient = new Button("Liste des Beneficiaires Client");
        Button btnMyBen = new Button("Mes Beneficiaires");
        
        btnAjoutBen.addActionListener(e-> new AjoutBen(current,u).show());
        btnListBen.addActionListener(e-> new ListBen(u).show());
        btnListBenClient.addActionListener(e-> new ListBenClient(u).show());
        btnMyBen.addActionListener(e-> new MyBen(u).show());
        
        //Tool Bar
        getToolbar().addCommandToSideMenu("Home", null, e -> new MenuPrincipal());
        getToolbar().addCommandToSideMenu("Gestions des Aides", null, e -> new MenuAide(u).show());
        getToolbar().addCommandToSideMenu("Gestions des Beneficiaires", null, e -> new MenuBen(u).show());

        addAll(btnAjoutBen,btnListBen,btnListBenClient,btnMyBen);

    }

}

