package dawesometoolkit;
import processing.core.*;
import processing.event.*;
import processing.core.PGraphics;
import java.util.*;
import static java.lang.Math.*;
import java.awt.Color;

public class DawesomeToolkit implements PConstants {
  PApplet parent;
  int lastCapture;
  char saveKey = 's';
  String saveFormat = ".png";
  

  public DawesomeToolkit(PApplet parent) {
    this.parent = parent;
    lastCapture = 0;
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
Converts radius, lat, lon to a PVector of world coordinates

@param radius a float defining the radius
@param lat a float in degrees defing the latitude
@param lon a float in degrees defing the longitude
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
}