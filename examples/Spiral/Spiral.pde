/*
Uses spiralLayout to create a spiral pattern
*/

import dawesometoolkit.*;

DawesomeToolkit ds;
ArrayList<PVector> layout;
int dotSize = 10;


void setup(){
  size(600,600);
  smooth();
  ds = new DawesomeToolkit(this);
  layout = ds.spiralLayout(300,200,0.3,0.01,1.15);

}

void draw(){
  
  background(50);
  fill(255);
  noStroke();
  translate(width/2,height/2);
  for (PVector p : layout) {
    ellipse(p.x,p.y,dotSize,dotSize);
  }
  
}
