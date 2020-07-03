public class ArrayDeque<Item> implements Deque<Item> {
    private Item[] Ts;
    private int size;
    private int nextFirst;
    private int nextLast;
    /** Creates an empty list. */

    public ArrayDeque() {
        Ts = (Item[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        Item[] newDeque = (Item[]) new Object[capacity];
        int first = plusOne(nextFirst); // the index of the first Item in original deque
        for (int newIndex = 0; newIndex < size; newIndex++) {
            newDeque[newIndex] = Ts[first];
            first = plusOne(first);
        }
        Ts = newDeque;
        nextFirst = capacity - 1; // since the new deque is starting from true 0 index.
        nextLast = size;
    }

    private int minusOne(int index) {
        if (index == 0) {
            return Ts.length-1;
        }
        return index-1;
    }

    private int plusOne(int index) {
        if (index == Ts.length-1) {
            return 0;
        }
        return index+1;
    }

    @Override
    public void addFirst(Item item) {

        if (Ts.length == size) {
            resize(size * 2);
        }
        Ts[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size+=1;
    }

    @Override
    public void addLast(Item item) {

        if (Ts.length == size) {
            resize(size * 2);
        }

        Ts[nextLast] = item;
        nextLast = plusOne(nextLast);
        size+=1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = plusOne(nextFirst); i != nextLast; i = plusOne(i)) {
            System.out.print(Ts[i] + " ");
        }
        System.out.println();
    }

    @Override
    public Item removeFirst() {
        if (size<(Ts.length / 4) && Ts.length>16) {
            resize(Ts.length / 2);
        }
        nextFirst = plusOne(nextFirst);
        Item toRemove = Ts[nextFirst];
        Ts[nextFirst] = null;
        if (!isEmpty()) {
            size -= 1;
        }
        return toRemove;
    }

    @Override
    public Item removeLast() {
        if (size<(Ts.length / 4) && Ts.length>16) {
            resize(Ts.length / 2);
        }
        nextLast = minusOne(nextLast);
        Item toRemove = Ts[nextLast];
        Ts[nextLast] = null;
        if (!isEmpty()) {
            size -= 1;
        }
        return toRemove;
    }

    @Override
    public Item get(int index) {
        if (index >= size) {
            return null;
        }
        int start = plusOne(nextFirst);
        return Ts[(start + index) % Ts.length];
    }
}
