package ru.kpfu.itis.application.models;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class GameField extends Parent {

    /*
    0 - empty not reachable
    1 - empty reachable
    2 - you
    3 - enemy reachable
    4 - enemy unreachable
    5 - ally
     */
    public static byte YOU = 10;
    public static byte ENEMY = 11;
    public static byte EMPTY = 0;
//    public static byte EMPTY_NREACH = 0;
//    public static byte EMPTY_REACH = 1;
//    public static byte ENEMY_REACH = 3;
//    public static byte ENEMY_NREACH = 4;

    private Cell[][] field;

    public GameField() {
        field = new Cell[10][10];
        GridPane gridPane = new GridPane();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell cell = new Cell(i, j);
                field[i][j] = cell;

            }
        }
    }

    public Cell getCell(int x, int y){
        try {
            return field[x][y];
        }catch (ArrayIndexOutOfBoundsException e){
            return null;
        }
    }

    public List<Cell> getNeighbours(Cell cell){
        int x = cell.X();
        int y = cell.Y();
        Cell cell1 = getCell(x+1, y);
        Cell cell2 = getCell(x-1, y);
        Cell cell3 = getCell(x, y+1);
        Cell cell4 = getCell(x, y-1);
        Cell cell5 = getCell(x+1, y+1);
        Cell cell6 = getCell(x+1, y-1);
        Cell cell7 = getCell(x-1, y+1);
        Cell cell8 = getCell(x-1, y+1);
        List<Cell> cells = new ArrayList<>();
        cells.add(cell1);
        cells.add(cell2);
        cells.add(cell3);
        cells.add(cell4);
        cells.add(cell5);
        cells.add(cell6);
        cells.add(cell7);
        cells.add(cell8);
        List<Cell> finalCells = new ArrayList<>();
        for (Cell fCell : cells) {
            if (fCell != null){
                finalCells.add(fCell);
            }
        }
        return finalCells;
    }

    public void setCell(int x, int y, Cell cell){
        field[x][y] = cell;
    }
}
