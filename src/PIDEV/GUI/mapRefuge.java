package PIDEV.GUI;

import PIDEV.Entities.Refuge;
import PIDEV.Services.ServiceRefuge;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.maps.Coord;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.getCurrentForm;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;

public class mapRefuge extends BaseForm {

    private static final String HTML_API_KEY = "AIzaSyBWeRU02YUYPdwRuMFyTKIXUbHjq6e35Gw";
    private Form current;

    public void init(Object context) {
        try {
            Resources theme = Resources.openLayered("/theme");
            UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
            Display.getInstance().setCommandBehavior(Display.COMMAND_BEHAVIOR_SIDE_NAVIGATION);
            UIManager.getInstance().getLookAndFeel().setMenuBarClass(SideMenuBar.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {

        ArrayList<Refuge> L;
        ServiceRefuge sr = new ServiceRefuge();
        L = sr.getAllRefuge();

        if (current != null) {
            current.show();
            return;
        }
        Form hi = new Form("Native Maps Test");
        hi.setLayout(new BorderLayout());
        final MapContainer cnt = new MapContainer();
        Toolbar tr = new Toolbar(true);
        hi.setToolbar(tr);
        Form previous = getCurrentForm();
        hi.getToolbar().setBackCommand("", (e) -> {
            previous.showBack();
        });
        cnt.setCameraPosition(new Coord(-26.1486233, 28.67401229999996));//this breaks the code //because the Google map is not loaded yet

        int maxZoom = cnt.getMaxZoom();

        System.out.println("Max zoom is " + maxZoom);
        Button btnMoveCamera = new Button("Move Camera");
        btnMoveCamera.addActionListener(e -> {
            cnt.setCameraPosition(new Coord(36.800399, 10.186619500000006));
        });

        Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, 3);
        FloatingActionButton nextForm = FloatingActionButton.createFAB(FontImage.MATERIAL_PLACE);

        nextForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
//             // for (Refuge ex:L)
//               // {
//                cnt.zoom(new Coord(ex.getLatitude(), ex.getLongitude()), 14);
//                }
            }
        });

        Container root = LayeredLayout.encloseIn(BorderLayout.north(nextForm.bindFabToContainer(cnt)));
        hi.add(BorderLayout.CENTER, root);
        for (Refuge ex : L) {
            final String val;
            if (ex.getDisponibiliteRefuge() == 0) {
                val = "libre";
            } else {
                val = "occupé";
            }
            cnt.addMarker(EncodedImage.createFromImage(markerImg, false), new Coord(ex.getLatitude(), ex.getLongitude()), "Hi marker", "Optional long description", new ActionListener() {
                public void actionPerformed(ActionEvent evt) {

                    System.out.println("Bounding box is " + cnt.getBoundingBox());
                    ToastBar.showMessage(ex.getRueRefuge() + " \n" + ex.getVilleRefuge() + "\n"
                            + "disponibilité: " + val + "-", FontImage.MATERIAL_PLACE, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            Message m = new Message("Body of message");
                            m.getAttachments().put("/resources/test.txt", "text/plain");
                            Display.getInstance().sendMessage(new String[]{"marwen1609@gmail.com"}, "Subject of message", m);
                        }
                    });

                }
            });
        }
        /* hi.addCommand(new Command("Move Camera") {
            public void actionPerformed(ActionEvent ev) {
                cnt.setCameraPosition(new Coord(-1.063402, 11.665030));}
        });
         hi.addCommand(new Command("Add Marker") {
            public void actionPerformed(ActionEvent ev) {
                try {
                    cnt.setCameraPosition(new Coord(41.889, -87.622));
                    cnt.addMarker(EncodedImage.create("/"
                            + "maps-pin.png"), new Coord(41.889, -87.622), "Hi marker", "Optional long description", new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            Dialog.show("Marker Clicked!", "You clicked the marker", "OK", null);
                        }
                    });
                } catch (IOException err) {
                    // since the image is iin the jar this is unlikely
                    err.printStackTrace();
                }
            }
        });
        hi.addCommand(new Command("Add Path") {
            public void actionPerformed(ActionEvent ev) {
                cnt.setCameraPosition(new Coord(-18.142, 178.431));
                cnt.addPath(new Coord(-33.866, 151.195), // Sydney
                        new Coord(-18.142, 178.431), // Fiji
                        new Coord(21.291, -157.821), // Hawaii
                        new Coord(37.423, -122.091) // Mountain View
                );
            }
        });
        hi.addCommand(new Command("Clear All") {
            public void actionPerformed(ActionEvent ev) {
                cnt.clearMapLayers();
            }
        });
         Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, 3);
      cnt.addMarker(EncodedImage.createFromImage(markerImg, false), new Coord(-1.063402, 11.665030), "Hi marker", "Optional long description", new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Bounding box is " + cnt.getBoundingBox());
                ToastBar.showMessage("palace"+ " \n" + "test"+ "\n"
                        + "Lundi à Samedi: " + "test1" + "-" , FontImage.MATERIAL_PLACE);
            }
        });*/
        hi.show();
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }

    public void destroy() {
    }

}
