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

    private boolean findTraverseDFS(Node thisRoot, E target)
    {
        // In order traversal
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

        System.out.println("BINGOOOOOOOOOO==="+temp.data+"papa=="+(temp.papa != null ? temp.papa.data : "null")+"left======"+(temp.left == null ? "null" : temp.left.data)+"right====="+(temp.right == null ? "null" : temp.right.data));
        if (temp.left != null) traverseDFS(temp.left);
        if (temp.right != null) traverseDFS(temp.right);
        return false;
    }

    public Node getNode(Node thisRoot, E target)
    {
        Node temp = thisRoot == null ? this.currRoot : thisRoot;
        int compareResult = temp.data.compareTo(target);
        System.out.println("comparing: "+(temp.data)+" vs. "+target+" = "+compareResult);
        //nodePrinter(temp);
        if (compareResult == 0) {
            //System.out.println("BINGOOOOOOOOOO"+temp.data+"======"+temp.data+" target=="+target+"... returning"+compareResult);
            return temp;
        }

        if (temp.left != null && compareResult > 0) {
            System.out.println("goingleft>>>>>>"+temp.left.data);
            return getNode(temp.left, target);
        }
        else if (temp.right != null && compareResult < 0) {
            System.out.println("goingrightdata>>>>>"+temp.data+"the====="+temp.right.data);
            System.out.println("goingright>>>>>"+temp.right.data);
            return getNode(temp.right, target);
        }
        return null;
    }

    public void nodePrinter(Node node)
    {
        System.out.println("=="+node.data+"; papa="+(node.papa != null ? node.papa.data : "nopapa")+"; left="+(node.left != null ? node.left.data : "noleft")+"; right="+(node.right != null ? node.right.data : "noright"));
    }

    public Node getLeafNode(Node thisRoot)
    {
        Node temp = thisRoot == null ? this.currRoot : thisRoot;
        if (temp.left == null && temp.right == null) {
            return temp;
        }

        if (temp.left != null) return getLeafNode(temp.left);
        else if (temp.right != null) return getLeafNode(temp.right);
        return null;
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

    public boolean remove(E o)
    {
        Node currNode = this.getNode(this.currRoot, o);
        Node leaf, papaNode, left, right;
        if (o == null || currNode == null || !this.contains(o)) return false;

        // check if internal or leaf
        System.out.println(">>>>>>>>>>"+currNode.data);
        // if leaf node
        if (currNode.left == null && currNode.right == null) {
            if (currNode.papa.left != null &&
                currNode.papa.left.data.compareTo(o) == 0) {
                currNode.papa.left = null;
                this.size--;
                return true;
            } else if (currNode.papa.right != null && 
                currNode.papa.right.data.compareTo(o) == 0) {
                currNode.papa.right = null;
                this.size--;
                return true;
            }
        } else if (currNode.left != null && currNode.data.compareTo(o) <= 0) {
            // get leafnode
            System.out.println("currnode======"+currNode.data);
            leaf = this.getLeafNode(currNode.left);
            System.out.println("leafNode======"+leaf.data+"papa==="+leaf.papa.data);
            papaNode = this.getNode(null, leaf.papa.data);
            currNode.data = leaf.data;

            // verify children
            left = currNode.left != null ? currNode.left : null;
            right = currNode.right != null ? currNode.right : null;
            // get lesser
            if (right == null || left.data.compareTo(right.data) < 0) {
                if (left.data.compareTo(currNode.data) > 0) {
                    currNode.right = left;
                    currNode.left = right;
                }
            } else {
                if (right.data.compareTo(currNode.data) < 0) {
                    currNode.left = right;
                    currNode.right = left;
                }
            }
            
            System.out.println("papaNode======"+papaNode);
            if (papaNode.left != null) {
                papaNode.left = null;
            } else {
                papaNode.right = null;
            }

            this.size--;
            return true;
        } else if (currNode.right != null && currNode.data.compareTo(o) >= 0) {
            leaf = this.getLeafNode(currNode.right);
            papaNode = this.getNode(currNode, leaf.papa.data);
            currNode.data = leaf.data;
            if (leaf.data.compareTo(papaNode.left.data) == 0) {
                papaNode.left = null;
            } else {
                papaNode.right = null;
            }

            this.size--;
            return true;
        } 

        return false;
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
