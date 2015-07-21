/*
Uses gridLayout and then mapPVectorsAroundSphere to map that grid around a sphere
then use lineAroundSphere to draw some connecting lines
*/

import dawesometoolkit.*;

DawesomeToolkit ds;
ArrayList<PVector> grid;
ArrayList<Integer> colors;

void setup(){
  size(600,600,OPENGL);
  smooth();
  ds = new DawesomeToolkit(this);
  grid = ds.gridLayout(75,5,10,10);
  grid = ds.mapPVectorsAroundSphere(grid,150,10);
  rectMode(CENTER);
}

void draw(){
  
  background(50);
  lights();
  noStroke();
  translate(width/2,height/2);
  float xRot = radians(270 -  millis()*.02);
  float yRot = radians(270 -  millis()*.03);
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
  color c = color(255,255,0,150);
  strokeWeight(1.5);
  stroke(c);
  beginShape();
    for (PVector p : lines) {
      vertex(p.x, p.y, p.z);
    }
  endShape();
 }
 popMatrix();
  
}


