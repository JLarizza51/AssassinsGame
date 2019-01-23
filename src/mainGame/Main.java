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
	final static int WINW = 1500;				// <---- The Width and the Height of the window (has to be a square)
	final static int mapW = Main.WINW/3*50;		// <---- The Map width, based off of the window width (it's larger than the window)
	final static int mapH = mapW/5*3;			// <---- The Map height, based off the map width, 60% of the map width
	static JFrame window;						// <---- Initializes the JFrame
	DrawingPanel drPanel = new DrawingPanel();	// <---- Creates the Drawing Panel
	
//NUMBERS USED FOR RATIOS	
	static double ratioW = mapW/2500.0,			// <---- Doubles used for drawing the map, so no matter what
		   	      ratioH = mapH/1500.0;			// 		 the window width is, the map will be properly scaled
	
//Colors
	Color White = new Color (255, 255, 255);	// <---- Creates colours
	Color Black = new Color (0, 0, 0);

//Timer	Variables
	Timer mainTimer;	// <---- Initializes the mainTimer
	int tSpeed = 5;		// <---- Sets the Timer Speed
	
	
//Player Sprite Variables
	int playerSprite_BACK  = 0,							// <---- Constants for player sprites
			playerSprite_LEFT  = 1,						//		 When W, A, S, or D is pressed,
			playerSprite_RIGHT = 2,						// 		 the sprite changes to the 
			playerSprite_FRONT = 3,						//		 corresponding direction
			playerSprite_CURRENT = playerSprite_FRONT;
	
//Player Variables
	String name = "Josh",
	       location = "Spawn Point";
       int maxHP = 20,
    	   HP = 20,
    	   enemiesKilled = 0;
    
    Player player = new Player(name, location, maxHP, HP, enemiesKilled);
    int originX = (player.xLoc-player.x)*-1,
    	originY = (player.yLoc-player.y)*-1;
  
//Input Variables
    boolean keyW = false,	// <---- Boolean variables for player
    		keyA = false,	//		 inputs. When the corresponding
    		keyS = false,	//		 key is pressed, the variable is
    		keyD = false;	//		 is set to true, and false when
    						//		 released
    
//2D Array for Texture Tiles
    int textureSTONE = 0,					// <---- Constants used for tiling
    	textureGRASS = 1,
    	textureWATER = 2,
    	textureWOOD  = 3;
    int textureTiles[][] = new int[48][28]; // <---- Creates the 2D array of tiles
    
