import junit.framework.TestCase;
import java.io.*;

public class BoundedDequeTester extends TestCase {
    private Deque12<Integer> intDeque120;
    private int intDeque120Capacity = 30;

    public void setUp()
    {
        intDeque120 = new Deque12<Integer>(intDeque120Capacity);
    }

    /** 
     * Should return the maximum number of items 
     * deque can hold and BoundedDeque should remain unchanged
     */
    public void testCapacity()
    {
        assertEquals(intDeque120Capacity, intDeque120.capacity());
    }

    /** 
     * Should return number of elements in BoundedDeque 
     * and it should remain unchanged 
     */
    public void testSize()
    {
        intDeque120.addFront(0);
        assertEquals(1, intDeque120.size());
    }

    /**
     * Adds element to the front of BoundedDeque if size 
     * less than capacity and increases size +1, and returns 
     * true.
     * new element is new front element of list
     */
    public void testAddFront()
    {
        int newEl = 99;
        assertTrue(intDeque120.addFront(newEl));
        assertEquals(newEl, (int)intDeque120.peekFront());
    }

    /**
     * If element null and size is less than capacity throw
     * NullPointerException
     */
    public void testBadNullAddFront()
    {
        try
        {
            intDeque120.addFront(null);
            fail("Can't add null element");
        } catch(NullPointerException e)
        {
            assertTrue(intDeque120.size() < intDeque120Capacity);
        }
    }

    /**
     * shouldn't be able to add if size greater or = to capacity
     * returns false since it failed
     */
    public void testFullListAddFront()
    {
        while(intDeque120.addFront(0))
        {

        }
        assertFalse(intDeque120.addFront(9999));
        assertTrue(intDeque120.size() == intDeque120Capacity);
    }

    /**
     * Adds element to back of BoundedDeque if size is less than
     * capacity, it increases size +1 and returns true.
     * new element is the new rear element of list
     */
    public void testAddBack()
    {}

    /**
     * If element null or size is greater or equal than capacity throw
     * NullPointerException
     * Cannot add if size greater or = to capacity and return
     * false.
     */
    public void testBadAddBack()
    {}

    /**
     * Remove front element from BoundedDeque return it or return null
     * if size == 0. If element is removed and size greater than 0, size--.
     */
    public void testRemoveFront()
    {}

    /**
     * Remove back element from BoundedDeque return it or return null if
     * size == 0. If element is removed, and size > 0, size--.
     */
    public void testRemoveBack()
    {}

    /**
     * Returns element at the front of BoundedDeque or null if size == 0.
     * BoundedDeque remains unchanged.
     */
    public void testPeekFront()
    {}

    /**
     * Returns element at the rear of BoundedDeque or null if size == 0.
     * BoundedDeque remains unchanged.
     */
    public void testPeekBack()
    {}
}
