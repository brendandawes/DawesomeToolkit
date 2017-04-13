// =====================================
// Dawesome Toolkit ====================
// =====================================

// =====================================
// Example using a black & white image 
// to create a list of PVectors
// =====================================

import java.util.Collections;
import java.util.Random;
import dawesometoolkit.*;

ArrayList<PVector> vectors;
DawesomeToolkit dawesome;
ArrayList<Integer> colors;


void setup() {
	size(600, 600);
	dawesome = new DawesomeToolkit(this);
	dawesome.enableLazySave('s', ".png");
	colors = dawesome.colorSpectrum(16, 100, 50);
	vectors = dawesome.maskToVectors("hello.png");
	vectors = dawesome.centerPVectors(vectors);
	long seed = System.nanoTime();
	Collections.shuffle(vectors, new Random(seed));
	smooth();
}

void draw() {
	background(20);
	translate(width/2, 200);
	int count = 0;
	for (PVector p: vectors){
		if (count%13==0){
			fill(colors.get(count%colors.size()));
			float dotSize = count%15;
			if (count%2==0){
				rect(p.x, p.y, dotSize, dotSize);
			} else {
				ellipse(p.x, p.y, dotSize, dotSize);
			}
		}
		count++;
	}

}

