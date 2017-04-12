/*
Uses gridLayout to create a grid of 10 x 10 dots, add sine waves, lights, action...
*/

import dawesometoolkit.*;

DawesomeToolkit dawesome;
ArrayList<PVector> grid;
float dotSize = 10;
float gridWidth;
float gridHeight;
int counter;

void setup(){
  size(600,600,P3D);
  smooth();
  dawesome = new DawesomeToolkit(this);
  grid = dawesome.gridLayout(100,20,20,10);
  PVector p = dawesome.getMaxValueFromListOfPVectors(grid);
  gridWidth = p.x-dotSize;
  gridHeight = p.y-dotSize;
  counter = 0;
}

void draw(){
  
  background(20);
  drawLights();
  fill(dawesome.BITTERSWEET);
  noStroke();

  translate(width/2-gridWidth/2,height/2-gridHeight/2,0);
  float xRot = radians(270 -  millis()*.02);
  float yRot = radians(270 -  millis()*.03);
  rotateX( xRot ); 
  rotateY( yRot );


  for (PVector p : grid) {
    pushMatrix();
    float z = sin(counter*556)*mouseY/2.0;
    translate(p.x, p.y, z);
    box(dotSize,dotSize,dotSize);
    popMatrix();
    counter++;
  }


}

void drawLights() {

  float spotX = width;
  float spotY = 0;
  float spotZ = 100;

  spotLight(93, 172, 129, spotX, spotY, spotZ, -1, 0, -1, PI/2, 2);

  spotX = width/2;
  spotY = height/2;
  spotZ = 100;
  spotLight(165,222,228, spotX, spotY, spotZ, -1, 0, -1, PI/2, 2);

  spotX = width;
  spotY = height/2;
  spotZ = 100;
  spotLight(208,16,76, spotX, spotY, spotZ, -1, 0, -1, PI/2, 2);


}
