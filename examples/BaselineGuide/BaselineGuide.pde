/*
Draws baseline guide
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
  fill(255);
  ellipse(width/2.0,height/2.0,200,200);
  dawesome.drawBaselineGuide(dawesome.BITTERSWEET,20);
  
}
