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
        // +2 because of sentinel front and rear
        list = new ArrayList<E>(initialCapacity+2);
        capacity = initialCapacity;
        front = 0;
        rear = 1;
        for (int i = 0; i < initialCapacity; i++) {
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
        frontRearHandler(true);
        size++;
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
        System.out.format("Front now @ %d", front);
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
        return list.get(front == capacity - 1 ? 0 : front + 1);
    }

    public E peekBack()
    {
        return null;
    }
}
