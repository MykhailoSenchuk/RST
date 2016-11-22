package IO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mykhailo on 11/12/2016.
 * You should create method which replace words in the File and returns String with replaced values
 * a) read file, save to string var
 * b) replace words
 * c) return new string
 * String replacer(Map<String, String> map)
 */
public class Task1 {
    public static void main(String[] args) {
        String path = "src/IO/Task";
        WorkFile workFile = new WorkFile(path);

        Map<String, String> replaceTo = new HashMap<String, String>() {
            {
                put("file", "BITCH");
            }
        };

        try {
            System.out.println(workFile.replacer(replaceTo));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}