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

    public Deque12(int initialCapacity)
    {
        list = new ArrayList<E>(initialCapacity);
        capacity = initialCapacity;
        front = 0;
        rear = 1;
    }

    public int capacity()
    {
        return capacity;
    }

    public int size()
    {
        return list.size();
    }

    public boolean addFront(E el)
    {
        if (el == null && size < capacity)
            throw new NullPointerException();
        else if (list.size() == capacity) 
            return false;

        list.add(front, el);
        frontRearHandler(true);
        return true;
    }

    public boolean addBack(E el)
    {
        return true;
    }

    private void frontRearHandler(boolean fw)
    {
        if (fw) {
            front = (front + capacity - 1) % capacity;
        } else {
            rear = (rear + 1) % capacity;
        }
    }

    public E removeFront()
    {
        return null;
    }

    public E removeBack()
    {
        return null;
    }

    public E peekFront()
    {
        return null;
    }

    public E peekBack()
    {
        return null;
    }
}
