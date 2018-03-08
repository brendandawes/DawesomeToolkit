// The Dawesome Toolkit —A Library for Processing by Brendan Dawes
// http://cloud.brendandawes.com/dawesometoolkit/
import dawesometoolkit.*;
import processing.pdf.*;


final int BACKGROUND_COLOR = #000000;

// define come colors in an array
int[] colors = {BACKGROUND_COLOR, #9C9999,#FC7357,#EDEDE0};

DawesomeToolkit dawesome;

void setup(){
  size(640,640);
  dawesome  = new DawesomeToolkit(this);
  // create a html swatch page with our color array
  dawesome.exportColorsToHtmlSwatchGuide(colors);
  // you'll find colors.html in the same directory as your sketch
}

void draw(){
  background(BACKGROUND_COLOR);

}




