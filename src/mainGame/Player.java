package mainGame;

import java.awt.Rectangle;

@SuppressWarnings("serial")
class Player extends Rectangle{

//Variables	
	String name,
		   location;
	   int maxHP,
		   HP,
		   enemiesKilled;
	   
	   int xLoc, yLoc; // <--- the X and Y Location of the Player. This is NOT the location
	   				   //	   on the screen, but rather the location on the map
	   int radius = 24;
	  
	
	
	Player(String name, String location, int maxHP, int HP, int enemiesKilled) {
		this.name = name;
		this.location = location;
		this.maxHP = maxHP;
		this.HP = HP;
		this.enemiesKilled = enemiesKilled;

		
		x = Main.WINW/2-radius;
		y = Main.WINW/2-radius;
		
		if (location.equals("Spawn Point")) {
		
		// Point: 1239, 739
			xLoc = (int) (DrawGameMap.ratioW*1239);
			yLoc = (int) (DrawGameMap.ratioH*739);
			
		}
		
		else {
			
			xLoc = (int) (DrawGameMap.ratioW*706);
			yLoc = (int) (DrawGameMap.ratioH*1282);
			
//			xLoc = (int) (DrawGameMap.ratioW*0);
//			yLoc = (int) (DrawGameMap.ratioH*0);
			
			
		}
	}
	
}
