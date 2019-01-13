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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Main implements KeyListener{
	
// --------------------------------------	
// ---------- Global Variables ----------
// --------------------------------------	

//JFrame and JWindow Creations
	String gameTitle = "Assassins";
	final static int WINW = 1000;
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
	int tSpeed = 1;	// <---- Sets the Timer Speed
	
//Player Variables
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
                           triangleBuilding1,   
                           mansionSideBuilding1,
                           mansionSideBuilding2,
                           triangleBuilding2,
                           houseArea1,
                           buildingWithBottomEntrance,
                           hiddenCourtyard,
                           hiddenCourtyardInterior,
                           houseArea2,
                           southEastWall,
                           southEastNorthBuilding,
                           southEastSouthBuilding,
                           southEastExtendedBoarder,
                           townSquareWEST,
                           townSquareSOUTHEAST,
                           townSquareNORTHEAST;
    
    static BuildingObjects[] buildings = {boarder,				
                                          maze,
                                          mansionFenceNorth,
                                          mansionFenceSouth,
                                          mansion,
                                          colosseumBoarder,
                                          triangleBuilding1,   
                                          mansionSideBuilding1,
                                          mansionSideBuilding2,
                                          triangleBuilding2,
                                          houseArea1,
                                          buildingWithBottomEntrance,
                                          hiddenCourtyard,
                                          hiddenCourtyardInterior,
                                          houseArea2,
                                          southEastWall,
                                          southEastNorthBuilding,
                                          southEastSouthBuilding,
                                          southEastExtendedBoarder,
                                          townSquareWEST,
                                          townSquareSOUTHEAST,
                                          townSquareNORTHEAST};
	
	public static void main(String[] args) {new Main();}

	Main() {
		GUISetup();
		timerSetup();
		mapSetup();
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
	
	@SuppressWarnings("serial")
	

	private class DrawingPanel extends JPanel {
		
		DrawingPanel() {this.setPreferredSize(new Dimension(WINW, WINW));}
		
		@Override
		public void paintComponent(Graphics g1) {
			Graphics2D g = (Graphics2D) g1;
			
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			super.paintComponent(g1);
			this.requestFocus();
			
			drawMap(g);
			drawPlayer(g);
		}
		
	}
	
	void drawPlayer(Graphics2D g) {
		
		int playerRadius = 24;
		
		int playerX = WINW/2-playerRadius;
		int playerY = WINW/2-playerRadius;
		
		g.setColor(Red);
		g.fillOval(playerX, playerY, playerRadius*2, playerRadius*2);
	}
	
	void drawMap(Graphics2D g) {
		
		boarder.paint(g); 
		maze.paint(g);
        mansionFenceNorth.paint(g);
        mansionFenceSouth.paint(g);
        mansion.paint(g);
        colosseumBoarder.paint(g);
        triangleBuilding1.paint(g);   
        mansionSideBuilding1.paint(g);
        mansionSideBuilding2.paint(g);
        triangleBuilding2.paint(g);
        houseArea1.paint(g);
        buildingWithBottomEntrance.paint(g);
        hiddenCourtyard.paint(g);
        hiddenCourtyardInterior.paint(g);
        houseArea2.paint(g);
        southEastWall.paint(g);
        southEastNorthBuilding.paint(g);
        southEastSouthBuilding.paint(g);
        southEastExtendedBoarder.paint(g);
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
			
				if (keyW) player.yLoc-=player.speed;
				if (keyA) player.xLoc-=player.speed;
				if (keyS) player.yLoc+=player.speed;
				if (keyD) player.xLoc+=player.speed;
				
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
