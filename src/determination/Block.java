/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package determination;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Block {
    
    
    
    
    public Board board;
    public boolean on;
    
    
    /// Planning to rewrite innate Shape and Rectangle class
    /// in order to save memory;
    /// problem is, that when grid is large,
    /// the program slows down.
    /// Rectagle here does not need certain things like
    /// strokeWidth, or binding properties, and other stuff
    public Rectangle rec;

    
    // total number of colors in a given color scheme
    public static int numberOfStates;
    public int stateIndex;

    /// have a substates variable vector that will accomodate for different games?
    // so that only few colors could be switched at a time, for instance
    public Block(Board board) {
        
        this.board = board;
        numberOfStates = board.getColorScheme().size();
        stateIndex = 0;
        
    }
    
//    public void switchColor(){
//        on = !on;
//        if (on) rec.setFill(onColor);
//        else rec.setFill(offColor); 
//       
//        //    if (on) turnOn();
//       // else turnOff();
//        }
    
        public void switchColor(){
        stateIndex++;
        stateIndex %= numberOfStates;
        setState(stateIndex);
        }


    
    public void turnOff() {
        on = false;
        setState(0);
    }
    
     public void turnOn() {
        on = true;
        setState(1);
    }

    public boolean isOn() {
        return on;
    }
    
   public void setState(int stateIndex) {
       this.stateIndex = stateIndex;
       rec.setFill(board.getColorScheme().get(stateIndex));
   }



}







