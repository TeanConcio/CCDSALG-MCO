import java.util.ArrayList;



public class CCDSALG_MCO2 {



    /* ---------- ---------- Constants ---------- ---------- */

    // Data Structure Modes
    static final int HASH_TABLE_ONLY = 0;
    static final int BINARY_SEARCH_TREE_ONLY = 1;
    static final int BOTH_DATA_STRUCTURES = 2;


    // Debug Mode
    static final boolean DEBUG_MODE = false;


    // Run Mode
    static final boolean BULK_RUN_MODE = true;
    static final int DATA_STRUCTURE_TO_RUN = BOTH_DATA_STRUCTURES;


    // Number of Repeats for Bulk Run Mode
    static final int NUMBER_OF_REPEATS = 50;

    // Number of Setbacks for Bulk Run Mode
        // Java code runs slower at the start
        // Setbacks are used to lessen the effects of the slow start
    static final int NUMBER_OF_SETBACKS = 50;


    // Length of String
    static final int STRING_LENGTH = (int) Math.pow(10, 6);

    // K-mer
    static final int KMER = 5;

    // Declare and Define String and K-mer
        // Comment out the String and K-mer you do not want to use
    // Default
    static String strDNA = "taccaccaccatag"; static int intKmer = 6;
    // Custom
    //static String strDNA = generateString(STRING_LENGTH); static int intKmer = KMER;





    /* ---------- ---------- Global Variables ---------- ---------- */

    // Build and Search Time of Data Structures
    static ArrayList<Integer> listintFNV1aHashTableCollisions = new ArrayList<>();
    static ArrayList<Double> listdblFNV1aHashTableBuildTime = new ArrayList<>();
    static ArrayList<Double> listdblFNV1aHashTableSearchTime = new ArrayList<>();

    static ArrayList<Integer> listintXXHASH32HashTableCollisions = new ArrayList<>();
    static ArrayList<Double> listdblXXHASH32HashTableBuildTime = new ArrayList<>();
    static ArrayList<Double> listdblXXHASH32HashTableSearchTime = new ArrayList<>();

    static ArrayList<Double> listdblBSTBuildTime = new ArrayList<>();
    static ArrayList<Double> listdblBSTSearchTime = new ArrayList<>();





    /* ---------- ---------- Main ---------- ---------- */

    public static void main(String[] args) {

        // Run once and Display all information
        if (!BULK_RUN_MODE)
            runOnce();


        // Display only time to use for bulk running
        else {

            // Display DNA String and K-mer
            System.out.printf("DNA String Length: %d\n", STRING_LENGTH);
            System.out.println("K-mer = " + KMER);
            
            if (DATA_STRUCTURE_TO_RUN == HASH_TABLE_ONLY ||
                    DATA_STRUCTURE_TO_RUN == BOTH_DATA_STRUCTURES) {

                // FNV1a
                runBulkHashTable(1);

                // XXHASH32
                runBulkHashTable(2);
            }

            if (DATA_STRUCTURE_TO_RUN == BINARY_SEARCH_TREE_ONLY ||
                    DATA_STRUCTURE_TO_RUN == BOTH_DATA_STRUCTURES)
                runBulkBST();

            displayDivider();
        }
    }





    /* ---------- ---------- Execution Methods ---------- ---------- */

    /**
     * runHashTable
     * - Runs the Hash Table implementation.
     *      Places the build and search time in their respective ArrayLists.
     *
     * @param strDNA The DNA Strand.
     * @param intKmer The K-mer Length.
     * @param boolAddToList Whether to add the times to the ArrayLists.
     */
    public static void runHashTable (String strDNA,
                                     int intKmer,
                                     int intHashFunction,
                                     boolean boolAddToList) {

        // Initiate Hash Table
        HashTable hashTable;

        // Initiate Time Value
        long longBuildTime;
        long longSearchTime;


        if (!BULK_RUN_MODE)
            System.out.println("Building Hash Table...");


        // Time Hash Table Setup

        // Note Start Time
        longBuildTime = System.nanoTime();

        // Run Hash Table Setup
        hashTable = new HashTable(strDNA, intKmer, intHashFunction);

        // Note End Time
        longBuildTime = System.nanoTime() - longBuildTime;


        // Initialize Output Arrays
        String[] arrstrDNAStrands = new String[hashTable.getIntNodeCount()];
        int[] arrintStrandCounts = new int[hashTable.getIntNodeCount()];


        // Time Hash Table Search

        // Note Start Time
        longSearchTime = System.nanoTime();

        // Run Hash Table Setup
        hashTable.getStrandAndCountArray(arrstrDNAStrands, arrintStrandCounts);

        // Note End Time
        longSearchTime = System.nanoTime() - longSearchTime;


        // Add Times to List
        if (boolAddToList) {

            switch (intHashFunction) {

                case 1:
                    listintFNV1aHashTableCollisions.add(hashTable.getIntCollisionCount());
                    listdblFNV1aHashTableBuildTime.add(1.0 * longBuildTime / 1_000);
                    listdblFNV1aHashTableSearchTime.add(1.0 * longSearchTime / 1_000);
                    break;

                case 2:
                    listintXXHASH32HashTableCollisions.add(hashTable.getIntCollisionCount());
                    listdblXXHASH32HashTableBuildTime.add(1.0 * longBuildTime / 1_000);
                    listdblXXHASH32HashTableSearchTime.add(1.0 * longSearchTime / 1_000);
                    break;
            }

        }


        // If not Bulk Run
        if (!BULK_RUN_MODE) {

            // Display K-mer Distribution Table
            System.out.println("\nHash Table Implementation Results");
            hashTable.displayDetails();
            displayKmerDistribution(intKmer, arrstrDNAStrands, arrintStrandCounts);
        }
    }



