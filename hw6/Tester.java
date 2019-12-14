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

        for (int i = 0; i < nums.size() - 1; i++) {
            currObj = nums.get(i);
            tree.add(currObj);
            assertTrue(tree.size() == i+1); 
            assertTrue(tree.verifyBST(null));
        }
    }
}
// vim:ts=4:sw=4:sw=78
