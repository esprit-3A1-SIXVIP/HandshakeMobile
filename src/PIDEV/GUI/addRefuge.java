/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package PIDEV.GUI;

import PIDEV.Entities.Refuge;
import PIDEV.Services.ServiceRefuge;
import com.codename1.components.FloatingHint;
import com.codename1.components.ToastBar;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 * addRefuge UI
 *
 * @author Shai Almog
 */
public class addRefuge extends BaseForm {

    public addRefuge(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");

        TextField tfrue = new TextField("", "rue", 20, TextField.ANY);
        TextField tfville = new TextField("", "ville", 20, TextField.ANY);
        TextField tfpays = new TextField("", "pays", 20, TextField.ANY);
        TextField tfdisp = new TextField("", "disponibilité", 20, TextField.ANY);
        TextField tfcap = new TextField("", "capacité", 20, TextField.ANY);
        Picker tfdeb = new Picker();
        Picker tffin = new Picker();

        TextField tflat = new TextField("", "latitude", 20, TextField.ANY);
        TextField tflong = new TextField("", "longitude", 20, TextField.ANY);

        tfrue.setSingleLineTextArea(false);
        tfville.setSingleLineTextArea(false);
        tfpays.setSingleLineTextArea(false);
        tfdisp.setSingleLineTextArea(false);
        tfcap.setSingleLineTextArea(false);
        tflat.setSingleLineTextArea(false);
        tflong.setSingleLineTextArea(false);
        Button next = new Button("save");
        Button signIn = new Button("Sign In");
        Button map=new Button("List");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");

        Container content = BoxLayout.encloseY(
                new Label("Nouveau Refuge", "LogoLabel"),
                new FloatingHint(tfrue),
                createLineSeparator(),
                new FloatingHint(tfville),
                createLineSeparator(),
                new FloatingHint(tfpays),
                createLineSeparator(),
                new FloatingHint(tfdisp),
                createLineSeparator(),
                tfcap,
                createLineSeparator(),
                tflat,
                createLineSeparator(),
                tflong,
                createLineSeparator(),
                tfdeb,
                createLineSeparator(),
                tffin,
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next
        ));
        next.requestFocus();
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Refuge t = new Refuge(tfrue.getText(), tfville.getText(), tfpays.getText(), Integer.parseInt(tfcap.getText()), Integer.parseInt(tfdisp.getText()), tfdeb.getDate(), tffin.getDate(), Double.parseDouble(tflat.getText()), Double.parseDouble(tflong.getText()));
                new ServiceRefuge().addRefuge(t);
                  ToastBar.showMessage("Refuge ajouté", FontImage.MATERIAL_PLACE, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                         new panelrefuge(res).show();
                        }
                    });
         
        //} 
            }
        });
          add(BorderLayout.EAST, BoxLayout.encloseY(
                map
        ));
        map.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
    
         
      
  
            mapRefuge pm = new mapRefuge();
            pm.start();
    
            }
        });   
    }

}
