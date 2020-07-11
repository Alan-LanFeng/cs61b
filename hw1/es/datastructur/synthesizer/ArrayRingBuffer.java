package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.

        if(isFull()) {
            throw new RuntimeException("Ring Buffer overflow");
        }
        fillCount++;
        rb[last] = x;
        last = add(last);
        return;
    }

    private int add(int curIndex) {
        if (curIndex == capacity()-1) {
            return 0;
        }
        return curIndex+1;
    }

//    private int minus(int curIndex) {
//        if (curIndex == 0) {
//            return capacity()-1;
//        }
//        return curIndex-1;
//    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {

        if(isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        fillCount--;
        int curFirst = first;
        first = add(first);
        return rb[curFirst];
    }

    @Override
    public boolean equals(Object other) {
        // Compare to itself.
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        ArrayRingBuffer<T> o = (ArrayRingBuffer<T>) other;
        if (o.fillCount() != this.fillCount()) {
            return false;
        }
        Iterator<T> thisIterator = this.iterator();
        Iterator<T> otherIterator = o.iterator();
        while (thisIterator.hasNext() && otherIterator.hasNext()) {
            if (thisIterator.next() != otherIterator.next()) {
                return false;
            }
        }
        return true;

    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.

        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
    private class ArrayRingBufferIterator implements Iterator<T> {
        private int wizPos;

        public ArrayRingBufferIterator() {
            wizPos = first;
        }

        @Override
        public boolean hasNext() {
            return wizPos < fillCount;
        }

        @Override
        public T next() {
            T returnItem = rb[wizPos];
            wizPos = add(wizPos);
            return returnItem;
        }
    }
}

