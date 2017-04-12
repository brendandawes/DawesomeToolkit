/*
Example of using the ColorSampler class to map an index to image strip. Useful
for generating color palettes.
*/

import dawesometoolkit.*;
DawesomeToolkit dawesome;
ColorSampler colorSampler;
ArrayList<PVector> grid;



void setup(){
	
	size(600,600);
	dawesome = new DawesomeToolkit(this);
	colorSampler = new ColorSampler(this,"colorstrip.png");
	grid = dawesome.gridLayout(100,20,20,10);
  grid = dawesome.centerPVectors(grid);


}

void draw() {

	background(20);
  noStroke();
  translate(width/2,height/2);
  for (int i=0; i < grid.size(); i++){
    PVector p = grid.get(i);
    float index = map(i, 0, grid.size()-1, 0, 1.0);
    color c = colorSampler.getColorAt(index);
    fill(c);
    ellipse(p.x,p.y,15,15);
  }

}

