// The Dawesome Toolkit —A Library for Processing by Brendan Dawes
// http://cloud.brendandawes.com/dawesometoolkit/
//A simple example using the Trigger class to trigger a method every second
import dawesometoolkit.*;


Trigger trigger;

void setup(){
  size(600,600);
  trigger = new Trigger(this);
  trigger.setTrigger(1000,"drawEllipse",true);
  smooth();
  background(20);
  noStroke();
}

void drawEllipse(){
  float x = random(width);
  float y = random(height);
  fill(random(100,255));
  ellipse(x,y,20,20);
}

void draw(){
  trigger.update();
}
