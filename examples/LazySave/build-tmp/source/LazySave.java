import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import dawesometoolkit.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class LazySave extends PApplet {

/*
Just add one line of code and you can quickly save screenshots
*/



DawesomeToolkit dawesome;
ArrayList<PVector> grid;
int dotSize = 10;
float gridWidth;
float gridHeight;

public void setup(){
  
  
  dawesome = new DawesomeToolkit(this);
  // Turn on the saveFrame capture feature
  // Creates a unique time based filename and has a 500ms debounce built-in.
  dawesome.enableLazySave('s',".png");
  // Can also use dawesome.enableLazySave() for default 's' key and png format
  grid = dawesome.gridLayout(100,20,20,10);
  PVector p = dawesome.getMaxValueFromListOfPVectors(grid);
  gridWidth = p.x-dotSize;
  gridHeight = p.y-dotSize;
}

public void draw(){
  
  background(20);
  fill(dawesome.BITTERSWEET);
  noStroke();
  translate(width/2-gridWidth/2,height/2-gridHeight/2);
  for (PVector p : grid) {
    ellipse(p.x,p.y,dotSize,dotSize);
  }
  
}
  public void settings() {  size(600,600);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "LazySave" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
