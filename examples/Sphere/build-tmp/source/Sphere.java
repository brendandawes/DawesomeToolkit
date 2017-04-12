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

public class Sphere extends PApplet {

/*
Uses gridLayout and then mapPVectorsAroundSphere to map that grid around a sphere
*/



DawesomeToolkit dawesome;
ArrayList<PVector> grid;



public void setup(){
  
  
  dawesome = new DawesomeToolkit(this);
  grid = dawesome.gridLayout(200,10,10,10);
  grid = dawesome.mapPVectorsAroundSphere(grid,150,10);
  noStroke();

}



public void draw(){
  
  background(20);
  // lights();

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
        fill(dawesome.BITTERSWEET);
       box(5,5,5);
     popMatrix();
     counter++;
  }
 
  
}
  public void settings() {  size(600,600,P3D);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--hide-stop", "Sphere" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
