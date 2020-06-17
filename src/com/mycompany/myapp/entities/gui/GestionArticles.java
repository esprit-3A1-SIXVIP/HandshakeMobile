/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.gui;


import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.L10NManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.entities.CommentaireArticle;
import com.mycompany.myapp.services.ServiceArticle;
import com.mycompany.myapp.services.ServiceCommentaireArticle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.ProgressIndicator;



/**
 *
 * @author bhk
 */
public class GestionArticles extends Form{
Form current;
String  filePath;
 Image fullWidthPlaceHolder;

 // private SocialClientParse client;
    public GestionArticles() {
        current=this;
        setTitle("Home");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        
        //add(new Label("Choose an option"));
        //Button btnAddTask = new Button("Add Article");
        //Button btnListTasks = new Button("List Articles");
       // Button btnListArticlesgui = new Button("test");
         Button btnListArticlesgui1 = new Button("Handshake blog");
       // btnAddTask.addActionListener(e-> postSearchForm());
       // btnListTasks.addActionListener(e-> new ListArticlesForm(current).show());
       // btnListArticlesgui.addActionListener(e->  showAddPostForm(current));
        btnListArticlesgui1.addActionListener(e->  showFeed());
        add(btnListArticlesgui1);
        int fullWidthImage = (int)Math.round(Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight())) - 20;
        fullWidthPlaceHolder = Image.createImage(fullWidthImage, (int)fullWidthImage );
        if (!(fullWidthPlaceHolder instanceof EncodedImage)) {
            fullWidthPlaceHolder = EncodedImage.createFromImage(fullWidthPlaceHolder, true);
        }
            
      
      
