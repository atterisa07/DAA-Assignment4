
import java.io.File;
import java.io.PrintWriter;
import java.util.Random;

public class Experiment {

    static Random random = new Random(42);

    static String generate(int n) {
        String s = "";
        for (int i = 0; i < n; i++) {
            s += (char) ('a' + random.nextInt(3));
        }
        return s;
    }

    static void save(PrintWriter out, String algorithm,
                     String input, int n, long start,
                     long operations, int run) {

        double time = (System.nanoTime() - start) / 1000000.0;

        out.printf(java.util.Locale.US,
                "%s,%s,%d,%.6f,%d,%d%n",
                algorithm, input, n, time, operations, run);
    }

    public static void main(String[] args) throws Exception {

        new File("results").mkdirs();

        PrintWriter out = new PrintWriter("results/results.csv");
        out.println("algorithm,input,n,time_ms,operations,run");

        RecursiveEditDistance recursive = new RecursiveEditDistance();
        TableEditDistance table = new TableEditDistance();
        TwoRowsEditDistance twoRows = new TwoRowsEditDistance();
        NaiveSearch naive = new NaiveSearch();
        KMPSearch kmp = new KMPSearch();

        int[] dpSizes = {2, 4, 6, 8};
        int[] searchSizes = {1000, 2000, 4000, 8000};

        for (int n : dpSizes) {
            for (String type : new String[]{"random", "different"}) {
                for (int run = 1; run <= 3; run++) {

                    String a = generate(n);
                    String b = type.equals("random")
                            ? generate(n) : "b".repeat(n);

                    if (type.equals("different")) {
                        a = "a".repeat(n);
                    }

                    long start = System.nanoTime();
                    recursive.distance(a, b);
                    save(out, "Recursive", type, n,
                            start, recursive.operations, run);

                    start = System.nanoTime();
                    table.distance(a, b);
                    save(out, "Table", type, n,
                            start, table.operations, run);

                    start = System.nanoTime();
                    twoRows.distance(a, b);
                    save(out, "TwoRows", type, n,
                            start, twoRows.operations, run);
                }
            }
        }

        for (int n : searchSizes) {
            for (String type : new String[]{"random", "worst"}) {
                for (int run = 1; run <= 3; run++) {

                    String text = type.equals("random")
                            ? generate(n) : "a".repeat(n);

                    String pattern = type.equals("random")
                            ? "abc" : "aaaab";

                    long start = System.nanoTime();
                    naive.search(text, pattern);
                    save(out, "Naive", type, n,
                            start, naive.comparisons, run);

                    start = System.nanoTime();
                    kmp.search(text, pattern);
                    save(out, "KMP", type, n,
                            start, kmp.comparisons, run);
                }
            }
        }

        out.close();
        System.out.println("Experiment finished!");
        System.out.println("Results saved to results/results.csv");
    }
}
