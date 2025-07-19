package Utils;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelReader {
    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook wb;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle Style;

    String path;

    public ExcelReader(String path) {
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(sheetName);
        int rowcount = sheet.getLastRowNum();
        fi.close();
        return rowcount;
    }

    public int getCellCount(String sheetName, int rownum) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(sheetName);
        row = sheet.getRow(rownum);
        int Cellcount = row.getLastCellNum();
        wb.close();
        fi.close();
        return Cellcount;
    }

    public String getCellData(String sheetName, int rownum, int cellnum) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(cellnum);

        DataFormatter formatter = new DataFormatter();
        String data;
        try {
            data = formatter.formatCellValue(cell);
        } catch (Exception e) {
            data = "";
        }
        wb.close();
        fi.close();
        return data;
    }

    public void setCellData(String sheetName, int rownum, int cellnum, String data) throws IOException {
        File xfile = new File(path);
        if (!xfile.exists()) {
            wb = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            wb.write(fo);
        }

        fi = new FileInputStream(path);
        wb = new XSSFWorkbook();

        if (wb.getSheetIndex(sheetName) == -1) {
            wb.createSheet(sheetName);
            wb.getSheet(sheetName);
        }
        if (sheet.getRow(rownum) == null) {
            sheet.createRow(rownum);
            row = sheet.getRow(rownum);
            cell = row.createCell(cellnum);
            cell.setCellValue(data);
            fo = new FileOutputStream(path);
            wb.write(fo);
            wb.close();
            fi.close();
            fo.close();
        }

    }

    public void fillColorGreen(String sheetName, int rownum, int col) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(col);

        Style = wb.createCellStyle();
        Style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        Style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(Style);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }

    public void fillColorRed(String sheetName, int rownum, int col) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(col);

        Style = wb.createCellStyle();
        Style.setFillForegroundColor(IndexedColors.RED.getIndex());
        Style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(Style);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }
}