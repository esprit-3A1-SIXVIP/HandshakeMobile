/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.GUI;

import PIDEV.Entities.Refuge;
import PIDEV.Services.ServiceRefuge;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.messaging.Message;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;

import java.util.List;

/**
 *
 * @author wajih
 */
public class mystat extends Form{
        Form current;

        public mystat() {
        setTitle("Liste des Aides");
          Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
    
             int nbdispo=0;
             int nbnondispo=0;
           
             List<Refuge> lista = ServiceRefuge.getInstance().getAllRefuge();;
             for (Refuge fi : lista) {
                 if(fi.getDisponibiliteRefuge()==0)
                 nbdispo++;
                 else{
                     nbnondispo++;
                 }
             }

                 double[] values = new double[]{nbdispo,nbnondispo};
    // Set up the renderer
    int[] colors = new int[]{ColorUtil.rgb(255, 69, 0), ColorUtil.rgb(255, 69, 0), ColorUtil.MAGENTA, ColorUtil.BLUE, ColorUtil.BLUE};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(80);
    renderer.setChartTitle("Statistique");
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.BLUE);
    r.setGradientStop(0, ColorUtil.WHITE);
    r.setHighlighted(true);
    // Create the chart ... pass the values and renderer to the chart object.
   PieChart chart = new PieChart(buildCategoryDataset("Project budget", values), renderer);
      //BarChart chart = new BarChart(buildBarDataset(), renderer,Type.DEFAULT);
    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);
    add(c);
        //Tool Bar
        getToolbar().addCommandToSideMenu("Home", null, e -> new MenuRefuge().show());
        getToolbar().addCommandToSideMenu("Gestions des Refuges", null, e -> new MenuRefuge().show());
        getToolbar().addCommandToSideMenu("Gestions des Réfugiés", null, e -> new MenuRefuge().show());
    }

    private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(15);
    renderer.setLegendTextSize(15);
    renderer.setMargins(new int[]{20, 30, 15, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}

/**
 * Builds a category series using the provided values.
 *
 * @param titles the series titles
 * @param values the values
 * @return the category series
 */
protected CategorySeries buildCategoryDataset(String title, double[] values) {
    CategorySeries series = new CategorySeries(title);
    /*
    int k = 0;
    for (double value : values) {
        series.add("Nombre " + ++k, value);
    }
    */
    series.add("Refuges disponibles" ,values[0]);
    series.add("Refuges non disponibles" ,values[1]);
    return series;
}

}
