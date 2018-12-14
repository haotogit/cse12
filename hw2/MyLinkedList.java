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
        this.head.prev = null;
        this.tail.prev = head;
        this.tail.next = null;
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
        if (size() == 0 && this.head.next == this.tail) throw new IndexOutOfBoundsException();
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
        return size() == 0 && this.head.next == this.tail;
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
            // if this.currDirection then going forwards
            // else going backwards
            this.currDirection = fwd;
        }

        @Override
        public E next()
        {
            if (!hasNext()) throw new NoSuchElementException();
            this.cursor = MyLinkedList.this.getNth(this.currIndex);
            this.currIndex++;
            this.currNode = this.cursor;
            this.stateChange = false;
            return this.cursor.data;
        }

        @Override
        public boolean hasNext()
        {
            setDirection(true);
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
            this.currIndex--;
            this.cursor = MyLinkedList.this.getNth(this.currIndex);
            this.currNode = this.cursor;
            this.stateChange = false;
            return this.cursor.data;
        }

        @Override
        public boolean hasPrevious()
        {
            setDirection(false);
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
            MyLinkedList.this.add(this.currIndex-1, e);
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

    //UNCOMMENT the following when you believe your MyListIterator class is
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
