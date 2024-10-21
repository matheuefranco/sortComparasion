/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sortingcomparison;

import java.util.Arrays;
import java.util.Random;

public class SortingComparison {

    // QuickSort
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
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

    // MergeSort
    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; i++)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[m + 1 + j];
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // CountingSort
    public static void countingSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        int[] count = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }
        int index = 0;
        for (int i = 0; i <= max; i++) {
            while (count[i] > 0) {
                arr[index++] = i;
                count[i]--;
            }
        }
    }

    // ShellSort
    public static void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }

    // HeapSort
    public static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && arr[left] > arr[largest])
            largest = left;
        if (right < n && arr[right] > arr[largest])
            largest = right;
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    // InsertionSort
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    // SelectionSort
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        int n = 200000;
        Random rand = new Random();

        // Generating a random array of 200000 elements
        int[] originalArray = rand.ints(n, 0, 1000000).toArray();

        // Array for testing different sorting algorithms
        int[] arrayToSort;

        long startTime, endTime;

        // QuickSort
        arrayToSort = originalArray.clone();
        startTime = System.currentTimeMillis();
        quickSort(arrayToSort, 0, arrayToSort.length - 1);
        endTime = System.currentTimeMillis();
        System.out.println("QuickSort: " + (endTime - startTime) + " ms");

        // MergeSort
        arrayToSort = originalArray.clone();
        startTime = System.currentTimeMillis();
        mergeSort(arrayToSort, 0, arrayToSort.length - 1);
        endTime = System.currentTimeMillis();
        System.out.println("MergeSort: " + (endTime - startTime) + " ms");

        // CountingSort
        arrayToSort = originalArray.clone();
        startTime = System.currentTimeMillis();
        countingSort(arrayToSort);
        endTime = System.currentTimeMillis();
        System.out.println("CountingSort: " + (endTime - startTime) + " ms");

        // ShellSort
        arrayToSort = originalArray.clone();
        startTime = System.currentTimeMillis();
        shellSort(arrayToSort);
        endTime = System.currentTimeMillis();
        System.out.println("ShellSort: " + (endTime - startTime) + " ms");

        // HeapSort
        arrayToSort = originalArray.clone();
        startTime = System.currentTimeMillis();
        heapSort(arrayToSort);
        endTime = System.currentTimeMillis();
        System.out.println("HeapSort: " + (endTime - startTime) + " ms");

        // InsertionSort
        arrayToSort = originalArray.clone();
        startTime = System.currentTimeMillis();
        insertionSort(arrayToSort);
        endTime = System.currentTimeMillis();
        System.out.println("InsertionSort: " + (endTime - startTime) + " ms");

        // SelectionSort
        arrayToSort = originalArray.clone();
        startTime = System.currentTimeMillis();
        selectionSort(arrayToSort);
        endTime = System.currentTimeMillis();
        System.out.println("SelectionSort: " + (endTime - startTime) + " ms");
    }
}
