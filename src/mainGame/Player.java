package mainGame;

import java.awt.Rectangle;

@SuppressWarnings("serial")
class Player extends Rectangle{

	double ratioW = Main.WINW/1000.0;
	
//Variables	
	String name,			// <---- The Name of the player
		   location;		// <---- Where the player will be spawned into the game
	   int maxHP,			// <---- How many total health points the player can have
		   HP,				// <---- How many health points the player currently has
		   enemiesKilled;	// <---- How many enemies the player has killed
	   
	   int xLoc, yLoc; // <--- the X and Y Location of the Player. This is NOT the location
	   				   //	   on the screen, but rather the location on the map
	   double speed;
	
	Player(String name, String location, int maxHP, int HP, int enemiesKilled) {
		this.name = name;
		this.location = location;
		this.maxHP = maxHP;
		this.HP = HP;
		this.enemiesKilled = enemiesKilled;
		
		this.speed = (int) (ratioW*10);
		
		width  = (int) (ratioW*48);
		height = (int) (ratioW*73);
		
		x = (int) (Main.WINW/2-width/2);
		y = (int) (Main.WINW/2-height/2);
		
		if (location.equals("Mansion")) {
			
		// Point 336, 739
			xLoc = (int) (DrawBuildingObjects.ratioW*336);
			yLoc = (int) (DrawBuildingObjects.ratioH*739);
		}
		
		else {
			
		// Point: 1239, 739
			xLoc = (int) (DrawBuildingObjects.ratioW*1239);
			yLoc = (int) (DrawBuildingObjects.ratioH*739);
			
		}
	}
	
}
