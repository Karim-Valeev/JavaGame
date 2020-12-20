package ru.kpfu.itis.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import ru.kpfu.itis.application.App;
import ru.kpfu.itis.application.client.GameClient;
import ru.kpfu.itis.application.client.exceptions.GameClientException;
import ru.kpfu.itis.application.client.listeners.WaitGameListener;
import ru.kpfu.itis.application.models.GameField;
import ru.kpfu.itis.protocol.Message;
import ru.kpfu.itis.screens.NewScreen;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private GameClient gameClient;

    @FXML
    private Button field;

    @FXML
    private GameField gameField;

    @FXML
    private void clickField(ActionEvent event) throws IOException {
        NewScreen newScreen = new NewScreen("/fxml/gameField.fxml", App.getWindow());
        newScreen.newScene();
    }

    public void requestGame(ActionEvent actionEvent){
        try {
            gameClient.registerListener(new WaitGameListener());
            gameClient.connect();
            gameClient.sendMessage(Message.createMessage(Message.TYPE_INIT_CONNECTION, new byte[]{0}));
            gameClient.sendMessage(Message.createMessage(Message.TYPE_REQ_GAME, new byte[]{0}));
            field.setVisible(false);
        } catch (GameClientException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameField = new GameField();
        try {
            gameClient = new GameClient(InetAddress.getLocalHost(), 11903, gameField);
        } catch (UnknownHostException e) {
            throw new IllegalStateException(e);
        }
    }
}
