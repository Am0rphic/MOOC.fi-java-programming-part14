/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.ui;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Am0rphic
 */
public class UserInterface extends Application {
    public Stage stage;
    @Override
    public void start(Stage stage) {
        this.stage=stage;
        NumberFormat format = new DecimalFormat("#");
        BorderPane mainPanel = new BorderPane();
        mainPanel.setPrefSize(1000, 700);
        
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis(0,30,1);
        LineChart chart = new LineChart(yAxis, xAxis);
        
        VBox sliders = new VBox();
        BorderPane boxTop = new BorderPane();
        BorderPane boxBot = new BorderPane();
        sliders.getChildren().addAll(boxTop, boxBot);
        
        boxTop.setLeft(new Label("Monthly savings"));        
        Slider topSlider = new Slider(25,250,25);
        topSlider.setShowTickLabels(true);
        topSlider.setShowTickMarks(true);
        topSlider.setMajorTickUnit(25f);
        topSlider.setBlockIncrement(5f);
        boxTop.setCenter(topSlider);        
        //not sure, check if bugs
        Label topSliderLabel = new Label("25");
        boxTop.setRight(topSliderLabel);
        topSlider.valueProperty().addListener((change, oldValue, newValue) -> {
            topSliderLabel.setText(format.format(newValue));
        });
        
        boxBot.setLeft(new Label("Yearly interest rate"));
        Slider botSlider = new Slider(0,10,0);
        botSlider.setShowTickLabels(true);
        botSlider.setShowTickMarks(true);
        botSlider.setMajorTickUnit(10f);
        botSlider.setBlockIncrement(10f);        
        boxBot.setCenter(botSlider);
        Label botSliderLabel = new Label("0");
        boxBot.setRight(botSliderLabel);
        botSlider.valueProperty().addListener((change, oldValue, newValue) -> {
            botSliderLabel.setText(format.format(newValue));
        });
        mainPanel.setTop(sliders);
        mainPanel.setCenter(chart);
        Scene scene = new Scene(mainPanel);
        stage.setScene(scene);
        stage.show();
    }
}
