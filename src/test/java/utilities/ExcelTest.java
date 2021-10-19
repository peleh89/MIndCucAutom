package utilities;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ExcelTest {

//    public static void main(String[] args) throws IOException {
//
//        String path ="C:\\Users\\Master\\IdeaProjects\\MindtekCucumberAutomation\\src\\test\\resources\\testdata\\TestData.xlsx";
//        FileInputStream input = new FileInputStream(path);
//        Workbook workbook = new XSSFWorkbook(input);
//        Sheet sheet1 = workbook.getSheet("Sheet1");
//        String firstName = sheet1.getRow(1).getCell(0).toString();
//        System.out.println(firstName);
//        System.out.println(sheet1.getRow(2).getCell(1));
//
//        sheet1.getRow(2).getCell(1).setCellValue("Goga");
//
//        sheet1.createRow(3).createCell(0).setCellValue("Pitala");
//
//        int numberOfColumnsRow2 = sheet1.getRow(1).getPhysicalNumberOfCells();
//        System.out.println(numberOfColumnsRow2);
//
//        for(int i=0; i<numberOfColumnsRow2; i++){
//            System.out.println(sheet1.getRow(1).getCell(i));
//        }
//        System.out.println(Arrays.asList(sheet1.getColumnBreaks()));
//
//
//        FileOutputStream output = new FileOutputStream(path);
//        workbook.write(output);
//
//
//
//    }

    public static void main(String[] args) {

        ExcelUtils.openExcelFile("TestData", "Sheet1");

        System.out.println(ExcelUtils.getValue(1,1));

        ExcelUtils.setValue(1,1,"Cola");

        ExcelUtils.setValue(6,6,"Fanta");

    }

}
