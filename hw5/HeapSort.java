import java.lang.Comparable;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class HeapSort implements Sort12
{
    public <T extends Comparable<? super T>> void sort(List<T> list)
    {
        Heap12 heep = new Heap12();
        ArrayList<T> tempList = new ArrayList<T>(list);
        Iterator tempIterator = tempList.iterator();
        while (tempIterator.hasNext()) {
            heep.offer(tempIterator.next());
        }

        Iterator heeper = tempList.iterator();
        for (int i = 0; heeper.hasNext(); i++) {
            list.set(i, heeper.next());
        }
    }
}
