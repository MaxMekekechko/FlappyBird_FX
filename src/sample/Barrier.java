package sample;

import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;

public class Barrier extends Pane {

    Rectangle rect;
    public int height;
    public Barrier (int height) {

        this.height = height;
        rect = new Rectangle(20,height);

        getChildren().add(rect);
    }



}
