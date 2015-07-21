/*
Uses vogelLayout to create a vogel spiral pattern
*/

import dawesometoolkit.*;

DawesomeToolkit ds;
ArrayList<PVector> grid;
int dotSize = 10;


void setup(){
  size(600,600);
  smooth();
  ds = new DawesomeToolkit(this);
  grid = ds.vogelLayout(200,10);

}

void draw(){
  
  background(50);
  fill(255);
  noStroke();
  translate(width/2,height/2);
  for (PVector p : grid) {
    ellipse(p.x,p.y,dotSize,dotSize);
  }
  
}
