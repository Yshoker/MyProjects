package resources;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class Excel{

	private static final String FILE_NAME = System.getProperty("user.dir") +"\\InputDataExcel.xlsx";

	private static boolean insideCS = false;
	private static boolean insideLOGOUT = false;
	private static boolean insideSHOP = false;

	public static List<String> CSInputList = new Vector<String>();
	public static List<String> LOGOUTInputList = new Vector<String>();
	public static List<String> SHOPInputList = new Vector<String>();

	public static void buildExcel() {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Input Data");
		Object[][] datatypes = { { "CSWrongQuery", "CSGoodQuery" }, { "!@", "refund" },
				{ "LLWrongMail", "LLWrongPass" }, { "sdfdsfs", "jkdfbkjdfn" }, { "SWrongQuery", "SGoodQuery" },
				{ "!@dscsdv%^", "car accessories" } };

		int rowNum = 0;
		System.out.println("Creating excel");

		for (Object[] datatype : datatypes) {
			Row row = sheet.createRow(rowNum++);
			int colNum = 0;
			for (Object field : datatype) {
				Cell cell = row.createCell(colNum++);
				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				}
			}
		}

		try {
			FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
			workbook.write(outputStream);
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Done");
	}

	public static void readExcel() {

		try {
			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();

			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();

				while (cellIterator.hasNext()) {

					Cell currentCell = cellIterator.next();
					// (currentCell.getCellTypeEnum() == CellType.NUMERIC) {

					if (currentCell.getCellTypeEnum() == CellType.STRING) {
						if (currentCell.getStringCellValue().equals("CSWrongQuery")
								|| currentCell.getStringCellValue().equals("CSGoodQuery")) {
							insideCS = true;
							insideLOGOUT = false;
							insideSHOP = false;
							break;
						} else if (currentCell.getStringCellValue().equals("LLWrongMail")
								|| currentCell.getStringCellValue().equals("LLWrongPass")) {
							insideLOGOUT = true;
							insideCS = false;
							insideSHOP = false;
							break;
						} else if (currentCell.getStringCellValue().equals("SWrongQuery")
								|| currentCell.getStringCellValue().equals("SGoodQuery")) {
							insideSHOP = true;
							insideLOGOUT = false;
							insideCS = false;
							break;
						} else if (insideCS) {
							CSInputList.add(currentCell.getStringCellValue());
							currentCell = cellIterator.next();
							CSInputList.add(currentCell.getStringCellValue());
						} else if (insideLOGOUT) {
							LOGOUTInputList.add(currentCell.getStringCellValue());
							currentCell = cellIterator.next();
							LOGOUTInputList.add(currentCell.getStringCellValue());
						} else if (insideSHOP) {
							SHOPInputList.add(currentCell.getStringCellValue());
							currentCell = cellIterator.next();
							SHOPInputList.add(currentCell.getStringCellValue());
						}
					}

				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
