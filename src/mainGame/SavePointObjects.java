package mainGame;

import java.awt.Graphics2D;
import java.awt.Rectangle;

@SuppressWarnings("serial")
class SavePointObjects extends Rectangle {

	
//Variables	
	
	
	SavePointObjects(int xLoc, int yLoc, int shapeWidth) {
		x = xLoc-shapeWidth/2;
		y = yLoc-shapeWidth/2;
		width = shapeWidth;
		height= shapeWidth;
	}
	
	void paint(Graphics2D g) {
		
		
	}
	
}
