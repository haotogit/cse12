import java.util.ListIterator;

public class Runner {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<Integer>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        
        //list.set(0, "newZero");
        //list.set(2, "newTOOO");
        //list.remove(2);
        //System.out.println(list.get(0));
        //System.out.println(list.get(1));
        //System.out.println(list.get(2));
        
        ListIterator<Integer> iter = list.QQQlistIterator();
        //iter.previous();
        //System.out.println("starting nextIndex:"+iter.nextIndex());

        //System.out.println('\n');
        //while (iter.hasNext()) {
        //    System.out.println("next:"+iter.next());
        //}
        iter.next();
        //System.out.println("prevIndex"+iter.previousIndex());
        //System.out.println("nextIndex"+iter.nextIndex());
        //iter.next();
        //iter.previous();
        iter.remove();
        System.out.println('\n');
        ListIterator<Integer> anotherIter = list.QQQlistIterator();
        while (anotherIter.hasNext()) {
            System.out.println("iterrr:"+anotherIter.next());
        }
    }
}
