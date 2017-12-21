/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package determination;

import java.util.ArrayList;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

// In principle, there are two ways of operationg a board
// One of them, is to have a checkerboard, where every element
// is movable and independant
// Second, is to have a board of lightbulbs.
// Lightbulbs cannot be moved, but they can change states;



 public class Board {
      
     
     public static ArrayList<Color> colorScheme;
     
   
     ControlPanel controls;
     
     public final static int COLUMNS = 50;
     public final static int ROWS = 50;
     public final static int WIDTH = 10;
     public final static int HEIGHT = 10;

  
     
     
     Block[][] blocks;
     Game game;
 
     
     public Board(Game game) {
      
        this.game = game;
        
        // initialize color scheme
        setColorScheme("vapor");
      
        blocks = new Block[ROWS][COLUMNS];
        initBlocks();
     }
     
     
     void initBlocks() {
         for (int y = 0; y < ROWS; y++) {
             for (int x = 0; x < COLUMNS; x++) {
                 blocks[y][x] = new Block();
                 blocks[y][x].rec = new Rectangle(WIDTH, HEIGHT);
                 game.getGrid().add(blocks[y][x].rec, x, y);
             }  
         }
     }
     
     
     
     
     
     
     
     ////  replace color X with color Y
     
     public void setColorScheme(String name) {
        ColorSchemes cs = new ColorSchemes();
        Board.colorScheme = cs.getScheme(name);
        
        // change the total number of states for the blocks
        Block.numberOfStates = colorScheme.size();
     }
     
     
     public void clear() {
         for (int y = 0; y < COLUMNS; y++) {
             for (int x = 0; x < ROWS; x++) {
                 blocks[x][y].turnOff();
             } 
         }
     }
     
     
     public void closer2() {
         for (int i = COLUMNS-2; i > 0; i--) {
             for (int j = 0; j <= ROWS/2; j++) {
                 if (blocks[j][i].isOn()) {
                     blocks[j][i].turnOff();
                     blocks[j+1][i+1].turnOn();
                 }
             }
             for (int j = ROWS/2+1; j < ROWS - 2; j++) {
                 if (blocks[j][i].isOn()) {
                     blocks[j][i].turnOff();
                     blocks[j-1][i+1].turnOn();
                 }
             }
         }
          
       
         
     
     }
     
     
     public void closer4() {
         for (int i = COLUMNS/2; i < COLUMNS; i++) {
             for (int j = ROWS/2; j < ROWS; j++) {
                 if (blocks[j][i].isOn()) {
                     blocks[j][i].turnOff();
                     blocks[j-1][i-1].turnOn();
                 }
             }
         }
         
         for (int i = COLUMNS/2 + 1; i > 0; i--) {
             for (int j = ROWS/2 + 1; j < ROWS; j++) {
                 if (blocks[j][i].isOn()) {
                     blocks[j][i].turnOff();
                     blocks[j-1][i+1].turnOn();
                 }
             }
         }
         
          for (int i = COLUMNS/2; i > 0; i--) {
             for (int j = ROWS/2; j > 0; j--) {
                 if (blocks[j][i].isOn()) {
                     blocks[j][i].turnOff();
                     blocks[j+1][i+1].turnOn();
                 }
             }
         }
         
         for (int i = COLUMNS/2 + 1; i < COLUMNS - 1; i++) {
             for (int j = ROWS/2 + 1; j > 0; j--) {
                 if (blocks[j][i].isOn()) {
                     blocks[j][i].turnOff();
                     blocks[j+1][i-1].turnOn();
                 }
             }
         }
 
     }
     
       public void eachBlockDown() {
           
           // for every column
           
           for (int j = 0; j < COLUMNS; j++) {
               // perform on column
               for (int i = ROWS - 1; i > 0; i--) {
                   
                   if (i == ROWS - 1) {
                       blocks[i][j].turnOff();
                   }
                   else {
                        if (blocks[i][j].isOn()) {
                            blocks[i+1][j].turnOn();
                         }
                         if (!blocks[i-1][j].isOn()) {
                            blocks[i][j].turnOff();
                         }
                   } 
               }
               blocks[0][j].turnOff();
           }
       }   
       
       
       
       public void moveAllRight() {
           int onInTheRow; 
           // count for every row how many are on
           for (int i = 0; i < ROWS; i++) {
                onInTheRow = 0;
                for (int j = 0; j < COLUMNS; j++) {
                    if (blocks[i][j].isOn()) {
                        blocks[i][j].turnOff();
                        onInTheRow++;
                    }
                }   
                // turn on
                for (int k = 0; k < onInTheRow; k++) {
                    blocks[i][k].turnOn(); 
                }
           }
     }
       
       
       
       // later considering modular support
       //
       public void invertSquare(int dimention, int row, int column, boolean modular)  {
    
           if (modular) {
                for (int y = row - dimention; y <= row + dimention; y++) {
                    for (int x = column - dimention; x <= column + dimention; x++) {
                        if (y < 0){
                            if (x < 0) {
                                blocks [Board.ROWS + y][Board.COLUMNS + x].switchColor();  
                            }
                            else {
                                blocks [Board.ROWS + y][x % Board.COLUMNS].switchColor();  
                            }
                        }
                        else {
                            if (x < 0) {
                                 blocks[y % Board.ROWS ][Board.COLUMNS + x].switchColor();  
                            }
                            else {
                                 blocks[y % Board.ROWS ][x % Board.COLUMNS].switchColor();  
                            }
                        }
                    }
               } 
           }
           else {
               int upperConstraint, lowerConstraint, leftConstraint, rightConsraint;
               lowerConstraint = (row - dimention > 0) ? row - dimention : 0;
               upperConstraint = (row + dimention < Board.ROWS)? row + dimention : Board.ROWS - 1;
               leftConstraint  = (column - dimention > 0) ? column - dimention : 0;
               rightConsraint  = (column + dimention < Board.COLUMNS) ? column + dimention : Board.COLUMNS - 1;
               
               for (int y = lowerConstraint; y <= upperConstraint; y++) {
                    for (int x = leftConstraint; x <= rightConsraint; x++) {
                            blocks[y][x].switchColor();
                    }
               }
            }
       }
       

       
       // getters

    public ArrayList<Color> getColorScheme() {
        return colorScheme;
    }
    
    public Game getGame() {
        return game;
    }
    
    
    
    
    
    
    // setters


   
    
    
    
    
    
    
       
      
 
 
 
 
 }