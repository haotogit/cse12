/**
 * My LinkedList implementation
 */

import java.util.*;

public class MyLinkedList<E> extends AbstractList<E>  {
    private int length = 0;
    // creating this to reference what type of list this
    // in order to check which type's allowed when adding. right ?
    private MyLinkedList thisList;

    protected class Node {
        E data;
        Node next;
        Node prev;
        // TODO - your code here
    }

    public MyLinkedList<E> MyLinkedList() {
        Node head = new Node();
        head.data = null;
        Node tail = new Node();
        tail.data = null;
        tail.prev = head;
        head.next = tail;
        return new MyLinkedList<E>();
    }

    // The following MyListIterator class is called an Inner Class
    // Because it is "Nested" inside of MyLinkedList
    //      o it has access to all of the MyLinkedList instance variables.  
    //        This includes private variables 
    //      o To access the set() method of the MyLinkedList instance 
    //        from within a method here use
    //                MyLinkedList.this.set(i,e);
    protected class MyListIterator implements ListIterator<E> {

        // TODO - your code here

        @Override
        public void add(E e)
        {
            // need to code the add method of ListIterators 
        }
        @Override
        public boolean hasNext()
        {
            // need to code the hasNext method of ListIterators 
            return false; // Modify. this is so that skeleton code will compile
        }
        @Override
        public boolean hasPrevious()
        {
            // need to code the hasPrevious method of ListIterators 
            return false; // So that skeleton code will compile
        }
        @Override
        public E next()
        {
            // need to code the next method of ListIterators
            return (E) null; // So that skeleton code will compile
        }
        @Override
        public int nextIndex()
        {
            // need to code the nextIndex method of ListIterators
            return -1; // So that skeleton code will compile
        }
        @Override
        public E previous()
        {
            // need to code the previous method of ListIterators
            return (E) null; // so that skeleton code will compile
        }
        @Override
        public int previousIndex()
        {
            // need to code the previousIndex method of ListIterators
            return -1; // So that skeleton code will compile
        }
        @Override
        public void remove()
        {
            // need to code the remove method of ListIterators
        }
        @Override
        public void set(E e)
        {
            //
            // need to code the set method of ListIterators
        }

    }

    // TODO - Your methods for the MyLinkedList Class 

    @Override
    public int size()
    {
        // need to implement the size method
        return length; // So skeleton code will compile
    }

    @Override
    public E get(int index)
    {
        // need to implement get  method
        return (E) null; // So skeleton code will compile
    }

    public Iterator<E> QQQiterator()
    {
        return new MyListIterator();
    }
    public ListIterator<E> QQQlistIterator()
    {
        return new MyListIterator();
    }

    /* retrieve the Node located at the Nth index */
    private Node getNth(int index)
    {
        return null;  // Modify. This is so that skeleton code will compile. 
    }

    /*  UNCOMMENT the following when you believe your MyListIterator class is
        functioning correctly
        public Iterator<E> iterator()
        {
        return new MyListIterator();
        }
        public ListIterator<E> listIterator()
        {
        return new MyListIterator();
        }
        */
}
// VIM: set the tabstop and shiftwidth to 4 
// vim:tw=78:ts=4:et:sw=4
