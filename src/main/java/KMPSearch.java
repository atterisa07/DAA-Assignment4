
import java.util.ArrayList;
import java.util.List;

public class KMPSearch {

    public long comparisons = 0;

    public int[] lps(String pattern) {
        int[] lps = new int[pattern.length()];
        int len = 0;
        int i = 1;

        while (i < pattern.length()) {
            comparisons++;

            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else if (len != 0) {
                len = lps[len - 1];
            } else {
                lps[i] = 0;
                i++;
            }
        }

        return lps;
    }

    public List<Integer> search(String text, String pattern) {
        comparisons = 0;

        if (pattern.isEmpty()) {
            throw new IllegalArgumentException("Empty pattern");
        }

        List<Integer> result = new ArrayList<>();
        int[] prefix = lps(pattern);

        int i = 0;
        int j = 0;

        while (i < text.length()) {
            comparisons++;

            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;

                if (j == pattern.length()) {
                    result.add(i - j);
                    j = prefix[j - 1];
                }
            } else if (j != 0) {
                j = prefix[j - 1];
            } else {
                i++;
            }
        }

        return result;
    }
}
