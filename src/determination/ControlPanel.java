/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package determination;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.util.Callback;
import javafx.scene.control.ListView;
import static determination.PlayBoard.HEIGHT;
import static determination.PlayBoard.WIDTH;
import java.util.ArrayList;
import java.util.Set;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 *
 * @author XDXD
 */
public class ControlPanel {
    PlayBoard board;
    public double mouseX, mouseY;
    public int invertRange;
    public int snapCount;
    
    Button modularInvertButton;
    boolean modularInvert;
    
    
    // combo box for picking color;
    ComboBox<Color> colorSchemeBox; 
    ObservableList<Color> colors;

    public ControlPanel(PlayBoard board) {
        invertRange = 0;
        this.board = board;
        initPanel();
    }
    
    
    private void initPanel(){
       
        // container for all the controls
        
        VBox root = new VBox();
        snapCount = 0;
        
        
        // invert square function slider
        
        Slider invertSquareDimention = new Slider();
        invertSquareDimention.setMin(0);
        invertSquareDimention.setMax(Math.min(PlayBoard.COLUMNS, PlayBoard.ROWS));
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
        
        // pick a scheme;
        ComboBox<String> schemesBox  = new ComboBox();
        schemesBox.setValue("Original");
        ObservableList<String> schemesBoxSet = FXCollections.observableArrayList(ColorSchemes.colorSchemes.keySet());
        schemesBox.setItems(schemesBoxSet); 
        root.getChildren().add(schemesBox);
        
        schemesBox.setOnAction(e -> {
            schemesBox.getValue();
            board.setColorScheme(schemesBox.getValue());
            colors = null;
            colors = FXCollections.observableArrayList(PlayBoard.colorScheme);
            colorSchemeBox.setItems(colors);
            
        });
        
        
        //ObservableList<Color> colors = FXCollections.observableArrayList(PlayBoard.colorScheme);

        
        
        
        
        // Working with // Color scheme 
        colorSchemeBox  = new ComboBox();
        colors = FXCollections.observableArrayList(PlayBoard.colorScheme);
        colorSchemeBox.setItems(colors);
        
        colorSchemeBox.setCellFactory(new Callback<ListView<Color>, ListCell<Color>>() {
            @Override 
            public ListCell<Color> call(ListView<Color> p) {
                return new ListCell<Color>() {
                    @Override
                    protected void updateItem(Color item, boolean empty) {
                        super.updateItem(item, empty);
                            if (item != null) {
                                String c = item.toString().substring(2,8);
                                setStyle("-fx-background-color: #" + c + ";");
                                setText("");
                            }
                    }
                };
            }
        });
        
        colorSchemeBox.setOnAction(e -> {
            try {
            String c = colorSchemeBox.getValue().toString().substring(2,8);
            colorSchemeBox.setStyle("-fx-background-color: #" + c + ";");
            } catch(Exception ex) { // quickfix(??)
            }
            
        });

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
             else if (e.getCode() == KeyCode.S) {
                 board.oddEvenGameStep();
               //  board.snap(snapCount);
               //  snapCount++;
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
