import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Data_Driven_Testing {

	public static void main(String[] args) throws IOException
	{
		FileInputStream file=new FileInputStream("D:\\Sujani\\Projects\\API-Testing\\DataDrivenInputData.xlsx");
		XSSFWorkbook workbook= new XSSFWorkbook(file);
		int sheets= workbook.getNumberOfSheets();
		for(int i=0;i<sheets;i++)
		{
			if(workbook.getSheetName(i).equalsIgnoreCase("DataSheet1"))
			{
				XSSFSheet sheet = workbook.getSheetAt(i);
			}
			
		}
	}

}
