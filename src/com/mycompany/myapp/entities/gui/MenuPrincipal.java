/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
<<<<<<< Updated upstream:src/com/mycompany/myapp/entities/gui/MenuPrincipal.java
package com.mycompany.myapp.entities.gui;
=======
package com.mycompany.myapp;
>>>>>>> Stashed changes:src/com/mycompany/myapp/MenuPrincipal.java

import com.mycompany.myapp.entities.gui.Aide.MenuAide;
import com.mycompany.myapp.entities.gui.Beneficiaire.MenuBen;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.User;
<<<<<<< Updated upstream:src/com/mycompany/myapp/entities/gui/MenuPrincipal.java
=======
import com.mycompany.myapp.entities.gui.ShakeHub;
>>>>>>> Stashed changes:src/com/mycompany/myapp/MenuPrincipal.java

/**
 *
 * @author wajih
 */
public class MenuPrincipal extends Form{
    Form current;

    public MenuPrincipal() {
                current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
                User u = new User(2, "junior");

        //BUTTONS
        add(new Label("Choisissez une option"));
        Button btnAide = new Button("Gestions des Aides");
        Button btnBen = new Button("Gestions des Beneficiaires");
        Button btnShakeHub = new Button("ShakeHub");
        
        btnShakeHub.addActionListener(e-> new ShakeHub().start());
        btnAide.addActionListener(e-> new MenuAide(u).show());
        btnBen.addActionListener(e-> new MenuBen(u).show());
        this.add(btnShakeHub);
<<<<<<< Updated upstream:src/com/mycompany/myapp/entities/gui/MenuPrincipal.java
        //Tool Bar
        getToolbar().addCommandToSideMenu("Home", null, e -> new MenuPrincipal().show());
        getToolbar().addCommandToSideMenu("Gestions des Aides", null, e -> new MenuAide(u).show());
        getToolbar().addCommandToSideMenu("Gestions des Beneficiaires", null, e -> new MenuBen(u).show());
        getToolbar().addCommandToSideMenu("ShakeHub", null,  e -> new ShakeHub().start());
=======
>>>>>>> Stashed changes:src/com/mycompany/myapp/MenuPrincipal.java
        addAll(btnAide,btnBen);

    }

}

