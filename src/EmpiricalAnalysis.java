import java.util.Arrays;

public class EmpiricalAnalysis {

    public static void main(String[] args) {
        // Array sizes to test. 
        int[] sizes = {10000, 20000, 40000, 80000, 160000};
        int numTrials = 3; // Number of times to run each size to get an average

        System.out.println("Starting Empirical Analysis...");
        System.out.println("ArraySize, BubbleSort(ms), SelectionSort(ms), MergeSort(ms), HeapSort(ms), JavaSort(ms)");

        for (int size : sizes) {
            long totalBubble = 0, totalSelection = 0, totalMerge = 0, totalHeap = 0, totalJava = 0;

            for (int trial = 0; trial < numTrials; trial++) {
                // 1. Generate one random array for this trial
                int[] originalArray = RandomArrayGenerator.generateRandomArray(size);

                // 2. Clone the array so each algorithm sorts the exact same numbers
                int[] arr1 = Arrays.copyOf(originalArray, originalArray.length);
                int[] arr2 = Arrays.copyOf(originalArray, originalArray.length);
                int[] arr3 = Arrays.copyOf(originalArray, originalArray.length);
                int[] arr4 = Arrays.copyOf(originalArray, originalArray.length);
                int[] arr5 = Arrays.copyOf(originalArray, originalArray.length);

                // --- Bubble Sort ---
                long start = System.nanoTime();
                SortingAlgorithms.bubbleSort(arr1);
                totalBubble += (System.nanoTime() - start);

                // --- Selection Sort ---
                start = System.nanoTime();
                SortingAlgorithms.selectionSort(arr2);
                totalSelection += (System.nanoTime() - start);

                // --- Merge Sort ---
                start = System.nanoTime();
                SortingAlgorithms.mergeSort(arr3);
                totalMerge += (System.nanoTime() - start);

                // --- Heap Sort ---
                start = System.nanoTime();
                SortingAlgorithms.heapSort(arr4);
                totalHeap += (System.nanoTime() - start);

                // --- Java's Arrays.sort (Quicksort) ---
                start = System.nanoTime();
                Arrays.sort(arr5);
                totalJava += (System.nanoTime() - start);
            }

            // Calculate averages in milliseconds
            long avgBubble = (totalBubble / numTrials) / 1_000_000;
            long avgSelection = (totalSelection / numTrials) / 1_000_000;
            long avgMerge = (totalMerge / numTrials) / 1_000_000;
            long avgHeap = (totalHeap / numTrials) / 1_000_000;
            long avgJava = (totalJava / numTrials) / 1_000_000;

            // Print as comma-separated values (CSV) for easy graphing
            System.out.println(size + ", " + avgBubble + ", " + avgSelection + ", " + avgMerge + ", " + avgHeap + ", " + avgJava);
        }
    }
}