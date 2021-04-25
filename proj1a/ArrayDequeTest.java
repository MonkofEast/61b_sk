
public class ArrayDequeTest {
    public static class helpers {
        private static boolean isEqual(int a, int b) {
            if (a == b) {
                return true;
            }
            return false;
        }

        private static void printRes(int expect, int actual, boolean isRight, String topic) {
            if (isRight) {
                System.out.println("In test of " + topic + ", result is GOOD");
                return;
            }
            System.out.println("In test of " + topic + ", result is BAD");
            System.out.print("Expecting ");
            System.out.print(expect);
            System.out.print(", resulting ");
            System.out.println(actual);
            return;
        }

        public static void assertEquals(int a, int b, String topic) {
            boolean isRight = isEqual(a, b);
            printRes(a, b, isRight, topic);
        }
    }

    public static void testEmptySize() {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        helpers.assertEquals(0, L.size(), "empty()");
    }


    public static void testAddAndSize() {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        L.addLast(99);
        L.addLast(98);
        L.addFirst(100);
        L.addFirst(101);
        helpers.assertEquals(4, L.size(), "add() and size()");
    }



    public static void testAddAndGetLast() {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        L.addLast(99);
        int res = L.get(L.size() - 1);
        helpers.assertEquals(99, res, "add() & get()");
        L.addLast(36);
        res = L.get(L.size() - 1);
        helpers.assertEquals(36, res, "add() & get()");
    }



    public static void testGet() {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        L.addLast(99);
        int res = L.get(0);
        helpers.assertEquals(99, res, "get()");
        L.addLast(36);
        res = L.get(0);
        helpers.assertEquals(99, res, "get()");
        res = L.get(1);
        helpers.assertEquals(36, res, "get()");
    }



    public static void testRemove() {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        L.addLast(99);
        int res = L.get(0);
        helpers.assertEquals(99, res, "addLast()");
        L.addLast(36);
        res = L.get(0);
        helpers.assertEquals(99, res, "addLast()");
        int ret = L.removeLast();
        res = L.get(L.size() - 1);
        helpers.assertEquals(99, res, "removeLast()");
        helpers.assertEquals(36, ret, "removeLast()");
        L.addLast(100);
        res = L.get(L.size() - 1);
        helpers.assertEquals(100, res, "addLast()");
        helpers.assertEquals(2, L.size(), "size()");
        L.addFirst(98);
        ret = L.removeFirst();
        res = L.get(L.size() - 1);
        helpers.assertEquals(98, ret, "removeFirst()");
        helpers.assertEquals(100, res, "removeFirst()");
        helpers.assertEquals(2, L.size(), "size()");
    }

    /** Tests insertion of a large number of items.*/

    public static void testMegaInsert() {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        int N = 1000000;
        for (int i = 0; i < N; i += 1) {
            L.addLast(i);
        }

        for (int i = 0; i < N; i += 1) {
            L.addLast(L.get(i));
        }
    }

    public static void testPrintAndCopy() {
        ArrayDeque<Integer> L1 = new ArrayDeque<>();
        ArrayDeque<Integer> L2 = new ArrayDeque<>();
        int N = 10;
        for (int i = 0; i < 10; i += 1) {
            L1.addLast(i);
        }
        L2 = L1;
        L1.printDeque();
        L2.printDeque();
    }

    public static void main(String[] args) {
//        testEmptySize();
//        testAddAndSize();
//        testAddAndGetLast();
//        testGet();
//        testRemove();
//        testMegaInsert();
        testPrintAndCopy();
    }
}
