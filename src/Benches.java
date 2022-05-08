import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.Random;

import javax.swing.*;

import org.jfree.data.xy.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;

public class Benches {

    public static void main(String[] args) throws Exception {
        int NUMBER_OF_DIRS = 100;
        int NUMBER_OF_FILES = 100;

        long start;
        long finish;
        long [] timeAverage = new long[NUMBER_OF_DIRS];
        List<Integer> list;

        for (int i = 1; i < NUMBER_OF_DIRS; i++) {

            Path setsPath = Path.of("datasets/" + i);

            for (int j = 1; j < NUMBER_OF_FILES + 1; j++) {
                list = Files
                        .lines(Path.of(setsPath + "/" + j + ".txt"))
                        .map(x -> Integer.parseInt(x))
                        .toList();

                Integer [] integers = new Integer[list.size()];
                integers = list.toArray(integers);

                start = System.nanoTime();
                MergeSort.sort(integers, (x, y) -> x - y);
                finish = System.nanoTime();
                long div = finish - start;
                timeAverage[i] += div;

            }
            System.out.println(i);
        }

        XYSeries series = new XYSeries("MergeSort");

        for (int i = 0; i < NUMBER_OF_DIRS; i++) {
            timeAverage[i] /= NUMBER_OF_FILES;
            series.add(1000 * i, timeAverage[i]);
        }

        XYDataset xyDataset = new XYSeriesCollection(series);

        JFreeChart chart = ChartFactory
                .createXYLineChart("Сортировка", "Количество элементов", "время(нс)",
                        xyDataset,
                        PlotOrientation.VERTICAL,
                        true, true, false);
        JFrame frame = new JFrame("Statistic");

        frame.setLayout(new FlowLayout());

        frame.add(new ChartPanel(chart));

        frame.setSize(1200, 900);

        frame.show();
    }



    public static void writeDataSets() throws IOException {
        Path dirPath = Path.of("datasets");
        DataSetCreator dataSetCreator = new DataSetCreator(Integer.MAX_VALUE);
        for (int i = 1; i < 100; i++) {
            Path pathOfSetsDirectory = Path.of(dirPath + "/" + i);
            Files.createDirectory(pathOfSetsDirectory);
            dataSetCreator.setsCreate(pathOfSetsDirectory, 100, i);
        }
    }

}

class DataSetCreator {

    private int rangeOfValues;

    public DataSetCreator(int rangeOfValues) {
        this.rangeOfValues = rangeOfValues;
    }

    public void setsCreate(Path outPath, int numberOfSets, int numberOfDir) throws IOException {
        Random numberGenerator = new Random();
        for (int i = 1; i < numberOfSets + 1; i++) {
            Path pathOfFile = Path.of(outPath.toString() + "/" + i + ".txt");
            Files.createFile(pathOfFile);
            try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(pathOfFile.toString()))) {
                for (int j = 0; j < 1000 * numberOfDir; j++) {
                    printWriter.print(numberGenerator.nextInt(rangeOfValues));
                    printWriter.println();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}