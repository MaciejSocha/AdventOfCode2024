package aoc.day03;

import utils.FileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) {
        String input = FileReader.read("src/main/java/aoc/day03/input.txt");
        List<String> matches = excludeMultiplicationsFromData(input);

        long sum = 0;
        for (String match : matches) {
            sum += multipleSingleMul(match);
        }
        System.out.println(sum);
    }

    private static List<String> excludeMultiplicationsFromData(String inputData) {
        List<String> matches = new ArrayList<>();
        Pattern pattern = Pattern.compile("(mul\\(\\d+,\\d+\\))?");
        Matcher matcher = pattern.matcher(inputData);

        while (matcher.find()) {
            matches.add(matcher.group());
        }

        matches.removeIf(item -> item == null || item.isEmpty());
        return matches;
    }

    private static int multipleSingleMul(String mulOperation) {
        String firstNumber = mulOperation.split(",")[0];
        String secondNumber = mulOperation.split(",")[1];

        Pattern pattern = Pattern.compile("(\\d+)");
        Matcher matcher = pattern.matcher(firstNumber);

        matcher.find();
        firstNumber = matcher.group();

        matcher = pattern.matcher(secondNumber);
        matcher.find();
        secondNumber = matcher.group();

        return Integer.parseInt(firstNumber) * Integer.parseInt(secondNumber);
    }
}
