import java.util.*;
import java.io.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Tester
{
    //@Test
    public void testAdd()
    {
        BST12<Integer> tree = new BST12<Integer>();
        int currObj;
        List<Integer> nums = Arrays.asList(5, 2, -99, 65, 10, 1, 0);

        for (int i = 0; i < nums.size(); i++) {
            currObj = nums.get(i);
            tree.add(currObj);
            assertTrue(tree.size() == i+1); 
            // TODO how to to test verifyBST
            assertTrue(tree.verifyBST(null));
        }
    }

    //@Test
    public void testContains()
    {
        BST12<Integer> tree = new BST12<Integer>();
        int currObj;
        List<Integer> nums = Arrays.asList(5, 2, -99, 65, 10, 1, 0);

        // for dupe el separate it out later
        for (int i = 0; i < nums.size(); i++) {
            currObj = nums.get(i);
            tree.add(currObj);
        }

        // this is testing BST12.findTraverseDFS()
        assertTrue(tree.contains(2));
        assertTrue(tree.contains(65));
        assertFalse(tree.contains(-65));
    }

    //@Test
    public void testGetNode()
    {
        BST12<Integer> tree = new BST12<Integer>();
        int currObj;
        List<Integer> nums = Arrays.asList(5, 2, -99, 65, 15, 9, 3, 10, 1, 0);
        
        for (int i = 0; i < nums.size(); i++) {
            currObj = nums.get(i);
            tree.add(currObj);
        }
        int val = tree.getNode(null, 9).data;
        int val1 = tree.getNode(null, 15).data;

        assertEquals(9, val);
        assertEquals(15, val1);
    }

    //@Test
    public void testGetLeafNode()
    {
        BST12<Integer> tree = new BST12<Integer>();
        int currObj;
        List<Integer> nums = Arrays.asList(5, 2, -99, 65, 15, 9, 3, 10, 1, 0);
        
        for (int i = 0; i < nums.size(); i++) {
            currObj = nums.get(i);
            tree.add(currObj);
        }

        BST12.Node node = tree.getLeafNode(null);
        assertTrue(node.left == null);
        assertTrue(node.right == null);
    }

    @Test
    public void testRemove()
    {
        BST12<Integer> tree = new BST12<Integer>();
        int currObj;
        List<Integer> nums = Arrays.asList(5, 2, -99, 65, 15, 9, 3, 10, 1, 0);
        
        for (int i = 0; i < nums.size(); i++) {
            currObj = nums.get(i);
            tree.add(currObj);
        }

        assertEquals(tree.size(), nums.size());
        tree.traverseDFS(null);
        tree.remove(65);
        assertEquals(tree.size(), nums.size()-1);
        System.out.println(">>>>>>>>>>>>>>>>");
        tree.traverseDFS(null);
        tree.remove(10);
        System.out.println(">>>>>>>>>>>>>>>>>         <<<<<<<<<<<<<<<<<<<<<<<<<");
        tree.traverseDFS(null);
        assertEquals(tree.size(), nums.size()-2);
    }
}
// vim:ts=4:sw=4:sw=78
