package mainGame;

import java.awt.Rectangle;

@SuppressWarnings("serial")
class StartMenuBackButtons extends Rectangle {

//Variables
	int arc;
	boolean hovering;
	
	StartMenuBackButtons(int x, int y) {
		
		this.x = x;
		this.y = y;
		
		height = startMenu.WIN/15;
		width = height*2;
		
		
		this.arc = startMenu.WIN/100;
		
	}
}
