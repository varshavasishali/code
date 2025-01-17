/**
 * @author Varsha Vaishali
 * @date Sept 09, 2019
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import java.io.Writer;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;

public class ReadExcel_cellType{

  public static void main( String [] args ) {
	  Writer writer = null;
	  ArrayList al=new ArrayList();
	  try(Workbook wb=WorkbookFactory.create(new File("C:\\Users\\HP\\Desktop\\test.xls")))
	  {
		  Sheet sheet=wb.getSheetAt(0);
		  int rowstart=sheet.getFirstRowNum();
		  int lastrow=sheet.getLastRowNum();
		

		    int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();

		    //Create a loop over all the rows of excel file to read it
		    for (int i = 0; i < rowCount+1; i++) {

		        Row rowForLoop = sheet.getRow(i);

		        //Create a loop to print cell values in a row
		        for (int j = 0; j < rowForLoop.getLastCellNum(); j++) {
		        	
		        	switch (rowForLoop.getCell(j).getCellType()) {
		            case STRING: 
		            	//Print Excel STRING data in console
			            System.out.print(rowForLoop.getCell(j).getStringCellValue()+"|| "); 
			            al.add(rowForLoop.getCell(j,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue()+"|| ");
			            break;
		            case NUMERIC: 
		            	//Print Excel data in console
		            	if (DateUtil.isCellDateFormatted(rowForLoop.getCell(j))) {
		            		//Print Excel DATE data in dateFormat to console
				            System.out.print(rowForLoop.getCell(j).getDateCellValue() + "|| "); 
				            al.add(rowForLoop.getCell(j,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getDateCellValue() + "|| ");
	                    } else {
	                    	//Print Excel NUMERIC data to console
				            System.out.print(rowForLoop.getCell(j).getNumericCellValue() + "|| ");
				            al.add(rowForLoop.getCell(j,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue() + "|| ");
	                    }
		            	break;
		            case BOOLEAN: 
		            	//Print Excel BOOLEAN data to console
			            System.out.print(rowForLoop.getCell(j,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue() + "|| ");
			            al.add(rowForLoop.getCell(j,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue() + "|| ");
			            break;
			        case FORMULA: 
			        	//Print Excel BOOLEAN data to console
			        	switch(rowForLoop.getCell(j).getCachedFormulaResultType()) {
			            case NUMERIC:
			            	System.out.println("Last evaluated as: " + rowForLoop.getCell(j,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue() + "|| ");
			            	 al.add(rowForLoop.getCell(j,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue() + "|| ");
			                break;
			            case STRING:
			                System.out.println("Last evaluated as \"" + rowForLoop.getCell(j,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue() + "|| ");
			                al.add(rowForLoop.getCell(j,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue() + "|| ");
			                break;
			            case BOOLEAN:
			                System.out.println("Last evaluated as \"" + rowForLoop.getCell(j,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue() + "|| ");
			                al.add(rowForLoop.getCell(j,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getBooleanCellValue() + "|| ");
			                break;
			            case ERROR:
			            	System.out.println("Last evaluated as \"" + rowForLoop.getCell(j,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getErrorCellValue() + "|| ");
			            	al.add(rowForLoop.getCell(j,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getErrorCellValue() + "|| ");
			            default: System.out.println("FORMULA CELL NOT EVELUATED YET|| ");
			            al.add("FORMULA CELL NOT EVELUATED YET|| ");
			        	}
			        case BLANK: 
		            	//Print Excel BOOLEAN data to console
			            System.out.print("|| ");
			            al.add("|| ");
			            break;
			        case _NONE: 
		            	//Print Excel BOOLEAN data to console
			            System.out.print("|| ");
			            al.add("|| ");
			            break;
			        }
		        }
				System.out.println();
			}
			
			  try (FileWriter writer1 = new FileWriter("E:\\TestSample1.txt");
			             BufferedWriter bw = new BufferedWriter(writer1)) {
				  		for(int k=0; k<al.size(); k++) {
			            bw.write(al.get(k).toString());
			            bw.newLine();
				  		}

			        } catch (IOException e) {
			            System.err.format("IOException: %s%n", e);
			        }
			  System.out.println("-------------------------");
			 
			  
		  }
				  
	  
	  catch(Exception e){
		  e.printStackTrace();
	  }

	   
	}

	 
   
}