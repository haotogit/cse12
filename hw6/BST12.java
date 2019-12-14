import java.util.*;
import java.util.Iterator;

/**
 *  Binary Search tree invariants
 *  - Structural: it's a binary tree
 *  - Ordering property: for any node x in the tree, all data elements
 *    on left of x are less than x.data, and all data elements to right
 *    of x are greater than x.data
 * - A full binary tree of height h has 2^h - 1 nodes
 * - The height of a full binary tree with n nodes is log2 (n+1),
 *   this is also the longest path in a full binary tree
 * - The height of a binary tree with n nodes is at least log2 (n+1)
 *   and at most n (this "worst case" occurs when no node has more than 1 child)
 */

public class BST12<E extends Comparable <? super E>> implements BinSearchTree12<E>
{
    protected class Node
    {
        Node papa;
        Node left;
        Node right;
        E data;

        public Node(E val)
        {
            this.data = val;
        }

        public Node(E val, Node left, Node right)
        {
            this.data = val;
            this.left = left;
            this.right = right;
        }
    }

    int size;
    Node currRoot;

    public boolean add(E el)
    {
        if (el == null) throw new NullPointerException("Can't add null element");
        if (this.contains(el)) {
            return false;
        }

        this.currRoot = this.addToTree(null, this.currRoot, el);
        this.size++;
        return this.verifyBST(null);
    }

    private Node addToTree(Node parent, Node currNode, E currVal)
    {
        // if reached an empty, null node
        if (currNode == null) {
            currNode = new Node(currVal);
            if (parent != null) {
                currNode.papa = parent;
            }
        } else {
            // traversing,
            // and comparing currNode to value to add
            int compareResult = currVal.compareTo(currNode.data);

            // if greater right
            if (compareResult > 0) {
                currNode.right = this.addToTree(currNode, currNode.right, currVal);
            // else if less left 
            } else if (compareResult < 0) {
                currNode.left = this.addToTree(currNode, currNode.left, currVal);
            }
        }

        return currNode;
    }

    public boolean verifyBST(Node thisRoot)
    {
        Node temp = thisRoot == null ? this.currRoot : thisRoot;

        // leftChild > currData || rightChild < currData == bad bst
        if ((temp.left != null && temp.left.data.compareTo(temp.data) > 0) ||
            (temp.right != null && temp.right.data.compareTo(temp.data) < 0)) {
            return false;
        }

        if (temp.left != null) verifyBST(temp.left);
        if (temp.right != null) verifyBST(temp.right);

        return true;
    }

    public void traverseDFS(Node thisRoot)
    {
        // In order traversal
        Node temp = thisRoot == null ? this.currRoot : thisRoot;
        if (temp.left != null) traverseDFS(temp.left);
        System.out.println(">>>> "+temp.data+" papa>>"+(temp.papa == null ? null : temp.papa.data)+" left>>"+(temp.left == null ? null : temp.left.data)+"right>>"+(temp.right == null ? null : temp.right.data));
        if (temp.right != null) traverseDFS(temp.right);
    }

    public ArrayList<E> traverseBFS()
    {
        Queue<Node> whereTo = new LinkedList<Node>();
        Node tempNode = this.currRoot;
        ArrayList<E> list = new ArrayList<E>();

        // visit currnode
        whereTo.add(tempNode);
        while(whereTo.size() > 0) {
            tempNode = whereTo.poll();
            System.out.println(">>>> "+tempNode.data+" papa>>"+(tempNode.papa == null ? null : tempNode.papa.data)+" left>>"+(tempNode.left == null ? null : tempNode.left.data)+"right>>"+(tempNode.right == null ? null : tempNode.right.data));
            list.add(tempNode.data);
            if (tempNode.left != null) {
                whereTo.offer(tempNode.left);
            }
            if (tempNode.right != null) whereTo.offer(tempNode.right);
        }

        return list;
    }

    public int numChildren(E target)
    {
        return 0;
    }

    public int height()
    {
        return 0;
    }

    public int size()
    {
        return this.size;
    }

    public boolean addAll(Collection<? extends E> c)
    {
        return true;
    }

    public void clear()
    {
        return;
    }

    public boolean contains(E o)
    {
        return false;
    }

    public E first()
    {
        return (E)null;
    }

    public boolean isEmpty()
    {
        return true;
    }

    public E last()
    {
        return (E)null;
    }

    public boolean remove(E o)
    {
        return true;
    }

    public Iterator<E> iterator()
    {
        return new BST12Iterator();
    }

    protected class BST12Iterator implements Iterator<E>
    {
        @Override
        public boolean hasNext()
        {
            return true;
        }

        public E next() throws NoSuchElementException
        {
            return (E) null;
        }
    }
}
