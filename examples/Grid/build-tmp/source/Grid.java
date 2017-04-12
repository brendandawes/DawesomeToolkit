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

public class Grid extends PApplet {

/*
Uses gridLayout to create a grid of 10 x 10 dots
*/



DawesomeToolkit dawesome;
ArrayList<PVector> grid;
int dotSize = 10;

public void setup(){
  
  
  dawesome = new DawesomeToolkit(this);
  // create a grid layout
  grid = dawesome.gridLayout(100,20,20,10);
  // center the grid so 0,0 is the center
  grid = dawesome.centerPVectors(grid);
}

public void draw(){
  
  background(20);
  fill(dawesome.BITTERSWEET);
  noStroke();
  translate(width/2,height/2);
  for (PVector p : grid) {
    ellipse(p.x,p.y,dotSize,dotSize);
  }
  
}
  public void settings() {  size(600,600);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--hide-stop", "Grid" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
