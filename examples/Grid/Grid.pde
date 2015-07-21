/*
Uses gridLayout to create a grid of 10 x 10 dots
*/

import dawesometoolkit.*;

DawesomeToolkit ds;
ArrayList<PVector> grid;
int dotSize = 10;
float gridWidth;
float gridHeight;

void setup(){
  size(600,600);
  smooth();
  ds = new DawesomeToolkit(this);
  grid = ds.gridLayout(100,20,20,10);
  PVector p = ds.getMaxValueFromListOfPVectors(grid);
  gridWidth = p.x-dotSize;
  gridHeight = p.y-dotSize;
}

void draw(){
  
  background(50);
  fill(255);
  noStroke();
  translate(width/2-gridWidth/2,height/2-gridHeight/2);
  for (PVector p : grid) {
    ellipse(p.x,p.y,dotSize,dotSize);
  }
  
}
