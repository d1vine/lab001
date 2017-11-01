package com.andrew.excel;

import com.andrew.analyzer.SortAnalyzer;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Class contains method to export
 * data as a table to .xlsx file
 */
public class ExportToExcel {

    /**
     * instance of a {@link SortAnalyzer} class
     */
    private final SortAnalyzer analyzer;
    /**
     * full name of file
     */
    private final String FILENAME;

    /**
     * Creates instance of a class
     *
     * @param analyzer instance of a ExportToExcel class
     * @param filename full name of .xlsx file
     */
    public ExportToExcel(SortAnalyzer analyzer, String filename) {
        this.analyzer = analyzer;
        this.FILENAME = filename;
    }


    /**
     * Method creates heading for a table to export
     *
     * @param sheet excel sheet where to create heading
     */
    private void createTable(XSSFSheet sheet) {
        //cell index
        int cellInt = 1;
        Row headingRow = sheet.createRow(0);
        for (Integer N : analyzer.getArraySizeToSec().keySet()) {
            headingRow.createCell(cellInt).setCellValue(N);
            cellInt++;
        }
    }

    /**
     * Method fills up tables in a workbook
     *
     * @param workbook workbook where to export the data table
     */
    private void writeData(XSSFWorkbook workbook) {
        int columnIndex = 0;
        //look through init methods
        for (Map.Entry<String, Map<String, Map<Integer, Double>>> initMethod : analyzer.getMyData().entrySet()) {
            //creates different sheet for init method
            XSSFSheet sheet = workbook.createSheet(initMethod.getKey());
            createTable(sheet);
            int intRow = 1;
            //look through class names
            for (Map.Entry<String, Map<Integer, Double>> className : initMethod.getValue().entrySet()) {
                int intCell = 0;
                Row dataRow = sheet.createRow(intRow++);
                dataRow.createCell(intCell).setCellValue(className.getKey());
                //fill up the table
                for (Double data : className.getValue().values()) {
                    dataRow.createCell(intCell + 1).setCellValue(data);
                    intCell++;
                }
                sheet.autoSizeColumn(columnIndex++);
            }

            //draw a chart
            ChartDrawer.draw(sheet, initMethod.getValue().keySet(), analyzer.getTestData().length);
        }
    }

    /**
     * Main method to export data to .xlsx file
     */
    public void write() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        writeData(workbook);
        save(workbook);
    }

    /**
     * Method saves data to a file
     *
     * @param workbook workbook to be saved
     */
    private void save(XSSFWorkbook workbook) {
        try {
            FileOutputStream outputStream = new FileOutputStream(new File(FILENAME));
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
