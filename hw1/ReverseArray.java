/** 
 * Output content of given file in reverse using an Array
 * of Strings, not ArrayList or similar but normal Array.
 * @author Sam Park
 * @version 1.0
 * */
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

class ReverseArray {
    private static String[] lineArr = new String[100];
    private static int lineCount = 0;

    public static void main(String[] args) throws IOException {
        if (args.length == 0) System.err.println("No file provided");
        FileReader reader = null;
        try {
            reader = new FileReader(args[0]);
        } catch (FileNotFoundException fe) {
            System.out.println("File not found");
        }

        int x;
        char currChar;
        String currLine = "";
        String[] tempArr;

        while ((x = reader.read()) != -1) {
            currChar = (char) x;
            if (currChar != '\n') currLine += currChar;
            else {
                if (lineCount >= lineArr.length) {
                    int oldLength = lineArr.length;
                    int newLength = oldLength+100;
                    tempArr = new String[newLength];
                    System.arraycopy(lineArr, 0, tempArr, 0, oldLength);
                    lineArr = new String[newLength];
                    System.arraycopy(tempArr, 0, lineArr, 0, oldLength);
                }
                lineArr[lineCount] = currLine;
                currLine = "";
                lineCount++;
            }
        }

        int i = lineArr.length - 1;
        while (i >= 0) {
            if (lineArr[i] != null) System.out.println(lineArr[i]);
            i--;
        }
    }
}
