package dawesometoolkit;
import processing.core.*;
import processing.event.*;
import processing.core.PGraphics;
import java.util.*;
import java.util.regex.*;
import java.util.Collections;
import java.util.Random;
import static java.lang.Math.*;
import java.awt.Color;
import java.io.FileWriter;
import java.io.*;
import java.io.BufferedWriter;
import java.lang.Exception;

public class DawesomeToolkit implements PConstants {
	PApplet parent;
	int lastCapture;
	char saveKey = 's';
	String saveFormat = ".png";
	String saveAppend = "";

	public final int BITTERSWEET;
	public final int BIANCA;
	public final int ONYX;
	public final int RICECAKE;




	public DawesomeToolkit(PApplet parent) {
		this.parent = parent;
		lastCapture = 0;

		BITTERSWEET = parent.color(253, 115, 87);
		BIANCA = parent.color(252, 250, 242);
		ONYX = parent.color(18, 10, 13);
		RICECAKE = parent.color(237, 237, 224);
	}

	/**
	  Enables pressing a key to save screenshot with a unique date based filename

	  @param  saveKey a char defining the key to use
	  @param saveFormat a string defining the file extension type and format eg .png
	  */
	public void enableLazySave(char saveKey, String saveFormat){
		this.saveKey = saveKey;
		this.saveFormat = saveFormat;
		parent.registerMethod("keyEvent", this);
	}

	public void enableLazySave(char saveKey, String saveFormat, String saveAppend){
		this.saveKey = saveKey;
		this.saveFormat = saveFormat;
		this.saveAppend = saveAppend;
		parent.registerMethod("keyEvent", this);
	}

	/**
	  Enables pressing the 's' key to save screenshot in png format 
	  */
	public void enableLazySave(){

		enableLazySave('s',".png");

	}

	public void disableLazySave(){
		parent.unregisterMethod("keyEvent", this);
	}

	public void keyEvent(KeyEvent e) {

		char key = e.getKey();
		if (key == saveKey){
			int currentCapture = parent.millis();
			if (currentCapture-lastCapture > 500){
				String filename = uniqueFileName();
				if (saveAppend != ""){
					filename += "_"+saveAppend;
				}
				parent.saveFrame(filename+saveFormat);
			}
			lastCapture = parent.millis();
		}
	}

	public final float map(float value, 
			float istart, 
			float istop, 
			float ostart, 
			float ostop) {
		return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
	}

	float lerp(float a, float b, float f) {

		return (a * (1.0f - f)) + (b * f);

	}

	/**
	  Returns a unique filename made up from date and time and random number. It's not strictly unique but as good as.

	  @return   a string with the generated filename
	  */

	public String uniqueFileName(){
		String DATE_FORMAT="yyyyMMdd_HHmmss-SSS";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		String d =  sdf.format(new java.util.Date());
		java.util.Random ran = new java.util.Random();
		int r =  ran.nextInt(9999);
		return d+"_"+r;

	}

	/**
	  Returns the ip address for this machine
	  @return a string with the ip address
	  */

	public String ipAddress(){
		String ip = "";

		try {
			return java.net.InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e){
			return "error geting ip address";
		}

	}


	/**
	  Returns a current timestamp
	  @param dateFormat a String defining the date format
	  @return a string with the generated timestamp
	  */


	public String timestamp(String dateFormat){
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(dateFormat);
		String d =  sdf.format(new java.util.Date());
		return d;
	}

	/**
	  Returns a current timestamp
	  @return a string with the generated timestamp
	  */

	public String timestamp(){
		String DATE_FORMAT="yyyy-MM-dd'T'HH:mm:ss.SSSZ";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		String d =  sdf.format(new java.util.Date());
		return d;
	}

	/**
	  Detects if a word is present in a string
	  @param needle the string to find
	  @param haystack the string to search
	  @return a boolean value, true or false
	  */

	public boolean isWordInString(String needle,String haystack) {
		haystack = haystack.toLowerCase();
		needle = needle.toLowerCase();
		Pattern p = Pattern.compile("\\b"+needle+"\\b");
		Matcher m = p.matcher(haystack);
		return m.find();
	}

