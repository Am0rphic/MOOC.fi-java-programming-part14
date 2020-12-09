package application;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;


public class PartiesApplication extends Application {
    @Override
    public void start(Stage stage) {
        ArrayList<Integer> years = new ArrayList<>();
        Map<String, Map<Integer, Double>> values = new HashMap<>();        
        String file = "partiesdata.tsv";
        int i =0;
        try (Scanner scan = new Scanner(Paths.get(file))) {
            while (scan.hasNextLine()) {
                Map<Integer,Double> yearValue = new HashMap<>();
                String[] row = scan.nextLine().split("\t");
                //if first row -> add years to list, year(0) remove at end
                if (i==0) {
                    for (int r=1; r<row.length; r++) {
                        years.add(Integer.valueOf(row[r]));
                    }
                 //   years.remove(0); //after this, years contains row 1 except for first element "party"
                }
                i++;
                //if past first row add (party, (year, value))
                if (i>0) {
                    for (int r = 1; r<row.length;r++) {                        
                        if (row[r].equals("-")) {
                            row[r]="0";
                        }
                        yearValue.put(years.get(r-1), Double.valueOf(row[r]));
                        
                    }
                    if (!row[0].equals("Party")) {
                        values.put(row[0], yearValue);
                    }                        
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
//        for (Map.Entry<Integer, Double> entry:yearValue.entrySet()) {
//            System.out.println(entry.getKey()+"   "+entry.getValue());
//        }
        
        NumberAxis xAxis = new NumberAxis(0, 30, 1);
        NumberAxis yAxis = new NumberAxis(1968, 2008, 1);
        
        xAxis.setLabel("Ranking %");
        yAxis.setLabel("Year");
        
        LineChart lineChart = new LineChart(yAxis, xAxis);
        lineChart.setTitle("Relative support of parties");
        
        ArrayList<String> test = new ArrayList<>();
        values.keySet().stream().forEach(party -> {
            // a different data set for every party
            XYChart.Series data = new XYChart.Series(); //new "row" data
            data.setName(party); //give current "row" data its name

            // add the party's support numbers to the data set
            values.get(party).entrySet().stream()   //stream in stream (so going through stuff from PARTY
                    .filter(value -> value.getValue()!=0) //ignore the "-"
                    .forEach(pair -> {
                data.getData().add(new XYChart.Data(pair.getKey(), pair.getValue()));  //add data points (year, id)
            //    test.add(pair.toString());
            });

            // and add the data set to the chart
            lineChart.getData().add(data);
        });
        System.out.println(test);
        Scene scene = new Scene(lineChart, 1000, 1000);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(PartiesApplication.class);
    }
}