//Initialize Building Objects
    static BuildingObjects boarder,					// <---- Creates the BuildingObjects			
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
                           townSquarePond,
                           townSquareWEST,
                           townSquareSOUTHEAST,
                           townSquareNORTHEAST;	
    
	static ColosseumBuildingObjects colosseumLarge,
	                                colosseumSmall1,
	                                colosseumSmall2;
    
	static ArrayList<BuildingObjects> buildings = new ArrayList<BuildingObjects>();  							// <---- Puts the BuildingObjects into an Array List
    static ArrayList<ColosseumBuildingObjects> colosseumBuildings = new ArrayList<ColosseumBuildingObjects>();
	
	public static void main(String[] args) {new Main();}

	Main() {
		GUISetup();					// <---- These methods are run
		fillTextureTilesArray();	//		 when the program is
		mapSetup();					//		 first run
		timerSetup();
		addObjectsToArrayList();
	}
	
	void GUISetup() {
		window = new JFrame(gameTitle);							// <---- Sets the Title and creates the JFrame
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// <---- Allows for closing
		window.setResizable(false);								// <---- Doesn't allow resizable
		drPanel.addKeyListener(this);							// <---- Adds the key listener to the Drawing Panel
		window.add(drPanel);									// <---- Adds the Drawing Panel to the JFrame
		window.pack();											// <---- Packs the JFrame into the window
		window.setVisible(true);								// <---- Sets the window to visible
	}
	
	void mapSetup() {
		originX = (player.xLoc-player.x)*-1;						// <---- originX and originY are variable points for where
	    originY = (player.yLoc-player.y)*-1;						//       everything on the map is drawn, are creates by taking
	    															//		 the player's location on the map (not the window) and
	    															//		 subtracting the x location on the screen (and making it
	    															//       negative
	    
	    DrawBuildingObjects.initializeBuildings(originX, originY);	// <---- Runs the Initialize Buildings method in the
	    															// 		 DrawBuildingObjects file, using the originX
	    															// 	     originY variables
	   
	}																		
																	
	void fillTextureTilesArray() {
		
		for (int i=0; i<48; i++) {					 // <---- Goes through the 2D Array of tiles, and
			for (int j=0; j<28; j++) {				 //		  sets them all to STONE to start off with
				textureTiles[i][j] = textureSTONE;
			}
		}
		
		for (int i=0; i<48; i++) {				 // <---- Goes through the 2D array of tiles again, and
			for (int j=0; j<28; j++) {			 //		  sets certain tiles to GRASS, WOOD, or WATER
				
			//GRASS BLOCKS
				if (i>=1 && i<=8 && j>=1 && j<=7)     textureTiles[i][j] = textureGRASS;
				if (i>=0 && i<=8 && j>=8 && j<=12)    textureTiles[i][j] = textureGRASS;
				if (i>=0 && i<=3 && j>=13 && j<=14)   textureTiles[i][j] = textureGRASS;
				if (i>=0 && i<=8 && j>=15 && j<=20)   textureTiles[i][j] = textureGRASS;
				
			//WOOD BLOCKS
				if (i>=10 && i<=33 && j>=1 && j<=10)  textureTiles[i][j] = textureWOOD;
				if (i>=22 && i<=26 && j>=11 && j<=15) textureTiles[i][j] = textureWOOD;
				if (i>=21 && i<=24 && j>=16 && j<=26) textureTiles[i][j] = textureWOOD;
				if (i>=18 && i<=20 && j>=17 && j<=18) textureTiles[i][j] = textureWOOD;
				if (i>=15 && i<=20 && j>=19 && j<=20) textureTiles[i][j] = textureWOOD;
				if (i>=13 && i<=20 && j>=21 && j<=26) textureTiles[i][j] = textureWOOD;
				if (i>=11 && i<=12 && j>=22 && j<=26) textureTiles[i][j] = textureWOOD;
				if (i>=25 && i<=27 && j>=21 && j<=26) textureTiles[i][j] = textureWOOD;
				if (i>=30 && i<=47 && j>=15 && j<=26) textureTiles[i][j] = textureWOOD;
				if (i>=43 && i<=45 && j>=10 && j<=14) textureTiles[i][j] = textureWOOD;
				if (i>=34 && i<=42 && j>=4 && j<=11)  textureTiles[i][j] = textureWOOD;
				if (i>=27 && i<=33 && j>=11 && j<=11) textureTiles[i][j] = textureWOOD;
				
			//WATER BLOCKS
				if (i>=35 && i<=37 && j>=7 && j<=9)   textureTiles[i][j] = textureWATER;
				
			}
		}
		
	//SINGLE TILES
		textureTiles[17][18] = textureWOOD;		// <---- Sets indiviudal tiles
		textureTiles[18][18] = textureWOOD;		//		 in the array to their
		textureTiles[14][20] = textureWOOD;		//		 textures
		textureTiles[10][23] = textureWOOD;
		textureTiles[10][24] = textureWOOD;
		textureTiles[10][25] = textureWOOD;
		textureTiles[10][26] = textureWOOD;
		textureTiles[20][16] = textureWOOD;
		textureTiles[28][26] = textureWOOD;
		textureTiles[29][26] = textureWOOD;
		textureTiles[34][2]  = textureWOOD;
		textureTiles[34][3]  = textureWOOD;
		textureTiles[40][8]  = textureSTONE;
		textureTiles[41][8]  = textureSTONE;
		textureTiles[42][8]  = textureSTONE;
		textureTiles[40][9]  = textureSTONE;
		textureTiles[41][9]  = textureSTONE;
		textureTiles[42][9]  = textureSTONE;
	}
	
	void addObjectsToArrayList() {
		buildings.clear();							// <---- This Method clears the "buildings" 
		buildings.add(boarder);						//		 ArrayList of all contents, then re-ads
		buildings.add(maze);						//		 them all, with their new paramiters and
		buildings.add(mansionFenceNorth);			//		 locations
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
        buildings.add(townSquarePond);
        buildings.add(townSquareWEST);
        buildings.add(townSquareSOUTHEAST);
        buildings.add(townSquareNORTHEAST);	   

		colosseumBuildings.clear();
		colosseumBuildings.add(colosseumLarge);
		colosseumBuildings.add(colosseumSmall1);
		colosseumBuildings.add(colosseumSmall2);
	}
	
	void timerSetup() {
		mainTimer = new Timer(tSpeed, new MainTimer());		// <---- Creates the Timer
		mainTimer.start();									// <---- Starts the timer
	}
	 
	void checkPlayerMove() {
															// <---- This method check is the keys
		if (keyW) {											//		 W, A, S, or D are being pressed,
			playerSprite_CURRENT = playerSprite_BACK;		//		 changes the sprite being displayed
			checkCollision("UP");							//		 to match the direction the player
		}													//		 is moving, and then runs the 
		if (keyA) {											//		 "checkCollision" method with the 
			playerSprite_CURRENT = playerSprite_LEFT;		//		 corresponding direction
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
		
		boolean collisionUP = false,		// <---- Boolean varaibles for checking
				collisionLEFT = false,		//		 if the player is colliding with
				collisionDOWN = false,		//		 a builing in a specific direction
				collisionRIGHT = false;
		
		if (direction.equals("UP")) {														// <---- If this is the direction
			for (BuildingObjects b: buildings) {											// <---- Goes through every single building in the array list
				if (b.polygon.intersects(player.x, player.y-player.speed, 					// <---- If the building interects the player, with some extened area in the direction of the collision
										 player.width, player.height)) collisionUP = true;
			}
			if (!collisionUP) player.yLoc-=player.speed;									// <---- If the player isn't colliding, then they can move in that direction
		}
		
		if (direction.equals("LEFT")) {
			for (BuildingObjects b: buildings) {
				if (b.polygon.intersects(player.x-player.speed, player.y, 
										 player.width, player.height)) collisionLEFT = true;
			}
			if (!collisionLEFT) player.xLoc-=player.speed;
		}
		
		if (direction.equals("DOWN")) {
			for (BuildingObjects b: buildings) {
				if (b.polygon.intersects(player.x, player.y, 
									   	 player.width, player.height+player.speed)) collisionDOWN = true;
			}
			if (!collisionDOWN) player.yLoc+=player.speed;
		}
		
		if (direction.equals("RIGHT")) {
			for (BuildingObjects b: buildings) {
				if (b.polygon.intersects(player.x, player.y, 
										 player.width+player.speed, player.height)) collisionRIGHT = true;
			}
			if (!collisionRIGHT) player.xLoc+=player.speed;
		}
	
	}
	
	
// --------------------------------------
// ---------- GRAPHICS METHODS ----------
// --------------------------------------
	
	@SuppressWarnings("serial")
	private class DrawingPanel extends JPanel {
		
		DrawingPanel() {this.setPreferredSize(new Dimension(WINW, WINW));} // <---- Creates the drawing panel with the Window Width
		
		@Override
		public void paintComponent(Graphics g1) {
			Graphics2D g = (Graphics2D) g1;			// <---- Creates the 2D graphics tool
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			super.paintComponent(g1);
			this.requestFocus();
			
		//Makes the Background Black
			g.setColor(Black);
			g.fillRect(0, 0, WINW, WINW);
		
		//everywhere that is the Map White
			g.setColor(White);
			g.fillRect(originX, originY, DrawBuildingObjects.mapW, DrawBuildingObjects.mapH);

	        townSquarePond.paint(g);		// <---- Draws the Pond object, which need to be drawn before the tiles because it's not a building, its a texture
			drawTexturesTexture(g);			// <---- Draws the Ground Textures
			drawMap(g);						// <---- Draws the map (building objects)
			drawPlayer(g);					// <---- Draws the player in the center of the screen
			
		}
		
	}
	
	void drawTexturesTexture(Graphics2D g) {
		
		BufferedImage TextureImg = null;									// <---- Initializes the Texture Sprite Image
		try { TextureImg = ImageIO.read(new File("TextureSprites.png"));	// <---- Loads the files
		} catch (IOException e) {
			System.out.println("CANT FIND IMAGE");
			System.exit(0);
		}	
		
		double startingX = ratioW*50,			// <---- The starting variables are where the first tile is
			   startingY = ratioH*50,			//		 drawn, and so where all the others are based off
			   
			       tileW = ratioW*50,			// <---- The tileW and tileH variables are for drawing the
			       tileH = ratioH*50;			//		 individual tiles
		
		int srcX1 = 0,			// <---- The initial source variables
		    srcY1 = 0,			//		 for the texture sprite image
		    srcX2 = 50,
		    srcY2 = 50;
			
		for (int i=0; i<48; i++) {				// <---- Goes through the For loop for the
			for (int j=0; j<28; j++) {			//		 2D Array of Texture Tiles
				
				if (textureTiles[i][j]==textureSTONE) {		// <---- Depending on what the textureTile
					srcX1 = 0;								//		 is set to, the source X1 and Y1
					srcY1 = 0;								//       locations are changed
				}
				
				if (textureTiles[i][j]==textureGRASS) {
					srcX1 = 50;
					srcY1 = 0;
				}
				
				if (textureTiles[i][j]==textureWATER) {
					srcX1 = 0;
					srcY1 = 50;
				}
				
				if (textureTiles[i][j]==textureWOOD) {
					srcX1 = 50;
					srcY1 = 50;
				}
				
				srcX2 = srcX1 + 49;		// <---- The X2 and Y2 Variables are 49 pixels
				srcY2 = srcY1 + 49;		//		 away from the X1 and Y2 Variables
				
				int tileX1 = (int)(startingX+(tileW*i))+originX,	// <---- Sets the current Tile's location variables,
					tileY1 = (int)(startingY+(tileW*j))+originY,	//		 based off the startingX or Y, and what the current
					tileX2 = (int)(tileX1+tileW),					//		 array number is in the for loop
					tileY2 = (int)(tileY1+tileH);
				
				g.drawImage(TextureImg,						// <---- Draws the current Tile
						tileX1, tileY1, tileX2, tileY2,
						srcX1, srcY1, srcX2, srcY2,
					    null);			
			}
		}
		
		
	}
	
	void drawPlayer(Graphics2D g) {
		
		BufferedImage PlayerImg = null;
		try { PlayerImg = ImageIO.read(new File("PlayerSpritesTEST.png")); 	// <---- Loads the player Sprite file
		} catch (IOException e) {}
		
		g.drawImage(PlayerImg,														// <---- Draws the player sprite in the centre of the screen,
			player.x, player.y, player.x+player.width, player.y+player.height,		//		 using the section of the sprite file that matches the
			38*playerSprite_CURRENT, 0, 38*(playerSprite_CURRENT+1)-1, 57,			//		 direction the player is moving
		    null);
		
	}
	
	void drawMap(Graphics2D g) {
		
		for (BuildingObjects b: buildings) { 	// <---- This goes through all of the BuildingObjects 
			b.paint(g);							//		 in the ArrayList and draws them
		}
        
	}

// --------------------------------------
// --------------- TIMER ----------------
// --------------------------------------
	private class MainTimer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {		// <---- Thes methods are run every single timer second
				checkPlayerMove();				// <---- Checks if the player is moving		
				mapSetup();						// <---- Sets the map up again, using the player's current location
				addObjectsToArrayList();		// <---- Resets the Building Objects ArrayList with the new locations
				window.repaint();				// <---- Repaints the window
			}
		}
	
	@Override
	public void keyPressed(KeyEvent e) {	
		
	// If a key is pressed, it's corresponding boolean variables is set to true
		if (e.getKeyChar()=='W' || e.getKeyChar()=='w') keyW = true;
		if (e.getKeyChar()=='A' || e.getKeyChar()=='a') keyA = true;
		if (e.getKeyChar()=='S' || e.getKeyChar()=='s') keyS = true;
		if (e.getKeyChar()=='D' || e.getKeyChar()=='d') keyD = true;
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {	
		
	// If a key is released, it's corresponding boolean variables is set to false	
		if (e.getKeyChar()=='W' || e.getKeyChar()=='w') keyW = false;
		if (e.getKeyChar()=='A' || e.getKeyChar()=='a') keyA = false;
		if (e.getKeyChar()=='S' || e.getKeyChar()=='s') keyS = false;
		if (e.getKeyChar()=='D' || e.getKeyChar()=='d') keyD = false;
		
	}
	
	public void keyTyped(KeyEvent arg0) {}
}
