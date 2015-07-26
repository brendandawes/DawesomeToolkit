package dawesometoolkit;
import processing.core.*;
import processing.event.*;
import processing.core.PGraphics;
import java.util.*;
import static java.lang.Math.*;
import java.util.Map;


public class Debug implements PConstants {

	PApplet parent;
  float debugWidth;
  float debugHeight;
  final int VERTICAL_SPACING = 20;
  HashMap<String,String> logs;
  boolean showDebug = false;
  PFont debugFont;

/**
Constructor

@param parent the PApplet to target - pretty much always 'this'
@param debugWidth a float defining the width of the debug window
@param debugHeight a float defining the height of the debug window
*/

 	public Debug(PApplet parent, float debugWidth, float debugHeight) {
    	this.parent = parent;
      this.debugWidth = debugWidth;
      this.debugHeight = debugHeight;
    	this.logs = new HashMap<String,String>();
      this.debugFont = parent.createFont("Monospaced",10);
      parent.registerMethod("keyEvent", this);
  	}

  public void keyEvent(KeyEvent e) {

    char key = e.getKey();
    if (key == '!'){
      showDebug = !showDebug;
    }
  }

  	public void draw() {

      if (showDebug){
        float w = parent.width;
        float h = parent.width;
        float x = 10;
        float y = 20;
        parent.textFont(debugFont);
        parent.textSize(10);
        parent.fill(0,100);
        parent.rect(0,0,debugWidth,debugHeight);
        for (Map.Entry entry : logs.entrySet()) {
          parent.fill(255);
          parent.text(entry.getKey()+": "+entry.getValue(),x,y);
          y += VERTICAL_SPACING;
        }
      }
  	}

  	public void update(String key, String value ) {

  		logs.put(key, value);

  	}

    public void update(String key, float value ) {

      String v = ""+value;
      update(key,v);

    }

     public void update(String key, int value ) {

      String v = ""+value;
      update(key,v);
      
    }

    public void update(String key, double value ) {

      String v = ""+value;
      update(key,v);
      
    }

    public void update(String key, PVector value ) {

      String v = ""+value;
      update(key,v);
      
    }



}

