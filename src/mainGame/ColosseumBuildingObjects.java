package mainGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class ColosseumBuildingObjects {

//Variables
	   int arcX,
	       arcY,
	       arcWidth,
	       arcHeight;
	int startAngle,
	    arcAngle;
	
	Color color;
	BasicStroke stroke;
	
	ColosseumBuildingObjects(int arcX, int arcY, int arcWidth, int arcHeight, int startAngle, int arcAngle, Color color, BasicStroke stroke) {
		this.arcX = arcX;
		this.arcY = arcY;
		this.arcWidth = arcWidth;
		this.arcHeight = arcHeight;
		this.startAngle = startAngle;
		this.arcAngle = arcAngle;
		
		this.color = color;
		this.stroke = stroke;
	}
	
	void paint(Graphics2D g) {
		g.setColor(color);
		g.setStroke(stroke);
		g.drawArc(arcX, arcY, arcWidth, arcHeight, startAngle, arcAngle);
	}
	
}
