/*
Just add one line of code and you can quickly save screenshots
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
  // Turn on the saveFrame capture feature
  // Creates a unique time based filename and has a 500ms debounce built-in.
  ds.enableLazySave('s',".png");
  // Can also use ds.enableLazySave() for default 's' key and png format
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
