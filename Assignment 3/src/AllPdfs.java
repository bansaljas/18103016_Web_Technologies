import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.opencsv.CSVWriter; 
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;



public class AllPdfs {

	static HashMap<String, Boolean> visited = new HashMap<String, Boolean>(); 
	static String outputFilePath = "./Pdf_Links.csv";
	static File file = new File(outputFilePath);
	static CSVWriter writer;
	
	static
	{
		FileWriter outputfile = null;
		try {
			outputfile = new FileWriter(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		 writer = new CSVWriter(outputfile);
	}
	
	//Download the pdfs
	private static void downloadUsingStream(String urlStr, String file) throws IOException{
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }

	//Crawl all the URLs to find pdfs
	public static void crawl(String currentUrl, int depth)
	{
		if(depth == 10)
			return;
		Document document;
		try {
			document = Jsoup.connect(currentUrl).ignoreContentType(true).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
		               .referrer("http://www.google.com").ignoreHttpErrors(true).get();
			
			Elements links = document.getElementsByTag("a");
			
			for (Element link : links) 
			{
				  String url = link.absUrl("href");				  
				  if(!visited.containsKey(url) && url.length() > 0)
				  {					  
					  visited.put(url, true);
					  
					  if(url.endsWith(".pdf"))
					  {
						  int index = url.lastIndexOf("/");
						  String file = url.substring(index+1);
						  downloadUsingStream(url, file);
						  String[] pdfLink = new String[1];
						  pdfLink[0] = url;
						  writer.writeNext(pdfLink);
					  }
					  else if(url.length() > 18 && url.substring(0, 18).equals("https://pec.ac.in/"))
						  crawl(url, depth+1);
				  }
			}	

		} catch (IOException e) {
			e.printStackTrace();
		}
    		
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println("Web Crawler running...");
		visited.put("https://pec.ac.in/", true);
		crawl("https://pec.ac.in/",0);
		writer.close();
		System.out.println("Done!");

	}

}
