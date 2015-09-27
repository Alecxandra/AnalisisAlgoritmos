
package analisis_proyecto1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author alecx
 */
public class chart {

    XYSeriesCollection dataset;
    ArrayList<Color> colores = new ArrayList();
    int bandera=0;
    public chart() {
       
    }

    //-------------coloca los puntos en su lugar--------------------------

    public void createDataset(ArrayList<puntos> puntos,puntos[] x,int bandera) {
        this.bandera=bandera;
        //System.out.println(puntos.size());
        final XYSeries datos = new XYSeries("puntos");
        final XYSeries datosx= new XYSeries("");
        for (int i = 0; i < puntos.size(); i++) {
            datos.add(puntos.get(i).getX(), puntos.get(i).getY());
        }
        if (x != null) {

            for (int i = 0; i < x.length; i++) {
                datosx.add(x[i].getX(), x[i].getY());
            }
             //System.out.println("Entro al cambio en el panel");
        } 
        dataset = new XYSeriesCollection();
        dataset.addSeries(datos);
        dataset.addSeries(datosx);
        //-----Si vienen los puntos dinamicos---------
        
    }

    //--------generar panel visual--------------------
    public ChartPanel generar_panel(ArrayList<Integer> medianas,int indice) {
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                "Par de puntos mas cercanos",
                "X",
                "Y",
                dataset, 
               PlotOrientation.VERTICAL,
                true, true, false
        );
        
        
        
        ChartPanel chartPanel = new ChartPanel(xylineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        final XYPlot plot = xylineChart.getXYPlot();
        //-----------------------------mediana----------
        if(medianas != null){
            for (int i = 0; i <=indice; i++) {
                ValueMarker marker = new ValueMarker(medianas.get(i));
                if(i >=colores.size()){
                    colores.add(getColor());
                  marker.setPaint(colores.get(colores.size()-1));
                }else{
                  marker.setPaint(colores.get(i));
                }
                
                marker.setStroke(new BasicStroke(4.0f));
                plot.addDomainMarker(marker);
        }        
            }
        
        //------------------------------
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesStroke(0, new BasicStroke(4.0f));
        if(bandera==1){
        renderer.setSeriesPaint(1, Color.MAGENTA);
        renderer.setSeriesLinesVisible(1, true);
        renderer.setSeriesStroke(1, new BasicStroke(4.0f));
        }else{
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesLinesVisible(1, true);
        renderer.setSeriesStroke(1, new BasicStroke(4.0f));
        }
        
        
        plot.setRenderer(renderer);
        return chartPanel;
    }
   
    private Color getColor() {
        return new Color( 60 + new Random().nextInt(120), 60 + new Random().nextInt(120), 60 + new Random().nextInt(120));
    }

}
