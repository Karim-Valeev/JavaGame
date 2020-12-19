package ru.kpfu.itis.application.models;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GameField extends Parent {

    private GridPane gridPane;

    private Cell[][] field;

    private Cell player;
    private Cell enemy;

    public GameField() {
        field = new Cell[10][10];
        gridPane = new GridPane();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell cell = new Cell(i, j);
                cell.setOnMouseEntered(mouseEvent -> {
                    cell.setStyle(cell.getStyle().concat(";-fx-border-color: lightgreen"));
                });
                cell.setOnMouseExited(mouseEvent -> {
                    cell.setStyle(cell.getStyle().concat(";-fx-border-color: lightgray"));
                });
                field[i][j] = cell;
                gridPane.add(cell, i, j);
            }
        }
        Hero baseHero = new Hero(100, 10);
        Player player = new Player(baseHero, false);
        Player enemy = new Player(baseHero, true);
        this.player = getCell(0, 0);
        this.player.setPlayer(player);
        this.enemy = getCell(9, 9);
        this.enemy.setPlayer(enemy);
        gridPane.setVisible(true);
        this.getChildren().add(gridPane);
        renewMap();
    }

    public Cell getCell(int x, int y) {
        try {
            return field[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public List<Cell> getNeighbours(Cell cell) {
        int x = cell.X();
        int y = cell.Y();
        Cell cell1 = getCell(x + 1, y);
        Cell cell2 = getCell(x - 1, y);
        Cell cell3 = getCell(x, y + 1);
        Cell cell4 = getCell(x, y - 1);
        Cell cell5 = getCell(x + 1, y + 1);
        Cell cell6 = getCell(x + 1, y - 1);
        Cell cell7 = getCell(x - 1, y + 1);
        Cell cell8 = getCell(x - 1, y + 1);
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
            if (fCell != null) {
                finalCells.add(fCell);
            }
        }
        return finalCells;
    }

    public void setCell(int x, int y, Cell cell) {
        field[x][y] = cell;
    }

    public void repaint() {
        for (Cell[] cells : field) {
            for (int j = 0; j < cells.length; j++) {
                Cell cell = cells[j];
                if (cell.getPlayer() != null) {
                    if (cell.isEnemy()) {
                        cell.setStyle("-fx-background-color: lightcoral;" +
                                " -fx-border-color: lightgray; -fx-border-width: 3;");
                        continue;
                    } else {
                        cell.setStyle("-fx-background-color: lightskyblue;" +
                                "-fx-border-color: lightgray; -fx-border-width: 3;");
                        continue;
                    }
                }
                cell.setStyle("-fx-border-color: lightgray; -fx-border-width: 3; -fx-background-color: white;" +
                        "-fx-min-width: 50; -fx-min-height: 50");
            }
        }
        List<Cell> neighbors = getPlayerNeighbors();
        for (Cell cell : neighbors){
            cell.setStyle("-fx-border-color: lightgray; -fx-border-width: 3; -fx-background-color: lightgreen;" +
                    "-fx-min-width: 50; -fx-min-height: 50");
        }
    }

    public void replaceHandlers(){
        for (Cell[] cells : field){
            for (int i = 0; i < cells.length; i++) {
                Cell cell = cells[i];
                cell.setOnMouseClicked(null);
            }
        }
        List<Cell> neighbors = getPlayerNeighbors();
        for (Cell cell : neighbors){
            cell.setOnMouseClicked(mouseEvent -> {
                movePlayer(cell.X(), cell.Y(), cell);
            });
        }
    }

    private List<Cell> getPlayerNeighbors(){
        for (Cell[] cells : field){
            for (int i = 0; i < cells.length; i++) {
                Cell cell = cells[i];
                if (cell.getPlayer() != null && !cell.isEnemy()){
                    return getNeighbours(cell);
                }
            }
        }
        throw new IllegalStateException("No player");
    }

    public void movePlayer(int x, int y, Cell newPlayer){
        newPlayer.setPlayer(player.getPlayer());
        player.setPlayer(null);
        player = newPlayer;
        renewMap();
    }

    public void renewMap(){
        repaint();
        replaceHandlers();
    }
}
