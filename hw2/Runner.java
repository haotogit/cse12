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

        while (iter.hasNext()) {
            System.out.println("nextPrevIndex:"+iter.nextIndex());
            System.out.println("next:"+iter.next());
        }
    }
}
