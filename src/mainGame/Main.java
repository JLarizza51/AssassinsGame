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
	static JFrame window;
	DrawingPanel drPanel = new DrawingPanel();	
	
//Colors, Font, and Strokes
	Color White = new Color (255, 255, 255);
	Color Blue = new Color (0, 0, 255);
	Color Red = new Color (255, 0, 0);
	Color Green = new Color(0, 255, 0);
	Color Black = new Color (0, 0, 0);

//Timer	Variables
	Timer mainTimer;	// <---- Initializes the mainTimer
	int tSpeed = 5;	// <---- Sets the Timer Speed
	
//Player Variables
	String imageFileName = "Frisk_Back";
	
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
    static ArrayList<ColosseumBuildingObjects> coloseeumBuildings = new ArrayList<ColosseumBuildingObjects>();
	
	public static void main(String[] args) {new Main();}

	Main() {
		GUISetup();
		mapSetup();
		timerSetup();
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
	    DrawGameMap.initializeBuildings(originX, originY);
	}
	
	void timerSetup() {
		mainTimer = new Timer(tSpeed, new MainTimer());	
		mainTimer.start();	
	}
	
	void playerMove() {
		
		if (keyW) {
			imageFileName = "Frisk_Back.png";
			checkCollision("UP");
		}
		if (keyA) {
			imageFileName = "Frisk_Left.png";
			checkCollision("LEFT");
		}
		if (keyS) {
			imageFileName = "Frisk_Forward.png";
			checkCollision("DOWN");
		}
		if (keyD) {
			imageFileName = "Frisk_Right.png";
			checkCollision("RIGHT");
		}
		
	}
	
	void checkCollision(String direction) {
		
		boolean collisionUP = false,
				collisionLEFT = false,
				collisionDOWN = false,
				collisionRIGHT = false;
	
		for (BuildingObjects b: buildings) {
			
			if (direction.equals("UP")) {
				if (b.polygon.intersects(player.x, player.y-player.speed, player.radius*2, player.radius*2)) collisionUP = true;
			}
			
			if (direction.equals("LEFT")) {
				if (b.polygon.intersects(player.x-player.speed, player.y, player.radius*2, player.radius*2)) {
					collisionLEFT = true;
					System.out.println(b.numOfPoints);
				}
			}
			
			if (direction.equals("DOWN")) {
				if (b.polygon.intersects(player.x, player.y, player.radius*2, player.radius*2+player.speed)) collisionDOWN = true;
			}
			
			if (direction.equals("RIGHT")) {
				if (b.polygon.intersects(player.x, player.y, player.radius*2+player.speed, player.radius*2)) collisionRIGHT = true;
			}
		}
		
		if (!collisionUP && direction.equals("UP")) player.yLoc-=player.speed;
		if (!collisionLEFT && direction.equals("LEFT")) player.xLoc-=player.speed;
		if (!collisionDOWN && direction.equals("DOWN")) player.yLoc+=player.speed;
		if (!collisionRIGHT && direction.equals("RIGHT")) player.xLoc+=player.speed;
	
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
			
			drawMap(g);
			drawPlayer(g);
			
		}
		
	}
	
	void drawPlayer(Graphics2D g) {
		
		int playerX = (int) (WINW/2-player.radius);
		int playerY = (int) (WINW/2-player.radius);
		
		g.setColor(Red);
		g.fillOval(playerX, playerY, (int)(player.radius*2), (int)(player.radius*2));
		
		
		
		
		BufferedImage PlayerImg = null;
		try { PlayerImg = ImageIO.read(new File(imageFileName)); 	// <---- Loads the player Sprite file
		} catch (IOException e) {}
		
		g.drawImage(PlayerImg, player.x, player.y, drPanel);
//		g.drawImage(PlayerImg, 0, 0, 38, 58, player.x, player.y, (int)(player.x+player.radius*2), (int)(player.y+player.radius*2), drPanel);
		
	}
	
	void drawMap(Graphics2D g) {
		
	//everywhere that is the Map White
		g.setColor(White);
		g.fillRect(originX, originY, DrawGameMap.mapW, DrawGameMap.mapH);
		
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
