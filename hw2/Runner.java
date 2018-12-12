import java.util.ListIterator;

public class Runner {
    static final int DIM = 5;
    static final int FIBMAX = 30;

    public static void main(String[] args) {
        //MyLinkedList<Integer> list = new MyLinkedList<Integer>();
        //for (int i = 0; i < 5; i++) {
        //    list.add(i);
        //}
        
        //list.set(0, "newZero");
        //list.set(2, "newTOOO");
        //list.remove(2);
        //System.out.println(list.get(0));
        //System.out.println(list.get(1));
        //System.out.println(list.get(2));
        
        //ListIterator<Integer> iter = list.QQQlistIterator();
        //iter.previous();
        //System.out.println("starting nextIndex:"+iter.nextIndex());

        //System.out.println('\n');
        //while (iter.hasNext()) {
        //    System.out.println("next:"+iter.next());
        //}
        //iter.next();
        //System.out.println("prevIndex"+iter.previousIndex());
        //System.out.println("nextIndex"+iter.nextIndex());
        //iter.next();
        //iter.previous();
        //iter.remove();
        //System.out.println('\n');
        //ListIterator<Integer> anotherIter = list.QQQlistIterator();
        //while (anotherIter.hasNext()) {
        //    System.out.println("iterrr:"+anotherIter.next());
        //}
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
        for (int j = 1; j < FIBMAX/2; j++) {
            sum = iter.next();
        }
        System.out.println("SUM:"+sum);
        System.out.println("previous:"+iter.previous());
        System.out.println("previousPrevious:"+iter.previous());
        //System.out.println("index?"+iter.nextIndex());
        //while (iter.hasNext()) {
        //    System.out.println("next::"+iter.next());
        //}
    }
}
