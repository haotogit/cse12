// TODO: Properly COMMENT with javadoc
import java.lang.Comparable;
import java.util.List;
import java.util.ArrayList;
public class Merge12 implements Sort12
{
	public  <T extends Comparable<? super T>> void  sort(List<T> list)
    {
        int listSize = list.size();
        ArrayList<T> tempList = new ArrayList<T>(listSize);
        ArrayList<T> copyList = new ArrayList<T>(listSize);

        for (T item : list)
            copyList.add(item);

        internalMergeSort(copyList, tempList, 0, listSize-1);

        for (int i = 0; i < copyList.size(); i++)
            list.set(i, copyList.get(i));
    }
	private  <T extends Comparable<? super T>> void 
		internalMergeSort(ArrayList<T> inputArray, ArrayList<T> tempArray,
					int first, int last)
	{
      int midIndex = (first + last) / 2;
      //System.out.println("doing"+first+"-"+midIndex+"-"+last);

      if (last > first) {
        internalMergeSort(inputArray, tempArray, first, midIndex);
        internalMergeSort(inputArray, tempArray, midIndex+1, last);
        merge(inputArray, tempArray, first, midIndex, last);
      }
	} // internalMergeSort

	private  <T extends Comparable<? super T>> void 
		merge(ArrayList<T> inputArray, ArrayList<T> tempArray,
					int first, int mid, int last)
	{
      //System.out.println("merging==="+first+"-"+mid+"-"+last);
      int leftIndex = first;
      int rightIndex = mid+1;
      int x = 0;

      while((leftIndex <= mid) && (rightIndex <= last))
      {
          T lefty = inputArray.get(leftIndex);
          T righty = inputArray.get(rightIndex);

          if (lefty.compareTo(righty) <= 0) {
              //System.out.printf("lefty wins %s vs %s, at index %d\n", lefty, righty, leftIndex);
            tempArray.add(lefty);
            leftIndex++;
          } else {
              //System.out.printf("righty wins %s vs %s, at index %d\n", righty, lefty, rightIndex);
            tempArray.add(righty);
            rightIndex++;
          }
      }

      // get leftovers
      if (rightIndex <= last) {
        while(rightIndex <= last) {
            tempArray.add(inputArray.get(rightIndex++));
        }
      } else {
        while(leftIndex <= mid) {
            tempArray.add(inputArray.get(leftIndex++));
        }
      }

      while (first <= last) 
          inputArray.set(first++, tempArray.get(x++));

      tempArray.clear();
	} // Merge
}
