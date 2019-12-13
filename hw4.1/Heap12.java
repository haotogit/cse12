import java.util.*;
import java.io.*;
import java.util.NoSuchElementException;
/** Heap12 class that implements an unbounded array-backed heap structure and is
 * an extension of the Java Collections AbstractQueue class 
 * <p>
 * The elements of the heap are ordered according to their natural 
 * ordering,  Heap12 does not permit null elements. 
 * The top of this heap is the minimal or maximal element (called min/max)  
 * with respect to the specified natural ordering.  
 * If multiple elements are tied for min/max value, the top is one of 
 * those elements -- ties are broken arbitrarily. 
 * The queue retrieval operations poll, remove, peek, and element 
 * access the element at the top of the heap.
 * <p>
 * A Heap12 is unbounded, but has an internal capacity governing the size of 
 * an array used to store the elements on the queue. It is always at least as 
 * large as the queue size. As elements are added to a Heap12, its capacity 
 * grows automatically. The details of the growth policy are not specified.
 * <p>
 * This class and its iterator implements the optional methods of the 
 * Iterator interface (including remove()). The Iterator provided in method 
 * iterator() is not guaranteed to traverse the elements of the Heap12 in 
 * any particular order. 
 * <p>
 * Note that this implementation is not synchronized. Multiple threads 
 * should not access a Heap12 instance concurrently if any of the 
 * threads modifies the Heap12. 
 *For a full tree with n nodes,
 the interior nodes of the tree
 are all found at indexes 0
 through n/2 – 1. and the
 leaves are at indexes n/2 through
 n – 1.
 */
import java.util.ArrayList;
import java.io.*;

