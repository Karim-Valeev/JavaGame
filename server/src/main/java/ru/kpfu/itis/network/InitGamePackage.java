package ru.kpfu.itis.network;

import ru.kpfu.itis.models.Hero;

import java.io.Serializable;

public class InitGamePackage implements Serializable {

    private static final long serialVersionUID = 1L;

    private int xPlayer;
    private int yPlayer;
    private int xEnemy;
    private int yEnemy;

    private Hero playerHero;
    private Hero enemyHero;

    public InitGamePackage(int xPlayer, int yPlayer, int xEnemy, int yEnemy, Hero playerHero, Hero enemyHero) {
        this.xPlayer = xPlayer;
        this.yPlayer = yPlayer;
        this.xEnemy = xEnemy;
        this.yEnemy = yEnemy;
        this.playerHero = playerHero;
        this.enemyHero = enemyHero;
    }

    public int getxPlayer() {
        return xPlayer;
    }

    public void setxPlayer(int xPlayer) {
        this.xPlayer = xPlayer;
    }

    public int getyPlayer() {
        return yPlayer;
    }

    public void setyPlayer(int yPlayer) {
        this.yPlayer = yPlayer;
    }

    public int getxEnemy() {
        return xEnemy;
    }

    public void setxEnemy(int xEnemy) {
        this.xEnemy = xEnemy;
    }

    public int getyEnemy() {
        return yEnemy;
    }

    public void setyEnemy(int yEnemy) {
        this.yEnemy = yEnemy;
    }

    public Hero getPlayerHero() {
        return playerHero;
    }

    public void setPlayerHero(Hero playerHero) {
        this.playerHero = playerHero;
    }

    public Hero getEnemyHero() {
        return enemyHero;
    }

    public void setEnemyHero(Hero enemyHero) {
        this.enemyHero = enemyHero;
    }
}
