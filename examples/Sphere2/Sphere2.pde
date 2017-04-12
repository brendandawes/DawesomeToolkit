/*
Uses gridLayout and then mapPVectorsAroundSphere to map that grid around a sphere
Adds some lights and a sine wave to alter the box size...
*/

import dawesometoolkit.*;

DawesomeToolkit dawesome;
ArrayList<PVector> grid;



void setup(){
  size(600,600,P3D);
  smooth();
  dawesome = new DawesomeToolkit(this);
  grid = dawesome.gridLayout(200,10,10,20);
  grid = dawesome.mapPVectorsAroundSphere(grid,150,10);
  noStroke();

}

void drawLights() {

  float spotX = width/2;
  float spotY = height/2;
  float spotZ = 0;
  spotLight(247, 92, 47, spotX, spotY, spotZ, -1, 0, -1, PI/2, 2);
  

  spotX = width/2;
  spotY = 0;
  spotZ = 200;
  spotLight(93, 172, 129, spotX, spotY, spotZ, -1, 0, -1, PI/2, 2);

  spotX = width;
  spotY = height;
  spotZ = 200;
  spotLight(165,222,228, spotX, spotY, spotZ, -1, 0, -1, PI/2, 2);

  spotX = width/2;
  spotY = height;
  spotZ = 200;
  spotLight(208,16,76, spotX, spotY, spotZ, -1, 0, -1, PI/2, 2);


}

void draw(){
  
  background(20);
 
  drawLights();
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
       pushMatrix();
         fill(255,0,255);
         rotateY(radians(90));
         //text(counter,0,0);
       popMatrix();
       fill(dawesome.BITTERSWEET);
       float boxSize = sin(counter)*75;
       box(10,boxSize,boxSize);
     popMatrix();
     counter++;
  }
 
  
}
