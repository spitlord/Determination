/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package determination;

import invertablesmodn.Element;
import invertablesmodn.Group;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 *
 * @author XDXD
 */
public class InvertibleBoard {
    
    public static int WIDTH = 25;
    public static int HEIGHT = 25;
    int ja = 2;
    Game game;
    public static ArrayList<Color> gradient;
    Block[][] blocks;
    
    
    
    public InvertibleBoard(Game game) {
        this.game = game;
        initBoard(new Group(ja), true);
        game.getScroll().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                ja+=1;
                initBoard(new Group(ja), true);
            }
        });
    }

    

    public void initBoard(Group g, boolean addLabel) {
        
        gradient = ColorSchemes.createGradient(g.size());
        game.getGrid().getChildren().clear();
        
       
        ArrayList<Element> elements = g.getElements();
        if (addLabel == true) {
        for (int i = 0; i < elements.size(); i++) {
            Rectangle rectangle;
            rectangle = new Rectangle(WIDTH, HEIGHT);
            rectangle.setFill(gradient.get(i).desaturate().desaturate());
            game.getGrid().add(rectangle,i+1,0);
            game.getGrid().add(
                    new Label(String.valueOf(elements.get(i).getValue())),
                    i+1,0);
            
            rectangle = new Rectangle(WIDTH, HEIGHT);
            rectangle.setFill(gradient.get(i).desaturate().desaturate());
            game.getGrid().add(rectangle, 0, i+1);
            game.getGrid().add(
                    new Label(String.valueOf(elements.get(i).getValue())),
                    0,i+1);
        }
        }
        else  {
            for (int i = 0; i < elements.size(); i++) {  
            Rectangle rectangle;
            rectangle = new Rectangle(WIDTH, HEIGHT);
            rectangle.setFill(gradient.get(i).desaturate().desaturate());
            game.getGrid().add(rectangle,i+1,0);
            
            rectangle = new Rectangle(WIDTH, HEIGHT);
            rectangle.setFill(gradient.get(i).desaturate().desaturate());
            game.getGrid().add(rectangle, 0, i+1);
            }
        }
        
        if (addLabel == true) {
           for (int i = 0; i < elements.size(); i++) {
               for (int j = 0; j < elements.size(); j++) {
                    int temp; 
                    temp = elements.get(i).getValue() * elements.get(j).getValue();
                    temp = temp % g.getModulus();
                   
                    
                    // match color to the element
                    Rectangle rectangle = new Rectangle(WIDTH, HEIGHT);
                    rectangle.setFill(gradient.get(findIndex(g, temp)));
                    game.getGrid().add(rectangle,i+1,j+1);
                
                    
                    game.getGrid().add(
                    new Label(String.valueOf(temp)),
                    i+1,j+1);
               }
           }
        }
        else {
            for (int i = 0; i < elements.size(); i++) {
               for (int j = 0; j < elements.size(); j++) {
                    int temp; 
                    temp = elements.get(i).getValue() * elements.get(j).getValue();
                    temp = temp % g.getModulus();
                   
                    
                    // match color to the element
                    Rectangle rectangle = new Rectangle(WIDTH, HEIGHT);
                    rectangle.setFill(gradient.get(findIndex(g, temp)));
                    game.getGrid().add(rectangle,i+1,j+1);
               }
           }
        }
    }
       
        // game.getGrid().setAlignment(Pos.CENTER);
        
        private int findIndex(Group g, int element) {
            for (int i = 0; i < g.size(); i++) {
                if (g.getElements().get(i).getValue() == element) return i;  
            }
            
            //somethings wrong
            return -1;
        }
        
     

}
    
    

