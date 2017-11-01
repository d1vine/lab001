package com.andrew.sorters;

/**
 * This class provides method to sort an array
 * Is a subtype of super class {@link Sort}
 *
 * @param <T> the type of element
 * @see BubbleReverseSort
 * @see BubbleSort
 * @see ArraySort
 * @see MergeSort
 * @see QuickSort
 */
@SuppressWarnings("ALL")
public class InsertionSort<T> extends Sort<T> {
    /**
     * Sorts the specified array into ascending natural order
     * Uses {@code insertionSort()} method.
     *
     * @param array array to be sorted.
     */
    @Override
    public void doSort(Comparable<T>[] array) {
        //noinspection unchecked
        insertionSort(array);
    }

    /**
     * Sorts the specified array into  natural order.
     * <p>
     * The sorting algorithm is insertion method.
     *
     * @param array array to be sorted.
     */
    private void insertionSort(Comparable<T>[] array) {

        int sortedRangeEnd = 0;
        while (sortedRangeEnd < array.length) {
            int nextIndex = FindIndexOfSmallestFromIndex(array, sortedRangeEnd);
            swap(array, sortedRangeEnd, nextIndex);
            sortedRangeEnd++;
        }
    }

    /**
     * Helping method finds smallest index and puts it at the beginning of an array.
     *
     * @param items          small array from first element to {@code sortedRangeEnd} index
     * @param sortedRangeEnd index of the end of small array
     * @return returns the sorted array
     */
    private int FindIndexOfSmallestFromIndex(Comparable<T>[] items, int sortedRangeEnd) {
        Comparable currentSmallest = items[sortedRangeEnd];
        int currentSmallestIndex = sortedRangeEnd;

        for (int i = sortedRangeEnd + 1; i < items.length; i++) {
            //noinspection unchecked
            if (currentSmallest.compareTo(items[i]) > 0) {
                currentSmallest = items[i];
                currentSmallestIndex = i;
            }
        }
        return currentSmallestIndex;
    }
}
