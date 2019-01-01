/**
 * Title: class BoundedDeque
 * Description: Fixed capacity deque utilizing a circular array as
 * backing store. Using ArrayList instead of Array for better
 * compatibility with Generics
 * @author Sam Park
 */
import java.util.ArrayList;
import java.io.*;

public class Deque12<E> implements BoundedDeque<E> {
    private int size = 0;
    private int capacity;
    private ArrayList<E> list;
    private int front;
    private int rear;
    private int capacityPlusSentinel;

    public Deque12(int initialCapacity)
    {
        // +2 because of sentinel front and rear
        capacityPlusSentinel = initialCapacity + 2;
        list = new ArrayList<E>(capacityPlusSentinel);
        capacity = initialCapacity;
        front = 0;
        rear = 1;
        for (int i = 0; i < capacityPlusSentinel; i++) 
        {
            list.add(null);
        }
    }

    public int capacity()
    {
        return capacity;
    }

    public int size()
    {
        return size;
    }

    public boolean addFront(E el)
    {
        if (el == null && size < capacity)
            throw new NullPointerException();
        else if (size == capacity) 
            return false;

        list.set(front, el);
        frontRearHandler(true, true);
        size++;
        return true;
    }

    public boolean addBack(E el)
    {
        if (el == null && size < capacity)
            throw new NullPointerException();
        else if (size == capacity)
            return false;

        list.set(rear, el);
        frontRearHandler(false, true);
        System.out.format("rearIndex @ %d\n", rear);
        if (rear == 11) System.out.format("0 item is %d\n", list.get(1));
        size++;
        return true;
    }

    private void frontRearHandler(boolean fw, boolean inc)
    {
        if (fw) {
            if (inc)
                front = (front + capacityPlusSentinel - 1) % capacityPlusSentinel;
            else
                front = (front + 1) % capacityPlusSentinel;
        } else {
            if (inc)
                rear = (rear + 1) % capacityPlusSentinel;
            else
                rear = (rear + capacityPlusSentinel - 1) % capacityPlusSentinel;
        }
    }

    public E removeFront()
    {
        if (size == 0) return null;
        E oldEl = peekFront();
        list.set(front == capacityPlusSentinel - 1 ? 0 : front + 1, null);
        frontRearHandler(true, false);
        size--;
        return oldEl;
    }

    public E removeBack()
    {
        if (size == 0) return null;
        E oldEl = peekBack();
        list.set(rear == 0 ? capacityPlusSentinel - 1 : rear - 1, null);
        frontRearHandler(false, false);
        size--;
        return oldEl;
    }

    public E peekFront()
    {
        return list.get(front == capacityPlusSentinel - 1 ? 0 : front + 1);
    }

    public E peekBack()
    {
        return list.get(rear == 0 ? capacityPlusSentinel - 1 : rear - 1);
    }
}
