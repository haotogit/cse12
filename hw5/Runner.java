public class Runner {
    public static void main(String[] args) {
        Heap12<Integer> heapArr = new Heap12<Integer>();
        int i = 0;
        while (i < 11)
        {
            heapArr.offer(i++);
        }
        System.out.printf("heapSize %d\n", heapArr.size());
    }
}
