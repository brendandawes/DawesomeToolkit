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

public class Grid2 extends PApplet {

/*
Uses gridLayout to create a grid of 10 x 10 dots, add sine waves, lights, action...
*/



DawesomeToolkit dawesome;
ArrayList<PVector> grid;
float dotSize = 10;
float gridWidth;
float gridHeight;
int counter;

public void setup(){
  
  
  dawesome = new DawesomeToolkit(this);
  grid = dawesome.gridLayout(100,20,20,10);
  PVector p = dawesome.getMaxValueFromListOfPVectors(grid);
  gridWidth = p.x-dotSize;
  gridHeight = p.y-dotSize;
  counter = 0;
}

public void draw(){
  
  background(20);
  drawLights();
  fill(dawesome.BITTERSWEET);
  noStroke();

  translate(width/2-gridWidth/2,height/2-gridHeight/2,0);
  float xRot = radians(270 -  millis()*.02f);
  float yRot = radians(270 -  millis()*.03f);
  rotateX( xRot ); 
  rotateY( yRot );


  for (PVector p : grid) {
    pushMatrix();
    float z = sin(counter*556)*mouseY/2.0f;
    translate(p.x, p.y, z);
    box(dotSize,dotSize,dotSize);
    popMatrix();
    counter++;
  }


}

public void drawLights() {

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
  public void settings() {  size(600,600,P3D);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Grid2" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
