
public class Main {

    public static void main(String[] args) {

        RecursiveEditDistance recursive = new RecursiveEditDistance();
        TableEditDistance table = new TableEditDistance();
        TwoRowsEditDistance twoRows = new TwoRowsEditDistance();

        System.out.println("EDIT DISTANCE");
        System.out.println("Recursive: " +
                recursive.distance("kitten", "sitting"));
        System.out.println("Table: " +
                table.distance("kitten", "sitting"));
        System.out.println("Two Rows: " +
                twoRows.distance("kitten", "sitting"));

        NaiveSearch naive = new NaiveSearch();
        KMPSearch kmp = new KMPSearch();

        System.out.println("\nSTRING SEARCH");
        System.out.println("Naive: " +
                naive.search("ABABABA", "ABA"));
        System.out.println("KMP: " +
                kmp.search("ABABABA", "ABA"));
    }
}
