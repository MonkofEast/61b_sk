public class ArrayDeque<T> {
    /** properties */
    public T[] items;
    public int size;
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
        nextFirst = 3;
        nextLast = 4;
    }
    /** deep copy */
    public ArrayDeque ArrayDeque(ArrayDeque other) {
        ArrayDeque res = new ArrayDeque();
        res.size = other.size;
        res.nextFirst = other.nextFirst;
        res.nextLast = other.nextLast;
        res.items = (T []) new Object[res.size];
        System.arraycopy(other.items, 0, res.items, 0, other.size);
        return res;
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
    // fnd how many items are on the right of startFrom = nextFirst - 1
    private int getPrevRightNum(int nextFirst, int nextLast, int prevLen, int startFrom) {
        if (nextFirst - nextLast >= -1) {
            // "nextFirst - nextLast == -1" means the array is full
            return prevLen - startFrom;
        }
        else {
            return nextLast - nextFirst;
        }
    }
    private void resize(int toCapacity) {
        // change capacity, add or reduce
        T[] newItems = (T []) new Object[toCapacity];
        // copy items, concentrate on left/right of startFrom
        int prevLen = items.length;
        int newLen = newItems.length;
        int startFrom = circulate(nextFirst, 1, prevLen);
        int prevRightNum = getPrevRightNum(nextFirst, nextLast, prevLen, startFrom);
        int prevLeftNum = size - prevRightNum;
        System.arraycopy(items, startFrom, newItems, (newLen - prevRightNum),
                prevRightNum); // copy items on the right of startFrom
        if (newLen > prevLen) {
            // if halve items, no need to copy items to the left half
            System.arraycopy(items, 0, newItems, 0,
                    prevLeftNum); // copy items on the left of startFrom
        }
        // update startFrom & nextFirst & nextLast & items
        startFrom = (newLen - prevRightNum);
        nextFirst = startFrom - 1;
        nextLast = prevLeftNum;
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
        if (index > size - 1 || index < 0) {
            return null;
        }
        int startFrom = circulate(nextFirst, 1, items.length);
        return items[circulate(startFrom, index, items.length)];
    }
    /** print all items */
    public void printDeque() {
        int currIdx = circulate(nextFirst, 1, items.length);
        while (items[currIdx] != null) {
            System.out.print(items[currIdx]);
            System.out.print(" ");
            currIdx = circulate(currIdx, 1, items.length);
        }
        System.out.println();
    }
    /** add to both sides */
    // add to first
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * REFACTOR);
        }
        items[nextFirst] = item;
        nextFirst = circulate(nextFirst, -1, items.length);
        size += 1;
    }
    // add to last\
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * REFACTOR);
        }
        items[nextLast] = item;
        nextLast = circulate(nextLast, 1, items.length);
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
        if (R <= Rbar) {
            resize(items.length/REFACTOR);
        }
        // save popped item & null out popped location
        int startFrom = circulate(nextFirst, 1, items.length);
        T pop = items[startFrom];
        items[startFrom] = null;
        // update nextFirst & startFrom & size
        nextFirst = startFrom;
        size -= 1;

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
        if (R <= Rbar) {
            resize(items.length/REFACTOR);
        }
        // save popped item & null out popped location
        int endWith = circulate(nextLast, -1, items.length);
        T pop = items[endWith];
        items[endWith] = null;
        // update nextLast & startFrom & size
        nextLast = endWith;
        size -= 1;

        return pop;
    }
}