	/**
	  Get the number of days between two dates
	  @param d1 first Date object
	  @param d2 second Date object
	  @return   an int with the number of days between
	  */

	public int numberOfDaysBetweenDates(java.util.Date d1, java.util.Date d2) {
		long diff;
		diff = d2.getTime() - d1.getTime();
		Integer i = (int) (long) (diff / 86400.0);
		return i;
	}

	/**
	  Get the distance in KM between two lat lon coordinates
	  @param lat1 first lat
	  @param lon1 first lon
	  @param lat2 second lat
	  @param lon2 second lon
	  @return   a float with the distance in KM
	  */

	public float distanceBetweenLatLonInKilometers(float lat1, float lon1, float lat2, float lon2) {

		int R = 6371; 
		float dLat = parent.radians(lat2-lat1); 
		float dLon = parent.radians(lon2-lon1); 
		double a = 
			sin(dLat/2) * sin(dLat/2) +
			cos(parent.radians(lat1)) * cos(parent.radians(lat2)) * 
			sin(dLon/2) * sin(dLon/2)
			; 
		double c = 2 * atan2(sqrt(a), sqrt(1-a)); 
		double d = R * c;
		return (float) d;
	}

	public float distanceBetweenLatLonInMiles(float lat1, float lon1, float lat2, float lon2) {

		float km = distanceBetweenLatLonInKilometers(lat1,lon1,lat2,lon2);
		float d = (float) (km * 0.621371);
		return d;
	}
	/**
	  Draws a center guide onto the canvas

	  @param  color an int defining the color of the guide
	  */
	public void drawCenterGuide(int color){

		float w = parent.width;
		float h = parent.height;
		float midW = (float)(w/2.0);
		float midH = (float)(h/2.0);
		parent.stroke(color);
		parent.strokeWeight((float)0.5);
		parent.line(midW,0,midW,h);
		parent.line(0,midH,w,midH);
	}

	/**
	  Draws a thirds guide onto the canvas

	  @param  color an int defining the color of the guide
	  */
	public void drawThirdsGuide(int color){

		float w = parent.width;
		float h = parent.height;
		float wThird = (float)(h/3.0);
		float hThird = (float)(w/3.0);
		parent.stroke(color);
		parent.strokeWeight((float)0.5);
		parent.line(0,hThird,w,hThird);
		parent.line(0,hThird*2,w,hThird*2);
		parent.line(wThird,0,wThird,h);
		parent.line(wThird*2,0,wThird*2,h);
	}

	/**
	  Draws a baseline grid

	  @param  color an int defining the color of the guide
	  @param  spacing a float defining the vertical spacing
	  */

	public void drawBaselineGuide(int color,float spacing){

		float w = parent.width;
		float h = parent.height;
		int amount = (int)(h/spacing);
		parent.stroke(color);
		parent.strokeWeight((float)0.5);
		for (int i=1; i < amount; i++){
			parent.line(0,spacing*i,w,spacing*i);
		}

	}
	/**
	  Gets the min x y z values from a list of PVectors

	  @param  vectors a PVector ArrayList of vectors
	  @return   a PVector with the min values
	  */
	public PVector getMinValueFromListOfPVectors( ArrayList<PVector> vectors ) {

		PVector  pMin = new PVector(  99999999,  99999999,  99999999 );

		for( int i = 0; i < vectors.size(); i++ )
		{
			PVector p = vectors.get(i);
			pMin.x = min( pMin.x, p.x );
			pMin.y = min( pMin.y, p.y );
			pMin.z = min( pMin.z, p.z );
		}

		return pMin;
	}

	/**
	  Gets the max x y z values from a list of PVectors

	  @param vectors a PVector ArrayList of vectors
	  @return a PVector with the max values
	  */

