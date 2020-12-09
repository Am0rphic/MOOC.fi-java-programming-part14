package application;

import application.ui.UserInterface;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.stream.IntStream;

public class SavingsCalculatorApplication2 extends Application {

    @Override
    public void start(Stage stage) {
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
        chart.setId("Savings");
        XYChart.Series monthlySavings = new XYChart.Series();
        monthlySavings.setName("Savings w/o interest");
        topSlider.valueProperty().addListener((change, oldValue, newValue) -> {
            topSliderLabel.setText(format.format(newValue));
        });
        //part 2 implement display of non-interest accumulation of wealth (by top slider)

        
        
        boxBot.setLeft(new Label("Yearly interest rate"));
        Slider botSlider = new Slider(0,10,0);
        botSlider.setShowTickLabels(true);
        botSlider.setShowTickMarks(true);
        botSlider.setMajorTickUnit(10f);
        botSlider.setBlockIncrement(10f);        
        boxBot.setCenter(botSlider);
        Label botSliderLabel = new Label("0");
        boxBot.setRight(botSliderLabel);
        XYChart.Series savingsWithInterest = new XYChart.Series();
        savingsWithInterest.setName("Savings with interest");
        botSlider.valueProperty().addListener((change, oldValue, newValue) -> {
            botSliderLabel.setText(format.format(newValue));
        });
        ArrayList<Integer> test = new ArrayList<>();
        //part 3 implement calculation of savings from part 2 with interest (via botSlider)   //NB! botslider and topslider are double, but displayed as int (aka try not to use 20 decimals)!!!
        IntStream.range(0,31).forEachOrdered( y -> {
            if (y>0) {
                test.add(test.get(y-1)+topSlider.valueProperty().intValue()*12*y + topSlider.valueProperty().intValue()*12*y*botSlider.valueProperty().intValue()/100);
            }   else {
                test.add(topSlider.valueProperty().intValue()*12*y + topSlider.valueProperty().intValue()*12*y*botSlider.valueProperty().intValue()/100);
            }
            savingsWithInterest.getData().add(new XYChart.Data(y, test.get(y)));
        });
        chart.getData().add(savingsWithInterest);
        mainPanel.setTop(sliders);
        mainPanel.setCenter(chart);
        Scene scene = new Scene(mainPanel);
        stage.setScene(scene);
        stage.show();
    }        
    public static void main(String[] args) {
        launch(SavingsCalculatorApplication.class);
    }
}
