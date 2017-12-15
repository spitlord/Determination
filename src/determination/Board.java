/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package determination;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

 public class Board {
     
     public final static int COLUMNS = 150;
     public final static int ROWS = 150;
     public final static int WIDTH = 5;
     public final static int HEIGHT = 5;
     public double mouseX, mouseY;
     
     
     Block[][] blocks;
     Game game;
 
     
     public Board(Game game) {
        this.game = game;
        blocks = new Block[ROWS][COLUMNS];
        initB(game);
     }
     
     
     void initB(Game game) {
         for (int i = 0; i < ROWS; i++) {
             for (int j = 0; j < COLUMNS; j++) {
                 blocks[i][j] = new Block();
                 blocks[i][j].rec = new Rectangle(WIDTH, HEIGHT);
                 game.getGrid().add(blocks[i][j].rec, j, i);
             }  
         }
         
         
         // press space;
         
         game.getScene().setOnKeyPressed(e -> {
             if (e.getCode() == KeyCode.SPACE) {
                 
                int column = (int)(mouseX / WIDTH);
                int row = (int)(mouseY / HEIGHT);
                invertSquare(15, row, column);
                e.consume();
             }
             else if (e.getCode() == KeyCode.A) {
                 clear();
             }
             
         });
         
          game.getScene().setOnMouseMoved(e -> {
               mouseX = e.getX();
               mouseY = e.getY();
               
          } );
         
          game.getScene().setOnMouseClicked(e -> {
              if (e.getTarget() instanceof Rectangle) {
                double mX = e.getX();
                double mY = e.getY();
                int column = (int)(mX / WIDTH);
                int row = (int)(mY / HEIGHT);
                invertSquare(15, row, column);
                e.consume();
              }
               
          }
          );
                  
//           game.getScene().setOnKeyPressed(e -> {
//              if (e.getCode() == KeyCode.A) moveAllRight();
//              else if (e.getCode() == KeyCode.W) eachBlockDown();
//              else if (e.getCode() == KeyCode.S) clear();
//              else if (e.getCode() == KeyCode.SPACE) {
//                closer2();
//              }
//              
//          }
//          );
           
           
     }
     
     public void clear() {
         for (int i = 0; i < COLUMNS; i++) {
             for (int j = 0; j < ROWS; j++) {
                 blocks[j][i].turnOff();
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
       
       
//       public void invertSquare(int dimention, int row, int column) {
//           
//           int leftCostraint = (column > dimention)? 0 : dimention - column;
//           int rightCostraint = (column + dimention < COLUMNS) ?
//                   0 : column + dimention - (COLUMNS - 1);
//           int topCostraint = (row > dimention) ?  0 : dimention - row;
//           int bottomCostraint = (row + dimention < ROWS) ?
//                   0 : row - (ROWS - 1);
//           
//           
//           for (int i = column - dimention + leftCostraint; i < column + dimention - rightCostraint; i++) {
//               for (int j = row - dimention + topCostraint; j < row + dimention - bottomCostraint; j++) {
//                   blocks[i][j].switchColor();
//               }    
//           }
//           
//       }
       
       public void invertSquare(int dimention, int row, int column)  {
           for (int i = column - dimention; i <= column + dimention; i++) {
               for (int j = row - dimention; j <= row + dimention; j++) {
                   try {
                       blocks[j][i].switchColor();
                   } catch (ArrayIndexOutOfBoundsException ex) {}
               }
               
           }
       }
       
       
      
 
 
 
 
 }