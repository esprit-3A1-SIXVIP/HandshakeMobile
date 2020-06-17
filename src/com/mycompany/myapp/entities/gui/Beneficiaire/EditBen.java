/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.gui.Beneficiaire;

import com.codename1.l10n.DateFormatPatterns;
import com.mycompany.myapp.entities.gui.Aide.*;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.ComponentStateChangeEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Aide;
import com.mycompany.myapp.entities.Beneficiaire;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.services.ServiceAide;
import com.mycompany.myapp.entities.services.ServiceBeneficiaire;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author wajih
 */
public class EditBen extends Form{
    
        public EditBen(Form previous,Beneficiaire ben,User u) {
        setTitle("Modifier Beneficiaire");
        setLayout(BoxLayout.y());
        Label l = new Label("Aide :");
        ComboBox<String> comboAide = new ComboBox<>();
        ArrayList<Aide> listA=ServiceAide.getInstance().getAllAides();
        for(int i=0;i<listA.size();i++)
        {
            comboAide.addItem(listA.get(i).getCategorieAide());      
            if(listA.get(i).getAideId()==ben.getAideId())
            {
                comboAide.setSelectedItem(listA.get(i).getCategorieAide());
            }
        }
        
        TextComponent tfNom= new TextComponent().label("Nom :");
        tfNom.text(ben.getNomBeneficiaire());
        TextComponent tfPre= new TextComponent().label("Prenom :");
        tfPre.text(ben.getPrenomBeneficiaire());

        TextComponent tfEmail= new TextComponent().label("Email :");
        tfEmail.text(ben.getEmail());

        Label l1 = new Label("Date :");
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        datePicker.setDate(ben.getDateNaissance());

        TextComponent tfVille= new TextComponent().label("Ville :");
        tfVille.text(ben.getVille());
        TextComponent tfAdresse= new TextComponent().label("AdresseGps :");
        tfAdresse.text(ben.getAdresseGPS());

        TextComponent tfTel= new TextComponent().label("Telephone :");
        tfTel.text(String.valueOf(ben.getTelephone()));

        Label l2 = new Label("Role :");
        ComboBox<String> combo = new ComboBox<>();
        combo.addItem("besoin");
        combo.addItem("nationalite");
        combo.setSelectedItem(ben.getRole());
        TextComponent tfbesnat= new TextComponent().label("");
                     if(combo.getSelectedItem().equals("besoin"))
                        {
                         tfbesnat.text(ben.getBesoin());
                        }
                        else
                        {
                         tfbesnat.text(ben.getNationalite());
                        }

        Button btnValider = new Button("Modifier Beneficiaire");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfPre.getText().length()==0)||(tfEmail.getText().length()==0)||(tfVille.getText().length()==0)||(tfAdresse.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        DateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                        Date datecreation = new Date(System.currentTimeMillis());
                        java.text.SimpleDateFormat format = new 
                        java.text.SimpleDateFormat(DateFormatPatterns.ISO8601);
                        Beneficiaire e= new Beneficiaire(tfNom.getText(), tfPre.getText(), tfEmail.getText(), datePicker.getDate(), tfVille.getText(), Integer.parseInt(tfTel.getText()), tfAdresse.getText(), combo.getSelectedItem(), u.getId());
                        e.setBeneficiaireId(ben.getBeneficiaireId());
                        if(combo.getSelectedItem().equals("besoin"))
                        {
                            e.setBesoin(tfbesnat.getText());
                            e.setNationalite(" ");
                        }
                        else
                        {
                            e.setNationalite(tfbesnat.getText());
                            e.setBesoin(" ");
                        }

                        ArrayList <Aide> aide = new ArrayList();
                        aide=ServiceAide.getInstance().getbyCat(comboAide.getSelectedItem().toString());
                        for (Aide a : aide)
                        {
                            e.setAideId(a.getAideId());
                        }
                        if( ServiceBeneficiaire.getInstance().editBen(e))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(l,comboAide,tfNom,tfPre,tfEmail,l1,datePicker,tfVille,tfAdresse,tfTel,l2,combo,tfbesnat,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new MyBen(u).show());
                
    }

   
}