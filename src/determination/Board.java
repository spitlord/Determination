/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package determination;

/**
 *
 * @author XDXD
 */
public abstract class Board {
     
    
     public final static int WIDTH = 40;
     public final static int HEIGHT = 40;
     Game game;
     Block[][] blocks;
 
    
    public abstract void initBoard();
    
    
}
