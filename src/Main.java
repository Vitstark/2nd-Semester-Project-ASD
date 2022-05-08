import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Integer [] ints1 = {1, 5, 9};
        Integer [] ints2 = {1, 2, 3, 4, 5, 5, 5, 8, 10, 1, 5, 9};

        MergeSort.merge(0, 8, ints2.length - 1, ints2, (x, y) -> x - y);
        System.out.println(Arrays.toString(ints2));

    }
}
