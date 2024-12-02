package aoc.day02;

import utils.DataConversion;
import utils.FileReader;

import java.util.List;

public class Day2 {
    public static void main(String[] args) {
        String input = FileReader.read("src/main/java/aoc/day02/input.txt");

        final int[] numberOfSafeReports = {0};
        input.lines().forEach((String line) -> {
            numberOfSafeReports[0] += isSafe(line);
        });

        System.out.println(numberOfSafeReports[0]);
    }

    private static int isSafe(String line) {
        final int maxDiff = 3;
        List<Integer> ints = DataConversion.convStringLineToInt(line);
        final int order = getNumbersOrder(ints.get(0), ints.get(1));

        for (int i = 0; i < ints.size() - 1; i++) {
            int firstNum = ints.get(i);
            int secondNum = ints.get(i + 1);
            if (Math.abs(firstNum - secondNum) == 0 ||
                    Math.abs(firstNum - secondNum) > maxDiff ||
                    getNumbersOrder(firstNum, secondNum) != order) return 0;
        }
        return 1;
    }

    // 0 - decreasing, 1 - increasing
    private static int getNumbersOrder(int firstNumber, int secondNumber) {
        return firstNumber - secondNumber > 0 ? 0 : 1;
    }
}
