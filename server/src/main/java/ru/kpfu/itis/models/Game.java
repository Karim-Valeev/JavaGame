package ru.kpfu.itis.models;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Player> players;
    private GameField gameField;

    public Game() {
        players = new ArrayList<>();
        gameField = new GameField();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public GameField getGameField() {
        return gameField;
    }

    public void setGameField(GameField gameField) {
        this.gameField = gameField;
    }
}
