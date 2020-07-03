public class LinkedListDeque<Item> implements Deque<Item> {


    private Node sentinel;
    private int size;

    private class Node {
        private Node prev;
        private Item item;
        private Node next;
        private Node(Item i, Node p, Node n) {
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

    public LinkedListDeque(Item item) {
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(item, sentinel.next, sentinel.prev);
        size = 1;
    }

    @Override
    public void addFirst(Item item) {
        size += 1;
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
    }
    @Override
    public void addLast(Item item) {
        size += 1;
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node p = new Node(sentinel.next.item, sentinel, sentinel.next.next);
        while (p.next != sentinel.next) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.print("\n");
    }

    @Override
    public Item removeFirst() {
        Item toRemove = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        if (!isEmpty()) {
            size -= 1;
        }
        return toRemove;
    }

    @Override
    public Item removeLast() {
        Item toRemove = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        if (!isEmpty()) {
            size -= 1;
        }
        return toRemove;
    }

    @Override
    public Item get(int index) {
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
            addLast((Item) other.get(i));
        }
    }

    private Item helper(Node p, int index) {
        while (index != 0) {
            index--;
            return helper(p.next, index);
        }
        return p.item;
    }

    public Item getRecursive(int index) {
        Node p = new Node(sentinel.next.item, sentinel.next.prev, sentinel.next.next);
        return helper(p, index);
    }
}
