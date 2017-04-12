/*
Draws a center guide
*/

import dawesometoolkit.*;

DawesomeToolkit dawesome;


void setup(){
  size(600,600);
  smooth();
  dawesome = new DawesomeToolkit(this);
}

void draw(){
  
  background(20);
  noStroke();
  fill(dawesome.RICECAKE);
  ellipse(width/2.0,height/2.0,200,200);
  dawesome.drawCenterGuide(dawesome.BITTERSWEET);
  
}
