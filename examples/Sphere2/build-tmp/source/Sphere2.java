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

public class Sphere2 extends PApplet {

/*
Uses gridLayout and then mapPVectorsAroundSphere to map that grid around a sphere
Adds some lights and a sine wave to alter the box size...
*/



DawesomeToolkit dawesome;
ArrayList<PVector> grid;



public void setup(){
  
  
  dawesome = new DawesomeToolkit(this);
  grid = dawesome.gridLayout(200,10,10,20);
  grid = dawesome.mapPVectorsAroundSphere(grid,150,10);
  noStroke();

}

public void drawLights() {

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

public void draw(){
  
  background(20);
 
  drawLights();
  translate(width/2,height/2);

  float xRot = radians(270 -  millis()*.02f);
  float yRot = radians(270 -  millis()*.03f);
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
  public void settings() {  size(600,600,P3D);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--hide-stop", "Sphere2" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
