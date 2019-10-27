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
    // cursor starts at 1 because of sentinel mode
    private int cursor = 1;
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
	public Heap12( boolean isMaxHeap)
	{
        this.heapArray = new ArrayList<Node>(5+1);
        //sentinel
        this.heapArray.add(new Node(null));
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
        this.heapArray = new ArrayList<Node>(capacity+1);
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
        this.heapArray.ensureCapacity((this.heapArray.size()*2)+1);
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
		return this.heapArray.get(1).data;
	}
	/** 
 	 * @return Element at top of heap. And remove it from the heap. 
 	 * 	return <tt>null</tt> if the heap is empty
	*/
	public E poll()
	{
        if (this.size == 0) return null;
        this.oldTop = this.heapArray.remove(1);
        this.heapArray.add(1, this.heapArray.remove(--this.size));
        System.out.printf("Removed>>>>%d and now new first %d\n", oldTop.data, this.heapArray.get(1).data);
        this.printTree();
        this.trickleDown(1);
		return this.oldTop.data;
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
        System.out.printf(">>>>>>>>>>>>>>>>>>>>adding %d\n", el);
        Node newNode = new Node(el);

        this.heapArray.add(newNode);
        this.size++;
        this.bubbler(this.size);
        return true;
	}

    public void getFamiliar(int idx)
    {
        Node currNode = this.heapArray.get(idx);
        currNode.papaNode = idx <= 1 ? null : this.heapArray.get(this.getPapaPos(idx));
        currNode.leftChild = this.size < 2*idx ? null : this.heapArray.get(2*idx);
        currNode.rightChild = this.size < 2*idx+1 ? null : this.heapArray.get(2*idx+1);
        //E papa = currNode.papaNode == null ? null : currNode.papaNode.data;
        //E lefty = currNode.leftChild == null ? null : currNode.leftChild.data;
        //E righty = currNode.rightChild == null ? null : currNode.rightChild.data;
        //System.out.println("...familiarizing>>>"+idx+"; left="+lefty+"; right="+righty+"; pop="+papa);
    }

    public void remove (int idx)
    {
        // this is if remove is requested as base 0
        if (++idx > this.size) return;
        Node currNode = this.heapArray.get(idx);
        this.heapArray.remove(idx);
        // TODO only do this if middle of the tree
        this.heapArray.add(idx, this.heapArray.remove(--this.size));
        System.out.printf("Removed >>>>> idx %d\n", idx);
        this.printTree();
        this.heepIt(idx);
    }

    public void heepIt (int initPos)
    {
        if (initPos < 1 || initPos >= this.size) return;
        if (this.bubbler(initPos)) {
            System.out.println("bubble bubble...");
        } else {
            System.out.println("trickle...");
            this.trickleDown(initPos);
        };
    }

    public int getLesserChilleIdx(int idx)
    {
        Node l = this.heapArray.get(2*idx);
        Node r = this.heapArray.get(2*idx+1);
        return l.data.compareTo(r.data) >= 0 ? (2*idx+1) : (2*idx); 
    }
    /**
     * 
     * can probably fruther abstract this and bubbler
     *
     */
    public boolean trickleDown(int starter)
    {
        boolean didSwap = false;
        // i feel like there's a quicker way to say im at the last level
        // but how to tell from idx that reached last depth ? TODO
        if (starter >= this.size) return didSwap;
        System.out.printf("Trinckling down from >>> %d\n", starter);
        Node currNode = this.heapArray.get(starter);
        // compare against the lesser of children
        int lesserChildIdx = this.getLesserChilleIdx(starter);
        Node lesserChild = this.heapArray.get(lesserChildIdx);
        // do it for minHeap|maxHeap
        if (currNode.data.compareTo(lesserChild.data) <= 0) return didSwap;
        this.swap(starter, lesserChildIdx);
        return this.trickleDown(lesserChildIdx);
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
        this.getFamiliar(pos);
        if (pos <= 1) return didSwap;
        this.getFamiliar(papaPos);
        Node currNode = this.heapArray.get(pos);
        System.out.printf("======comparing %d:%d vs %d:%d\n", pos, currNode.data, papaPos, currNode.papaNode.data);

        if (this.isMinHeep) {
            // if minHeap and current node >= parent node ok
            if (currNode.data.compareTo(currNode.papaNode.data) >= 0) {
                System.out.printf("%d:%d >= %d:%d\n", pos, currNode.data, this.getPapaPos(pos), currNode.papaNode.data);
                return didSwap;
            }
        }
        else {
            return didSwap;
            //if (currNode.data.compareTo(papaNode.data) <= 0) return didSwap;
        }

        didSwap = true;
        this.swap(pos, papaPos);
        // continue to bubbler until currNode == root which should be index = 1
        return this.bubbler(papaPos);
    }

    public int getPapaPos (int currNodePos)
    {
        return currNodePos/2;
    }

    public void swap(int from, int to)
    {
        Node temp = this.heapArray.get(to);
        Node fromEl = this.heapArray.get(from);
        this.heapArray.set(to, fromEl);
        this.heapArray.set(from, temp);
        this.getFamiliar(from);
        // update parent with new child
        int papaPos = this.getPapaPos(from);
        Node papaNode = this.heapArray.get(papaPos); 
        this.getFamiliar(papaPos);
        if (papaNode.leftChild != null)
            this.getFamiliar(2*papaPos);
        if (papaNode.rightChild != null)
            this.getFamiliar(2*papaPos+1);

        int pops = this.getPapaPos(to);
        Node popsNode = this.heapArray.get(pops);
        this.getFamiliar(to);
        this.getFamiliar(pops);
        if (popsNode.leftChild != null)
            this.getFamiliar(2*pops);
        if (popsNode.rightChild != null)
            this.getFamiliar(2*pops+1);
        System.out.printf("** UPDATE %d=%d, %d=%d\n", to, fromEl.data, from, temp.data);
        this.printTree();
    }

    public void printTree()
    {
        int idx = 1;
        System.out.println("====================");
        while (idx <= this.size) {
            Node currNode = this.heapArray.get(idx);
            E papa = currNode.papaNode == null ? null : currNode.papaNode.data;
            E lefty = currNode.leftChild == null ? null : currNode.leftChild.data;
            E righty = currNode.rightChild == null ? null : currNode.rightChild.data;
            //System.out.printf("#%d >>>>> %d, pops=%s\n", idx, currNode.data, currNode.papaNode.data);
            //System.out.println(idx+" - "+currNode.data+"; papa="+currNode.papaNode.data+"; left="+currNode.leftChild.data+"; right="+currNode.rightChild.data);
            System.out.println(currNode.data+"; papa="+papa+"; left="+lefty+"; right="+righty);
            idx++;
        }
    }

    /** Inner Class for an Iterator 
	This is a recommended class name. You may change it**/
	private class Heap12Iterator implements Iterator<E>
	{
        	private boolean canRemove;
		/* there are several ways to iterate through a heap, 
 		 * the simplest is breadth-first, which is just through
 		 * the indices
 		 */
	
        	private Heap12Iterator()
        	{
        	}

		public boolean hasNext()
		{
            		return true; // TODO: change this when code is implmented	
		}
		public E next() throws NoSuchElementException
		{
			return (E) null;  // TODO: change this when code is implemented
		}
		public void remove() throws IllegalStateException
		{
		}	
	}
} 
// vim:ts=4:sw=4:tw=78:et
