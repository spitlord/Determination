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

    HashMap<String, ArrayList> colorSchemes;

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
       // defaultScheme.add(Color.CYAN);
       // defaultScheme.add(Color.RED);
        colorSchemes.put("original", defaultScheme);
    }
    
    public ArrayList getScheme (String name) {
        return colorSchemes.get(name);
    }
    
    
    
    
    
    
    
    
    
    
    
    private void extractFromJson(){
        
    }
    
    
}
