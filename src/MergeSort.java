import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MergeSort {

    public static <T> void merge(int startIndex, int mediumIndex, int finishIndex,
                                 T[] array, Comparator<? super T> comp) {
        int i = startIndex;
        int j = mediumIndex + 1;
        int k = 0;
        T [] sortedSubArray = (T[]) new Object[finishIndex - startIndex + 1];
        while (i <= mediumIndex && j<= finishIndex) {
            if (comp.compare(array[i], array[j]) < 0) {
                sortedSubArray[k] = array[i];
                i++;
            } else {
                sortedSubArray[k] = array[j];
                j++;
            }
            k++;
        }

        while (i <= mediumIndex) {
            sortedSubArray[k] = array[i];
            i++;
            k++;
        }

        while (j <= finishIndex) {
            sortedSubArray[k] = array[j];
            j++;
            k++;
        }

        for (int l = startIndex; l <= finishIndex; l++) {
            array[l] = sortedSubArray[l - startIndex];
        }
    }

    public static <T> void sort(T[] array, Comparator<? super T> comp) {
        mergeSort(array, comp, 0, array.length - 1);
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
}
