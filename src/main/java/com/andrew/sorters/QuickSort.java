package com.andrew.sorters;


/**
 * This class provides method to sort an array
 * Is a subtype of super class {@link Sort}
 *
 * @param <T> the type of the element
 * @see BubbleReverseSort
 * @see BubbleSort
 * @see InsertionSort
 * @see MergeSort
 * @see ArraySort
 */
public class QuickSort<T> extends Sort<T> {

    /**
     * Sorts the specified array into natural order.
     * <p>
     * The sorting algorithm is quick sort method.
     *
     * @param array array to be sorted.
     */
    @SuppressWarnings("unchecked")
    public void doSort(Comparable<T>[] array) {
        if (array == null)
            return;
        quickSort(array, 0, array.length - 1);
    }

    /**
     * Sorts the specified range of the array by Quicksort.
     *
     * @param array array to be sorted
     * @param left  the index of the first element, inclusive, to be sorted
     * @param right the index of the last element, inclusive, to be sorted
     */
    private void quickSort(Comparable<T>[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = left + (int) ((Math.random() * (right - left)));
            int newPivot = partition(array, left, right, pivotIndex);

            quickSort(array, left, newPivot - 1);
            quickSort(array, newPivot + 1, right);
        }
    }

    /**
     * The values are shifted by the method partition.
     * <p>
     * Rearrange elements in such a way, that all elements
     * which are lesser than the pivot go to the left part
     * of the array and all elements greater than the pivot,
     * go to the right part of the array
     *
     * @param array      array to be sorted
     * @param left       the index of the first element
     * @param right      the index of the last element
     * @param pivotIndex random key value on which array is divided
     * @return new pivotIndex
     */
    @SuppressWarnings("unchecked")
    private int partition(Comparable<T>[] array, int left, int right, int pivotIndex) {
        Comparable<T> pivotValue = array[pivotIndex];
        swap(array, pivotIndex, right);

        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (array[i].compareTo((T) pivotValue) < 0) {
                swap(array, i, storeIndex);
                storeIndex += 1;
            }
        }
        swap(array, storeIndex, right);
        return storeIndex;
    }


}
