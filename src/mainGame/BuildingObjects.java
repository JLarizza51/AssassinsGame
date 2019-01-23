package mainGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

@SuppressWarnings("serial")
class BuildingObjects extends Rectangle {
	
//Variables	
	int[] xPos,			// <---- The Array of points to create the polygon
		  yPos;			
	int numOfPoints;	// <---- The number of points the polygon will have
	
	Polygon polygon;	// <---- Initializes the polygon, color, and stroke
	Color color;
	BasicStroke stroke;
	
	boolean onScreen;	// <---- If the Building is On Screen or not 
	
	
	BuildingObjects(int xCoordinates_Array[], int yCoordinates_Array[], int points, Color color, BasicStroke stroke) {
		xPos = xCoordinates_Array;
		yPos = yCoordinates_Array;
		numOfPoints = points;
		
		onScreen = true;
		this.color = color;
		this.stroke = stroke;
		polygon = new Polygon(xPos, yPos, numOfPoints);		// <---- Creates the polygon using the given variables
	}
	
	void paint(Graphics2D g) {		// <---- Draws the polygon for the BuildingObject
		g.setColor(color);
		g.setStroke(stroke);
		g.fillPolygon(polygon);
	}
	
}
