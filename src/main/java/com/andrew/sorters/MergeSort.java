package com.andrew.sorters;


/**
 * This class provides method to sort an array
 * Is a subtype of super class {@link Sort}
 *
 * @param <T> the type of element
 * @see BubbleReverseSort
 * @see BubbleSort
 * @see InsertionSort
 * @see ArraySort
 * @see QuickSort
 */
@SuppressWarnings("ALL")
public class MergeSort<T> extends Sort<T> {

    /**
     * Sorts the specified array into  natural order.
     * <p>
     * The sorting algorithm is merge method.
     *
     * @param array array to be sorted.
     */
    @Override
    public void doSort(Comparable<T>[] array) {
        //If list is empty; no need to do anything
        if (array.length <= 1) {
            return;
        }

        //Split the array in half in two parts
        Comparable[] first = new Comparable[array.length / 2];
        Comparable[] second = new Comparable[array.length - first.length];
        System.arraycopy(array, 0, first, 0, first.length);
        System.arraycopy(array, first.length, second, 0, second.length);

        //Sort each half recursively
        //noinspection unchecked
        doSort(first);
        //noinspection unchecked
        doSort(second);
        //noinspection unchecked,unchecked
        mergeSort(first, second, array);
    }

    /**
     * Method merges both halves together, overwriting to original array.
     *
     * @param first  index Position in first array - starting with first element
     * @param second index Position in second array - starting with first element
     * @param result index Position in merged array - starting with first position
     */
    @SuppressWarnings("unchecked")
    private void mergeSort(Comparable<T>[] first, Comparable<T>[] second, Comparable<T>[] result) {
        int iFirst = 0;
        int iSecond = 0;
        int iMerged = 0;

        //Compare elements at iFirst and iSecond,
        //and move smaller element at iMerged
        while (iFirst < first.length && iSecond < second.length) {
            if (first[iFirst].compareTo((T) second[iSecond]) < 0) {
                result[iMerged] = first[iFirst];
                iFirst++;
            } else {
                result[iMerged] = second[iSecond];
                iSecond++;
            }
            iMerged++;
        }
        //copy remaining elements from both halves - each half will have already sorted elements
        System.arraycopy(first, iFirst, result, iMerged, first.length - iFirst);
        System.arraycopy(second, iSecond, result, iMerged, second.length - iSecond);
    }
}
