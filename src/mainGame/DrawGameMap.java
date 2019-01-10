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
	final static int WINH = 1500;
	static JFrame window;
	DrawingPanel drPanel = new DrawingPanel();
			
//Colors, Font, and Strokes
	Color White = new Color (255, 255, 255);
	Color Blue = new Color (0, 0, 255);
	Color Red = new Color (255, 0, 0);
	Color Green = new Color(0, 255, 0);
	Color Black = new Color (0, 0, 0);	

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
			
			drawBoarder(g);
			drawMaze(g);
			drawMansionFence(g);
			drawMansion(g);
			drawColosseumBoarder(g);
			drawSpawnPointBoarder(g);
			drawTriangleBuilding1(g);
			drawMansionSideBuilding1(g);
			drawMansionSideBuilding2(g);
			drawTriangleBuilding2(g);
			drawHousesArea1(g);
			
		}
		
	}
	
	void drawBoarder(Graphics2D g) {
		
		g.setColor(Black);
		
		g.fillRect(0, 0, WINW, 50);
		g.fillRect(0, 0, 50, WINH);
		g.fillRect(0, WINH-50, WINW, WINH-50);
		g.fillRect(WINW-50, 0, WINW-50, WINH);
		
	}
	
	void drawMaze(Graphics2D g) {
		
		g.setColor(Black);
		
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
		
		g.setColor(Black);
		g.fillRect(81, 626, 201, 248);
		
	}

	void drawColosseumBoarder(Graphics2D g) {
		
		g.setColor(Black);
		
		g.fillRect(1181, 81, 50, 190);
		g.fillRect(531, 81, 700, 40);
		g.fillRect(531, 81, 50, 595);
		g.fillRect(531, 636, 620, 40);
		g.fillRect(531, 586, 700, 50);
		g.fillRect(1181, 320, 50, 316);
		
	}

	void drawSpawnPointBoarder(Graphics2D g) {
		
		g.setColor(Black);
		
		g.drawLine(1150, 676, 1150, 724);
	}

	void drawTriangleBuilding1(Graphics2D g) {
		
		g.setColor(Black);
		
		int point1X = 531,
			point2X = 531,
			point3X = 917;
		
		int point1Y = 824,
			point2Y = 1056,
			point3Y = 824;
		
		int xPos[] = {point1X, point2X, point3X};
		int yPos[] = {point1Y, point2Y, point3Y};
		
		Polygon building = new Polygon(xPos, yPos, 3);
		
		g.fillPolygon(building);
		
		
	}

	void drawMansionSideBuilding1(Graphics2D g) {
		
		g.setColor(Black);
		
		int point1X = 81,
			point2X = 228,
			point3X = 228,
			point4X = 81;
		
		int point1Y = 1079,
			point2Y = 1079,
			point3Y = 1242,
			point4Y = 1332;
		
		int xPos[] = {point1X, point2X, point3X, point4X};
		int yPos[] = {point1Y, point2Y, point3Y, point4Y};
		
		Polygon building = new Polygon(xPos, yPos, 4);
		
		g.fillPolygon(building);
		
		
		
	}

	void drawMansionSideBuilding2(Graphics2D g) {
		
		g.setColor(Black);
		
		int point1X = 279,
			point2X = 480,
			point3X = 480,
			point4X = 279;
		
		int point1Y = 1079,
			point2Y = 1079,
			point3Y = 1090,
			point4Y = 1211;
		
		int xPos[] = {point1X, point2X, point3X, point4X};
		int yPos[] = {point1Y, point2Y, point3Y, point4Y};
		
		Polygon building = new Polygon(xPos, yPos, 4);
		
		g.fillPolygon(building);
		
		
		
	}

	void drawTriangleBuilding2(Graphics2D g) {
		
		g.setColor(Black);
		
		int point1X = 480,
			point2X = 480,
			point3X = 195;
		
		int point1Y = 1209,
			point2Y = 1380,
			point3Y = 1380;
		
		int xPos[] = {point1X, point2X, point3X};
		int yPos[] = {point1Y, point2Y, point3Y};
		
		Polygon building = new Polygon(xPos, yPos, 3);
		
		g.fillPolygon(building);
		
	}

	void drawHousesArea1(Graphics2D g) {
		
//POINTS LIST
//	01:	910, 946		02: 960, 1018		03: 816, 1081		04: 816, 1085		05: 759, 1085
//	06:	759, 1151		07: 632, 1151		08: 632, 1242		09: 617, 1242		10:	617, 1218
//	11: 576, 1218		12:	576, 1283		13: 617, 1286		14: 617, 1254		15:	632, 1254
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
	int point01X = 910,  point02X = 960,  point03X = 816,  point04X = 816,  point05X = 759,
		point06X = 759,  point07X = 632,  point08X = 632,  point09X = 617,  point10X = 617,
		point11X = 576,  point12X = 576,  point13X = 617,  point14X = 617,  point15X = 632,
		point16X = 632,  point17X = 617,  point18X = 617,  point19X = 576,  point20X = 576,
		point21X = 617,  point22X = 617,  point23X = 666,  point24X = 666,  point25X = 707,
		point26X = 707,  point27X = 666,  point28X = 666,  point29X = 647,  point30X = 647,
		point31X = 666,  point32X = 666,  point33X = 707,  point34X = 707,  point35X = 666,
		point36X = 666,  point37X = 647,  point38X = 647,  point39X = 759,  point40X = 759,
		point41X = 816,  point42X = 816,  point43X = 737,  point44X = 737,  point45X = 909,
		point46X = 909,  point47X = 835,  point48X = 835,  point49X = 888,  point50X = 888,
		point51X = 990,  point52X = 990,  point53X = 964,  point54X = 964,  point55X = 990,
		point56X = 990,  point57X = 962,  point58X = 962,  point59X = 1032, point60X = 1032,
		point61X = 1008, point62X = 1008, point63X = 1034, point64X = 1034, point65X = 1008,
		point66X = 1008, point67X = 888,  point68X = 888,  point69X = 835,  point70X = 835,
		point71X = 969,  point72X = 1075, point73X = 1075, point74X = 531,  point75X = 531;
	
//Y Coordinates
	int point01Y = 946,  point02Y = 1018, point03Y = 1081, point04Y = 1085, point05Y = 1085,
		point06Y = 1151, point07Y = 1151, point08Y = 1242, point09Y = 1242, point10Y = 1218,
		point11Y = 1218, point12Y = 1283, point13Y = 1286, point14Y = 1254, point15Y = 1254,
		point16Y = 1330, point17Y = 1330, point18Y = 1305, point19Y = 1370, point20Y = 1370,
		point21Y = 1370, point22Y = 1340, point23Y = 1340, point24Y = 1370, point25Y = 1370,
		point26Y = 1305, point27Y = 1305, point28Y = 1330, point29Y = 1330, point30Y = 1254,
		point31Y = 1254, point32Y = 1283, point33Y = 1283, point34Y = 1354, point35Y = 1218,
		point36Y = 1242, point37Y = 1242, point38Y = 1169, point39Y = 1169, point40Y = 1214,
		point41Y = 1214, point42Y = 1303, point43Y = 1303, point44Y = 1354, point45Y = 1354,
		point46Y = 1303, point47Y = 1303, point48Y = 1214, point49Y = 1214, point50Y = 1169,
		point51Y = 1169, point52Y = 1185, point53Y = 1185, point54Y = 1253, point55Y = 1253,
		point56Y = 1287, point57Y = 1287, point58Y = 1354, point59Y = 1354, point60Y = 1287,
		point61Y = 1287, point62Y = 1253, point63Y = 1253, point64Y = 1185, point65Y = 1032,
		point66Y = 1151, point67Y = 1151, point68Y = 1085, point69Y = 1085, point70Y = 1032,
		point71Y = 1032, point72Y = 1186, point73Y = 1380, point74Y = 1380, point75Y = 1178;
	
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
	
	g.fillPolygon(building);
	
	
	}



}
