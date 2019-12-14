import java.util.*;
import java.util.Iterator;

public class BST12<E extends Comparable <? super E>> implements BinSearchTree12<E>
{
    protected class Node
    {
        Node papa;
        Node left;
        Node right;
        E data;

        public Node(E val)
        {
            this.data = val;
        }

        public Node(E val, Node left, Node right)
        {
            this.data = val;
            this.left = left;
            this.right = right;
        }
    }

    int size;
    Node root;

    public boolean add(E e)
    {
        return true;
    }

    public int numChildren(E target)
    {
        return 0;
    }

    public int height()
    {
        return 0;
    }

    public int size()
    {
        return 0;
    }

    public boolean addAll(Collection<? extends E> c)
    {
        return true;
    }

    public void clear()
    {
        return;
    }

    public boolean contains(E o)
    {
        return true;
    }

    public E first()
    {
        return (E)null;
    }

    public boolean isEmpty()
    {
        return true;
    }

    public E last()
    {
        return (E)null;
    }

    public boolean remove(E o)
    {
        return true;
    }

    public Iterator<E> iterator()
    {
        return new BST12Iterator();
    }

    protected class BST12Iterator implements Iterator<E>
    {
        @Override
        public boolean hasNext()
        {
            return true;
        }

        public E next() throws NoSuchElementException
        {
            return (E) null;
        }
    }
}
