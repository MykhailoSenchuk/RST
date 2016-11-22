package IO;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

/**
 * forRST: general. Should I create separate class for methods like "replacer", "fileContentReplacer" below
 * forMHS: it depends. I think it's fine as it is.
 */
public class WorkFile {

    private File inputFile;
    private File outputFile;

    
    // forMHS: Reuse constructors as much as possible. This allows you to put general initialization logic to the single place.
    
    /**
     * Assign one file for input and output
     * @param file
     */
    public WorkFile(File file){
        this(file, file);
    }

    /**
     * Assign different output and input files
     * @param inputFile
     * @param outputFile
     */
    public WorkFile(File inputFile, File outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        // forMHS: this is the main constructor and all common logic is here.
    }

    /**
     * Assign one file for input and output based on path
     * @param path
     */
    public WorkFile(String path){
        // forMHS: why didn't you use https://docs.oracle.com/javase/7/docs/api/java/io/File.html#File(java.lang.String) constructor?
        // forMHS: left your code for now.
        this(Paths.get(path).toFile());
    }

    /**
     * Assign one file for input and output based on path
     * @param inPath input file path
     * @param outPath output file path
     */
    public WorkFile(String inPath, String outPath){
        this(Paths.get(inPath).toFile(), Paths.get(outPath).toFile());
    }



    /**
     * read inputFile and save to String array
     * @return String with text from file
     */
    public String readFile() throws IOException {
        /*
        forRST: is there a better class to read from file string text?
        forMHS: http://www.adam-bien.com/roller/abien/entry/java_8_reading_a_file
        forRST: How can I read cyrillic text?
        forMHS: http://docs.oracle.com/javase/7/docs/api/java/lang/String.html#String(byte[],%20java.nio.charset.Charset)    
         */

         // forMHS: Never EVER build string like this! Count how many string allocations you're doing! That's awful!
         // forMHS: use https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html
         // forMHS: http://stackoverflow.com/questions/5234147/why-stringbuilder-when-there-is-string/5234160#5234160
         // forMHS: http://stackoverflow.com/questions/18453458/string-builder-vs-string-concatenation/18453485#18453485

        String buffer = new String(Files.readAllBytes( Paths.get( inputFile.getPath() ) ), StandardCharsets.UTF_8);

        return buffer;
    }


    /**
     * write string to the outputFile
     */
    public void writeFile(String text) throws IOException{
        /*
        forRST: is there a better class to write string text to file ?
        forMHS: http://www.adam-bien.com/roller/abien/entry/java_7_writing_a_string
        */
        Files.write(Paths.get( outputFile.getPath() ), text.getBytes());
    }


    /**
     * add string to the outputFile
     */
    public void addToFile(String text)throws IOException{

        /*
        forRST: is there a better class to write string text to file ?
        forMHS: http://www.adam-bien.com/roller/abien/entry/java_7_writing_a_string
        */
        Files.write(Paths.get( outputFile.getPath() ), text.getBytes(), StandardOpenOption.APPEND);
    }

    /**
     * Read inputFile and return string with replaced words based on map
     * @param map (KEY word to be replaced -> VALUE word replaced to)
     * @throws IOException
     */
    public String replacer(Map<String, String> map) throws IOException{
        String result = readFile();

        //forMHS: btw, you'd better use 
        // https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#replace-java.lang.CharSequence-java.lang.CharSequence-
        // or https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#replaceAll-java.lang.String-java.lang.String-
        // instead of your implementation.
        
        // receive distinct words from inputFile
        //forRST: is there a possibility to make more complex split options (split by space AND by paragraph AND by coma) ?
        //forMHS: http://docs.oracle.com/javase/7/docs/api/java/lang/String.html#split(java.lang.String) splits by any Regular Expression.

        for (Map.Entry<String, String> entry : map.entrySet()) {
            result = result.replace(entry.getKey(),entry.getValue());
        }

        return result;
    }

    /**
     * Read inputFile and write text with replaced words(based on map) to outputFile
     * @param map: KEY word to be replaced -> VALUE word replaced to
     * @return outputFile
     * @throws IOException
     */
    public File fileContentReplacer(Map<String, String> map)throws IOException{
        String text = replacer(map);
        writeFile(text);
        return outputFile;
    }

    /**
     * Replace words in the inputFile and add result to existing outputFile content
     * @param map: KEY word to be replaced -> VALUE word replaced to
     * @return outputFile
     * @throws IOException
     */
    public File fileContentMerger(Map<String, String> map) throws IOException{
        String text = replacer(map);
        addToFile(text);
        return outputFile;
    }


    /**
     * Counts quantity(case insensetive) of searched word in inputFile
     * @param searchWord
     * @return Print number of times searchWord repeated in the file, 0 if wasn't found
     * @throws IOException
     */
    public int countWord (String searchWord) throws IOException {

        return readFile().split("(?i)"+searchWord).length-1;
    }

    public File getInputFile() {
        return inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }
}
