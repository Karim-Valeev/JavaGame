package ru.kpfu.itis.models;

import java.io.Serializable;

public class Hero implements Serializable {

    private static final long serialVersionUID = 1L;

    private int hp;
    private int attack;

    public Hero(int hp, int attack) {
        this.hp = hp;
        this.attack = attack;
    }

    public Hero() {
        hp = 100;
        attack = 10;
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