    /**
     * runBinarySearchTree
     * - Runs the Binary Search Tree implementation.
     *      Places the build and search time in their respective ArrayLists.
     *
     * @param strDNA The DNA Strand.
     * @param intKmer The K-mer Length.
     * @param boolAddToList Whether to add the times to the ArrayLists.
     */
    public static void runBinarySearchTree (String strDNA,
                                            int intKmer,
                                            boolean boolAddToList) {

        // Initiate Binary Search Tree
        BinarySearchTree treeBST;

        // Initiate Time Value
        long longBuildTime;
        long longSearchTime;

        if (!BULK_RUN_MODE)
            System.out.println("Building Binary Search Tree...");


        // Time Hash Table Setup

        // Note Start Time
        longBuildTime = System.nanoTime();

        // Run Hash Table Setup
        treeBST = new BinarySearchTree(strDNA, intKmer);

        // Note End Time
        longBuildTime = System.nanoTime() - longBuildTime;


        // Initialize Output Arrays
        String[] arrstrDNAStrands = new String[treeBST.getIntNodeCount()];
        int[] arrintStrandCounts = new int[treeBST.getIntNodeCount()];


        // Time Hash Table Search

        // Note Start Time
        longSearchTime = System.nanoTime();

        // Run Hash Table Setup
        treeBST.getStrandAndCountArray(arrstrDNAStrands, arrintStrandCounts);

        // Note End Time
        longSearchTime = System.nanoTime() - longSearchTime;


        // Add Times to List
        if (boolAddToList) {

            listdblBSTBuildTime.add(1.0 * longBuildTime / 1_000);
            listdblBSTSearchTime.add(1.0 * longSearchTime / 1_000);
        }


        // If not Bulk Run
        if (!BULK_RUN_MODE) {

            // Display K-mer Distribution Table
            System.out.println("\nBinary Search Tree Implementation Results");
            treeBST.displayDetails();
            displayKmerDistribution(intKmer, arrstrDNAStrands, arrintStrandCounts);
        }
    }





    /* ---------- ---------- Run Modes ---------- ---------- */

    /**
     * runOnce
     * - Runs the program once.
     *      Displays all information.
     */
    public static void runOnce () {

        // Display DNA String and K-mer
        System.out.printf("DNA Sequence (Len = %d): %s\n",
                strDNA.length(), strDNA);
        System.out.println("K-mer = " + intKmer);

        displayDivider();

        // If Hash Table or Both Structures Mode
        if (DATA_STRUCTURE_TO_RUN == HASH_TABLE_ONLY ||
                DATA_STRUCTURE_TO_RUN == BOTH_DATA_STRUCTURES) {

            // Run FNV1a Hash Table
            runHashTable(strDNA, intKmer, 1, true);

            displayDivider();

            // Run XXHASH32 Hash Table
            runHashTable(strDNA, intKmer, 2, true);
        }


        if (DATA_STRUCTURE_TO_RUN == BOTH_DATA_STRUCTURES)
            displayDivider();

        // If BST or Both Structures Mode
        if (DATA_STRUCTURE_TO_RUN == BINARY_SEARCH_TREE_ONLY ||
                DATA_STRUCTURE_TO_RUN == BOTH_DATA_STRUCTURES)
            // Run Binary Search Tree
            runBinarySearchTree(strDNA, intKmer, true);

        displayDivider();


        // Display Runtimes

        // Hash Table
        if (DATA_STRUCTURE_TO_RUN == HASH_TABLE_ONLY ||
                DATA_STRUCTURE_TO_RUN == BOTH_DATA_STRUCTURES) {

            // FNV1a Hash Table
            System.out.printf("FNV1a Hash Table Build Time:      %10.1f microseconds\n",
                    listdblFNV1aHashTableBuildTime.get(0));
            System.out.printf("FNV1a Hash Table Search Time:     %10.1f microseconds\n",
                    listdblFNV1aHashTableSearchTime.get(0));
            System.out.printf("Total FNV1a Hash Table Time:      %10.1f microseconds\n\n",
                    listdblFNV1aHashTableBuildTime.get(0) + listdblFNV1aHashTableSearchTime.get(0));

            // XXHASH32 Hash Table
            System.out.printf("XXHASH32 Hash Table Build Time:      %10.1f microseconds\n",
                    listdblXXHASH32HashTableBuildTime.get(0));
            System.out.printf("XXHASH32 Hash Table Search Time:     %10.1f microseconds\n",
                    listdblXXHASH32HashTableSearchTime.get(0));
            System.out.printf("Total XXHASH32 Hash Table Time:      %10.1f microseconds\n\n",
                    listdblXXHASH32HashTableBuildTime.get(0) + listdblXXHASH32HashTableSearchTime.get(0));
        }

        // Binary Search Tree
        if (DATA_STRUCTURE_TO_RUN == BINARY_SEARCH_TREE_ONLY ||
                DATA_STRUCTURE_TO_RUN == BOTH_DATA_STRUCTURES) {

            System.out.printf("Binary Search Tree Build Time:      %10.1f microseconds\n",
                    listdblBSTBuildTime.get(0));
            System.out.printf("Binary Search Tree Search Time:     %10.1f microseconds\n",
                    listdblBSTSearchTime.get(0));
            System.out.printf("Total Binary Search Tree Time:      %10.1f microseconds\n\n",
                    listdblBSTBuildTime.get(0) + listdblBSTSearchTime.get(0));
        }
    }



