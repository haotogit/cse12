import java.util.ArrayList;

public class Deque12<E> implements BoundedDeque<E> {
    private int size; 
    private int capacity;
    private ArrayList list;
    private E head;
    private E tail;

    public Deque12(int initialCapacity)
    {
        list = new ArrayList(initialCapacity);
        capacity = initialCapacity;
        //head = list.get(0);
        //tail = list.get(capacity - 1);
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
        return true;
    }

    public boolean addBack(E el)
    {
        return true;
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
