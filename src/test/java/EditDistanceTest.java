
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class EditDistanceTest {

    RecursiveEditDistance recursive = new RecursiveEditDistance();
    TableEditDistance table = new TableEditDistance();
    TwoRowsEditDistance twoRows = new TwoRowsEditDistance();

    void check(String a, String b, int expected) {
        assertEquals(expected, recursive.distance(a, b));
        assertEquals(expected, table.distance(a, b));
        assertEquals(expected, twoRows.distance(a, b));
    }

    @Test
    void referenceTests() {
        check("kitten", "sitting", 3);
        check("flaw", "lawn", 2);
        check("intention", "execution", 5);
    }

    @Test
    void emptyStrings() {
        check("", "", 0);
        check("", "abc", 3);
        check("abc", "", 3);
        check("abc", "abc", 0);
    }

    @Test
    void randomTests() {
        Random random = new Random(42);

        for (int t = 0; t < 100; t++) {
            String a = "";
            String b = "";

            int n = random.nextInt(5);
            int m = random.nextInt(5);

            for (int i = 0; i < n; i++) {
                a += (char) ('a' + random.nextInt(3));
            }

            for (int i = 0; i < m; i++) {
                b += (char) ('a' + random.nextInt(3));
            }

            int expected = table.distance(a, b);

            assertEquals(expected, recursive.distance(a, b));
            assertEquals(expected, twoRows.distance(a, b));
        }
    }
}