    /**
     * runBulkHashTable
     * - Runs the Hash Table implementation multiple times.
     *      Displays the average times.
     */
    public static void runBulkHashTable (int intHashFunction) {

        boolean boolGetResults = false;

        double dblAvgCollisions = 0;

        double dblAvgBuildTime = 0;
        double dblAvgSearchTime = 0;
        double dblAvgTotalTime = 0;


        // Get Hash Function Name and Array Lists
        String strHashFunction = "";
        ArrayList<Integer> listintCollisions = new ArrayList<>();
        ArrayList<Double> listdblBuildTime = null;
        ArrayList<Double> listdblSearchTime =  null;
        switch (intHashFunction) {

            case 1:
                strHashFunction = "FNV1a";
                listintCollisions = listintFNV1aHashTableCollisions;
                listdblBuildTime = listdblFNV1aHashTableBuildTime;
                listdblSearchTime = listdblFNV1aHashTableSearchTime;
                break;

            case 2:
                strHashFunction = "XXHASH32";
                listintCollisions = listintXXHASH32HashTableCollisions;
                listdblBuildTime = listdblXXHASH32HashTableBuildTime;
                listdblSearchTime = listdblXXHASH32HashTableSearchTime;
                break;
        }

        displayDivider();

        System.out.printf("%s Hash Table Run\n\n", strHashFunction);
        
        System.out.printf("Running %d Setbacks: \n", NUMBER_OF_SETBACKS);

        for (int i = -1 * NUMBER_OF_SETBACKS; i < NUMBER_OF_REPEATS; i++) {

            // Get Results only after Setbacks
            if (i == 0) {

                boolGetResults = true;
                // Print Hash Table Trials
                System.out.printf("\nRunning %s Hash Table %d Times...\n\n",
                        strHashFunction, NUMBER_OF_REPEATS);
            }

            // Randomize DNA
            strDNA = generateString(STRING_LENGTH);

            // Run Hash Table
            runHashTable(strDNA, intKmer, intHashFunction, boolGetResults);

            // Loading
            if (!boolGetResults) {
                System.out.print("|");
                if (i == -1)
                    System.out.println();
                else if ((i+1) % 5 == 0)
                    System.out.print(" ");
            }
        }


        // Print Collision Count
        System.out.printf("%s Hash Table Collisions:\n", strHashFunction);
        for (Integer intCollision : listintCollisions) {

            System.out.printf("%d ", intCollision);
            dblAvgCollisions += intCollision;
        }
        System.out.printf("\nAverage %s Hash Table Collisions: %.1f\n\n",
                strHashFunction, dblAvgCollisions / NUMBER_OF_REPEATS);

        // Print Build Time
        System.out.printf("%s Hash Table Build Times in Microseconds:\n",
                strHashFunction);
        for (Double dblSearchTime : listdblBuildTime) {

            System.out.printf("%.1f ", dblSearchTime);
            dblAvgBuildTime += dblSearchTime;
        }
        System.out.printf("\nAverage %s Hash Table Build Time: %.3f\n\n",
                strHashFunction, dblAvgBuildTime / NUMBER_OF_REPEATS);

        // Print Search Time
        System.out.printf("%s Hash Table Search Times in Microseconds:\n",
                strHashFunction);
        for (Double dblSearchTime : listdblSearchTime) {

            System.out.printf("%.1f ", dblSearchTime);
            dblAvgSearchTime += dblSearchTime;
        }
        System.out.printf("\nAverage %s Hash Table Search Time: %.3f\n\n",
                strHashFunction, dblAvgSearchTime / NUMBER_OF_REPEATS);

        // Print Total Time
        System.out.printf("%s Hash Table Total Times in Microseconds:\n",
                strHashFunction);
        for (int i = 0; i < listdblBuildTime.size(); i++) {

            System.out.printf("%.1f ",
                    listdblBuildTime.get(i) + listdblSearchTime.get(i));
            dblAvgTotalTime += listdblBuildTime.get(i) + listdblSearchTime.get(i);
        }
        System.out.printf("\nAverage %s Hash Table Total Time: %.3f\n\n",
                strHashFunction, dblAvgTotalTime / NUMBER_OF_REPEATS);
    }



