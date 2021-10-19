package utilities;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {

    /*
    .openExcelFile(String name of file, and sheet name "")  -> return void no return
    .getValue(0,1) -> return String , 0 and 1 stands for row and column
    .setValue(5,0,"Value"); -> 5 and 0 stands for row and column and newValue for new Value
     */

    private static Sheet sheet;
    private static Workbook workbook;
    private static FileOutputStream output;
    private static FileInputStream input;
    private static String path;

    /**
     * This method will open excel spreadsheet
     */

    public static void openExcelFile(String fileName, String sheetName){
        path = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\"+fileName+".xlsx";

        try {
            input = new FileInputStream(path);
            workbook = new XSSFWorkbook(input);
            sheet = workbook.getSheet(sheetName);
        } catch (FileNotFoundException e) {
            System.err.println("Excel spreadsheet path is invalid: "+path);
        } catch (IOException e) {
            System.err.println("Could not open Excel spreadsheet.");
        }
    }

    /**
     * This method will return the data from provided row number and cell
     */

    public static String getValue(int rowNum, int cellNum){
        String value = sheet.getRow(rowNum).getCell(cellNum).toString();
        return value;
    }

    /**
     * This method will set value to provided row and cell numbers.
     */

    public static void setValue(int rowNum, int cellNum, String value){
        if(sheet.getPhysicalNumberOfRows()<=rowNum){
            sheet.createRow(rowNum).createCell(cellNum).setCellValue(value);
        }else if (sheet.getRow(rowNum).getPhysicalNumberOfCells()<=cellNum){
            sheet.getRow(rowNum).createCell(cellNum).setCellValue(value);
        }else {
            sheet.getRow(rowNum).getCell(cellNum).setCellValue(value);
        }

        try {
            output = new FileOutputStream(path);
            workbook.write(output);
        } catch (FileNotFoundException e) {
            System.err.println("Excel spreadsheet path is invalid: "+path);
        } catch (IOException e) {
            System.err.println("Could not save changes to excel spreadsheet.");
        }finally {
            try {
                output.close();
            } catch (IOException e) {
                System.err.println("Could not close FileOutputStream object.");
            }
        }
    }
}
