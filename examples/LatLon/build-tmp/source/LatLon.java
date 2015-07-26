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

public class LatLon extends PApplet {

/*
Uses getCartesian to map lat lon of cities, read from a csv file, on a globe
*/



DawesomeToolkit ds;
ArrayList<PVector> grid;
Table table;



public void setup(){
  size(800,800,OPENGL);
  table = loadTable("capitals.csv", "header");
  smooth();
  ds = new DawesomeToolkit(this);
  noStroke();
  textSize(9);

}

public void draw(){
  
  background(50);
  lights();
  translate(width/2,height/2);
  float xRot = radians(270 -  millis()*.02f);
  float yRot = radians(270 -  millis()*.03f);
  //rotateX( xRot ); 
  rotateY( yRot );
  int counter = 0;
   for (TableRow row : table.rows()) {
    int lat = row.getInt("CapitalLatitude");
    int lon = row.getInt("CapitalLongitude");
    String name = row.getString("CapitalName");
    PVector p = ds.getCartesian(300,lat,lon);
    pushMatrix();
       translate(p.x,p.y,p.z);
       PVector polar = ds.cartesianToPolar(p);
       rotateY(polar.y);
       rotateZ(polar.z);
       pushMatrix();
          fill(255,0,255);
          rotateY(radians(90));
          if (counter%3==0){
            text(name,0,0);
          }
       popMatrix();
       fill(255);
       box(10,3,3);
     popMatrix();
     counter++;
  }
 
     
   
  
 
  
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--hide-stop", "LatLon" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
