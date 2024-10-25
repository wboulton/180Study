public class MergeSort {
    /*
     * Merge sort is a classic array sorting algorithm. Write a method to implement it.
     * According to Geeks for Geeks it works in the following way:
     * 
     * Step 1: Divide Array into Two Parts.
     * Step 2: Merge Sort the first part of the array.
     * Step 3: Merge Sort the second part of the array.
     * Step 4: Merge Both the parts.
     * Step 5: Return the Sorted Array.
     * 
     * the method will be passed the original integer array and the first index and last index
     * (see the main method for reference)
     */
    
                    /* SOLUTION */
    
        // Method to merge two subarrays of a[].
        // First subarray is a[l...m]
        // Second subarray is a[m+1...r]
        void merge(int a[], int l, int m, int r) {
            // Calculate the sizes of the two subarrays to be merged
            int n1 = m - l + 1; // Size of the left subarray
            int n2 = r - m;     // Size of the right subarray
    
            // Create temporary arrays to hold the elements of the subarrays
            int L[] = new int[n1];
            int R[] = new int[n2];
    
            // Copy data to the temporary arrays L[] and R[]
            for (int i = 0; i < n1; i++) {
                L[i] = a[l + i]; // Copy elements from the left half of a[] to L[]
            }
            for (int i = 0; i < n2; i++) { 
                R[i] = a[m + 1 + i]; // Copy elements from the right half of a[] to R[]
            }
    
            // Initialize indices for L[], R[], and merged array a[]
            int i = 0, j = 0; // Initial indexes for L[] and R[]
            int k = l;        // Initial index for merged subarray a[]
    
            // Merge the two subarrays back into the original array a[]
            while (i < n1 && j < n2) {
                // Choose the smaller of the two elements from L and R
                // and place it in the correct position in a[]
                if (L[i] <= R[j]) {
                    a[k] = L[i];
                    i++; // Move to the next element in L[]
                } else {
                    a[k] = R[j];
                    j++; // Move to the next element in R[]
                }
                k++; // Move to the next position in the merged array
            }
    
            // Copy any remaining elements from L[] (if any)
            while (i < n1) {
                a[k] = L[i];
                i++;
                k++;
            }
    
            // Copy any remaining elements from R[] (if any)
            while (j < n2) {
                a[k] = R[j];
                j++;
                k++;
            }
        }
    
        // Recursive method to implement merge sort
        void sort(int a[], int l, int r) {
            if (l < r) {
                // Find the midpoint to divide the array into two halves
                int m = (l + r) / 2;
    
                // Recursively sort the first and second halves
                sort(a, l, m);   // Sort the left half
                sort(a, m + 1, r); // Sort the right half
    
                // Merge the sorted halves
                merge(a, l, m, r);
            }
        }
    
        public static void main(String args[]) {
            // Example array to be sorted
            int a[] = { 12, 11, 13, 5, 6, 7 };
    
            // Create an object of MergeSort 
            MergeSort ob = new MergeSort();
    
            // Call the sort method on the entire array
            ob.sort(a, 0, a.length - 1);
    
            // Output the sorted array
            System.out.println("Sorted array:");
            for (int i = 0; i < a.length; ++i) {
                System.out.print(a[i] + " ");
            }
        }
    
        // I had chatGPT comment this for some explination of how it works.
        /* 
         * Essentially this works by first running the sort algorithm, which will break down the array until there are only
         * 1 element in each array. Then because of the recursiveness, the smallest array is sorted by the merge method 
         * (naming conventions are wonky) then passed back up to the sort method as a sorted array, which then uses it for merging together 
         * the next smallest array and so on. So, the arrays get split like this:
         * [12,11,13,5,6,7]
         * [12,11,13] [5,6,7]
         * [12,11] [13] [5,6] [7]
         * [12] [11] [13] [5] [6] [7]
         * then these get sorted and merged back together
         * [11,12] [13] | [5,6] [7]
         * [11,12,13] [5,6,7]
         * [5,6,7,11,12,13]
         */
    }
    