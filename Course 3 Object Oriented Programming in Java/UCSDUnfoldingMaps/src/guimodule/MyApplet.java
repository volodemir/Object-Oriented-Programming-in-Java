package guimodule;

import processing.core.PApplet;
import processing.core.PImage;

public class MyApplet extends PApplet{
	PImage img;
	
	public void setup() {
		size(400, 400);
		background(255);
		stroke (0);
		img = loadImage("https://get.pxhere.com/photo/beach-sea-coast-tree-sand-ocean-plant-sun-sunlight-shore-atmosphere-vacation-holiday-island-body-of-water-caribbean-tropics-cape-palma-flowering-plant-woody-plant-land-plant-arecales-palm-family-borassus-flabellifer-687624.jpg", "jpg");
		img.resize(0, HEIGHT);
		image(img, 0, 0);
	}
	public void draw() {
		int[] color = sunColorSec(second());
		fill(color[0], color[1], color[2]);
		ellipse(width/4, height/5, width/4, height/5);
	}
	
	public int[] sunColorSec (float seconds) {
		int[] rgb = new int[3];
		float diffFrom30 = Math.abs(30-seconds);
		
		float ratio = diffFrom30/30;
		rgb[0] = (int)(255*ratio);
		rgb[1] = (int)(255*ratio);
		rgb[2] = 0;
		
		return rgb;
	}
}
