package ru.kpfu.itis.application.models;

public class Hero {

    private int hp;
    private int attack;

    public Hero(int hp, int attack) {
        this.hp = hp;
        this.attack = attack;
    }

    public Hero() {

    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
