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



DawesomeToolkit dawesome;
ArrayList<PVector> vectors;
float boxSize = 10;


public void setup(){
  
  
  dawesome = new DawesomeToolkit(this);
  vectors = dawesome.fibonacciSphereLayout(100,150);

}



public void draw(){
  
  background(20);
  lights();
  fill(dawesome.BITTERSWEET);
  noStroke();
  translate(width/2,height/2);
  float xRot = radians(270 -  millis()*.02f);
  float yRot = radians(270 -  millis()*.03f);
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
  public void settings() {  size(600,600,P3D);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--hide-stop", "Fibonacci" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
