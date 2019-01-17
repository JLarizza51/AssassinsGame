package mainGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Main implements KeyListener{
	
// --------------------------------------	
// ---------- Global Variables ----------
// --------------------------------------	

//JFrame and JWindow Creations
	String gameTitle = "Assassins";
	final static int WINW = 1500;
	final static int mapW = Main.WINW/3*50;
	final static int mapH = mapW/5*3;
	static JFrame window;
	DrawingPanel drPanel = new DrawingPanel();	
	
//NUMBERS USED FOR RATIOS	
	static double ratioW = mapW/2500.0,
		   	      ratioH = mapH/1500.0;
	
//Colors, Font, and Strokes
	Color White = new Color (255, 255, 255);
	Color Blue = new Color (0, 0, 255);
	Color Red = new Color (255, 0, 0);
	Color Green = new Color(0, 255, 0);
	Color Black = new Color (0, 0, 0);
	Color PlayerColor = Red;

//Timer	Variables
	Timer mainTimer;	// <---- Initializes the mainTimer
	int tSpeed = 5;	// <---- Sets the Timer Speed
	
//Player Variables
	int playerSprite_BACK  = 0,
		playerSprite_LEFT  = 1,
		playerSprite_RIGHT = 2,
		playerSprite_FRONT = 3,
		playerSprite_CURRENT = playerSprite_FRONT;
			
	
	String name = "Josh",
	       location = "Anywhere Else";
       int maxHP = 55,
    	   HP = 35,
    	   enemiesKilled = 4;
    
    Player player = new Player(name, location, maxHP, HP, enemiesKilled);
    int originX = (player.xLoc-player.x)*-1,
    	originY = (player.yLoc-player.y)*-1;
  
//Input Variables
    boolean keyW = false,
    		keyA = false,
    		keyS = false,
    		keyD = false;
    
//Initialize Building Objects
    static BuildingObjects boarder,					
                           maze,
                           mansionFenceNorth,
                           mansionFenceSouth,
                           mansion,
                           colosseumBoarder,
                           spawnPointBoarderNW,
                           spawnPointBoarderSW,
                           spawnPointBoarderSE,
                           spawnPointBoarderNE,
                           triangleBuilding1,   
                           mansionSideBuilding1,
                           mansionSideBuilding2,
                           triangleBuilding2,
                           houseArea1,
                           spawnPointSideStreetNORTH,
                           spawnPointSideStreetSOUTH,
                           buildingWithBottomEntrance,
                           hiddenCourtyard,
                           hiddenCourtyardInterior,
                           houseArea2,
                           houseArea3OUTSIDE,
                           houseArea3INSIDE,
                           southEastWall,
                           southEastNorthBuilding,
                           southEastSouthBuilding,
                           southEastExtendedBoarder,
                           townSquareWEST,
                           townSquareSOUTHEAST,
                           townSquareNORTHEAST;	
    
	static ColosseumBuildingObjects colosseumLarge,
	                                colosseumSmall1,
	                                colosseumSmall2;
    
	static ArrayList<BuildingObjects> buildings = new ArrayList<BuildingObjects>(); 
    static ArrayList<ColosseumBuildingObjects> colosseumBuildings = new ArrayList<ColosseumBuildingObjects>();
	
	public static void main(String[] args) {new Main();}

	Main() {
		GUISetup();
		mapSetup();
		timerSetup();
		addObjectsToArrayList();
	}
	
	void GUISetup() {
		window = new JFrame(gameTitle);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		drPanel.addKeyListener(this);
		window.add(drPanel);
		window.pack();
		window.setVisible(true);
	}
	
	void mapSetup() {
		originX = (player.xLoc-player.x)*-1;
	    originY = (player.yLoc-player.y)*-1;
	    DrawBuildingObjects.initializeBuildings(originX, originY);
	}
	
	void addObjectsToArrayList() {
		buildings.clear();
		buildings.add(boarder);
		buildings.add(maze);
		buildings.add(mansionFenceNorth);
		buildings.add(mansionFenceSouth);
		buildings.add(mansion);
		buildings.add(colosseumBoarder);
		buildings.add(spawnPointBoarderNW);
		buildings.add(spawnPointBoarderSW);
		buildings.add(spawnPointBoarderSE);
		buildings.add(spawnPointBoarderNE);
		buildings.add(triangleBuilding1); 
		buildings.add(mansionSideBuilding1);
		buildings.add(mansionSideBuilding2);
		buildings.add(triangleBuilding2);
		buildings.add(houseArea1);
		buildings.add(spawnPointSideStreetNORTH);
        buildings.add(spawnPointSideStreetSOUTH);
        buildings.add(buildingWithBottomEntrance);
        buildings.add(hiddenCourtyard);
        buildings.add(hiddenCourtyardInterior);
        buildings.add(houseArea2);
        buildings.add(houseArea3OUTSIDE);
        buildings.add(houseArea3INSIDE);
        buildings.add(southEastWall);
        buildings.add(southEastNorthBuilding);
        buildings.add(southEastSouthBuilding);
        buildings.add(southEastExtendedBoarder);
        buildings.add(townSquareWEST);
        buildings.add(townSquareSOUTHEAST);
        buildings.add(townSquareNORTHEAST);	   

		colosseumBuildings.clear();
		colosseumBuildings.add(colosseumLarge);
		colosseumBuildings.add(colosseumSmall1);
		colosseumBuildings.add(colosseumSmall2);
	}
	
	void timerSetup() {
		mainTimer = new Timer(tSpeed, new MainTimer());	
		mainTimer.start();	
	}
	
	void playerMove() {
		
		if (keyW) {
			playerSprite_CURRENT = playerSprite_BACK;
			checkCollision("UP");
		}
		if (keyA) {
			playerSprite_CURRENT = playerSprite_LEFT;
			checkCollision("LEFT");
		}
		if (keyS) {
			playerSprite_CURRENT = playerSprite_FRONT;
			checkCollision("DOWN");
		}
		if (keyD) {
			playerSprite_CURRENT = playerSprite_RIGHT;
			checkCollision("RIGHT");
		}
		
	}
	
	void checkCollision(String direction) {
		
		boolean collisionUP = false,
				collisionLEFT = false,
				collisionDOWN = false,
				collisionRIGHT = false;
		
		if (direction.equals("UP")) {
			for (BuildingObjects b: buildings) {
				if (b.polygon.intersects(player.x, player.y-player.speed, player.width, player.height)) collisionUP = true;
			}
			if (!collisionUP) player.yLoc-=player.speed;
		}
		
		if (direction.equals("LEFT")) {
			for (BuildingObjects b: buildings) {
				if (b.polygon.intersects(player.x-player.speed, player.y, player.width, player.height)) collisionLEFT = true;
			}
			if (!collisionLEFT) player.xLoc-=player.speed;
		}
		
		if (direction.equals("DOWN")) {
			for (BuildingObjects b: buildings) {
				if (b.polygon.intersects(player.x, player.y, player.width, player.height+player.speed)) collisionDOWN = true;
			}
			if (!collisionDOWN) player.yLoc+=player.speed;
		}
		
		if (direction.equals("RIGHT")) {
			for (BuildingObjects b: buildings) {
				if (b.polygon.intersects(player.x, player.y, player.width+player.speed, player.height)) collisionRIGHT = true;
			}
			if (!collisionRIGHT) player.xLoc+=player.speed;
		}
	
	}
	
	
// --------------------------------------
// ---------- GRAPHICS METHODS ----------
// --------------------------------------
	
	@SuppressWarnings("serial")
	private class DrawingPanel extends JPanel {
		
		DrawingPanel() {this.setPreferredSize(new Dimension(WINW, WINW));}
		
		@Override
		public void paintComponent(Graphics g1) {
			Graphics2D g = (Graphics2D) g1;
			
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			super.paintComponent(g1);
			this.requestFocus();
			
		//Makes the Background Black
			g.setColor(Black);
			g.fillRect(0, 0, WINW, WINW);
		
		//everywhere that is the Map White
			g.setColor(White);
			g.fillRect(originX, originY, DrawBuildingObjects.mapW, DrawBuildingObjects.mapH);
			
			drawStoneTexture(g);
			drawMap(g);
			drawPlayer(g);
			
		}
		
	}
	
	void drawStoneTexture(Graphics2D g) {
		
		BufferedImage TextureImg = null;
		try { TextureImg = ImageIO.read(new File("TextureSprites.png"));
		} catch (IOException e) {
			System.out.println("CANT FIND IMAGE");
			System.exit(0);
		}
		
		int textureX = 0,
		    textureY = 0,
		    textureW = 50,
		    textureH = 50;
		
//		for (int i=0; i<mapW; i+=(int)(textureW*ratioW)) {	
//			for (int j=0; j<mapH; j+=(int)(textureH*ratioH)) {
				
//		for (int i=0; i<100; i++) {
//			for (int j=0; j<100; j++) {
//				g.drawImage(TextureImg,
//						i*textureW, j*textureH, textureW, textureH,
//						textureX, textureY, textureW, textureH,
//					    null);
//			}
//		}
		
				
				
//			}
//		}	
			
		
		
		
	}
	
	void drawPlayer(Graphics2D g) {
		
		BufferedImage PlayerImg = null;
		try { PlayerImg = ImageIO.read(new File("PlayerSpritesTEST.png")); 	// <---- Loads the player Sprite file
		} catch (IOException e) {}
		
		g.drawImage(PlayerImg,
			player.x, player.y, player.x+player.width, player.y+player.height,
			38*playerSprite_CURRENT, 0, 38*(playerSprite_CURRENT+1)-1, 57,
		    drPanel);
		
	}
	
	void drawMap(Graphics2D g) {
		
		boarder.paint(g); 
		maze.paint(g);
        mansionFenceNorth.paint(g);
        mansionFenceSouth.paint(g);
        mansion.paint(g);
        colosseumBoarder.paint(g);
        spawnPointBoarderNW.paint(g);
        spawnPointBoarderSW.paint(g);
        spawnPointBoarderSE.paint(g);
        spawnPointBoarderNE.paint(g);
        triangleBuilding1.paint(g);   
        mansionSideBuilding1.paint(g);
        mansionSideBuilding2.paint(g);
        triangleBuilding2.paint(g);
        houseArea1.paint(g);
        spawnPointSideStreetNORTH.paint(g);
        spawnPointSideStreetSOUTH.paint(g);
        buildingWithBottomEntrance.paint(g);
        hiddenCourtyard.paint(g);
        hiddenCourtyardInterior.paint(g);
        houseArea2.paint(g);
        houseArea3OUTSIDE.paint(g);
        houseArea3INSIDE.paint(g);
        southEastWall.paint(g);
        southEastNorthBuilding.paint(g);
        southEastSouthBuilding.paint(g);
        southEastExtendedBoarder.paint(g);
        colosseumLarge.paint(g);
        colosseumSmall1.paint(g);
        colosseumSmall2.paint(g);
        townSquareWEST.paint(g);
        townSquareSOUTHEAST.paint(g);
        townSquareNORTHEAST.paint(g);
        
	}

// --------------------------------------
// --------------- TIMER ----------------
// --------------------------------------
	private class MainTimer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
				playerMove();
				mapSetup();
				addObjectsToArrayList();
				window.repaint();	
			}
		}
	
	@Override
	public void keyPressed(KeyEvent e) {	
		
		if (e.getKeyChar()=='W' || e.getKeyChar()=='w') keyW = true;
		if (e.getKeyChar()=='A' || e.getKeyChar()=='a') keyA = true;
		if (e.getKeyChar()=='S' || e.getKeyChar()=='s') keyS = true;
		if (e.getKeyChar()=='D' || e.getKeyChar()=='d') keyD = true;
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {	
		
		if (e.getKeyChar()=='W' || e.getKeyChar()=='w') keyW = false;
		if (e.getKeyChar()=='A' || e.getKeyChar()=='a') keyA = false;
		if (e.getKeyChar()=='S' || e.getKeyChar()=='s') keyS = false;
		if (e.getKeyChar()=='D' || e.getKeyChar()=='d') keyD = false;
		
	}
	
	public void keyTyped(KeyEvent arg0) {}
}