    /**
     * runBulkBST
     * - Runs the Binary Seach Tree implementation multiple times.
     *      Displays the average times.
     */
    public static void runBulkBST () {

        boolean boolGetResults = false;

        double dblAvgBuildTime = 0;
        double dblAvgSearchTime = 0;
        double dblAvgTotalTime = 0;


        displayDivider();

        System.out.println("Binary Search Tree Bulk Run\n");

        System.out.printf("Running %d Setbacks: \n", NUMBER_OF_SETBACKS);

        for (int i = -1 * NUMBER_OF_SETBACKS; i < NUMBER_OF_REPEATS; i++) {

            // Get Results only after Setbacks
            if (i == 0) {

                boolGetResults = true;
                // Print Binary Search Tree Trials
                System.out.printf("\nRunning Binary Search Tree %d Times...\n\n",
                        NUMBER_OF_REPEATS);
            }

            // Randomize DNA
            strDNA = generateString(STRING_LENGTH);

            // Run Binary Search Tree
            runBinarySearchTree(strDNA, intKmer, boolGetResults);

            // Loading
            if (!boolGetResults) {
                System.out.print("|");
                if (i == -1)
                    System.out.println();
                else if ((i+1) % 5 == 0)
                    System.out.print(" ");
            }
        }

        // Print Build Time
        System.out.println("Binary Search Tree Build Times in Microseconds:");
        for (Double dblBSTBuildTime : listdblBSTBuildTime) {

            System.out.printf("%.1f ", dblBSTBuildTime);
            dblAvgBuildTime += dblBSTBuildTime;
        }
        System.out.printf("\nAverage Binary Search Tree Build Time: %.3f\n\n",
                dblAvgBuildTime / NUMBER_OF_REPEATS);

        // Print Search Time
        System.out.println("Binary Search Tree Search Times in Microseconds:");
        for (Double dblBSTSearchTime : listdblBSTSearchTime) {

            System.out.printf("%.1f ", dblBSTSearchTime);
            dblAvgSearchTime += dblBSTSearchTime;
        }
        System.out.printf("\nAverage Binary Search Tree Search Time: %.3f\n\n",
                dblAvgSearchTime / NUMBER_OF_REPEATS);

        // Print Total Time
        System.out.println("Total Binary Search Tree Times in Microseconds:");
        for (int i = 0; i < listdblBSTSearchTime.size(); i++) {

            System.out.printf("%.1f ",
                    listdblBSTSearchTime.get(i) +
                            listdblBSTBuildTime.get(i));
            dblAvgTotalTime += listdblBSTSearchTime.get(i) +
                    listdblBSTBuildTime.get(i);
        }
        System.out.printf("\nAverage Total Binary Search Tree Time: %.3f\n",
                dblAvgTotalTime / NUMBER_OF_REPEATS);
    }





    /* ---------- ---------- General Purpose Methods ---------- ---------- */

    /**
     * generateRandomNum
     * - Generates a random number between the min and max.
     *
     * @param intMin The minimum number to generate.
     * @param intMax The maximum number to generate.
     *
     * @return The generated random number.
     */
    static int generateRandomNum (int intMin,
                                  int intMax) {

        return (int) (Math.random() * (intMax - intMin + 1) + intMin);
    }



    /** 
     * generateString
     * - Generates a random string of ['a', 'g', 'c', 't'] of length intLength.
     *
     * @param intLength Length of the string to generate.
     *
     * @return Random string of length intLength.
     */
    static String generateString (int intLength) {

        // Declare and Initialize String
        StringBuilder strRandomString = new StringBuilder();
        char[] arrchrPossibleLetters = {'a', 'g', 'c', 't'};

        // Generate Random String from PossibleLetters
        for (int i = 0; i < intLength; i++) {

            strRandomString.append(arrchrPossibleLetters[
                    generateRandomNum(0, 3)]);
        }

        // Return Random String
        return strRandomString.toString();
    }

    

