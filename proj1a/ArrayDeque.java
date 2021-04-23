public class ArrayDeque<T> {
    /** properties */
    public T[] items;
    public int size;
    private int startFrom; // where does the logical array starts
    private int nextFirst;
    private int nextLast;
    private static int REFACTOR = 2;
    private double R;
    private static double Rbar = 0.25;
    /** construct */
    // empty
    public ArrayDeque() {
        size = 0;
        items = (T []) new Object[8];
        startFrom = -1;
        nextFirst = 3;
        nextLast = 4;
    }
    /** deep copy */
    public ArrayDeque ArrayDeque(ArrayDeque other) {
        ArrayDeque res = new ArrayDeque();
        res.size = other.size;
        res.nextFirst = other.nextFirst;
        res.nextLast = other.nextLast;
        res.startFrom = other.startFrom;
        res.items = (T []) new Object[res.size];
        System.arraycopy(other.items, 0, res.items, 0, other.size);
        return res;
    }
    /** update startFrom */ // given up this method
    private int setStartFromByNextFirst(int prevStartFrom, int nextFirst) {
        // CAUTION: nextFirst must be true nextFirst (after updated)
        if (nextFirst == items.length) {
            return 0;
        }
        return nextFirst - 1;
    }
    /** circulate the index, assume at most 1 round */
    private int circulate(int startIdx, int deltaIdx, int capacity) {
        // CAUTION: remember to count 0th element
        int resIdx = startIdx + deltaIdx;
        if (resIdx >= capacity) { // for get()
            return resIdx - capacity;
        }
        else if (resIdx < 0) { // for resize()
            return resIdx + capacity;
        }
        else { // for resize()
            return resIdx;
        }
    }
    /** resize */
    private void resize(int toCapacity) {
        // change capacity, add or reduce
        T[] newItems = (T []) new Object[toCapacity];
        // copy items, concentrate on left/right of startFrom
        int prevLen = items.length;
        int newLen = newItems.length;
        int prevRightLen = prevLen - startFrom;
        int prevLeftLen = size - prevRightLen;
        // CAUTION
        // prevLeftLen can only be precisely 0 (halve items)
        // or bigger (double items)
        System.arraycopy(items, startFrom, newItems, (newLen - prevRightLen),
                prevRightLen); // copy items on the right of startFrom
        if (newLen > prevLen) {
            // if halve items, no need to copy items to the left half
            System.arraycopy(items, 0, newItems, 0,
                    prevLeftLen); // copy items on the left of startFrom
        }
        // update startFrom & nextFirst & nextLast & items
        startFrom = (newLen - prevRightLen);
        nextFirst = startFrom - 1;
        nextLast = prevLeftLen;
        items = newItems;
    }
    /** check empty */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    /** get size */
    public int size() {
        return size;
    }
    /** get item at given idx */
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        return items[circulate(startFrom, index, items.length)];
    }
    /** print all items */
    public void printDeque() {
        int currIdx = startFrom;
        while (items[currIdx] != null) {
            System.out.print(items[currIdx]);
            System.out.print(" ");
            currIdx = circulate(currIdx, 1, items.length);
        }
        System.out.println();
    }
    /** add to both sides */
    // add to first
    private int updateNextFirst(int prevNextFirst) {
        if (prevNextFirst == 0) {
            return (items.length - 1);
        }
        return (prevNextFirst - 1);
    }
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * REFACTOR);
        }
        items[nextFirst] = item;
        nextFirst = updateNextFirst(nextFirst);
        startFrom = setStartFromByNextFirst(startFrom, nextFirst);
        size += 1;
    }
    // add to last
    private int updateNextLast(int prevNextLast) {
        if (prevNextLast == items.length) {
            return 0;
        }
        return (prevNextLast + 1);
    }
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * REFACTOR);
        }
        items[nextLast] = item;
        nextLast = updateNextLast(nextLast);
        // only changing startFrom from -1 to positive idx is necessary
        startFrom = setStartFromByNextFirst(startFrom, nextFirst);
        size += 1;
    }
    /** remove from both sides */
    // degenerate startFrom to -1 if empty
    private int degenStartFrom(int size, int prevStartFrom) {
        if (isEmpty()) {
            return -1;
        }
        return prevStartFrom + 1;
    }
    // remove from first
    public T removeFirst() {
        // check empty
        if (isEmpty()) {
            System.out.println("It's empty!");
            return null;
        }
        // check memory
        R = (double)size/(double)items.length;
        if (R == Rbar) { // in fact, R will never smaller than 0.25
            resize(items.length/REFACTOR);
        }
        // save popped item & null out popped location
        T pop = items[startFrom];
        items[startFrom] = null;
        // update nextFirst & startFrom & size
        nextFirst = startFrom;
        size -= 1;
        startFrom = degenStartFrom(size, startFrom);

        return pop;
    }
    // remove from last
    public T removeLast() {
        // check empty
        if (isEmpty()) {
            System.out.println("It's empty!");
            return null;
        }
        // check memory
        R = (double)size/(double)items.length;
        if (R == Rbar) { // in fact, R will never smaller than 0.25
            resize(items.length/REFACTOR);
        }
        // save popped item & null out popped location
        T pop = items[startFrom];
        items[startFrom] = null;
        // update nextLast & startFrom & size
        nextLast = startFrom;
        size -= 1;
        startFrom = degenStartFrom(size, startFrom);

        return pop;
    }
}
