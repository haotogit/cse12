import java.util.ArrayList;

public class Deque12<E> implements BoundedDeque<E> {
    private int size = 0; 
    private ArrayList list;

    public Deque12(int initialCapacity)
    {
        list = new ArrayList(initialCapacity);
    }

    public int capacity()
    {
        return 0;
    }

    public int size()
    {
        return size;
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
