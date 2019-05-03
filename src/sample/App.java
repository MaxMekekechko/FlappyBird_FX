package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.Random;

    /**
     Created by Maksym Mekekechko
     **/

public class App extends Application {
    public static Pane appRoot = new Pane();
    public static Pane gameRoot = new Pane();

    public static ArrayList<Barrier> barriers = new ArrayList <>();
    Bird bird = new Bird();
    public static int score = 0;
    public Label scoreLabel = new Label ("Score: " + score );


    public Parent createContent() {
        gameRoot.setPrefSize(600, 600);

        for (int i = 0; i < 100; i++) {
            int enter = (int) (Math.random() * 100 + 50);
            int height = new Random().nextInt(600 - enter);
            Barrier barrierUp = new Barrier(height);
            barrierUp.setTranslateX(i*350+600);
            barrierUp.setTranslateY(0);
            barriers.add(barrierUp);


            Barrier barrierDown = new Barrier(600-enter-height);
            barrierDown.setTranslateX(i*350+600);
            barrierDown.setTranslateY(height+enter);
            barriers.add(barrierDown);


            gameRoot.getChildren().addAll(barrierUp, barrierDown);
        }
        appRoot.getChildren().add(scoreLabel);
        gameRoot.getChildren().add(bird);
        appRoot.getChildren().addAll(gameRoot);
        return appRoot;
    }

    public void update() {

        if(bird.velocity.getY() < 5) {
            bird.velocity = bird.velocity.add(0,1);
        }

        bird.moveX((int) bird.velocity.getX());
        bird.moveY((int)bird.velocity.getY());
        scoreLabel.setText("Score: " + score);


        bird.translateXProperty().addListener((obs,old,newValue)-> {
            int offset = newValue.intValue();
            if(offset > 200) gameRoot.setLayoutX(-(offset-200));
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene scene = new Scene(createContent());
        scene.setOnMouseClicked(event->bird.jump());
        primaryStage.setTitle("My Flappy Bird");
        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
