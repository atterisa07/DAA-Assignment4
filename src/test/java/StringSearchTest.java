
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class StringSearchTest {

    NaiveSearch naive = new NaiveSearch();
    KMPSearch kmp = new KMPSearch();

    @Test
    void lpsTests() {
        assertArrayEquals(
                new int[]{0, 1, 2, 0, 1, 2, 3, 3},
                kmp.lps("AAACAAAA")
        );

        assertArrayEquals(
                new int[]{0, 0, 1, 2, 0, 1, 2, 3, 4},
                kmp.lps("ABABCABAB")
        );
    }

    @Test
    void referenceTests() {
        check("ABABDABACDABABCABAB", "ABABCABAB",
                List.of(10));

        check("AAAAA", "AA",
                List.of(0, 1, 2, 3));

        check("ABABABA", "ABA",
                List.of(0, 2, 4));
    }

    void check(String text, String pattern, List<Integer> expected) {
        assertEquals(expected, naive.search(text, pattern));
        assertEquals(expected, kmp.search(text, pattern));
    }

    @Test
    void edgeCases() {
        check("abc", "abcdef", List.of());
        check("abc", "xyz", List.of());
        check("", "a", List.of());

        assertThrows(IllegalArgumentException.class,
                () -> naive.search("abc", ""));

        assertThrows(IllegalArgumentException.class,
                () -> kmp.search("abc", ""));
    }

    @Test
    void randomTests() {
        Random random = new Random(42);

        for (int t = 0; t < 100; t++) {
            String text = "";
            String pattern = "";

            for (int i = 0; i < 20; i++) {
                text += (char) ('A' + random.nextInt(3));
            }

            for (int i = 0; i < 3; i++) {
                pattern += (char) ('A' + random.nextInt(3));
            }

            assertEquals(
                    naive.search(text, pattern),
                    kmp.search(text, pattern)
            );
        }
    }
}
