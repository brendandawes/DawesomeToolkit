/*
Uses spiralLayout to create a spiral pattern
*/

import dawesometoolkit.*;

DawesomeToolkit dawesome;
ArrayList<PVector> layout;
int dotSize = 10;


void setup(){
  size(600,600);
  smooth();
  dawesome = new DawesomeToolkit(this);
  layout = dawesome.spiralLayout(300,200,0.3,0.01,1.15);

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
