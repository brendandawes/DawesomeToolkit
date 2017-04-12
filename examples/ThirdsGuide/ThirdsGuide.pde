/*
Draws a thirds guide
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
  fill(dawesome.BITTERSWEET);
  ellipse(width/2.0,height/2.0,300,300);
  dawesome.drawThirdsGuide(255);
  
}
