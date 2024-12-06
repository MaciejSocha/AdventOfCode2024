package aoc.day06;

import java.util.List;

public class Game {
    private final char[][] matrix;
    private final int rowCount;
    private final int columnCount;
    private int currentX;
    private int currentY;
    private char movingDirection;
    private int positionChanges;
    private int pointsVisited;
    private final boolean[][] visited;

    public Game(String input) {
        List<String> list = input.lines().toList();
        rowCount = list.size();
        columnCount = list.get(0).length();
        matrix = new char[rowCount][columnCount];
        visited = new boolean[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                matrix[i][j] = list.get(i).charAt(j);
                visited[i][j] = false;
            }
        }
        movingDirection = 'u';
        positionChanges = 0;
        pointsVisited = 0;
    }

    public void findStartingCoordinate() {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (matrix[i][j] == '^') {
                    currentX = j;
                    currentY = i;
                }
            }
        }
    }

    public boolean makeMove() {
        if (!canMove()) {
            System.out.println("Can't make a move!");
            return false;
        }
        if (!checkObstacle()) {
            changeDirection();
            return true;
        }
        if (positionChanges == 0) { //handle first position change
            pointsVisited++;
            visited[currentY][currentX] = true;
        }

        switch (movingDirection) {
            case 'u' -> {
                currentY -= 1;
                positionChanges++;
                if (!visited[currentY][currentX]) {
                    pointsVisited++;
                    visited[currentY][currentX] = true;
                }
                return true;
            }
            case 'd' -> {
                currentY += 1;
                positionChanges++;
                if (!visited[currentY][currentX]) {
                    pointsVisited++;
                    visited[currentY][currentX] = true;
                }
                return true;
            }
            case 'r' -> {
                currentX += 1;
                positionChanges++;
                if (!visited[currentY][currentX]) {
                    pointsVisited++;
                    visited[currentY][currentX] = true;
                }
                return true;
            }
            case 'l' -> {
                currentX -= 1;
                positionChanges++;
                if (!visited[currentY][currentX]) {
                    pointsVisited++;
                    visited[currentY][currentX] = true;
                }
                return true;
            }
        }
        return false;
    }

    public void printData() {
        System.out.println("Actual position of guard is: " + currentX + " X and: " + currentY + " Y. Moves count: " + positionChanges + " Points visited: " + pointsVisited);
    }

    public void printGame() {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    private boolean canMove() {
        switch (movingDirection) {
            case 'u' -> {
                return currentY - 1 >= 0;
            }
            case 'd' -> {
                return currentY + 1 < rowCount;
            }
            case 'r' -> {
                return currentX + 1 < columnCount;
            }
            case 'l' -> {
                return currentX - 1 >= 0;
            }
        }
        return false;
    }

    private boolean checkObstacle() {
        switch (movingDirection) {
            case 'u' -> {
                if (matrix[currentY - 1][currentX] == '#') return false;
            }
            case 'd' -> {
                if (matrix[currentY + 1][currentX] == '#') return false;
            }
            case 'r' -> {
                if (matrix[currentY][currentX + 1] == '#') return false;
            }
            case 'l' -> {
                if (matrix[currentY][currentX - 1] == '#') return false;
            }
        }
        return true;
    }

    private void changeDirection() {
        switch (movingDirection) {
            case 'u' -> {
                movingDirection = 'r';
            }
            case 'd' -> {
                movingDirection = 'l';
            }
            case 'r' -> {
                movingDirection = 'd';
            }
            case 'l' -> {
                movingDirection = 'u';
            }
        }
    }
}
