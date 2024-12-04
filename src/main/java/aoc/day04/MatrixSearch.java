package aoc.day04;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MatrixSearch {
    private final char[][] matrix;
    private final int rowCount;
    private final int rowLength;

    public MatrixSearch(char[][] matrix, int rowCount, int rowLength) {
        this.matrix = matrix;
        this.rowCount = rowCount;
        this.rowLength = rowLength;
    }

    public int searchRows(List<String> rows) {
        int count = 0;
        for (String row : rows) {
            if (row.contains("XMAS")) {
                count += StringUtils.countMatches(row, "XMAS");
            }
        }
        return count;
    }

    public int searchRowsBackwards(List<String> rows) {
        int count = 0;
        for (String row : rows) {
            if (row.contains("SAMX")) {
                count += StringUtils.countMatches(row, "SAMX");
            }
        }
        return count;
    }

    public int searchColumns() {
        String targetWord = "XMAS";
        int count = 0;
        String word = "";
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < rowCount - 3; j++) {
                word = String.valueOf(matrix[j][i]) + String.valueOf(matrix[j + 1][i]) +
                        String.valueOf(matrix[j + 2][i] + String.valueOf(matrix[j + 3][i]));
                if (word.equals(targetWord)) count++;
            }
        }
        return count;
    }

    public int searchColumnsBackwards() {
        String targetWord = "SAMX";
        int count = 0;
        String word = "";
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < rowCount - 3; j++) {
                word = String.valueOf(matrix[j][i]) + String.valueOf(matrix[j + 1][i]) +
                        String.valueOf(matrix[j + 2][i] + String.valueOf(matrix[j + 3][i]));
                if (word.equals(targetWord)) count++;
            }
        }
        return count;
    }

    public List<String> searchRightDiagonals() {
        List<String> listOfDiagonals = new ArrayList<>();
        StringBuilder diagonal = new StringBuilder();
        for (int i = 1; i <= (rowCount + rowLength - 1); i++) {
            int start = Math.max(0, i - rowCount);
            int number = Math.min(Math.min(i, (rowLength - start)), rowCount);
            for (int j = 0; j < number; j++) {
                diagonal.append(matrix[Math.min(rowCount, i) - j - 1][start + j]);
            }
            listOfDiagonals.add(diagonal.toString());
            diagonal = new StringBuilder();
        }
        return listOfDiagonals;
    }

    public List<String> searchLeftDiagonals() {
        List<String> listOfDiagonals = new ArrayList<>();
        StringBuilder diagonal = new StringBuilder();
        for (int i = 1; i <= (rowCount + rowLength - 1); i++) {
            int start = Math.max(0, i - rowCount);
            int number = Math.min(Math.min(i, (rowLength - start)), rowCount);
            for (int j = number - 1; j >= 0; j--) {
                int first = rowCount - 1 - (Math.min(rowCount, i) - j - 1);
                int second = start + j;
                diagonal.append(matrix[first][second]);
            }
            listOfDiagonals.add(diagonal.toString());
            diagonal = new StringBuilder();
        }
        return listOfDiagonals;
    }

    public int countWordInList(List<String> list) {
        int count = 0;
        for (String diagonal : list) {
            if (diagonal.contains("XMAS")) {
                count += StringUtils.countMatches(diagonal, "XMAS");
            }
        }
        return count;
    }

    public int countWordBackwardsInList(List<String> list) {
        int count = 0;
        for (String diagonal : list) {
            if (diagonal.contains("SAMX")) {
                count += StringUtils.countMatches(diagonal, "SAMX");
            }
        }
        return count;
    }

    public int countXSubArray(int i, int j) {
        String d1 = String.valueOf(matrix[i][j]) + String.valueOf(matrix[i + 1][j + 1]) + String.valueOf(matrix[i + 2][j + 2]);
        String d2 = String.valueOf(matrix[i][j + 2]) + String.valueOf(matrix[i + 1][j + 1]) + String.valueOf(matrix[i + 2][j]);

        return ((d1.equals("MAS") || d1.equals("SAM")) && (d2.equals("MAS") || d2.equals("SAM"))) ? 1 : 0;
    }
}
