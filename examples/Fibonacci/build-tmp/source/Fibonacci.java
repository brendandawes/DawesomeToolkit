import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import dawesometoolkit.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Fibonacci extends PApplet {

/*
Uses fibonacciSphereLayout to create an ArrayList of PVectors nicely
distributed around a sphere using Fibonacci.
Code taken from http://www.openprocessing.org/sketch/103897 by Martin Prout
*/



DawesomeToolkit ds;
ArrayList<PVector> vectors;
float boxSize = 10;


public void setup(){
  size(600,600,P3D);
  smooth();
  ds = new DawesomeToolkit(this);
  vectors = ds.fibonacciSphereLayout(100,150);

}

public void drawLights() {

  float spotX = width;
  float spotY = height/2;
  float spotZ = 0;
  spotLight(234, 60, 138, spotX, spotY, spotZ, -1, 0, 1, PI/2, 2);

  spotX = width;
  spotY = 0;
  spotZ = 0;
  spotLight(125,185,222, spotX, spotY, spotZ, -1, 0, 0, PI/2, 2);
  
}

public void draw(){
  
  background(50);
  drawLights();
  fill(255);
  noStroke();
  translate(width/2,height/2);
  float xRot = radians(270 -  millis()*.02f);
  float yRot = radians(270 -  millis()*.03f);
  rotateX( xRot ); 
  rotateY( yRot );

  for (PVector p : vectors) {
    pushMatrix();
      float scaler = sin(frameCount/100.0f)*1.5f;
      p = PVector.mult(p,scaler);
      translate(p.x, p.y, p.z);
      PVector polar = ds.cartesianToPolar(p);
      rotateY(polar.y);
      rotateZ(polar.z);
      box(boxSize,boxSize,boxSize);
    popMatrix();
  }
 
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--hide-stop", "Fibonacci" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
