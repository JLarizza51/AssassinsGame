package mainGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

@SuppressWarnings("serial")
class BuildingObjects extends Rectangle{
	
	
//Colors and Stroke	
	static Color White = new Color (255, 255, 255);
	static Color Blue = new Color (0, 0, 255);
	static Color Red = new Color (255, 0, 0);
	static Color Green = new Color(0, 255, 0);
	static Color Black = new Color (0, 0, 0);
	static BasicStroke DefaultStroke = new BasicStroke(1);
	
	
//Variables	
	int[] xPos,
		  yPos;
	int numOfPoints;
	
	Polygon polygon;
	Color color;
	BasicStroke stroke;
	
	boolean onScreen;
	
	
	BuildingObjects(int xCoordinates_Array[], int yCoordinates_Array[], int points, Color color, BasicStroke stroke) {
		xPos = xCoordinates_Array;
		yPos = yCoordinates_Array;
		numOfPoints = points;
		
		onScreen = true;
		this.color = color;
		this.stroke = stroke;
		polygon = new Polygon(xPos, yPos, numOfPoints);
	}
	
	void paint(Graphics2D g) {
		g.setColor(color);
		g.setStroke(stroke);
		g.fillPolygon(polygon);
	}
	
}
