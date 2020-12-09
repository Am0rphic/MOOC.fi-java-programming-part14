package collage;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CollageApplication extends Application {

    @Override
    public void start(Stage stage) {

        // the example opens the image, creates a new image, and copies the opened image
        // into the new one, pixel by pixel
        Image sourceImage = new Image("file:monalisa.png");

        PixelReader imageReader = sourceImage.getPixelReader();

        int width = (int) sourceImage.getWidth();
        int height = (int) sourceImage.getHeight();

        WritableImage targetImage = new WritableImage(width, height);
        PixelWriter imageWriter = targetImage.getPixelWriter();

//        int yCoordinate = 0;
//        while (yCoordinate < height) {
//            int xCoordinate = 0;
//            while (xCoordinate < width) {
//
//                Color color = imageReader.getColor(xCoordinate, yCoordinate);
//                double red = color.getRed();
//                double green = color.getGreen();
//                double blue = color.getBlue();
//                double opacity = color.getOpacity();
//
//                Color newColor = new Color(red, green, blue, opacity);
//
//                imageWriter.setColor(xCoordinate, yCoordinate, newColor);
//
//                xCoordinate++;
//            }
//
//            yCoordinate++;
//        }
//        ImageView image = new ImageView(targetImage);

        Pane pane = new Pane();
    //    pane.getChildren().add(image);
        
//        //topLeft
//        yCoordinate = 0;
//        while (yCoordinate < height) {
//            int xCoordinate = 0;
//            while (xCoordinate < width) {
//
//                Color color = imageReader.getColor(xCoordinate, yCoordinate);
//                double red = color.getRed();
//                double green = color.getGreen();
//                double blue = color.getBlue();
//                double opacity = color.getOpacity();
//
//                Color newColor = new Color(red, green, blue, opacity);
//
//                imageWriter.setColor(xCoordinate/2, yCoordinate/2, newColor);
//
//                xCoordinate++;
//            }
//
//            yCoordinate++;
//        }
//        ImageView imageTopLeft = new ImageView(targetImage);
        
//        //topRight
//        yCoordinate = 0;
//        while (yCoordinate < height) {
//            int xCoordinate = 0;
//            while (xCoordinate < width) {
//
//                Color color = imageReader.getColor(xCoordinate, yCoordinate);
//                double red = color.getRed();
//                double green = color.getGreen();
//                double blue = color.getBlue();
//                double opacity = color.getOpacity();
//
//                Color newColor = new Color(red, green, blue, opacity);
//
//                imageWriter.setColor(width/2+xCoordinate/2, yCoordinate/2, newColor);
//
//                xCoordinate++;
//            }
//
//            yCoordinate++;
//        }
//        ImageView imageTopRight = new ImageView(targetImage);
        
   //     ImageView imageTopLeft = squishImage(imageReader, imageWriter, targetImage, height, width, "topLeft");
   //     ImageView imageTopRight = squishImage(imageReader, imageWriter, targetImage, height, width, "topRight");
   //     ImageView imageBotLeft = squishImage(imageReader, imageWriter, targetImage, height, width, "botLeft");
        ImageView imageBotRight = squishImage(imageReader, imageWriter, targetImage, height, width, "botRight");        
        pane.getChildren().add(imageBotRight);  //imageTopLeft, imageTopRight, imageBotLeft, 

        stage.setScene(new Scene(pane));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public static ImageView squishImage(PixelReader imageReader, PixelWriter imageWriter, WritableImage targetImage, int height, int width, String position) {
        int yCoordinate = 0;
        while (yCoordinate < height) {
            int xCoordinate = 0;
            while (xCoordinate < width) {

                Color color = imageReader.getColor(xCoordinate, yCoordinate);
                double red = 1-color.getRed();
                double green = 1-color.getGreen();
                double blue = 1-color.getBlue();
                double opacity = color.getOpacity();

                Color newColor = new Color(red, green, blue, opacity);
                if (xCoordinate%2==0 && yCoordinate%2==0) {
            //    if (position.equals("topLeft")) {
                    imageWriter.setColor(xCoordinate/2, yCoordinate/2, newColor);
             //   }   else if (position.equals("topRight")) {
                    imageWriter.setColor(width/2+xCoordinate/2, yCoordinate/2, newColor);
             //   }   else if (position.equals("botLeft")) {
                    imageWriter.setColor(xCoordinate/2, height/2+yCoordinate/2, newColor);
             //   }   else if (position.equals("botRight")) {
                    imageWriter.setColor(width/2+xCoordinate/2, height/2+yCoordinate/2, newColor);
            //    }
                }    

                xCoordinate++;
            }
        
            yCoordinate++;

        }
        return new ImageView(targetImage);        
    }
}
