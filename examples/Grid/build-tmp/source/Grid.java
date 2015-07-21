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



DawesomeToolkit ds;
ArrayList<PVector> grid;
int dotSize = 10;
float gridWidth;
float gridHeight;

public void setup(){
  size(600,600);
  smooth();
  ds = new DawesomeToolkit(this);
  grid = ds.gridLayout(100,20,20,10);
  PVector p = ds.getMaxValueFromListOfPVectors(grid);
  gridWidth = p.x-dotSize;
  gridHeight = p.y-dotSize;
}

public void draw(){
  
  background(50);
  fill(255);
  noStroke();
  translate(width/2-gridWidth/2,height/2-gridHeight/2);
  for (PVector p : grid) {
    ellipse(p.x,p.y,dotSize,dotSize);
  }
  
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--hide-stop", "Grid" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
