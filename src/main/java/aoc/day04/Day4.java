package aoc.day04;

import utils.FileReader;

import java.util.List;

public class Day4 {
    private static int rowCount;
    private static int rowLength;

    public static void main(String[] args) {
        int totalCount = 0;
        String input = FileReader.read("src/main/java/aoc/day04/input.txt");
        List<String> rows = input.lines().toList();
        char[][] matrix = convertToMatrix(input);
        MatrixSearch searches = new MatrixSearch(matrix, rowCount, rowLength);

        int rowsCount = searches.searchRows(rows);
        System.out.println("Rows: " + rowsCount);

        int rowsBackwardsCount = searches.searchRowsBackwards(rows);
        System.out.println("Rows back: " + rowsBackwardsCount);

        int columnsCount = searches.searchColumns();
        System.out.println("Columns: "+columnsCount);

        int columnsBackwardCount = searches.searchColumnsBackwards();
        System.out.println("Columns back: "+columnsBackwardCount);

        List<String> diagonals = searches.searchRightDiagonals();
        int diagonalsRightCount = searches.countWordInList(diagonals);
        System.out.println("Diagonals right: "+diagonalsRightCount);
        int diagonalsRightBackwardsCount = searches.countWordBackwardsInList(diagonals);
        System.out.println("Diagonals right back: "+diagonalsRightBackwardsCount);

        List<String> diagonalsLeft = searches.searchLeftDiagonals();
        int diagonalsLeftCount = searches.countWordInList(diagonalsLeft);
        System.out.println("Diagonals left: "+diagonalsLeftCount);
        int diagonalsLeftBackwardsCount = searches.countWordBackwardsInList(diagonalsLeft);
        System.out.println("Diagonals left back: "+diagonalsLeftBackwardsCount);

        totalCount = rowsCount + rowsBackwardsCount + columnsCount + columnsBackwardCount + diagonalsRightCount + diagonalsRightBackwardsCount + diagonalsLeftCount + diagonalsLeftBackwardsCount;
        System.out.println(totalCount);

        int totalXCount = 0;
        for (int i = 0; i < rowCount-2; i++) {
            for (int j = 0; j < rowLength-2; j++) {
                totalXCount += searches.countXSubArray(i, j);
            }
        }

        System.out.println(totalXCount);
    }

    private static char[][] convertToMatrix(String input) {
        List<String> list = input.lines().toList();
        rowCount = list.size();
        rowLength = list.get(0).length();
        char[][] matrix = new char[list.size()][list.get(0).length()];
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).length(); j++) {
                matrix[i][j] = list.get(i).charAt(j);
            }
        }
        return matrix;
    }


}
