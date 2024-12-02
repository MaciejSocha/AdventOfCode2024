package aoc.day02;

import utils.DataConversion;
import utils.FileReader;

import java.util.List;

public class Day2 {
    public static void main(String[] args) {
        String input = FileReader.read("src/main/java/aoc/day02/input.txt");

        final int[] numberOfSafeReports = {0};
        input.lines().forEach((String line) -> numberOfSafeReports[0] += isSafe(DataConversion.convStringLineToInt(line), 1));

        System.out.println(numberOfSafeReports[0]);
    }

    private static int isSafe(List<Integer> ints, int maxRemovedLevel) {
        final int order = validateOrder(ints);
        int removedLevels = 0;

        for (int i = 0; i < ints.size() - 1; i++) {
            int firstNum = ints.get(i);
            int secondNum = ints.get(i + 1);
            if (checkIfLevelsAreNotRight(firstNum, secondNum, order)) {
                if (removedLevels < maxRemovedLevel) {
                    List<Integer> secondList = new java.util.ArrayList<>(ints.stream().toList());
                    secondList.remove(i + 1);
                    if (isSafe(secondList, 0) == 0) {
                        List<Integer> thirdList = new java.util.ArrayList<>(ints.stream().toList());
                        thirdList.remove(i);
                        return isSafe(thirdList, 0);
                    }
                } else {
                    return 0;
                }
            }
        }
        return 1;
    }

    private static boolean checkIfLevelsAreNotRight(int firstNumber, int secondNumber, int order) {
        final int maxDifference = 3;
        return Math.abs(firstNumber - secondNumber) == 0 ||
                Math.abs(firstNumber - secondNumber) > maxDifference ||
                getNumbersOrder(firstNumber, secondNumber) != order;
    }

    // 0 - decreasing, 1 - increasing
    private static int validateOrder(List<Integer> line) {
        int desc = 0;
        int incr = 0;
        for (int i = 0; i < line.size() - 1; i++) {
            if (getNumbersOrder(line.get(i), line.get(i + 1)) == 0) desc++;
            else incr++;
        }
        if (desc > incr) return 0;
        else return 1;
    }

    // 0 - decreasing, 1 - increasing
    private static int getNumbersOrder(int firstNumber, int secondNumber) {
        return firstNumber - secondNumber > 0 ? 0 : 1;
    }
}
