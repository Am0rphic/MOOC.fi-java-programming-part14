package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class ShanghaiApplication extends Application {
    
    @Override
    public void start (Stage stage) {
        
        NumberAxis xAxis = new NumberAxis(0, 100, 5);
        NumberAxis yAxis = new NumberAxis(2007, 2017, 1);
        
        xAxis.setLabel("Ranking %");
        yAxis.setLabel("Year");
        
        LineChart chart = new LineChart(yAxis, xAxis);
        chart.setTitle("Ranking in years 2007-2017");
        
        XYChart.Series newData = new XYChart.Series();
        newData.setName("Helsinki Shanghai ranking");
        newData.getData().add(new XYChart.Data(2007,73));
        newData.getData().add(new XYChart.Data(2008,68));
        newData.getData().add(new XYChart.Data(2009,72));
        newData.getData().add(new XYChart.Data(2010,72));
        newData.getData().add(new XYChart.Data(2011,74));
        newData.getData().add(new XYChart.Data(2012,73));
        newData.getData().add(new XYChart.Data(2013,76));
        newData.getData().add(new XYChart.Data(2014,73));
        newData.getData().add(new XYChart.Data(2015,67));
        newData.getData().add(new XYChart.Data(2016,56));
        newData.getData().add(new XYChart.Data(2017,56));
        
        chart.getData().add(newData);
        Scene viewOne = new Scene(chart, 900, 700);
        stage.setScene(viewOne);
        stage.show();
        
    }
    public static void main(String[] args) {
        launch(ShanghaiApplication.class);
    }

}
