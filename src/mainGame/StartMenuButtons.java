package mainGame;

import java.awt.Rectangle;

@SuppressWarnings("serial")
class StartMenuButtons extends Rectangle {

//Variables	
	int main = 0,
		file = 1,
		arc;
	
	boolean hovering;
	
	StartMenuButtons(int x, int y, int type) {
		this.x = x;
		this.y = y;
		
		this.arc = startMenu.WIN/100;
		this.hovering = false;
		
		if (type==main) {
			height=startMenu.WIN/10;
			width=height*3;
		}
		
		if (type==file) {
			width=startMenu.WIN/4;
			height=width;
		}
		
	}
	
	
	
	
}
