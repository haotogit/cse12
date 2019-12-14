import java.util.*;
import java.io.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Tester
{
    @Test
    public void testAdd()
    {
        BST12<Integer> tree = new BST12<Integer>();
        int currObj;
        List<Integer> nums = Arrays.asList(5, 1, 2, -99, 65, 10, 1, 0);

        for (int i = 1; i < nums.size() - 2; i++) {
            currObj = nums.get(i);
            tree.add(currObj);
            assertTrue(tree.size() == i); 
            // TODO how to to test verifyBST
            assertTrue(tree.verifyBST(null));
        }
    }

    @Test
    public void testFindDFS()
    {
        BST12<Integer> tree = new BST12<Integer>();
        int currObj;
        List<Integer> nums = Arrays.asList(5, 1, 2, -99, 65, 10, 1, 0);

        // for dupe el separate it out later
        for (int i = 1; i < nums.size() - 2; i++) {
            currObj = nums.get(i);
            tree.add(currObj);
        }
        assertTrue(tree.findTraverseDFS(null, 2));
        assertTrue(tree.findTraverseDFS(null, 65));
    }
}
// vim:ts=4:sw=4:sw=78
