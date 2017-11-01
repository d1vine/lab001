package com.andrew.excel;


import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.charts.XSSFChartLegend;

import java.util.Set;


/**
 * Class contains method to draw charts
 */
class ChartDrawer {

    /**
     * Method draw chart based on data table
     *
     * @param sheet      sheet that contains data table
     * @param classNames {@code String} class name used for a legend
     * @param colNumber  table columns
     */
    static void draw(XSSFSheet sheet, Set<String> classNames, int colNumber) {
        XSSFDrawing drawing = sheet.createDrawingPatriarch();
        //chart position
        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 10, 15, 40);
        XSSFChart chart = drawing.createChart(anchor);
        //chart legend
        XSSFChartLegend legend = chart.getOrCreateLegend();
        legend.setPosition(LegendPosition.BOTTOM);

        //table data
        LineChartData data = chart.getChartDataFactory().createLineChartData();
        //chart axis
        ChartAxis bottomAxis = chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
        ValueAxis leftAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrossBetween(AxisCrossBetween.BETWEEN);

        setData(sheet, data, classNames, colNumber);
        chart.plot(data, bottomAxis, leftAxis);
    }

    /**
     * Reads data from a sheet
     *
     * @param sheet      sheet that contains a data table
     * @param data       read data
     * @param classNames {@code String} class name used for a legend
     * @param colNumber  table columns
     */
    private static void setData(XSSFSheet sheet, LineChartData data, Set<String> classNames, int colNumber) {
        //reads heading row
        ChartDataSource<Number> xs = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(0, 0, 1, colNumber));
        int i = 1;
        //reads tables rows
        for (String name : classNames) {
            ChartDataSource<Number> ys1 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(i, i, 1, colNumber));
            LineChartSeries chartSerie = data.addSeries(xs, ys1);
            chartSerie.setTitle(name);
            i++;
        }
    }



}
