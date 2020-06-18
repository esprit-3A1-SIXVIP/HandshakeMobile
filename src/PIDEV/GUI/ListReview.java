/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.GUI;

import PIDEV.Entities.Etablissement;
import PIDEV.Entities.Review;
import PIDEV.Entities.User;
import PIDEV.Services.ReviewService;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import static com.codename1.ui.CN.convertToPixels;
import static com.codename1.ui.CN.getCurrentForm;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;





/**
 *
 * @author Emir
 */
public class ListReview extends BaseForm{
    Form f;
    Label titre;
    Label name;
    Label commentaire;
    Container cx = new Container(BoxLayout.y());
    
public ListReview(int id,Etablissement etab,Resources res){
     
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Liste Reviews");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
          FloatingActionButton back = FloatingActionButton.createFAB(FontImage.MATERIAL_BACKUP);
           Form previous = getCurrentForm();
        tb.setBackCommand("", (e) -> {
            previous.showBack();
        });
   
         ReviewService lr = new ReviewService();
            Image img = res.getImage("profilebg.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Image placeholderx = Image.createImage(240, 120);
        EncodedImage encImagex = EncodedImage.createFromImage(placeholderx, false);
        String url = "http://localhost/PIDEV/web/devis/" + etab.getDevis_name();
        String thumb = "http://localhost/PIDEV/web/devis/" + etab.getDevis_name();
        URLImage thumbImage = URLImage.createToStorage(encImagex, url, thumb, URLImage.RESIZE_SCALE_TO_FILL);
        ScaleImageButton btn = new ScaleImageButton(thumbImage);
        btn.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        btn.setUIID("PictureWhiteBackground");
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        GridLayout.encloseIn(1,
                                FlowLayout.encloseCenter(
                                        btn
                        )
                )
        )));
        
        
        Container c1 = null;
        
         
        for (Review e : lr.getList2(id)) {
            User x =SignInForm.userCon;
               FloatingActionButton nextForm = FloatingActionButton.createFAB(FontImage.MATERIAL_DELETE);
                                  if(e.getIduser().getId()!=x.getId())
                                  {
                                      nextForm.setVisible(false);
                                  }
              
               nextForm.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent evt) {
                
             
                    
                        
                        lr.deleterev(e.getId(),id);
                        ToastBar.showMessage("Votre commentaire a été supprimé", FontImage.MATERIAL_INFO);
                       try {
                           new ProfilResto(res,id).show();
                       } catch (IOException ex) {
                          
                       }
}
                
                       
                   
                   
               });
          
            Image placeholder = Image.createImage(350, 150);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);

          //  URLImage imgUrl = URLImage.createToStorage(encImage, "http://localhost/PIDEV/web/devis/" + e.getIduser().getDevis_name(), "http://localhost/PIDEV/web/devis/" + e.getIduser().getDevis_name());
           // System.out.println("http://localhost/PIDEV/web/devis/" + e.getIduser().getDevis_name());
          //  ImageViewer img1 = new ImageViewer(imgUrl);

            titre = new Label();

           commentaire = new Label();
           name = new Label();

           
            int fontSize = Display.getInstance().convertToPixels(3);

            // requires Handlee-Regular.ttf in the src folder root!
            Font ttfFont = Font.createTrueTypeFont("Poppins", "Poppins-Bold.ttf").
                    derive(fontSize, Font.STYLE_PLAIN);
            Container cnom = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            name = new Label(e.getIduser().getSurname()+" "+e.getIduser().getName());
            

            name.setUIID("Label");

            titre = new Label(e.getTitre());
            Style souscaticon = new Style(titre.getUnselectedStyle());
            souscaticon.setFgColor(0x73879c);
            FontImage souscaticonx = FontImage.createMaterial(FontImage.MATERIAL_HOME, souscaticon);
            titre.setIcon(souscaticonx);
           

            String qualiteXX = String.valueOf(e.getQualite());
            String ServiceXX = String.valueOf(e.getService());

            Slider qualite = createStarRankSlider(Integer.parseInt(qualiteXX.substring(0, 1)));
            Slider service = createStarRankSlider(Integer.parseInt(ServiceXX.substring(0, 1)));
            
            System.out.println(Integer.parseInt(qualiteXX.substring(0, 1)));
            cnom.getStyle().setPaddingBottom(2);
cnom.add(name);
cnom.add(nextForm);
            cnom.add(titre);
commentaire.setText(e.getCommentaire());
 commentaire.setUIID("Label2");
            cnom.add(commentaire);
           Container qualitec = new Container(BoxLayout.x());
           Container servicec = new Container(BoxLayout.x());
           Label qualitel = new Label("Qualite :");
           Label servicel = new Label("Service :");
           qualitec.add(qualitel);qualitec.add(qualite);
           servicec.add(servicel);servicec.add(service);
           cnom.add(qualitec);
            cnom.add(servicec);
        

//           cnom.add(servicec);
            c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            c1.getStyle().setPaddingBottom(20);

           // add(img1);

            add(cnom);

//            c1.add(details);
           
            
         

        }
        
      

    }

 
    private Slider createStarRankSlider(int value) {
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(convertToPixels(2, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        Slider starRank = new Slider() {
            public void refreshTheme(boolean merge) {
                // special case when changing the theme while the dialog is showing
                initStarRankStyle(getSliderEmptySelectedStyle(), emptyStar);
                initStarRankStyle(getSliderEmptyUnselectedStyle(), emptyStar);
                initStarRankStyle(getSliderFullSelectedStyle(), fullStar);
                initStarRankStyle(getSliderFullUnselectedStyle(), fullStar);
            }
        };

        starRank.setEditable(false);
       
        starRank.setMinValue(0);
        starRank.setMaxValue(5);
         starRank.setProgress(value);
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }
    
}
