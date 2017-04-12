/*
Uses fibonacciSphereLayout to create an ArrayList of PVectors nicely
distributed around a sphere using Fibonacci.
Code taken from http://www.openprocessing.org/sketch/103897 by Martin Prout
*/

import dawesometoolkit.*;

DawesomeToolkit dawesome;
ArrayList<PVector> vectors;
float boxSize = 10;


void setup(){
  size(600,600,P3D);
  smooth();
  dawesome = new DawesomeToolkit(this);
  vectors = dawesome.fibonacciSphereLayout(100,150);

}



void draw(){
  
  background(20);
  lights();
  fill(dawesome.BITTERSWEET);
  noStroke();
  translate(width/2,height/2);
  float xRot = radians(270 -  millis()*.02);
  float yRot = radians(270 -  millis()*.03);
  rotateX( xRot ); 
  rotateY( yRot );

  for (PVector p : vectors) {
    pushMatrix();
      // float scaler = sin(frameCount/100.0)*1.5;
      // p = PVector.mult(p,scaler);
      translate(p.x, p.y, p.z);
      PVector polar = dawesome.cartesianToPolar(p);
      rotateY(polar.y);
      rotateZ(polar.z);
      box(boxSize,boxSize,boxSize);
    popMatrix();
  }
 
}
