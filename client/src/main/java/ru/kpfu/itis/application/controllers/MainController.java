package ru.kpfu.itis.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ru.kpfu.itis.application.App;
import ru.kpfu.itis.screens.NewScreen;

import java.io.IOException;

public class MainController {
    @FXML
    private Button quitButton;
    @FXML
    private Button regButton;
    @FXML
    private Button infoButton;
    @FXML
    private Button field;

    @FXML
    private void clickField(ActionEvent event) throws IOException {
//        field.setText("You've clicked!");
//        App.getWindow().setTitle("Field");
//        GridPane root = new GridPane();
//        root.setMinSize(200,200);
//        root.setGridLinesVisible(true);
//        Scene fieldScene = new Scene(root);
//        for (int x = 0; x < 10; x++) {
//            for (int y = 0; y < 10; y++) {
//                Button button = new Button();
//                root.add(button, x, y);
//                button.setMaxWidth(Double.MAX_VALUE);
//                button.setMaxHeight(Double.MAX_VALUE);
//                GridPane.setHgrow(button, Priority.ALWAYS);
//                GridPane.setVgrow(button, Priority.ALWAYS);
//            }
//        }
//        Image boy = new Image("/new_images/BOY.png");
//        ImageView iv2 = new ImageView();
//        iv2.setImage(boy);
//        iv2.setFitWidth(40);
//        iv2.setFitHeight(40);
//        iv2.setPreserveRatio(true);
//        iv2.setSmooth(true);
//        iv2.setCache(true);
//        App.getWindow().setScene(fieldScene);
        NewScreen newScreen = new NewScreen("/fxml/gameField.fxml", App.getWindow());
        newScreen.newScene();
    }
}
