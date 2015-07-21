/*
Creates a color spectrum palette
*/

import dawesometoolkit.*;

DawesomeToolkit ds;
ArrayList<PVector> grid;
int dotSize = 10;
float gridWidth;
float gridHeight;
ArrayList<Integer> colors;

void setup(){
  size(600,600);
  smooth();
  rectMode(CENTER);
  ds = new DawesomeToolkit(this);
  grid = ds.gridLayout(100,20,20,20);
  PVector p = ds.getMaxValueFromListOfPVectors(grid);
  gridWidth = p.x-dotSize;
  gridHeight = p.y-dotSize;
  colors = ds.colorSpectrum(100,0.6,0.9);
}

void draw(){
  
  background(50);
  fill(0);
  noStroke();
  translate(width/2-gridWidth/2,height/2-gridHeight/2);
  for (int i=0; i < grid.size(); i++) {
    PVector p = grid.get(i);
    color c = colors.get(i);
    fill(c);
    rect(p.x,p.y,dotSize,dotSize);
  }
  
}
