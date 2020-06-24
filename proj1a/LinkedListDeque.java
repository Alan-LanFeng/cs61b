public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    private class Node {
        private Node prev;
        private T item;
        private Node next;
        private Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(T item) {
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(item, sentinel.next, sentinel.prev);
        size = 1;
    }

    public void addFirst(T item) {
        size += 1;
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
    }

    public void addLast(T item) {
        size += 1;
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = new Node(sentinel.next.item, sentinel, sentinel.next.next);
        while (p.next != sentinel.next) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.print("\n");
    }

    public T removeFirst() {
        if (size != 0) {
            sentinel.next = sentinel.next.next;
            sentinel.next.next.prev = sentinel.next;
            size--;
            return sentinel.next.item;
        }
        return null;
    }

    public T removeLast() {
        if (size != 0) {
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.prev.next = sentinel.prev;
            size--;
            return sentinel.prev.item;
        }
        return null;
    }

    public T get(int index) {
        Node p = new Node(sentinel.next.item, sentinel.next.prev, sentinel.next.next);
        while (index > 0) {
            index--;
            p = p.next;
        }
        return  p.item;
    }

    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
        for (int i = 0; i < other.size; i++) {
            addLast((T) other.get(i));
        }
    }

    private T helper(Node p, int index) {
        while (index != 0) {
            index--;
            return helper(p.next, index);
        }
        return p.item;
    }

    public T getRecursive(int index) {
        Node p = new Node(sentinel.next.item, sentinel.next.prev, sentinel.next.next);
        return helper(p, index);
    }
}
