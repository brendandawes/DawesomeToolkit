/*
Draws a thirds guide
*/

import dawesometoolkit.*;

DawesomeToolkit ds;


void setup(){
  size(600,600);
  smooth();
  ds = new DawesomeToolkit(this);
}

void draw(){
  
  background(50);
  noStroke();
  fill(255);
  ellipse(width/2.0,height/2.0,300,300);
  ds.drawThirdsGuide(color(255,0,255));
  
}
