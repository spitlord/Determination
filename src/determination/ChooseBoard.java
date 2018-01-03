/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package determination;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author XDXD
 */
public class ChooseBoard {
    int choise;
    public int message() {
        
        choise = 0;
        
        Stage s = new Stage();
        s.setTitle("Which board to set up?");
        HBox h = new HBox();
        Button a, b;
        a = new Button("PlayBoard");
        b = new Button("Invertibles");
        
        
        a.setOnMouseClicked(e -> {
            choise = 1;
            s.close();
        });
        b.setOnMouseClicked(e -> {
            choise = 2;
            s.close();
        });
        
        h.getChildren().add(a);
        h.getChildren().add(b);
        Scene scene = new Scene(h);
        s.setScene(scene);
        
        s.showAndWait();
        return choise;
    }
    
}
