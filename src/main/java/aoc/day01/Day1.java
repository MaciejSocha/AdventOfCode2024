package aoc.day01;

import utils.FileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 {
    public static void main(String[] args) {
        String input = FileReader.read("src/main/java/aoc/day01/input.txt");
        List<Integer> leftColumn = new ArrayList<>();
        List<Integer> rightColumn = new ArrayList<>();

        input.lines().forEach((String e) -> {
            leftColumn.add(Integer.valueOf(e.split("\\s+")[0]));
            rightColumn.add(Integer.valueOf(e.split("\\s+")[1]));
        });

        Collections.sort(leftColumn);
        Collections.sort(rightColumn);

        int sum = 0;
        for(int i=0;i< leftColumn.size();i++) {
            sum += Math.abs(leftColumn.get(i) - rightColumn.get(i));
        }

        System.out.println(sum);

        int similaritySum = 0;
        for (int number : leftColumn) {
            similaritySum += number * Collections.frequency(rightColumn, number);
        }

        System.out.println(similaritySum);
    }
}
