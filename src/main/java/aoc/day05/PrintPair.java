package aoc.day05;

public class PrintPair {
    private final int firstNumber;
    private final int secondNumber;

    public PrintPair(String pair) {
        this.firstNumber = Integer.parseInt(pair.split("\\|")[0]);
        this.secondNumber = Integer.parseInt(pair.split("\\|")[1]);
    }

    public boolean isPair(int first, int second) {
        return first == firstNumber && second == secondNumber;
    }

    @Override
    public String toString() {
        return "PrintPair{" +
                firstNumber +
                "|" + secondNumber +
                '}';
    }
}
