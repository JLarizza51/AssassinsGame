package mainGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class DrawGameMap {
	
// --------------------------------------	
// ---------- Global Variables ----------
// --------------------------------------	
	
//JFrame and JWindow Creations
	String gameTitle = "Assassins";
	final static int mapW = Main.WINW/3*50;
//	final static int mapW = 2500;
	final static int mapH = mapW/5*3;
	static JFrame window;
	DrawingPanel drPanel = new DrawingPanel();
			
//Colors, Font, and Strokes
	static Color White = new Color (255, 255, 255);
	static Color Blue = new Color (0, 0, 255);
	static Color Red = new Color (255, 0, 0);
	static Color Green = new Color(0, 255, 0);
	static Color Black = new Color (0, 0, 0);
	static BasicStroke DefaultStroke = new BasicStroke(1);
	static BasicStroke ColosseumStroke = new BasicStroke(mapW/50);
	
//NUMBERS USED FOR RATIOS
	static double ratioW = mapW/2500.0,
			   	  ratioH = mapH/1500.0;
	

	public static void main(String[] args) {new DrawGameMap();}

	DrawGameMap() {
		GUISetup();
	}
	
	void GUISetup() {
		window = new JFrame(gameTitle);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.add(drPanel);
		window.pack();
		window.setVisible(true);
	}
	
	static void initializeBuildings(int originX, int originY) {
		initializeBoarder(originX, originY);
		initializeMaze(originX, originY);
		initializeMansionFenceNorth(originX, originY);
		initializeMansionFenceSouth(originX, originY);
		initializeMansion(originX, originY);
		
		initializeColosseumBoarder(originX, originY);
		initializeTriangleBuilding1(originX, originY);
		initializeMansionSideBuilding1(originX, originY);
		initializeMansionSideBuilding2(originX, originY);
		initializeTriangleBuilding2(originX, originY);
		initializeHouseArea1(originX, originY);
//		initializeSpawnPointSideStreet(originX, originY);
		initializeBuildingWithBottomEntrance(originX, originY);
		initializeHiddenCourtyard(originX, originY);
		initializeHiddenCourtyardInterior(originX, originY);
		initializeHouseArea2(originX, originY);
		initializeHouseArea3(originX, originY);
		initializeSouthEastWall(originX, originY);
		initializeSouthEastNorthBuilding(originX, originY);
		initializeSouthEastSouthBuilding(originX, originY);
		initializeSouthEastExtendedBoarder(originX, originY);
		initializeColosseum(originX, originY);
		initializeTownSquareWEST(originX, originY);
		initializeTownSquareSOUTHEAST(originX, originY);
		initializeTownSquareNORTHEAST(originX, originY);
		

//Main.spawnPointBoarder = new BuildingObjects(xPos, yPos, #);
//Main.houseArea3 = new BuildingObjects(xPos, yPos, #)		
//Main.colosseum = new BuildingObjects(xPos, yPos, #);
		

		
	}
	
	
	
	@SuppressWarnings("serial")
	private class DrawingPanel extends JPanel {
		
		DrawingPanel() {this.setPreferredSize(new Dimension (mapW, mapH));}
		
		@Override
		public void paintComponent(Graphics g1) {
			Graphics2D g = (Graphics2D) g1;
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			super.paintComponent(g1);
			this.requestFocus();
			
//			initializeBoarder(g);
//			initializeMaze(g);
//			initializeMansionFenceNorth(g);
//			initializeMansionFenceSouth(g);
//			initializeMansion(g);
//			initializeSpawnPointBoarder(g);
//			initializeColosseumBoarder(g);
//			initializeTriangleBuilding1(g);
//			initializeMansionSideBuilding1(g);
//			initializeMansionSideBuilding2(g);
//			initializeTriangleBuilding2(g);
//			initializeHouseArea1(g);
//			initializeSpawnPointSideStreet(g);
//			initializeBuildingWithBottomEntrance(g);
//			initializeHiddenCourtyard(g);
//			initializeHiddenCourtyardInterior(g);
//			initializeHouseArea2(g);
//			initializeHouseArea3(g);
//			initializeSouthEastWall(g);
//			initializeSouthEastNorthBuilding(g);
//			initializeSouthEastSouthBuilding(g);
//			initializeSouthEastExtendedBoarder(g);
//			initializeColosseum(g);
//			initializeTownSquareWEST(g);
//			initializeTownSquareSOUTHEAST(g);
//			initializeTownSquareNORTHEAST(g);
		}
		
	}
	
	static void initializeBoarder(int originX, int originY) {
		
	//POINT LIST
	// 01: 0, 0		02: WINW, 0		03: WINW, WINH		04: 0, WINH			05: 0, 50
	// 06: 50, 50	07: 50, 1450	08: 2450, 1450		09: 2450, 50		10: 0, 50
		
		double point01X = 0,         point02X = mapW,        point03X = mapW,        point04X = 0,           point05X = 0,
			   point06X = ratioW*50, point07X = ratioW*50,   point08X = ratioW*2450, point09X = ratioW*2450, point10X = ratioW*0;
		int xPos[] = {(int)point01X, (int)point02X, (int)point03X, (int)point04X, (int)point05X,
				      (int)point06X, (int)point07X, (int)point08X, (int)point09X, (int)point10X};
		
		
		
		double point01Y = 0,         point02Y = 0,           point03Y = mapH,        point04Y = mapH,        point05Y = ratioH*50,
			   point06Y = ratioH*50, point07Y = ratioH*1450, point08Y = ratioH*1450, point09Y = ratioH*50,   point10Y = ratioH*50;
		int yPos[] = {(int)point01Y, (int)point02Y, (int)point03Y, (int)point04Y, (int)point05Y,
				      (int)point06Y, (int)point07Y, (int)point08Y, (int)point09Y, (int)point10Y};
		
		for (int i=0; i<xPos.length; i++) xPos[i]+=originX;
		for (int i=0; i<yPos.length; i++) yPos[i]+=originY;
		
		Main.boarder = new BuildingObjects(xPos, yPos, 10, Black, DefaultStroke);
		
	}
	
	static void initializeMaze(int originX, int originY) {
		
	//POINTS LIST
	//	01: 480, 81			02: 81, 81 			03: 81, 430			04:	480, 430		05: 480, 131
	//	06:	131, 131		07: 131, 380		08: 430, 380		09: 430, 181 		10: 181, 181	
	//	11: 181, 330		12:	380, 330		13: 380, 231		14: 356, 231		15:	356, 306
	//	16: 205, 306		17: 205, 205		18:	406, 205		19:	406, 356		20: 155, 356 
	//	21:	155, 155		22: 456, 155		23: 456, 406		24: 105, 406		25: 105, 105	
	//	26:	480, 105
	
	//X Coordinates
		double point01X = ratioW*480,  point02X = ratioW*81,   point03X = ratioW*81,   point04X = ratioW*480,  point05X = ratioW*480,
			   point06X = ratioW*131,  point07X = ratioW*131,  point08X = ratioW*430,  point09X = ratioW*430,  point10X = ratioW*181,
			   point11X = ratioW*181,  point12X = ratioW*380,  point13X = ratioW*380,  point14X = ratioW*356,  point15X = ratioW*356,
			   point16X = ratioW*205,  point17X = ratioW*205,  point18X = ratioW*406,  point19X = ratioW*406,  point20X = ratioW*155,
			   point21X = ratioW*155,  point22X = ratioW*456,  point23X = ratioW*456,  point24X = ratioW*105,  point25X = ratioW*105,
			   point26X = ratioW*480;
			
	//Y Coordinates
		double point01Y = ratioH*81,   point02Y = ratioH*81,   point03Y = ratioH*430,  point04Y = ratioH*430,  point05Y = ratioH*131,
			   point06Y = ratioH*131,  point07Y = ratioH*380,  point08Y = ratioH*380,  point09Y = ratioH*181,  point10Y = ratioH*181,
			   point11Y = ratioH*330,  point12Y = ratioH*330,  point13Y = ratioH*231,  point14Y = ratioH*231,  point15Y = ratioW*306,
			   point16Y = ratioH*306,  point17Y = ratioH*205,  point18Y = ratioH*205,  point19Y = ratioH*356,  point20Y = ratioH*356,
			   point21Y = ratioH*155,  point22Y = ratioH*155,  point23Y = ratioH*406,  point24Y = ratioH*406,  point25Y = ratioH*105,
			   point26Y = ratioH*105;
			
		int xPos[] = {(int)point01X,  (int)point02X,  (int)point03X,  (int)point04X,  (int)point05X,
					  (int)point06X,  (int)point07X,  (int)point08X,  (int)point09X,  (int)point10X,
					  (int)point11X,  (int)point12X,  (int)point13X,  (int)point14X,  (int)point15X,
					  (int)point16X,  (int)point17X,  (int)point18X,  (int)point19X,  (int)point20X,
					  (int)point21X,  (int)point22X,  (int)point23X,  (int)point24X,  (int)point25X,
					  (int)point26X};
			
		int yPos[] = {(int)point01Y,  (int)point02Y,  (int)point03Y,  (int)point04Y,  (int)point05Y,
					  (int)point06Y,  (int)point07Y,  (int)point08Y,  (int)point09Y,  (int)point10Y,
					  (int)point11Y,  (int)point12Y,  (int)point13Y,  (int)point14Y,  (int)point15Y,
					  (int)point16Y,  (int)point17Y,  (int)point18Y,  (int)point19Y,  (int)point20Y,
					  (int)point21Y,  (int)point22Y,  (int)point23Y,  (int)point24Y,  (int)point25Y,
					  (int)point26Y};
	
		for (int i=0; i<xPos.length; i++) xPos[i]+=originX;
		for (int i=0; i<yPos.length; i++) yPos[i]+=originY;
			
		Main.maze = new BuildingObjects(xPos, yPos, 26, Black, DefaultStroke);
		
//		Polygon building = new Polygon(xPos, yPos, 26);
//			
//		g.setColor(Black);
//		g.setStroke(DefaultStroke);
//		g.fillPolygon(building);
		
		
	}

	static void initializeMansionFenceNorth(int originX, int originY) {
		
	//POINTS LIST
	//	01:	332, 720		02: 367, 720		03: 367, 670		04: 475, 670		05: 475, 465
	//	06:	85, 465		    07: 85, 625		    08: 281, 625		09: 281, 720		10:	321, 720
	//	11: 321, 724		12:	280, 724		13: 280, 627		14: 81, 627		    15:	81, 461
	//	16: 479, 461		17: 479, 674		18:	371, 674		19:	371, 724		20: 332, 724	
			
	//X Coordinates
		double point01X = ratioW*332,  point02X = ratioW*367,  point03X = ratioW*367,  point04X = ratioW*475,  point05X = ratioW*475,
			   point06X = ratioW*85,   point07X = ratioW*85,   point08X = ratioW*281,  point09X = ratioW*281,  point10X = ratioW*321,
			   point11X = ratioW*321,  point12X = ratioW*280,  point13X = ratioW*280,  point14X = ratioW*81,   point15X = ratioW*81,
			   point16X = ratioW*479,  point17X = ratioW*479,  point18X = ratioW*371,  point19X = ratioW*371,  point20X = ratioW*332;
		
	//Y Coordinates
		double point01Y = ratioH*720,  point02Y = ratioH*720,  point03Y = ratioH*670, point04Y = ratioH*670, point05Y = ratioH*465,
			   point06Y = ratioH*465,  point07Y = ratioH*625,  point08Y = ratioH*625, point09Y = ratioH*720, point10Y = ratioH*720,
			   point11Y = ratioH*724,  point12Y = ratioH*724,  point13Y = ratioH*627, point14Y = ratioH*627, point15Y = ratioH*461,
			   point16Y = ratioH*461,  point17Y = ratioH*674,  point18Y = ratioH*674, point19Y = ratioH*724, point20Y = ratioH*724;
		
		int xPos[] = {(int)point01X,  (int)point02X,  (int)point03X,  (int)point04X,  (int)point05X,
				      (int)point06X,  (int)point07X,  (int)point08X,  (int)point09X,  (int)point10X,
				      (int)point11X,  (int)point12X,  (int)point13X,  (int)point14X,  (int)point15X,
				      (int)point16X,  (int)point17X,  (int)point18X,  (int)point19X,  (int)point20X};
		
		int yPos[] = {(int)point01Y,  (int)point02Y,  (int)point03Y,  (int)point04Y,  (int)point05Y,
				      (int)point06Y,  (int)point07Y,  (int)point08Y,  (int)point09Y,  (int)point10Y,
				      (int)point11Y,  (int)point12Y,  (int)point13Y,  (int)point14Y,  (int)point15Y,
				      (int)point16Y,  (int)point17Y,  (int)point18Y,  (int)point19Y,  (int)point20Y};
		
		for (int i=0; i<xPos.length; i++) xPos[i]+=originX;
		for (int i=0; i<yPos.length; i++) yPos[i]+=originY;
		
		Main.mansionFenceNorth = new BuildingObjects(xPos, yPos, 20, Black, DefaultStroke);
		
//		Polygon fence = new Polygon(xPos, yPos, 20);
//		
//		g.setColor(Black);
//		g.setStroke(DefaultStroke);
//		g.fillPolygon(fence);
	}

	static void initializeMansionFenceSouth(int originX, int originY) {
		
	//POINTS LIST
	//	01:	332, 779		02: 367, 779		03: 367, 829		04: 475, 829		05: 475, 1034
	//	06:	85, 1034		07: 85, 874		    08: 281, 874		09: 281, 779		10:	321, 779
	//	11: 321, 775		12:	280, 775		13: 280, 870		14: 81, 870		    15:	81, 1038
	//	16: 479, 1038		17: 479, 825		18:	371, 825		19:	371, 775		20: 332, 775	
			
	//X Coordinates
		double point01X = ratioW*332,  point02X = ratioW*367,  point03X = ratioW*367,  point04X = ratioW*475,  point05X = ratioW*475,
			   point06X = ratioW*85,   point07X = ratioW*85,   point08X = ratioW*281,  point09X = ratioW*281,  point10X = ratioW*321,
			   point11X = ratioW*321,  point12X = ratioW*280,  point13X = ratioW*280,  point14X = ratioW*81,   point15X = ratioW*81,
			   point16X = ratioW*479,  point17X = ratioW*479,  point18X = ratioW*371,  point19X = ratioW*371,  point20X = ratioW*332;
		
	//Y Coordinates
		double point01Y = ratioH*779,  point02Y = ratioH*779,  point03Y = ratioH*829, point04Y = ratioH*829, point05Y = ratioH*1034,
			   point06Y = ratioH*1034, point07Y = ratioH*874,  point08Y = ratioH*874, point09Y = ratioH*779, point10Y = ratioH*779,
			   point11Y = ratioH*775,  point12Y = ratioH*775,  point13Y = ratioH*870, point14Y = ratioH*870, point15Y = ratioH*1038,
			   point16Y = ratioH*1038, point17Y = ratioH*825,  point18Y = ratioH*825, point19Y = ratioH*775, point20Y = ratioH*775;
		
		int xPos[] = {(int)point01X,  (int)point02X,  (int)point03X,  (int)point04X,  (int)point05X,
				      (int)point06X,  (int)point07X,  (int)point08X,  (int)point09X,  (int)point10X,
				      (int)point11X,  (int)point12X,  (int)point13X,  (int)point14X,  (int)point15X,
				      (int)point16X,  (int)point17X,  (int)point18X,  (int)point19X,  (int)point20X};
		
		int yPos[] = {(int)point01Y,  (int)point02Y,  (int)point03Y,  (int)point04Y,  (int)point05Y,
				      (int)point06Y,  (int)point07Y,  (int)point08Y,  (int)point09Y,  (int)point10Y,
				      (int)point11Y,  (int)point12Y,  (int)point13Y,  (int)point14Y,  (int)point15Y,
				      (int)point16Y,  (int)point17Y,  (int)point18Y,  (int)point19Y,  (int)point20Y};
		
		for (int i=0; i<xPos.length; i++) xPos[i]+=originX;
		for (int i=0; i<yPos.length; i++) yPos[i]+=originY;
		
		
		Main.mansionFenceSouth = new BuildingObjects(xPos, yPos, 20, Black, DefaultStroke);
		
		
//		Polygon fence = new Polygon(xPos, yPos, 20);
//		
//		g.setColor(Black);
//		g.setStroke(DefaultStroke);
//		g.fillPolygon(fence);
		
		}
	
	static void initializeMansion(int originX, int originY) {

	//POINTS LIST
	//	01:	81, 626		
	//	02: 282, 626		
	//	03: 81, 874
	//	04: 282, 874
		
		double point01X = ratioW*81, point02X = ratioW*282, point03X = ratioW*282, point04X = ratioW*81;
		int xPos[] = {(int)point01X, (int)point02X, (int)point03X, (int)point04X};
		
		double point01Y = ratioH*626, point02Y = ratioH*626, point03Y = ratioH*874, point04Y = ratioH*874;
		int yPos[] = {(int)point01Y, (int)point02Y, (int)point03Y, (int)point04Y};

		for (int i=0; i<xPos.length; i++) xPos[i]+=originX;
		for (int i=0; i<yPos.length; i++) yPos[i]+=originY;
		
		
		Main.mansion = new BuildingObjects(xPos, yPos, 4, Black, DefaultStroke);
		
		
//		Polygon building = new Polygon(xPos, yPos, 4);
//	
//		g.setColor(Black);
//		g.setStroke(DefaultStroke);
//		
//		g.fillPolygon(building);
		
	}

	static void initializeColosseumBoarder(int originX, int originY) {
		
	//POINTS LIST
	//	01: 1230, 270		02: 1230, 81		03: 531, 81			04:	531, 674		05: 1150, 674
	//	06:	1150, 635		07: 1230, 635		08: 1230, 320		09: 1181, 320 		10: 1181, 586 	
	//	11: 580, 586		12:	580, 120		13: 1181, 120		14: 1181, 270
	
	//X Coordinates
		double point01X = ratioW*1230,  point02X = ratioW*1230,  point03X = ratioW*531,   point04X = ratioW*531,   point05X = ratioW*1150,
			   point06X = ratioW*1150,  point07X = ratioW*1230,  point08X = ratioW*1230,  point09X = ratioW*1181,  point10X = ratioW*1181,
			   point11X = ratioW*580,   point12X = ratioW*580,   point13X = ratioW*1181,  point14X = ratioW*1181;
			
	//Y Coordinates
		double point01Y = ratioH*270,   point02Y = ratioH*81,  point03Y = ratioH*81,   point04Y = ratioH*674,  point05Y = ratioH*674,
			   point06Y = ratioH*635,  point07Y = ratioH*635,  point08Y = ratioH*320,  point09Y = ratioH*320,  point10Y = ratioH*586,
			   point11Y = ratioH*586,  point12Y = ratioH*120,  point13Y = ratioH*120,  point14Y = ratioH*270;
			
		int xPos[] = {(int)point01X,  (int)point02X,  (int)point03X,  (int)point04X,  (int)point05X,
					  (int)point06X,  (int)point07X,  (int)point08X,  (int)point09X,  (int)point10X,
					  (int)point11X,  (int)point12X,  (int)point13X,  (int)point14X};
			
		int yPos[] = {(int)point01Y,  (int)point02Y,  (int)point03Y,  (int)point04Y,  (int)point05Y,
					  (int)point06Y,  (int)point07Y,  (int)point08Y,  (int)point09Y,  (int)point10Y,
					  (int)point11Y,  (int)point12Y,  (int)point13Y,  (int)point14Y};
		
		for (int i=0; i<xPos.length; i++) xPos[i]+=originX;
		for (int i=0; i<yPos.length; i++) yPos[i]+=originY;
		
		Main.colosseumBoarder = new BuildingObjects(xPos, yPos, 14, Black, DefaultStroke);
		
//		
//		Polygon building = new Polygon(xPos, yPos, 14);
//		
//		g.setColor(Black);
//		g.setStroke(DefaultStroke);
//		g.fillPolygon(building);
		
	}

	static void initializeSpawnPointBoarder(int originX, int originY) {
		
	//NORTH WEST POINTS LIST
	//	01:	1146, 674		02: 1150, 674		03: 1150, 724		04: 1146, 724
			
		double NWpoint01X = ratioW*1146, NWpoint02X = ratioW*1150, NWpoint03X = ratioW*1150, NWpoint04X = ratioW*1146;
		int NWxPos[] = {(int)NWpoint01X, (int)NWpoint02X, (int)NWpoint03X, (int)NWpoint04X};
		
		double NWpoint01Y = ratioH*674,  NWpoint02Y = ratioH*674, NWpoint03Y = ratioH*724, NWpoint04Y = ratioH*724;
		int NWyPos[] = {(int)NWpoint01Y, (int)NWpoint02Y, (int)NWpoint03Y, (int)NWpoint04Y};
		
		for (int i=0; i<NWxPos.length; i++) NWxPos[i]+=originX;
		for (int i=0; i<NWyPos.length; i++) NWyPos[i]+=originY;
		Polygon NWBoarder = new Polygon(NWxPos, NWyPos, 4);	
		
//		g.setColor(Black);
//		g.setStroke(DefaultStroke);
//		g.fillPolygon(NWBoarder);
		
	//SOUTH WEST POINTS LIST
	//	01:	1146, 770		02: 1150, 770		03: 1150, 807		04: 1146, 807
			
		double SWpoint01X = ratioW*1146, SWpoint02X = ratioW*1150, SWpoint03X = ratioW*1150, SWpoint04X = ratioW*1146;
		int SWxPos[] = {(int)SWpoint01X, (int)SWpoint02X, (int)SWpoint03X, (int)SWpoint04X};
		
		double SWpoint01Y = ratioH*770,  SWpoint02Y = ratioH*770, SWpoint03Y = ratioH*807, SWpoint04Y = ratioH*807;
		int SWyPos[] = {(int)SWpoint01Y, (int)SWpoint02Y, (int)SWpoint03Y, (int)SWpoint04Y};
		
		for (int i=0; i<SWxPos.length; i++) SWxPos[i]+=originX;
		for (int i=0; i<SWyPos.length; i++) SWyPos[i]+=originY;
		Polygon SWBoarder = new Polygon(SWxPos, SWyPos, 4);	
//		g.fillPolygon(SWBoarder);
		
	//SOUTH EAST POINTS LIST
	//	01:	1355, 770		02: 1359, 770		03: 1359, 807		04: 1355, 807
			
		double SEpoint01X = ratioW*1355, SEpoint02X = ratioW*1359, SEpoint03X = ratioW*1359, SEpoint04X = ratioW*1355;
		int SExPos[] = {(int)SEpoint01X, (int)SEpoint02X, (int)SEpoint03X, (int)SEpoint04X};
		
		double SEpoint01Y = ratioH*770,  SEpoint02Y = ratioH*770, SEpoint03Y = ratioH*807, SEpoint04Y = ratioH*807;
		int SEyPos[] = {(int)SEpoint01Y, (int)SEpoint02Y, (int)SEpoint03Y, (int)SEpoint04Y};
		
		for (int i=0; i<SExPos.length; i++) SExPos[i]+=originX;
		for (int i=0; i<SEyPos.length; i++) SEyPos[i]+=originY;
		Polygon SEBoarder = new Polygon(SExPos, SEyPos, 4);	
//		g.fillPolygon(SEBoarder);
		
	//NORTH EAST POINTS LIST
	//	01:	1355, 674		02: 1359, 674		03: 1359, 724		04: 1355, 724
			
		double NEpoint01X = ratioW*1355, NEpoint02X = ratioW*1359, NEpoint03X = ratioW*1359, NEpoint04X = ratioW*1355;
		int NExPos[] = {(int)NEpoint01X, (int)NEpoint02X, (int)NEpoint03X, (int)NEpoint04X};
		
		double NEpoint01Y = ratioH*674,  NEpoint02Y = ratioH*674, NEpoint03Y = ratioH*724, NEpoint04Y = ratioH*724;
		int NEyPos[] = {(int)NEpoint01Y, (int)NEpoint02Y, (int)NEpoint03Y, (int)NEpoint04Y};

		for (int i=0; i<NExPos.length; i++) NExPos[i]+=originX;
		for (int i=0; i<NEyPos.length; i++) NEyPos[i]+=originY;
		Polygon NEBoarder = new Polygon(NExPos, NEyPos, 4);
//		g.fillPolygon(NEBoarder);
	}

	static void initializeTriangleBuilding1(int originX, int originY) {
		
//		g.setColor(Black);
//		g.setStroke(DefaultStroke);
		
	//POINT LIST
	// 01: 531, 824
	// 02: 531, 1056
	// 03: 824, 824
		
		double point1X = ratioW*531, point2X = ratioW*531, point3X = ratioW*917;
		int xPos[] = {(int)point1X, (int)point2X, (int)point3X};
		
		double point1Y = ratioH*824, point2Y = ratioH*1056, point3Y = ratioH*824;
		int yPos[] = {(int)point1Y, (int)point2Y, (int)point3Y};
		
		for (int i=0; i<xPos.length; i++) xPos[i]+=originX;
		for (int i=0; i<yPos.length; i++) yPos[i]+=originY;
		
		Main.triangleBuilding1 = new BuildingObjects(xPos, yPos, 3, Black, DefaultStroke);
		
		
//		Polygon building = new Polygon(xPos, yPos, 3);
//		
//		g.fillPolygon(building);
		
		
	}

	static void initializeMansionSideBuilding1(int originX, int originY) {
		
//		g.setColor(Black);
//		g.setStroke(DefaultStroke);
		
	//POINT LIST
	// 01: 81, 1079
	// 02: 228, 1079
	// 03: 228, 1242
	// 04: 81, 1332
	
		double point1X = ratioW*81, point2X = ratioW*228, point3X = ratioW*228, point4X = ratioW*81;
		int xPos[] = {(int)point1X, (int)point2X, (int)point3X, (int)point4X};
		
		double point1Y = ratioH*1079, point2Y = ratioH*1079, point3Y = ratioH*1242, point4Y = ratioH*1332;
		int yPos[] = {(int)point1Y, (int)point2Y, (int)point3Y, (int)point4Y};
		
		for (int i=0; i<xPos.length; i++) xPos[i]+=originX;
		for (int i=0; i<yPos.length; i++) yPos[i]+=originY;
		
		Main.mansionSideBuilding1 = new BuildingObjects(xPos, yPos, 4, Black, DefaultStroke);
		
		
//		Polygon building = new Polygon(xPos, yPos, 4);
//		
//		g.fillPolygon(building);
		
		
		
	}

	static void initializeMansionSideBuilding2(int originX, int originY) {
		
	//POINT LIST
	// 01: 279, 1079
	// 02: 480, 1079
	// 03: 480, 1090
	// 04: 279, 1211
		
		double point1X = ratioW*279, point2X = ratioW*480, point3X = ratioW*480, point4X = ratioW*279;
		int xPos[] = {(int)point1X, (int)point2X, (int)point3X, (int)point4X};
		
		double point1Y = ratioH*1079, point2Y = ratioH*1079, point3Y = ratioH*1090, point4Y = ratioH*1211;
		int yPos[] = {(int)point1Y, (int)point2Y, (int)point3Y, (int)point4Y};
		
		for (int i=0; i<xPos.length; i++) xPos[i]+=originX;
		for (int i=0; i<yPos.length; i++) yPos[i]+=originY;
		
		Main.mansionSideBuilding2 = new BuildingObjects(xPos, yPos, 4, Black, DefaultStroke);
		
//		Polygon building = new Polygon(xPos, yPos, 4);
//		
//		g.fillPolygon(building);
		
		
		
	}

	static void initializeTriangleBuilding2(int originX, int originY) {
		
//		g.setColor(Black);
//		g.setStroke(DefaultStroke);
		
	//POINT LIST
	// 01: 480, 1209
	// 02: 480, 1380
	// 03: 195, 1380
		
		double point1X = ratioW*480, point2X = ratioW*480, point3X = ratioW*195;
		int xPos[] = {(int)point1X, (int)point2X, (int)point3X};
		
		double point1Y = ratioH*1209, point2Y = ratioH*1380, point3Y = ratioH*1380;
		int yPos[] = {(int)point1Y, (int)point2Y, (int)point3Y};
		
		for (int i=0; i<xPos.length; i++) xPos[i]+=originX;
		for (int i=0; i<yPos.length; i++) yPos[i]+=originY;
		
		Main.triangleBuilding2 = new BuildingObjects(xPos, yPos, 3, Black, DefaultStroke);
		
//		Polygon building = new Polygon(xPos, yPos, 3);
//		
//		g.fillPolygon(building);
		
	}

	static void initializeHouseArea1(int originX, int originY) {
		
	//POINTS LIST
	//	01:	910, 946		02: 960, 1018		03: 816, 1081		04: 816, 1085		05: 759, 1085
	//	06:	759, 1151		07: 632, 1151		08: 632, 1242		09: 617, 1242		10:	617, 1218
	//	11: 576, 1218		12:	576, 1283		13: 617, 1283		14: 617, 1254		15:	632, 1254
	//	16: 632, 1330		17: 617, 1305		18:	576, 1305		19:	576, 1370		20: 617, 1370	
	//	21:	617, 1340		22: 666, 1340		23: 666, 1370		24: 707, 1370		25: 707, 1305	
	//	26:	666, 1305		27:	666, 1330		28: 647, 1330		29: 647, 1254		30: 666, 1254	
	//	31:	666, 1283		32: 707, 1283		33:	707, 1218		34:	666, 1218		35: 666, 1242	
	//	36:	647, 1242		37:	647, 1169		38:	759, 1169		39:	759, 1214		40:	816, 1214
	//	41:	816, 1303		42:	737, 1303		43:	737, 1354		44:	909, 1354		45:	909, 1303
	//	46:	835, 1303		47:	835, 1214		48:	888, 1214		49:	888, 1169		50:	990, 1169
	//	51:	990, 1185		52:	964, 1185		53:	964, 1253		54:	990, 1253		55:	990, 1287
	//	56:	962, 1287		57:	962, 1354		58:	1032, 1354		59: 1032, 1287		60:	1008, 1287
	//	61: 1008, 1253		62:	1034, 1253		63:	1034, 1185		64:	1008, 1185		65:	1008, 1151
	//	66:	888, 1151		67:	888, 1085		68:	835, 1085		69:	835, 1032		70:	969, 1032
	//	71:	1075, 1186		72:	1075, 1380		73:	531, 1380		74:	531, 1178	
			
	//X Coordinates
		double point01X = ratioW*910,  point02X = ratioW*960,  point03X = ratioW*816,  point04X = ratioW*816,  point05X = ratioW*759,
			   point06X = ratioW*759,  point07X = ratioW*632,  point08X = ratioW*632,  point09X = ratioW*617,  point10X = ratioW*617,
			   point11X = ratioW*576,  point12X = ratioW*576,  point13X = ratioW*617,  point14X = ratioW*617,  point15X = ratioW*632,
			   point16X = ratioW*632,  point17X = ratioW*617,  point18X = ratioW*617,  point19X = ratioW*576,  point20X = ratioW*576,
			   point21X = ratioW*617,  point22X = ratioW*617,  point23X = ratioW*666,  point24X = ratioW*666,  point25X = ratioW*707,
			   point26X = ratioW*707,  point27X = ratioW*666,  point28X = ratioW*666,  point29X = ratioW*647,  point30X = ratioW*647,
			   point31X = ratioW*666,  point32X = ratioW*666,  point33X = ratioW*707,  point34X = ratioW*707,  point35X = ratioW*666,
			   point36X = ratioW*666,  point37X = ratioW*647,  point38X = ratioW*647,  point39X = ratioW*759,  point40X = ratioW*759,
			   point41X = ratioW*816,  point42X = ratioW*816,  point43X = ratioW*737,  point44X = ratioW*737,  point45X = ratioW*909,
			   point46X = ratioW*909,  point47X = ratioW*835,  point48X = ratioW*835,  point49X = ratioW*888,  point50X = ratioW*888,
			   point51X = ratioW*990,  point52X = ratioW*990,  point53X = ratioW*964,  point54X = ratioW*964,  point55X = ratioW*990,
			   point56X = ratioW*990,  point57X = ratioW*962,  point58X = ratioW*962,  point59X = ratioW*1032, point60X = ratioW*1032,
			   point61X = ratioW*1008, point62X = ratioW*1008, point63X = ratioW*1034, point64X = ratioW*1034, point65X = ratioW*1008,
			   point66X = ratioW*1008, point67X = ratioW*888,  point68X = ratioW*888,  point69X = ratioW*835,  point70X = ratioW*835,
			   point71X = ratioW*969,  point72X = ratioW*1075, point73X = ratioW*1075, point74X = ratioW*531,  point75X = ratioW*531;
		
	//Y Coordinates
		double point01Y = ratioH*946,  point02Y = ratioH*1018, point03Y = ratioH*1018, point04Y = ratioH*1085, point05Y = ratioH*1085,
			   point06Y = ratioH*1151, point07Y = ratioH*1151, point08Y = ratioH*1242, point09Y = ratioH*1242, point10Y = ratioH*1218,
			   point11Y = ratioH*1218, point12Y = ratioH*1283, point13Y = ratioH*1283, point14Y = ratioH*1254, point15Y = ratioH*1254,
			   point16Y = ratioH*1330, point17Y = ratioH*1330, point18Y = ratioH*1305, point19Y = ratioH*1305, point20Y = ratioH*1370,
			   point21Y = ratioH*1370, point22Y = ratioH*1340, point23Y = ratioH*1340, point24Y = ratioH*1370, point25Y = ratioH*1370,
			   point26Y = ratioH*1305, point27Y = ratioH*1305, point28Y = ratioH*1330, point29Y = ratioH*1330, point30Y = ratioH*1254,
			   point31Y = ratioH*1254, point32Y = ratioH*1283, point33Y = ratioH*1283, point34Y = ratioH*1218, point35Y = ratioH*1218,
			   point36Y = ratioH*1242, point37Y = ratioH*1242, point38Y = ratioH*1169, point39Y = ratioH*1169, point40Y = ratioH*1214,
			   point41Y = ratioH*1214, point42Y = ratioH*1303, point43Y = ratioH*1303, point44Y = ratioH*1354, point45Y = ratioH*1354,
			   point46Y = ratioH*1303, point47Y = ratioH*1303, point48Y = ratioH*1214, point49Y = ratioH*1214, point50Y = ratioH*1169,
			   point51Y = ratioH*1169, point52Y = ratioH*1185, point53Y = ratioH*1185, point54Y = ratioH*1253, point55Y = ratioH*1253,
			   point56Y = ratioH*1287, point57Y = ratioH*1287, point58Y = ratioH*1354, point59Y = ratioH*1354, point60Y = ratioH*1287,
			   point61Y = ratioH*1287, point62Y = ratioH*1253, point63Y = ratioH*1253, point64Y = ratioH*1185, point65Y = ratioH*1185,
			   point66Y = ratioH*1151, point67Y = ratioH*1151, point68Y = ratioH*1085, point69Y = ratioH*1085, point70Y = ratioH*1032,
			   point71Y = ratioH*1032, point72Y = ratioH*1186, point73Y = ratioH*1380, point74Y = ratioH*1380, point75Y = ratioH*1178;
		
		int xPos[] = {(int)point01X,  (int)point02X,  (int)point03X,  (int)point04X,  (int)point05X,
				      (int)point06X,  (int)point07X,  (int)point08X,  (int)point09X,  (int)point10X,
				      (int)point11X,  (int)point12X,  (int)point13X,  (int)point14X,  (int)point15X,
				      (int)point16X,  (int)point17X,  (int)point18X,  (int)point19X,  (int)point20X,
				      (int)point21X,  (int)point22X,  (int)point23X,  (int)point24X,  (int)point25X,
				      (int)point26X,  (int)point27X,  (int)point28X,  (int)point29X,  (int)point30X,
				      (int)point31X,  (int)point32X,  (int)point33X,  (int)point34X,  (int)point35X,
				      (int)point36X,  (int)point37X,  (int)point38X,  (int)point39X,  (int)point40X,
				      (int)point41X,  (int)point42X,  (int)point43X,  (int)point44X,  (int)point45X,
				      (int)point46X,  (int)point47X,  (int)point48X,  (int)point49X,  (int)point50X,
				      (int)point51X,  (int)point52X,  (int)point53X,  (int)point54X,  (int)point55X,
				      (int)point56X,  (int)point57X,  (int)point58X,  (int)point59X,  (int)point60X,
				      (int)point61X,  (int)point62X,  (int)point63X,  (int)point64X,  (int)point65X,
				      (int)point66X,  (int)point67X,  (int)point68X,  (int)point69X,  (int)point70X,
				      (int)point71X,  (int)point72X,  (int)point73X,  (int)point74X,  (int)point75X};
		
		int yPos[] = {(int)point01Y,  (int)point02Y,  (int)point03Y,  (int)point04Y,  (int)point05Y,
				      (int)point06Y,  (int)point07Y,  (int)point08Y,  (int)point09Y,  (int)point10Y,
				      (int)point11Y,  (int)point12Y,  (int)point13Y,  (int)point14Y,  (int)point15Y,
				      (int)point16Y,  (int)point17Y,  (int)point18Y,  (int)point19Y,  (int)point20Y,
				      (int)point21Y,  (int)point22Y,  (int)point23Y,  (int)point24Y,  (int)point25Y,
				      (int)point26Y,  (int)point27Y,  (int)point28Y,  (int)point29Y,  (int)point30Y,
				      (int)point31Y,  (int)point32Y,  (int)point33Y,  (int)point34Y,  (int)point35Y,
				      (int)point36Y,  (int)point37Y,  (int)point38Y,  (int)point39Y,  (int)point40Y,
				      (int)point41Y,  (int)point42Y,  (int)point43Y,  (int)point44Y,  (int)point45Y,
				      (int)point46Y,  (int)point47Y,  (int)point48Y,  (int)point49Y,  (int)point50Y,
				      (int)point51Y,  (int)point52Y,  (int)point53Y,  (int)point54Y,  (int)point55Y,
				      (int)point56Y,  (int)point57Y,  (int)point58Y,  (int)point59Y,  (int)point60Y,
				      (int)point61Y,  (int)point62Y,  (int)point63Y,  (int)point64Y,  (int)point65Y,
				      (int)point66Y,  (int)point67Y,  (int)point68Y,  (int)point69Y,  (int)point70Y,
				      (int)point71Y,  (int)point72Y,  (int)point73Y,  (int)point74Y,  (int)point75Y};
		
		for (int i=0; i<xPos.length; i++) xPos[i]+=originX;
		for (int i=0; i<yPos.length; i++) yPos[i]+=originY;
		
		Main.houseArea1 = new BuildingObjects(xPos, yPos, 75, Black, DefaultStroke);
		
//		Polygon building = new Polygon(xPos, yPos, 75);
//		
//		g.setColor(Black);
//		g.setStroke(DefaultStroke);
//		g.fillPolygon(building);
	
	}

	static void initializeSpawnPointSideStreet(int originX, int originY) {
		
	//POINTS LIST - NORTH SECTION
	//	01:	1150, 807		02: 1150, 819		03: 1219, 819		04: 1219, 923		05: 1142, 923
	//	06:	1142, 895		07: 1068, 895		08: 1068, 972		09: 1072, 972		10:	1082, 1046
	//	11: 1030, 1084		12:	927, 935		13: 1133, 807
		
	//X Coordinates - North Section
		double Npoint01X = ratioW*1150, Npoint02X = ratioW*1150, Npoint03X = ratioW*1219, Npoint04X = ratioW*1219, Npoint05X = ratioW*1142,
			   Npoint06X = ratioW*1142, Npoint07X = ratioW*1068, Npoint08X = ratioW*1068, Npoint09X = ratioW*1082, Npoint10X = ratioW*1082,
			   Npoint11X = ratioW*1030, Npoint12X = ratioW*927,  Npoint13X = ratioW*1133;
		
	//Y Coordinates - North Section
		double Npoint01Y = ratioH*807,  Npoint02Y = ratioH*819,  Npoint03Y = ratioH*819,  Npoint04Y = ratioH*923,  Npoint05Y = ratioH*923,
			   Npoint06Y = ratioH*895,  Npoint07Y = ratioH*895,  Npoint08Y = ratioH*972,  Npoint09Y = ratioH*972,  Npoint10Y = ratioH*1046,
			   Npoint11Y = ratioH*1084, Npoint12Y = ratioH*935,  Npoint13Y = ratioH*807;		
				
		int NxPos[] = {(int)Npoint01X, (int)Npoint02X, (int)Npoint03X, (int)Npoint04X, (int)Npoint05X,
				       (int)Npoint06X, (int)Npoint07X, (int)Npoint08X, (int)Npoint09X, (int)Npoint10X,
				       (int)Npoint11X, (int)Npoint12X, (int)Npoint13X};
		
		int NyPos[] = {(int)Npoint01Y, (int)Npoint02Y, (int)Npoint03Y, (int)Npoint04Y, (int)Npoint05Y,
				       (int)Npoint06Y, (int)Npoint07Y, (int)Npoint08Y, (int)Npoint09Y, (int)Npoint10Y,
				       (int)Npoint11Y, (int)Npoint12Y, (int)Npoint13Y};
		
		for (int i=0; i<NxPos.length; i++) NxPos[i]+=originX;
		for (int i=0; i<NyPos.length; i++) NyPos[i]+=originY;
		
		
		
		Polygon NorthBuilding = new Polygon(NxPos, NyPos, 13);
		
		
		
	//POINTS LIST - SOUTH SECTION
	//	01:	1219, 940		02: 1219, 1176		03: 1093, 1176		04: 1039, 1099		05: 1093, 1061
	//	06:	1132, 1061		07: 1132, 972		08: 1142, 972		09: 1142, 940		
	
	//X Coordinates - North Section
		double Spoint01X = ratioW*1219, Spoint02X = ratioW*1219, Spoint03X = ratioW*1093, Spoint04X = ratioW*1039, Spoint05X = ratioW*1093,
			   Spoint06X = ratioW*1132, Spoint07X = ratioW*1132, Spoint08X = ratioW*1142, Spoint09X = ratioW*1142;
	
	//Y Coordinates - North Section
		double Spoint01Y = ratioH*940,  Spoint02Y = ratioH*1176, Spoint03Y = ratioH*1176, Spoint04Y = ratioH*1099, Spoint05Y = ratioH*1061,
		       Spoint06Y = ratioH*1061, Spoint07Y = ratioH*972,  Spoint08Y = ratioH*972,  Spoint09Y = ratioH*940;  	
			
		int SxPos[] = {(int)Spoint01X, (int)Spoint02X, (int)Spoint03X, (int)Spoint04X, (int)Spoint05X,
					   (int)Spoint06X, (int)Spoint07X, (int)Spoint08X, (int)Spoint09X};
	
		int SyPos[] = {(int)Spoint01Y, (int)Spoint02Y, (int)Spoint03Y, (int)Spoint04Y, (int)Spoint05Y,
				       (int)Spoint06Y, (int)Spoint07Y, (int)Spoint08Y, (int)Spoint09Y};
		
		for (int i=0; i<NxPos.length; i++) NxPos[i]+=originX;
		for (int i=0; i<NyPos.length; i++) NyPos[i]+=originY;
		Polygon SouthBuilding = new Polygon(SxPos, SyPos, 9);
			
//		g.setColor(Black);
//		g.setStroke(DefaultStroke);
//		
//		g.fillPolygon(NorthBuilding);
//		g.fillPolygon(SouthBuilding);

			
	}

	static void initializeBuildingWithBottomEntrance(int originX, int originY) {
		
	//POINTS LIST
	//	01:	1122, 1211		02: 1375, 1211		03: 1375, 1380		04: 1270, 1380		05: 1270, 1346
	//	06:	1216, 1346		07: 1216, 1380		08: 1122, 1380		
			
		double point01X = ratioW*1122, point02X = ratioW*1375, point03X = ratioW*1375, point04X = ratioW*1270, point05X = ratioW*1270,
			   point06X = ratioW*1216, point07X = ratioW*1216, point08X = ratioW*1122;
			
		double point01Y = ratioH*1211, point02Y = ratioH*1211, point03Y = ratioH*1380, point04Y = ratioH*1380, point05Y = ratioH*1346,
			   point06Y = ratioH*1346, point07Y = ratioH*1380, point08Y = ratioH*1380;	
		
		int xPos[] = {(int)point01X, (int)point02X, (int)point03X, (int)point04X, (int)point05X,
					  (int)point06X, (int)point07X, (int)point08X};
		
		int yPos[] = {(int)point01Y, (int)point02Y, (int)point03Y, (int)point04Y, (int)point05Y,
				      (int)point06Y, (int)point07Y, (int)point08Y};
		
		for (int i=0; i<xPos.length; i++) xPos[i]+=originX;
		for (int i=0; i<yPos.length; i++) yPos[i]+=originY;
		
		
		Main.buildingWithBottomEntrance = new BuildingObjects(xPos, yPos, 8, Black, DefaultStroke);
		
		
//		Polygon building = new Polygon(xPos, yPos, 8);
//		
//		g.setColor(Black);
//		g.setStroke(DefaultStroke);
//		g.fillPolygon(building);
	
	}

	static void initializeHiddenCourtyard(int originX, int originY) {
		
	//POINTS LIST
	//	01:	1533, 1351		02: 1533, 1380		03: 1422, 1380 		04: 1422, 1776		05: 1266, 1176 
	//	06:	1266, 819		07: 1355, 819		08: 1355, 798 		09: 1615, 798		10:	1615, 1380
	//	11: 1533, 1380		12:	1533, 1312		13: 1528, 1312		14: 1312, 1090		15:	1539, 1090
	//	16: 1539, 888		17: 1345, 888		18:	1345, 1090		19:	1499, 1090		20:	1499, 1312
	//	21:	1494, 1312		22: 1494, 1351	
			
	//X Coordinates
		double point01X = ratioW*1533,  point02X = ratioW*1533,  point03X = ratioW*1422,  point04X = ratioW*1422,  point05X = ratioW*1266,
			   point06X = ratioW*1266,  point07X = ratioW*1355,  point08X = ratioW*1355,  point09X = ratioW*1615,  point10X = ratioW*1615,
			   point11X = ratioW*1533,  point12X = ratioW*1533,  point13X = ratioW*1528,  point14X = ratioW*1528,  point15X = ratioW*1539,
			   point16X = ratioW*1539,  point17X = ratioW*1345,  point18X = ratioW*1345,  point19X = ratioW*1499,  point20X = ratioW*1499,
			   point21X = ratioW*1494,  point22X = ratioW*1494;
		
	//Y Coordinates
		double point01Y = ratioH*1351,  point02Y = ratioH*1380,  point03Y = ratioH*1380,  point04Y = ratioH*1176,  point05Y = ratioH*1176,
			   point06Y = ratioH*819,   point07Y = ratioH*819,   point08Y = ratioH*798,   point09Y = ratioH*798,   point10Y = ratioH*1380,
			   point11Y = ratioH*1380,  point12Y = ratioH*1312,  point13Y = ratioH*1312,  point14Y = ratioH*1090,  point15Y = ratioH*1090,
			   point16Y = ratioH*888,   point17Y = ratioH*888,   point18Y = ratioH*1090,  point19Y = ratioH*1090,  point20Y = ratioH*1312,
			   point21Y = ratioH*1312,  point22Y = ratioH*1351;
		
		int xPos[] = {(int)point01X,  (int)point02X,  (int)point03X,  (int)point04X,  (int)point05X,
				      (int)point06X,  (int)point07X,  (int)point08X,  (int)point09X,  (int)point10X,
				      (int)point11X,  (int)point12X,  (int)point13X,  (int)point14X,  (int)point15X,
				      (int)point16X,  (int)point17X,  (int)point18X,  (int)point19X,  (int)point20X,
				      (int)point21X,  (int)point22X};
		
		int yPos[] = {(int)point01Y,  (int)point02Y,  (int)point03Y,  (int)point04Y,  (int)point05Y,
				      (int)point06Y,  (int)point07Y,  (int)point08Y,  (int)point09Y,  (int)point10Y,
				      (int)point11Y,  (int)point12Y,  (int)point13Y,  (int)point14Y,  (int)point15Y,
				      (int)point16Y,  (int)point17Y,  (int)point18Y,  (int)point19Y,  (int)point20Y,
				      (int)point21Y,  (int)point22Y};
		
		for (int i=0; i<xPos.length; i++) xPos[i]+=originX;
		for (int i=0; i<yPos.length; i++) yPos[i]+=originY;
		
		
		Main.hiddenCourtyard = new BuildingObjects(xPos, yPos, 22, Black, DefaultStroke);
		
		
//		Polygon building = new Polygon(xPos, yPos, 22);
//		
//		g.setColor(Black);
//		g.setStroke(DefaultStroke);
//		g.fillPolygon(building);
	
	}

	static void initializeHiddenCourtyardInterior(int originX, int originY) {
		
	//POINTS LIST
	//	01:	1485, 937		02: 1485, 1025		03: 1403, 1025		04: 1403, 937
			
		double point01X = ratioW*1485, point02X = ratioW*1485, point03X = ratioW*1403, point04X = ratioW*1403;
		int xPos[] = {(int)point01X, (int)point02X, (int)point03X, (int)point04X};
		
		double point01Y = ratioH*937,  point02Y = ratioH*1025, point03Y = ratioH*1025, point04Y = ratioH*937;
		int yPos[] = {(int)point01Y, (int)point02Y, (int)point03Y, (int)point04Y};
		
		for (int i=0; i<xPos.length; i++) xPos[i]+=originX;
		for (int i=0; i<yPos.length; i++) yPos[i]+=originY;
		
		
		Main.hiddenCourtyardInterior = new BuildingObjects(xPos, yPos, 4, Black, DefaultStroke);
		
		
//		Polygon building = new Polygon(xPos, yPos, 4);	
//		
//		g.setColor(Black);
//		g.setStroke(DefaultStroke);
//		g.fillPolygon(building);
			
	}

	static void initializeHouseArea2(int originX, int originY) {
		
	//POINTS LIST
	//	01:	2004, 798		02: 2004, 1065		03: 1884, 1065		04: 1884, 1042		05: 1859, 1042
	//	06:	1859, 1020		07: 1934, 1020		08: 1934, 1034		09: 1984, 1034		10:	1984, 983
	//	11: 1934, 983		12:	1934, 994		13: 1860, 994		14: 1860, 953		15:	1934, 953
	//	16: 1934, 967		17: 1983, 967		18:	1983, 916		19:	1934, 916		20: 1934, 928
	//	21:	1859, 928		22: 1859, 873		23: 1934, 873		24: 1934, 886		25: 1984, 886
	//	26:	1984, 834		27:	1934, 834		28: 1934, 849		29: 1859, 849		30: 1859, 837
	//	31:	1832, 837		32: 1832, 934		33:	1792, 934		34:	1792, 847		35: 1717, 847	
	//	36:	1717, 1022		37:	1792, 1022		38:	1792, 948		39:	1832, 948		40:	1832, 1042
	//	41:	1807, 1042		42:	1807, 1065		43:	1690, 1065		44:	1690, 798
			
	//X Coordinates
		double point01X = ratioW*2004, point02X = ratioW*2004, point03X = ratioW*1884, point04X = ratioW*1884, point05X = ratioW*1859,
			   point06X = ratioW*1859, point07X = ratioW*1934, point08X = ratioW*1934, point09X = ratioW*1984, point10X = ratioW*1984,
			   point11X = ratioW*1934, point12X = ratioW*1934, point13X = ratioW*1860, point14X = ratioW*1860, point15X = ratioW*1934,
			   point16X = ratioW*1934, point17X = ratioW*1983, point18X = ratioW*1983, point19X = ratioW*1934, point20X = ratioW*1934,
			   point21X = ratioW*1859, point22X = ratioW*1859, point23X = ratioW*1934, point24X = ratioW*1934, point25X = ratioW*1984,
			   point26X = ratioW*1984, point27X = ratioW*1934, point28X = ratioW*1934, point29X = ratioW*1859, point30X = ratioW*1859,
			   point31X = ratioW*1832, point32X = ratioW*1832, point33X = ratioW*1792, point34X = ratioW*1792, point35X = ratioW*1717,
			   point36X = ratioW*1717, point37X = ratioW*1792, point38X = ratioW*1792, point39X = ratioW*1832, point40X = ratioW*1832,
			   point41X = ratioW*1807, point42X = ratioW*1807, point43X = ratioW*1690, point44X = ratioW*1690;
		
	//Y Coordinates
		double point01Y = ratioH*798,  point02Y = ratioH*1065, point03Y = ratioH*1065, point04Y = ratioH*1042, point05Y = ratioH*1042,
			   point06Y = ratioH*1020, point07Y = ratioH*1020, point08Y = ratioH*1034, point09Y = ratioH*1034, point10Y = ratioH*983,
			   point11Y = ratioH*983,  point12Y = ratioH*994,  point13Y = ratioH*994,  point14Y = ratioH*953,  point15Y = ratioH*953,
			   point16Y = ratioH*967,  point17Y = ratioH*967,  point18Y = ratioH*916,  point19Y = ratioH*916,  point20Y = ratioH*928,
			   point21Y = ratioH*928,  point22Y = ratioH*873,  point23Y = ratioH*873,  point24Y = ratioH*886,  point25Y = ratioH*886,
			   point26Y = ratioH*834,  point27Y = ratioH*834,  point28Y = ratioH*849,  point29Y = ratioH*849,  point30Y = ratioH*837,
			   point31Y = ratioH*837,  point32Y = ratioH*934,  point33Y = ratioH*934,  point34Y = ratioH*847,  point35Y = ratioH*847,
			   point36Y = ratioH*1022, point37Y = ratioH*1022, point38Y = ratioH*948,  point39Y = ratioH*948,  point40Y = ratioH*1042,
			   point41Y = ratioH*1042, point42Y = ratioH*1065, point43Y = ratioH*1065, point44Y = ratioH*798;
		
		int xPos[] = {(int)point01X,  (int)point02X,  (int)point03X,  (int)point04X,  (int)point05X,
					  (int)point06X,  (int)point07X,  (int)point08X,  (int)point09X,  (int)point10X,
					  (int)point11X,  (int)point12X,  (int)point13X,  (int)point14X,  (int)point15X,
					  (int)point16X,  (int)point17X,  (int)point18X,  (int)point19X,  (int)point20X,
					  (int)point21X,  (int)point22X,  (int)point23X,  (int)point24X,  (int)point25X,
					  (int)point26X,  (int)point27X,  (int)point28X,  (int)point29X,  (int)point30X,
					  (int)point31X,  (int)point32X,  (int)point33X,  (int)point34X,  (int)point35X,
					  (int)point36X,  (int)point37X,  (int)point38X,  (int)point39X,  (int)point40X,
					  (int)point41X,  (int)point42X,  (int)point43X,  (int)point44X};
		
		int yPos[] = {(int)point01Y,  (int)point02Y,  (int)point03Y,  (int)point04Y,  (int)point05Y,
					  (int)point06Y,  (int)point07Y,  (int)point08Y,  (int)point09Y,  (int)point10Y,
					  (int)point11Y,  (int)point12Y,  (int)point13Y,  (int)point14Y,  (int)point15Y,
					  (int)point16Y,  (int)point17Y,  (int)point18Y,  (int)point19Y,  (int)point20Y,
					  (int)point21Y,  (int)point22Y,  (int)point23Y,  (int)point24Y,  (int)point25Y,
					  (int)point26Y,  (int)point27Y,  (int)point28Y,  (int)point29Y,  (int)point30Y,
					  (int)point31Y,  (int)point32Y,  (int)point33Y,  (int)point34Y,  (int)point35Y,
					  (int)point36Y,  (int)point37Y,  (int)point38Y,  (int)point39Y,  (int)point40Y,
					  (int)point41Y,  (int)point42Y,  (int)point43Y,  (int)point44Y};
		
		for (int i=0; i<xPos.length; i++) xPos[i]+=originX;
		for (int i=0; i<yPos.length; i++) yPos[i]+=originY;
		
		
		Main.houseArea2 = new BuildingObjects(xPos, yPos, 44, Black, DefaultStroke);
		
		
//		Polygon building = new Polygon(xPos, yPos, 44);
//		
//		g.setColor(Black);
//		g.setStroke(DefaultStroke);
//		g.fillPolygon(building);
		
	}

	static void initializeHouseArea3(int originX, int originY) {
		
	//OUTSIDE POINTS LIST
	//	01:	2004, 1098		02: 2004, 1354		03: 1690, 1354		04: 1690, 1098		05: 1807, 1098
	//	06:	1807, 1127		07: 1832, 1127		08: 1832, 1150		09: 1713, 1150		10:	1713, 1200
	//	11: 1706, 1200		12:	1706, 1243		13: 1713, 1243		14: 1713, 1311		15:	1831, 1311
	//	16: 1831, 1324		17: 1859, 1324		18:	1859, 1308		19:	1974, 1308		20: 1974, 1267
	//	21:	1945, 1267		22: 1945, 1233		23: 1966, 1233		24: 1966, 1161		25: 1897, 1161
	//	26:	1897, 1233		27:	1922, 1233		28: 1922, 1267		29: 1860, 1267		30: 1860, 1127
	//	31:	1884, 1127		32: 1884, 1098
			
	//X Coordinates
		double Opoint01X = ratioW*2004, Opoint02X = ratioW*2004, Opoint03X = ratioW*1690, Opoint04X = ratioW*1690, Opoint05X = ratioW*1807,
			   Opoint06X = ratioW*1807, Opoint07X = ratioW*1832, Opoint08X = ratioW*1832, Opoint09X = ratioW*1713, Opoint10X = ratioW*1713,
			   Opoint11X = ratioW*1706, Opoint12X = ratioW*1706, Opoint13X = ratioW*1713, Opoint14X = ratioW*1713, Opoint15X = ratioW*1831,
			   Opoint16X = ratioW*1831, Opoint17X = ratioW*1859, Opoint18X = ratioW*1859, Opoint19X = ratioW*1974, Opoint20X = ratioW*1974,
			   Opoint21X = ratioW*1945, Opoint22X = ratioW*1945, Opoint23X = ratioW*1966, Opoint24X = ratioW*1966, Opoint25X = ratioW*1897,
			   Opoint26X = ratioW*1897, Opoint27X = ratioW*1922, Opoint28X = ratioW*1922, Opoint29X = ratioW*1860, Opoint30X = ratioW*1860,
			   Opoint31X = ratioW*1884, Opoint32X = ratioW*1884;
		
	//Y Coordinates
		double Opoint01Y = ratioH*1098, Opoint02Y = ratioH*1354, Opoint03Y = ratioH*1354, Opoint04Y = ratioH*1098, Opoint05Y = ratioH*1098,
			   Opoint06Y = ratioH*1127, Opoint07Y = ratioH*1127, Opoint08Y = ratioH*1150, Opoint09Y = ratioH*1150, Opoint10Y = ratioH*1200,
			   Opoint11Y = ratioH*1200, Opoint12Y = ratioH*1243, Opoint13Y = ratioH*1243, Opoint14Y = ratioH*1311, Opoint15Y = ratioH*1311,
			   Opoint16Y = ratioH*1324, Opoint17Y = ratioH*1324, Opoint18Y = ratioH*1308, Opoint19Y = ratioH*1308, Opoint20Y = ratioH*1267,
			   Opoint21Y = ratioH*1267, Opoint22Y = ratioH*1233, Opoint23Y = ratioH*1233, Opoint24Y = ratioH*1161, Opoint25Y = ratioH*1161,
			   Opoint26Y = ratioH*1233, Opoint27Y = ratioH*1233, Opoint28Y = ratioH*1267, Opoint29Y = ratioH*1267, Opoint30Y = ratioH*1127,
			   Opoint31Y = ratioH*1127, Opoint32Y = ratioH*1098;
		
		int OxPos[] = {(int)Opoint01X,  (int)Opoint02X,  (int)Opoint03X,  (int)Opoint04X,  (int)Opoint05X,
				       (int)Opoint06X,  (int)Opoint07X,  (int)Opoint08X,  (int)Opoint09X,  (int)Opoint10X,
				       (int)Opoint11X,  (int)Opoint12X,  (int)Opoint13X,  (int)Opoint14X,  (int)Opoint15X,
				       (int)Opoint16X,  (int)Opoint17X,  (int)Opoint18X,  (int)Opoint19X,  (int)Opoint20X,
				       (int)Opoint21X,  (int)Opoint22X,  (int)Opoint23X,  (int)Opoint24X,  (int)Opoint25X,
				       (int)Opoint26X,  (int)Opoint27X,  (int)Opoint28X,  (int)Opoint29X,  (int)Opoint30X,
				       (int)Opoint31X,  (int)Opoint32X};
		
		int OyPos[] = {(int)Opoint01Y,  (int)Opoint02Y,  (int)Opoint03Y,  (int)Opoint04Y,  (int)Opoint05Y,
				       (int)Opoint06Y,  (int)Opoint07Y,  (int)Opoint08Y,  (int)Opoint09Y,  (int)Opoint10Y,
				       (int)Opoint11Y,  (int)Opoint12Y,  (int)Opoint13Y,  (int)Opoint14Y,  (int)Opoint15Y,
				       (int)Opoint16Y,  (int)Opoint17Y,  (int)Opoint18Y,  (int)Opoint19Y,  (int)Opoint20Y,
				       (int)Opoint21Y,  (int)Opoint22Y,  (int)Opoint23Y,  (int)Opoint24Y,  (int)Opoint25Y,
				       (int)Opoint26Y,  (int)Opoint27Y,  (int)Opoint28Y,  (int)Opoint29Y,  (int)Opoint30Y,
				       (int)Opoint31Y,  (int)Opoint32Y};
		
		for (int i=0; i<OxPos.length; i++) OxPos[i]+=originX;
		for (int i=0; i<OyPos.length; i++) OyPos[i]+=originY;
		Polygon building = new Polygon(OxPos, OyPos, 32);

	
	//INSIDE POINTS LIST
	//	01:	1832, 1162		02: 1832, 1215		03: 1794, 1215		04: 1794, 1210		05: 1769, 1210
	//	06:	1769, 1236		07: 1793, 1236		08: 1793, 1232		09: 1832, 1232		10:	1832, 1299
	//	11: 1732, 1299		12:	1732, 1243		13: 1748, 1243		14: 1748, 1200		15:	1732, 1200
	//	16: 1732, 1162
			
	//X Coordinates
		double Ipoint01X = ratioW*1832, Ipoint02X = ratioW*1832, Ipoint03X = ratioW*1794, Ipoint04X = ratioW*1794, Ipoint05X = ratioW*1769,
			   Ipoint06X = ratioW*1769, Ipoint07X = ratioW*1793, Ipoint08X = ratioW*1793, Ipoint09X = ratioW*1832, Ipoint10X = ratioW*1832,
			   Ipoint11X = ratioW*1732, Ipoint12X = ratioW*1732, Ipoint13X = ratioW*1748, Ipoint14X = ratioW*1748, Ipoint15X = ratioW*1732,
			   Ipoint16X = ratioW*1732;
		
	//Y Coordinates
		double Ipoint01Y = ratioH*1162, Ipoint02Y = ratioH*1215, Ipoint03Y = ratioH*1215, Ipoint04Y = ratioH*1210, Ipoint05Y = ratioH*1210,
			   Ipoint06Y = ratioH*1236, Ipoint07Y = ratioH*1236, Ipoint08Y = ratioH*1232, Ipoint09Y = ratioH*1232, Ipoint10Y = ratioH*1299,
			   Ipoint11Y = ratioH*1299, Ipoint12Y = ratioH*1243, Ipoint13Y = ratioH*1243, Ipoint14Y = ratioH*1200, Ipoint15Y = ratioH*1200,
			   Ipoint16Y = ratioH*1162;
		
		int IxPos[] = {(int)Ipoint01X,  (int)Ipoint02X,  (int)Ipoint03X,  (int)Ipoint04X,  (int)Ipoint05X,
				       (int)Ipoint06X,  (int)Ipoint07X,  (int)Ipoint08X,  (int)Ipoint09X,  (int)Ipoint10X,
				       (int)Ipoint11X,  (int)Ipoint12X,  (int)Ipoint13X,  (int)Ipoint14X,  (int)Ipoint15X,
				       (int)Ipoint16X};
		
		int IyPos[] = {(int)Ipoint01Y,  (int)Ipoint02Y,  (int)Ipoint03Y,  (int)Ipoint04Y,  (int)Ipoint05Y,
					   (int)Ipoint06Y,  (int)Ipoint07Y,  (int)Ipoint08Y,  (int)Ipoint09Y,  (int)Ipoint10Y,
					   (int)Ipoint11Y,  (int)Ipoint12Y,  (int)Ipoint13Y,  (int)Ipoint14Y,  (int)Ipoint15Y,
					   (int)Ipoint16Y};
		
		for (int i=0; i<IxPos.length; i++) IxPos[i]+=originX;
		for (int i=0; i<IyPos.length; i++) IyPos[i]+=originY;
		Polygon interiorBuilding = new Polygon(IxPos, IyPos, 16);
		
//	g.setColor(Black);
//	g.setStroke(DefaultStroke);
//	g.fillPolygon(building);
//	g.fillPolygon(interiorBuilding);
		
	}

	static void initializeSouthEastWall(int originX, int originY) {
		
	//POINTS LIST
	//	01:	2111, 1023		
	//	02: 2111, 1354		
	//	03: 2068, 1354		
	//	04: 2068, 1023
			
		double point01X = ratioW*2111, point02X = ratioW*2111, point03X = ratioW*2068, point04X = ratioW*2068;
		int xPos[] = {(int)point01X, (int)point02X, (int)point03X, (int)point04X};
		
		double point01Y = ratioH*1023,  point02Y = ratioH*1354, point03Y = ratioH*1354, point04Y = ratioH*1023;
		int yPos[] = {(int)point01Y, (int)point02Y, (int)point03Y, (int)point04Y};
		
		for (int i=0; i<xPos.length; i++) xPos[i]+=originX;
		for (int i=0; i<yPos.length; i++) yPos[i]+=originY;
		
		Main.southEastWall = new BuildingObjects(xPos, yPos, 4, Black, DefaultStroke);
		
		
//		Polygon building = new Polygon(xPos, yPos, 4);	
//		
//		g.setColor(Black);
//		g.setStroke(DefaultStroke);
//		g.fillPolygon(building);
		
	}

	static void initializeSouthEastNorthBuilding(int originX, int originY) {
		
	//POINTS LIST
	//	01:	2377, 1023		02: 2377, 1156		03: 2323, 1156		04: 2323, 1111 		05:2184, 1111		
	//	06: 2184, 1156		07: 2153, 1156		08: 2153, 1023
			
		double point01X = ratioW*2377, point02X = ratioW*2377, point03X = ratioW*2323, point04X = ratioW*2323, point05X = ratioW*2184, 
			   point06X = ratioW*2184, point07X = ratioW*2153, point08X = ratioW*2153;
		
		double point01Y = ratioH*1023,  point02Y = ratioH*1156, point03Y = ratioH*1156, point04Y = ratioH*1111, point05Y = ratioH*1111,  
			   point06Y = ratioH*1156,  point07Y = ratioH*1156, point08Y = ratioH*1023;
		
		int xPos[] = {(int)point01X, (int)point02X, (int)point03X, (int)point04X, (int)point05X, 
					  (int)point06X, (int)point07X, (int)point08X};
		
		int yPos[] = {(int)point01Y, (int)point02Y, (int)point03Y, (int)point04Y, (int)point05Y, 
				      (int)point06Y, (int)point07Y, (int)point08Y};
		
		for (int i=0; i<xPos.length; i++) xPos[i]+=originX;
		for (int i=0; i<yPos.length; i++) yPos[i]+=originY;
		
		Main.southEastNorthBuilding = new BuildingObjects(xPos, yPos, 8, Black, DefaultStroke);
		
		
//		
//		Polygon building = new Polygon(xPos, yPos, 8);	
//		
//		g.setColor(Black);
//		g.setStroke(DefaultStroke);
//		g.fillPolygon(building);
			
	}

	static void initializeSouthEastSouthBuilding(int originX, int originY) {
		
	//POINTS LIST
	//	01:	2377, 1198		02: 2377, 1354		03: 2296, 1354		04: 2296, 1315 		05:2215, 1315		
	//	06: 2215, 1354		07: 2153, 1354		08: 2153, 1198		09: 2184, 1198		10: 2184, 1252
	//	11: 2322, 1252		12: 2322, 1198
			
		double point01X = ratioW*2377, point02X = ratioW*2377, point03X = ratioW*2296, point04X = ratioW*2296, point05X = ratioW*2215, 
			   point06X = ratioW*2215, point07X = ratioW*2153, point08X = ratioW*2153, point09X = ratioW*2184, point10X = ratioW*2184,
			   point11X = ratioW*2322, point12X = ratioW*2322;
		
		double point01Y = ratioH*1198,  point02Y = ratioH*1354, point03Y = ratioH*1354, point04Y = ratioH*1315, point05Y = ratioH*1315,  
			   point06Y = ratioH*1354,  point07Y = ratioH*1354, point08Y = ratioH*1198, point09Y = ratioH*1198, point10Y = ratioH*1252,
		       point11Y = ratioH*1252,  point12Y = ratioH*1198;
		
		int xPos[] = {(int)point01X, (int)point02X, (int)point03X, (int)point04X, (int)point05X, 
				      (int)point06X, (int)point07X, (int)point08X, (int)point09X, (int)point10X,
				      (int)point11X, (int)point12X};
		
		int yPos[] = {(int)point01Y, (int)point02Y, (int)point03Y, (int)point04Y, (int)point05Y, 
				      (int)point06Y, (int)point07Y, (int)point08Y, (int)point09Y, (int)point10Y,
				      (int)point11Y, (int)point12Y};
		
		for (int i=0; i<xPos.length; i++) xPos[i]+=originX;
		for (int i=0; i<yPos.length; i++) yPos[i]+=originY;
		
		
		Main.southEastSouthBuilding = new BuildingObjects(xPos, yPos, 12, Black, DefaultStroke);
		
		
		
//		Polygon building = new Polygon(xPos, yPos, 12);	
//		
//		g.setColor(Black);
//		g.setStroke(DefaultStroke);
//		g.fillPolygon(building);
		
	}
	
	static void initializeSouthEastExtendedBoarder(int originX, int originY) {
		
	//POINTS LIST
	//	01:	1690, 1400		02: 2432, 1400		03: 2432, 1198		04: 2450, 1198 		05: 2450, 1156		
	//	06: 2432, 1156		07: 2432, 974		08: 2068, 974		09: 2068, 925		10: 2297, 925
	//	11: 2297, 764		12: 2315, 764		13: 2315, 733		14: 2433, 733		15: 2433, 587		
	//  16: 2450, 587		17: 2450, 381		18: 2389, 381		19:	2389, 349		20: 2500, 349	
	//	21:	2500, 1500		22: 1690, 1500
			
		double point01X = ratioW*1690,  point02X = ratioW*2432, point03X = ratioW*2432,  point04X = ratioW*2450,  point05X = ratioW*2450, 
			   point06X = ratioW*2432,  point07X = ratioW*2432, point08X = ratioW*2068,  point09X = ratioW*2068,  point10X = ratioW*2297,
			   point11X = ratioW*2297,  point12X = ratioW*2315, point13X = ratioW*2315,  point14X = ratioW*2433,  point15X = ratioW*2433,  
			   point16X = ratioW*2450,  point17X = ratioW*2450, point18X = ratioW*2389,  point19X = ratioW*2389,  point20X = ratioW*2500,
			   point21X = ratioW*2500,  point22X = ratioW*1690;
		
		double point01Y = ratioH*1400,  point02Y = ratioH*1400, point03Y = ratioH*1198,  point04Y = ratioH*1198,  point05Y = ratioH*1156,  
			   point06Y = ratioH*1156,  point07Y = ratioH*974,  point08Y = ratioH*974,   point09Y = ratioH*925,   point10Y = ratioH*925,
			   point11Y = ratioH*764,   point12Y = ratioH*764,  point13Y = ratioH*733,   point14Y = ratioH*733,   point15Y = ratioH*587,  
			   point16Y = ratioH*587,   point17Y = ratioH*381,  point18Y = ratioH*381,   point19Y = ratioH*349,   point20Y = ratioH*349,
			   point21Y = ratioH*1500,  point22Y = ratioH*1500;
		
		int xPos[] = {(int)point01X, (int)point02X, (int)point03X, (int)point04X, (int)point05X, 
					  (int)point06X, (int)point07X, (int)point08X, (int)point09X, (int)point10X,
					  (int)point11X, (int)point12X, (int)point13X, (int)point14X, (int)point15X, 
					  (int)point16X, (int)point17X, (int)point18X, (int)point19X, (int)point20X,
				      (int)point21X, (int)point22X};
		
		int yPos[] = {(int)point01Y, (int)point02Y, (int)point03Y, (int)point04Y, (int)point05Y, 
					  (int)point06Y, (int)point07Y, (int)point08Y, (int)point09Y, (int)point10Y,
					  (int)point11Y, (int)point12Y, (int)point13Y, (int)point14Y, (int)point15Y, 
					  (int)point16Y, (int)point17Y, (int)point18Y, (int)point19Y, (int)point20Y,
				      (int)point21Y, (int)point22Y};
		
		for (int i=0; i<xPos.length; i++) xPos[i]+=originX;
		for (int i=0; i<yPos.length; i++) yPos[i]+=originY;
		
		Main.southEastExtendedBoarder = new BuildingObjects(xPos, yPos, 22, Black, DefaultStroke);
		
		
		
//		Polygon building = new Polygon(xPos, yPos, 22);	
		
//		g.setColor(Black);
//		g.setStroke(DefaultStroke);
//		g.fillPolygon(building);
		
	}
	
	static void initializeColosseum(int originX, int originY) {
		
//		g.setStroke(ColosseumStroke);
//		g.setColor(Black);
		
	//Arc Variables for All
		double arcX = ratioW*656+originX, 
			   arcY = ratioH*164+originY,
			   arcWidth = ratioW*350,
			   arcHeight = ratioH*350;
		
	//Arc 1 Variables
		int arc1startAngle = 60;
		int arc1arcAngle = 240;
	
	//Arc 2 Variables
		int arc2startAngle = 15;
		int arc2arcAngle = 12;
		
	//Arc 3 Variables
		int arc3startAngle = -15;
		int arc3arcAngle = -12;
		
//		g.drawArc((int)arcX, (int)arcY, (int)arcWidth, (int)arcHeight, arc1startAngle, arc1arcAngle);
//		g.drawArc((int)arcX, (int)arcY, (int)arcWidth, (int)arcHeight, arc2startAngle, arc2arcAngle);
//		g.drawArc((int)arcX, (int)arcY, (int)arcWidth, (int)arcHeight, arc3startAngle, arc3arcAngle);
		
	}
	
	static void initializeTownSquareWEST(int originX, int originY) {
		
	//POINTS LIST
	//	01:	1855, 639		02: 1855, 675		03: 1355, 675		04: 1355, 638		05: 1288, 638
	//	06:	1288, 530		07: 1619, 530		08: 1619, 562		09: 1615, 562		10:	1615, 640
	//	11: 1690, 640		12:	1690, 562		13: 1644, 562		14: 1644, 452		15:	1668, 452
	//	16: 1668, 375		17: 1594, 375		18:	1594, 451		19:	1619, 451		20: 1619, 491	
	//	21:	1288, 491		22: 1288, 328		23: 1467, 328		24: 1467, 408		25: 1428, 408	
	//	26:	1428, 464		27:	1548, 464		28: 1548, 408		29: 1503, 408		30: 1503, 328	
	//	31:	1511, 328		32: 1511, 272		33:	1477, 272		34:	1477, 195		35: 1497, 195	
	//	36:	1497, 123		37:	1415, 123		38:	1415, 195		39:	1438, 195		40:	1438, 272
	//	41:	1288, 272		42:	1288, 82		43:	1645, 82		44:	1645, 126		45:	1537, 232
	//	46:	1645, 346		47:	1691, 311		48:	1731, 350		49:	1731, 639	
			
	//X Coordinates
		double point01X = ratioW*1855,  point02X = ratioW*1855,  point03X = ratioW*1355,  point04X = ratioW*1355,  point05X = ratioW*1288,
			   point06X = ratioW*1288,  point07X = ratioW*1619,  point08X = ratioW*1619,  point09X = ratioW*1615,  point10X = ratioW*1615,
			   point11X = ratioW*1690,  point12X = ratioW*1690,  point13X = ratioW*1644,  point14X = ratioW*1644,  point15X = ratioW*1668,
			   point16X = ratioW*1668,  point17X = ratioW*1594,  point18X = ratioW*1594,  point19X = ratioW*1619,  point20X = ratioW*1619,
			   point21X = ratioW*1288,  point22X = ratioW*1288,  point23X = ratioW*1467,  point24X = ratioW*1467,  point25X = ratioW*1428,
			   point26X = ratioW*1428,  point27X = ratioW*1548,  point28X = ratioW*1548,  point29X = ratioW*1503,  point30X = ratioW*1503,
			   point31X = ratioW*1511,  point32X = ratioW*1511,  point33X = ratioW*1477,  point34X = ratioW*1477,  point35X = ratioW*1497,
			   point36X = ratioW*1497,  point37X = ratioW*1415,  point38X = ratioW*1415,  point39X = ratioW*1438,  point40X = ratioW*1438,
			   point41X = ratioW*1288,  point42X = ratioW*1288,  point43X = ratioW*1645,  point44X = ratioW*1645,  point45X = ratioW*1537,
			   point46X = ratioW*1645,  point47X = ratioW*1691,  point48X = ratioW*1731,  point49X = ratioW*1731;
		
	//Y Coordinates
		double point01Y = ratioH*639,   point02Y = ratioH*675,   point03Y = ratioH*675,   point04Y = ratioH*638, point05Y = ratioH*638,
			   point06Y = ratioH*530,   point07Y = ratioH*530,   point08Y = ratioH*562,   point09Y = ratioH*562, point10Y = ratioH*640,
			   point11Y = ratioH*640,   point12Y = ratioH*562,   point13Y = ratioH*562,   point14Y = ratioH*452, point15Y = ratioH*452,
			   point16Y = ratioH*375,   point17Y = ratioH*375,   point18Y = ratioH*451,   point19Y = ratioH*451, point20Y = ratioH*491,
			   point21Y = ratioH*491,   point22Y = ratioH*328,   point23Y = ratioH*328,   point24Y = ratioH*408, point25Y = ratioH*408,
			   point26Y = ratioH*464,   point27Y = ratioH*464,   point28Y = ratioH*408,   point29Y = ratioH*408, point30Y = ratioH*328,
			   point31Y = ratioH*328,   point32Y = ratioH*272,   point33Y = ratioH*272,   point34Y = ratioH*195, point35Y = ratioH*195,
			   point36Y = ratioH*123,   point37Y = ratioH*123,   point38Y = ratioH*195,   point39Y = ratioH*195, point40Y = ratioH*272,
			   point41Y = ratioH*272,   point42Y = ratioH*82,    point43Y = ratioH*82,    point44Y = ratioH*126, point45Y = ratioH*232,
			   point46Y = ratioH*346,   point47Y = ratioH*311,   point48Y = ratioH*350,   point49Y = ratioH*639;
		
		
		int xPos[] = {(int)point01X,  (int)point02X,  (int)point03X,  (int)point04X,  (int)point05X,
				      (int)point06X,  (int)point07X,  (int)point08X,  (int)point09X,  (int)point10X,
				      (int)point11X,  (int)point12X,  (int)point13X,  (int)point14X,  (int)point15X,
				      (int)point16X,  (int)point17X,  (int)point18X,  (int)point19X,  (int)point20X,
				      (int)point21X,  (int)point22X,  (int)point23X,  (int)point24X,  (int)point25X,
				      (int)point26X,  (int)point27X,  (int)point28X,  (int)point29X,  (int)point30X,
				      (int)point31X,  (int)point32X,  (int)point33X,  (int)point34X,  (int)point35X,
				      (int)point36X,  (int)point37X,  (int)point38X,  (int)point39X,  (int)point40X,
				      (int)point41X,  (int)point42X,  (int)point43X,  (int)point44X,  (int)point45X,
				      (int)point46X,  (int)point47X,  (int)point48X,  (int)point49X};
		
		int yPos[] = {(int)point01Y,  (int)point02Y,  (int)point03Y,  (int)point04Y,  (int)point05Y,
				      (int)point06Y,  (int)point07Y,  (int)point08Y,  (int)point09Y,  (int)point10Y,
				      (int)point11Y,  (int)point12Y,  (int)point13Y,  (int)point14Y,  (int)point15Y,
				      (int)point16Y,  (int)point17Y,  (int)point18Y,  (int)point19Y,  (int)point20Y,
				      (int)point21Y,  (int)point22Y,  (int)point23Y,  (int)point24Y,  (int)point25Y,
				      (int)point26Y,  (int)point27Y,  (int)point28Y,  (int)point29Y,  (int)point30Y,
				      (int)point31Y,  (int)point32Y,  (int)point33Y,  (int)point34Y,  (int)point35Y,
				      (int)point36Y,  (int)point37Y,  (int)point38Y,  (int)point39Y,  (int)point40Y,
				      (int)point41Y,  (int)point42Y,  (int)point43Y,  (int)point44Y,  (int)point45Y,
				      (int)point46Y,  (int)point47Y,  (int)point48Y,  (int)point49Y};
		
		for (int i=0; i<xPos.length; i++) xPos[i]+=originX;
		for (int i=0; i<yPos.length; i++) yPos[i]+=originY;
		
		
		Main.townSquareWEST = new BuildingObjects(xPos, yPos, 49, Black, DefaultStroke);
		
		
		
//		Polygon building = new Polygon(xPos, yPos, 49);
//		
//		g.setColor(Black);
//		g.setStroke(DefaultStroke);
//		g.fillPolygon(building);
	}

	static void initializeTownSquareSOUTHEAST(int originX, int originY) {

		
		//POINTS LIST
		//	01:	2358, 515		02: 2358, 587		03: 2392, 587		04: 2392, 699		05: 2315, 699
		//	06:	2315, 673		07: 2226, 673		08: 2226, 764		09: 2262, 764		10:	2262, 873
		//	11: 2068, 873		12:	2068, 798		13: 2135, 798		14: 2135, 675		15:	1916, 675
		//	16: 1916, 639		17: 2037, 639		18:	2037, 515		19:	2152, 515		20: 2152, 563	
		//	21:	2119, 563		22: 2119, 619		23: 2224, 619		24: 2224, 563		25: 2185, 563	
		//	26:	2185, 515	
				
		//X Coordinates
			double point01X = ratioW*2358,  point02X = ratioW*2358,  point03X = ratioW*2392,  point04X = ratioW*2392,  point05X = ratioW*2315,
				   point06X = ratioW*2315,  point07X = ratioW*2226,  point08X = ratioW*2226,  point09X = ratioW*2262,  point10X = ratioW*2262,
				   point11X = ratioW*2068,  point12X = ratioW*2068,  point13X = ratioW*2135,  point14X = ratioW*2135,  point15X = ratioW*1916,
				   point16X = ratioW*1916,  point17X = ratioW*2037,  point18X = ratioW*2037,  point19X = ratioW*2152,  point20X = ratioW*2152,
				   point21X = ratioW*2119,  point22X = ratioW*2119,  point23X = ratioW*2224,  point24X = ratioW*2224,  point25X = ratioW*2185,
				   point26X = ratioW*2185;
			
		//Y Coordinates
			double point01Y = ratioH*515,   point02Y = ratioH*587,   point03Y = ratioH*587,   point04Y = ratioH*699, point05Y = ratioH*699,
				   point06Y = ratioH*673,   point07Y = ratioH*673,   point08Y = ratioH*764,   point09Y = ratioH*764, point10Y = ratioH*873,
				   point11Y = ratioH*873,   point12Y = ratioH*798,   point13Y = ratioH*798,   point14Y = ratioH*675, point15Y = ratioH*675,
				   point16Y = ratioH*639,   point17Y = ratioH*639,   point18Y = ratioH*515,   point19Y = ratioH*515, point20Y = ratioH*563,
				   point21Y = ratioH*563,   point22Y = ratioH*619,   point23Y = ratioH*619,   point24Y = ratioH*563, point25Y = ratioH*563,
				   point26Y = ratioH*515;
			
			
			int xPos[] = {(int)point01X,  (int)point02X,  (int)point03X,  (int)point04X,  (int)point05X,
					      (int)point06X,  (int)point07X,  (int)point08X,  (int)point09X,  (int)point10X,
					      (int)point11X,  (int)point12X,  (int)point13X,  (int)point14X,  (int)point15X,
					      (int)point16X,  (int)point17X,  (int)point18X,  (int)point19X,  (int)point20X,
					      (int)point21X,  (int)point22X,  (int)point23X,  (int)point24X,  (int)point25X,
					      (int)point26X};
			
			int yPos[] = {(int)point01Y,  (int)point02Y,  (int)point03Y,  (int)point04Y,  (int)point05Y,
					      (int)point06Y,  (int)point07Y,  (int)point08Y,  (int)point09Y,  (int)point10Y,
					      (int)point11Y,  (int)point12Y,  (int)point13Y,  (int)point14Y,  (int)point15Y,
					      (int)point16Y,  (int)point17Y,  (int)point18Y,  (int)point19Y,  (int)point20Y,
					      (int)point21Y,  (int)point22Y,  (int)point23Y,  (int)point24Y,  (int)point25Y,
					      (int)point26Y};
			
			for (int i=0; i<xPos.length; i++) xPos[i]+=originX;
			for (int i=0; i<yPos.length; i++) yPos[i]+=originY;
			
			
			Main.townSquareSOUTHEAST = new BuildingObjects(xPos, yPos, 26, Black, DefaultStroke);
			
			
//			
//			Polygon building = new Polygon(xPos, yPos, 26);
//			
//			g.setColor(Black);
//			g.setStroke(DefaultStroke);
//			g.fillPolygon(building);
	}

	static void initializeTownSquareNORTHEAST(int originX, int originY) {
		
	//POINTS LIST
	//	01:	2341, 50		02: 2341, 159		03: 2422, 159		04: 2422, 308		05: 2363, 308
	//	06:	2363, 381		07: 2358, 381		08: 2358, 462		09: 2037, 462		10:	2037, 333
	//	11: 1808, 333		12:	1802, 327		13: 1820, 309		14: 2053, 309		15:	2053, 364
	//	16: 2123, 364		17: 2123, 369		18:	2156, 369		19:	2156, 332		20: 2124, 332	
	//	21:	2124, 354		22: 2082, 354		23: 2082, 271		24: 1818, 271		25: 1782, 307	
	//	26:	1736, 261		27: 1775, 222		28: 1693, 140		29: 1693, 82		30: 1759, 82	
	//	31:	1759, 147		32: 1806, 147		33:	1806, 234		34:	1943, 234		35: 1943, 122	
	//	36:	2109, 122		37:	2109, 159		38:	2227, 159		39:	2227, 343		40:	2329, 343
	//	41:	2329, 250		42:	2259, 250		43:	2259, 159		44:	2277, 159		45:	2277, 50	
			 
	//X Coordinates
		double point01X = ratioW*2341,  point02X = ratioW*2341,  point03X = ratioW*2422,  point04X = ratioW*2422,  point05X = ratioW*2363,
			   point06X = ratioW*2363,  point07X = ratioW*2358,  point08X = ratioW*2358,  point09X = ratioW*2037,  point10X = ratioW*2037,
			   point11X = ratioW*1808,  point12X = ratioW*1802,  point13X = ratioW*1820,  point14X = ratioW*2053,  point15X = ratioW*2053,
			   point16X = ratioW*2123,  point17X = ratioW*2123,  point18X = ratioW*2156,  point19X = ratioW*2156,  point20X = ratioW*2124,
			   point21X = ratioW*2124,  point22X = ratioW*2082,  point23X = ratioW*2082,  point24X = ratioW*1818,  point25X = ratioW*1782,
			   point26X = ratioW*1736,  point27X = ratioW*1775,  point28X = ratioW*1693,  point29X = ratioW*1693,  point30X = ratioW*1759,
			   point31X = ratioW*1759,  point32X = ratioW*1806,  point33X = ratioW*1806,  point34X = ratioW*1943,  point35X = ratioW*1943,
			   point36X = ratioW*2109,  point37X = ratioW*2109,  point38X = ratioW*2227,  point39X = ratioW*2227,  point40X = ratioW*2329,
			   point41X = ratioW*2329,  point42X = ratioW*2259,  point43X = ratioW*2259,  point44X = ratioW*2277,  point45X = ratioW*2277;
		
	//Y Coordinates
		double point01Y = ratioH*50,    point02Y = ratioH*159,   point03Y = ratioH*159,   point04Y = ratioH*308,   point05Y = ratioH*308,
			   point06Y = ratioH*381,   point07Y = ratioH*381,   point08Y = ratioH*462,   point09Y = ratioH*462,   point10Y = ratioH*333,
			   point11Y = ratioH*333,   point12Y = ratioH*327,   point13Y = ratioH*309,   point14Y = ratioH*309,   point15Y = ratioH*364,
			   point16Y = ratioH*364,   point17Y = ratioH*369,   point18Y = ratioH*369,   point19Y = ratioH*332,   point20Y = ratioH*332,
			   point21Y = ratioH*354,   point22Y = ratioH*354,   point23Y = ratioH*271,   point24Y = ratioH*271,   point25Y = ratioH*307,
			   point26Y = ratioH*261,   point27Y = ratioH*222,   point28Y = ratioH*140,   point29Y = ratioH*82,    point30Y = ratioH*82,
			   point31Y = ratioH*147,   point32Y = ratioH*147,   point33Y = ratioH*234,   point34Y = ratioH*234,   point35Y = ratioH*122,
			   point36Y = ratioH*122,   point37Y = ratioH*159,   point38Y = ratioH*159,   point39Y = ratioH*343,   point40Y = ratioH*343,
			   point41Y = ratioH*250,   point42Y = ratioH*250,   point43Y = ratioH*159,   point44Y = ratioH*159,   point45Y = ratioH*50;
		
		int xPos[] = {(int)point01X,  (int)point02X,  (int)point03X,  (int)point04X,  (int)point05X,
				      (int)point06X,  (int)point07X,  (int)point08X,  (int)point09X,  (int)point10X,
				      (int)point11X,  (int)point12X,  (int)point13X,  (int)point14X,  (int)point15X,
				      (int)point16X,  (int)point17X,  (int)point18X,  (int)point19X,  (int)point20X,
				      (int)point21X,  (int)point22X,  (int)point23X,  (int)point24X,  (int)point25X,
				      (int)point26X,  (int)point27X,  (int)point28X,  (int)point29X,  (int)point30X,
				      (int)point31X,  (int)point32X,  (int)point33X,  (int)point34X,  (int)point35X,
				      (int)point36X,  (int)point37X,  (int)point38X,  (int)point39X,  (int)point40X,
				      (int)point41X,  (int)point42X,  (int)point43X,  (int)point44X,  (int)point45X};
		
		int yPos[] = {(int)point01Y,  (int)point02Y,  (int)point03Y,  (int)point04Y,  (int)point05Y,
				      (int)point06Y,  (int)point07Y,  (int)point08Y,  (int)point09Y,  (int)point10Y,
				      (int)point11Y,  (int)point12Y,  (int)point13Y,  (int)point14Y,  (int)point15Y,
				      (int)point16Y,  (int)point17Y,  (int)point18Y,  (int)point19Y,  (int)point20Y,
				      (int)point21Y,  (int)point22Y,  (int)point23Y,  (int)point24Y,  (int)point25Y,
				      (int)point26Y,  (int)point27Y,  (int)point28Y,  (int)point29Y,  (int)point30Y,
				      (int)point31Y,  (int)point32Y,  (int)point33Y,  (int)point34Y,  (int)point35Y,
				      (int)point36Y,  (int)point37Y,  (int)point38Y,  (int)point39Y,  (int)point40Y,
				      (int)point41Y,  (int)point42Y,  (int)point43Y,  (int)point44Y,  (int)point45Y};
		
		for (int i=0; i<xPos.length; i++) xPos[i]+=originX;
		for (int i=0; i<yPos.length; i++) yPos[i]+=originY;
		
		
		Main.townSquareNORTHEAST = new BuildingObjects(xPos, yPos, 45, Black, DefaultStroke);
		
		
//		Polygon building = new Polygon(xPos, yPos, 45);
//		
//		g.setColor(Black);
//		g.setStroke(DefaultStroke);
//		g.fillPolygon(building);
		
	}
}