	public PVector getMaxValueFromListOfPVectors( ArrayList<PVector> vectors ) {

		PVector  pMax = new PVector(  -99999999, -99999999, -99999999 );

		for( int i = 0; i < vectors.size(); i++ )
		{
			PVector p = vectors.get(i);
			pMax.x = max( pMax.x, p.x );
			pMax.y = max( pMax.y, p.y );
			pMax.z = max( pMax.z, p.z );
		}

		return pMax;
	}

	/**
	  Centers a list of PVectors in the x and y axis so that 0,0 is the center point

	  @param vectors a PVector ArrayList of vectors
	  @return an ArrayList of adjusted PVectors
	  */

	public ArrayList<PVector> centerPVectors( ArrayList<PVector> vectors ) {

		PVector pMax = getMaxValueFromListOfPVectors(vectors);

		float xOffset = pMax.x/2;
		float yOffset = pMax.y/2;

		for( int i = 0; i < vectors.size(); i++ ){
			PVector p = vectors.get(i);
			p.x -= xOffset;
			p.y -= yOffset;
		}

		return vectors;
	}

	/**
	  Multiplies (scales) an ArrayList of PVectors

	  @param vectors a PVector ArrayList of PVectors
	  @param scaler the scaler value
	  @return an ArrayList of PVectors
	  */

	public ArrayList<PVector> multiplyPVectors(ArrayList<PVector> vectors,float scaler){

		for (PVector p : vectors) {
			p.mult(scaler);
		}

		return vectors;

	}

	/**
	  Rotates an ArrayList of PVectors

	  @param vectors a PVector ArrayList of PVectors
	  @param angle the angle value
	  @return an ArrayList of PVectors
	  */

	public ArrayList<PVector> rotatePVectors(ArrayList<PVector> vectors,float angle){

		for (PVector p : vectors) {
			p.rotate(angle);
		}

		return vectors;

	}

	/**
	  Creates a grid of PVectors

	  @param numItems the number of items in the grid
	  @param xStep the spacing in the x direction
	  @param yStep the spacing in the y direction
	  @param cols the number of columns
	  @return an ArrayList of PVectors
	  */


	public ArrayList<PVector> gridLayout(int numItems, int xStep, int yStep, int cols) {

		int totalWidth = xStep*cols;
		ArrayList<PVector> grid = new ArrayList<PVector>();
		for (int i=0; i < numItems; i++) {
			PVector p = new PVector( (float)java.lang.Math.floor((i*xStep)%totalWidth),(float)java.lang.Math.floor((i*xStep)/totalWidth)*yStep);
			grid.add(p);
		}
		return grid;
	}

	/**
	  Creates a Vogel spiral pattern of PVectors

	  @param numItems the number of items
	  @param sizeOfNode the size of an individual node
	  @return an ArrayList of PVectors
	  */

	public ArrayList<PVector> vogelLayout(int numItems, int sizeOfNode){

		ArrayList<PVector> locations = new ArrayList<PVector>();
		float phi = (float)(1 + sqrt(5)) / 2;
		int i = 0;
		for (int c=0; c < numItems; c++) {
			i = c + 1;
			float r = (float)sqrt(i);
			float theta = i * ((2 * PI)/(phi*phi)); 
			float x = (float)cos(theta) * r * sizeOfNode;
			float y = (float)sin(theta) * r * sizeOfNode;
			PVector p = new PVector(x,y);
			locations.add(p);
		}

		return locations;
	}

	/**
	  Creates a pattern of PVectors distributed around a circle

	  @param numItems the number of items
	  @param radius the radius of the circle
	  @return an ArrayList of PVectors
	  */

	public ArrayList<PVector> circularLayout(int numItems, float radius){

		float inc = TWO_PI/numItems;
		float a = 0;
		ArrayList<PVector> locations = new ArrayList<PVector>();
		for (int i=0; i < numItems; i++) {
			float x = (float)sin(a)*radius;
			float y = (float)cos(a)*radius;
			PVector p = new PVector(x,y);
			locations.add(p);
			a += inc;
		}

		return locations;

	}

	/**
	  Creates a spiral pattern of PVectors

	  @param numItems the number of items
	  @param radius a float defining the radius of the circle
	  @param resolution a float defining the resolution
	  @param spacing a float defining the spacing
	  @param inc a float defining the increment
	  @return an ArrayList of PVectors
	  */

