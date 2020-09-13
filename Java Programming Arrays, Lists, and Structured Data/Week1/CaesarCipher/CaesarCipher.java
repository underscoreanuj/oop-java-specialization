import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    
    public String encryptWithTwoKeys(String input, int key1, int key2) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i+=2) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet1.charAt(idx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }
            //Otherwise: do nothing
        }
        
        for(int i = 1; i < encrypted.length(); i+=2) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet2.charAt(idx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    
    public void testCaesar() {
        int key = 15;
        // FileResource fr = new FileResource();
        String message = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        
        // int key1 = 26-2;
        // int key2 = 26-20;
        
        String encrypted = encryptWithTwoKeys(message, 26-14, 26-24);
        System.out.println(encrypted);       
        String decrypted = encryptWithTwoKeys(encrypted, 14, 24);
        System.out.println(decrypted);
    }
    
    public String halfOfString(String message, int start){
       String halfMessage = "";
       for (int i= start; i<message.length(); i=i+2){
           halfMessage = halfMessage + message.charAt(i);
        }
        return halfMessage;
    }

    public int[] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        
        for(int k=0; k<message.length(); k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1){
                counts[dex] +=1;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] values){
        int maxLength =0;
        int indexOfMax =0;
        
        for (int k=0; k<values.length; k++){
            if (values[k]>maxLength){
                maxLength =values[k];
                indexOfMax = k;
            }
        }
        return indexOfMax;
    }
    
    public int getKey(String e_message){
       int[] freqs = countLetters(e_message);
       int maxDex = maxIndex(freqs);
       int dkey = maxDex-4;
       if (maxDex < 4) {
           dkey = 26 - (4-maxDex);
        }
        return dkey;
    }
    
    public void breakCaesarCipher(String encrypted){
        
       String message1 = halfOfString(encrypted,0);
       String message2 = halfOfString(encrypted,1);
    
       int k1= getKey(message1);
       int k2= getKey(message2);
       
       System.out.println("decrypted: ");
       System.out.println("Keys found are " + k1 + "  " + k2);
       
       String result = encryptWithTwoKeys(encrypted, 26-k1, 26-k2);
       System.out.println(result);
       
    }
    
    void testBreakTwoKeyCaesarCipher() {
        FileResource fr = new FileResource("tmp.txt");
        String message = fr.asString();
        
        //for(String word : fr.words()) {
        //    message += word + " ";
        //}
        
        System.out.println(message);
        System.out.println("****************************************");
        
        breakCaesarCipher(message);
        
    }
    
}

