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

public class Spiral extends PApplet {

/*
Uses spiralLayout to create a spiral pattern
*/



DawesomeToolkit ds;
ArrayList<PVector> layout;
int dotSize = 10;


public void setup(){
  size(600,600);
  smooth();
  ds = new DawesomeToolkit(this);
  layout = ds.spiralLayout(300,200,0.3f,0.01f,1.15f);

}

public void draw(){
  
  background(50);
  fill(255);
  noStroke();
  translate(width/2,height/2);
  for (PVector p : layout) {
    ellipse(p.x,p.y,dotSize,dotSize);
  }
  
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--hide-stop", "Spiral" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
