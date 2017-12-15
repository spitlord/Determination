/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package determination;

import java.util.Vector;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Block {
    
    public boolean on;
    public Rectangle rec;
    public Vector<BlockState> states;
    public final Color onColor = Color.ORANGE;
    public final Color offColor = Color.BLACK;
    public int stateIndex;

    /// have a substates variable vector that will accomodate for different games?
    // so that only few colors could be switched at a time, for instance
    public Block() {
        stateIndex = 0;
        
        initializeStates();
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
        stateIndex %= states.size();
        setState(stateIndex);
        }

    public void turnOn() {
        on = true;
        setState(1);
        // rec.setFill(new Color(0.2,Math.random(), Math.random(), 1));
    }
    
    public void turnOff() {
        on = false;
        setState(0);
        
      //  rec.setFill(new Color(Math.random(),0.2, Math.random(), 1));
    }

    public boolean isOn() {
        return on;
    }
    
    private void initializeStates() {
        states = new Vector();
        states.add(new BlockState("Off", Color.BLACK));
        states.add(new BlockState("On", Color.ORANGE));
        states.add(new BlockState("Water", Color.BLUE));
        states.add(new BlockState("Pinki", Color.PINK));
        states.add(new BlockState("Cyan", Color.CYAN));
        states.add(new BlockState("Red", Color.RED));
    }
    
   public void setState(int stateIndex) {
       this.stateIndex = stateIndex;
       rec.setFill(states.get(stateIndex).color);
   }



}







