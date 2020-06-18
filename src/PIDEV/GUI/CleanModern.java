package PIDEV.GUI;

import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.maps.Coord;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;

public class CleanModern {

    private static final String HTML_API_KEY = "AIzaSyBWeRU02YUYPdwRuMFyTKIXUbHjq6e35Gw";
    private Form current;
    private Resources theme;

    public void init(Object context) {
        try {
          theme = Resources.openLayered("/theme");
            UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if (current != null) {
            current.show();
            return;
        }

        new SignInForm(theme).show();

        /* Form hi = new Form("Native Maps Test");
            hi.setLayout(new BorderLayout());
            final MapContainer cnt = new MapContainer();
            hi.addComponent(BorderLayout.CENTER, cnt);
            hi.addCommand(new Command("Move Camera") {
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
            });
            hi.show();*/
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }

    public void destroy() {
    }

}
