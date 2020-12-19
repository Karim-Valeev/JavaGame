package ru.kpfu.itis.application.controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.kpfu.itis.Game;
import ru.kpfu.itis.application.App;
import ru.kpfu.itis.screens.NewScreen;

import java.io.IOException;

public class MainController {
    @FXML
    private Button quitButton;
    @FXML
    private Button regButton1;
    @FXML
    private Button infoButton;
    @FXML
    private Button field;

    @FXML
    private void clickReg(ActionEvent event) throws IOException {
        regButton1.setText("You've clicked!");
        NewScreen screen = new NewScreen("/fxml/registration.fxml", App.getWindow());
        screen.newScene();
    }

    @FXML
    private void clickField(ActionEvent event) throws IOException {
        field.setText("You've clicked!");
        App.getWindow().setTitle("Field");
        GridPane root = new GridPane();
        root.setMinSize(200,200);
        root.setGridLinesVisible(true);
        Scene fieldScene = new Scene(root);
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                Button button = new Button();
                root.add(button, x, y);
                button.setMaxWidth(Double.MAX_VALUE);
                button.setMaxHeight(Double.MAX_VALUE);
                GridPane.setHgrow(button, Priority.ALWAYS);
                GridPane.setVgrow(button, Priority.ALWAYS);
            }
        }
        Image boy = new Image("/new_images/BOY.png");
        ImageView iv2 = new ImageView();
        iv2.setImage(boy);
        iv2.setFitWidth(40);
        iv2.setFitHeight(40);
        iv2.setPreserveRatio(true);
        iv2.setSmooth(true);
        iv2.setCache(true);
        App.getWindow().setScene(fieldScene);
    }
}
