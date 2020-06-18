/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.GUI;

import PIDEV.Entities.Refuge;
import PIDEV.Services.ServiceRefuge;
import com.codename1.ui.Form;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *listRefuge
 * 
 * @author Emir
 */
public class listRefuge extends BaseForm {

    public listRefuge(Resources res) 
    {
        ServiceRefuge pr = new ServiceRefuge();
           System.out.println("hello word");
           Refuge g=new Refuge(2.222, 6.2222);
       // for (Refuge e : pr.getAllRefuge()) 
       // {
          System.out.println("hello word");
            mapRefuge pm = new mapRefuge();
            //pm.start(g);
         
        //}
    }
}