	public ArrayList<PVector> spiralLayout(int numItems, float radius, float resolution, float spacing, float inc){

		float n = 0;
		float angle;
		float x;
		float y;
		ArrayList<PVector> locations = new ArrayList<PVector>();

		for (int i=0; i < numItems; i++){
			angle = n * resolution;
			n+=inc;
			radius = radius - angle * spacing;
			x = (float)cos(angle) * radius;
			y = (float)sin(angle) * radius;
			PVector p = new PVector(x,y);
			locations.add(p);
		}

		return locations;
	}

	/**
	  Creates a Fibonacci distribution of PVectors around a sphere
	  Original code from http://www.openprocessing.org/sketch/103897

	  @param numItems the number of items
	  @param radius a float defining the radius of the circle
	  @return an ArrayList of PVectors
	  */

	public ArrayList<PVector> fibonacciSphereLayout(int numItems, float radius){

		float phi = (float)(sqrt(5)+1)/2 - 1; // golden ratio
		float ga = phi*2*PI;           // golden angle

		ArrayList<PVector> locations = new ArrayList<PVector>();
		for (int i = 0; i < numItems; ++i) {
			float lon = ga*i;
			lon /= 2*PI; 
			lon -= floor(lon); 
			lon *= 2*PI;
			if (lon > PI)  lon -= 2*PI;
			float lat = (float)asin(-1 + 2*i/(float)numItems);
			PVector p = getCartesian(radius,(float)toDegrees(lat),(float)toDegrees(lon));
			locations.add(p);
		}

		return locations;
	}

	/**
	  Converts radius, lat, lon to a PVector of world coordinates

	  @param radius a float defining the radius
	  @param lat a float in degrees defining the latitude
	  @param lon a float in degrees defining the longitude
	  @return a PVector 
	  */

	public PVector getCartesian(float radius, float lat, float lon) {

		lon = (float)toRadians(lon);
		lat = (float)toRadians(lat);
		float x = (float)(radius * cos(lat)*cos(lon));
		float y = (float)(radius * cos(lat) * sin(lon));
		float z = (float)(radius *sin(lat));
		PVector p = new PVector(x,y,z);
		return p;

	}



	/**
	  Maps a float to latitude in degrees

	  @param f the float to map
	  @param minimum the minium value of f
	  @param maximum the maximum value of f
	  @return a float representing the latitude
	  */

	public float floatToLat(float f,float minimum,float maximum) {

		return map(f,minimum,maximum,-90,90);

	}

	/**
	  Maps a float to longitude in degrees

	  @param f the float to map
	  @param minimum the minium value of f
	  @param maximum the maximum value of f
	  @return a float representing the longitude
	  */

	public float floatToLon(float f,float minimum,float maximum) {

		return map(f,minimum,maximum,-180,180);

	}

	/**
	  Converts Cartesian coordinates to polar coordinates

	  @param vector the PVector to convert
	  @return a PVector;
	  */

	public PVector cartesianToPolar(PVector vector) {
		PVector res = new PVector();
		res.x = vector.mag();
		if (res.x > 0) {
			res.y = (float)-atan2(vector.z, vector.x);
			res.z = (float)asin(vector.y / res.x);
		} 
		else {
			res.y = 0;
			res.z = 0;
		}
		return res;
	}

	/**
	  Maps a list of PVectors around a sphere

	  @param vectors a PVector ArrayList of PVectors
	  @param radius a float defining the radius
	  @param offset a float defining the offset from the poles
	  @return a PVector ArrayList of PVectors
	  */

	public ArrayList<PVector> mapPVectorsAroundSphere(ArrayList<PVector> vectors, float radius, float offset){

		PVector minP = getMinValueFromListOfPVectors(vectors);
		PVector maxP = getMaxValueFromListOfPVectors(vectors);

		ArrayList<PVector> locations = new ArrayList<PVector>();

		for (PVector p : vectors) {
			float lat = (float)floatToLat(p.y,minP.y-offset,maxP.y+offset);
			float lon = (float)floatToLon(p.x,minP.x,maxP.x);
			PVector v = getCartesian(radius,lat,lon);
			locations.add(v);
		}

		return locations;

	}

