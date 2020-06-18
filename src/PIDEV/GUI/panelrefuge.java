/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.GUI;

import PIDEV.Entities.Refuge;
import PIDEV.Services.ServiceRefuge;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.OnOffSwitch;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.convertToPixels;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.ScrollListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author amisa
 */
public class panelrefuge extends BaseForm {

    Container cx = new Container(BoxLayout.y());

    public panelrefuge(Resources res) {

        super(BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
        tb.setOnTopSideMenu(true);
        tb.setGlobalToolbar(true);
        getToolbar().addCommandToSideMenu("Home", null, e -> new MenuRefuge().show());
        Button map = new Button("+");
        int mm = Display.getInstance().convertToPixels(3);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 4, 0), false);
        Image icon1 = URLImage.createToStorage(placeholder, "icon1", "https://freepngimg.com/thumb/christmas/34475-8-christmas-home-free-download.png");
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        ArrayList<Refuge> L;
        ServiceRefuge sr = new ServiceRefuge();
        L = sr.getAllRefuge();
        String val = "";
        Display.getInstance().scheduleBackgroundTask(() -> {
            Display.getInstance().callSerially(() -> {
                removeAll();
                for (Refuge e : L) {
                    MultiButton m = new MultiButton();
                    m.setIcon(icon1);
                    m.setTextLine1("adresse: " + String.valueOf(e.getVilleRefuge() + "-" + e.getRueRefuge()));
                    if (e.getDisponibiliteRefuge() == 0) {
                        m.setTextLine2("libre");
                    } else {
                        m.setTextLine2("occupé");
                    }
                    m.setTextLine3("capacité : " + e.getCapaciteRefuge());
                    m.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            if (Dialog.show("Confirmation", "Voulez vous Supprimer cette Aide ?", "Supprimer", "Annuler")) {

                                Refuge t = new Refuge(e.getDonId());

                                if (ServiceRefuge.getInstance().deleteRefuge(t)) {
                                    {
                                        ToastBar.showMessage("refuge supprimé", FontImage.MATERIAL_INFO);
                                        new panelrefuge(res).show();
                                    }

                                }
                            }
                        }
                    });
                   
                    
                    
                    add(m);

                }

                revalidate();
            });
        });
        addSideMenu(Resources.getGlobalResources());
        getToolbar().addCommandToSideMenu("ajouter Refuge", null, e -> new addRefuge(res).show());
        getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : getContentPane()) {
                    MultiButton mb = (MultiButton) cmp;
                    String line1 = mb.getTextLine1();
                    String line2 = mb.getTextLine2();
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
                            || line2 != null && line2.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);
                }
                getContentPane().animateLayout(150);
            }
        }, 4);

        FloatingActionButton nextForm = FloatingActionButton.createFAB(FontImage.MATERIAL_MAP);

        nextForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                mapRefuge pm = new mapRefuge();
                pm.start();
            }
        });

        nextForm.bindFabToContainer(getContentPane());

    }

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();

    }

    private void addTab(Tabs swipe, Image img, Label spacer, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (img.getHeight() < size) {
            img = img.scaledHeight(size);
        }

        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");

        Container page1
                = LayeredLayout.encloseIn(
                        image,
                        overlay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                                )
                        )
                );

        swipe.addTab("", page1);
    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
}
