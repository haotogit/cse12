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
        if (this.size > 0 && this.contains(el)) {
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
            if (compareResult > 0) {
                currNode.right = this.addToTree(currNode, currNode.right, currVal);
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

    private boolean findTraverseDFS(Node thisRoot, E target)
    {
        Node temp = thisRoot == null ? this.currRoot : thisRoot;
        int compareResult = temp.data.compareTo(target);
        if (compareResult == 0) {
            //System.out.println("BINGOOOOOOOOOO"+temp.data+"======"+temp.data+" target=="+target+"... returning"+compareResult);
            return compareResult == 0;
        }

        if (temp.left != null && compareResult > 0) return findTraverseDFS(temp.left, target);
        else if (temp.right != null && compareResult < 0) return findTraverseDFS(temp.right, target);
        return false;
    }

    public boolean traverseDFS(Node thisRoot)
    {
        Node temp = thisRoot == null ? this.currRoot : thisRoot;

        if (temp.left != null) traverseDFS(temp.left);
        nodePrinter(temp);
        if (temp.right != null) traverseDFS(temp.right);
        return false;
    }

    public Node getNode(Node thisRoot, E target)
    {
        Node temp = thisRoot == null ? this.currRoot : thisRoot;
        int compareResult = temp.data.compareTo(target);
        //System.out.println("comparing: "+(temp.data)+" vs. "+target+" = "+compareResult);
        //nodePrinter(temp);
        if (compareResult == 0) {
            //System.out.println("BINGOOOOOOOOOO"+temp.data+"======"+temp.data+" target=="+target+"... returning"+compareResult);
            return temp;
        }

        if (temp.left != null && compareResult > 0) {
            return getNode(temp.left, target);
        }
        else if (temp.right != null && compareResult < 0) {
            return getNode(temp.right, target);
        }
        return null;
    }

    public void nodePrinter(Node node)
    {
        System.out.println("=="+node.data+"; papa="+(node.papa != null ? node.papa.data : "nopapa")+"; left="+(node.left != null ? node.left.data : "noleft")+"; right="+(node.right != null ? node.right.data : "noright"));
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
            nodePrinter(tempNode);
            list.add(tempNode.data);
            if (tempNode.left != null) {
                whereTo.offer(tempNode.left);
            }
            if (tempNode.right != null) whereTo.offer(tempNode.right);
        }

        return list;
    }

    private Node removeNode(Node root, E el) {
        Node temp;
        Node currNode = root;
        if (currNode == null) {
            return currNode;
        }

        if (currNode.data.compareTo(el) > 0) {            
            currNode.left = this.removeNode(currNode.left, el);
        } else if (currNode.data.compareTo(el) < 0) {
            currNode.right = this.removeNode(currNode.right, el);
        } else {
            // if leafNode delete node
            if (currNode.left == null && currNode.right == null) {
                currNode = null;
            } else if (currNode.right == null) {
                if (currNode == this.currRoot) this.currRoot = currNode.left;
                currNode = currNode.left;
            } else if (currNode.left == null) {
                if (currNode == this.currRoot) this.currRoot = currNode.right;
                currNode = currNode.right;
            } else {
                // get minimum node from right
                temp  = this.getLeafNode(currNode.right);
                currNode.data = temp.data;
                // find temp item that was moved and delete it
                currNode.right = this.removeNode(currNode.right, temp.data);
            }
        }

        return currNode;
    }

    private Node getLeafNode(Node node) {
        if (node.left == null)
            return node;
        else return this.getLeafNode(node.left);
    }

    public boolean remove(E o)
    {
        if (o != null && this.contains(o)) {
            this.removeNode(this.currRoot, o);
            this.size--;
            return true;
        } else return false;
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
        this.currRoot = null;
        this.size = 0;
    }

    public boolean contains(E o)
    {
        return this.findTraverseDFS(null, o);
    }

    public E first()
    {
        return this.currRoot.data;
    }

    public boolean isEmpty()
    {
        return this.size == 0;
    }

    public E last()
    {
        return (E)null;
    }
    
    @Override
    public String toString()
    {
        // dfs iterative in order
        String returnStr = "[";
        Stack<Node> stack = new Stack<Node>();
        Node temp = this.currRoot;
        if (temp != null) {
            stack.push(temp);
            while(!stack.isEmpty()) {
                while (temp.left != null) {
                    temp = temp.left;
                    if (!returnStr.contains(temp.data.toString())) stack.push(temp);
                }

                temp = stack.pop();
                returnStr += returnStr.length() > 1 ? ", "+temp.data : temp.data;
                if (temp.right != null) {
                    temp = temp.right;
                    if (!returnStr.contains(temp.data.toString())) stack.push(temp);
                }
            }
        }

        return returnStr+"]";
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
