/*
Demonstrates getting the distance between two lat lon coordinates
*/
import dawesometoolkit.*;
DawesomeToolkit dawesome;



void setup(){
	
	size(600,600);
	dawesome = new DawesomeToolkit(this);
	float distanceMiles = dawesome.getDistanceBetweenLatLonInMiles(51.507351,-0.127758,40.712784,-74.005941);
	float distanceKM = dawesome.getDistanceBetweenLatLonInKilometers(51.507351,-0.127758,40.712784,-74.005941);

	println("distance from London to New York: "+distanceMiles+" miles");
	println("distance from London to New York: "+distanceKM+" kilometres");

}

void draw() {

}