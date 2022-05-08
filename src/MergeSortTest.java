import org.junit.*;
import static org.junit.Assert.*;

public class MergeSortTest {

    public static Integer[] arrayOfIntegers = {1, 2, 3, 3, 4, 5, 7, 8, 1, 6, 6, 8, 10, 12, 12};
    public static String [] arrayOfStrings;

    @Test
    public void mergeSubArrays() {
        Integer [] arrayAfterMerge = {1, 1, 2, 3, 3, 4, 5, 6, 6, 7, 8, 8, 10, 12, 12};
        MergeSort.merge(0, 7,
                arrayOfIntegers.length - 1, arrayOfIntegers, (x, y) -> x - y);
        assertEquals(arrayAfterMerge, arrayOfIntegers);
    }
}
