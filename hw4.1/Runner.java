import java.util.*;
import java.io.*;
class PerfInfo
{
	long time;
	long maxItems;	
}
public class Runner {
    public static void main(String[] args) {
        long start, end;
        PerfInfo rdata = new PerfInfo();

        // now read in words, can store in any List structure. Pick one
        int nwords = 0;
        int numwords = 10;
        //LinkedList<String>  unsorted = new LinkedList<String>();
        ArrayList<String>  unsorted = new ArrayList<String>(numwords);

        // get the start time
        long totalTime = 0;

        try {
            // open up the document
            FileInputStream in = new FileInputStream("small-wordlist.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;

            // make sure we are ready to go
            unsorted.clear();

            String line;
            // read in the document
            while((line = br.readLine())!= null && nwords <= numwords)
            {
                String[] arr = line.split( " " );
                for ( String word : arr ) {
                    word = SortTimer.trimPunctuation(word);
                    unsorted.add(nwords++, word);
                }
            }
        } catch (IOException e) {
            System.out.println("problem reading from wordlist:" + e.getMessage());
            //return rdata;
        }

        // get the start time 
        start = System.currentTimeMillis();
        Sort12 sorter = new HeapSort();
        Sort12 otherSorter = new Merge12();
        ArrayList<String>  otherUnsorted = new ArrayList<String>(unsorted);
        sorter.sort(unsorted);
        otherSorter.sort(otherUnsorted);
        // get the end time 
        end = System.currentTimeMillis();
        // count the number of milliseconds elapsed
        totalTime += ( end-start );
        // property based testing
            Iterator<String> iter = unsorted.iterator();
            while (iter.hasNext())	
                System.out.println(iter.next());

        System.out.println(">>>>>>>"+unsorted.equals(otherUnsorted));
        Iterator<String> iter1 = otherUnsorted.iterator();
            while (iter1.hasNext())	
                System.out.println(iter1.next());
        rdata.maxItems=nwords;
        rdata.time=totalTime;

        System.out.printf("%7d words in %7d milliseconds\n", rdata.maxItems, totalTime);
    }
}
