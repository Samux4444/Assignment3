import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
    private Comparator<T> comparator;

    public SortedDoubleLinkedList(Comparator<T> comparator) {
        super();
        this.comparator = comparator;
    }

    public void add(T data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            Node current = head;
            while (current != null && comparator.compare(data, current.data) > 0) {
                current = current.next;
            }
            if (current == null) { // End of the list
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            } else if (current.prev == null) { // Start of the list
                head.prev = newNode;
                newNode.next = head;
                head = newNode;
            } else {
                newNode.prev = current.prev;
                newNode.next = current;
                current.prev.next = newNode;
                current.prev = newNode;
            }
        }
        size++;
    }

    @Override
    public BasicDoubleLinkedList<T> addToFront(T data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BasicDoubleLinkedList<T> addToEnd(T data) {
        throw new UnsupportedOperationException("Invalid operation for sorted list");
    }

    
    private class NodeIterator implements ListIterator<T> {
        private Node current;
        public NodeIterator() {
            current = head;
        }

        public boolean hasNext() {
            return current != null;
        }

        
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T item = current.data;
            current = current.next;
            return item;
        }

        
        public boolean hasPrevious() {
            return current != null && current.prev != null;
        }

        
        public T previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
            current = current.prev;
            return current.data;
        }

     
        
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        
        public void remove() {
            throw new UnsupportedOperationException();
        }

        
        public void set(T t) {
            throw new UnsupportedOperationException();
        }

        
        public void add(T t) {
            throw new UnsupportedOperationException();
        }
    }

    
    
    public ListIterator<T> iterator() {
        return new NodeIterator();
    }

    
}
