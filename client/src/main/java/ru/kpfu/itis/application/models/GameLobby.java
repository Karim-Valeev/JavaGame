package ru.kpfu.itis.application.models;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class GameLobby {

    Player clientPlayer;
    Player enemyPlayer;
    GameField gameField;
    Scene lobbyScene;

    public GameLobby(Scene lobbyScene) {
        this.lobbyScene = lobbyScene;
        init();
    }

    public void init(){

    }

    public void movePlayer(int x, int y, Player player){

    }
}
