/*
Creates a color palette of 12 colors using iWantHue dataviz colors
*/

import dawesometoolkit.*;

DawesomeToolkit dawesome;
ArrayList<PVector> grid;
int dotSize = 10;
float gridWidth;
float gridHeight;
ArrayList<Integer> colors;

void setup(){
  size(600,600);
  smooth();
  rectMode(CENTER);
  dawesome = new DawesomeToolkit(this);
  grid = dawesome.gridLayout(12,20,20,20);
  PVector p = dawesome.getMaxValueFromListOfPVectors(grid);
  gridWidth = p.x-dotSize;
  gridHeight = p.y-dotSize;
  colors = dawesome.iWantHue();
}

void draw(){
  
  background(20);
  fill(0);
  noStroke();
  translate(width/2-gridWidth/2,height/2-gridHeight/2);
  for (int i=0; i < grid.size(); i++) {
    PVector p = grid.get(i);
    color c = colors.get(i);
    fill(c);
    rect(p.x,p.y,dotSize,dotSize*5);
  }
  
}
