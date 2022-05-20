import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MergeSort {

    static <T> void merge(int startIndex, int separatingIndex, int lastIndex,
                                 T[] array, Comparator<? super T> comp) {
        int i = startIndex;
        int j = separatingIndex + 1;
        int k = 0;
        T[] sortedSubArray = (T[]) new Object[lastIndex - startIndex + 1];
        while (i <= separatingIndex && j<= lastIndex) {
            if (comp.compare(array[i], array[j]) < 0) {
                sortedSubArray[k] = array[i];
                i++;
            } else {
                sortedSubArray[k] = array[j];
                j++;
            }
            k++;
        }

        while (i <= separatingIndex) {
            sortedSubArray[k] = array[i];
            i++;
            k++;
        }

        while (j <= lastIndex) {
            sortedSubArray[k] = array[j];
            j++;
            k++;
        }

        for (int l = startIndex; l <= lastIndex; l++) {
            array[l] = sortedSubArray[l - startIndex];
        }
    }

    static <T> void mergeSort(T[] array, Comparator<? super T> comp, int startIndex, int lastIndex) {

        if (startIndex >= lastIndex) {
            return;
        }
        int separatingIndex = (startIndex + lastIndex) / 2;

        mergeSort(array,comp, startIndex, separatingIndex);
        mergeSort(array,comp, separatingIndex + 1, lastIndex);
        merge(startIndex, separatingIndex, lastIndex, array, comp);
    }

    private static int getMaxDeep() {
        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        int maxDeep = 0;
        while (numberOfThreads > 1) {
            numberOfThreads /= 2;
            maxDeep++;
        }
        return maxDeep;
    }

    public static <T> void parallelSort(T[] array, Comparator<? super T> comp) {
        int maxDeep = getMaxDeep();
        System.out.println(maxDeep);
        ParallelMergeSort<T> parallelSort = new ParallelMergeSort<>(
                array, comp, 0, array.length - 1, 0, maxDeep);
        parallelSort.run();
    }

    public static <T> void sort(T[] array, Comparator<? super T> comp) {
        mergeSort(array, comp, 0, array.length - 1);
    }

    public static <T> void sort(List<T> list, Comparator<T> comp) {
        T[] array = (T[]) new Object[list.size()];
        sort(list.toArray(array), comp);
    }

    public static <T extends Comparable<T>> void sort (List<T> list) {
        sort(list, Comparable::compareTo);
    }

}
