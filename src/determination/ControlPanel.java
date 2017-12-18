/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package determination;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author XDXD
 */
public class ControlPanel {
    Board board;

    public ControlPanel(Board board) {
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
            board.setInvertRange((int) invertSquareDimention.getValue());
        });
        invertSquareDimention.setOnMouseDragged(e -> {
            board.setInvertRange((int) invertSquareDimention.getValue());
        });
        root.getChildren().add(invertSquareDimention);
        
        
        
        
        // set up the window
        
        Scene scene = new Scene(root, 200, 50);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.setX(board.getGame().getStage().getX() + board.getGame().getStage().getWidth());
        stage.setY(board.getGame().getStage().getY());
        stage.show();
        
    }
    
    
    
}
