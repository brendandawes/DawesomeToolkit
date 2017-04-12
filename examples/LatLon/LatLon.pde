/*
Uses getCartesian to map lat lon of cities, read from a csv file, on a globe
*/

import dawesometoolkit.*;

DawesomeToolkit dawesome;
ArrayList<PVector> grid;
ArrayList<Integer> colors;
Table table;



void setup(){
  size(800,800,OPENGL);
  table = loadTable("capitals.csv", "header");
  smooth();
  dawesome = new DawesomeToolkit(this);
  colors = dawesome.iWantHue();
  noStroke();
  textSize(9);

}

void draw(){
  
  background(20);
  lights();
  translate(width/2,height/2);
  float xRot = radians(270 -  millis()*.02);
  float yRot = radians(270 -  millis()*.03);
  //rotateX( xRot ); 
  rotateY( yRot );
  int counter = 0;
   for (TableRow row : table.rows()) {
    int lat = row.getInt("CapitalLatitude");
    int lon = row.getInt("CapitalLongitude");
    String name = row.getString("CapitalName");
    PVector p = dawesome.getCartesian(300,lat,lon);
    pushMatrix();
       translate(p.x,p.y,p.z);
       PVector polar = dawesome.cartesianToPolar(p);
       rotateY(polar.y);
       rotateZ(polar.z);
       pushMatrix();
          fill(255);
          rotateY(radians(90));
          if (counter%3==0){
            text(name,0,0);
          }
       popMatrix();
       fill(colors.get(counter%colors.size()));
       box(10,3,3);
     popMatrix();
     counter++;
  }
 
     
   
  
 
  
}
