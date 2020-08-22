/**
 * Find the highest (hottest) temperature in any number of files of CSV weather data chosen by the user.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
    public CSVRecord ColdestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord smallestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    }

    public void testColdestInDay () {
        FileResource fr = new FileResource("data/2013/weather-2013-08-10.csv");
        CSVRecord largest = ColdestHourInFile(fr.getCSVParser());
        System.out.println("lowest TemperatureF was " + largest.get("TemperatureF") +
                   " at " + largest.get("DateUTC"));
    }

    public CSVRecord coldestInManyDays() {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = ColdestHourInFile(fr.getCSVParser());
            // use method to compare two records
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    }
    
    public double getAverage(CSVParser parser, String col) {
        //start with largestSoFar as nothing
        double sum = 0, count = 0;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            if(Integer.parseInt(currentRow.get("Humidity")) >= 80) {
                sum += Double.parseDouble(currentRow.get(col));
                count += 1;
            }
        }
        //The largestSoFar is the answer
        return sum / count;
    }

    public CSVRecord getSmallestOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar) {
        //If smallestSoFar is nothing
        if (smallestSoFar == null) {
             smallestSoFar = currentRow;
        }
        //Otherwise
        else {
            if(!currentRow.get("Humidity").equals("N/A")) {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                //Check if currentRow’s temperature < smallestSoFar’s
                if (currentTemp < smallestTemp) {
                    //If so update smallestSoFar to currentRow
                    smallestSoFar = currentRow;
                }
            }
        }
        return smallestSoFar;
    }

    public void testColdestInManyDays () {
        CSVRecord largest = coldestInManyDays();
        System.out.println("coldest TemperatureF was " + largest.get("TemperatureF") +
                   " at " + largest.get("DateUTC"));
    }
    
    public void testGetAverage() {
        FileResource fr = new FileResource("data/2013/weather-2013-09-02.csv");
        double average = getAverage(fr.getCSVParser(), "TemperatureF");
        System.out.println("average: " + average);
    }
}
