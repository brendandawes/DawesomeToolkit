/*
Uses gridLayout and then mapPVectorsAroundSphere to map that grid around a sphere
*/

import dawesometoolkit.*;

DawesomeToolkit dawesome;
ArrayList<PVector> grid;



void setup(){
  size(600,600,P3D);
  smooth();
  dawesome = new DawesomeToolkit(this);
  grid = dawesome.gridLayout(200,10,10,10);
  grid = dawesome.mapPVectorsAroundSphere(grid,150,10);
  noStroke();

}



void draw(){
  
  background(20);
  // lights();

  translate(width/2,height/2);

  float xRot = radians(270 -  millis()*.02);
  float yRot = radians(270 -  millis()*.03);
  rotateX( xRot ); 
  rotateY( yRot );
 int counter = 0;
  for (PVector p : grid) {
     pushMatrix();
       translate(p.x,p.y,p.z);
       PVector polar = dawesome.cartesianToPolar(p);
       rotateY(polar.y);
       rotateZ(polar.z);
        fill(dawesome.BITTERSWEET);
       box(5,5,5);
     popMatrix();
     counter++;
  }
 
  
}
