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

public class Log extends PApplet {

/*
Example of using the handy debug panel. Press ! to toggle showing and hiding the panel
*/



DawesomeToolkit ds;
Debug debug;
ArrayList<PVector> grid;



public void setup(){
  size(600,600,OPENGL);
  smooth();
  ds = new DawesomeToolkit(this);
  debug = new Debug(this,120,100);
  grid = ds.gridLayout(200,10,10,20);
  grid = ds.mapPVectorsAroundSphere(grid,150,10);
  noStroke();

}



public void draw(){
  
  background(50);
  debug.update("fps",frameRate);
  debug.update("frameCount",frameCount);
  debug.draw();
  lights();

  translate(width/2,height/2);

  float xRot = radians(270 -  millis()*.02f);
  float yRot = radians(270 -  millis()*.03f);
  rotateX( xRot ); 
  rotateY( yRot );
  debug.update("xRot",xRot);
  debug.update("yRot",yRot);
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
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--hide-stop", "Log" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
