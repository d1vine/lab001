import com.andrew.fillers.ArrayInit;
import com.andrew.sorters.*;
import com.google.common.collect.Ordering;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class SortTest {


    private final int N = 10;

    @Test
    public void arraySortTest() {
        Sort<Integer> arraySort = new ArraySort<>();
        Integer[] array = ArrayInit.initRandom(N);
        arraySort.doSort(array);
        List<Integer> arrayAsList = Arrays.asList(array);
        assert Ordering.natural().isOrdered(arrayAsList);


    }

    @Test
    public void bubbleReverseSortTest() {
        Sort<Integer> bubbleReverseSort = new BubbleReverseSort<>();
        Integer[] array = ArrayInit.initRandom(N);
        bubbleReverseSort.doSort(array);
        List<Integer> arrayAsList = Arrays.asList(array);
        assert Ordering.natural().isOrdered(arrayAsList);
    }

    @Test
    public void insertionSortTest() {
        Sort<Integer> insertionSort = new InsertionSort<>();
        Integer[] array = ArrayInit.initRandom(N);
        insertionSort.doSort(array);
        List<Integer> arrayAsList = Arrays.asList(array);
        assert Ordering.natural().isOrdered(arrayAsList);

    }

    @Test
    public void mergeSort() {
        Sort<Integer> mergeSort = new MergeSort<>();
        Integer[] array = ArrayInit.initRandom(N);
        mergeSort.doSort(array);
        List<Integer> arrayAsList = Arrays.asList(array);
        assert Ordering.natural().isOrdered(arrayAsList);

    }

    @Test
    public void quickSort() {
        Sort<Integer> quickSort = new QuickSort<>();
        Integer[] array = ArrayInit.initRandom(N);
        quickSort.doSort(array);
        List<Integer> arrayAsList = Arrays.asList(array);
        assert Ordering.natural().isOrdered(arrayAsList);

    }
}
