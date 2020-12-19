package ru.kpfu.itis.application.network;

import java.io.Serializable;

public class MovementPackage implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int xPlayer;
    private final int yPlayer;
    private final int xEnemy;
    private final int yEnemy;

    public MovementPackage(int xPlayer, int yPlayer, int xEnemy, int yEnemy) {
        this.xPlayer = xPlayer;
        this.yPlayer = yPlayer;
        this.xEnemy = xEnemy;
        this.yEnemy = yEnemy;
    }

    public int getxPlayer() {
        return xPlayer;
    }

    public int getyPlayer() {
        return yPlayer;
    }

    public int getxEnemy() {
        return xEnemy;
    }

    public int getyEnemy() {
        return yEnemy;
    }
}
