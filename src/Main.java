import java.util.*;

public class Main {

    int dist(String file1, String file2) {
        int numberOfWords = 0;
        String [] word1 = file1.split("\b");
        String [] word2 = file2.split("\b");
        for (int i = 0; i < word1.length; i++) {
            for (int j = 0; j < word2.length; j++) {
                if (word1[i].equals(word2[j])) {
                    numberOfWords++;
                }
            }
        }
        return numberOfWords;
    }

    public static void main(String[] args) {
        Integer [] array = new Integer[50];
        for (Integer i = 49; i >= 0; i--) {
            array[i] = i;
        }

        MergeSort.parallelSort(array, (x,y) -> x.compareTo(y));

        System.out.println(Arrays.toString(array));

    }
}
