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

public class BaselineGuide extends PApplet {

/*
Draws baseline guide
*/



DawesomeToolkit dawesome;


public void setup(){
  
  
  dawesome = new DawesomeToolkit(this);
}

public void draw(){
  
  background(20);
  noStroke();
  fill(255);
  ellipse(width/2.0f,height/2.0f,200,200);
  dawesome.drawBaselineGuide(dawesome.BITTERSWEET,20);
  
}
  public void settings() {  size(600,600);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--hide-stop", "BaselineGuide" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
