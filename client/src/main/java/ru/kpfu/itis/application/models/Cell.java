package ru.kpfu.itis.application.models;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Cell extends Rectangle {

    private int x;
    private int y;
    private Player player;
    private boolean isEnemy;

    public Cell(int x, int y) {
        super(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.x = x;
        this.y = y;
        player = null;
    }

    public int X() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int Y() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isEnemy() {
        return isEnemy;
    }

    public void setEnemy(boolean enemy) {
        isEnemy = enemy;
    }
}