public class Heap12<E extends Comparable <? super E>> extends 
	AbstractQueue<E> 
{
    private ArrayList<Node> heapArray;
    private int size;
    private int maxSize = 5;
    private int cursor = 0;
    private boolean isMinHeep = true;
    private Node oldTop;
    protected class Node
    {
        E data;
        // 2*i+1
        // or 2 * i if array root at 1
        Node leftChild;
        // 2 * i + 2
        // or 2*i+1 if array root at 1
        Node rightChild;
        //parent (i-1)/2
        // or i/2 if array root at 1
        Node papaNode;

        public Node(E val) {
            this.data = val;
        }
    }

    /** 0-argument constructor. Creates and empty Heap12 with capacity of 5
 	 *  elements, and is a min-heap 
 	 */ 
	public Heap12()
	{
        this(true);
	}

	/** 
 	 * Constructor to build a min or max heap
 	 * @param isMaxHeap 	if true, this is a max-heap, else a min-heap. Initial
	 * capacity of the heap should be 5.
 	 */ 
	public Heap12(boolean isMinHeap)
	{
        this.heapArray = new ArrayList<Node>(5);
        this.isMinHeep = isMinHeap;
	}

	/** 
 	 * Constructor to build a with specified initial capacity 
 	 *  min or max heap
 	 * @param capacity  	initial capacity of the heap.	
 	 * @param isMaxHeap 	if true, this is a max-heap, else a min-heap 
 	 */ 
	public Heap12( int capacity, boolean isMaxHeap)
	{
        capacity++;
        this.heapArray = new ArrayList<Node>(capacity);
        this.isMinHeep = isMaxHeap;
        this.maxSize = capacity;
	}
	/** Copy constructor. Creates Heap12 with a deep copy of the argument
 	 * @param toCopy      the heap that should be copied
     *public Heap12 (ArrayList<E> toCopy)
	{
        this.heapArray = new ArrayList<Node>(toCopy);
        this.heapArray.ensureCapacity(toCopy.size()*2); 
	}
 	 */

	/* The following are defined "stub" methods that provide degenerate
	 * implementations of methods defined as abstract in parent classes.
	 * These need to be coded properly for Heap12 to function correctly
	 */

	/** Size of the heap
 	 * @return the number of elements stored in the heap
	*/
	public int size()
	{
		return this.size;
	}

    public void doubleSize ()
    {
        ArrayList<Node> temp = this.heapArray;
        // I don't know if this is right ???
        this.heapArray = new ArrayList<Node>(this.heapArray);
        this.heapArray.ensureCapacity((this.heapArray.size()*2));
        this.maxSize = this.maxSize*2;
    }

	

	/** 
 	 * @return Element at top of heap. Do not remove 
	*/
	public E peek()
	{
		return this.heapArray.get(0).data;
	}
	/** 
 	 * @return Element at top of heap. And remove it from the heap. 
 	 * 	return <tt>null</tt> if the heap is empty
	*/
	public E poll()
	{
        if (this.size == 0) return null;
        this.oldTop = this.heapArray.remove(0);
        this.size--;
        if (this.size > 1) {
            this.heapArray.add(0, this.heapArray.remove(this.size-1));
        }
        if(!this.heapArray.isEmpty() && this.size > 1) {
            this.trickleDown(0);
        }
		return this.oldTop.data;
	}

    public E remove(int idx)
    {
        if (this.size == 0) return null;
        Node removal = this.heapArray.remove(idx);
        this.size--;
        if (idx < this.size) {
            this.heapArray.add(idx, this.heapArray.remove(this.size-1));
        }
        //System.out.printf("Removed >>>>> idx %d=%s, and added %d\n", idx, removal.data, this.heapArray.get(idx).data);
        return removal.data;
    }

	/** 
	 * insert an element in the heap
     * insert new el at rightmost leaf, size++, bubbleup:
     * if node is root return
	 * @return true
	 * @throws ClassCastException 
	 * 	if the class of the element prevents it from being added
	 * @throws NullPointerException 
	 * 	if the specified element is null	
	 * @throws IllegalArgumentException
	 * 	if some property of the element keeps it from being added. 
	 */
	public boolean offer (E el)
	{
        if (el == null) throw new NullPointerException("Can't add null element");
        // if list full do the constructor with a copy arg
        // but coudlnt get this.Heap12(<Heap12>) or this.Heap12(<ArrayList>)
        // how am i supposed to copy from that Heap object if list is private?
        // TODO 
        if (this.size == this.maxSize) this.doubleSize();
        System.out.printf(">>>>>>>>>>>>>>>>>>>>adding %s\n", el);
        Node newNode = new Node(el);

        this.heapArray.add(newNode);
        this.size++;
        System.out.println("bubbledUPTO==============="+this.bubbler(this.size-1));
        //this.offer(this.size);
        //optionals java 8
        return true;
        // stream api list
        // for(T e : list)
        // 
	}

    public boolean add (int idx, E el)
    {
        if (el == null) throw new NullPointerException("Can't add null element");
        if (this.size == this.maxSize) this.doubleSize();
        System.out.printf("^^adding %s@%d\n", el, idx);
        Node newNode = new Node(el);
        this.heapArray.add(idx, newNode);
        this.size++;
        this.printTree();
        return true;
    }

    public Integer heepIt (int initPos)
    {
        if (initPos < 0 || initPos > this.size) return null;
        int bubbledUpTo = this.bubbler(initPos);
        if (bubbledUpTo != initPos) {
            System.out.println("...bubbledupto..."+bubbledUpTo);
            return bubbledUpTo;
        } else {
            this.trickleDown(initPos);
            return null;
        }
    }

    public int biggerChildIdx(int idx)
    {
        // if left is greater than right, then return lesser right
        Node leftChild = getLeftChild(idx);
        Node rightChild = getRightChild(idx);
        if (rightChild != null && leftChild.data.compareTo(rightChild.data) <= 0) {
            return 2*idx + 2;
        } else return 2*idx + 1;
    }

    public int lesserChildIdx(int idx)
    {
        // if left is greater than right, then return lesser right
        Node leftChild = getLeftChild(idx);
        Node rightChild = getRightChild(idx);
        if (rightChild != null && leftChild.data.compareTo(rightChild.data) >= 0) {
            return 2*idx + 2;
        } else return 2*idx + 1;
    }
    /**
     * 
     * can probably fruther abstract this and bubbler
     *
     */
    private Node getLeftChild(int pos) {
        int i = pos*2 + 1;
        if(i >= this.size)
            return null;
        return heapArray.get(i);
    }

    private Node getRightChild(int pos) {
        int i = pos*2 + 2;
        if(i >= this.size)
            return null;
        return heapArray.get(i);
    }
    public boolean trickleDown(int starter)
    {
        boolean didSwap = false;
        if (starter > this.size) return didSwap;
        Node currNode = this.heapArray.get(starter);
        if(getLeftChild(starter) == null && getRightChild(starter) == null) {
            return didSwap;
        }
        // compare against child
        int currChildIdx = this.isMinHeep ? this.lesserChildIdx(starter) : this.biggerChildIdx(starter);
        if (currChildIdx == 0 || currChildIdx >= this.size) return didSwap;
        Node currChild = this.heapArray.get(currChildIdx);
        if (this.isMinHeep) {
            if (currNode.data.compareTo(currChild.data) < 0) return didSwap;
        } else {
            // if currRoot is greater than greater child then dont swap
            if (currNode.data.compareTo(currChild.data) >= 0) return didSwap;
        }
        System.out.println("trickling...");
        this.swap(starter, currChildIdx);
        this.trickleDown(currChildIdx);
        return didSwap;
    }

    /* ------ Private Helper Methods ----
     *
    /** 
     * check heep, ensure it's still heep by verifying:
     * 1. structural property - heap is a complete binary tree
     *  complete binary tree is where every node has 1 or 2 leafs and they're
     *  contiguously to the left.
     * 2. ordering property - heap is either minheap(parent <= children) or maxheap(parent >= child)
     */
    public int bubbler (int pos)
    {
        if (pos == 0) return pos;
        int bubbledUpTo = pos;
        int papaPos = this.getPapaPos(pos);
        Node currNode = this.heapArray.get(pos);
        System.out.printf("======comparing@bubbler %d:%s vs %d:%s\n", pos, currNode.data, papaPos, this.heapArray.get(papaPos).data);

        if (this.isMinHeep) {
            // if minHeap and current node > parent node ok
            if (currNode.data.compareTo(this.heapArray.get(papaPos).data) > 0) {
                return bubbledUpTo;
            }
        }
        else {
            // if maxHeap and currNode is less than parent
            if (currNode.data.compareTo(this.heapArray.get(papaPos).data) <= 0) return bubbledUpTo;
        }

        this.swap(pos, papaPos);
        bubbledUpTo = papaPos;
        // continue to bubbler until currNode == root which should be index = 1
        return this.bubbler(papaPos);
    }

    public int getPapaPos (int currPos)
    {
        return (currPos-1)/2;
    }

    public void swap(int from, int to)
    {
        Node toEl = this.heapArray.get(to);
        Node fromEl = this.heapArray.get(from);
        this.heapArray.set(to, fromEl);
        this.heapArray.set(from, toEl);
        System.out.printf("** UPDATE %d=%s, %d=%s\n", to, fromEl.data, from, toEl.data);
    }

    public void printTree()
    {
        int idx = 0;
        //System.out.println("====================");
        while (idx < this.size) {
            Node currNode = this.heapArray.get(idx);
            //System.out.printf("#%d >>>>> %d, pops=%s\n", idx, currNode.data, currNode.papaNode.data);
            //System.out.println(idx+" - "+currNode.data+"; papa="+currNode.papaNode.data+"; left="+currNode.leftChild.data+"; right="+currNode.rightChild.data);
            System.out.println("#"+idx+"# "+currNode.data);
            idx++;
        }
    }

    /** Inner Class for an Iterator 
	This is a recommended class name. You may change it**/
	protected class Heap12Iterator implements Iterator<E>
	{
        private boolean canRemove;
        private int cursor = 0;
        private boolean stateChange = false;
        private ArrayList<Integer> rememberList = new ArrayList<>(Heap12.this.heapArray.size());
		/* there are several ways to iterate through a heap, 
 		 * the simplest is breadth-first, which is just through
 		 * the indices
 		 */
	
        private Heap12Iterator Heap12Iterator()
        {
            return new Heap12Iterator();
        }

        @Override
		public boolean hasNext()
		{
            return this.cursor < Heap12.this.size;
		}

        @Override
		public E next() throws NoSuchElementException
		{
            if (this.cursor > Heap12.this.size) throw new NoSuchElementException();
            this.stateChange = false;
            return Heap12.this.heapArray.get(this.cursor++).data;
		}

        /**
         * when iterating through this heap, elements should only be
         * enumerated once and only once, hence when removing
         * there are three cases:
         * 1. element doesn't move and cursor stays, next should return current cursor
         * 2. Element bubbles up
         * 3. Element trickles down, subsquent items have not been
         * enumerated, hence cursor stays and next is cursor.
         */
		public void remove() throws IllegalStateException
		{
            // TODO if removing from middle... how to handle that.
            if (this.stateChange) throw new IllegalStateException();
            E curr = Heap12.this.remove(--this.cursor);
            System.out.println("removingindex=================="+this.cursor);
            Integer heeped = Heap12.this.heepIt(this.cursor);
            this.stateChange = true;

            // if not null it bubbled up and already enumerated previous
            // indexes
            // therefore cursor stays at next;
            if (heeped != null) {
                System.out.println("heeped}}}}}}}}}}}}}}}}}}}}}}}} "+heeped);
                this.cursor++;
                this.rememberList.add(heeped);
            } else {
                // if trickle down or no movement stay at same spot
            }
		}

        public void seeRememberList()
        {
            this.rememberList.forEach(item->{
            System.out.println("<<<<<!!!!!!!!!!");
                if (item != null) {
                    System.out.println("yoo"+Heap12.this.heapArray.get(item).data);
                }
            });
        }
	}

    /** 
 	 * @return an Iterator for the heap 
	*/
	public Heap12Iterator iterator()
	{
        return new Heap12Iterator();
	}
} 
// vim:ts=4:sw=4:tw=78:et
