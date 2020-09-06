/**
 * Print out the names for which 100 or fewer babies were born in a chosen CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.util.*;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.File;

public class BabyBirths {
    protected static CSVParser getFileParser(int year) {
        FileResource fr = new FileResource(String.format("./data/yob%s.csv", year));
        return fr.getCSVParser(false);
    }
    
    protected Map<Integer, Integer> getAllYearsByNameAndGender(String name, String gender) throws IOException {

        DirectoryResource dr = new DirectoryResource();

        Map<Integer, Integer> ranks = new HashMap<>();

        for (File file : dr.selectedFiles()) {
            String fn = file.getName().replaceAll("[^0-9]", "");
            if(fn.length() > 0) {
                int year = Integer.parseInt(fn);
                int rank = getRank(year, name, gender);

                if (rank >= 0) {
                    ranks.put(year, rank);
                }
            }
        }

        return ranks;

    }
    
    public int getRank(int year, String name, String gender) throws IOException {
        CSVParser parser = getFileParser(year);
        List<CSVRecord> babies = parser.getRecords().stream().filter(w -> w.get(1).equals(gender)).collect(Collectors.toList());
        
        babies.sort((o1, o2) -> {
            if(Integer.parseInt(o1.get(2)) > Integer.parseInt(o2.get(2)))
                return -1;
            else
                return 1;
        });
        
        int rank = -1;
        
        for(CSVRecord rec : babies)
            if(rec.get(0).equals(name)) {
                rank = babies.indexOf(rec) + 1;
                break;
            }
        
        return rank;
    }
    
    public String getName(int year, int rank, String gender) throws IOException {
        CSVParser parser = getFileParser(year);
        List<CSVRecord> babies = parser.getRecords().stream().filter(w -> w.get(1).equals(gender)).collect(Collectors.toList());
        
        babies.sort((o1, o2) -> {
            if(Integer.parseInt(o1.get(2)) > Integer.parseInt(o2.get(2)))
                return -1;
            else
                return 1;
        });
        
        if(babies.size() < rank || rank <= 0) return "NONE";
        else return babies.get(rank-1).get(0);
    }
    
    public int yearOfHighestRank(String name, String gender) throws IOException {
        Map<Integer, Integer> ranks = getAllYearsByNameAndGender(name, gender);
        
        if(ranks.size() == 0) return -1;
        
        Map<Integer, Integer> sortedRanks = ranks.entrySet().stream().sorted(Map.Entry.comparingByValue((o1, o2) -> {
            if(o1 >= o2) return 1;
            else return -1;
        })).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        
        return (int) sortedRanks.keySet().toArray()[0];
    }
    
    public Double getAverageRank(String name, String gender) throws IOException {

        Map<Integer, Integer> ranks = getAllYearsByNameAndGender(name, gender);

        if (ranks.size() == 0) {
            return -1.0;
        }

        return (double) ranks.values().stream().mapToInt(Integer::intValue).sum() / ranks.size();

    }
    
    public Integer getTotalBirthsRankedHigher(int year, String name, String gender) throws IOException {

        CSVParser parser = getFileParser(year);

        List<CSVRecord> babies = parser.getRecords().stream().filter(w -> w.get(1).equals(gender)).collect(Collectors.toList());


        // sort list by rank
        babies.sort((o1, o2) -> {
            if (Integer.parseInt(o1.get(2)) > Integer.parseInt(o2.get(2)))
                return -1;
            else
                return 1;
        });

        int rank = -1;
        for (CSVRecord rec : babies) if (rec.get(0).equals(name)) rank = babies.indexOf(rec);

        if (rank == -1) {
            return -1;

        }

        List<CSVRecord> listBabies = babies.subList(0, rank);


        int sum = listBabies.stream().mapToInt(value -> Integer.parseInt(value.get(2))).sum();

        return sum;

    }
    
    
    public void test() {
        try {
            // System.out.println(getName(1982, 450, "M"));
            int rank = getRank(1972, "Susan", "F");
            System.out.println(rank);
            String new_name = getName(2014, rank, "F");
            System.out.println(new_name);
        }
        catch(Exception e) {
            System.out.println("error" + e);
        }
    }
    
    
    public void printNames () {
        FileResource fr = new FileResource();
        int count = 0 ;
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if(rec.get(1).equals("M")) {
                count += 1;
            }
            // int numBorn = Integer.parseInt(rec.get(2));
            // if (numBorn <= 100) {
            //     System.out.println("Name " + rec.get(0) +
            //     " Gender " + rec.get(1) +
            //                " Num Born " + rec.get(2));
            // }
        }   
        System.out.println("count: " + count);
    }
}
