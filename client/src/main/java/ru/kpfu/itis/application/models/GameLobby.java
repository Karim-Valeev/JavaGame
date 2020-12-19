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
        clientPlayer = new Player();
        enemyPlayer = new Player();
        gameField.getCell(clientPlayer.getX(), clientPlayer.getY()).setPlayer(clientPlayer);
        gameField.getCell(enemyPlayer.getX(), enemyPlayer.getY()).setPlayer(enemyPlayer);
        gameField.getCell(enemyPlayer.getX(), enemyPlayer.getY()).setEnemy(true);
    }

    public void movePlayer(int x, int y, Player player){

        player.setX(x);
        player.setY(y);

    }

    public void repaint(){

    }
}
