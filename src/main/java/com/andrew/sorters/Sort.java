package com.andrew.sorters;

/**
 * Abstract class for sorting. Consist of two methods
 *
 * @param <T> the type of the element
 * @see ArraySort
 * @see BubbleReverseSort
 * @see BubbleSort
 * @see InsertionSort
 * @see MergeSort
 * @see QuickSort
 */
public abstract class Sort<T> {

    /**
     * Helping method. Swaps two elements.Takes any array that implements {@link Comparable}
     *
     * @param items array of elements
     * @param left  index of left element in array
     * @param right index of rigth element in array
     */
    void swap(Comparable<T>[] items, int left, int right) {
        if (left != right) {
            Comparable<T> temp = items[left];
            items[left] = items[right];
            items[right] = temp;
        }
    }

    /**
     * abstract sorting method. Takes any array
     * elements which that implements {@link Comparable}
     *
     * @param array array to be sorted.
     */
     public abstract void doSort(Comparable<T>[] array);
}
