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

public class Circular extends PApplet {

/*
Uses circularLayout to create a circular layout - obvs!
*/



DawesomeToolkit dawesome;
ArrayList<PVector> layout;
int dotSize = 10;


public void setup(){
  
  
  dawesome = new DawesomeToolkit(this);
  layout = dawesome.circularLayout(100,200);

}

public void draw(){
  
  background(20);
  fill(dawesome.BITTERSWEET);
  noStroke();
  translate(width/2,height/2);
  for (PVector p : layout) {
    ellipse(p.x,p.y,dotSize,dotSize);
  }
  
}
  public void settings() {  size(600,600);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--hide-stop", "Circular" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
