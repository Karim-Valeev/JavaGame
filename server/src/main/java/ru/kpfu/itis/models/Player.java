package ru.kpfu.itis.models;

import ru.kpfu.itis.server.Connection;

public class Player {

    private int x;
    private int y;
    private Hero hero;
    private Connection connection;

    public Player(int x, int y, Connection connection) {
        this.x = x;
        this.y = y;
        this.connection = connection;
        hero = new Hero();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
