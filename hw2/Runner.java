import java.util.ListIterator;

public class Runner {
    static final int DIM = 5;
    static final int FIBMAX = 30;

    public static void main(String[] args) {
        MyLinkedList<Integer> fib  = new MyLinkedList<Integer>();
        ListIterator<Integer> iter;
        // List: 0 1 1 2 3 5 8 13 ... 
        // Build the list with integers 1 .. FIBMAX
        int t, p = 0, q = 1;
        fib.add(0,p);
        fib.add(1,q);
        for (int k = 2; k < FIBMAX; k++)
        {
            t = p+q;
            fib.add(k,t);
            p = q;
            q = t; 
        }
        // Now iterate through the list to near the middle, read the
        // previous two entries and verify the sum.
        iter = fib.listIterator();
        int sum = 0;
        
        //iter.previous();
        //iter.remove();
        System.out.println("whaa"+iter.next());
        System.out.println("whaasii"+iter.previous());
        iter.remove();
        System.out.println("whaasiinext"+iter.next());
        System.out.println("whaasiinext"+iter.previous());
        System.out.println("whaasiinext"+fib.size());
        //while (iter.hasNext())
        //{
        //    System.out.println("whaa"+iter.next());
        //}

        //while (iter.hasPrevious())
        //{
        //    System.out.println("whaasii"+iter.previous());
        //}

        //for (int j = 1; j < FIBMAX/2; j++) {
        //    sum = iter.next();
        //}
    }
}
