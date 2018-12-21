package mainGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;

public class startMenu implements KeyListener, MouseListener, MouseMotionListener {

// --------------------------------------	
// ---------- Global Variables ----------
// --------------------------------------
	
//Random Variables
	String gameTitle = "Assassins";
	
//JFrame and JWindow Creations
	final static int WIN = 1000;
	static JFrame window;
	StartMenuPanel smPanel = new StartMenuPanel();
	
//Colors, Font, and Strokes
	Color White = new Color (255, 255, 255);
	Color Blue = new Color (0, 0, 255);
	Color Red = new Color (255, 0, 0);
	Color Green = new Color(0, 255, 0);
	Color Black = new Color (0, 0, 0);
	BasicStroke mainMenuButtonStroke = new BasicStroke(WIN/100);
	BasicStroke fileMenuFileTitleUnderlines = new BasicStroke(WIN/200);
	Font mainMenuButtons = new Font("Century", Font.BOLD, WIN/25);
	Font mainMenuTitle = new Font("Century", Font.BOLD, WIN/100*8);
	Font fileMenuTitle = new Font("Century", Font.BOLD, WIN/100*6);
	Font fileMenuButtonsTitle = new Font("Georgia", Font.PLAIN, WIN/100*6);
	Font fileMenuButtonsText = new Font("Georgia", Font.PLAIN, WIN/500*18);
	
//Start Menu Variables
	int menu_MAIN     = 1,
		menu_NEWGAME  = 2,
		menu_LOADGAME = 3,
		menu_OPTIONS  = 4,
		menu_CURRENT  = menu_MAIN,
		mainButtonType = 0,
		fileButtonType = 1;
	
//Main Menu Buttons	
	StartMenuButtons mainButton1 = new StartMenuButtons(WIN/20*3, WIN/20*9, mainButtonType);
	StartMenuButtons mainButton2 = new StartMenuButtons(WIN/20*11, WIN/20*9, mainButtonType);
	StartMenuButtons mainButton3 = new StartMenuButtons(WIN/20*3, WIN/10*6, mainButtonType);
	StartMenuButtons mainButton4 = new StartMenuButtons(WIN/20*11, WIN/10*6, mainButtonType);
	StartMenuButtons[] mainButtons = {mainButton1, mainButton2, mainButton3, mainButton4};	// <---- Organize the Main Buttons into an array
	
//File Menu Buttons
	StartMenuButtons fileButton1 = new StartMenuButtons(WIN/40*3, WIN/5*2, fileButtonType);
	StartMenuButtons fileButton2 = new StartMenuButtons(WIN/2-WIN/8, WIN/5*2, fileButtonType);
	StartMenuButtons fileButton3 = new StartMenuButtons(WIN/40*27, WIN/5*2, fileButtonType);	
	StartMenuButtons[] fileButtons = {fileButton1, fileButton2, fileButton3};				// <---- Organize the File Buttons into an array
	
//Variables for each file
	SaveFiles file1 = new SaveFiles();
	SaveFiles file2 = new SaveFiles();
	SaveFiles file3 = new SaveFiles();
	SaveFiles[] saveFiles = {file1, file2, file3};	// <---- Organize the Save Files into an array
	
//Input Variables
	int mouseX, mouseY;
	boolean M1 = false;
	
//Timers
	int tSpeed = 1;
	Timer startMenuTimer;
	
	public static void main(String[] args) {new startMenu();}
	
	startMenu() {
		GUISetup();
		
		readFileMenuFileInformation();
		setFileMenuFileInformation();
		
		startMenuTimer = new Timer(tSpeed, new StartMenuTimer());
		startMenuTimer.start();
	}
	

//Start Menu Methods
	@SuppressWarnings("serial")
	private class StartMenuPanel extends JPanel {
		
		StartMenuPanel() {
			this.setPreferredSize(new Dimension (WIN, WIN));
			this.setBackground(Black);
		}
	
		@Override
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			super.paintComponent(g);
			this.requestFocus();
			
		//Draw Background	
			g.setColor(Black);
			g.fillRect(0, 0, WIN, WIN);
			
