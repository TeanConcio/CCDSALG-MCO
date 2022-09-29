class Suffix_Array {

    // Declare Members
    int intNumElem;
    String[] arrstrStrings;
    int[] arrintIndices;



    // Object Constructors
    // Initialize from String
    Suffix_Array (String strT) {

        // Initialize Number of Elements
        this.intNumElem = strT.length();


        // Initialize Suffix Strings
        this.arrstrStrings = new String[this.intNumElem];

        // Initialize Suffix Indices
        this.arrintIndices = new int[this.intNumElem];


        // For each character in T
        for (int i = 0; i < this.intNumElem; i++) {

            // Add its substring into the Suffix Array
            this.arrstrStrings[i] = strT.substring(i);

            // Assign Indices to the array
            this.arrintIndices[i] = i;
        }
    }

    // Initialize from subarray of Suffix Array
    Suffix_Array (Suffix_Array objSrcSuffixArray,
                  int intStartIndex,
                  int intEndIndex) {

        // Initialize Number of Elements
        this.intNumElem = intEndIndex - intStartIndex + 1;


        // Initialize Suffix Strings
        this.arrstrStrings = new String[this.intNumElem];

        // Initialize Suffix Indices
        this.arrintIndices = new int[this.intNumElem];


        // Copy String sub-array of source Suffix Array, O(intNumElem)
        System.arraycopy(objSrcSuffixArray.arrstrStrings,
                         intStartIndex,
                         this.arrstrStrings,
                         0,
                         intNumElem);

        // Copy String sub-array of source Suffix Array, O(intNumElem)
        System.arraycopy(objSrcSuffixArray.arrintIndices,
                         intStartIndex,
                         this.arrintIndices,
                         0,
                         intNumElem);
    }





    void swapElem(int intIndex1,
                  int intIndex2) {

        /*
        // Memory Saving
        
        // Swap Suffix Strings
        arrstrStrings[intIndex1] = arrstrStrings[intIndex1].concat(arrstrStrings[intIndex2]);
        arrstrStrings[intIndex2] = arrstrStrings[intIndex1].substring(0, arrstrStrings[intIndex1].length() - arrstrStrings[intIndex2].length());
        arrstrStrings[intIndex1] = arrstrStrings[intIndex1].substring(arrstrStrings[intIndex2].length());

        // Swap Suffix Indices
        arrintIndices[intIndex1] = arrintIndices[intIndex1] + arrintIndices[intIndex2];
        arrintIndices[intIndex2] = arrintIndices[intIndex1] - arrintIndices[intIndex2];
        arrintIndices[intIndex1] = arrintIndices[intIndex1] - arrintIndices[intIndex2];
        */
        
        // Time Saving
        // Swap Suffix Strings
        String arrstrTemp = arrstrStrings[intIndex1];
        arrstrStrings[intIndex1] = arrstrStrings[intIndex2];
        arrstrStrings[intIndex2] = arrstrTemp;

        // Swap Suffix Indices
        int arrintTemp = arrintIndices[intIndex1];
        arrintIndices[intIndex1] = arrintIndices[intIndex2];
        arrintIndices[intIndex2] = arrintTemp;
    }



    void copyElem(Suffix_Array objSrcSuffixArray,
                  int intCopyIndex,
                  int intPasteIndex) {

        arrstrStrings[intPasteIndex] = objSrcSuffixArray.arrstrStrings[intCopyIndex];
        arrintIndices[intPasteIndex] = objSrcSuffixArray.arrintIndices[intCopyIndex];
    }
}




public class CCDSALG_MP1_Objects {

