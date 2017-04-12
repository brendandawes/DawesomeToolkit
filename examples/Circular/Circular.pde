/*
Uses circularLayout to create a circular layout - obvs!
*/

import dawesometoolkit.*;

DawesomeToolkit dawesome;
ArrayList<PVector> layout;
int dotSize = 10;


void setup(){
  size(600,600);
  smooth();
  dawesome = new DawesomeToolkit(this);
  layout = dawesome.circularLayout(100,200);

}

void draw(){
  
  background(20);
  fill(dawesome.BITTERSWEET);
  noStroke();
  translate(width/2,height/2);
  for (PVector p : layout) {
    ellipse(p.x,p.y,dotSize,dotSize);
  }
  
}
