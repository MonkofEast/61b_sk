public class LinkedListDeque<T> {
    /** nested class */
    private class TNode {
        // properties
        public T item;
        public TNode prev;
        public TNode next;
        /** constructor */
        public TNode(T i, TNode p, TNode n) {
            item = i;
            prev = p;
            next = n;
        }
        /** deep copy */
        public TNode TNode() {
            TNode copy = new TNode(this.item, this.prev, this.next);
            return copy;
        }
    }
    /** properties */
    private int size;
    private TNode sentinel;
    /** constructor */
    public LinkedListDeque() {
        size = 0;
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }
    /** deep copy (new a obj) */
    public LinkedListDeque LinkedListDeque(LinkedListDeque other) {
        LinkedListDeque res = new LinkedListDeque();
        TNode otherTmpNode = other.sentinel;
        TNode thisTmpNode = res.sentinel;
        while (otherTmpNode.next != other.sentinel) {
            TNode addNode = otherTmpNode.next.TNode(); // copy a node
            addNode.next = null;
            addNode.prev = thisTmpNode;
            thisTmpNode.next = addNode;
            otherTmpNode = otherTmpNode.next;
            thisTmpNode = thisTmpNode.next;
        }
        thisTmpNode.next = res.sentinel;
        res.sentinel.prev = thisTmpNode;
        res.size = other.size;
        return res;
    }
    /** adding to both side */
    public void addFirst(T item) {
        TNode addNode = new TNode(item, sentinel, sentinel.next);
        sentinel.next.prev = addNode;
        sentinel.next = addNode;
        size += 1;
    }
    public void addLast(T item) {
        TNode addNode = new TNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = addNode;
        sentinel.prev = addNode;
        size += 1;
    }
    /** check empty */
    public boolean isEmpty() {
        if (sentinel.prev == sentinel) {
            return true;
        }
        return false;
    }
    /** get size */
    public int size() {
        return size;
    }
    /** size can't be negative */
    public void checkSize() {
        if (size < 0) {
            size = 0;
        }
    }
    /** print */
    public void printDeque() {
        TNode tmpNode = sentinel;
        if (this.isEmpty()) {
            System.out.println("It's empty!");
            return;
        }
        while (tmpNode.next != sentinel) {
            System.out.print(tmpNode.next.item);
            System.out.print(" ");
            tmpNode = tmpNode.next;
        }
        System.out.println();
    }
    /** remove from both sides */
    public T removeFirst() {
        T res = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        this.checkSize();
        return res;
    }
    public T removeLast() {
        TNode resNode = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        this.checkSize();
        return resNode.item;
    }
    /** get the item at given idx from 0 */
    // if not exist, return null
    // iter //
    public T get(int index) {
        TNode tmpNode = sentinel.next;
        for (int i = 0; i < this.size(); i += 1) {
            if (i == index) {
                return tmpNode.item;
            }
            tmpNode = tmpNode.next;
        }
        return null;
    }
    // recur //
    // 1. helper
    private T getRecur(TNode sentinel, int index) {
        if (index == 0) {
            return sentinel.next.item;
        }
        return getRecur(sentinel.next, index - 1);
    }
    // 2. caller
    public T getRecursive(int index) {
        return getRecur(sentinel, index);
    }
    /** test by main */
//    public static void main(String[] args) {
//        /** set benchmark */
//        LinkedListDeque<String> bench = new LinkedListDeque<>();
//        /** check methods while empty */
//        // System.out.println("Empty Check");
//        LinkedListDeque<String> copy = bench.LinkedListDeque(bench);
//        /*
//        System.out.println(bench.isEmpty());
//        System.out.println(copy.isEmpty());
//        System.out.println(bench.size());
//        System.out.println(copy.size());
//        bench.printDeque();
//        copy.printDeque();
//        System.out.println(bench.removeFirst());
//        System.out.println(copy.removeFirst());
//        System.out.println(bench.removeLast());
//        System.out.println(copy.removeLast());
//        System.out.println(bench.get(2));
//        System.out.println(copy.get(2));
//        System.out.println(bench.getRecursive(2));
//        System.out.println(bench.getRecursive(2));
//        System.out.println("***********");
//
//        */
//        /** check adding */
//        //System.out.println("Adding Check");
//        bench.addFirst("one");
//        bench.addLast("two");
//        /*
//        System.out.println(bench.isEmpty());
//        System.out.println(copy.isEmpty());
//        System.out.println(bench.size());
//        System.out.println(copy.size());
//        copy = bench.LinkedListDeque(bench);
//        System.out.println(copy.isEmpty());
//        System.out.println(copy.size());
//        bench.printDeque();
//        copy.printDeque();
//        System.out.println("***********");
//
//         */
//        /** check get & remove */
//        System.out.println("Get/Remove Check");
//        bench.addLast("three");
//        bench.printDeque();
//        System.out.println(bench.get(0));
//        System.out.println(bench.get(1));
//        System.out.println(bench.get(2));
//        System.out.println(bench.get(3));
//        System.out.println(bench.getRecursive(0));
//        System.out.println(bench.getRecursive(1));
//        System.out.println(bench.getRecursive(2));
//        System.out.println(bench.getRecursive(3));
//        System.out.println(bench.removeFirst());
//        System.out.println(bench.removeLast());
//        System.out.println(bench.size());
//        System.out.println("****************");
//    }
}
