/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package determination;

import static determination.Board.HEIGHT;
import static determination.Board.WIDTH;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author XDXD
 */
public class ControlPanel {
    Board board;
    public double mouseX, mouseY;
    public int invertRange;
    
    Button modularInvertButton;
    boolean modularInvert;

    public ControlPanel(Board board) {
        invertRange = 0;
        this.board = board;
        initPanel();
    }
    
    
    private void initPanel(){
       
        // container for all the controls
        
        VBox root = new VBox();
        
        
        // invert square function slider
        
        Slider invertSquareDimention = new Slider();
        invertSquareDimention.setMin(0);
        invertSquareDimention.setMax(Math.min(Board.COLUMNS, Board.ROWS));
        invertSquareDimention.setBlockIncrement(2);
        invertSquareDimention.setMajorTickUnit(2);
        invertSquareDimention.setShowTickMarks(true);
        invertSquareDimention.setMinorTickCount(0);
        invertSquareDimention.setSnapToTicks(true); 
        
        invertSquareDimention.setOnMouseClicked(e -> {
            invertRange = ((int) invertSquareDimention.getValue());
        });
        invertSquareDimention.setOnMouseDragged(e -> {
            invertRange = ((int) invertSquareDimention.getValue());
        });
        root.getChildren().add(invertSquareDimention);
        
        
        // Working with // Color scheme 
        ComboBox<Color> colorSchemeBox  = new ComboBox();
        ObservableList<Color> colors = FXCollections.observableArrayList(Board.colorScheme);
        colorSchemeBox.setItems(colors);
        root.getChildren().add(colorSchemeBox);
        
        
        
        modularInvert = false;
        modularInvertButton = new Button();
        modularInvertButton.setOnAction(e -> {
            modularInvert = !modularInvert;
        });
        root.getChildren().add(modularInvertButton);
        
        
            board.game.getScene().setOnKeyPressed(e -> {
             if (e.getCode() == KeyCode.SPACE) {
                 
                int column = (int)(mouseX / WIDTH);
                int row = (int)(mouseY / HEIGHT);
                board.invertSquare(invertRange, row, column, modularInvert);
                e.consume();
             }
             else if (e.getCode() == KeyCode.A) {
                 board.clear();
             }
             
         });
         
          board.game.getScene().setOnMouseMoved(e -> {
               mouseX = e.getX();
               mouseY = e.getY();
               
          } );
         
          board.game.getScene().setOnMouseClicked(e -> {
              if (e.getTarget() instanceof Rectangle) {
                double mX = e.getX();
                double mY = e.getY();
                int column = (int)(mX / WIDTH);
                int row = (int)(mY / HEIGHT);
                board.invertSquare(invertRange, row, column, modularInvert);
                e.consume();
              }
               
          }
          );
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        // set up the window
        
        Scene scene = new Scene(root, 200, 100);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.setX(board.getGame().getStage().getX() + board.getGame().getStage().getWidth());
        stage.setY(board.getGame().getStage().getY());
        stage.show();
        
    }
    
    
    
}
