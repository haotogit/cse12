public class Queue12<E> implements BoundedQueue<E> {
    private BoundedDeque<E> daQ;

    public Queue12(int cap)
    {
        daQ = new Deque12<E>(cap);
    }

    public int capacity()
    {
        return daQ.capacity();
    }

    public int size()
    {
        return daQ.size();
    }

    public boolean enqueue(E e)
    {
        return daQ.addBack(e);
    }

    public E dequeue()
    {
        return daQ.removeFront();
    }

    public E peek()
    {
        return daQ.peekFront();
    }
}
