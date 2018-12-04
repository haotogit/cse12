public class Runner {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<Integer>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        
        //list.set(0, "newZero");
        //list.set(2, "newTOOO");
        //list.remove(2);
        list.clear();
        //System.out.println(list.get(0));
        //System.out.println(list.get(1));
        System.out.println(list.size());
        //System.out.println(list.get(2));
    }
}
