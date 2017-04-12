/*
Example of using the ColorSampler class to get an ArrayList with a defined number of colors from a color strip image.
*/

import dawesometoolkit.*;
DawesomeToolkit dawesome;
ColorSampler colorSampler;
ArrayList<PVector> grid;
ArrayList<Integer> colors;


void setup(){
	
	size(600,600);
	dawesome = new DawesomeToolkit(this);
	colorSampler = new ColorSampler(this,"colorstrip.png");
  int amountOfColors = 10;
  colors = colorSampler.getColors(amountOfColors);
	grid = dawesome.gridLayout(10,20,20,10);
  grid = dawesome.centerPVectors(grid);
  smooth();

}

void draw() {

	background(20);
  noStroke();
  translate(width/2,height/2);
  for (int i=0; i < grid.size(); i++){
    PVector p = grid.get(i);
    color c = colors.get(i%colors.size());
    fill(c);
    ellipse(p.x,p.y,15,15);
  }

}

