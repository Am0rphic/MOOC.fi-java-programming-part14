package smiley;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.FillRule;
import javafx.stage.Stage;

public class SmileyApplication extends Application {

    @Override
    public void start(Stage window) {
        BorderPane layout = new BorderPane();
        Canvas canvas = new Canvas(800, 800);
        layout.setCenter(canvas);
        GraphicsContext painting = canvas.getGraphicsContext2D();
        painting.setFill(Color.WHITE);
        painting.fillRect(0, 0, 800, 800);
        painting.setFill(Color.BLACK);
        painting.fillRect(200, 100, 100, 100);
        painting.fillRect(500, 100, 100, 100);
        painting.fillRect(100, 400, 100, 100);
        painting.fillRect(200, 500, 100, 100);
        painting.fillRect(300, 500, 100, 100);
        painting.fillRect(400, 500, 100, 100);
        painting.fillRect(500, 500, 100, 100);
        painting.fillRect(600, 400, 100, 100);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(SmileyApplication.class);
    }

}
