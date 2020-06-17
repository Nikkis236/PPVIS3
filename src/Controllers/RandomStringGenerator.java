package Controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomStringGenerator {


    private String text = "";

    public static String readUsingBufferedReader(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader(fileName));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        while( ( line = reader.readLine() ) != null ) {
            stringBuilder.append( line );
            stringBuilder.append( ls );
        }

        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }

    public void setSource (String filename) throws IOException {

        text=readUsingBufferedReader(filename);

    }

    public List<String> getRandomStrings (){
        List<String> randomStrings = new ArrayList<>();
        int range;


        for(int n=1; n<26; n++) {

            Random random = new Random();
            range = random.nextInt(text.length()-(n+1));

            if(text.charAt(range)!=' '&& text.charAt(range+n-1)!=' ') {
                randomStrings.add(text.substring(range, range + n));
            }else n--;

        }

        /*for (String string:randomStrings) {

            System.out.println(string.length());
        }*/

        return randomStrings;
    }

    public String getText(){return text;}

}
