/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;



/**
 *
 * @author wajih
 */
public class MenuRefuge extends Form{
    Form current;
    Resources theme;
        Resources theme2;
   public MenuRefuge() {
        current = this;
        
        theme2 = UIManager.initFirstTheme("/theme2");
        Toolbar.setGlobalToolbar(true);
     
        setTitle("Gestions des Refuge");

        setLayout(BoxLayout.y());

        //BUTTONS
        add(new Label("faites votre choix"));
        Button btnAjoutRef = new Button("Ajouter refuge");
        Button btnListref = new Button("Liste des refuge");
        Button map = new Button("maMap");
        Button btnMyAide = new Button("Mes Aides");
        Button btnStat = new Button("Stat");
        btnStat.addActionListener(e -> new mystat().show());

        //Tool Bar
        getToolbar().addCommandToSideMenu("Home", null, e -> new SignInForm(theme).show());
        getToolbar().addCommandToSideMenu("Gestions des refuges", null, e -> new MenuRefuge().show());
        getToolbar().addCommandToSideMenu("Gestions des Beneficiaires", null, e -> new panelrefuge(theme).show());
        btnAjoutRef.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new addRefuge(theme).show();
            }
        });
        btnListref.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new panelrefuge(theme).show();

            }
        });
        map.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                mapRefuge pm = new mapRefuge();
                pm.start();
            }
        });
        addAll(btnAjoutRef, btnListref, map, btnMyAide, btnStat);
        refreshTheme();
    }
}

