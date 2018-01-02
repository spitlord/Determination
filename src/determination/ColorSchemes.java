/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package determination;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.paint.Color;

/**
 *
 * @author XDXD
 */
public class ColorSchemes {
    
    // class for vectors of colors;
    // saving them into json file
    // being able to get them
    // works as a hash table?

    public static HashMap<String, ArrayList<Color>> colorSchemes;

    public ColorSchemes() {
        colorSchemes = new HashMap();
        
        ArrayList defaultScheme = new ArrayList();
        defaultScheme.add(Color.BLACK);
      //  defaultScheme.add(Color.rgb(0, 0, 0));
       // defaultScheme.add(Color.rgb(255, 165, 0));
        defaultScheme.add(Color.ORANGE);
        // defaultScheme.add(Color.rgb(0, 0, 255));
       // defaultScheme.add(Color.BLUE);
       // defaultScheme.add(Color.PINK);
        defaultScheme.add(Color.CYAN);
       // defaultScheme.add(Color.RED);
        colorSchemes.put("original", defaultScheme);
        ArrayList vapor = new ArrayList();
        vapor.add(Color.PINK);
        vapor.add(Color.CYAN);
        vapor.add(Color.VIOLET);
        vapor.add(Color.WHITE);
        vapor.add(Color.BLUE);
        vapor.add(Color.DARKORCHID);
        vapor.add(Color.SKYBLUE);
        vapor.add(Color.SALMON);
        colorSchemes.put("vapor", vapor);
        
         ArrayList mild = new ArrayList();
        mild.add(Color.BLACK);
        mild.add(Color.GREY);
      
        colorSchemes.put("mild", mild);
    }
    
    public ArrayList getScheme (String name) {
        return colorSchemes.get(name);
    }
    
    
    // creates a pallate with a given number of 
    // 
    public static ArrayList createGradient(int number) {
        ArrayList colors = new ArrayList();
        if (number > 0 ) {
            for (int i = 0; i < number; i++) {
                colors.add(Color.hsb(i*360.0/number, 0.5, 1));  
            }
            return colors;
        }
        else return null;
      
    }
    
    
    
    
    
    
    
    private void extractFromJson(){
        
    }
    
    
}
