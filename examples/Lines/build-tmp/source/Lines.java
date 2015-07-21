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

public class Lines extends PApplet {

/*
Uses gridLayout and then mapPVectorsAroundSphere to map that grid around a sphere
then use lineAroundSphere to draw some connecting lines
*/



DawesomeToolkit ds;
ArrayList<PVector> grid;
ArrayList<Integer> colors;

public void setup(){
  size(600,600,OPENGL);
  smooth();
  ds = new DawesomeToolkit(this);
  grid = ds.gridLayout(75,5,10,10);
  grid = ds.mapPVectorsAroundSphere(grid,150,10);
  rectMode(CENTER);
}

public void draw(){
  
  background(50);
  lights();
  noStroke();
  translate(width/2,height/2);
  float xRot = radians(270 -  millis()*.02f);
  float yRot = radians(270 -  millis()*.03f);
  rotateX( xRot ); 
  rotateY( yRot );
 int counter = 0;
  for (PVector p : grid) {
     pushMatrix();
       translate(p.x,p.y,p.z);
       PVector polar = ds.cartesianToPolar(p);
      rotateY(polar.y);
       rotateZ(polar.z);
       pushMatrix();
        noStroke();
        fill(255,0,255);
        rotateY(radians(90));
        //text(counter,0,0);
       popMatrix();
       fill(255);
       box(3,3,3);
     popMatrix();
     counter++;
  }
pushMatrix();

for (int j=1; j < grid.size(); j++){
  // choose some vectors 
  PVector p1 = grid.get(j);
  PVector p2 = grid.get((j+2)%(grid.size()-1));
  // generate vectors to draw lines
  ArrayList<PVector> lines = ds.lineAroundSphere(p1,p2,150);
  noFill();
  int c = color(255,255,0,150);
  strokeWeight(1.5f);
  stroke(c);
  beginShape();
    for (PVector p : lines) {
      vertex(p.x, p.y, p.z);
    }
  endShape();
 }
 popMatrix();
  
}


  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--hide-stop", "Lines" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
