import java.util.Iterator;

public class Runner {
    public static void main(String[] args) {
        Heap12<Integer> heapArr = new Heap12<Integer>();
        int i = 0;
        int[] nums = new int[]{ 15, 5, 2, 10, 3, 13, 1, 11 };
        while (i < nums.length)
        {
            heapArr.offer(nums[i++]);
        }

        heapArr.printTree();
        //Iterator heap12iterator = heapArr.iterator();

        //while (heap12iterator.hasNext()) {
        //    System.out.println(">>>>>>"+heap12iterator.next());
        //}
    }
}
