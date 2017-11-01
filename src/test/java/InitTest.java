import com.andrew.fillers.ArrayInit;
import com.google.common.collect.Ordering;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class InitTest {

    private final int N = 100;

    @Test
    public void initRandomTest() {
        Integer[] array = ArrayInit.initRandom(N);
        List<Integer> arrayAsList = Arrays.asList(array);

        assert array.length == N;
        assert !Ordering.arbitrary().isOrdered(arrayAsList);

    }

    @Test
    public void initTest() {
        Integer[] array = ArrayInit.init(N);
        List<Integer> arrayAsList = Arrays.asList(array);

        assert array.length == N;
        assert Ordering.natural().isOrdered(arrayAsList);

    }

    @Test
    public void initPlusXTest() {
        Integer[] array = ArrayInit.initPlusX(N);
        List<Integer> arrayAsList = Arrays.asList(array);

        assert array.length == N;
        assert !Ordering.arbitrary().isOrdered(arrayAsList);

    }

    @Test
    public void initReverseTest() {
        Integer[] array = ArrayInit.initReverse(N);
        List<Integer> arrayAsList = Arrays.asList(array);

        assert array.length == N;
        assert !Ordering.arbitrary().isOrdered(arrayAsList);

    }
}
