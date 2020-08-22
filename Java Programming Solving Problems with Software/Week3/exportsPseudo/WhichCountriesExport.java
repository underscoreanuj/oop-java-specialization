/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void fishAndNuts (CSVParser parser, String country) {
        //for each row in the CSV File
        for(CSVRecord record : parser) {
            String value = record.get("Value (dollars)");
            if(value.length() > 16) {
                System.out.println(record.get("Country") + " : " + value);
            }
        }
    }

    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        fishAndNuts(parser, "coffee");
    }
}
