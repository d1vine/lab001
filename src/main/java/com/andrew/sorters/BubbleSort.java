package com.andrew.sorters;

/**
 * This class provides method to sort an array
 * Is a subtype of super class {@link Sort}
 *
 * @param <T> the type of the element
 * @see BubbleReverseSort
 * @see ArraySort
 * @see InsertionSort
 * @see MergeSort
 * @see QuickSort
 */
@SuppressWarnings("ALL")
public class BubbleSort<T> extends Sort<T> {

    /**
     * Sorts the specified array into numerical order.
     * <p>
     * Takes any array that implements {@link Comparable}
     *
     * @param array array to be sorted.
     */
    @SuppressWarnings("unchecked")
    public void doSort(Comparable<T>[] array) {
        if (array == null) {
            return;
        }
        testSort(array);
    }

    /**
     * Sorts the specified array into ascending numerical order.
     * <p>
     * The sorting algorithm is bubble sorting method.
     * Takes any array that elements of which implements {@link Comparable}
     *
     * @param array array to be sorted
     */
    private void bubbleSort(Comparable<T>[] array) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < array.length; i++) {
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
     * Sorts the specified array into ascending numerical order.
     * <p>
     * The sorting algorithm is bubble sorting method.
     * Takes any array that elements of which implements {@link Comparable}
     *
     * @param array array to be sorted
     */
    private void testSort(Comparable<T>[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                //noinspection unchecked
                if (array[j - 1].compareTo((T) array[j]) > 0) {
                    //swap elements

                    swap(array, j - 1, j);
                }

            }
        }
    }
}
