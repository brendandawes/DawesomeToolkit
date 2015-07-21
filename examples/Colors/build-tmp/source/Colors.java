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

public class Colors extends PApplet {

/*
Creates a color spectrum palette
*/



DawesomeToolkit ds;
ArrayList<PVector> grid;
int dotSize = 10;
float gridWidth;
float gridHeight;
ArrayList<Integer> colors;

public void setup(){
  size(600,600);
  smooth();
  rectMode(CENTER);
  ds = new DawesomeToolkit(this);
  grid = ds.gridLayout(100,20,20,20);
  PVector p = ds.getMaxValueFromListOfPVectors(grid);
  gridWidth = p.x-dotSize;
  gridHeight = p.y-dotSize;
  colors = ds.colorSpectrum(100,0.6f,0.9f);
}

public void draw(){
  
  background(50);
  fill(0);
  noStroke();
  translate(width/2-gridWidth/2,height/2-gridHeight/2);
  for (int i=0; i < grid.size(); i++) {
    PVector p = grid.get(i);
    int c = colors.get(i);
    fill(c);
    rect(p.x,p.y,dotSize,dotSize);
  }
  
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--hide-stop", "Colors" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
