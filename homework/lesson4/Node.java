package homework.lesson4;

/**
 * Created by Anatoly on 15.09.2016.
 */
public class Node<T> {
    private T value;
    private Node next;
    private Node previous;

    public Node(T value) {
        this.value = value;
        next = null;
        previous = null;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
