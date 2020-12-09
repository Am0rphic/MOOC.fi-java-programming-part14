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
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.stream.IntStream;

public class SavingsCalculatorApplication extends Application {

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
        IntStream.range(0,31).forEachOrdered( y -> {
            monthlySavings.getData().add(new XYChart.Data(y, topSlider.getValue()*12*y));
        });
        ArrayList<Double> holder = new ArrayList<>();
        topSlider.valueProperty().addListener((change, oldValue, newValue) -> {
            topSliderLabel.setText(format.format(newValue));
            monthlySavings.getData().clear();
            holder.clear();
            IntStream.range(0,31).forEachOrdered( y -> {
                holder.add(topSlider.getValue()*12*y);
                monthlySavings.getData().add(new XYChart.Data(y, topSlider.getValue()*12*y));
            });
        });
        //^part 2 implement display of non-interest accumulation of wealth (by top slider), took longer than being proud of, tried too complicated

        chart.getData().add(monthlySavings);
        
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
        ArrayList<Double> test = new ArrayList<>();
        IntStream.range(0,31).forEachOrdered( y -> {
            if (y>0) {
                test.add((test.get(y-1)+topSlider.getValue()*12)*(1+(botSlider.getValue()/100))); //+ topSlider.getValue()*12*y*botSlider.getValue()/100);
            }   else {
               // test.add(topSlider.getValue()*12*y + topSlider.getValue()*12*y*botSlider.getValue()/100);
               test.add(0.0);
            }
            savingsWithInterest.getData().add(new XYChart.Data(y, test.get(y)));
        });
        botSlider.valueProperty().addListener((change, oldValue, newValue) -> {
            botSliderLabel.setText(format.format(newValue));
            savingsWithInterest.getData().clear();
            test.clear();
            IntStream.range(0,31).forEachOrdered( y -> {
                if (y>0) {
                    test.add((test.get(y-1)+topSlider.getValue()*12)*(1+(botSlider.getValue()/100))); //+ topSlider.getValue()*12*y*botSlider.getValue()/100);
                }   else {
                   // test.add(topSlider.getValue()*12*y + topSlider.getValue()*12*y*botSlider.getValue()/100);
                   test.add(0.0);
                }
                savingsWithInterest.getData().add(new XYChart.Data(y, test.get(y)));
            });
        });
        //part 3 implement calculation of savings from part 2 with interest (via botSlider)   //NB! botslider and topslider are double, but displayed as int (aka try not to use 20 decimals)!!!
        chart.getData().add(savingsWithInterest);
        mainPanel.setTop(sliders);
        mainPanel.setCenter(chart);
        Scene scene = new Scene(mainPanel, 320, 300);
        stage.setScene(scene);
        stage.show();
    }        
    public static void main(String[] args) {
        launch(SavingsCalculatorApplication.class);
    }
}
