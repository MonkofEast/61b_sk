public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        int len = word.length();
        ArrayDeque res = new ArrayDeque();
        for (int i = 0; i < len; i++) res.addLast(word.charAt(i));
        return res;
    }
    /* ugly implement of isPalindrome */
    public static String dequeToWord(Deque<Character> q, boolean reverse) {
        String res = "";
        if (reverse != true) {
            for (int i = 0; i < q.size(); i++) res += q.get(i);
            return res;
        }
        for (int i = q.size() - 1; i > -1; i--) res += q.get(i);
        return res;
    }
    /* a way I made up as a recur private helper */
    private boolean recurDeque(Deque q) {
        if (q.size() == 0 || q.size() == 1) return true;
        // this is stupid since there may be many calls to resize()
        if (q.removeFirst() == q.removeLast()) return recurDeque(q);
        return false;
    }
    public boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) return true;
        Deque q = wordToDeque(word);
        return recurDeque(q);
    }
    // overload the recur for cc
    private boolean recurDeque(Deque q, CharacterComparator cc) {
        if (q.size() == 0 || q.size() == 1) return true;
        char x = (char) q.removeLast();
        char y = (char) q.removeFirst();
        if (cc.equalChars(x, y)) return recurDeque(q, cc);
        return false;
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 0 || word.length() == 1) return true;
        Deque q = wordToDeque(word);
        return recurDeque(q, cc);
    }
}