    /**
     * displayDivider
     * - Displays a divider.
     */
    static void displayDivider () {

        System.out.println("\n_____________________________________________\n");
    }


    
    /**
     * displayKmerDistribution
     * - Displays the K-mer Distribution of the given DNA String in table form.
     *
     * @param intKmer The K-mer Length.
     * @param arrstrDNAStrands The DNA Strands to display.
     * @param arrintCounts The counts of the K-mers of the DNA Strands.
     */
    static void displayKmerDistribution (int intKmer,
                                         String[] arrstrDNAStrands,
                                         int[] arrintCounts) {

        // Print Label
        // If Kmer is less than Label
        if (intKmer < "XX-mer ".length()) {

            System.out.println("\t" + "-".repeat(25));

            System.out.printf("\t| %2d-mer  | Occurrences |\n",
                    intKmer);

            System.out.println("\t" + "-".repeat(25));

            // For each Bucket in Bucket List
            for (int i = 0; i < arrstrDNAStrands.length; i++) {

                // Print Node String and Count
                System.out.printf("\t| %s%s |     %4d    |\n",
                        arrstrDNAStrands[i], " ".repeat(Math.abs(intKmer-7)),
                        arrintCounts[i]);
            }

            System.out.println("\t" + "-".repeat(25));
        }

        else {

            System.out.println("\t" + "-".repeat(25 + (intKmer-7)));

            System.out.printf("\t| %2d-mer%s  | Occurrences |\n",
                    intKmer, " ".repeat(intKmer-7));

            System.out.println("\t" + "-".repeat(25 + (intKmer-7)));

            // For each Bucket in Bucket List
            for (int i = 0; i < arrstrDNAStrands.length; i++) {

                // Print Node String and Count
                System.out.printf("\t| %s |     %4d    |\n",
                        arrstrDNAStrands[i], arrintCounts[i]);
            }

            System.out.println("\t" + "-".repeat(25 + (intKmer-7)));
        }
    }
}





/* ---------- Hash Table Implementation ---------- */

class HashTable {
    
    
    
    /* ---------- HashNode Class ---------- */

    static class HashNode {

        // Attributes
        // Key
        private String strDNAStrand;
        // Value
        private int intCount;
        // Next HashNode
        private HashNode nodeNext;


        // Constructors

        public HashNode (String strDNAStrand) {

            this.strDNAStrand = strDNAStrand;
            this.intCount = 1;
            this.nodeNext = null;
        }



        // Getters and Setters

        public String getStrDNAStrand() {return strDNAStrand;}

        public int getIntCount() {return intCount;}
        public void setIntCount(int intCount) {this.intCount = intCount;}

        public HashNode getNodeNext() {return nodeNext;}
        public void setNodeNext(HashNode nodeNext) {this.nodeNext = nodeNext;}
    }





    /* ---------- HashTable Attributes ---------- */

    // Bucket List
    private ArrayList<HashNode> listBuckets;

    // Table Status;
    private int intHashFunction;
    private int intStrandCount = 0;
    private int intNodeCount = 0;
    private int intBucketCount = 0;
    private int intCollisionCount = 0;
    private int intEmptyBucketCount;
    private float fltLoadFactor = 0;

    // DNA Strands List
    private ArrayList<String> liststrDNAStrands = new ArrayList<>();





    /* ---------- HashTable Constructors ---------- */

    /**
     * HashTable
     * - Default Constructor.
     *
     * @param strDNA The DNA String.
     * @param intKmer The K-mer Length.
     */
    public HashTable (String strDNA,
                      int intKmer,
                      int intHashFunction) {

        // Create Bucket List
        this.create();

        // Initialize Values
        this.intHashFunction = intHashFunction;
        this.intBucketCount = this.computeBucketCount(strDNA.length(), intKmer);
        this.intEmptyBucketCount = this.intBucketCount;

        // Initialize Bucket List
        makeEmptyBuckets(this.intBucketCount);


        // For each Substring of K-mer in the DNA
        for (int i = 0; i < strDNA.length() - (intKmer - 1); i++) {

            this.add(strDNA.substring(i, i + intKmer));
            this.intStrandCount++;
        }
    }





    /* ---------- HashTable Methods ---------- */

    /**
     * create
     * - Creates the HashTable.
     */
    public void create() {

        // Create Bucket List
        this.listBuckets = new ArrayList<>();

        // Rest Table Status;
        this.intBucketCount = 0;
        this.intNodeCount = 0;
        this.intCollisionCount = 0;
        this.intEmptyBucketCount = 0;
        this.intStrandCount = 0;
        this.fltLoadFactor = 0;

        // Reset DNA Strands and Count List
        this.liststrDNAStrands = new ArrayList<>();
    }


    /**
     * computeBucketCount
     * - Computes the Bucket Count based on the DNA Length and K-mer.
     *
     * @param intDNALength The DNA Length.
     * @param intKmer The K-mer Length.
     *
     * @return The Bucket Count.
     */
    public int computeBucketCount (int intDNALength,
                                   int intKmer) {

        int intDNAStrands = intDNALength - (intKmer - 1);
        int intPossibleCombinations = (int)Math.pow(4, intKmer);

        // If more DNA Strands than Possible Kmer Combinations
        if (intDNAStrands > intPossibleCombinations)
            // Return Load Factor * Number of Possible Kmer Combinations
            return intPossibleCombinations;

        else
            // Return number of DNA Strands
            return intDNAStrands;
    }



    /**
     * makeEmptyBuckets
     * - Initializes all Buckets to be empty.
     *
     * @param intBucketCount The Bucket Count.
     */
    public void makeEmptyBuckets (int intBucketCount) {

        // Initialize Buckets with nulls
        for (int i = 0; i < intBucketCount; i++) {

            this.listBuckets.add(null);
        }
    }