			if (menu_CURRENT == menu_MAIN) {
				drawMainMenuButtons(g, g2);
				drawMainMenuTitle(g, g2);
			}
			
			if (menu_CURRENT == menu_NEWGAME) {
				drawFileMenuButtons(g, g2);
				drawFileMenuTitle(g, g2, "START A NEW GAME");
				drawFileMenuButtonsInformation(g, g2);
			}
			
			if (menu_CURRENT == menu_LOADGAME) {
				drawFileMenuButtons(g, g2);
				drawFileMenuTitle(g, g2, "LOAD A SAVED GAME");
				drawFileMenuButtonsInformation(g, g2);
			}
		
		}
	
	}

//Start Menu Timer
	private class StartMenuTimer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
			if (menu_CURRENT == menu_MAIN) {
				checkMouseOverMainButtons();
				checkMainButtonsPressed();
			}
			
			if (menu_CURRENT == menu_NEWGAME) {
				checkMouseOverFileButtons();
				checkFileButtonsPressed();
			}
			
			if (menu_CURRENT == menu_LOADGAME) {
				checkMouseOverFileButtons();
				checkFileButtonsPressed();
			}
			
			window.repaint();
		}
		
	}

//DRAWING METHODS
	void drawMainMenuTitle(Graphics g, Graphics2D g2) {
		FontMetrics fontMetrics = g2.getFontMetrics(mainMenuTitle);
		g.setFont(mainMenuTitle);
		g.setColor(White);	
		
		String title = "ASSASSINS";
		int titleX = WIN/2-fontMetrics.stringWidth(title)/2;
		
		g.drawString(title, titleX, WIN/4);
		
	}
	
	void drawMainMenuButtons(Graphics g, Graphics2D g2) {
		
	//Draw Buttons
		g2.setStroke(mainMenuButtonStroke);
		Color ButtonOutline = new Color (140, 0, 0);
		Color ButtonFill = new Color (51, 0, 0);
		
	//Draw Button #1
		if (mainButton1.hovering) g.setColor(ButtonOutline);
		else g.setColor(ButtonFill);
		g.fillRoundRect(mainButton1.x, mainButton1.y, mainButton1.width, mainButton1.height, mainButton1.arc, mainButton1.arc);
		g.setColor(ButtonOutline);
		g.drawRoundRect(mainButton1.x, mainButton1.y, mainButton1.width, mainButton1.height, mainButton1.arc, mainButton1.arc);
			
	//Draw Button #2
		if (mainButton2.hovering) g.setColor(ButtonOutline);
		else g.setColor(ButtonFill);
		g.fillRoundRect(mainButton2.x, mainButton2.y, mainButton2.width, mainButton2.height, mainButton2.arc, mainButton2.arc);
		g.setColor(ButtonOutline);
		g.drawRoundRect(mainButton2.x, mainButton2.y, mainButton2.width, mainButton2.height, mainButton2.arc, mainButton2.arc);
			
	//Draw Button #3
		if (mainButton3.hovering) g.setColor(ButtonOutline);
		else g.setColor(ButtonFill);
		g.fillRoundRect(mainButton3.x, mainButton3.y, mainButton3.width, mainButton3.height, mainButton3.arc, mainButton3.arc);
		g.setColor(ButtonOutline);
		g.drawRoundRect(mainButton3.x, mainButton3.y, mainButton3.width, mainButton3.height, mainButton3.arc, mainButton3.arc);
			
	//Draw Button #4
		if (mainButton4.hovering) g.setColor(ButtonOutline);
		else g.setColor(ButtonFill);
		g.fillRoundRect(mainButton4.x, mainButton4.y, mainButton4.width, mainButton4.height, mainButton4.arc, mainButton4.arc);
		g.setColor(ButtonOutline);
		g.drawRoundRect(mainButton4.x, mainButton4.y, mainButton4.width, mainButton4.height, mainButton4.arc, mainButton4.arc);
			
	//Draw Button Text
		g.setColor(White);	
		g.setFont(mainMenuButtons);
		FontMetrics fontMetrics = g2.getFontMetrics(mainMenuButtons);	
		
		String button1text = "NEW GAME";
		String button2text = "LOAD GAME";
		String button3text = "OPTIONS";
		String button4text = "EXIT";
		
		int buttons1textX = (mainButton1.x+mainButton1.width/2)-(fontMetrics.stringWidth(button1text)/2);
		int buttons2textX = (mainButton2.x+mainButton2.width/2)-(fontMetrics.stringWidth(button2text)/2);
		int buttons3textX = (mainButton3.x+mainButton3.width/2)-(fontMetrics.stringWidth(button3text)/2);
		int buttons4textX = (mainButton4.x+mainButton4.width/2)-(fontMetrics.stringWidth(button4text)/2);
		int topButtonsTextY = (mainButton1.y+mainButton1.height/2)+(fontMetrics.getAscent()/2)-5;
		int bottomButtonsTextY = (mainButton3.y+mainButton1.height/2)+(fontMetrics.getAscent()/2)-5;
		
	//Draw Button Text #1
		g.drawString(button1text, buttons1textX, topButtonsTextY);
		g.drawString(button2text, buttons2textX, topButtonsTextY);
		g.drawString(button3text, buttons3textX, bottomButtonsTextY);
		g.drawString(button4text, buttons4textX, bottomButtonsTextY);
		
	}
	
	void drawFileMenuTitle(Graphics g, Graphics2D g2, String title) {
		FontMetrics fontMetrics = g2.getFontMetrics(fileMenuTitle);
		g.setFont(fileMenuTitle);
		g.setColor(White);	
		
		int titleX = WIN/2-fontMetrics.stringWidth(title)/2;
		g.drawString(title, titleX, WIN/4);
	}
	
	void drawFileMenuButtons(Graphics g, Graphics2D g2) {
		
		g2.setStroke(mainMenuButtonStroke);
		Color ButtonOutline = new Color (140, 0, 0);
		Color ButtonFill = new Color (51, 0, 0);
		
	//Button 1
		if (fileButton1.hovering) g.setColor(ButtonOutline);
		else g.setColor(ButtonFill);
		g.fillRoundRect(fileButton1.x, fileButton1.y, fileButton1.width, fileButton1.height, fileButton1.arc, fileButton1.arc);
		g.setColor(ButtonOutline);
		g.drawRoundRect(fileButton1.x, fileButton1.y, fileButton1.width, fileButton1.height, fileButton1.arc, fileButton1.arc);
		
	//Button 2
		if (fileButton2.hovering) g.setColor(ButtonOutline);
		else g.setColor(ButtonFill);
		g.fillRoundRect(fileButton2.x, fileButton2.y, fileButton2.width, fileButton2.height, fileButton2.arc, fileButton2.arc);
		g.setColor(ButtonOutline);
		g.drawRoundRect(fileButton2.x, fileButton2.y, fileButton2.width, fileButton2.height, fileButton2.arc, fileButton2.arc);
	
	//Button 3
		if (fileButton3.hovering) g.setColor(ButtonOutline);
		else g.setColor(ButtonFill);
		g.fillRoundRect(fileButton3.x, fileButton3.y, fileButton3.width, fileButton3.height, fileButton3.arc, fileButton3.arc);
		g.setColor(ButtonOutline);
		g.drawRoundRect(fileButton3.x, fileButton3.y, fileButton3.width, fileButton3.height, fileButton3.arc, fileButton3.arc);
	
	}
	
	void drawFileMenuButtonsInformation(Graphics g, Graphics2D g2) {

		g.setColor(White);
		g2.setStroke(fileMenuFileTitleUnderlines);
		FontMetrics fMetricsTitle = g2.getFontMetrics(fileMenuButtonsTitle);
		FontMetrics fMetricsText = g2.getFontMetrics(fileMenuButtonsText);
		
		for (int i=0; i<3; i++) {
			
			g.setFont(fileMenuButtonsTitle);
			
			if (saveFiles[i].empty) {
				
				g.setFont(fileMenuButtonsText);
				String title = "EMPTY";
				int titleX = (fileButtons[i].x+(fileButtons[i].width/2))-(fMetricsText.stringWidth(title)/2);
				int titleY = (fileButtons[i].y+(fileButtons[i].height/2))-(fMetricsText.getHeight());
				g.drawString(title, titleX, titleY);
				
				continue;
			}
			
			String title = saveFiles[i].name;
			int titleX = (fileButtons[i].x+(fileButtons[i].width/2))-(fMetricsTitle.stringWidth(title)/2);
			int titleY = (fileButtons[i].y+WIN/100*6);
			g.drawString(title, titleX, titleY);
			
			g.drawLine(titleX-WIN/20, titleY+WIN/100, titleX+fMetricsTitle.stringWidth(title)+WIN/20, titleY+WIN/100);
			
			g.setFont(fileMenuButtonsText);
			
			String hp = "HP: "+saveFiles[i].HP+"/"+saveFiles[i].maxHP;
			int hpX = (fileButtons[i].x+(fileButtons[i].width/2))-(fMetricsText.stringWidth(hp)/2);
			int hpY = (titleY+WIN/20);
			g.drawString(hp, hpX, hpY);
			
			String loc = saveFiles[i].location;
			int locX = (fileButtons[i].x+(fileButtons[i].width/2))-(fMetricsText.stringWidth(loc)/2);
			int locY = (hpY+WIN/20);
			g.drawString(loc, locX, locY);
			
			String prog = "33%";
			int progX = (fileButtons[i].x+(fileButtons[i].width/2))-(fMetricsText.stringWidth(prog)/2);
			int progY = (locY+WIN/20);
			g.drawString(prog, progX, progY);
			
		}
		
	}
	
