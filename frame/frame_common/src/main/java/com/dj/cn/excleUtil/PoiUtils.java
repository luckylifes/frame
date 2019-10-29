package com.dj.cn.excleUtil;

import devutility.external.poi.common.ExcelType;
import devutility.external.poi.models.FieldColumnMap;
import devutility.external.poi.models.RowStyle;
import devutility.external.poi.utils.ExcelUtils;
import devutility.external.poi.utils.SheetUtils;
import devutility.external.poi.utils.WorkbookUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @version V1.0
 * @Title: poiUtil
 * @Package: com.dj.cn.excleUtil
 * @author: Lenovo
 * @date: 2019/7/22 11:50
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
public class PoiUtils {
    public PoiUtils() {
    }

    public static <T> List<T> read(String filePath, String sheetName, FieldColumnMap<T> fieldColumnMap, Class<T> clazz) throws Exception {
        Workbook workbook = WorkbookUtils.load(filePath);
        Sheet sheet = SheetUtils.get(workbook, sheetName);
        List<T> list = SheetUtils.toList(sheet, fieldColumnMap, clazz);
        workbook.close();
        return list;
    }

    public static <T> List<T> read(InputStream inputStream, String sheetName, FieldColumnMap<T> fieldColumnMap, Class<T> clazz) throws Exception {
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = SheetUtils.get(workbook, sheetName);
        List<T> list = SheetUtils.toList(sheet, fieldColumnMap, clazz);
        workbook.close();
        return list;
    }

    public static <T> void save(InputStream templateInputStream, String sheetName, FieldColumnMap<T> fieldColumnMap, List<T> list, RowStyle rowStyle, OutputStream outputStream) throws Exception {
        Workbook workbook = WorkbookUtils.save(templateInputStream, sheetName, fieldColumnMap, list, rowStyle);
        workbook.write(outputStream);
        workbook.close();
    }

    public static <T> void save(InputStream templateInputStream, String sheetName, FieldColumnMap<T> fieldColumnMap, List<T> list, OutputStream outputStream) throws Exception {
        save((InputStream)templateInputStream, sheetName, fieldColumnMap, list, (RowStyle)null, (OutputStream)outputStream);
    }

    public static <T> void save(InputStream templateInputStream, String sheetName, FieldColumnMap<T> fieldColumnMap, List<T> list, RowStyle rowStyle, String filePath) throws Exception {
        FileOutputStream outputStream = createFileOutputStream(filePath);
        save((InputStream)templateInputStream, sheetName, fieldColumnMap, list, rowStyle, (OutputStream)outputStream);
    }

    public static <T> void save(InputStream templateInputStream, String sheetName, FieldColumnMap<T> fieldColumnMap, List<T> list, String filePath) throws Exception {
        save((InputStream)templateInputStream, sheetName, fieldColumnMap, list, (RowStyle)null, (String)filePath);
    }

    public static <T> void save(ExcelType excelType, String sheetName, FieldColumnMap<T> fieldColumnMap, List<T> list, OutputStream outputStream) throws Exception {
        Workbook workbook = WorkbookUtils.save(excelType, sheetName, fieldColumnMap, list);
        workbook.write(outputStream);
        workbook.close();
    }

    public static <T> void save(ExcelType excelType, FieldColumnMap<T> fieldColumnMap, List<T> list, OutputStream outputStream) throws Exception {
        String sheetName = SheetUtils.getName(1);
        save(excelType, sheetName, fieldColumnMap, list, outputStream);
    }

    public static <T> void save(String sheetName, FieldColumnMap<T> fieldColumnMap, List<T> list, String filePath) throws Exception {
        FileOutputStream outputStream = createFileOutputStream(filePath);
        ExcelType excelType = ExcelUtils.getExcelType(filePath);
        save((ExcelType)excelType, sheetName, fieldColumnMap, list, (OutputStream)outputStream);
    }

    public static <T> void save(FieldColumnMap<T> fieldColumnMap, List<T> list, String filePath) throws Exception {
        String sheetName = SheetUtils.getName(1);
        save(sheetName, fieldColumnMap, list, filePath);
    }

    public static <T> void save(Workbook templateWorkbook, String sheetName, FieldColumnMap<T> fieldColumnMap, List<T> list, RowStyle rowStyle, OutputStream outputStream) throws Exception {
        Workbook workbook = WorkbookUtils.save(templateWorkbook, sheetName, fieldColumnMap, list, rowStyle);
        workbook.write(outputStream);
    }

    public static <T> void save(Workbook templateWorkbook, String sheetName, FieldColumnMap<T> fieldColumnMap, List<T> list, RowStyle rowStyle, String filePath) throws Exception {
        FileOutputStream outputStream = createFileOutputStream(filePath);
        save((Workbook)templateWorkbook, sheetName, fieldColumnMap, list, rowStyle, (OutputStream)outputStream);
    }

    private static FileOutputStream createFileOutputStream(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        return new FileOutputStream(file);
    }
}
