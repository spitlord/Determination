package determination;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class Game extends Application {
    InvertibleBoard secondaryBoard;    
    ScrollPane scroll;
    Board mainBoard;
    GridPane grid;
    Scene scene;
    Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

   
    @Override
    public void start(Stage primaryStage) {
        
        this.primaryStage = primaryStage;
        

        scroll  = new ScrollPane();
       
        grid = new GridPane();
        grid.setHgap(0);
        grid.setVgap(0);
        
        scroll.setContent(grid);
       
        int x = 0 ;
        switch (x) {
            case 1:
                break;
            default:
        }
        
        // mainBoard = new Board(this);
       
        secondaryBoard = new InvertibleBoard(this);
       
        scene = new Scene(scroll, grid.getPrefWidth(), grid.getPrefHeight());
        primaryStage.setScene(scene);
        primaryStage.show(); 
        
        
       // ControlPanel controls = new ControlPanel(mainBoard);
        primaryStage.toFront();
        
       
        
       

       
} 

    public Scene getScene() {
        return scene;
    }
    
    public Stage getStage() {
        return primaryStage;
    }

    public GridPane getGrid() {
        return grid;
    }

    public ScrollPane getScroll() {
        return scroll;
    }

    
    
    
   
    
    
    
    

}


        
    