import java.util.Comparator;

public class ParallelMergeSort<T> extends Thread {
    private T[] array;
    private Comparator<? super T> comp;
    private int startIndex;
    private int lastIndex;
    private int deep;
    private int maxDeep;

    ParallelMergeSort(T[] array, Comparator<? super T> comp, int startIndex,
                      int lastIndex, int deep, int maxDeep){
        this.array = array;
        this.comp = comp;
        this.startIndex = startIndex;
        this.lastIndex = lastIndex;
        this.deep = deep;
        this.maxDeep = maxDeep;
    }

    @Override
    public void run() {

        if (startIndex >= lastIndex) {
            return;
        }

        int separatingIndex = (startIndex + lastIndex) / 2;

        ParallelMergeSort thread1 = new ParallelMergeSort<>(array,
                comp, startIndex, separatingIndex, ++deep, maxDeep);

        ParallelMergeSort thread2 = new ParallelMergeSort<>(array,
                comp, separatingIndex + 1, lastIndex, ++deep, maxDeep);

        if (deep < maxDeep) {
            thread1.start();
            thread2.start();
            try {
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            thread1.run();
            thread2.run();
        }

        merge();
    }

    void merge() {
        int separatingIndex = (startIndex + lastIndex) / 2;
        int i = startIndex;
        int j = separatingIndex + 1;
        int k = 0;
        T [] sortedSubArray = (T[]) new Object[lastIndex - startIndex + 1];
        while (i <= separatingIndex  && j<= lastIndex) {
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


}
