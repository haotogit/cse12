import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Tester {
    @Test
    public void testHeapProperty() {
       // add items to a heep
       // iterate over them and see if each are within heap invariant
        Heap12<Integer> heap = new Heap12<>(false);
        List<Integer> integers = Arrays.asList(5, 1, 2, -99, 65, 10, 1, 0);
        integers.forEach(heap::offer);
        ArrayList<Integer> heapContent = new ArrayList<>(heap);
        //Iterator tempIterator = list.iterator();

        for(int i = 0; i < heapContent.size(); i++) {
            Integer root = heapContent.get(i);
            int leftIdx = 2*i+1;
            int rightIdx = leftIdx+1;

            // if leftIdx in bounds of end of array
            if (leftIdx < heapContent.size()) {
                assertTrue(heapContent.get(leftIdx) < root);
            }
            if (rightIdx < heapContent.size()) {
                assertTrue(heapContent.get(rightIdx) < root);
            }
        }
    }
}
