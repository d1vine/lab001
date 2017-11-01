package com.andrew.utils;

/**
 * Class contains method to manipulates {@code Comparable} arrays
 *
 * @param <T> the type of element
 */
public class ArrayUtils<T> {

    /**
     * Method displays {@code array} to a console
     *
     * @param array array to be displayed
     */
    public void show(T[] array) {
        for (T item : array) {
            System.out.print(item);
            System.out.print(", ");
        }
        System.out.println("");
    }
}
