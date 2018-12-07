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
        System.out.println("starting nextIndex:"+iter.nextIndex());
        iter.next();
        System.out.println("after first next nextIndex:"+iter.nextIndex());
        iter.previous();
        System.out.println("after previous nextIndex:"+iter.nextIndex());
        iter.add(15);
        //System.out.println("size::"+list.size());

        System.out.println('\n');
        while (iter.hasNext()) {
            System.out.println("loopNexInt:"+iter.nextIndex());
            System.out.println("loopNext:"+iter.next());
        }
        System.out.println("last:"+iter.nextIndex());
    }
}
