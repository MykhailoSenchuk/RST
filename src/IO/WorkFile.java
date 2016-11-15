package IO;

import java.io.*;
import java.nio.file.Paths;
import java.util.Map;

/**
 * forRST: general. Should I create separate class for methods like "replacer", "fileContentReplacer" below
 */
public class WorkFile {

    private File inputFile;
    private File outputFile;

    /**
     * Assign one file for input and output
     * @param file
     */
    public WorkFile(File file){
        inputFile = file;
        outputFile = file;
    }

    /**
     * Assign different output and input files
     * @param inputFile
     * @param outputFile
     */
    public WorkFile(File inputFile, File outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    /**
     * Assign one file for input and output based on path
     * @param path
     */
    public WorkFile(String path){
        inputFile = Paths
                .get(path)
                .toFile();
        outputFile = Paths
                .get(path)
                .toFile();
    }

    /**
     * Assign one file for input and output based on path
     * @param inPath input file path
     * @param outPath output file path
     */
    public WorkFile(String inPath, String outPath){
        inputFile = Paths
                .get(inPath)
                .toFile();
        outputFile = Paths
                .get(outPath)
                .toFile();
    }



    /**
     * read inputFile and save to String array
     * @return String with text from file
     */
    public String readFile() throws IOException {
        /*
        forRST: is there a better class to read from file string text?
        How can I read cyrillic text
         */

        String buffer = "";
        try(FileInputStream in = new FileInputStream(inputFile)){
            int b;
            while(( b = in.read() ) != -1) {
                buffer = buffer + (char) b;
            }
        }

        return buffer;
    }


    /**
     * write string to the outputFile
     */
    public void writeFile(String text) throws IOException{
        /*
        forRST: is there a better class to write string text to file ?
        */
        try(PrintWriter out = new PrintWriter ( outputFile )){
            out.print(text);
        }
    }


    /**
     * add string to the outputFile
     */
    public void addToFile(String text)throws IOException{

        /*
        forRST: is there a better class to write string text to file ?
        */

        try(PrintWriter out = new PrintWriter ( new FileOutputStream(outputFile, true) )){
            out.append(text);
        }
    }

    /**
     * Read inputFile and return string with replaced words based on map
     * @param map (KEY word to be replaced -> VALUE word replaced to)
     * @throws IOException
     */
    public String replacer(Map<String, String> map) throws IOException{
        String result = null;

        // receive distinct words from inputFile
        //forRST: is there a possibility to make more complex split options (split by space AND by paragraph AND by coma) ?
        String[] words = readFile()
                .split(" ");

        // replace words
        for (int i = 0; i < words.length; i++) {
            String replaceFor = map.get(words[i]);
            if(replaceFor != null){
                words[i] = replaceFor;
            }
        }

        //join words and return

        /*
        forRST: IDEA don't like line below:

        Static member 'java.lang.String.join(java.lang.CharSequence, java.lang.CharSequence...)' accessed via instance reference less... (Ctrl+F1)
        Shows references to static methods and fields via class instance rather than a class itself.

         Method invocation 'join' may produce 'java.lang.NullPointerException' less... (Ctrl+F1)
        This inspection analyzes method control and data flow to report possible conditions that
        are always true or false, expressions whose value is statically proven to be constant,
        and situations that can lead to nullability contract violations.
        Variables, method parameters and return values marked as @Nullable or @NotNull are treated as nullable
        (or not-null, respectively) and used during the analysis to check nullability contracts, e.g. report NullPointerException
        (NPE) errors that might be produced.
        More complex contracts can be defined using @Contract annotation, for example:
        @Contract("_, null -> null") — method returns null if its second argument is null
        @Contract("_, null -> null; _, !null -> !null") — method returns null if its second argument is null and not-null otherwise
        @Contract("true -> fail") — a typical assertFalse method which throws an exception if true is passed to it
        The inspection can be configured to use custom @Nullable
        @NotNull annotations (by default the ones from annotations.jar will be used)

        Confusing argument 'words', unclear if a varargs or non-varargs call is desired less... (Ctrl+F1)
        Reports any calls to a variable arity method where the call has a single argument in the variable
        arity parameter position, which is either a null or an array of a subtype of the variable arity parameter.
        Such an argument may be confusing as it is unclear if a varargs or non-varargs call is desired. For example System.out.printf("%s", null).

         */
        return result.join(" ",words);
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
     * Counts quantity of searched word in inputFile
     * @param searchWord
     * @return Print number of times searchWord repeated in the file, 0 if wasn't found
     * @throws IOException
     */
    public int countWord (String searchWord) throws IOException {

        int count = 0;

        // receive distinct words from inputFile
        String[] words = readFile()
                .split(" ");

        //search for the word
        for (String word : words) {
            String pureWord = word.replaceAll("[^A-Za-z]+", "");
            if (pureWord == null)
                continue;
            if (pureWord.equalsIgnoreCase(searchWord))
                count++;
        }
        return count;
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
