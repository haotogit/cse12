import java.lang.Comparable;
import java.util.List;
import java.util.ArrayList;

public class MyQuick12 implements Sort12 
{
    public <T extends Comparable<? super T>> void sort(List<T> list)
    {
        // copy list into arrayList
        ArrayList<T> inputArr = new ArrayList<T>(list.size());
        for (T item : list)
        {
            //System.out.format("adding %s\n", item);
            inputArr.add(item);
        }

        quickSort(inputArr, 0, list.size()-1);

        // copy arrayList into list
    }

    private <T extends Comparable<? super T>> void
    quickSort(ArrayList<T> inputArr, int first, int last)
    {
    }
}
