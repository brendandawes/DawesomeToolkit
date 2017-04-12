/*
Uses vogelLayout to create a vogel spiral pattern
*/

import dawesometoolkit.*;

DawesomeToolkit dawesome;
ArrayList<PVector> grid;
int dotSize = 10;


void setup(){
  size(600,600);
  smooth();
  dawesome = new DawesomeToolkit(this);
  grid = dawesome.vogelLayout(200,10);

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
