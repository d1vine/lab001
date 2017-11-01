import com.andrew.analyzer.SortAnalyzer;
import com.andrew.excel.ExportToExcel;


class Lab1 {

    public static void main(String... args) {




        SortAnalyzer analyzer = new SortAnalyzer(new int[]{10,100,10000,50000,100000});
        analyzer.analyze("com.andrew.sorters.Sort", "com.andrew.fillers.ArrayInit");

        ExportToExcel export = new ExportToExcel(analyzer ,"C:\\test\\list11.xlsx");
        export.write();



    }
}
