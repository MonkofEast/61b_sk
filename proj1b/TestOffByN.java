import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    public int N;

    @Test
    public void testOffByN () {
        N = 5;
        CharacterComparator offByN = new OffByN(N);

        assertTrue(offByN.equalChars('a', 'f'));  // true
        assertTrue(offByN.equalChars('f', 'a'));  // true
        assertTrue(offByN.equalChars('e', 'j'));  // true
        assertTrue(offByN.equalChars('k', 'p'));  // true
        assertTrue(offByN.equalChars('s', 'x'));  // true
        assertFalse(offByN.equalChars('s', 'a'));  // true
        assertFalse(offByN.equalChars('s', 'f'));  // true

        int minLength = 0;
        In in = new In("words.txt");
        Palindrome palindrome = new Palindrome();

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, offByN)) {
                System.out.println(word);
            }
        }
    }
}