    // Disable private CDN URLs as this doesn't seem to work with free accounts
    
        
    }

   
     
    private EncodedImage encodedImage(Image img) {
        if (img instanceof EncodedImage) {
            return (EncodedImage)img;
        } else {
            return EncodedImage.createFromImage(img, true);
        }
    }
    
    public void showAddPostForm(Form back) {
        Form f = new Form("Add Post");
         f.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> showFeed());

        f.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        TextArea commentField = new TextArea();
       //  f.setUIID("form1");
        TextField tfAuteur = new TextField("","ArticleAuteur");
        TextField tfTitre= new TextField("", "ArticleTitre");
        commentField.setRows(5);
        commentField.setHint("Enter comment");
        Button photoButton = new Button("Attach Photo");
        photoButton.setTextPosition(Label.BOTTOM);
        photoButton.addActionListener((evt)-> {
            //String file = Capture.capturePhoto(1024, 1024);
            filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
            if (filePath == null) {
                return;
            }
          
            try {
                Image img = Image.createImage(filePath).scaledSmallerRatio(256, 256);
              Image  img1 = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath));
             
                photoButton.setIcon(img);
                EncodedImage fullImage = encodedImage(Image.createImage(filePath));
                
                    try {
                photoButton.putClientProperty("fullImage", fullImage.scaledEncoded(1024, fullImage.getHeight() / fullImage.getWidth() * 1024));
                    }catch(Exception ex1)
                            {
                                System.out.println("exception zero");
                            }
                
                    
                
                f.revalidate();
            } catch (IOException ex) {
//                showError(ex.getMessage());
                return;
            }

        });

        Button submitButton = new Button("Submit");
        submitButton.addActionListener((evt)->{
            try {  
                if (commentField.getText()=="" ||tfTitre.getText()=="" ||tfAuteur.getText()==""  ){
                  Dialog.show("empty field", "to succefully save post fill in all fields ", "","ok");
               return ;
                }
                Map vals = new HashMap();
                vals.put("desc", commentField.getText());
                vals.put("title", tfTitre.getText());
                vals.put("write", tfAuteur.getText());
                
                //if (photoButton.getIcon() != null) {
                   // vals.put("photo", (Image)photoButton.getClientProperty("fullImage"));
                //}
                Dialog dialog = new InfiniteProgress().showInifiniteBlocking();
                upload(filePath,vals);
                dialog.dispose();
                showFeed();
               // try {
                 //   String id = client.post(vals);
                //} finally {
                //    dialog.dispose();
               // }
               // showFeed();
            } catch(Exception ex) {
              //  showError(ex.getMessage());
                return;
            }
        });
        
        Button cancelButton = new Button("Cancel");
        cancelButton.addActionListener((evt)->{
            back.showBack();
        });
        //  tfTitre.setUIID("FORM1");
        f.addComponent(tfAuteur);
        f.addComponent(tfTitre);
        
        f.addComponent(commentField);
        f.addComponent(photoButton);
        f.addComponent(submitButton);
        f.addComponent(cancelButton);
       
        f.show();
    }
    
    private void addMenu(Form f) {
                  Toolbar tb = f.getToolbar();
          


   
 //  tb.setUIID("SideCommand");
     f.addCommand(new Command("Show feed") {

            @Override
            public void actionPerformed(ActionEvent evt) {
                showFeed();
            }
            
        });
  f.addCommand(new Command("Search post") {

            @Override
            public void actionPerformed(ActionEvent evt) {
                postSearchForm();
            }
            
        });
         
          f.addCommand(new Command("Add new post") {

            @Override
            public void actionPerformed(ActionEvent evt) {
                showAddPostForm(current);
            }
            
        });
        
        f.addCommand(new Command("Logout") {

            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                 
                   new GestionArticles().show();    
                } catch (Exception ex) {
                    //showError(ex.getMessage());
                }
            }
            
        });
    
         
    }
        public void showFeed() {
            
         Form f = new Form("post");
        addMenu(f);
        f.setLayout(new BorderLayout());
           current=this;
        Container buttons = new Container();
        buttons.setLayout(new GridLayout(1, 2));
        Button addPost = new Button("Add Post");
        addPost.addActionListener((e)->{
            showAddPostForm(f);
        });
        buttons.addComponent(addPost);
        
        
        Container list = new Container();
        list.setScrollableY(true);
        list.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Dialog progress = new InfiniteProgress().showInifiniteBlocking();
        ArrayList<Article> feed = ServiceArticle.getInstance().getAllArticles();
        Collections.sort(feed);
        for(Article item : feed) {
            Container itemWrapper = new Container();
            itemWrapper.setUIID("FeedItem");
            itemWrapper.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
            
            Container topRow = new Container();
            topRow.setUIID("FeedItemTopRow");
            topRow.setLayout(new BoxLayout(BoxLayout.X_AXIS));
            //String avatarUrl =  (String)item.get("avatar");
            //URLImage img = URLImage.createToStorage((EncodedImage)defaultAvatarSmall, avatarUrl+"?small", avatarUrl, URLImage.RESIZE_SCALE_TO_FILL);
            // topRow.addComponent(new Label(img));
            
            Container postDetails = new Container();
            postDetails.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
            
            // postDetails.addComponent(new Label((String)item.get("screen_name")));
            postDetails.addComponent(new Label(item.getTitre()));
            Label posted = new Label("Posted "+L10NManager.getInstance().formatDateLongStyle(item.getDatePublication())+" by "+ item.getAuteur());
            posted.setUIID("FeedDateLabel");
            
            postDetails.addComponent(posted);
           
            topRow.addComponent(postDetails);
            itemWrapper.addComponent(topRow);
            
             Integer name = item.getDescription().length()>100?100:item.getDescription().length();
            itemWrapper.addComponent(new SpanLabel(item.getDescription().substring(0, name )));
             Style s = UIManager.getInstance().getComponentStyle("Button");
              Image icon1 = FontImage.createMaterial(FontImage.MATERIAL_COMMENT, s);
            Button bcommentaire = new Button("comment",icon1);
           
            Image icon = FontImage.createMaterial(FontImage.MATERIAL_ARROW_FORWARD, s);
           Button voirTout = new Button("read more...",icon);
             if (item.getPhoto() != null) {
              String photoUrl = "http://localhost/HandshakeWebSym/web/uploads/"+(String)item.getPhoto();
             URLImage photo = URLImage.createToStorage((EncodedImage)fullWidthPlaceHolder, photoUrl+"?"+Display.getInstance().getDisplayWidth(), photoUrl, URLImage.RESIZE_SCALE);
              itemWrapper.addComponent(new Label(photo));
            }
           // ScaleImageLabel fillLabel = new ScaleImageLabel(icon);
            Container c2 = new Container();
           c2.addComponent(voirTout);
          c2.addComponent(bcommentaire);
           itemWrapper .addComponent( c2);
          
            
            list.addComponent(itemWrapper);
           voirTout.addActionListener((ActionEvent evt) -> {
               articleExpend(item,f);
           });
            bcommentaire.addActionListener((evt) -> {
              comment( item,f);
             // showFeed();
            });
        }
        progress.dispose();
        
        //f.addComponent(BorderLayout.NORTH, buttons);
        f.addComponent(BorderLayout.CENTER, list);
    
        
        f.show();
    }
    
       private void upload(String filePath, Map vals)
       {
          MultipartRequest cr = new MultipartRequest();
          MultipartRequest cr1 = new MultipartRequest();
    cr.setUrl("http://localhost/HandshakeWebSym/web/app_dev.php/article/mob/upload/");
    cr.setPost(true);
    String mime = "image/png";
    try {
        cr.addData("file", filePath, mime);
    } catch (IOException e) {
        e.printStackTrace();
    }
    String fichernom = System.currentTimeMillis() + ".png";
    cr.setFilename("file", fichernom);
cr.addArgument("desc", vals.get("desc").toString());
    cr.addArgument("title", vals.get("title").toString());
    cr.addArgument("write", vals.get("write").toString());
    InfiniteProgress prog = new InfiniteProgress();
    Dialog dlg = prog.showInifiniteBlocking();
    cr.setDisposeOnCompletion(dlg);
    NetworkManager.getInstance().addToQueueAndWait(cr);
  
  
       }
        private void comment( Article vals,Form prev)
       {
              Form f3 = new Form();
              addMenu(f3);
              f3.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> prev.showBack());
             
        f3.setLayout(new BoxLayout(BoxLayout.Y_AXIS
        ));
       // f3.setUIID("form1");
        Container itemWrapper3 = new Container();
        TextArea commentField = new TextArea();
             commentField.setRows(5);
        commentField.setHint("Enter comment");
        Button Button = new Button("submit");
        Button.addActionListener((evt3) -> {
            if (commentField.getText()!=""){
              
                    
                       MultipartRequest cr = new MultipartRequest();
          
    cr.setUrl("http://localhost/HandshakeWebSym/web/app_dev.php/article/mob/newcomment");
    cr.setPost(true);
   
cr.addArgument("idArt", vals.getId()+"");
    cr.addArgument("desc", commentField.getText());
   
    InfiniteProgress prog = new InfiniteProgress();
    Dialog dlg = prog.showInifiniteBlocking();
    cr.setDisposeOnCompletion(dlg);
    NetworkManager.getInstance().addToQueueAndWait(cr);
                     Dialog dialog = new InfiniteProgress().showInifiniteBlocking();
               
                dialog.dispose();
                    comment(vals,prev);
            }
            
        });
        f3.add(commentField);
        f3.add(Button);
        Container itemWrapper = null;
        ArrayList<CommentaireArticle> comment = ServiceCommentaireArticle.getInstance().getAllCommentaireArticles(vals.getId());
       Collections.sort(comment);
        for(CommentaireArticle item : comment) {
            if (item.getArticle().getId()==vals.getId())
            {
            itemWrapper = new Container();
            
            itemWrapper.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
            
            Container topRow = new Container();
          
            topRow.setLayout(new BoxLayout(BoxLayout.X_AXIS));
           
           Label posted1 = new Label("Posted "+L10NManager.getInstance().formatDateLongStyle(item.getDatePublication()));
 posted1.setUIID("FeedDateLabel");
            
            
            topRow.addComponent(posted1);

            
            Container postDetails = new Container();
            postDetails.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
            postDetails.add(item.getDescription());
            itemWrapper.addComponent(postDetails);
            itemWrapper.addComponent(topRow);
            
         f3.add(itemWrapper);
         itemWrapper.setUIID("FeedItem");
            }
            }
             f3.add(itemWrapper3);
             f3.show();
           
           
           
        
  
  
       }
       public void articleExpend(Article item,Form prev){
            Form f1 = new Form(item.getTitre());
                  addMenu(f1);
         f1.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e1-> prev.showBack());
        f1.setLayout(new BorderLayout());
      // f1.setUIID("form1");
        Container itemWrapper1 = new Container();
            itemWrapper1.setUIID("FeedItem");
            itemWrapper1.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
            
            Container topRow1 = new Container();
            topRow1.setUIID("FeedItemTopRow");
            topRow1.setLayout(new BoxLayout(BoxLayout.X_AXIS));
            //String avatarUrl =  (String)item.get("avatar");
            //URLImage img = URLImage.createToStorage((EncodedImage)defaultAvatarSmall, avatarUrl+"?small", avatarUrl, URLImage.RESIZE_SCALE_TO_FILL);
            // topRow.addComponent(new Label(img));
            
            Container postDetails1 = new Container();
            postDetails1.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
            
            // postDetails.addComponent(new Label((String)item.get("screen_name")));
            postDetails1.addComponent(new Label(item.getTitre()));
            Label posted1 = new Label("Posted "+L10NManager.getInstance().formatDateLongStyle(item.getDatePublication())+" by "+ item.getAuteur());
            posted1.setUIID("FeedDateLabel");
            
            postDetails1.addComponent(posted1);
           
            topRow1.addComponent(postDetails1);
            itemWrapper1.addComponent(topRow1);
            URLImage photo;
             Integer name1 = item.getDescription().length()>100?100:item.getDescription().length();
            itemWrapper1.addComponent(new SpanLabel(item.getDescription()));

            if (item.getPhoto() != null) {
                int i =Display.getInstance().getDisplayWidth(); 
           
String photoUrl = "http://localhost/HandshakeWebSym/web/uploads/"+(String)item.getPhoto();
  
    
       
            
               photo = URLImage.createToStorage((EncodedImage)fullWidthPlaceHolder, photoUrl+"?"+Display.getInstance().getDisplayWidth(), photoUrl, URLImage.RESIZE_SCALE_TO_FILL);
               Label l = new Label(photo);
               itemWrapper1.addComponent(l);
            }
            Style s = UIManager.getInstance().getComponentStyle("Button");
              Image icon1= FontImage.createMaterial(FontImage.MATERIAL_COMMENT, s);
            Button bcommentaire1 = new Button("comment",icon1);
           bcommentaire1.addActionListener((e4) -> {
               comment(item,f1);
           });
            
           
           // ScaleImageLabel fillLabel = new ScaleImageLabel(icon);
            Container c21 = new Container();
           c21.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
          c21.addComponent(bcommentaire1);
           
        // f1.addComponent(BorderLayout.NORTH ,photo );
        f1.addComponent(BorderLayout.CENTER,itemWrapper1 );
        f1.addComponent(BorderLayout.SOUTH,c21 );
        f1.show();
       }
               
        
        
         public void postSearchForm() {
        Form f = new Form("Find  posts");
        addMenu(f);
        
        f.setLayout(new BorderLayout());
       //  f.setUIID("form1");
        TextField search = new TextField();
        search.setHint("Search title");
        
     Container liste = new Container();
        liste.setScrollableY(true);
        liste.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
       
        // We want the data change to delay by 800ms
        // so that we don't send an update for every keystroke.
       // OperationQueue dataChangeQueue = new OperationQueue(400);
        search.addDataChangeListener((type, index) -> {
         //   dataChangeQueue.run(()-> {
                if (search.getText().length() > 2) {
                    try {
                        
                        liste.removeAll();
                        Map x = null;
                        String t = search.getText();
                        ArrayList<Article> results1 = ServiceArticle.getInstance().getSimilarArticles(t);
                     for (Article entry : results1) {
      Container itemWrapper = new Container();
            
            itemWrapper.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
         
            Container topRow = new Container();
          
            topRow.setLayout(new BoxLayout(BoxLayout.X_AXIS));
           
           Label posted1 = new Label("Posted "+L10NManager.getInstance().formatDateLongStyle(entry.getDatePublication()));
 posted1.setUIID("FeedDateLabel");
            
            
            topRow.addComponent(posted1);
 Style s = UIManager.getInstance().getComponentStyle("Button");
              Image icon1 = FontImage.createMaterial(FontImage.MATERIAL_EXPAND_MORE, s);
            Button b1 = new Button(icon1);

            topRow.addComponent(b1);
            b1.addActionListener((evt) -> {
                articleExpend(entry,f);
                
                
                
                
                
                
            });
            
            
            Container postDetails = new Container();
            postDetails.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
            postDetails.add(entry.getDescription());
            itemWrapper.addComponent(postDetails);
            itemWrapper.addComponent(topRow);
            
         liste.addComponent(itemWrapper);
         itemWrapper.setUIID("FeedItem");
         
            
                       }
                    
                        f.revalidate();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Log.e(ex);
                     //   showError(ex.getMessage());
                    }
                }
            });
            
        //});
        
        
       
        
        f.addComponent(BorderLayout.NORTH, search);
        if (liste!= null)
            f.addComponent(BorderLayout.CENTER, liste);
        
        f.show();
    }
    
}

