public class Runner {
    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<String>();
        list.add(0,"zero");
        //list.fuck();
        list.add(0, "one");
        list.add(1, "dos");
        //list.set(0, "newZero");
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
    }
}
