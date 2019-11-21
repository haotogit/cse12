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
	public Heap12(boolean isMaxHeap)
	{
        this.heapArray = new ArrayList<Node>(5);
        this.isMinHeep = isMaxHeap;
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
 	 * @return an Iterator for the heap 
	*/
	public Iterator<E> iterator()
	{
		return new Heap12Iterator();
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
        if(!this.heapArray.isEmpty()) {
            this.trickleDown(0);
        }
		return this.oldTop.data;
	}

    public E remove (int idx)
    {
        if (this.size == 0) return null;
        Node removal = this.heapArray.remove(idx);
        this.size--;
        if (removal.leftChild != null || removal.rightChild != null) {
            Node temp = this.heapArray.remove(this.size);
            this.heapArray.add(idx, temp);
        }
        System.out.printf("Removed >>>>> idx %d=%s\n", idx, removal.data);
        this.heepIt(1);
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
        this.bubbler(this.size-1);
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

    public void getFamiliar(int idx)
    {
        Node lNut;
        Node rNut;
        Node currNode = this.heapArray.get(idx);
        Node papaNode;
        int papaPos = this.getPapaPos(idx);
        currNode.papaNode = idx <= 1 ? null : this.heapArray.get(papaPos);
        currNode.leftChild = this.size < 2*idx ? null : this.heapArray.get(2*idx);
        currNode.rightChild = this.size < 2*idx+1 ? null : this.heapArray.get(2*idx+1);
        if (currNode.leftChild != null) {
            lNut = this.heapArray.get(2*idx);
            lNut.papaNode = currNode;
        }

        if (currNode.rightChild != null) {
            rNut = this.heapArray.get(2*idx+1);
            rNut.papaNode = currNode;
        }

        // update parent
        papaNode = this.heapArray.get(papaPos); 
        papaNode.leftChild = this.size < 2*papaPos ? null : this.heapArray.get(2*papaPos);
        papaNode.rightChild = this.size < 2*papaPos+1 ? null : this.heapArray.get(2*papaPos+1);

        if (papaNode.leftChild != null) {
            lNut = this.heapArray.get(2*papaPos);
            lNut.papaNode = papaNode;
        }

        if (papaNode.rightChild != null) {
            rNut = this.heapArray.get(2*papaPos+1);
            rNut.papaNode = papaNode;
        }
    }

    

    public void heepIt (int initPos)
    {
        if (initPos < 1 || initPos > this.size) return;
        if (this.bubbler(initPos)) {
            System.out.println("...bubble bubble..."+initPos);
        } else {
            System.out.println("trickle...");
            this.trickleDown(initPos);
        };
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
        // i feel like there's a quicker way to say im at the last level
        // but how to tell from idx that reached last depth ? TODO
        if (starter > this.size) return didSwap;
        Node currNode = this.heapArray.get(starter);
        if(getLeftChild(starter) == null && getRightChild(starter) == null) {
            return didSwap;
        }
        // compare against child
        int currChildIdx = this.isMinHeep ? this.lesserChildIdx(starter) : this.biggerChildIdx(starter);
        if (currChildIdx == 0 || currChildIdx >= this.size) return didSwap;
        Node currChild = this.heapArray.get(currChildIdx);
        //System.out.println("currIndex="+starter+"compareChild="+currChildIdx);
        // do it for minHeap|maxHeap
        if (this.isMinHeep) {
            if (currNode.data.compareTo(currChild.data) < 0) return didSwap;
        } else {
            // if currRoot is greater than greater child then dont swap
            if (currNode.data.compareTo(currChild.data) >= 0) return didSwap;
        }
        this.swap(starter, currChildIdx);
        //this.trickleDown(currChildIdx);
        this.trickleDown(currChildIdx);
        return didSwap;
    }

    /* ------ Private Helper Methods ----
     *
    /** 
     * check heep, ensure it's still heep by verifying:
     * 1. structural property - heap is a complete binary tree
     * 2. ordering property - heap is either minheap(parent <= children) or maxheap(parent >= child)
     */
    public boolean bubbler (int pos)
    {
        int papaPos = this.getPapaPos(pos);
        boolean didSwap = false;
        if (pos == 0) return didSwap;
        Node currNode = this.heapArray.get(pos);
        System.out.printf("======comparing@bubbler %d:%s vs %d:%s\n", pos, currNode.data, papaPos, this.heapArray.get(papaPos).data);

        if (this.isMinHeep) {
            // if minHeap and current node > parent node ok
            if (currNode.data.compareTo(this.heapArray.get(papaPos).data) > 0) {
                System.out.println("notswapping==="+didSwap);
                return didSwap;
            }
        }
        else {
            // if maxHeap and currNode is less than parent
            if (currNode.data.compareTo(this.heapArray.get(papaPos).data) <= 0) return didSwap;
        }

        didSwap = true;
        this.swap(pos, papaPos);
        // continue to bubbler until currNode == root which should be index = 1
        this.bubbler(papaPos);
        return didSwap;
    }

    public int getPapaPos (int currNodePos)
    {
        return (currNodePos-1)/2;
    }

    public void swap(int from, int to)
    {
        Node temp = this.heapArray.get(to);
        Node fromEl = this.heapArray.get(from);
        this.heapArray.set(to, fromEl);
        this.heapArray.set(from, temp);
        System.out.printf("** UPDATE %d=%s, %d=%s\n", to, fromEl.data, from, temp.data);
        //this.printTree();
    }

    public void printTree()
    {
        int idx = 1;
        //System.out.println("====================");
        while (idx <= this.size) {
            Node currNode = this.heapArray.get(idx);
            E papa = currNode.papaNode == null ? null : currNode.papaNode.data;
            E lefty = currNode.leftChild == null ? null : currNode.leftChild.data;
            E righty = currNode.rightChild == null ? null : currNode.rightChild.data;
            //System.out.printf("#%d >>>>> %d, pops=%s\n", idx, currNode.data, currNode.papaNode.data);
            //System.out.println(idx+" - "+currNode.data+"; papa="+currNode.papaNode.data+"; left="+currNode.leftChild.data+"; right="+currNode.rightChild.data);
            System.out.println("#"+idx+"# "+currNode.data+"; papa="+papa+"; left="+lefty+"; right="+righty);
            idx++;
        }
    }

    /** Inner Class for an Iterator 
	This is a recommended class name. You may change it**/
	private class Heap12Iterator implements Iterator<E>
	{
        private boolean canRemove;
        private int cursor = 0;
        private boolean stateChange = false;
		/* there are several ways to iterate through a heap, 
 		 * the simplest is breadth-first, which is just through
 		 * the indices
 		 */
	
        private Heap12Iterator Heap12Iterator()
        {
            return new Heap12Iterator();
        }

		public boolean hasNext()
		{
            return this.cursor < Heap12.this.size;
		}
		public E next() throws NoSuchElementException
		{
            if (this.cursor > Heap12.this.size) throw new NoSuchElementException();
            this.stateChange = false;
            return Heap12.this.heapArray.get(this.cursor++).data;
		}

		public E remove(int index) throws IllegalStateException
		{
            if (this.stateChange) throw new IllegalStateException();
            E curr = Heap12.this.remove(1);
            this.stateChange = true;
            return curr;
		}
	}
} 
// vim:ts=4:sw=4:tw=78:et
