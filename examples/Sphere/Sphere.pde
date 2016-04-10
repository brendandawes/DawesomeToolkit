/*
Uses gridLayout and then mapPVectorsAroundSphere to map that grid around a sphere
*/

import dawesometoolkit.*;

DawesomeToolkit ds;
ArrayList<PVector> grid;



void setup(){
  size(600,600,P3D);
  smooth();
  ds = new DawesomeToolkit(this);
  grid = ds.gridLayout(200,10,10,20);
  grid = ds.mapPVectorsAroundSphere(grid,150,10);
  noStroke();

}



void draw(){
  
  background(50);
  lights();

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
         fill(255,0,255);
         rotateY(radians(90));
         //text(counter,0,0);
       popMatrix();
       fill(255);
       box(5,5,5);
     popMatrix();
     counter++;
  }
 
  
}
