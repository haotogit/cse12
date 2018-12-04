/**
 * My LinkedList implementation
 */

import java.util.*;
import java.io.*;

public class MyLinkedList<E> extends AbstractList<E>  {
    private int size = 0;
    private Node head;
    private Node tail;

    protected class Node {
        E data;
        Node next;
        Node prev;

        public Node(E val) {
            this.data = val;
        }
    }

    public MyLinkedList() 
    {
        this.head = new Node(null);
        this.tail = new Node(null);
        this.head.next = tail;
        this.tail.prev = head;
    }

    @Override
    public int size()
    {
        return size;
    }

    private Node getNth(int index)
    {
        int count = 0;
        Node temp = this.head;
        while(temp.next != null && count <= index) {
            temp = temp.next;
            count++;
        }

        return temp;
    }

    @Override
    public E get(int index)
    {
        if (size() == 0) throw new IndexOutOfBoundsException();
        return getNth(index).data;
    }

    @Override
    public boolean add(E el) 
    {
        if (el == null) throw new NullPointerException("Can't add null element");
        Node newNode = new Node(el);
        Node last = tail.prev;
        newNode.prev = last;
        newNode.next = tail;
        last.next = newNode;
        tail.prev = newNode;
        int prevSize = size();
        size++;
        return prevSize != size;
    }

    @Override
    public void add(int index, E el) 
    {
        if (el == null) throw new NullPointerException("Can't add null element");
        else if (index < 0 || index > size()) throw new IndexOutOfBoundsException();
        int i = 0;
        Node temp = this.head;
        while(temp.next != null && i <= index) {
            temp = temp.next;
            i++;
        }

        Node prev = temp.prev;
        Node next = temp;
        Node newNode = new Node(el);
        newNode.prev = prev;
        newNode.next = next;
        next.prev = newNode;
        prev.next = newNode;
        size++;
    }

    @Override
    public E set(int index, E el)
    {
        if (el == null) throw new NullPointerException("Null value not allowed");
        else if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();
        int i = 0;
        Node temp = this.head;
        while(temp != null && i <= index) {
            temp = temp.next;
            i++;
        }

        E oldData = temp.data;
        temp.data = el;
        return oldData;
    }

    @Override
    public E remove(int index)
    {
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();
        Node temp = getNth(index); 
        E tempVal = temp.data;
        Node prev = temp.prev;
        Node next = temp.next;
        prev.next = next;
        next.prev = prev;
        temp.prev = null;
        temp.next = null;
        size--;
        return tempVal;
    }

    @Override
    public void clear()
    {
        while (size() != 0) {
            remove(0);
        }
    }

    @Override
    public boolean isEmpty()
    {
        return size() == 0;
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

    public Iterator<E> QQQiterator()
    {
        return new MyListIterator();
    }
    public ListIterator<E> QQQlistIterator()
    {
        return new MyListIterator();
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
