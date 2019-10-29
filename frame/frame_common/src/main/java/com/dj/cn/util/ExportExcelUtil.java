package com.dj.cn.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Field;
import java.rmi.AccessException;
import java.util.*;

/**
 * 
 * export data to excel
 * 
 * @author: yuanwc1
 * @date: 2018-02-05 17:02:69
 * @Copyright: 2018 www.lenovo.com Inc. All rights reserved.
 */
public class ExportExcelUtil {

	private final static int XLSX_MAXROWNUM = 1048575;
	private final static int DATA_SIZE_BUFFER = 200;

	/**
	 * 
	 * export data to excel @param headerMap data header @param data excel data @param sheetName the excel sheet
	 * name @return Workbook @throws ExportExcelException @throws
	 * 将数据导出到excel @param headerMap数据头@param data excel data @param sheetName excel工作表
	 * name @return Workbook @throw ExportExcelException @throw
	 */
	public static Workbook exportToExcel(LinkedHashMap<String, String> headerMap, List<?> data, String sheetName) throws Exception {
		if (headerMap.isEmpty()) {
			throw new AccessException("Please set excel header definition!");
		}
		Workbook workBook = null;
		try {
			int titleRows = 1;
			int maxRowNum = XLSX_MAXROWNUM;
			workBook = new SXSSFWorkbook(DATA_SIZE_BUFFER);
			int sheetCount = (data.size() / (maxRowNum - titleRows) + (data.size() % (maxRowNum - titleRows) > 0 ? 1 : 0));
			Sheet sheet = createTitleRow(headerMap, workBook, sheetName, sheetCount > 1);
			for (int s = 0; s < sheetCount; s++) {
				int startIndex = (maxRowNum - titleRows) * s;
				int endIndex = (maxRowNum - titleRows) * (s + 1) > data.size() ? data.size() : (maxRowNum - titleRows) * (s + 1);
				int j = 0;
				for (int rowNum = titleRows; startIndex < endIndex; rowNum++, startIndex++) {
					Object entity = data.get(startIndex);
					Row row = sheet.createRow(rowNum);
					j = 0;
					for (String key : headerMap.keySet()) {
						Cell cell = row.createCell(j, CellType.STRING);
						cell.setCellValue(getValue(entity, key));
						j++;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workBook;
	}

	/**
	 * 将数据导出到excel使用template @param columns数据列@param data excel data @param inputStream模板文件
	 * Export data to excel use template @param columns data columns @param data excel data @param inputStream template file
	 * stream @param dataAreaKey template data areaKey @param discreteData the discrete data (key:discrete areaKey.
	 * value:data value) @param sheetName the sheet name @return Workbook @throws ExportExcelException @throws
	 */
	public static Workbook exportToExcelByTemplate(String[] columns, List<?> data, InputStream inputStream, String dataAreaKey, Map<String, Object> discreteData, String sheetName) throws Exception {
		if (columns == null || columns.length == 0) {
			throw new AccessException("Column should not be blank!");
		}
		if (inputStream == null) {
			throw new AccessException("Please set export template file!");
		}
		Workbook workBook = null;
		if (!inputStream.markSupported()) {
			inputStream = new PushbackInputStream(inputStream, 8);
		}
		try {
			workBook = new SXSSFWorkbook(new XSSFWorkbook(inputStream), DATA_SIZE_BUFFER);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AccessException("Parse excel template occured exception!");
		}
		if (discreteData != null && !discreteData.isEmpty()) {
			fillDiscreteData(workBook, discreteData);
		}
		fillListData(workBook, dataAreaKey, columns, data);
		return workBook;
	}

	/**
	 * 
	 * createTitleRow
	 * 
	 * @param headerMap column header @param workBook excel workbook @param sheetName sheetName @param isMultiSheet is more
	 *            than one sheet @return Sheet @throws
	 */
	private static Sheet createTitleRow(LinkedHashMap<String, String> headerMap, Workbook workBook, String sheetName, boolean isMultiSheet) {
		if (sheetName == null || "".equals(sheetName.trim())) {
			sheetName = "sheet";
		}
		int sheetIndex = workBook.getNumberOfSheets();
		Sheet sheet = workBook.createSheet();
		workBook.setSheetName(sheetIndex, sheetName + (isMultiSheet ? (sheetIndex + 1) : ""));
		Font font = workBook.createFont();
		font.setColor(Font.COLOR_NORMAL);
		CellStyle cellStyle = workBook.createCellStyle();
		cellStyle.setFont(font);
		Row titleRow = sheet.createRow(0);
		int colIndex = 0;
		for (String key : headerMap.keySet()) {
			sheet.setColumnWidth(colIndex, 3000);
			Cell cell = titleRow.createCell(colIndex, CellType.STRING);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(headerMap.get(key));
			colIndex++;
		}
		return sheet;
	}

	/**
	 * 
	 * fillDiscreteData
	 * 
	 * @param workBook excel workbook @param discreteData discrete data @throws
	 */
	private static void fillDiscreteData(Workbook workBook, Map<String, Object> discreteData) {
		if (discreteData != null && !discreteData.isEmpty()) {
			for (String discreteKey : discreteData.keySet()) {
				String value = discreteData.get(discreteKey) == null ? "" : String.valueOf(discreteData.get(discreteKey));
				Name discreteName = workBook.getName(discreteKey);
				if (discreteName != null) {
					AreaReference areaReference = new AreaReference(discreteName.getRefersToFormula(), workBook.getSpreadsheetVersion());
					CellReference[] cellReferences = areaReference.getAllReferencedCells();
					for (CellReference cellReference : cellReferences) {
						Sheet sheet = workBook.getSheet(cellReference.getSheetName());
						Row row = sheet.getRow(cellReference.getRow());
						if (row == null) {
							row = sheet.createRow(cellReference.getRow());
						}
						Cell cell = row.getCell(Integer.valueOf(cellReference.getCol()));
						if (cell == null) {
							cell = row.createCell(Integer.valueOf(cellReference.getCol()));
						}
						cell.setCellValue(value.trim());
					}
				}
			}
		}
	}

	/**
	 * 
	 * fillListData
	 * 
	 * @param workBook excel workbook @param dataAreaKey dataList area signed key @param columns data columns @param data
	 *            list data @throws
	 */
	private static void fillListData(Workbook workBook, String dataAreaKey, String[] columns, List<?> data) {
		Name name = workBook.getName(dataAreaKey);
		if (name != null && data != null && !data.isEmpty()) {
			int listDataSize = data.size();

			AreaReference areaReference = new AreaReference(name.getRefersToFormula(), workBook.getSpreadsheetVersion());
			CellReference[] cellReferences = areaReference.getAllReferencedCells();
			int rowIndex = cellReferences[0].getRow();
			int columnIndex = cellReferences[0].getCol();
			Sheet sheet = workBook.getSheet(cellReferences[0].getSheetName());
			/**
			 * if (false) { int lastRowNum = sheet.getLastRowNum() + 1; if (listDataSize > 1) { sheet.shiftRows(rowIndex,
			 * lastRowNum, listDataSize - 1); } }
			 */

			Object entity = null;
			Row row = null;
			Cell cell = null;
			for (int j = 0; j < listDataSize; j++, rowIndex++) {
				entity = data.get(j);
				row = sheet.createRow(rowIndex);
				for (int i = 0, columnsLength = columns.length; i < columnsLength; i++) {
					cell = row.createCell(columnIndex + i);
					cell.setCellValue(getValue(entity, columns[i]));
				}
			}
		}
	}

	/**
	 * 
	 * Get dataObject field value @param dataObj data Object @param field field name @return String @throws
	 */
	private static String getValue(Object dataObj, String field) {
		Object value = null;
		try {
			if (!Map.class.isAssignableFrom(dataObj.getClass())) {
				Field[] fields = getFields(dataObj.getClass());
				for (int i = 0; i < fields.length; i++) {
					fields[i].setAccessible(true);
					if (fields[i].getName().equalsIgnoreCase(field)) {
						value = fields[i].get(dataObj);
						break;
					}
				}
			} else {
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) dataObj;
				value = map.get(field);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (value != null) {
			return String.valueOf(value);
		}
		return null;
	}

	private static Field[] getFields(Class<?> clazz) {
		Set<Field> fieldSet = new HashSet<>();
		Field[] selfFields = clazz.getDeclaredFields();
		Field[] superClassFields = clazz.getSuperclass().getDeclaredFields();
		fieldSet.addAll(Arrays.asList(selfFields));
		fieldSet.addAll(Arrays.asList(superClassFields));
		return fieldSet.toArray(new Field[0]);
	}
}