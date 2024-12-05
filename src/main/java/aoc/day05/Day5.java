package aoc.day05;

import utils.FileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day5 {
    public static void main(String[] args) {
        String input = FileReader.read("src/main/java/aoc/day05/input.txt");
        List<PrintPair> printPairs = new ArrayList<>();
        List<List<Integer>> pages = new ArrayList<>();
        boolean arePageNumbers = false;

        for (String line : input.lines().toList()) {
            if (line.isEmpty()) {
                arePageNumbers = true;
                continue;
            }
            if (arePageNumbers) {
                List<Integer> temp = new ArrayList<>();
                for (String s : line.split(",")) {
                    temp.add(Integer.parseInt(s));
                }
                pages.add(temp);
            } else {
                printPairs.add(new PrintPair(line));
            }
        }

        boolean good = false;
        int middleSum = 0;
        int incorrectSum = 0;
        for (int i = 0; i < pages.size(); i++) {
            for (int j = 0; j < pages.get(i).size() - 1; j++) {
                for (int k = j + 1; k < pages.get(i).size(); k++) {
                    if (findPair(pages.get(i).get(j), pages.get(i).get(k), printPairs)) good = true;
                    else {
                        good = false;
                        break;
                    }
                }
                if (!good) break;
            }
            if (good) middleSum += calculateIntMedian(pages.get(i));
            else {
                List<Integer> newRecord = correctRecord(pages.get(i), printPairs);
                if (newRecord != null) incorrectSum += calculateIntMedian(newRecord);
            }
        }
        System.out.println(middleSum);
        System.out.println(incorrectSum);
    }

    private static int calculateIntMedian(List<Integer> list) {
        if (list.size() % 2 == 1)
            return list.get(list.size() / 2);
        else
            return (list.get(list.size() / 2 - 1) + list.get(list.size() / 2)) / 2;
    }

    private static List<Integer> correctRecord(List<Integer> incorrectRecord, List<PrintPair> pairs) {
        List<Integer> correctedRecord;
        for (int j = 0; j < incorrectRecord.size() - 1; j++) {
            for (int k = j + 1; k < incorrectRecord.size(); k++) {
                if (!findPair(incorrectRecord.get(j), incorrectRecord.get(k), pairs)) {
                    int newSecond = findNewPair(incorrectRecord.get(j), incorrectRecord, pairs);
                    if (newSecond < 0) {
                        return null;
                    } else if (newSecond == 0) {
                        correctedRecord = incorrectRecord;
                        Collections.swap(correctedRecord, j, k);
                        if (checkRecord(correctedRecord, pairs)) return correctedRecord;
                    } else {
                        correctedRecord = incorrectRecord;
                        Collections.swap(correctedRecord, j, correctedRecord.indexOf(newSecond));
                        if (checkRecord(correctedRecord, pairs)) return correctedRecord;
                    }
                }
            }
        }

        return null;
    }

    private static int findNewPair(int first, List<Integer> records, List<PrintPair> pairs) {
        for (int i : records) {
            if (findPair(first, i, pairs)) {
                Collections.swap(records, records.indexOf(first), records.indexOf(i));
                if (checkRecord(records, pairs)) return i;
            }
            if (findPair(i, first, pairs)) {
                return 0;
            }
        }
        return -1;
    }

    private static boolean checkRecord(List<Integer> record, List<PrintPair> pairs) {
        boolean good = false;
        for (int j = 0; j < record.size() - 1; j++) {
            for (int k = j + 1; k < record.size(); k++) {
                if (findPair(record.get(j), record.get(k), pairs)) good = true;
                else {
                    good = false;
                    break;
                }
            }
            if (!good) break;
        }
        return good;
    }

    private static boolean findPair(int first, int second, List<PrintPair> pairs) {
        return pairs.stream().anyMatch(p -> p.isPair(first, second));
    }
}
