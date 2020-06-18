/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.gui;

import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.mycompany.myapp.entities.gui.Aide.MenuAide;
import com.mycompany.myapp.entities.gui.Beneficiaire.MenuBen;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import static com.codename1.ui.Dialog.TYPE_INFO;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.gui.MenuEvenement;
import com.mycompany.myapp.entities.gui.ShakeHub;
import java.io.IOException;

/**
 *
 * @author wajih
 */
public class MenuPrincipal extends Form {

    Form current;
    private EncodedImage ec = null;
    private ImageViewer iv = null;
    String cnx = "http://localhost/mobileshakehub/cnx.php";

    public MenuPrincipal() {
        current = this;
        current = new Form("Welcome to Handshake", BoxLayout.y());
        Image img;
        try {
            img = Image.createImage("/handshake.png");
            iv = new ImageViewer(img);
            current.add(iv);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        Label l1 = new Label("Email");
        Label l2 = new Label("Password");
        TextField login = new TextField("", "Entrez votre Email:");
        TextField password = new TextField("", "Entrez votre password:");
        password.setConstraint(TextField.PASSWORD);
        Button btn1 = new Button("Login");
        current.addAll(l1, login, l2, password);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ConnectionRequest requete1 = new ConnectionRequest();
                requete1.setUrl(cnx);
                requete1.setPost(false);
                requete1.addArgument("email", login.getText());
                requete1.addArgument("pass", password.getText());
                NetworkManager.getInstance()
                        .addToQueue(requete1);
                requete1.addResponseListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt1) {
                        String res1 = new String(requete1.getResponseData());

                        if (res1.equals("error")) {
                            Dialog.show("Error", "Bad credentials", "Ok", null);
                        } else {
                            

                            Form S = new Form("HandShake", BoxLayout.y());
                            User U = new User();
                            
                            U.parse(res1);
                            try {
                Label imageDialogue;
                try {
                                    ec = EncodedImage.create("/load.jpg");
                                } catch (IOException ex) {
                                    System.out.println(ex.getMessage());
                                }
                if (!(U.getProfil().equals(""))) {
                    imageDialogue = new Label(URLImage.createToStorage(ec, "http://localhost/mobileshakehub/"+U.getProfil(), "http://localhost/mobileshakehub/"+U.getProfil(), URLImage.RESIZE_SCALE));
                } else {
                    imageDialogue = new Label(Image.createImage("/usericon.png").scaled(300, 300));
                }
                imageDialogue.setText(("Logged in successfully as " + U.getPrenomUser() + " " + U.getNomUser()) + " !");
                imageDialogue.setTextPosition(Label.BOTTOM);
                Dialog.show("Welcome to the ShakeHub", imageDialogue, null, TYPE_INFO, null, 2000);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
                            //BUTTONS
                            add(new Label("Choisissez une option"));
                            Button btnAide = new Button("Gestions des Aides");
                            Button btnBen = new Button("Gestions des Beneficiaires");
                            Button btnShakeHub = new Button("ShakeHub");
                            Button btnEvenement = new Button("Gestions des Evenements");
                            Button btnArticle = new Button("Gestions des Articles");
                            btnShakeHub.addActionListener(e -> new ShakeHub().start(U));
                            btnEvenement.addActionListener(e -> new MenuEvenement().show());
                            btnArticle.addActionListener(e -> new GestionArticles().show());

                            btnAide.addActionListener(e -> new MenuAide(U).show());
                            btnBen.addActionListener(e -> new MenuBen(U).show());
                            S.getToolbar()
                                    .addMaterialCommandToOverflowMenu("Sign out", FontImage.MATERIAL_BACKSPACE, new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent back
                                        ) {
                                            S.removeAll();
                                            current.showBack();

                                        }
                                    }
                                    );
                            S.addAll(btnAide, btnBen, btnEvenement, btnShakeHub);
                            
                            S.add(btnArticle);
                            S.show();
                        }

                    }

                });
            }
        });
        current.add(btn1);
        current.show();
    }
}
