import java.lang.Comparable;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class HeapSort implements Sort12
{
    public <T extends Comparable<? super T>> void sort(List<T> list)
    {
        Heap12<T> heep = new Heap12<>(false);
        Iterator tempIterator = list.iterator();
        //while (tempIterator.hasNext()) {
        //    heep.offer(tempIterator.next().toString());
        //}
        //heep.printTree();
        System.out.println("swapping==========");
        for(T e : list) {
            heep.offer(ee);
        }

        for (int i = heep.size()-1; i > 0; i--) {
            // pop the top el
            Comparable<T> temp = heep.poll();
            System.out.println("doingswaps>>>>>>>>>>>>>>>>>>>>>>>>>>"+temp);
            list.set(i, (T)temp);
        }

        //Iterator heeper = heep.iterator();
        //for (int i = 0; heeper.hasNext(); i++) {
        //    T curr = (T)heeper.next().toString();
        //    list.set(i, curr);
        //}
    }
}
