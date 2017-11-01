package com.andrew.fillers;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

/**
 * Class provides method to generate integer arrays.
 */
public class ArrayInit {

    /**
     * Generates sorted integer array up to {@code N} elements.
     * Fills array by following the pattern (1,2,3,4,5...N).
     *
     * @param N array size
     * @return integer array
     */
    public static Integer[] init(int N) {
        Integer[] array = new Integer[N];
        Arrays.setAll(array, i -> i + 1);
        return array;
    }

    /**
     * Generates sorted integer array up to {@code N} elements.
     * Fills array by following the pattern (1,2,3,4,5...N,X).
     *
     * @param N array size
     * @return integer array
     */
    public static Integer[] initPlusX(int N) {
        Integer[] array = new Integer[N];
        IntStream.range(1, N).forEach(val -> array[val - 1] = val);
        array[N - 1] = 1 + (int) (Math.random() * 100);
        return array;
    }

    /**
     * Generates sorted integer array up to {@code N} elements.
     * Fills array by following the pattern (N,N-1...5,4,3,2,1).
     *
     * @param N array size
     * @return integer array
     */
    public static Integer[] initReverse(int N) {
        Integer[] array = new Integer[N];
        Arrays.setAll(array, i -> i + 1);
        Collections.reverse(Arrays.asList(array));
        return array;
    }

    /**
     * Generates sorted integer array up to {@code N} elements.
     * Fills array with random values.
     *
     * @param N array size
     * @return integer array
     */
    public static Integer[] initRandom(int N) {
        Integer[] array = new Integer[N];
        Arrays.setAll(array, i -> 1 + (int) (Math.random() * 1000));
        return array;
    }
}
