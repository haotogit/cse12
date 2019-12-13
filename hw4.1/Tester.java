import org.junit.Test;
import java.util.*;
import java.io.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class Tester {
    @Test
    public void testMaxHeapProperty()
    {
        // test heap invariant and max-heapify works
       // add items to a heep
       // iterate over them and see if each are within heap invariant
        Heap12<Integer> heap = new Heap12<>(false);
        List<Integer> integers = Arrays.asList(5, 1, 2, -99, 65, 10, 1, 0);
        integers.forEach(heap::offer);
        ArrayList<Integer> heapContent = new ArrayList<>(heap);

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

    @Test
    public void testMinHeapProperty()
    {
        // test heap invariant and min-heapify works
       // add items to a heep
       // iterate over them and see if each are within heap invariant
        Heap12<Integer> heap = new Heap12<>();
        List<Integer> integers = Arrays.asList(5, 1, 2, -99, 11, 80, 88, 3, 4, 24, 14, 50, 65, 10, 1, 0);
        integers.forEach(heap::offer);
        ArrayList<Integer> heapContent = new ArrayList<>(heap);

        for(int i = 0; i < heapContent.size(); i++) {
            Integer currRoot = heapContent.get(i);
            int leftIdx = 2*i+1;
            int rightIdx = leftIdx+1;

            // if leftIdx in bounds of end of array
            if (leftIdx < heapContent.size()) {
                assertTrue(heapContent.get(leftIdx) > currRoot);
            }
            if (rightIdx < heapContent.size()) {
                assertTrue(heapContent.get(rightIdx) > currRoot);
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
        Object[] sortedCorrectly = integers.toArray();
        Arrays.sort(sortedCorrectly);
        integers.forEach(heap::offer);

        int counter = heap.size()-1;
        while (heap.size() > 0) {
            assertEquals(sortedCorrectly[counter--], heap.poll().intValue());
        }
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

    @Test
    public void testIterator()
    {
        Heap12<Integer> heap = new Heap12<>();
        List<Integer> integers = Arrays.asList(5, 32, 38, 29, 31, 6, 15, 22, 20,
            8, 21, 2, 9, 17, 11, 24, 14, 0);
        integers.forEach(heap::offer);

        Heap12.Heap12Iterator iter = heap.iterator();
        int i = 0;
        while (iter.hasNext()) {
            iter.next();
            i++;
        }
        assertFalse(iter.hasNext());
        assertEquals(i, integers.size());
    }

    // testing that iterator enumerates each item only once
    // still need to test in the case of a bubble up after remove
    @Test
    public void removeInIterator() {
        Heap12<Integer> heap = new Heap12<>();
        List<Integer> integers = Arrays.asList(5, 32, 38, 29, 31, 6, 15, 22, 20,
            8, 21, 2, 9, 17, 11, 24, 1, 0);
        ArrayList<Integer> removedList = new ArrayList<Integer>();
        integers.forEach(heap::offer);

        System.out.println("<<<<<<<<<                                >>>>>>>>>");
        int i = 0;
        int diff = 0;
        Heap12.Heap12Iterator iter = heap.iterator();
        while (iter.hasNext()) {
            Comparable curr = iter.next();
            if ((i < 9) && i % 2 == 0) {
                iter.remove();
                diff++;
            }

            if (!removedList.contains(curr)) {
                removedList.add((Integer)curr);
            }

            i++;
        }

        assertEquals(removedList.size(), heap.size()+diff);
    }

    //TODO
    // test bubbler
}