	/**
	  Creates a list of PVectors to map a line around a sphere

	  @param p1 the PVector of the first point
	  @param p2 the PVector of the second point
	  @param radius a float defining the radius
	  @return a PVector ArrayList of PVectors
	  */

	public ArrayList<PVector> lineAroundSphere(PVector p1, PVector p2, float radius){

		float resolution  = 40;
		float lon1 = (float) Math.atan2(p1.y,p1.x);
		float lon2 = (float) Math.atan2(p2.y,p2.x);
		float lat1 = (float) Math.asin(p1.z/radius);
		float lat2 = (float) Math.asin(p2.z/radius);

		ArrayList<PVector> locations = new ArrayList<PVector>();

		for (int j=0; j < resolution; j++) {
			float divFactor = (float)((1.02/resolution)*j);
			float lon = lerp((float)toDegrees(lon1),(float)toDegrees(lon2),divFactor);
			float lat = lerp((float)toDegrees(lat1),(float)toDegrees(lat2),divFactor);
			PVector p = getCartesian(radius,lat,lon);
			locations.add(p);
		}

		return locations;

	}

	/**
	  Multiply a PVector using X and Y

	  @param vector the PVector
	  @param scalerX a float defining the scale of X
	  @param scalerY a float defining the scale of Y
	  @return a PVector
	  */

	public PVector multXY(PVector vector,float scalerX,float scalerY){
		PVector multX = PVector.mult(vector,scalerX);
		PVector multY = PVector.mult(vector,scalerY);
		PVector multipliedVector = new PVector(multX.x,multY.y);
		return multipliedVector;
	}



	/**
	  Snap a PVector to a grid

	  @param loc the PVector to snap
	  @param sizeOfGrid a float defining the size of the grid to snap to
	  @return a PVector
	  */

	public PVector snapToGrid(PVector loc,float sizeOfGrid) {

		float snappedX = (float)floor(loc.x / sizeOfGrid) * sizeOfGrid;
		float snappedY = (float)floor(loc.y / sizeOfGrid) * sizeOfGrid;
		PVector p = new PVector(snappedX,snappedY);
		return p;

	}

	/**
	  Snap a float to a grid

	  @param n the float to snap
	  @param sizeOfGrid a float defining the size of the grid to snap to
	  @return a float
	  */

	public float snapToGrid(float n,float sizeOfGrid) {

		float snapped = (float)floor(n / sizeOfGrid) * sizeOfGrid;
		return snapped;

	}

	/**
	  Creates an ArrayList color spectrum

	  @param numItems an int defining the number of colors to generate
	  @param saturation a float between 0 and 1.0 defining the saturation value
	  @param brightness a float between 0 and 1.0 defining the brightness value
	  @return an Integer ArrayList of colors
	  */
	public ArrayList colorSpectrum(int numItems, float saturation, float brightness){

		ArrayList colors = new ArrayList();
		for (int i=0; i < numItems; i++){
			Color c = new Color(Color.HSBtoRGB((float)(1.0/numItems)*i,saturation,brightness));
			colors.add(c.getRGB());
		}
		return colors;
	}

	/**
	  Creates an ArrayList of 12 unique colors defined by the iwanthue algorithm

	  @return an Integer ArrayList of colors
	  */

	public ArrayList iWantHue(){

		ArrayList colors = new ArrayList();

		colors.add(parent.color(126,131,207));
		colors.add(parent.color(197,73,107));
		colors.add(parent.color(112,207,72));
		colors.add(parent.color(126,184,193));
		colors.add(parent.color(194,91,49));
		colors.add(parent.color(93,64,109));
		colors.add(parent.color(137,211,153));
		colors.add(parent.color(200,160,157));
		colors.add(parent.color(78,55,48));
		colors.add(parent.color(205,190,77));
		colors.add(parent.color(90,111,59));
		colors.add(parent.color(195,88,196));

		return colors;
	}

