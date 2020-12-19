package ru.kpfu.itis.application.models;


import javafx.scene.layout.Region;
import java.util.Objects;

public class Cell extends Region {

    private int x;
    private int y;
    private Player player;
    private boolean isEnemy;

    public Cell(int x, int y) {
        super();
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
        if (player == null){
            isEnemy = false;
            return;
        }
        if (player.isEnemy){
            isEnemy = true;
        }
    }

    public boolean isEnemy() {
        return isEnemy;
    }

    public void setEnemy(boolean enemy) {
        isEnemy = enemy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y && isEnemy == cell.isEnemy && Objects.equals(player, cell.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, player, isEnemy);
    }
}
