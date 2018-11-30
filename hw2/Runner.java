public class Runner {
    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<String>();
        list.add("zero");
        list.set(0, "newZero");
        System.out.println(list.get(0));
    }

}
