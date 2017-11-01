package com.andrew.sorters;

import java.util.Arrays;

/**
 * This class provides method to sort an array
 * Is a subtype of super class {@link Sort}
 *
 * @param <T> the type of the element
 * @see BubbleReverseSort
 * @see BubbleSort
 * @see InsertionSort
 * @see MergeSort
 * @see QuickSort
 */
public class ArraySort<T> extends Sort<T> {

    /**
     * Sorts the specified array into numerical order.
     * <p>
     * doSort uses {@code sort()} method from {@link Arrays} class.
     * Takes any array that elements of which implements {@link Comparable}
     *
     * @param array array to be sorted.
     */
    public void doSort(Comparable<T>[] array) {
        Arrays.sort(array);
    }
}
