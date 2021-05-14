package cn.sunyog.echart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;

/**
 * @Author: MysteriousGT
 * @Date: 2021/3/29 10:24 上午
 * @Desc:
 */
public class BarChartTool {

    /**
     * @Desc: 获取柱状图
     * @Author: MysteriousGT
     * @Date: 2021/3/29
     * @Param: []
     * @Return: void
     */
    public static JFreeChart getBarChart() {
        String rowKey1 = "RowKey-001";
        String rowKey2 = "RowKey-002";
        String rowKey3 = "RowKey-003";

        String columnKey1 = "2020-01";
        String columnKey2 = "2020-02";
        String columnKey3 = "2020-03";
        String columnKey4 = "2020-04";
        String columnKey5 = "2020-05";
        String columnKey6 = "2020-06";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(10.1D, rowKey1, columnKey1);
        dataset.addValue(20.0D, rowKey1, columnKey2);
        dataset.addValue(30.0D, rowKey1, columnKey3);
        dataset.addValue(40.0D, rowKey1, columnKey4);
        dataset.addValue(50.0D, rowKey1, columnKey5);
        dataset.addValue(60.0D, rowKey1, columnKey6);

        dataset.addValue(30.1D, rowKey2, columnKey1);
        dataset.addValue(20.0D, rowKey2, columnKey2);
        dataset.addValue(40.0D, rowKey2, columnKey3);
        dataset.addValue(10.0D, rowKey2, columnKey4);
        dataset.addValue(60.0D, rowKey2, columnKey5);
        dataset.addValue(50.0D, rowKey2, columnKey6);

        dataset.addValue(60.1D, rowKey3, columnKey1);
        dataset.addValue(50.0D, rowKey3, columnKey2);
        dataset.addValue(40.0D, rowKey3, columnKey3);
        dataset.addValue(30.0D, rowKey3, columnKey4);
        dataset.addValue(20.0D, rowKey3, columnKey5);
        dataset.addValue(10.0D, rowKey3, columnKey6);

        JFreeChart lineChart = ChartFactory.createLineChart("图表标题", "横坐标", "纵坐标", dataset, PlotOrientation.VERTICAL,true,true,false);
        CategoryPlot plot = (CategoryPlot)lineChart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.yellow);
        return lineChart;
    }

    public static void pringChart(JFreeChart lineChart){
        ChartFrame frame = new ChartFrame("折线图 ", lineChart, true);
        frame.pack();
        frame.setVisible(true);
    }
}
