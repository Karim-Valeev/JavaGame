package ru.kpfu.itis.application.models;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class GameLogic {

    private GameField gameField;

    public GameLogic() {
    }

    public GameField getGameField() {
        return gameField;
    }

    public void setGameField(GameField gameField) {
        this.gameField = gameField;
    }
}
