import java.io.IOException;
import java.util.*;
import java.io.File;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;


public class WebCrawler 
{
	static HashMap<String, Boolean> mp = new HashMap<String, Boolean>(); 
	private static final int TAG_COLUMN = 0; // Stores title of the information being stored in first column
    private static final int VALUE_COLUMN = 1; // Stores the value corresponding to the title in second column
    static int aRow = -2, pRow = -2;
    
    static WritableWorkbook workbook;
    static WritableSheet first_sheet, second_sheet;
    static 
    {
    	try {
    		workbook = Workbook.createWorkbook(new File("F:\\eclipse workspace\\Assignment 3\\URLs.xls"));
    		workbook.createSheet( "Paragraph tags", 0); //Sheet 0 will be to store the p tags
    		workbook.createSheet( "Anchor Tags", 1); //Sheet 1 will be to store the a tags
    		first_sheet = workbook.getSheet(0);
			second_sheet = workbook.getSheet(1);
			
			first_sheet.setColumnView(0, 25);
			first_sheet.setColumnView(1, 30);
			second_sheet.setColumnView(0, 40);
			second_sheet.setColumnView(1, 30);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
	public static void crawl(String currentUrl, int depth)
	{
		if(depth == 15)
			return;
		Document document;
		try {
			aRow+=2; pRow+=2;
			//Adding the current URL to the sheet
			Label headingCell = new Label(TAG_COLUMN, pRow, "Current URL:");
            first_sheet.addCell(headingCell);
            headingCell = new Label(TAG_COLUMN, aRow, "Current URL:");
            second_sheet.addCell(headingCell);
            headingCell = new Label(VALUE_COLUMN, pRow, currentUrl);
            first_sheet.addCell(headingCell);
            headingCell = new Label(VALUE_COLUMN, aRow, currentUrl);
            second_sheet.addCell(headingCell);
            aRow++; pRow++;
                                 
			//Get Document object after parsing the html from given url.
			document = Jsoup.connect(currentUrl).ignoreContentType(true).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
		               .referrer("http://www.google.com").ignoreHttpErrors(true).get();
			
			
			String title = document.title();
			
			//Adding the title to the sheets
			headingCell = new Label(TAG_COLUMN, pRow, "Title:");
            first_sheet.addCell(headingCell);
            headingCell = new Label(TAG_COLUMN, aRow, "Title:");
            second_sheet.addCell(headingCell);
            headingCell = new Label(VALUE_COLUMN, pRow, title);
            first_sheet.addCell(headingCell);
            headingCell = new Label(VALUE_COLUMN, aRow, title);
            second_sheet.addCell(headingCell);
			aRow+=2; pRow+=2; //keeping a blank line
			
			Elements links = document.getElementsByTag("a");
			Elements paras = document.getElementsByTag("p");
			
			//Adding headings for p tag sheet
			headingCell = new Label(TAG_COLUMN, pRow, "Tags");
			first_sheet.addCell(headingCell);
			headingCell = new Label(VALUE_COLUMN, pRow, "Text contained");
			first_sheet.addCell(headingCell);
			pRow++;
			
			//Storing the p tags text in the Excel Sheet
			for(Element para : paras)
			{
				String paraText = para.text();
				if(paraText.length() > 1)
				{
					Label paraCell = new Label(TAG_COLUMN, pRow, "<p>");
					first_sheet.addCell(paraCell);
					paraCell = new Label(VALUE_COLUMN, pRow, paraText);
					first_sheet.addCell(paraCell);
					pRow++;
				}
			}
			
			//Adding headings for a tag sheet
			headingCell = new Label(TAG_COLUMN, aRow, "URL");
			second_sheet.addCell(headingCell);
			headingCell = new Label(VALUE_COLUMN, aRow, "Text contained");
			second_sheet.addCell(headingCell);
			aRow++;
			
			//Storing the anchor tags and its text in the Excel Sheet
			for (Element link : links) 
			{
				  String url = link.absUrl("href");	
				  String text = link.text();
				  if(url.length() > 0)
				  {
					  Label aCell = new Label(TAG_COLUMN, aRow, url);
					  second_sheet.addCell(aCell);
					  aCell = new Label(VALUE_COLUMN, aRow, text);
					  second_sheet.addCell(aCell);
					  aRow++;
				  }
			}
			
			//Visiting all the URLs recursively till depth 3
			for (Element link : links) 
			{
				  String url = link.absUrl("href");				  
				  if(!mp.containsKey(url) && url.length() > 0)
				  {					  
					  mp.put(url, true);
					  
					  if(url.length() > 18 && url.substring(0, 18).equals("https://pec.ac.in/"))
					  {
						  crawl(url, depth+1);
					  }
				  }
			}
		} catch (IOException | WriteException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String args[])
	{
		System.out.println("Web Crawler running...");
		mp.put("https://pec.ac.in/", true);
		crawl("https://pec.ac.in/", 0);	    
		try {
			workbook.write();
			workbook.close();
		} catch (IOException | WriteException e) {
			e.printStackTrace();
		}
		System.out.println("Done!");
	}
}