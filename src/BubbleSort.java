import java.lang.reflect.Array;
import java.util.Arrays;

public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        int i, j, temp;
        int n = arr.length;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }

    public static void selectionSort(int[] arr) {

        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }

    public static void insertionSort(int[] arr) {

        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {

                arr[j + 1] = arr[j];

                j = j - 1;
            }
            arr[j + 1] = key;
        }

    }

    public static int partition(int[] arr, int low, int high) {

        int pivot = arr[high];

        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

            }

        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;

    }

    public static void quickSort(int[] arr, int low, int high) {

        if (low < high) {

            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);

            quickSort(arr, pi + 1, high);

        }

    }

    static void merge(int[] arr, int l, int m, int r)
    {

        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int[] L = new int[n1];
        int[] R = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static void mergeSort(int[] arr, int l, int r) {

        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }

    }

    public static void shellSort(int[] arr) {

        int inner, outer;

        int valueToInsert;

        int interval = 1;

        int elements = arr.length;

        int i = 0;

        while (interval <= elements / 3) {

            interval = interval * 3 + 1;

        }

        while (interval > 0) {

            for (outer = interval; outer < elements; outer++) {

                valueToInsert = arr[outer];

                inner = outer;

                while (inner > interval - 1 && arr[inner - interval] >= valueToInsert) {

                    arr[inner] = arr[inner - interval];

                    inner -= interval;


                }

                arr[inner] = valueToInsert;


            }

            interval = (interval - 1) / 3;

            i++;


        }

    }

    public static void main(String[] args) {

        int[] arr = {5, 1, 4, 2, 8};

        System.out.println("Mảng ban đầu:");
        System.out.println(Arrays.toString(arr));
        shellSort(arr);
        System.out.println("Mảng sau khi sắp xếp:");
        System.out.println(Arrays.toString(arr));

    }

}
