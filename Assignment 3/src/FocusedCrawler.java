import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.opencsv.CSVWriter; 

public class FocusedCrawler {

	public static void main(String[] args) {
		System.out.println("Web Crawler Running....");
		String outputFilePath = "./Faculty_details.csv";
		File file = new File(outputFilePath);
		
		Document document;
		try {
			FileWriter outputfile = new FileWriter(file);
			
			CSVWriter writer = new CSVWriter(outputfile);
						
			document = Jsoup.connect("http://www.pec.ac.in/faculty-index").get();

			Element table = document.select("table").get(0);
			Elements rows = table.select("tr");
			
			for(int i=0; i<rows.size(); i++)
			{
				Element row = rows.get(i);
				Elements cols = row.select("td");
				if(cols.size() <= 1)
					cols = row.select("th");
				
				String[] info = new String[cols.size()-2];
				for(int j=1; j<cols.size()-1; j++)
					info[j-1] = cols.get(j).text();
				writer.writeNext(info);
					
			}
			 
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Done!");
	}
}
