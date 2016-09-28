package homework.lesson4;

import java.util.Iterator;

/**
 * Created by Anatoly on 16.09.2016.
 */
public class LinkedListIterator<E> implements Iterator{
    private LinkedList<E> linkedList;
    private Node current;

    public LinkedListIterator() {
    }

    public LinkedListIterator(LinkedList<E> linkedList) {
        this.linkedList = linkedList;
        current = null;
    }

    @Override
    public boolean hasNext() {
        if (current == null)
            return linkedList.getFirst() != null;
        else {
            return current.getNext() != null;
        }

    }

    @Override
    public Node next() {
        if (hasNext()) {
            current = current == null ? linkedList.getFirst() : current.getNext();
            return current;
        }
        return null;
    }
}
