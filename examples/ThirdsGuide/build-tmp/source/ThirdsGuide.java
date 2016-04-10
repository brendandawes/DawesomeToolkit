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

public class ThirdsGuide extends PApplet {

/*
Draws a thirds guide
*/



DawesomeToolkit ds;


public void setup(){
  size(600,600);
  smooth();
  ds = new DawesomeToolkit(this);
}

public void draw(){
  
  background(50);
  noStroke();
  fill(255);
  ellipse(width/2.0f,height/2.0f,300,300);
  ds.drawThirdsGuide(color(255,0,255));
  
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--hide-stop", "ThirdsGuide" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
