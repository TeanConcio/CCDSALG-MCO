public class CCDSALG_MP1 {





    /*
    * swap
    * - Swaps 2 integers / String of an array of characters from their indexes
    * @param A Integer / String Array
    * @param i Index of 1st Character
    * @param j Index of 2nd Character
    * */
    static void swap (int[] A, int i, int j) {

        int iTemp = A[i];
        A[i] = A[j];
        A[j] = iTemp;
    }

    static void swap (String[] A, int i, int j) {

        String sTemp = A[i];
        A[i] = A[j];
        A[j] = sTemp;
    }





    static int[] makeIndexArray (int n) {

        // Make array with n elements
        int[] aiIndexArray = new int[n];

        // For n
        for (int i = 0; i < n; i++) {

            // Assign indexes to the array
            aiIndexArray[i] = i;
        }

        // Return Index Array
        return aiIndexArray;
    }





    static String[] makeSuffixArray (String A) {

        // Make String array with the length of A
        String[] asSuffixArray = new String[A.length()];

        // For each character in A
        for (int i = 0; i < A.length(); i++) {

            // Add its substring into the Suffix Array
            asSuffixArray[i] = A.substring(i);
        }

        // Return Suffix Array
        return asSuffixArray;
    }





    static void printArray (int[] A, int n) {

        System.out.print("[ ");

        for (int i = 0; i < n; i++) {

            System.out.printf("%2d ", A[i]);
        }

        System.out.print("]\n");
    }

    static void printArray (String[] A, int n) {

        for (int i = 0; i < n; i++) {

            System.out.println(A[i]);
        }
    }





    /*
    * Bubble_Sort
    * - Sorts a String composing a sequence of the characters {a,c,g,t} in ascending order using the Bubble Sort Algorithm.
    *
    * @param strT String to be sorted
    * @param intStringLen Number of characters in the String
    *
    * @return Suffix Array of the characters of strT in ascending order
    * */
    static int[] Bubble_Sort (String sT, int iStringLen) {

        int[] aiSortedSuffix = makeIndexArray(iStringLen);
        String[] asSuffixArray = makeSuffixArray(sT);
        boolean bSorted;


        // printArray(asSuffixArray, aiSortedSuffix.length);
        // printArray(aiSortedSuffix, aiSortedSuffix.length);


        // For each unsorted character in String
        for (int i = 0; i < iStringLen - 1; i++) {

            // Reset value of checker
            bSorted = true;

            for (int j = 0; j < iStringLen - 1 - i; j++) {

                // If next String's lexicographically is less than current
                if (asSuffixArray[j+1].compareTo(asSuffixArray[j]) < 1) {

                    // Swap both Characters
                    swap(asSuffixArray, j, j + 1);

                    // Apply swap in Suffix Array
                    swap(aiSortedSuffix, j, j+1);

                    // Set value of checker to false
                    bSorted = false;
                }
            }

            // If sorted, then break
            if (bSorted)
                break;
        }


        // printArray(asSuffixArray, aiSortedSuffix.length);
        // printArray(aiSortedSuffix, aiSortedSuffix.length);


        return aiSortedSuffix;
    }





    /*
     * Merge_Sort
     * - Sorts a String composing a sequence of the characters {a,c,g,t} in ascending order using the Merge Sort Algorithm.
     *
     * @param strT String to be sorted
     * @param intStringLen Number of characters in the String
     *
     * @return Suffix Array of the characters of strT in ascending order
     * */
    static int[] Merge_Sort (String sT, int iStringLen) {

        int[] aiSortedSuffix = makeIndexArray(iStringLen);
        String[] asSuffixArray = makeSuffixArray(sT);
        boolean bSorted;


        // printArray(asSuffixArray, aiSortedSuffix.length);
        // printArray(aiSortedSuffix, aiSortedSuffix.length);





        // printArray(asSuffixArray, aiSortedSuffix.length);
        // printArray(aiSortedSuffix, aiSortedSuffix.length);


        return aiSortedSuffix;
    }





    public static void main(String[] args) {

        // Initiate and Define String
        String sT = "tgtgtgtgcaccg";

        // Bubble_Sort(sT, sT.length());
        printArray(Bubble_Sort(sT, sT.length()), sT.length());
    }
}
