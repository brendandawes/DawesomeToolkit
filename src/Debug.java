package dawesometoolkit;
import processing.core.*;
import processing.event.*;
import processing.core.PGraphics;
import java.util.*;
import static java.lang.Math.*;
import java.util.Map;
import java.io.FileWriter;
import java.io.*;
import java.io.BufferedWriter;
import java.lang.Exception;


public class Debug implements PConstants {

	PApplet parent;
	float debugWidth;
	float debugHeight;
	final int VERTICAL_SPACING = 20;
	HashMap<String,String> logs;
	boolean showDebug = false;
	PFont debugFont;
	int backgroundColor;
	int foregroundColor;

	/**
	  Constructor

	  @param parent the PApplet to target - pretty much always 'this'
	  @param debugWidth a float defining the width of the debug window
	  @param debugHeight a float defining the height of the debug window
	  */

	public Debug(PApplet parent, float debugWidth, float debugHeight) {
		this.parent = parent;
		this.debugWidth = debugWidth;
		this.debugHeight = debugHeight;
		this.backgroundColor = parent.color(0,0,0);
		this.foregroundColor = parent.color(255);
		this.logs = new HashMap<String,String>();
		this.debugFont = parent.createFont("Monospaced",10);
		parent.registerMethod("keyEvent", this);
	}

	public void keyEvent(KeyEvent e) {

		char key = e.getKey();
		if (key == '!'){
			showDebug = !showDebug;
		}
	}

	public void draw() {

		if (showDebug){
			float w = parent.width;
			float h = parent.width;
			float x = 10;
			float y = 20;
			parent.pushStyle();
			parent.textFont(debugFont);
			parent.textSize(10);
			parent.fill(backgroundColor);
			parent.rect(0,0,debugWidth,debugHeight);
			for (Map.Entry entry : logs.entrySet()) {
				parent.noStroke();
				parent.fill(foregroundColor);
				parent.text(entry.getKey()+": "+entry.getValue(),x,y);
				y += VERTICAL_SPACING;
			}
			parent.popStyle();
		}
	}

	public String timestamp(){
		String DATE_FORMAT="yyyy-MM-dd'T'HH:mm:ss.SSSZ";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		String d =  sdf.format(new java.util.Date());
		return d;
	}

	public void log(String entry){
		PrintWriter logger;
		try {
			java.io.File pathToData = new java.io.File(parent.sketchPath(""));
			logger = new PrintWriter(new BufferedWriter(new FileWriter(pathToData+"/log.txt", true)));
			String time = timestamp();
			logger.println(time + " " + entry);
			logger.close();
		}
		catch (IOException cause) {
			cause.printStackTrace();
		}


	}

	public void log(int value){

		String v = ""+value;
		log(v);

	}

	public void log(boolean value){

		String v = ""+value;
		log(v);

	}


	public void log(PVector value){

		String v = ""+value;
		log(v);

	}
	public void log(double value){

		String v = ""+value;
		log(v);

	}
	public void log(float value){

		String v = ""+value;
		log(v);

	}

	public void log(Exception value){

		String v = ""+value;
		log(v);
	}

	public void update(String key, String value ) {

		logs.put(key, value);

	}

	public void update(String key, float value ) {

		String v = ""+value;
		update(key,v);

	}

	public void update(String key, int value ) {

		String v = ""+value;
		update(key,v);

	}

	public void update(String key, double value ) {

		String v = ""+value;
		update(key,v);

	}

	public void update(String key, PVector value ) {

		String v = ""+value;
		update(key,v);

	}

	public void setVisible(boolean v){
		showDebug = v;
	}

	public void setBackgroundColor(int c){
		backgroundColor = c;
	}

	public void setForegroundColor(int c){
		foregroundColor = c;
	}



}

