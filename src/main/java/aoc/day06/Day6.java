package aoc.day06;

import utils.FileReader;

public class Day6 {
    public static void main(String[] args) {
        String input = FileReader.read("src/main/java/aoc/day06/input.txt");
        Game game = new Game(input);
        game.findStartingCoordinate();
        game.printGame();
        game.printData();
        while (game.makeMove()) {
            game.printData();
        }
        game.printData();
    }
}