//SYSTEM METHODS
	
	void GUISetup(){
		window = new JFrame(gameTitle);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		smPanel.addMouseListener(this);
		smPanel.addMouseMotionListener(this);
		window.add(smPanel);
		window.pack();
		window.setVisible(true);
//		window.setVisible(false);
	}
	
	void readFileMenuFileInformation() {
		
		String fileNames[] = {"SAVE FILE 1", "SAVE FILE 2", "SAVE FILE 3"};
		
		for (int i=0; i<3; i++) {
			
		//Create the Buffered Reader and Read the file
			BufferedReader brFile = null;
			try {
				brFile = new BufferedReader (new FileReader(new File(fileNames[i])));
			} catch (FileNotFoundException ee) {
				createNewFilesText();
			}
			
		//Process the text
			while (true) {
				try {
					String s = brFile.readLine();
					if (s==null) break;
					saveFiles[i].text.add(s);
				} catch (IOException e) {}
			}
			
		//Print it out FOR (TESTING)
//			System.out.println(saveFiles[i].text);
			
		}
	}

	void setFileMenuFileInformation() {
		
		String[] currentStr;
		
		for (int i=0; i<3; i++) {
			
			String strEmpty = saveFiles[i].text.get(0);
			String strName = saveFiles[i].text.get(1);
			String strMaxHP = saveFiles[i].text.get(2);
			String strHP = saveFiles[i].text.get(3);
			String strLocation = saveFiles[i].text.get(4);
			String strEnemiesKilled = saveFiles[i].text.get(5);
			
		//Set the "empty" boolean for the Save File
			currentStr = strEmpty.split(": ");
			strEmpty = currentStr[1];
			saveFiles[i].empty = Boolean.parseBoolean(strEmpty);
			
			if(saveFiles[i].empty) continue;
			
		//Set the Save File's Name
			currentStr = strName.split(": ");
			strName = currentStr[1];
			saveFiles[i].name = strName;
			
		//Set the Save File's MaxHP stat
			currentStr = strMaxHP.split(": ");
			strMaxHP = currentStr[1];
			saveFiles[i].maxHP = Integer.parseInt(strMaxHP);
			
		//Set the Save File's HP stat
			currentStr = strHP.split(": ");
			strHP = currentStr[1];
			saveFiles[i].HP = Integer.parseInt(strHP);
			
		//Set the Save File's location stat		
			currentStr = strLocation.split(": ");
			strLocation = currentStr[1];
			saveFiles[i].location = strLocation;
			
		//Set the Save File's "Enemies Killed" stat
			currentStr = strEnemiesKilled.split(": ");
			strEnemiesKilled = currentStr[1];
			saveFiles[i].enemiesKilled = Integer.parseInt(strEnemiesKilled);
			
		}
		
	}

	void checkMouseOverMainButtons() {

	//Left Buttons
		if (mouseX>=mainButton1.x && mouseX<=mainButton1.x+mainButton1.width) {
			if (mouseY>=mainButton1.y && mouseY<=mainButton1.y+mainButton1.height) mainButton1.hovering = true;
			else mainButton1.hovering = false;
			
			if (mouseY>=mainButton3.y && mouseY<=mainButton3.y+mainButton3.height) mainButton3.hovering = true;
			else mainButton3.hovering = false;
		}
		
		else {
			mainButton1.hovering = false; 
			mainButton3.hovering = false;
		}
		
	//Right Buttons
		if (mouseX>=mainButton2.x && mouseX<=mainButton2.x+mainButton2.width) {
			if (mouseY>=mainButton2.y && mouseY<=mainButton2.y+mainButton2.height) mainButton2.hovering = true;
			else mainButton2.hovering = false;
			
			if (mouseY>=mainButton4.y && mouseY<=mainButton4.y+mainButton4.height) mainButton4.hovering = true;
			else mainButton4.hovering = false;
		}
		
		else {
			mainButton2.hovering = false; 
			mainButton4.hovering = false;
		}
	}

	void checkMainButtonsPressed() {
		
		if (M1) {
			if (mainButton1.hovering) menu_CURRENT = menu_NEWGAME;
			if (mainButton2.hovering) menu_CURRENT = menu_LOADGAME;
			if (mainButton3.hovering) menu_CURRENT = menu_OPTIONS;
			if (mainButton4.hovering) System.exit(0);
		}
		
	}
	
	void checkMouseOverFileButtons() {
	
		if (mouseY>=fileButton1.y && mouseY<=fileButton1.y+fileButton1.height) {
			
		//Button 1
			if (mouseX>=fileButton1.x && mouseX<=fileButton1.x+fileButton1.width) {
				fileButton1.hovering = true;
			}
			else fileButton1.hovering = false;
			
		//Button 2	
			if (mouseX>=fileButton2.x && mouseX<=fileButton2.x+fileButton2.width) {
				fileButton2.hovering = true;
			}
			else fileButton2.hovering = false;
			
		//Button 3
			if (mouseX>=fileButton3.x && mouseX<=fileButton3.x+fileButton3.width) {
				fileButton3.hovering = true;
			}
			else fileButton3.hovering = false;
		}
		else {
			fileButton1.hovering = false;
			fileButton2.hovering = false;
			fileButton3.hovering = false;
		}
		
	}
	
	void checkFileButtonsPressed() {
		//TODO: WRITE THE CODE
	}
	
	void createNewFilesText() {
		
		//TODO: FIX THIS
		
		PrintWriter pwFile = null;
		try {pwFile = new PrintWriter( new BufferedWriter( new FileWriter ("SAVE FILES")));
			} catch (IOException e) {e.printStackTrace();
		}
		
		pwFile.println("FILE 1: ");
		pwFile.println("EMPTY: TRUE");
		pwFile.println("NAME: ");
		pwFile.println("MAXHP: ");
		pwFile.println("HP: ");
		pwFile.println("LOCATION: ");
		pwFile.println("ENEMIES KILLED: ");
		
		pwFile.println("");
		
		pwFile.println("FILE 2: ");
		pwFile.println("EMPTY: TRUE");
		pwFile.println("NAME: ");
		pwFile.println("MAXHP: ");
		pwFile.println("HP: ");
		pwFile.println("LOCATION: ");
		pwFile.println("ENEMIES KILLED: ");
		
		pwFile.println("");
		
		pwFile.println("FILE 3: ");
		pwFile.println("EMPTY: TRUE");
		pwFile.println("NAME: ");
		pwFile.println("MAXHP: ");
		pwFile.println("HP: ");
		pwFile.println("LOCATION: ");
		pwFile.println("ENEMIES KILLED: ");
		
		pwFile.close();
	}
	
//Keyboard Presses
	
	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	
//Mouse Inputs	
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton()==MouseEvent.BUTTON1) M1 = true;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

//UNUSED METHODS
	public void keyTyped(KeyEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mouseClicked(MouseEvent arg0) {}
}
