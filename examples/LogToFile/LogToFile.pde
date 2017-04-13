/*
Example of using the log to file feature of the Debug class. 
*/

import dawesometoolkit.*;

DawesomeToolkit dawesome;
Debug debug;
ArrayList<PVector> sphere;



void setup(){
  size(600,600,OPENGL);
  smooth();
  dawesome = new DawesomeToolkit(this);
  debug = new Debug(this,120,100);
  sphere = dawesome.fibonacciSphereLayout(100,150);
  noStroke();
}



void draw(){
  
  background(20);
  debug.update("fps",frameRate);
  debug.update("frameCount",frameCount);
  debug.draw();
  lights();

  // log entry (append) to a file called log.txt every two seconds. Will auto add a timestamp with each entry

  if (frameCount%120==0){
    debug.log(frameCount);
  }

  translate(width/2,height/2);

  float xRot = radians(270 -  millis()*.02);
  float yRot = radians(270 -  millis()*.03);
  rotateX( xRot ); 
  rotateY( yRot );
  debug.update("xRot",xRot);
  debug.update("yRot",yRot);
  int counter = 0;
  for (PVector p : sphere) {
     pushMatrix();
       translate(p.x,p.y,p.z);
       PVector polar = dawesome.cartesianToPolar(p);
       rotateY(polar.y);
       rotateZ(polar.z);
       pushMatrix();
         fill(dawesome.BITTERSWEET);
         rotateY(radians(90));
         text(counter,0,0);
       popMatrix();
       fill(dawesome.BITTERSWEET);
       box(5,5,5);
     popMatrix();
     counter++;
  }
 
  
}
