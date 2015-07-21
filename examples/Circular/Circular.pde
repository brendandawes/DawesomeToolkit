/*
Uses circularLayout to create a circular layout - obvs!
*/

import dawesometoolkit.*;

DawesomeToolkit ds;
ArrayList<PVector> layout;
int dotSize = 10;


void setup(){
  size(600,600);
  smooth();
  ds = new DawesomeToolkit(this);
  layout = ds.circularLayout(25,100);

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