    /**
     * getHashCode
     * - Computes the Hash Code for the given DNA Strand.
     *      Can use 3 different Hash Functions based on the HASH_FUNCTION_MODE.
     *
     * @param strDNAStrand The DNA Strand to Hash.
     *
     * @return The Hash Code of the DNA Strand.
     */
    public int getHashCode (String strDNAStrand) {

        int intHashCode = 0;

        switch (this.intHashFunction) {

            case 1:
                // Hash Function 1 : FNV1a
                intHashCode = FNV1a.hash32(strDNAStrand.getBytes());
                break;

            case 2:
                // Hash Function 2 : XXHash32 + hashCode
                intHashCode = (int)(new XXHash32(strDNAStrand.hashCode())).getValue();
                break;

            default:
                // Error
                System.out.println("Hash Function Mode is Wrong");
        }

        // Check Negative
        if (intHashCode < 0)
            return intHashCode * -1;
        else
            return intHashCode;
    }


    /**
     * get
     * - Gets the Count of a DNA Strand inside the HashTable.
     *
     * @param strDNAStrand The DNA Strand.
     *
     * @return The Count of the DNA Strand.
     */
    public int get (String strDNAStrand) {

        // Get Bucket Index
        int intBucketIndex = this.getHashCode(strDNAStrand) % this.intBucketCount;


        // Start of the Bucket Chain
        HashNode nodeHead = this.listBuckets.get(intBucketIndex);

        // While not end of Chain
        while (nodeHead != null) {

            // If Node matches, return Count
            if (nodeHead.getStrDNAStrand().equals(strDNAStrand))
                return nodeHead.getIntCount();

            // Go to next Node
            nodeHead = nodeHead.getNodeNext();
        }


        // DNA Strand not found in Bucket Chain
        return -1;
    }



    /**
     * add
     * - Adds a DNA Strand to the HashTable.
     *
     * @param strDNAStrand The DNA Strand.
     *
     * @return The Node of the DNA Strand added.
     */
    public HashNode add (String strDNAStrand) {

        // Get Bucket Index
        int intBucketIndex = this.getHashCode(strDNAStrand) % this.intBucketCount;

        // Debug: Show Add DNA Strand to Bucket Index
        if (CCDSALG_MCO2.DEBUG_MODE)
            System.out.printf("\tAdd %s to Bucket %d\n", strDNAStrand, intBucketIndex);


        // Start of the Bucket Chain
        HashNode nodeHead = this.listBuckets.get(intBucketIndex);

        // While not end of Chain
        while (nodeHead != null) {

            // If Node matches
            if (nodeHead.getStrDNAStrand().equals(strDNAStrand)) {

                // Increment Count of DNA Strand
                nodeHead.setIntCount(nodeHead.getIntCount() + 1);

                // Return Node
                return nodeHead;
            }

            // Go to next Node
            nodeHead = nodeHead.getNodeNext();
        }


        // If not found in Bucket Chain, then Chain New in front

        // Get reference of Bucket Chain Head
        nodeHead = this.listBuckets.get(intBucketIndex);

        // If Bucket Chain is not Empty
        if (nodeHead != null){

            // Increment Collision Count
            this.intCollisionCount++;

            // Debug: Show Collision
            if (CCDSALG_MCO2.DEBUG_MODE)
                System.out.printf("\t\tCollision in Bucket %d : %s <- %s\n",
                        intBucketIndex, nodeHead.getStrDNAStrand(), strDNAStrand);
        }

        // If Bucket is Empty
        else
            this.intEmptyBucketCount--;

        // Initialize New Node as DNA Strand and put Head as next
        HashNode nodeNew = new HashNode(strDNAStrand);
        nodeNew.setNodeNext(nodeHead);

        // Set the New Node as New Bucket Chain Head and Increment Node Count
        this.listBuckets.set(intBucketIndex, nodeNew);
        this.intNodeCount++;

        // Add DNA Strand List
        this.liststrDNAStrands.add(strDNAStrand);


        // Update Load Factor
        this.fltLoadFactor = 1.0f * intNodeCount / intBucketCount;

        // Return New Node
        return nodeNew;
    }



    /**
     * getStrandAndCountArray
     * - Puts the Array of DNA Strands and their Counts into 2 separate Arrays.
     *
     * @param arrstrDNAStrandDestination The Destination Array of DNA Strands.
     * @param arrintCountDestination The Destination Array of DNA Strand Counts.
     */
    public void getStrandAndCountArray (String[] arrstrDNAStrandDestination,
                                        int[] arrintCountDestination) {

        // For each Unique DNA Strand
        for (int i = 0; i < this.liststrDNAStrands.size(); i++) {

            // Add Unique DNA Strand and its Counts to the Destination Arrays
            arrstrDNAStrandDestination[i] = this.liststrDNAStrands.get(i);
            arrintCountDestination[i] = this.get(this.liststrDNAStrands.get(i));
        }
    }



