package homework.lesson4;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Anatoly on 15.09.2016.
 */
public class LinkedList<E> {
    private Node first;
    private Node last;
    private Node current;
    private int size;

    private Node get(int index) {
        try{
            if (index == 0) {
                return first;
            }
            if (index == size - 1) {
                return last;
            }
            if (index < size/2) {
                current = first;
                for (int i = 0; i < index; i++) {
                    current = current.getNext();
                }
                return current;
            }
            else {
                current = last;
                for (int i = 0; i < size - 1 - index; i++) {
                    current = current.getPrevious();
                }
                return current;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private void addFirst(E value) {
        Node temp = new Node<E>(value);
        if (first == null) {
            first = last = temp;
        }
        else {
            temp.setPrevious(null);
            temp.setNext(first);
            first.setPrevious(temp);
            first = temp;
        }
        size++;
    }

    private void addLast(E value) {
        Node temp = new Node<E>(value);

        if (first == null) {
            first = last = temp;
        }
        else {
            temp.setPrevious(last);
            temp.setNext(null);
            last.setNext(temp);
            last = temp;
        }
        size++;
    }

    private void addToMiddle(int index, E value) {
        Node temp = new Node<E>(value);
        current = get(index);
        temp.setPrevious(current.getPrevious());
        temp.setNext(current);
        current.setPrevious(temp);
        size++;
    }

    public void add(int index, E value) {
        try {
            if (index == 0) {
                addFirst(value);
            }
            else if (index == size - 1) {
                addLast(value);
            }
            else {
                addToMiddle(index, value);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void add(E value) {
        addLast(value);
    }

    public void remove(int index) {
        try {
            if (index == 0) {
                removeFirst();
            }
            else if (index == size - 1) {
                removeLast();
            }
            else {
                removeFromMiddle(index);
            }
            size--;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void removeFromMiddle(int index) {
        current = get(index);
        if (current != null) {
            current.getPrevious().setNext(current.getNext());
        }
        current.getNext().setPrevious(current.getPrevious());
    }

    private void removeLast() {
        last = last.getPrevious();
    }

    private void removeFirst() {
        first = first.getNext();
    }

    public LinkedListIterator iterator() {
        return new LinkedListIterator<E>(this);
    }

    public Node getFirst() {
        return first;
    }

    public Node getLast() {
        return last;
    }

    public int getSize() {
        return size;
    }

    public boolean addAll(Collection<E> c) {
        try {
            for (E value: c) {
                add(value);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void removeAll() {
        for (int i = 0; i < size; i++) {
            remove(0);
        }
    }

    public boolean copy(Collection c) {
        removeAll();
        return addAll(c);
    }
}
