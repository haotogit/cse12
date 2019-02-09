// TODO: Properly COMMENT with javadoc
import java.lang.Comparable;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Array;

public class Merge12 implements Sort12
{
    public  <T extends Comparable<? super T>> void  sort(List<T> list)
    {
        int listSize = list.size();
        int mid = (0+listSize+1)/2;
        
        ArrayList<T> mergeList = new ArrayList<T>(listSize);
        ArrayList<T> copyList = new ArrayList<T>(listSize);
        for (T item : list)
        {
            copyList.add(item);
        }

        internalMergeSort(copyList, mergeList, 0, listSize-1);
        for (int i = 0; i < copyList.size(); i++) {
            list.set(i, copyList.get(i));
        }
    }

    private  <T extends Comparable<? super T>> void 
    internalMergeSort(ArrayList<T> inputArray, ArrayList<T> tempArray,
            int first, int last)
    {
        int mid = (first + last + 1) / 2;
        if (last > first)
        {
            internalMergeSort(inputArray, tempArray, first, mid-1);
            internalMergeSort(inputArray, tempArray, mid, last);
            merge(inputArray, tempArray, first, mid, last);
        }
    } // internalMergeSort

    private  <T extends Comparable<? super T>> void 
    merge(ArrayList<T> inputArray, ArrayList<T> tempArray,
            int first, int mid, int last)
    {
        int left = first;
        int right = mid;

        while (left < mid && right <= last) {
            T lefty = inputArray.get(left);
            T righty = inputArray.get(right);

            if (lefty.compareTo(righty) <= 0) {
                tempArray.add(lefty);
                left++;
            } else {
                tempArray.add(righty);
                right++;
            }
        }

        // leftovers from left or right
        if (left < mid) {
            while (left < mid) {
                tempArray.add(inputArray.get(left++));
            }
        } else if (right <= last) {
            while (right <= last) {
                tempArray.add(inputArray.get(right++));
            }
        }

        int x = 0;
        while (first <= last) {
            inputArray.set(first++, tempArray.get(x++));
        }

        tempArray.clear();
    } // Merge
}
// vim:ts=4:sw=4:sw=78
