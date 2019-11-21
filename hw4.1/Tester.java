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
    public void testHeapProperty()
    {
        // test heap invariant and max-heapify works
       // add items to a heep
       // iterate over them and see if each are within heap invariant
        Heap12<Integer> heap = new Heap12<>(false);
        List<Integer> integers = Arrays.asList(5, 1, 2, -99, 65, 10, 1, 0);
        integers.forEach(heap::offer);
        ArrayList<Integer> heapContent = new ArrayList<>(heap);
        //Iterator tempIterator = list.iterator();

        for(int i = 0; i < heapContent.size(); i++) {
            Integer currRoot = heapContent.get(i);
            System.out.println(currRoot);
            int leftIdx = 2*i+1;
            int rightIdx = leftIdx+1;

            // if leftIdx in bounds of end of array
            if (leftIdx < heapContent.size()) {
                assertTrue(heapContent.get(leftIdx) < currRoot);
            }
            if (rightIdx < heapContent.size()) {
                assertTrue(heapContent.get(rightIdx) < currRoot);
            }
        }
    }

    // test that polling will return items in the expected order after
    // offering them into heap. testing polling plus trickle down works
    @Test
    public void testPollAndHeapProperty()
    {
        Heap12<Integer> heap = new Heap12<>(false);
        List<Integer> integers = Arrays.asList(20, 5, 84, 2, 1, -1, 55, 3, 24, 0, 9);
        integers.forEach(heap::offer);

        assertEquals(84, heap.poll().intValue());
        assertEquals(55, heap.poll().intValue());
        assertEquals(24, heap.poll().intValue());
        assertEquals(20, heap.poll().intValue());
        assertEquals(9, heap.poll().intValue());
        assertEquals(5, heap.poll().intValue());
        assertEquals(3, heap.poll().intValue());
        assertEquals(2, heap.poll().intValue());
        assertEquals(1, heap.poll().intValue());
        assertEquals(0, heap.poll().intValue());
        assertEquals(-1, heap.poll().intValue());
        assertTrue(heap.size() == 0);
    }

    // test heapSort
    @Test
    public void testSort() {
        List<Integer> integers = Arrays.asList(20, 5, 84, 2, 1, -1, 55, 3, 24, 0, 9);
        ArrayList<Integer> heapSorted = new ArrayList<>(integers);
        Object[] sortedCorrectly = integers.toArray();
        Arrays.sort(sortedCorrectly);

        new HeapSort().sort(heapSorted);
        assertArrayEquals(sortedCorrectly, heapSorted.toArray());
    }
}