    /**
     * displayDetails
     * - Displays the Details of the Binary Search Tree.
     */
    public void displayDetails () {

        // Display Hash Function Used
        System.out.print("\tHash Function Used: ");
        switch (this.intHashFunction) {

            case 1:
                // Hash Function 1 : FNV1a
                System.out.println("FNV1a");
                break;

            case 2:
                // Hash Function 2 : XXHash32 + hashCode
                System.out.println("XXHash32 + hashCode");
                break;

            default:
                // Error
                System.out.println("None");
        }


        System.out.println("\tTotal DNA Strand Count: " + this.intStrandCount);
        System.out.println("\tUnique DNA Strands Count: " + this.intNodeCount);
        System.out.println("\tBucket Count: " + this.intBucketCount);
        System.out.println("\tCollision Count: " + this.intCollisionCount);
        System.out.println("\tEmpty Bucket Count: " + this.intEmptyBucketCount);
        System.out.println("\tLoad Factor: " + this.fltLoadFactor);
    }





    /* ---------- HashTable Getters and Setters ---------- */

    public int getIntNodeCount() {return intNodeCount;}

    public int getIntCollisionCount() {return intCollisionCount;}
}





/* ---------- Binary Search Tree Implementation ---------- */

class BinarySearchTree {



    /* ---------- TreeNode Class ---------- */

    static class TreeNode {

        // Attributes
        // Key
        private String strDNAStrand;
        // Value
        private int intCount = 0;
        // Left and Right TreeNode
        private TreeNode nodeLeft;
        private TreeNode nodeRight;



        // Constructors
        public TreeNode() {}

        public TreeNode (String strDNAStrand) {

            this.strDNAStrand = strDNAStrand;
            this.intCount = 1;

            this.nodeLeft = null;
            this.nodeRight = null;
        }



        // Methods

        /**
         * initialize
         * - Initializes the TreeNode with the given parameters.
         *
         * @param strDNAStrand The DNA Strand.
         *
         * @return The Node itself.
         */
        public TreeNode initialize (String strDNAStrand) {

            this.strDNAStrand = strDNAStrand;
            this.intCount = 1;

            this.nodeLeft = null;
            this.nodeRight = null;

            return this;
        }



        // Getters and Setters

        public String getStrDNAStrand() {return strDNAStrand;}

        public int getIntCount() {return intCount;}
        public void setIntCount(int intCount) {this.intCount = intCount;}

        public TreeNode getNodeLeft() {return nodeLeft;}
        public TreeNode setNodeLeft(TreeNode nodeLeft) {return this.nodeLeft = nodeLeft;}

        public TreeNode getNodeRight() {return nodeRight;}
        public TreeNode setNodeRight(TreeNode nodeRight) {return this.nodeRight = nodeRight;}
    }





    /* ---------- BinarySearchTree Attributes ---------- */

    // Root Node
    private TreeNode nodeRoot;

    // Tree Status;
    private int intStrandCount = 0;
    private int intNodeCount = 0;
    private int intLowestDepth = 0;

    // DNA Strands and Count List
    private ArrayList<String> liststrDNAStrands = new ArrayList<>();





    /* ---------- BinarySearchTree Constructors ---------- */

    /**
     * BinarySearchTree
     * - Default Constructor.
     *
     * @param strDNA The DNA String.
     * @param intKmer The K-mer Length.
     */
    public BinarySearchTree (String strDNA,
                             int intKmer) {

        // Create Binary Search Tree
        this.create();


        // For each Substring of K-mer in the DNA
        for (int i = 0; i < strDNA.length() - (intKmer - 1); i++) {

            this.insert(strDNA.substring(i, i + intKmer));
            this.intStrandCount++;
        }
    }





    /* ---------- BinarySearchTree Methods ---------- */

    /**
     * create
     * - Creates a Binary Search Tree.
     */
    public void create() {

        // Create new Root Node
        this.nodeRoot = new TreeNode();

        // Reset Tree Status;
        this.intLowestDepth = 0;
        this.intNodeCount = 0;
        this.intStrandCount = 0;

        // Reset DNA Strands List
        this.liststrDNAStrands = new ArrayList<>();
    }



    /**
     * search
     * - Searches for the Count of a DNA Strand inside the Binary Search Tree.
     *
     * @param strDNAStrand The DNA Strand.
     *
     * @return The Count of the DNA Strand.
     */
    public int search (String strDNAStrand) {

        TreeNode nodeToSearch = this.nodeRoot;


        // While there is still Next Node
        while (nodeToSearch != null) {

            // If Next Node Matches DNA Strand
            if (nodeToSearch.getStrDNAStrand().equals(strDNAStrand)) {

                return nodeToSearch.getIntCount();
            }


            // Else If DNA Strand is Lexicographically less than Node To Search
            else if (nodeToSearch.getStrDNAStrand().compareTo(strDNAStrand) > 0)
                // Set Next Node as Left Node
                nodeToSearch = nodeToSearch.getNodeLeft();

                // Else If DNA Strand is Lexicographically more than Node To Search
            else if (nodeToSearch.getStrDNAStrand().compareTo(strDNAStrand) < 0)
                // Set Next Node as Right Node
                nodeToSearch = nodeToSearch.getNodeRight();
        }


        // DNA Strand not found in Tree
        return 0;
    }



