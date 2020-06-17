 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;


/**
 *
 * @author Soreilla
 */
public class HomeForm extends Form {
Form current;
    public HomeForm(){
        current =this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddEvenement = new Button("Creer Evenement");
        Button btnListEvenements = new Button("Listes des Evenements");
        Button btnGestEvents = new Button("Gerer mes evenements");
        
        btnAddEvenement.addActionListener(e-> new AddEvenementForm(current).show());
        btnListEvenements.addActionListener(e->  {
            try {
                new  ListEvenementsForm(current).show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
         btnGestEvents.addActionListener(e->  {
            try {
                new gestEventsForm(current).show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        addAll(btnAddEvenement,btnListEvenements,btnGestEvents);
        
    }
    
    
}
