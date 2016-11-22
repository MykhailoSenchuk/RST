package IO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * You should create method which replace words in the File and rewrite File content with new values
 * a) read file, save to string var
 * b) replace words
 * c) rewrite file with string
 * File fileContentReplacer(Map<String, String> map)
 */
public class Task2 {
    public static void main(String[] args) {

        String inPath = "src/IO/Task";
        String outPath = "src/IO/output.txt";
        WorkFile workFile = new WorkFile(inPath,outPath);

        // replace parameters
        Map<String, String> replaceTo = new HashMap<String, String>(){
            {
                put("replace","FUCK UP");
            }
        };

        try{
            workFile.fileContentReplacer(replaceTo);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
