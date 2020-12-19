package ru.kpfu.itis.application.models;

public class Player {

    Hero hero;
    boolean isEnemy;

    public Player(Hero hero, boolean isEnemy) {
        this.hero = hero;
        this.isEnemy = isEnemy;
    }

    public boolean isEnemy() {
        return isEnemy;
    }

    public void setEnemy(boolean enemy) {
        isEnemy = enemy;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }
}
