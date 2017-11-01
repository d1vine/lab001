package com.andrew.sorters;

/**
 * This class provides method to sort an array
 * Is a subtype of super class {@link Sort}
 *
 * @param <T> the type of the element
 * @see ArraySort
 * @see BubbleSort
 * @see InsertionSort
 * @see MergeSort
 * @see QuickSort
 */
@SuppressWarnings("ALL")
public class BubbleReverseSort<T> extends Sort<T> {
    /**
     * Sorts the specified array into reverse natural order.
     * Uses {@code bubbleReverseSort()} method.
     * Takes any array that implements {@link Comparable}
     *
     * @param array array to be sorted.
     */
    @SuppressWarnings("unchecked")
    public void doSort(Comparable<T>[] array) {
        //bubbleReverseSort(array);
        testSort(array);
    }

    /**
     * Sorts the specified array into reverse natural order.
     * <p>
     * The sorting algorithm is reverse bubble method.
     * Difference is sort method look through array from
     * end to beginning.     *
     * Takes any array that elements of which implements {@link Comparable}
     *
     * @param array array to be sorted
     */
    private void bubbleReverseSort(Comparable<T>[] array) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = array.length - 1; i >= 1; i--) {
                //noinspection unchecked
                if (array[i - 1].compareTo((T) array[i]) > 0) {
                    //noinspection unchecked
                    swap(array, i - 1, i);
                    swapped = true;
                }
            }
        } while (swapped);

    }
    /**
     * Sorts the specified array into reverse natural order.
     * <p>
     * The sorting algorithm is reverse bubble method.
     * Difference is sort method look through array from
     * end to beginning.     *
     * Takes any array that elements of which implements {@link Comparable}
     *
     * @param array array to be sorted
     */
    private void testSort(Comparable<T>[] array) {
        int n = array.length;
        for (int i = n-1; i >= 1; i--) {
            for (int j = n-1; j >= (n - i); j--) {
                //noinspection unchecked
                if (array[j - 1].compareTo((T) array[j]) > 0) {
                    //swap elements
                    swap(array, j - 1, j);
                }

            }
        }
    }
}