    /**
     * insert
     * - Inserts a new TreeNode into the Binary Search Tree.
     *
     * @param strDNAStrand The DNA Strand.
     */
    public void insert (String strDNAStrand) {

        TreeNode nodeInserted = null;
        int intCurrentDepth = 0;


        // If Root Node is Empty
        if (this.nodeRoot.getStrDNAStrand() == null) {

            // Initialize Root Node as DNA Strand
            nodeInserted = nodeRoot.initialize(strDNAStrand);

            // Increment Node Count
            this.intNodeCount++;

            // Add DNA Strand to List
            this.liststrDNAStrands.add(strDNAStrand);
        }


        // Root is not Empty
        else {

            TreeNode nodePrevious = null;
            TreeNode nodeNext = this.nodeRoot;

            // While there is still Next Node
            while (nodeNext != null) {

                // If Next Node Matches DNA Strand
                if (nodeNext.getStrDNAStrand().equals(strDNAStrand)) {

                    // Increment Count of DNA Strand
                    nodeNext.setIntCount(nodeNext.getIntCount() + 1);

                    nodeInserted = nodeNext;
                    break;
                }

                nodePrevious = nodeNext;

                // If DNA Strand is Lexicographically less than Node To Search
                if (nodeNext.getStrDNAStrand().compareTo(strDNAStrand) > 0)
                    // Set Next Node as Left Node
                    nodeNext = nodeNext.getNodeLeft();

                    // Else If DNA Strand is Lexicographically more than Node To Search
                else if (nodeNext.getStrDNAStrand().compareTo(strDNAStrand) < 0)
                    // Set Next Node as Right Node
                    nodeNext = nodeNext.getNodeRight();

                // Increment Current Depth
                intCurrentDepth++;
            }

            // If found Node is Null
            if (nodeNext == null) {

                // If DNA Strand is Lexicographically less than Node To Search
                if (nodePrevious.getStrDNAStrand().compareTo(strDNAStrand) > 0)
                    // Initialize Left Node as DNA Strand
                    nodeInserted = nodePrevious.setNodeLeft(new TreeNode(strDNAStrand));

                    // Else If DNA Strand is Lexicographically more than Node To Search
                else if (nodePrevious.getStrDNAStrand().compareTo(strDNAStrand) < 0)
                    // Initialize Right Node as DNA Strand
                    nodeInserted = nodePrevious.setNodeRight(new TreeNode(strDNAStrand));

                // Increment Node Count
                this.intNodeCount++;

                // Add DNA Strand to List
                this.liststrDNAStrands.add(strDNAStrand);
            }
        }


        // Debug: Show Add DNA Strand to Tree on Depth Level
        if (CCDSALG_MCO2.DEBUG_MODE) {

            if (nodeInserted.getIntCount() == 1)
                System.out.printf("\tAdd %s to Depth %d\n",
                        strDNAStrand, intCurrentDepth);

            else if (nodeInserted.getIntCount() > 1)
                System.out.printf("\tCount of %s increased to %d in Depth %d\n",
                        strDNAStrand, nodeInserted.getIntCount(), intCurrentDepth);
        }


        // Check Lowest Depth Level
        if (intCurrentDepth > this.intLowestDepth) {

            // Note new Lowest Depth
            this.intLowestDepth = intCurrentDepth;

            // Debug: Show new Lowest Depth
            if (CCDSALG_MCO2.DEBUG_MODE)
                System.out.printf("\t\tNew Lowest Depth : %d\n",
                        this.intLowestDepth);
        }
    }



    /**
     * getStrandAndCountArray
     * - Puts the Array of DNA Strands and their Counts into 2 separate Arrays.
     *
     * @param arrstrDNAStrandDestination The Destination Array of DNA Strands.
     * @param arrintCountDestination The Destination Array of DNA Strand Counts.
     */
    public void getStrandAndCountArray (String[] arrstrDNAStrandDestination,
                                        int[] arrintCountDestination) {

        // For each Unique DNA Strand
        for (int i = 0; i < this.liststrDNAStrands.size(); i++) {

            // Add Unique DNA Strand and its Counts to the Destination Arrays
            arrstrDNAStrandDestination[i] = this.liststrDNAStrands.get(i);
            arrintCountDestination[i] = this.search(this.liststrDNAStrands.get(i));
        }
    }



    /**
     * displayDetails
     * - Displays the Details of the Binary Search Tree.
     */
    public void displayDetails () {

        System.out.println("\tTotal DNA Strand Count: " + this.intStrandCount);
        System.out.println("\tUnique DNA Strands Count: " + this.intNodeCount);
        System.out.println("\tLowest Depth Level: " + this.intLowestDepth);
    }





    /* ---------- BinarySearchTree Getters and Setters ---------- */

    public int getIntNodeCount() {return intNodeCount;}
}