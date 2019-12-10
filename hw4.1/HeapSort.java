import java.lang.Comparable;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class HeapSort implements Sort12
{
    public <T extends Comparable<? super T>> void sort(List<T> list)
    {
        Heap12<T> heep = new Heap12<>(false);
        for(T e : list) {
            heep.offer(e);
        }

        for (int i = heep.size()-1; i >= 0; i--) {
            // pop the top el
            T temp = heep.poll();
            list.set(i, temp);
        }
    }
}
