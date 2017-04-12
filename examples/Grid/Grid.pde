/*
Uses gridLayout to create a grid of 10 x 10 dots
*/

import dawesometoolkit.*;

DawesomeToolkit dawesome;
ArrayList<PVector> grid;
int dotSize = 10;

void setup(){
  size(600,600);
  smooth();
  dawesome = new DawesomeToolkit(this);
  // create a grid layout
  grid = dawesome.gridLayout(100,20,20,10);
  // center the grid so 0,0 is the center
  grid = dawesome.centerPVectors(grid);
}

void draw(){
  
  background(20);
  fill(dawesome.BITTERSWEET);
  noStroke();
  translate(width/2,height/2);
  for (PVector p : grid) {
    ellipse(p.x,p.y,dotSize,dotSize);
  }
  
}
