import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Random random = new Random();
        Integer [] integers = new Integer[20];
        for (int i = 0; i < 20; i++) {
            integers[i] = random.nextInt(30) - 15;
        }

        System.out.println(Arrays.toString(integers));

        MergeSort.sort(integers, (x,y) -> x - y);

        System.out.println(Arrays.toString(integers));

    }
}
