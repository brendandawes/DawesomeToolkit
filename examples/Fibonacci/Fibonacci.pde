/*
Uses fibonacciSphereLayout to create an ArrayList of PVectors nicely
distributed around a sphere using Fibonacci.
Code taken from http://www.openprocessing.org/sketch/103897 by Martin Prout
*/

import dawesometoolkit.*;

DawesomeToolkit ds;
ArrayList<PVector> vectors;
float boxSize = 10;


void setup(){
  size(600,600,OPENGL);
  smooth();
  ds = new DawesomeToolkit(this);
  vectors = ds.fibonacciSphereLayout(100,150);

}

void drawLights() {

  float spotX = width;
  float spotY = height/2;
  float spotZ = 0;
  spotLight(234, 60, 138, spotX, spotY, spotZ, -1, 0, 1, PI/2, 2);

  spotX = width;
  spotY = 0;
  spotZ = 0;
  spotLight(125,185,222, spotX, spotY, spotZ, -1, 0, 0, PI/2, 2);
  
}

void draw(){
  
  background(50);
  drawLights();
  fill(255);
  noStroke();
  translate(width/2,height/2);
  float xRot = radians(270 -  millis()*.02);
  float yRot = radians(270 -  millis()*.03);
  rotateX( xRot ); 
  rotateY( yRot );

  for (PVector p : vectors) {
    pushMatrix();
      float scaler = sin(frameCount/100.0)*1.5;
      p = PVector.mult(p,scaler);
      translate(p.x, p.y, p.z);
      PVector polar = ds.cartesianToPolar(p);
      rotateY(polar.y);
      rotateZ(polar.z);
      box(boxSize,boxSize,boxSize);
    popMatrix();
  }
 
}
