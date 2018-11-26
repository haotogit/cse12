/** 
 * Output content of given file in reverse with LinkedList
 * @author Sam Park
 * @version 1.0
 * */
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Iterator;

class ReverseList {
    private static FileReader reader = null;
    private static LinkedList<String> lineList = new LinkedList<String>();

    public static void main(String[] args) throws IOException {
        ReverseList reverser = new ReverseList();
        reverser.readFile(args);
        reverser.getLines();
        reverser.printReversed();
    }

    private void readFile(String[] args) {
        if (args.length == 0) System.err.println("No file provided");
        try {
            reader = new FileReader(args[0]);
        } catch (FileNotFoundException fe) {
            System.err.println("File not found");
        }
    }

    private void getLines() throws IOException {
        int x;
        char currChar;
        String currLine = "";

        while ((x = reader.read()) != -1) {
            currChar = (char) x;
            if (currChar != '\n') currLine += currChar;
            else {
                lineList.add(currLine);
                currLine = "";
            }
        }
    }

    private void printReversed() {
        Iterator it = lineList.iterator();

        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
