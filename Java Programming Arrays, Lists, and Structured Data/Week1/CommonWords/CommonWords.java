
/**
 * Count common words in Shakespeare's works
 * 
 * @author Duke Software Team
 * @version 1.0
 */
import edu.duke.*;

public class CommonWords
{
    public String[] getCommon(){
        FileResource resource = new FileResource("data/common.txt");
        String[] common = new String[20];
        int index = 0;
        for(String s : resource.words()){
            common[index] = s;
            index += 1;
        }
        return common;
    }
    
    public int indexOf(String[] list, String word) {
        for (int k=0; k<list.length; k++) {
            if (list[k].equals(word)) {
                   return k;
               }
           }
        return -1;
    }
    
    public void countWords(FileResource resource, String[] common, int[] counts){
        for(String word : resource.words()){
            word = word.toLowerCase();
            int index = indexOf(common,word);
            if (index != -1) {
                counts[index] += 1;
            }
        }
    }
    void countShakespeare(){
        //String[] plays = {"caesar.txt", "errors.txt", "hamlet.txt",
            //          "likeit.txt", "macbeth.txt", "romeo.txt"};
        String[] plays = {"lotsOfWords.txt"};
        String[] common = getCommon();
        int[] counts = new int[common.length];
        for(int k=0; k < plays.length; k++){
            FileResource resource = new FileResource("data/" + plays[k]);
            countWords(resource,common,counts);
            System.out.println("done with " + plays[k]);
        }
        
        for(int k=0; k < common.length; k++){
            System.out.println(common[k] + "\t" + counts[k] + "\t" + common[k].length());
        }
    }
    
    String processWord(String word) {
        StringBuilder sb = new StringBuilder();
        int len = word.length()-1;
        int start = 0;
        while(len >= 0 && !Character.isLetter(word.charAt(len))) --len;
        while(start < len && !Character.isLetter(word.charAt(start))) ++start;
        
        return word.substring(start, len+1);
    }
    
    void countCommonWordLength() {
        FileResource resource = new FileResource("data/tmp.txt");
        int[] counts = new int[100];
        
        for(String word : resource.words()){
            word = word.toLowerCase();
            word = processWord(word);
            int len = word.length();
            if (len >= 0 && len < 100) {
                counts[len] += 1;
            }
        }
        
        for(int i=0; i<100; ++i) {
            System.out.println(i + ":\t" + counts[i]);
        }
    }
}
