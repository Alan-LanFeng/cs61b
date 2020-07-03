import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    @Test
    public void testIsPalindrome() {
        String a = "persiflage";
        String b = "noon";
        String c = "";
        String d = "a";
        assertFalse(palindrome.isPalindrome(a));
        assertTrue(palindrome.isPalindrome(b));
        assertTrue(palindrome.isPalindrome(c));
        assertTrue(palindrome.isPalindrome(d));
    }

    public void testOffByOne() {
        CharacterComparator cc = new OffByOne();
        String a = "edged";
        String b = "Flake";
        assertTrue(palindrome.isPalindrome(a, cc));
        assertFalse(palindrome.isPalindrome(b, cc));

    }
    public void testOffByN() {
        CharacterComparator cc = new OffByN(3);
        String a = "adggd";
        String b = "Flake";
        assertTrue(palindrome.isPalindrome(a, cc));
        assertFalse(palindrome.isPalindrome(b, cc));

    }
}
