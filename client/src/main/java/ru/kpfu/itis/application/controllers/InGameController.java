package ru.kpfu.itis.application.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ru.kpfu.itis.application.models.GameField;

import java.net.URL;
import java.util.ResourceBundle;

public class InGameController implements Initializable {
    @FXML
    GameField gameField;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameField = new GameField();
    }

    public void setGameField(GameField gameField) {
        this.gameField = gameField;
    }
}
