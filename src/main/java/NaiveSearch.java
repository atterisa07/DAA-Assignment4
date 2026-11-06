
import java.util.ArrayList;
import java.util.List;

public class NaiveSearch {

    public long comparisons = 0;

    public List<Integer> search(String text, String pattern) {
        comparisons = 0;

        if (pattern.isEmpty()) {
            throw new IllegalArgumentException("Empty pattern");
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i <= text.length() - pattern.length(); i++) {
            int j = 0;

            while (j < pattern.length()) {
                comparisons++;

                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
                j++;
            }

            if (j == pattern.length()) {
                result.add(i);
            }
        }

        return result;
    }
}
