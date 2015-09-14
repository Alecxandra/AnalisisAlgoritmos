
package analisis_proyecto1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
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

    public chart() {
       
    }

    //-------------coloca los puntos en su lugar--------------------------

    public void createDataset(ArrayList<puntos> puntos) {
        System.out.println(puntos.size());
        final XYSeries datos = new XYSeries("puntos");
        for (int i = 0; i < puntos.size(); i++) {
            datos.add(puntos.get(i).getX(), puntos.get(i).getY());
        }
        dataset = new XYSeriesCollection();
        dataset.addSeries(datos);
    }

    public ChartPanel generar_panel() {
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

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesStroke(0, new BasicStroke(4.0f));
        plot.setRenderer(renderer);
        return chartPanel;
    }

}
