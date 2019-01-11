package mainGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
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
	final static int WINW = 2500;
	final static int WINH = WINW/5*3;
	static JFrame window;
	DrawingPanel drPanel = new DrawingPanel();
			
//Colors, Font, and Strokes
	Color White = new Color (255, 255, 255);
	Color Blue = new Color (0, 0, 255);
	Color Red = new Color (255, 0, 0);
	Color Green = new Color(0, 255, 0);
	Color Black = new Color (0, 0, 0);
	BasicStroke DefaultStroke = new BasicStroke(1);
	BasicStroke ColosseumStroke = new BasicStroke(WINW/50);
	
//NUMBERS USED FOR RATIOS
	static int ratioW = WINW/2500,
			   ratioH = WINH/1500;
	

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
	
	private class DrawingPanel extends JPanel {
		
		DrawingPanel() {this.setPreferredSize(new Dimension (WINW, WINH));}
		
		@Override
		public void paintComponent(Graphics g1) {
			Graphics2D g = (Graphics2D) g1;
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			super.paintComponent(g1);
			this.requestFocus();
			
//			drawBoarder(g);
//			drawMaze(g);
//			drawMansionFence(g);
			drawMansion(g);
//			drawColosseumBoarder(g);
//			drawSpawnPointBoarder(g);
			drawTriangleBuilding1(g);
			drawMansionSideBuilding1(g);
			drawMansionSideBuilding2(g);
			drawTriangleBuilding2(g);
			drawHouseArea1(g);
			drawSpawnPointSideStreet(g);
			drawBuildingWithBottomEntrance(g);
			drawHiddenCourtyard(g);
			drawHiddenCourtyardInterior(g);
			drawHouseArea2(g);
			drawHouseArea3(g);
			drawSouthEastWall(g);
			drawSouthEastNorthBuilding(g);
			drawSouthEastSouthBuilding(g);
			drawSouthEastExtendedBoarder(g);
			drawColosseum(g);
			
		}
		
	}
	
	void drawBoarder(Graphics2D g) {
		
		g.setColor(Black);
		g.setStroke(DefaultStroke);
		
	//POINT LIST
	// 01: 0, 0		02: WINW, 0		03: WINW, WINH		04: 0, WINH			05: 0, 0
	// 06: 50, 0	07: 50, 1450	08: 2450, 1450		09: 2450, 50		10: 0, 50
		
		int point01X = 0,  point02X = WINW, point03X = WINW, point04X = 0,    point05X = 0,
			point06X = 50, point07X = 50,   point08X = 2450, point09X = 2450, point10X = 0;
		int xPos[] = {point01X, point02X, point03X, point04X, point05X,
					  point06X, point07X, point08X, point09X, point10X};
		
		int point01Y = 0, point02Y = 0,    point03Y = WINH, point04Y =WINH, point05Y = 0,
			point06Y = 0, point07Y = 1450, point08Y = 1450, point09Y = 50,  point10Y = 50;
		int YPos[] = {point01Y, point02Y, point03Y, point04Y, point05Y,
				      point06Y, point07Y, point08Y, point09Y, point10Y};
		
		g.fillRect(0, 0, WINW, 50);
		g.fillRect(0, 0, 50, WINH);
		g.fillRect(0, WINH-50, WINW, WINH-50);
		g.fillRect(WINW-50, 0, WINW-50, WINH);
		
	}
	
	void drawMaze(Graphics2D g) {
		
		g.setColor(Black);
		g.setStroke(DefaultStroke);
		
		g.fillRect(81, 81, 400, 25);
		g.fillRect(81, 81, 25, 350);
		g.fillRect(81, 406, 400, 25);
		g.fillRect(456, 131, 25, 300);
		
		g.fillRect(131, 131, 350, 25);
		g.fillRect(131, 131, 25, 250);
		g.fillRect(156, 356, 275, 25);
		g.fillRect(406, 181, 25, 200);
		
		g.fillRect(181, 181, 250, 25);
		g.fillRect(181, 181, 25, 150);
		g.fillRect(181, 306, 200, 25);
		g.fillRect(356, 231, 25, 100);
		
		
	}

	void drawMansionFence(Graphics2D g) {
		
		g.setColor(Black);
		g.setStroke(DefaultStroke);
		
	//Ratios based off the width
		int width_EIGHTYONE = WINW/2500*81,
			width_FOURHUNDREDEIGHTY = WINW/125*24,
			width_THREEHUNDREDSEVENTYONE = WINW/2500*371,
			width_THREEHUNDREDTHIRTYTWO = WINW/1250*166,
			width_TWOHUNDREDEIGHTYTWO = WINW/1250*141;
		
	//North Fence
		g.drawLine(81, 461, 81, 625);
		g.drawLine(81, 461, 480, 461);
		g.drawLine(480, 461, 480, 675);
		g.drawLine(371, 675, 480, 675);
		g.drawLine(371, 675, 371, 724);
		g.drawLine(332, 724, 371, 724);
		g.drawLine(282, 724, 321, 724);
		
	//South Fence
		g.drawLine(81, 874, 81, 1038);
		g.drawLine(81, 1038, 480, 1038);
		g.drawLine(480, 824, 480, 1038);
		g.drawLine(371, 824, 480, 824);
		g.drawLine(371, 775, 371, 824);
		g.drawLine(332, 775, 371, 775);
		g.drawLine(282, 775, 321, 775);
		
	}
	
	void drawMansion(Graphics2D g) {

	//POINTS LIST
	//	01:	81, 626		
	//	02: 282, 626		
	//	03: 81, 874
	//	04: 282, 874
		
		int point01X = ratioW*81, point02X = ratioW*282, point03X = ratioW*282, point04X = ratioW*81;
		int xPos[] = {point01X, point02X, point03X, point04X};
		
		int point01Y = ratioH*626, point02Y = ratioH*626, point03Y = ratioH*874, point04Y = ratioH*874;
		int yPos[] = {point01Y, point02Y, point03Y, point04Y};
		
		Polygon building = new Polygon(xPos, yPos, 4);
	
		g.setColor(Black);
		g.setStroke(DefaultStroke);
		
		g.fillPolygon(building);
		
	}

	void drawColosseumBoarder(Graphics2D g) {
		
		g.setColor(Black);
		g.setStroke(DefaultStroke);
		
		g.fillRect(1181, 81, 50, 190);
		g.fillRect(531, 81, 700, 40);
		g.fillRect(531, 81, 50, 595);
		g.fillRect(531, 636, 620, 40);
		g.fillRect(531, 586, 700, 50);
		g.fillRect(1181, 320, 50, 316);
		
	}

	void drawSpawnPointBoarder(Graphics2D g) {
		
		g.setColor(Black);
		g.setStroke(DefaultStroke);
		
		g.drawLine(1150, 676, 1150, 724);
		g.drawLine(1150, 770, 1150, 807);
		g.drawLine(1355, 770, 1355, 807);
		
	}

	void drawTriangleBuilding1(Graphics2D g) {
		
		g.setColor(Black);
		g.setStroke(DefaultStroke);
		
	//POINT LIST
	// 01: 531, 824
	// 02: 531, 1056
	// 03: 824, 824
		
		int point1X = ratioW*531,
			point2X = ratioW*531,
			point3X = ratioW*917;
		
		int point1Y = ratioH*824,
			point2Y = ratioH*1056,
			point3Y = ratioH*824;
		
		int xPos[] = {point1X, point2X, point3X};
		int yPos[] = {point1Y, point2Y, point3Y};
		
		Polygon building = new Polygon(xPos, yPos, 3);
		
		g.fillPolygon(building);
		
		
	}

	void drawMansionSideBuilding1(Graphics2D g) {
		
		g.setColor(Black);
		g.setStroke(DefaultStroke);
		
	//POINT LIST
	// 01: 81, 1079
	// 02: 228, 1079
	// 03: 228, 1242
	// 04: 81, 1332
	
		int point1X = ratioW*81, point2X = ratioW*228, point3X = ratioW*228, point4X = ratioW*81;
		int xPos[] = {point1X, point2X, point3X, point4X};
		
		int point1Y = ratioH*1079, point2Y = ratioH*1079, point3Y = ratioH*1242, point4Y = ratioH*1332;
		int yPos[] = {point1Y, point2Y, point3Y, point4Y};
		
		Polygon building = new Polygon(xPos, yPos, 4);
		
		g.fillPolygon(building);
		
		
		
	}

	void drawMansionSideBuilding2(Graphics2D g) {
		
		g.setColor(Black);
		g.setStroke(DefaultStroke);
		
	//POINT LIST
	// 01: 279, 1079
	// 02: 480, 1079
	// 03: 480, 1090
	// 04: 279, 1211
		
		int point1X = ratioW*279, point2X = ratioW*480, point3X = ratioW*480, point4X = ratioW*279;
		int xPos[] = {point1X, point2X, point3X, point4X};
		
		int point1Y = ratioH*1079, point2Y = ratioH*1079, point3Y = ratioH*1090, point4Y = ratioH*1211;
		int yPos[] = {point1Y, point2Y, point3Y, point4Y};
		
		Polygon building = new Polygon(xPos, yPos, 4);
		
		g.fillPolygon(building);
		
		
		
	}

	void drawTriangleBuilding2(Graphics2D g) {
		
		g.setColor(Black);
		g.setStroke(DefaultStroke);
		
	//POINT LIST
	// 01: 480, 1209
	// 02: 480, 1380
	// 03: 195, 1380
		
		int point1X = ratioW*480, point2X = ratioW*480, point3X = ratioW*195;
		int xPos[] = {point1X, point2X, point3X};
		
		int point1Y = ratioH*1209, point2Y = ratioH*1380, point3Y = ratioH*1380;
		int yPos[] = {point1Y, point2Y, point3Y};
		
		Polygon building = new Polygon(xPos, yPos, 3);
		
		g.fillPolygon(building);
		
	}

	void drawHouseArea1(Graphics2D g) {
		
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
		int point01X = ratioW*910,  point02X = ratioW*960,  point03X = ratioW*816,  point04X = ratioW*816,  point05X = ratioW*759,
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
		int point01Y = ratioH*946,  point02Y = ratioH*1018, point03Y = ratioH*1018, point04Y = ratioH*1085, point05Y = ratioH*1085,
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
		
		int xPos[] = {point01X,  point02X,  point03X,  point04X,  point05X,
					  point06X,  point07X,  point08X,  point09X,  point10X,
				      point11X,  point12X,  point13X,  point14X,  point15X,
				      point16X,  point17X,  point18X,  point19X,  point20X,
				      point21X,  point22X,  point23X,  point24X,  point25X,
				      point26X,  point27X,  point28X,  point29X,  point30X,
				      point31X,  point32X,  point33X,  point34X,  point35X,
				      point36X,  point37X,  point38X,  point39X,  point40X,
				      point41X,  point42X,  point43X,  point44X,  point45X,
				      point46X,  point47X,  point48X,  point49X,  point50X,
				      point51X,  point52X,  point53X,  point54X,  point55X,
				      point56X,  point57X,  point58X,  point59X,  point60X,
				      point61X,  point62X,  point63X,  point64X,  point65X,
				      point66X,  point67X,  point68X,  point69X,  point70X,
				      point71X,  point72X,  point73X,  point74X,  point75X};
		
		int yPos[] = {point01Y,  point02Y,  point03Y,  point04Y,  point05Y,
					  point06Y,  point07Y,  point08Y,  point09Y,  point10Y,
				      point11Y,  point12Y,  point13Y,  point14Y,  point15Y,
				      point16Y,  point17Y,  point18Y,  point19Y,  point20Y,
				      point21Y,  point22Y,  point23Y,  point24Y,  point25Y,
				      point26Y,  point27Y,  point28Y,  point29Y,  point30Y,
				      point31Y,  point32Y,  point33Y,  point34Y,  point35Y,
				      point36Y,  point37Y,  point38Y,  point39Y,  point40Y,
				      point41Y,  point42Y,  point43Y,  point44Y,  point45Y,
				      point46Y,  point47Y,  point48Y,  point49Y,  point50Y,
				      point51Y,  point52Y,  point53Y,  point54Y,  point55Y,
				      point56Y,  point57Y,  point58Y,  point59Y,  point60Y,
				      point61Y,  point62Y,  point63Y,  point64Y,  point65Y,
				      point66Y,  point67Y,  point68Y,  point69Y,  point70Y,
				      point71Y,  point72Y,  point73Y,  point74Y,  point75Y};
		
		Polygon building = new Polygon(xPos, yPos, 75);
		
		g.setColor(Black);
		g.setStroke(DefaultStroke);
		g.fillPolygon(building);
	
	}

	void drawSpawnPointSideStreet(Graphics2D g) {
		
	//POINTS LIST - NORTH SECTION
	//	01:	1150, 807		02: 1150, 819		03: 1219, 819		04: 1219, 923		05: 1142, 923
	//	06:	1142, 895		07: 1068, 895		08: 1068, 972		09: 1072, 972		10:	1082, 1046
	//	11: 1030, 1084		12:	927, 935		13: 1133, 807
		
	//X Coordinates - North Section
		int Npoint01X = ratioW*1151, Npoint02X = ratioW*1151, Npoint03X = ratioW*1219, Npoint04X = ratioW*1219, Npoint05X = ratioW*1142,
			Npoint06X = ratioW*1142, Npoint07X = ratioW*1068, Npoint08X = ratioW*1068, Npoint09X = ratioW*1082, Npoint10X = ratioW*1082,
			Npoint11X = ratioW*1030, Npoint12X = ratioW*927,  Npoint13X = ratioW*1133;
		
	//Y Coordinates - North Section
		int Npoint01Y = ratioH*807,  Npoint02Y = ratioH*819,  Npoint03Y = ratioH*819,  Npoint04Y = ratioH*923,  Npoint05Y = ratioH*923,
			Npoint06Y = ratioH*895,  Npoint07Y = ratioH*895,  Npoint08Y = ratioH*972,  Npoint09Y = ratioH*972,  Npoint10Y = ratioH*1046,
			Npoint11Y = ratioH*1084, Npoint12Y = ratioH*935,  Npoint13Y = ratioH*807;		
				
		int NxPos[] = {Npoint01X, Npoint02X, Npoint03X, Npoint04X, Npoint05X,
					   Npoint06X, Npoint07X, Npoint08X, Npoint09X, Npoint10X,
					   Npoint11X, Npoint12X, Npoint13X};
		
		int NyPos[] = {Npoint01Y, Npoint02Y, Npoint03Y, Npoint04Y, Npoint05Y,
				  	   Npoint06Y, Npoint07Y, Npoint08Y, Npoint09Y, Npoint10Y,
				       Npoint11Y, Npoint12Y, Npoint13Y};
		
		Polygon NorthBuilding = new Polygon(NxPos, NyPos, 13);
		
		
		
	//POINTS LIST - SOUTH SECTION
	//	01:	1219, 940		02: 1219, 1176		03: 1093, 1176		04: 1039, 1099		05: 1093, 1061
	//	06:	1132, 1061		07: 1132, 972		08: 1142, 972		09: 1142, 940		
	
	//X Coordinates - North Section
		int Spoint01X = ratioW*1219, Spoint02X = ratioW*1219, Spoint03X = ratioW*1093, Spoint04X = ratioW*1039, Spoint05X = ratioW*1093,
			Spoint06X = ratioW*1132, Spoint07X = ratioW*1132, Spoint08X = ratioW*1142, Spoint09X = ratioW*1142;
	
	//Y Coordinates - North Section
		int Spoint01Y = ratioH*940,  Spoint02Y = ratioH*1176, Spoint03Y = ratioH*1176, Spoint04Y = ratioH*1099, Spoint05Y = ratioH*1061,
		    Spoint06Y = ratioH*1061, Spoint07Y = ratioH*972,  Spoint08Y = ratioH*972,  Spoint09Y = ratioH*940;  	
			
		int SxPos[] = {Spoint01X, Spoint02X, Spoint03X, Spoint04X, Spoint05X,
					   Spoint06X, Spoint07X, Spoint08X, Spoint09X};
	
		int SyPos[] = {Spoint01Y, Spoint02Y, Spoint03Y, Spoint04Y, Spoint05Y,
				       Spoint06Y, Spoint07Y, Spoint08Y, Spoint09Y};
	
		Polygon SouthBuilding = new Polygon(SxPos, SyPos, 9);
			
		g.setColor(Black);
		g.setStroke(DefaultStroke);
		
		g.fillPolygon(NorthBuilding);
		g.fillPolygon(SouthBuilding);

			
	}

	void drawBuildingWithBottomEntrance(Graphics2D g) {
		
	//POINTS LIST
	//	01:	1122, 1211		02: 1375, 1211		03: 1375, 1380		04: 1270, 1380		05: 1270, 1346
	//	06:	1216, 1346		07: 1216, 1380		08: 1122, 1380		
			
		int point01X = ratioW*1122, point02X = ratioW*1375, point03X = ratioW*1375, point04X = ratioW*1270, point05X = ratioW*1270,
			point06X = ratioW*1216, point07X = ratioW*1216, point08X = ratioW*1122;
			
		int point01Y = ratioH*1211, point02Y = ratioH*1211, point03Y = ratioH*1380, point04Y = ratioH*1380, point05Y = ratioH*1346,
			point06Y = ratioH*1346, point07Y = ratioH*1380, point08Y = ratioH*1380;	
		
		int xPos[] = {point01X, point02X, point03X, point04X, point05X,
					  point06X, point07X, point08X};
		
		int yPos[] = {point01Y, point02Y, point03Y, point04Y, point05Y,
				      point06Y, point07Y, point08Y};
		
		Polygon building = new Polygon(xPos, yPos, 8);
		
		g.setColor(Black);
		g.setStroke(DefaultStroke);
		g.fillPolygon(building);
	
	}

	void drawHiddenCourtyard(Graphics2D g) {
		
	//OUTSIDE POINTS LIST
	//	01:	1615, 798		02: 1615, 1380		03: 1422, 1380		04: 1422, 1176		05: 1266, 1176
	//	06:	1266, 819		07: 1355, 819		08: 1355, 798		
			
		int Opoint01X = ratioW*1615, Opoint02X = ratioW*1615, Opoint03X = ratioW*1422, Opoint04X = ratioW*1422, Opoint05X = ratioW*1266,
			Opoint06X = ratioW*1266, Opoint07X = ratioW*1355, Opoint08X = ratioW*1355;
			
		int Opoint01Y = ratioH*798, Opoint02Y = ratioH*1380, Opoint03Y = ratioH*1380, Opoint04Y = ratioH*1176, Opoint05Y = ratioH*1176,
			Opoint06Y = ratioH*819, Opoint07Y = ratioH*819,  Opoint08Y = ratioH*798;	
		
		int OxPos[] = {Opoint01X, Opoint02X, Opoint03X, Opoint04X, Opoint05X,
					  Opoint06X, Opoint07X, Opoint08X};
		
		int OyPos[] = {Opoint01Y, Opoint02Y, Opoint03Y, Opoint04Y, Opoint05Y,
				      Opoint06Y, Opoint07Y, Opoint08Y};
		
		Polygon building = new Polygon(OxPos, OyPos, 8);
		
		
	//INSIDE POINTS LIST
	//	01:	1539, 888		02: 1539, 1090		03: 1528, 1090		04: 1528, 1312		05: 1533, 1312
	//	06:	1533, 1351		07: 1494, 1351		08: 1494, 1312		09: 1499, 1312		10: 1499, 1090	
	//	11:	1345, 1090		12: 1345, 888
		
		int Ipoint01X = ratioW*1539, Ipoint02X = ratioW*1539, Ipoint03X = ratioW*1528, Ipoint04X = ratioW*1528, Ipoint05X = ratioW*1533,
			Ipoint06X = ratioW*1533, Ipoint07X = ratioW*1494, Ipoint08X = ratioW*1494, Ipoint09X = ratioW*1499, Ipoint10X = ratioW*1499, 
			Ipoint11X = ratioW*1345, Ipoint12X = ratioW*1345;
		
		int Ipoint01Y = ratioH*888,  Ipoint02Y = ratioH*1090, Ipoint03Y = ratioH*1090, Ipoint04Y = ratioH*1312, Ipoint05Y = ratioH*1312,
			Ipoint06Y = ratioH*1351, Ipoint07Y = ratioH*1351, Ipoint08Y = ratioH*1312, Ipoint09Y = ratioH*1312, Ipoint10Y = ratioH*1090,  
			Ipoint11Y = ratioH*1090, Ipoint12Y = ratioH*888;	
	
		int IxPos[] = {Ipoint01X, Ipoint02X, Ipoint03X, Ipoint04X, Ipoint05X,
				  	   Ipoint06X, Ipoint07X, Ipoint08X, Ipoint09X, Ipoint10X, 
				  	   Ipoint11X, Ipoint12X};
	
		int IyPos[] = {Ipoint01Y, Ipoint02Y, Ipoint03Y, Ipoint04Y, Ipoint05Y,
			       	   Ipoint06Y, Ipoint07Y, Ipoint08Y, Ipoint09Y, Ipoint10Y, 
				  	   Ipoint11Y, Ipoint12Y};
		
		Polygon buildingCutout = new Polygon(IxPos, IyPos, 12);
		
		g.setColor(Black);
		g.setStroke(DefaultStroke);
		g.fillPolygon(building);
		
		g.setColor(White);
		g.setStroke(DefaultStroke);
		g.fillPolygon(buildingCutout);
	
	}

	void drawHiddenCourtyardInterior(Graphics2D g) {
		
	//POINTS LIST
	//	01:	1485, 937		
	//	02: 1485, 1025		
	//	03: 1403, 1025		
	//	04: 1403, 937
			
		int point01X = ratioW*1485, point02X = ratioW*1485, point03X = ratioW*1403, point04X = ratioW*1403;
		int xPos[] = {point01X, point02X, point03X, point04X};
		
		int point01Y = ratioH*937,  point02Y = ratioH*1025, point03Y = ratioH*1025, point04Y = ratioH*937;
		int yPos[] = {point01Y, point02Y, point03Y, point04Y};
		
		Polygon building = new Polygon(xPos, yPos, 4);	
		
		g.setColor(Black);
		g.setStroke(DefaultStroke);
		g.fillPolygon(building);
			
	}

	void drawHouseArea2(Graphics2D g) {
		
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
		int point01X = ratioW*2004, point02X = ratioW*2004, point03X = ratioW*1884, point04X = ratioW*1884, point05X = ratioW*1859,
			point06X = ratioW*1859, point07X = ratioW*1934, point08X = ratioW*1934, point09X = ratioW*1984, point10X = ratioW*1984,
			point11X = ratioW*1934, point12X = ratioW*1934, point13X = ratioW*1860, point14X = ratioW*1860, point15X = ratioW*1934,
			point16X = ratioW*1934, point17X = ratioW*1983, point18X = ratioW*1983, point19X = ratioW*1934, point20X = ratioW*1934,
			point21X = ratioW*1859, point22X = ratioW*1859, point23X = ratioW*1934, point24X = ratioW*1934, point25X = ratioW*1984,
			point26X = ratioW*1984, point27X = ratioW*1934, point28X = ratioW*1934, point29X = ratioW*1859, point30X = ratioW*1859,
			point31X = ratioW*1832, point32X = ratioW*1832, point33X = ratioW*1792, point34X = ratioW*1792, point35X = ratioW*1717,
			point36X = ratioW*1717, point37X = ratioW*1792, point38X = ratioW*1792, point39X = ratioW*1832, point40X = ratioW*1832,
			point41X = ratioW*1807, point42X = ratioW*1807, point43X = ratioW*1690, point44X = ratioW*1690;
		
	//Y Coordinates
		int point01Y = ratioH*798,  point02Y = ratioH*1065, point03Y = ratioH*1065, point04Y = ratioH*1042, point05Y = ratioH*1042,
			point06Y = ratioH*1020, point07Y = ratioH*1020, point08Y = ratioH*1034, point09Y = ratioH*1034, point10Y = ratioH*983,
			point11Y = ratioH*983,  point12Y = ratioH*994,  point13Y = ratioH*994,  point14Y = ratioH*953,  point15Y = ratioH*953,
			point16Y = ratioH*967,  point17Y = ratioH*967,  point18Y = ratioH*916,  point19Y = ratioH*916,  point20Y = ratioH*928,
			point21Y = ratioH*928,  point22Y = ratioH*873,  point23Y = ratioH*873,  point24Y = ratioH*886,  point25Y = ratioH*886,
			point26Y = ratioH*834,  point27Y = ratioH*834,  point28Y = ratioH*849,  point29Y = ratioH*849,  point30Y = ratioH*837,
			point31Y = ratioH*837,  point32Y = ratioH*934,  point33Y = ratioH*934,  point34Y = ratioH*847,  point35Y = ratioH*847,
			point36Y = ratioH*1022, point37Y = ratioH*1022, point38Y = ratioH*948,  point39Y = ratioH*948,  point40Y = ratioH*1042,
			point41Y = ratioH*1042, point42Y = ratioH*1065, point43Y = ratioH*1065, point44Y = ratioH*798;
		
		int xPos[] = {point01X,  point02X,  point03X,  point04X,  point05X,
					  point06X,  point07X,  point08X,  point09X,  point10X,
				      point11X,  point12X,  point13X,  point14X,  point15X,
				      point16X,  point17X,  point18X,  point19X,  point20X,
				      point21X,  point22X,  point23X,  point24X,  point25X,
				      point26X,  point27X,  point28X,  point29X,  point30X,
				      point31X,  point32X,  point33X,  point34X,  point35X,
				      point36X,  point37X,  point38X,  point39X,  point40X,
				      point41X,  point42X,  point43X,  point44X};
		
		int yPos[] = {point01Y,  point02Y,  point03Y,  point04Y,  point05Y,
					  point06Y,  point07Y,  point08Y,  point09Y,  point10Y,
				      point11Y,  point12Y,  point13Y,  point14Y,  point15Y,
				      point16Y,  point17Y,  point18Y,  point19Y,  point20Y,
				      point21Y,  point22Y,  point23Y,  point24Y,  point25Y,
				      point26Y,  point27Y,  point28Y,  point29Y,  point30Y,
				      point31Y,  point32Y,  point33Y,  point34Y,  point35Y,
				      point36Y,  point37Y,  point38Y,  point39Y,  point40Y,
				      point41Y,  point42Y,  point43Y,  point44Y};
		
		Polygon building = new Polygon(xPos, yPos, 44);
		
		g.setColor(Black);
		g.setStroke(DefaultStroke);
		g.fillPolygon(building);
		
	}

	void drawHouseArea3(Graphics2D g) {
		
	//OUTSIDE POINTS LIST
	//	01:	2004, 1098		02: 2004, 1354		03: 1690, 1354		04: 1690, 1098		05: 1807, 1098
	//	06:	1807, 1127		07: 1832, 1127		08: 1832, 1150		09: 1713, 1150		10:	1713, 1200
	//	11: 1706, 1200		12:	1706, 1243		13: 1713, 1243		14: 1713, 1311		15:	1831, 1311
	//	16: 1831, 1324		17: 1859, 1324		18:	1859, 1308		19:	1974, 1308		20: 1974, 1267
	//	21:	1945, 1267		22: 1945, 1233		23: 1966, 1233		24: 1966, 1161		25: 1897, 1161
	//	26:	1897, 1233		27:	1922, 1233		28: 1922, 1267		29: 1860, 1267		30: 1860, 1127
	//	31:	1884, 1127		32: 1884, 1098
			
	//X Coordinates
		int Opoint01X = ratioW*2004, Opoint02X = ratioW*2004, Opoint03X = ratioW*1690, Opoint04X = ratioW*1690, Opoint05X = ratioW*1807,
			Opoint06X = ratioW*1807, Opoint07X = ratioW*1832, Opoint08X = ratioW*1832, Opoint09X = ratioW*1713, Opoint10X = ratioW*1713,
			Opoint11X = ratioW*1706, Opoint12X = ratioW*1706, Opoint13X = ratioW*1713, Opoint14X = ratioW*1713, Opoint15X = ratioW*1831,
			Opoint16X = ratioW*1831, Opoint17X = ratioW*1859, Opoint18X = ratioW*1859, Opoint19X = ratioW*1974, Opoint20X = ratioW*1974,
			Opoint21X = ratioW*1945, Opoint22X = ratioW*1945, Opoint23X = ratioW*1966, Opoint24X = ratioW*1966, Opoint25X = ratioW*1897,
			Opoint26X = ratioW*1897, Opoint27X = ratioW*1922, Opoint28X = ratioW*1922, Opoint29X = ratioW*1860, Opoint30X = ratioW*1860,
			Opoint31X = ratioW*1884, Opoint32X = ratioW*1884;
		
	//Y Coordinates
		int Opoint01Y = ratioH*1098, Opoint02Y = ratioH*1354, Opoint03Y = ratioH*1354, Opoint04Y = ratioH*1098, Opoint05Y = ratioH*1098,
			Opoint06Y = ratioH*1127, Opoint07Y = ratioH*1127, Opoint08Y = ratioH*1150, Opoint09Y = ratioH*1150, Opoint10Y = ratioH*1200,
			Opoint11Y = ratioH*1200, Opoint12Y = ratioH*1243, Opoint13Y = ratioH*1243, Opoint14Y = ratioH*1311, Opoint15Y = ratioH*1311,
			Opoint16Y = ratioH*1324, Opoint17Y = ratioH*1324, Opoint18Y = ratioH*1308, Opoint19Y = ratioH*1308, Opoint20Y = ratioH*1267,
			Opoint21Y = ratioH*1267, Opoint22Y = ratioH*1233, Opoint23Y = ratioH*1233, Opoint24Y = ratioH*1161, Opoint25Y = ratioH*1161,
			Opoint26Y = ratioH*1233, Opoint27Y = ratioH*1233, Opoint28Y = ratioH*1267, Opoint29Y = ratioH*1267, Opoint30Y = ratioH*1127,
			Opoint31Y = ratioH*1127, Opoint32Y = ratioH*1098;
		
		int OxPos[] = {Opoint01X,  Opoint02X,  Opoint03X,  Opoint04X,  Opoint05X,
					  Opoint06X,  Opoint07X,  Opoint08X,  Opoint09X,  Opoint10X,
				      Opoint11X,  Opoint12X,  Opoint13X,  Opoint14X,  Opoint15X,
				      Opoint16X,  Opoint17X,  Opoint18X,  Opoint19X,  Opoint20X,
				      Opoint21X,  Opoint22X,  Opoint23X,  Opoint24X,  Opoint25X,
				      Opoint26X,  Opoint27X,  Opoint28X,  Opoint29X,  Opoint30X,
				      Opoint31X,  Opoint32X};
		
		int OyPos[] = {Opoint01Y,  Opoint02Y,  Opoint03Y,  Opoint04Y,  Opoint05Y,
					  Opoint06Y,  Opoint07Y,  Opoint08Y,  Opoint09Y,  Opoint10Y,
				      Opoint11Y,  Opoint12Y,  Opoint13Y,  Opoint14Y,  Opoint15Y,
				      Opoint16Y,  Opoint17Y,  Opoint18Y,  Opoint19Y,  Opoint20Y,
				      Opoint21Y,  Opoint22Y,  Opoint23Y,  Opoint24Y,  Opoint25Y,
				      Opoint26Y,  Opoint27Y,  Opoint28Y,  Opoint29Y,  Opoint30Y,
				      Opoint31Y,  Opoint32Y};
		
		Polygon building = new Polygon(OxPos, OyPos, 32);

	
//INSIDE POINTS LIST
//		01:	1832, 1162		02: 1832, 1215		03: 1794, 1215		04: 1794, 1210		05: 1769, 1210
//		06:	1769, 1236		07: 1793, 1236		08: 1793, 1232		09: 1832, 1232		10:	1832, 1299
//		11: 1732, 1299		12:	1732, 1243		13: 1748, 1243		14: 1748, 1200		15:	1732, 1200
//		16: 1732, 1162
			
	//X Coordinates
		int Ipoint01X = ratioW*1832, Ipoint02X = ratioW*1832, Ipoint03X = ratioW*1794, Ipoint04X = ratioW*1794, Ipoint05X = ratioW*1769,
			Ipoint06X = ratioW*1769, Ipoint07X = ratioW*1793, Ipoint08X = ratioW*1793, Ipoint09X = ratioW*1832, Ipoint10X = ratioW*1832,
			Ipoint11X = ratioW*1732, Ipoint12X = ratioW*1732, Ipoint13X = ratioW*1748, Ipoint14X = ratioW*1748, Ipoint15X = ratioW*1732,
			Ipoint16X = ratioW*1732;
		
	//Y Coordinates
		int Ipoint01Y = ratioH*1162, Ipoint02Y = ratioH*1215, Ipoint03Y = ratioH*1215, Ipoint04Y = ratioH*1210, Ipoint05Y = ratioH*1210,
			Ipoint06Y = ratioH*1236, Ipoint07Y = ratioH*1236, Ipoint08Y = ratioH*1232, Ipoint09Y = ratioH*1232, Ipoint10Y = ratioH*1299,
			Ipoint11Y = ratioH*1299, Ipoint12Y = ratioH*1243, Ipoint13Y = ratioH*1243, Ipoint14Y = ratioH*1200, Ipoint15Y = ratioH*1200,
			Ipoint16Y = ratioH*1162;
		
		int IxPos[] = {Ipoint01X,  Ipoint02X,  Ipoint03X,  Ipoint04X,  Ipoint05X,
					   Ipoint06X,  Ipoint07X,  Ipoint08X,  Ipoint09X,  Ipoint10X,
				       Ipoint11X,  Ipoint12X,  Ipoint13X,  Ipoint14X,  Ipoint15X,
				       Ipoint16X};
		
		int IyPos[] = {Ipoint01Y,  Ipoint02Y,  Ipoint03Y,  Ipoint04Y,  Ipoint05Y,
					   Ipoint06Y,  Ipoint07Y,  Ipoint08Y,  Ipoint09Y,  Ipoint10Y,
				       Ipoint11Y,  Ipoint12Y,  Ipoint13Y,  Ipoint14Y,  Ipoint15Y,
				       Ipoint16Y};
		
		Polygon interiorBuilding = new Polygon(IxPos, IyPos, 16);
		
	g.setColor(Black);
	g.setStroke(DefaultStroke);
	g.fillPolygon(building);
	g.fillPolygon(interiorBuilding);
		
	}

	void drawSouthEastWall(Graphics2D g) {
		
	//POINTS LIST
	//	01:	2111, 1023		
	//	02: 2111, 1354		
	//	03: 2068, 1354		
	//	04: 2068, 1023
			
		int point01X = ratioW*2111, point02X = ratioW*2111, point03X = ratioW*2068, point04X = ratioW*2068;
		int xPos[] = {point01X, point02X, point03X, point04X};
		
		int point01Y = ratioH*1023,  point02Y = ratioH*1354, point03Y = ratioH*1354, point04Y = ratioH*1023;
		int yPos[] = {point01Y, point02Y, point03Y, point04Y};
		
		Polygon building = new Polygon(xPos, yPos, 4);	
		
		g.setColor(Black);
		g.setStroke(DefaultStroke);
		g.fillPolygon(building);
		
	}

	void drawSouthEastNorthBuilding(Graphics2D g) {
		
	//POINTS LIST
	//	01:	2377, 1023		02: 2377, 1156		03: 2323, 1156		04: 2323, 1111 		05:2184, 1111		
	//	06: 2184, 1156		07: 2153, 1156		08: 2153, 1023
			
		int point01X = ratioW*2377, point02X = 2377, point03X = ratioW*2323, point04X = ratioW*2323, point05X = ratioW*2184, 
			point06X = ratioW*2184, point07X = 2153, point08X = ratioW*2153;
		
		int point01Y = ratioH*1023,  point02Y = ratioH*1156, point03Y = ratioH*1156, point04Y = ratioH*1111, point05Y = ratioH*1111,  
			point06Y = ratioH*1156,  point07Y = ratioH*1156, point08Y = ratioH*1023;
		
		int xPos[] = {point01X, point02X, point03X, point04X, point05X, 
				      point06X, point07X, point08X};
		
		int yPos[] = {point01Y, point02Y, point03Y, point04Y, point05Y, 
				      point06Y, point07Y, point08Y};
		
		Polygon building = new Polygon(xPos, yPos, 8);	
		
		g.setColor(Black);
		g.setStroke(DefaultStroke);
		g.fillPolygon(building);
			
	}

	void drawSouthEastSouthBuilding(Graphics2D g) {
		
	//POINTS LIST
	//	01:	2377, 1198		02: 2377, 1354		03: 2296, 1354		04: 2296, 1315 		05:2215, 1315		
	//	06: 2215, 1354		07: 2153, 1354		08: 2153, 1198		09: 2184, 1198		10: 2184, 1252
	//	11: 2322, 1252		12: 2322, 1198
			
		int point01X = ratioW*2377, point02X = ratioW*2377, point03X = ratioW*2296, point04X = ratioW*2296, point05X = ratioW*2215, 
			point06X = ratioW*2215, point07X = ratioW*2153, point08X = ratioW*2153, point09X = ratioW*2184, point10X = ratioW*2184,
			point11X = ratioW*2322, point12X = ratioW*2322;
		
		int point01Y = ratioH*1198,  point02Y = ratioH*1354, point03Y = ratioH*1354, point04Y = ratioH*1315, point05Y = ratioH*1315,  
			point06Y = ratioH*1354,  point07Y = ratioH*1354, point08Y = ratioH*1198, point09Y = ratioH*1198, point10Y = ratioH*1252,
		    point11Y = ratioH*1252,  point12Y = ratioH*1198;
		
		int xPos[] = {point01X, point02X, point03X, point04X, point05X, 
				      point06X, point07X, point08X, point09X, point10X,
				      point11X, point12X};
		
		int yPos[] = {point01Y, point02Y, point03Y, point04Y, point05Y, 
				      point06Y, point07Y, point08Y, point09Y, point10Y,
				      point11Y, point12Y};
		
		Polygon building = new Polygon(xPos, yPos, 12);	
		
		g.setColor(Black);
		g.setStroke(DefaultStroke);
		g.fillPolygon(building);
		
	}
	
	void drawSouthEastExtendedBoarder(Graphics2D g) {
		
	//POINTS LIST
	//	01:	1690, 1400		02: 2432, 1400		03: 2432, 1198		04: 2450, 1198 		05: 2450, 1156		
	//	06: 2432, 1156		07: 2432, 974		08: 2068, 974		09: 2068, 925		10: 2297, 925
	//	11: 2297, 764		12: 2315, 764		13: 2315, 733		14: 2433, 733		15: 2433, 587		
	//  16: 2499, 587		17: 2499, 1499		18: 1690, 1499
			
		int point01X = ratioW*1690, point02X = ratioW*2432, point03X = ratioW*2432, point04X = ratioW*2450, point05X = ratioW*2450, 
			point06X = ratioW*2432, point07X = ratioW*2432, point08X = ratioW*2068, point09X = ratioW*2068, point10X = ratioW*2297,
			point11X = ratioW*2297, point12X = ratioW*2315, point13X = ratioW*2315, point14X = ratioW*2433, point15X = ratioW*2433,  
			point16X = ratioW*2499, point17X = ratioW*2499, point18X = ratioW*1690;
		
		int point01Y = ratioH*1400,  point02Y = ratioH*1400, point03Y = ratioH*1198, point04Y = ratioH*1198, point05Y = ratioH*1156,  
			point06Y = ratioH*1156,  point07Y = ratioH*974,  point08Y = ratioH*974,  point09Y = ratioH*925,  point10Y = ratioH*925,
		    point11Y = ratioH*764,   point12Y = ratioH*764,  point13Y = ratioH*733,  point14Y = ratioH*733,  point15Y = ratioH*587,  
		    point16Y = ratioH*587,   point17Y = ratioH*1499, point18Y = ratioH*1499;
		
		int xPos[] = {point01X, point02X, point03X, point04X, point05X, 
				      point06X, point07X, point08X, point09X, point10X,
				      point11X, point12X, point13X, point14X, point15X, 
				      point16X, point17X, point18X};
		
		int yPos[] = {point01Y, point02Y, point03Y, point04Y, point05Y, 
				      point06Y, point07Y, point08Y, point09Y, point10Y,
				      point11Y, point12Y, point13Y, point14Y, point15Y, 
				      point16Y, point17Y, point18Y};
		
		Polygon building = new Polygon(xPos, yPos, 18);	
		
		g.setColor(Black);
		g.setStroke(DefaultStroke);
		g.fillPolygon(building);
		
	}
	
	void drawColosseum(Graphics2D g) {
		
		g.setStroke(ColosseumStroke);
		g.setColor(Black);
		
	//Arc Variables for All
		int arcX = ratioW*656, 
			arcY = ratioH*164,
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
		
		g.drawArc(arcX, arcY, arcWidth, arcHeight, arc1startAngle, arc1arcAngle);
		g.drawArc(arcX, arcY, arcWidth, arcHeight, arc2startAngle, arc2arcAngle);
		g.drawArc(arcX, arcY, arcWidth, arcHeight, arc3startAngle, arc3arcAngle);
		
	}
	
	
}
