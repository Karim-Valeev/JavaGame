package ru.kpfu.itis;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import ru.kpfu.itis.entities.Sprite;
import ru.kpfu.itis.screens.NewScreen;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class Game extends Application {
    private static Stage window;
    Scene scene, scene1;

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
    @FXML
    private void exit(ActionEvent event) {
        event.consume();
        System.out.println("Hello, World!");
        Platform.exit();
    }
    @FXML
    private void regScene(ActionEvent event) throws IOException {
        event.consume();

        NewScreen screen = new NewScreen("/fxml/registration.fxml", Game.window);
        screen.newScene();

    }


    @FXML
    private void gameCanvas(ActionEvent event) throws IOException {
        event.consume();

        window.setTitle( "GAME" );


//        Todo working with ImageViews
//        Todo стрелять через ПРОБЕЛ

        Group root = new Group();
        Scene theScene = new Scene( root );

        Canvas canvas = new Canvas( 512, 512 );
        root.getChildren().add( canvas );

        ArrayList<String> input = new ArrayList<String>();


//        ОЧЕРЕДИ ДВИЖЕНИЙ ТАНКА
        theScene.setOnKeyPressed(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        if ( !input.contains(code) )
                            input.add( code );
                    }
                });

        theScene.setOnKeyReleased(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        input.remove( code );
                    }
                });


        GraphicsContext gc = canvas.getGraphicsContext2D();


        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc.setFont( theFont );
        gc.setFill( Color.GREEN );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(1);

        Sprite player = new Sprite();
        player.setImage("/images/player.png");
//        player.resizeImage(64,64);
        player.setPosition(200, 0);

        Sprite enemy = new Sprite();
        enemy.setImage("/images/enemy.png");
//        player.resizeImage(64,64);
        enemy.setPosition(100, 100);
        enemy.render(gc);

        ArrayList<Sprite> moneybagList = new ArrayList<Sprite>();

        for (int i = 0; i < 15; i++)
        {
            Sprite moneybag = new Sprite();
            moneybag.setImage("/images/moneybag.png");
            double px = 350 * Math.random() + 50;
            double py = 350 * Math.random() + 50;
            moneybag.setPosition(px,py);
            moneybagList.add( moneybag );
        }

        LongValue lastNanoTime = new LongValue( System.nanoTime() );

        IntValue score = new IntValue(0);

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                // calculate time since last update.
                if (score.value * 100 == 500) {
                    System.out.println("VICTORY");
                    System.exit(0);
                }
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;

                // game logic

                player.setVelocity(0, 0);
                if (input.contains("LEFT"))
                    player.addVelocity(-100, 0);
                if (input.contains("RIGHT"))
                    player.addVelocity(100, 0);
                if (input.contains("UP"))
                    player.addVelocity(0, -100);
                if (input.contains("DOWN"))
                    player.addVelocity(0, 100);

                System.out.println(" Before update "+player.getPositionX() + " : " + player.getPositionY() +"\n");
                player.update(elapsedTime);
                System.out.println(" After update " + player.getPositionX() + " : " + player.getPositionY() );
                // collision detection

                Iterator<Sprite> moneybagIter = moneybagList.iterator();
                while (moneybagIter.hasNext()) {
                    Sprite moneybag = moneybagIter.next();
                    if (player.intersects(moneybag)) {
                        moneybagIter.remove();
                        score.value++;
                    }
                }

                // render
                gc.clearRect(0, 0, 512, 512);
                player.render(gc);
                enemy.render(gc);

                for (Sprite moneybag : moneybagList)
                    moneybag.render(gc);

                String pointsText = "Cash: $" + (100 * score.value);
                gc.fillText(pointsText, 360, 36);
                gc.strokeText(pointsText, 360, 36);
            }
        }.start();
        window.setScene(theScene);
//        window.show();

    }

    @FXML
    private void field(ActionEvent event){
        event.consume();

        Group nodes = new Group();

        Scene fieldScene = new Scene( nodes );
        window.setTitle("Field");
//        ПАЧАНЫ
//        Размер поля 500х500 px
//        Размер квадрата 50x50 , по сути 40 на 40 из-за отступов
//        Здесь идет чистая геометрия и матеша, на бумажке чекайте координаты
        
        Rectangle[][] squares = new Rectangle[10][10];
        for(int x = 0; x<10; x++){
            for(int y = 0; y<10;y++){
                Rectangle rec = new Rectangle(5 + 50*x, 5+50*y, 40,40);
                rec.setFill(Color.RED);
//                Можно и нужно скорее всего только на клик сделать
//                rec.setOnMouseClicked();
                rec.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(rec.getFill().equals(Color.RED)){
                            rec.setFill(Color.GREEN);
                        } else {
                            rec.setFill(Color.RED);
                        }

                    }
                });
                squares[x][y] = rec;
                nodes.getChildren().add(rec);
            }
        }
        window.setScene( fieldScene );
    }

    @FXML
    private void operator(ActionEvent event){
        event.consume();
    }


}