    // Declare and Define String
    static String strT = "tgtgtgtgcaccg";
    // static String strT = "aataccggacaccatccctgcactagagccaatacctcgacactcgattaggaggctcacacttcccgcggccctaggcagttaaaccgtgaattgaagcggtcactgcgacagcttttcgtctatatgtgatcctcacggaacaaaagactgcgggaagcttaaaacgtgcacgcagaaagaggtaactagatggccgttcggccaatgtacttcctctgtcgtgcgcttaacagttctgcataggaaatctctgtggtgtacatgaactgtatcgagtacaccgtatgtagtccaggagcgtgcagaccggaagctaggatccccccattgtaagcagtcgatggttactgccgagttttaagcgtgttcacatgtccgctcagtggtcggtgccgatttggcccttctgaattgaacctagattgcgttgatccaagacattataagccaggctaggcacctcgctcatgactccggctacttaacgttcgcagggactgcaacgagcttgatccacactaccgtggattggaattgcaactcctattagcgaatacatacccagtcgccgatcttcgcaaggatgcacgcacacctcaaggcttggaaataatagtcacttaaagagggagatctttcctgcagccttattgtaacggttcccatccgtagtgaggggggttctcaggtgactttccggagtcgtccacagaactaaatcatgcagagggaccgatacgcaacggcttgcaaattagtggtccgggctacagcggccttatataccactaagaagacagagatgagttgaaaaatcctagtagcacccttatagtctccattcagatggtctagccgatgaccctgctctaaaccgacagccgccgcacccatcggacatgatcagttcggatgtttcagggtggtagtcgaccgagtagcgccttgctatcccaggaaattgttgtacgtaccttccgatgcattagcgccgggcaaacaaggcaccccaatgcaacctaataccggacaccatccctgcactagagccaatacctcgacactcgattaggaggctcacacttcccgcggccctaggcagttaaaccgtgaattgaagcggtcactgcgacagcttttcgtctatatgtgatcctcacggaacaaaagactgcgggaagcttaaaacgtgcacgcagaaagaggtaactagatggccgttcggccaatgtacttcctctgtcgtgcgcttaacagttctgcataggaaatctctgtggtgtacatgaactgtatcgagtacaccgtatgtagtccaggagcgtgcagaccggaagctaggatccccccattgtaagcagtcgatggttactgccgagttttaagcgtgttcacatgtccgctcagtggtcggtgccgatttggcccttctgaattgaacctagattgcgttgatccaagacattataagccaggctaggcacctcgctcatgactccggctacttaacgttcgcagggactgcaacgagcttgatccacactaccgtggattggaattgcaactcctattagcgaatacatacccagtcgccgatcttcgcaaggatgcacgcacacctcaaggcttggaaataatagtcacttaaagagggagatctttcctgcagccttattgtaacggttcccatccgtagtgaggggggttctcaggtgactttccggagtcgtccacagaactaaatcatgcagagggaccgatacgcaacggcttgcaaattagtggtccgggctacagcggccttatataccactaagaagacagagatgagttgaaaaatcctagtagcacccttatagtctccattcagatggtctagccgatgaccctgctctaaaccgacagccgccgcacccatcggacatgatcagttcggatgtttcagggtggtagtcgaccgagtagcgccttgctatcccaggaaattgttgtacgtaccttccgatgcattagcgccgggcaaacaaggcaccccaatgcaacct";





    /* ---------- ---------- General Purpose Methods ---------- ---------- */

    private static void printArray (int[] arrintA) {

        System.out.print("[ ");

        for (int i = 0; i < arrintA.length - 1; i++) {

            System.out.printf("%d, ", arrintA[i]);
        }

        System.out.printf("%d ]\n", arrintA[arrintA.length - 1]);
    }





    /* ---------- ---------- Bubble Sort Methods ---------- ---------- */

    /*
    * Bubble_Sort
    * - Sorts a String composing a sequence of the characters {a,c,g,t} in ascending order using the Bubble Sort Algorithm.
    *
    * @param strT String to be sorted
    * @param intStringLen Number of characters in the String
    *
    * @return Suffix Array of the characters of strT in ascending order
    * */
    private static int[] Bubble_Sort (String strT,
                              int intStringLen) {

        // Define variables
            // Initialize Object Arrays
        Suffix_Array objSuffixArray = new Suffix_Array(strT);


        // For each character in String
        for (int i = 0; i < intStringLen - 1; i++) {


            // For each character to move in String
            for (int j = 0; j < intStringLen - 1 - i; j++) {

                // If next String's lexicographically is less than current
                if (objSuffixArray.arrstrStrings[j+1].compareTo(objSuffixArray.arrstrStrings[j]) < 0) {

                    // Swap both Elements
                    objSuffixArray.swapElem(j, j+1);
                }
            }
        }


        // Return Sorted Suffix Array
        return objSuffixArray.arrintIndices;
    }





    /* ---------- ---------- Merge Sort Methods ---------- ---------- */

    /*
     * Merge_Sort
     * - Sorts a String composing a sequence of the characters {a,c,g,t} in ascending order using the Merge Sort Algorithm.
     *
     * @param strT String to be sorted
     * @param intStringLen Number of characters in the String
     *
     * @return Suffix Array of the characters of strT in ascending order
     * */
    private static int[] Merge_Sort (String strT,
                                     int intStringLen) {

        // Define variables
            // Initialize Object Arrays
        Suffix_Array objSuffixArray = new Suffix_Array(strT);
        

        // Start Merge Sort
        objSuffixArray = sortHalves(objSuffixArray,
                0,
                objSuffixArray.intNumElem - 1);


        // Return Sorted Suffix Array
        return objSuffixArray.arrintIndices;
    }



    private static Suffix_Array sortHalves (Suffix_Array objSuffixArray,
                                    int intStartIndex,
                                    int intEndIndex) {

        // If Start Index is less than End Index, There is more than 1 element
        if (intStartIndex < intEndIndex) {

            // Get midpoint
            int intMidpoint = (intStartIndex + (intEndIndex - 1)) / 2;

            // Recurse sort halves
            objSuffixArray = sortHalves(objSuffixArray,
                    intStartIndex,
                    intMidpoint);
            objSuffixArray = sortHalves(objSuffixArray,
                    intMidpoint + 1,
                    intEndIndex);

            // After arrays are formed
            // Merge and compare both arrays
            objSuffixArray = mergeHalves(objSuffixArray,
                    intStartIndex,
                    intMidpoint,
                    intEndIndex);
        }


        // Return sorted Suffix Array
        return objSuffixArray;
    }



