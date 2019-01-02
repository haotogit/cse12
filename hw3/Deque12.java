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
        // TODO: Need to throw IllegalArgumentException if
        // initialCapacity < 0
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
        E oldEl = peekFront();
        if (size == 0 || oldEl == null) return null;
        list.set(getFrontIndex(), null);
        frontRearHandler(true, false);
        size--;
        return oldEl;
    }

    public E removeBack()
    {
        E oldEl = peekBack();
        if (size == 0 || oldEl == null) return null;
        list.set(getRearIndex(), null);
        frontRearHandler(false, false);
        size--;
        return oldEl;
    }

    private int getFrontIndex()
    {
        return (front + 1) % capacityPlusSentinel;
    }

    private int getRearIndex()
    {
        return (rear + capacityPlusSentinel - 1) % capacityPlusSentinel;
    }

    public E peekFront()
    {
        return list.get(getFrontIndex());
    }

    public E peekBack()
    {
        return list.get(getRearIndex());
    }
}
