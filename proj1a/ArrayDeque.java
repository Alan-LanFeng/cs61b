public class ArrayDeque<Item> {
    private Item[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    /** Creates an empty list. */
    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        Item[] newDeque = (Item[]) new Object[capacity];
        int first = plusOne(nextFirst); // the index of the first item in original deque
        for (int newIndex = 0; newIndex < size; newIndex++) {
            newDeque[newIndex] = items[first];
            first = plusOne(first);
        }
        items = newDeque;
        nextFirst = capacity - 1; // since the new deque is starting from true 0 index.
        nextLast = size;
    }

    private int minusOne(int index) {
        if (index == 0) {
            return items.length-1;
        }
        return index-1;
    }

    private int plusOne(int index) {
        if (index == items.length-1) {
            return 0;
        }
        return index+1;
    }

    public void addFirst(Item item) {

        if (items.length == size) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size+=1;
    }

    public void addLast(Item item) {

        if (items.length == size) {
            resize(size * 2);
        }

        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size+=1;
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
        for (int i = plusOne(nextFirst); i != nextLast; i = plusOne(i)) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public Item removeFirst() {
        if (size<(items.length / 4) && items.length>16) {
            resize(items.length / 2);
        }
        nextFirst = plusOne(nextFirst);
        Item toRemove = items[nextFirst];
        items[nextFirst] = null;
        if (!isEmpty()) {
            size -= 1;
        }
        return toRemove;
    }

    public Item removeLast() {
        if (size<(items.length / 4) && items.length>16) {
            resize(items.length / 2);
        }
        nextLast = minusOne(nextLast);
        Item toRemove = items[nextLast];
        items[nextLast] = null;
        if (!isEmpty()) {
            size -= 1;
        }
        return toRemove;
    }

    public Item get(int index) {
        if (index >= size) {
            return null;
        }
        int start = plusOne(nextFirst);
        return items[(start + index) % items.length];
    }

    public Item getLast() {
        return items[minusOne(nextLast)];
    }

    public ArrayDeque(ArrayDeque other) {
        items = (Item[]) new Object[other.size];
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        size = other.size;

        System.arraycopy(other.items, 0, items, 0, other.size);
    }


}
