// TODO: Properly COMMENT with javadoc
import java.lang.Comparable;
import java.util.List;
import java.util.ArrayList;
public class Merge12 implements Sort12
{
    public  <T extends Comparable<? super T>> void  sort(List<T> list)
    {
        int listSize = list.size();
        int midPt = 0 + listSize / 2;
        
        ArrayList<T> mergeList = new ArrayList<T>(listSize);
        ArrayList<T> copyList = new ArrayList<T>(listSize);
        for (T tempItem : list)
        {
            copyList.add(tempItem);
        }

        // do left internalmergesort
        internalMergeSort(copyList, mergeList, 0, midPt-1);
        // do right internalmergesort
        internalMergeSort(copyList, mergeList, midPt, listSize-1);

        // do merge

        // copy from mergeList into list 
    }

    private  <T extends Comparable<? super T>> void 
    internalMergeSort(ArrayList<T> inputArray, ArrayList<T> tempArray,
            int first, int last)
    {
        int midPtIdx = (first + last + 1) / 2;

        if (last - first > 1)
        {
            internalMergeSort(inputArray, tempArray, first, midPtIdx-1);
            if (last > midPtIdx)
                internalMergeSort(inputArray, tempArray, midPtIdx, last);
        }
        else 
        {
            System.out.format("First: %d, Last: %d, midPt: %d\n", first, last, midPtIdx);
            //T firstItem = inputArray.get(first);
            //T lastItem = inputArray.get(last);
            //if (lastItem.compareTo(firstItem) <= 0)
            //{
            //    tempArray.add(first, lastItem);
            //}
            //else if (inputArray.get(inputArray.size()))
            //{
            //}
        }
    } // internalMergeSort

    private  <T extends Comparable<? super T>> void 
    merge(ArrayList<T> inputArray, ArrayList<T> tempArray,
            int first, int mid, int last)
    {
    } // Merge
}
// vim:ts=4:sw=4:sw=78
