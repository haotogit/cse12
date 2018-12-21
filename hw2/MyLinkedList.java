/**
 * My LinkedList implementation
 */

import java.util.*;
import java.io.*;

public class MyLinkedList<E> extends AbstractList<E>  {
    private int size = 0;
    private Node head;
    private Node tail;

    protected class Node
    {
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
        this.head.next = this.tail;
        this.head.prev = null;
        this.tail.prev = this.head;
        this.tail.next = null;
    }

    @Override
    public int size()
    {
        return this.size;
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
        if (this.size() == 0 && this.head.next == this.tail) throw new IndexOutOfBoundsException();
        return this.getNth(index).data;
    }

    @Override
    public boolean add(E el)
    {
        if (el == null) throw new NullPointerException("Can't add null element");
        Node newNode = new Node(el);
        Node last = this.tail.prev;
        newNode.prev = last;
        newNode.next = this.tail;
        last.next = newNode;
        this.tail.prev = newNode;
        int prevSize = this.size();
        this.size++;
        return prevSize != this.size;
    }

    @Override
    public void add(int index, E el)
    {
        if (el == null) throw new NullPointerException("Can't add null element");
        else if (index < 0 || index > this.size()) throw new IndexOutOfBoundsException();
        Node temp = this.getNth(index);
        Node prev = temp.prev;
        Node next = temp;
        Node newNode = new Node(el);
        newNode.prev = prev;
        newNode.next = next;
        next.prev = newNode;
        prev.next = newNode;
        this.size++;
    }

    @Override
    public E set(int index, E el)
    {
        if (el == null) throw new NullPointerException("Null value not allowed");
        else if (index < 0 || index >= this.size()) throw new IndexOutOfBoundsException();
        Node temp = this.getNth(index);
        E oldData = temp.data;
        temp.data = el;
        return oldData;
    }

    @Override
    public E remove(int index)
    {
        if (index < 0 || index >= this.size()) throw new IndexOutOfBoundsException();
        Node temp = this.getNth(index);
        E tempVal = temp.data;
        Node prev = temp.prev;
        Node next = temp.next;
        prev.next = next;
        next.prev = prev;
        temp.prev = null;
        temp.next = null;
        this.size--;
        return tempVal;
    }

    @Override
    public void clear()
    {
        while (this.size() != 0) {
            this.remove(0);
        }
    }

    @Override
    public boolean isEmpty()
    {
        return this.size() == 0 && this.head.next == this.tail;
    }

    // The following MyListIterator class is called an Inner Class
    // Because it is "Nested" inside of MyLinkedList
    //      o it has access to all of the MyLinkedList instance variables.  
    //        This includes private variables 
    //      o To access the set() method of the MyLinkedList instance 
    //        from within a method here use
    //                MyLinkedList.this.set(i,e);
    protected class MyListIterator implements ListIterator<E> {
        private Node cursor = MyLinkedList.this.head;
        // currDirection starts going forward true = fw;
        private boolean currDirection = true;
        private int currIndex = 0;
        private boolean stateChange = false;
        private Node currNode = null;

        private void setDirection(boolean fwd)
        {
            // this is to reposition cursor if it was going backwards put
            // cursor back to forward position
            if (!this.currDirection) this.cursor = this.cursor.prev;
            // if this.currDirection then going forwards
            // else going backwards
            this.currDirection = fwd;
        }

        @Override
        public E next()
        {
            if (!this.hasNext()) throw new NoSuchElementException();
            this.setDirection(true);
            this.cursor = this.cursor.next;
            this.currIndex++;
            this.currNode = this.cursor;
            this.stateChange = false;
            return this.cursor.data;
        }

        @Override
        public boolean hasNext()
        {
            return this.cursor.next != null && this.currIndex < MyLinkedList.this.size();
        }

        @Override
        public int nextIndex()
        {
            return this.currIndex >= MyLinkedList.this.size() ?
                MyLinkedList.this.size() : this.currIndex;
        }

        @Override
        public E previous()
        {
            if (!hasPrevious()) throw new NoSuchElementException();
            this.setDirection(false);
            this.currIndex--;
            this.currNode = this.cursor;
            this.stateChange = false;
            return this.cursor.data;
        }

        @Override
        public boolean hasPrevious()
        {
            return this.cursor.prev != null && this.currIndex > 0;
        }

        @Override
        public int previousIndex()
        {
            return this.currIndex - 1 == 0 ? -1 : this.currIndex - 2; 
        }

        @Override
        public void add(E e)
        {
            int addIndex = this.currIndex - 1;
            MyLinkedList.this.add(addIndex, e);
            this.cursor = MyLinkedList.this.getNth(addIndex);
            this.stateChange = true;
        }

        @Override
        public void remove()
        {
            if (this.currNode == null || this.stateChange) throw new IllegalStateException();
            int removalIndex = this.currDirection ? this.currIndex - 1 : this.currIndex;
            MyLinkedList.this.remove(removalIndex);
            this.cursor = MyLinkedList.this.getNth(removalIndex);
            this.stateChange = true;
        }

        @Override
        public void set(E e)
        {
            if (this.currNode == null || this.stateChange) throw new IllegalStateException();
            int replaceIndex = this.currIndex == 0 ? 0 : this.currIndex - 1;
            MyLinkedList.this.set(replaceIndex, e);
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

    public Iterator<E> iterator()
    {
        return new MyListIterator();
    }
    public ListIterator<E> listIterator()
    {
        return new MyListIterator();
    }
}
// VIM: set the tabstop and shiftwidth to 4 
// vim:tw=78:ts=4:et:sw=4