	/**
	  Takes a black and white image and returns an ArrayList of vectors for any non-white pixels 
	  @param file a string defining the location of the image
	  @return an ArrayList of PVectors
	  */
	public ArrayList<PVector> maskToVectors(String file){

		PImage img = parent.loadImage(file);
		ArrayList<PVector> vectors = new ArrayList<PVector>();
		for (int i=0; i < img.width; i++) {
			for (int j=0; j < img.height; j++) {
				int c = img.get(i,j);
				if (parent.red(c) != 255 && parent.green(c) != 255 && parent.blue(c) != 255) {
					vectors.add(new PVector(i,j));
				}
			}
		}

		return vectors;
	}

	/**
	  Takes a black and white image and returns an ArrayList of vectors for any non-white pixels 
	  @param img a PImage 
	  @return an ArrayList of PVectors
	  */

	public ArrayList<PVector> maskToVectors(PImage img){

		ArrayList<PVector> vectors = new ArrayList<PVector>();
		for (int i=0; i < img.width; i++) {
			for (int j=0; j < img.height; j++) {
				int c = img.get(i,j);
				if (parent.red(c) != 255 && parent.green(c) != 255 && parent.blue(c) != 255) {
					vectors.add(new PVector(i,j));
				}
			}
		}

		return vectors;
	}

	/**
	  Shuffles an ArrayList using a random seed   
	  @param list an ArrayList that you want to shuffle
	  @return a shuffled ArrayList
	  */
	public ArrayList shuffle(ArrayList list) {

		long seed = System.nanoTime();
		Collections.shuffle(list, new Random(seed));
		return list;

	}

	/**
	  Simple sorting of an ArrayList  
	  @param list an ArrayList that you want to sort
	  @return a sorted ArrayList
	  */
	public ArrayList sort(ArrayList list) {

		Collections.sort(list);
		return list;

	}

	/**
	  Reverse an ArrayList  
	  @param list an ArrayList that you want to reverse
	  @return a reversed order ArrayList
	  */
	public ArrayList reverse(ArrayList list) {

		Collections.reverse(list);
		return list;

	}

	/**
	  Export Colors to HTML Swatch Guide
	  @param colors an int array of colors
	  */
	public void exportColorsToHtmlSwatchGuide(int[] colors){
		PrintWriter output;
		try {
			java.io.File pathToData = new java.io.File(parent.sketchPath(""));
			String sketchName = parent.getClass().getSimpleName();
			output = new PrintWriter(new BufferedWriter(new FileWriter(pathToData+"/colors.html", false)));
			output.println("<style>");
			output.println(".swatch {");
			output.println("width:100px;");
			output.println("height:100px;");
			output.println("display:block;");
			output.println("}");
			output.println("td {");
			output.println("padding:20px;");
			output.println("}");
			output.println("body {font-family: Helvetica, sans-serif;}");
			output.println("</style>");
			output.println("<html>");
			output.println("<head>");
			output.println("<title>"+sketchName+" Colors</title>");
			output.println("</head>");
			output.println("<body>");
			output.println("<h1>"+sketchName+" Colors</h1>");
			output.println("<table width=\"200px\">");
			output.println("<tr><th>Swatch</th><th>Hex</th><th>R</th><th>G</th><th>B</th></tr>");
			for (int i=0; i < colors.length;i++){
				output.println("<tr><td><span class=\"swatch\" style=\"background: #"+ parent.hex(colors[i],6)+"\"></span></td><td nowrap>#"+parent.hex(colors[i],6)+" </td>");
				output.println("<td>"+Math.round(parent.red(colors[i]))+"</td><td>"+Math.round(parent.green(colors[i]))+"</td><td>"+Math.round(parent.blue(colors[i]))+"</td>");
				output.println("</tr>");

			}
			output.println("</table>");
			output.println("</body>");
			output.println("</html>");

			output.close();
		}
		catch (IOException cause) {
			cause.printStackTrace();
		}

	}


}