    private static Suffix_Array mergeHalves (Suffix_Array objSuffixArray,
                                             int intStartIndex,
                                             int intMidpoint,
                                             int intEndIndex) {

        // Define and Initialize Temp Suffix Arrays as Sub-Arrays of the original
        Suffix_Array objTempSuffixArray1 = new Suffix_Array(objSuffixArray,
                intStartIndex,
                intMidpoint);
        Suffix_Array objTempSuffixArray2 = new Suffix_Array(objSuffixArray,
                intMidpoint+1,
                intEndIndex);


        // Compare elements from both arrays
        // Initialize Indices
        int intIndex1st = 0;
        int intIndex2nd = 0;
        int intIndexMain = intStartIndex;

        // While not yet end of a Sub-Array
        while (intIndex1st < objTempSuffixArray1.intNumElem &&
                intIndex2nd < objTempSuffixArray2.intNumElem) {

            // If Element in Sub-Array 1 is first than Element in Sub-Array 2
            if (objTempSuffixArray1.arrstrStrings[intIndex1st].compareTo(objTempSuffixArray2.arrstrStrings[intIndex2nd]) < 0) {

                // Copy element of Sub-Array 1 into the Main Array
                objSuffixArray.copyElem(objTempSuffixArray1,
                        intIndex1st,
                        intIndexMain);
                // Increment Index of Sub-Array 1
                intIndex1st++;
            }
            else {
                // Copy element of Sub-Array 2 into the Main Array
                objSuffixArray.copyElem(objTempSuffixArray2,
                        intIndex2nd,
                        intIndexMain);
                // Increment Index of Sub-Array 2
                intIndex2nd++;
            }

            // Increment Index of Main Array
            intIndexMain++;
        }


        // Leftover checking
        // While there is leftover in Sub-Array 1
        while (intIndex1st < objTempSuffixArray1.intNumElem) {
            // Copy element of Sub-Array 1 into the Main Array
            objSuffixArray.copyElem(objTempSuffixArray1,
                    intIndex1st,
                    intIndexMain);
            // Increment Index of Sub-Array 1
            intIndex1st++;
            // Increment Index of Main Array
            intIndexMain++;
        }

        while (intIndex2nd < objTempSuffixArray2.intNumElem) {
            // Copy element of Sub-Array 2 into the Main Array
            objSuffixArray.copyElem(objTempSuffixArray2,
                    intIndex2nd,
                    intIndexMain);
            // Increment Index of Sub-Array 1
            intIndex2nd++;
            // Increment Index of Main Array
            intIndexMain++;
        }


        // Return sorted Suffix Array
        return objSuffixArray;
    }




    /* ---------- ---------- Main ---------- ---------- */

    public static void main(String[] args) {

        // Declare Monitor Variables
        // Initiate Memory Values
        Runtime runtime = Runtime.getRuntime();
        long longBubbleMem;
        long longMergeMem;
        // Initiate Time values
        long longBubbleTime;
        long longMergeTime;



        System.out.printf("String Sequence: \"%s\"\n\n", strT);



        // Bubble Sort Output
        // Note Start Memory and Time
        longBubbleMem = runtime.totalMemory() - runtime.freeMemory();
        longBubbleTime = System.nanoTime();

        // Run and Display Bubble Sort Answer
        System.out.println("Bubble Sort Output:");
        printArray(Bubble_Sort(strT, strT.length()));

        // Note End Memory and Time
        longBubbleMem = runtime.totalMemory() - runtime.freeMemory() - longBubbleMem;
        longBubbleTime = System.nanoTime() - longBubbleTime;


        // Merge Sort Output
        // Note Start Memory and Time
        longMergeMem = runtime.totalMemory() - runtime.freeMemory();
        longMergeTime = System.nanoTime();

        // Run and Display Merge Sort Answer
        System.out.println("Merge Sort Output:");
        printArray(Merge_Sort(strT, strT.length()));

        // Note End Memory and Time
        longMergeMem = runtime.totalMemory() - runtime.freeMemory() - longMergeMem;
        longMergeTime = System.nanoTime() - longMergeTime;


        // Display Memory and Time Differences
        // Memory
        System.out.printf("\nBubble Sort Memory:   %6.7f Kb\n", (double)longBubbleMem/(1024));
        System.out.printf("Merge Sort Memory:    %6.7f Kb\n", (double)longMergeMem/(1024));
        System.out.printf("Memory Difference:    %6.7f Kb\n", (double)(longBubbleMem - longMergeMem)/(1024));
        // Time
        System.out.printf("\nBubble Sort Runtime:   %3.7f seconds\n", (double)(longBubbleTime) / 1_000_000_000);
        System.out.printf("Merge Sort Runtime:    %3.7f seconds\n", (double)(longMergeTime) / 1_000_000_000);
        System.out.printf("Runtime Difference:    %3.7f seconds\n", (double)(longBubbleTime - longMergeTime) / 1_000_000_000);
    }
}
