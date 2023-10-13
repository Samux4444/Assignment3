import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> implements Iterable<T> {

    protected class Node {
        T data;
        Node prev;
        Node next;

        Node(T data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    protected class DoubleLinkedListIterator implements ListIterator<T> {
        private Node currentNode;
        private Node lastAccessed;

        public DoubleLinkedListIterator() {
            this.currentNode = head;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastAccessed = currentNode;
            T data = currentNode.data;
            currentNode = currentNode.next;
            return data;
        }

        @Override
        public boolean hasPrevious() {
            return currentNode != null && currentNode.prev != null;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            currentNode = currentNode.prev;
            lastAccessed = currentNode;
            return currentNode.data;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(T e) {
            if (lastAccessed == null) throw new IllegalStateException();
            lastAccessed.data = e;
        }

        @Override
        public void add(T e) {
            throw new UnsupportedOperationException();
        }
    }

    protected Node head;
    protected Node tail;
    protected int size;

    public BasicDoubleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public BasicDoubleLinkedList<T> addToFront(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
        return this;
    }

    public BasicDoubleLinkedList<T> addToEnd(T data) {
        Node newNode = new Node(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        return this;
    }

    public T getFirst() {
        if (head == null) {
            return null;
        }
        return head.data;
    }

    public T getLast() {
        if (tail == null) {
            return null;
        }
        return tail.data;
    }

    public int getSize() {
        return size;
    }

    public ArrayList<T> toArrayList() {
        ArrayList<T> list = new ArrayList<>();
        Node current = head;
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }
        return list;
    }

    public T retrieveFirstElement() {
        if (head == null) {
            return null;
        }
        T data = head.data;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return data;
    }

    public T retrieveLastElement() {
        if (tail == null) {
            return null;
        }
        T data = tail.data;
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return data;
    }

    public BasicDoubleLinkedList<T> remove(T target, Comparator<T> comparator) {
        Node current = head;

        while (current != null) {
            if (comparator.compare(current.data, target) == 0) {
                if (current == head) {
                    retrieveFirstElement();
                } else if (current == tail) {
                    retrieveLastElement();
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    size--;
                }
                break;
            }
            current = current.next;
        }

        return this;
    }

    @Override
    public ListIterator<T> iterator() {
        return new DoubleLinkedListIterator();
    }

}
