package ru.kpfu.itis.application;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.kpfu.itis.screens.NewScreen;

public class App extends Application {
    private static Stage window;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setMinWidth(500);
        window.setMinHeight(535);

        NewScreen main = new NewScreen("/fxml/mainPage.fxml", window);
        main.newScene();
        window.setTitle("A simple FXML Example");
        window.show();
    }
}
