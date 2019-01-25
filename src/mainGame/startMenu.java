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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

public class startMenu implements MouseListener, MouseMotionListener {

// --------------------------------------	
// ---------- Global Variables ----------
// --------------------------------------
	
//JFrame and JWindow Creations
	String gameTitle = "Assassins";
	final static int WIN = 1500;
	static JFrame window;
	StartMenuPanel smPanel = new StartMenuPanel();
	
//Colors, Font, and Strokes
	Color White = new Color (255, 255, 255);
	Color Blue = new Color (0, 0, 255);
	Color Red = new Color (255, 0, 0);
	Color Green = new Color(0, 255, 0);
	Color Black = new Color (0, 0, 0);
	BasicStroke mainMenuButtonStroke = new BasicStroke((int)(WIN/100.0));
	BasicStroke backButtonStroke = new BasicStroke((int)(WIN/500.0));
	BasicStroke fileMenuFileTitleUnderlines = new BasicStroke((int)(WIN/200.0));
	Font mainMenuButtons = new Font("Century", Font.BOLD, WIN/25);
	Font mainMenuTitle = new Font("Century", Font.BOLD, WIN/100*8);
	Font backButtonText = new Font("Georgia", Font.BOLD, (int)(WIN/300.0*13));
	Font fileMenuTitle = new Font("Century", Font.BOLD, WIN/100*6);
	Font fileMenuButtonsTitle = new Font("Georgia", Font.PLAIN, WIN/100*6);
	Font fileMenuButtonsText = new Font("Georgia", Font.PLAIN, (int)(WIN/500.0*18));
	
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
	
//Back Button
	StartMenuBackButtons backButton = new StartMenuBackButtons(WIN/150, WIN/150);
	
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
	boolean M1 = false;			// <---- This boolean is set to true when the mouse1 button is pressed, and false when released
	
//Timers
	int tSpeed = 60;
	Timer startMenuTimer;
	
//File Menu Messages
	long displayMessage = 0L;								// <---- Long variable used to stop showing a message after a certain time has passed
	boolean displayCannotLoadEmptyMessage = false;			// <---- Booleans for displaying certain messages or not
	boolean displayCannotOverwriteFileMessage = false;		// <---/
	
	public static void main(String[] args) {new startMenu();}
	
	startMenu() {
		
		GUISetup();													// <---- Sets up the GUI								
	
		readFileMenuFileInformation();								// <---- Reads the Save Files	
		setFileMenuFileInformation();								// <---- Sets the Save File variables to what is read from each Save File
		
		startMenuTimer = new Timer(tSpeed, new StartMenuTimer());	// <---- Creates the Timer
		startMenuTimer.start();										// <---- Starts the Timer
	}
	

//Start Menu Methods
	@SuppressWarnings("serial")
	private class StartMenuPanel extends JPanel {
		
		StartMenuPanel() {this.setPreferredSize(new Dimension (WIN, WIN));}				// <---- Draws the Window with the set window widths
	
		@Override
		public void paintComponent(Graphics g1) {
			Graphics2D g = (Graphics2D) g1;
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			super.paintComponent(g);
			this.requestFocus();
			
		//Draw Background	
			g.setColor(Black);
			g.fillRect(0, 0, WIN, WIN);
			
			if (menu_CURRENT == menu_MAIN) {				// <---- What is drawn on the Main Menu
				drawMainMenuButtons(g);
				drawMainMenuTitle(g);
			}
			
			if (menu_CURRENT == menu_NEWGAME) {				// <---- What is drawn on the New Game Menu
				drawBackButton(g);
				drawFileMenuButtons(g);
				drawFileMenuTitle(g, "START A NEW GAME");
				drawFileMenuButtonsInformation(g);
				drawFileMenuMessages(g);
			}
			
			if (menu_CURRENT == menu_LOADGAME) {			// <---- What is drawn on the Load Game Menu
				drawBackButton(g);
				drawFileMenuButtons(g);
				drawFileMenuTitle(g, "LOAD A SAVED GAME");
				drawFileMenuButtonsInformation(g);
				drawFileMenuMessages(g);
			}
			
			if (menu_CURRENT == menu_OPTIONS) {				// <---- What is drawn on the Options Menu
				drawBackButton(g);
				drawOptionsMenu(g);
			}
		
		}
	
	}

//Start Menu Timer
	private class StartMenuTimer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
			if (menu_CURRENT == menu_MAIN) {		// <---- What methods are run on the Main Menu
				checkMouseOverMainButtons();
				checkMainButtonsPressed();
			}
			
			if (menu_CURRENT == menu_NEWGAME) {		// <---- What methods are run on the New Game Menu
				checkMouseOverBackButton();
				checkBackButtonPressed();
				checkMouseOverFileButtons();
				checkFileButtonsPressed();
			}
			
			if (menu_CURRENT == menu_LOADGAME) {	// <---- What methods are run on the Load Game Menu
				checkMouseOverBackButton();
				checkBackButtonPressed();
				checkMouseOverFileButtons();
				checkFileButtonsPressed();
			}
			
			if (menu_CURRENT == menu_OPTIONS) {		// <---- What methods are run on the Options Menu
				checkMouseOverBackButton();
				checkBackButtonPressed();
			}
			
			window.repaint();						// <---- Repainting the window every timer second	
		}
		
	}

//DRAWING METHODS
	void drawMainMenuTitle(Graphics2D g) {
		FontMetrics fontMetrics = g.getFontMetrics(mainMenuTitle);		// <---- Creates the FontMetrics
		g.setFont(mainMenuTitle);										// <---- Sets the Font
		g.setColor(White);												// <---- Sets the Color
		
		String title = "ASSASSINS";										// <---- Set the title to be drawn
		int titleX = WIN/2-fontMetrics.stringWidth(title)/2;			// <---- Sets the x coordinate of the title to make it centered
		
		g.drawString(title, titleX, WIN/4);								// <---- Draws the title
		
	}
	
	void drawMainMenuButtons(Graphics2D g) {
		
	//Draw Buttons
		g.setStroke(mainMenuButtonStroke);										// <---- Sets the stroke for the buttons
		Color ButtonOutline = new Color (140, 0, 0);
		Color ButtonFill = new Color (51, 0, 0);
		
		for (int i=0; i<mainButtons.length; i++) {								// <---- Draws all of the main menu buttons
			if (mainButtons[i].hovering) g.setColor(ButtonOutline);				// <---- If the mouse is hovering over, make the inside the same colour as the outline
			else g.setColor(ButtonFill);										// <---- If the mouse is not hovering over, make the inside and outline different colours
			
			g.fillRoundRect(mainButtons[i].x, mainButtons[i].y, mainButtons[i].width, mainButtons[i].height, mainButtons[i].arc, mainButtons[i].arc);	// <---- Draws the Inside Rectangle 
			g.setColor(ButtonOutline);																													// <---- Changes the color
			g.drawRoundRect(mainButtons[i].x, mainButtons[i].y, mainButtons[i].width, mainButtons[i].height, mainButtons[i].arc, mainButtons[i].arc);	// <---- Draws the Outline Rectangle
		}
		
		
	//Draw Button Text
		g.setColor(White);																					// <---- Sets the Color
		g.setFont(mainMenuButtons);																			// <---- Changes the Font
		FontMetrics fontMetrics = g.getFontMetrics(mainMenuButtons);										// <---- Makes a new FontMetrics
		
		String button1text = "NEW GAME";																	// <---- The Text for the Buttons
		String button2text = "LOAD GAME";
		String button3text = "OPTIONS";
		String button4text = "EXIT";
		
		int buttons1textX = (mainButton1.x+mainButton1.width/2)-(fontMetrics.stringWidth(button1text)/2);	// <---- The X values for the string, to make
		int buttons2textX = (mainButton2.x+mainButton2.width/2)-(fontMetrics.stringWidth(button2text)/2);	//		 the strings centered in the buttons
		int buttons3textX = (mainButton3.x+mainButton3.width/2)-(fontMetrics.stringWidth(button3text)/2);
		int buttons4textX = (mainButton4.x+mainButton4.width/2)-(fontMetrics.stringWidth(button4text)/2);
		int topButtonsTextY = (mainButton1.y+mainButton1.height/2)+(fontMetrics.getAscent()/2)-5;			// <---- The Y values for the top and bottom
		int bottomButtonsTextY = (mainButton3.y+mainButton1.height/2)+(fontMetrics.getAscent()/2)-5;		// 		 buttons
		
		g.drawString(button1text, buttons1textX, topButtonsTextY);			// <---- Draws all of the button text
		g.drawString(button2text, buttons2textX, topButtonsTextY);
		g.drawString(button3text, buttons3textX, bottomButtonsTextY);
		g.drawString(button4text, buttons4textX, bottomButtonsTextY);
		
	}
	
	void drawBackButton(Graphics2D g) {
		
		Color ButtonOutline = new Color (140, 0, 0);
		Color ButtonFill = new Color (51, 0, 0);
		g.setStroke(backButtonStroke);
		
		if (backButton.hovering) g.setColor(ButtonOutline);
		else g.setColor(ButtonFill);
		g.fillRoundRect(backButton.x, backButton.y, backButton.width, backButton.height, backButton.arc, backButton.arc);
		g.setColor(ButtonOutline);
		g.drawRoundRect(backButton.x, backButton.y, backButton.width, backButton.height, backButton.arc, backButton.arc);
	
		
		g.setColor(White);
		g.setFont(backButtonText);
		FontMetrics fontMetrics = g.getFontMetrics(backButtonText);
		
		String buttonText = "Back";
		int buttonTextX = (backButton.x+backButton.width/2)-(fontMetrics.stringWidth(buttonText)/2);
		int buttonTextY = (backButton.y+backButton.height/2)+(fontMetrics.getAscent()/2)-5;
		
		g.drawString(buttonText, buttonTextX, buttonTextY);
		
	}
	
	void drawFileMenuTitle(Graphics2D g, String title) {
		FontMetrics fontMetrics = g.getFontMetrics(fileMenuTitle);
		g.setFont(fileMenuTitle);
		g.setColor(White);	
		
		int titleX = WIN/2-fontMetrics.stringWidth(title)/2;
		g.drawString(title, titleX, WIN/4);
	}
	
	void drawFileMenuButtons(Graphics2D g) {
		
		g.setStroke(mainMenuButtonStroke);
		Color ButtonOutline = new Color (140, 0, 0);
		Color ButtonFill = new Color (51, 0, 0);
		
		for (int i=0; i<fileButtons.length; i++) {
			if (fileButtons[i].hovering) g.setColor(ButtonOutline);
			else g.setColor(ButtonFill);
			g.fillRoundRect(fileButtons[i].x, fileButtons[i].y, fileButtons[i].width, fileButtons[i].height, fileButtons[i].arc, fileButtons[i].arc);
			g.setColor(ButtonOutline);
			g.drawRoundRect(fileButtons[i].x, fileButtons[i].y, fileButtons[i].width, fileButtons[i].height, fileButtons[i].arc, fileButtons[i].arc);
		}
		
	}
	
	void drawFileMenuMessages(Graphics2D g) {
		
		FontMetrics fontMetrics = g.getFontMetrics(fileMenuTitle);
		g.setFont(fileMenuTitle);
		g.setColor(White);	
		
		if (displayCannotLoadEmptyMessage) {
			String str = "Cannot Load Empty File";
			int strX = WIN/2-fontMetrics.stringWidth(str)/2;
			int strY = 1100;
			
			g.drawString(str, strX, strY);
			
		}
		
		if (displayCannotOverwriteFileMessage) {
			String str = "Cannot Overwrite Save File";
			int strX = WIN/2-fontMetrics.stringWidth(str)/2;
			int strY = 1100;
			
			g.drawString(str, strX, strY);
			
		}
		
		
		
	}
	
	void drawFileMenuButtonsInformation(Graphics2D g) {

		g.setColor(White);
		g.setStroke(fileMenuFileTitleUnderlines);
		FontMetrics fMetricsTitle = g.getFontMetrics(fileMenuButtonsTitle);
		FontMetrics fMetricsText = g.getFontMetrics(fileMenuButtonsText);
		
		for (int i=0; i<3; i++) {									// <---- Goes through each of the 3 save files
			
			g.setFont(fileMenuButtonsTitle);
			
			if (saveFiles[i].empty) {								// <---- What to draw for an empty save file
				
				g.setFont(fileMenuButtonsText);
				String title = "EMPTY";
				int titleX = (fileButtons[i].x+(fileButtons[i].width/2))-(fMetricsText.stringWidth(title)/2);
				int titleY = (fileButtons[i].y+(fileButtons[i].height/2))-(fMetricsText.getHeight());
				g.drawString(title, titleX, titleY);
				
				continue;
			}
		
		//Draws the title
			String title = saveFiles[i].name;
			int titleX = (fileButtons[i].x+(fileButtons[i].width/2))-(fMetricsTitle.stringWidth(title)/2);
			int titleY = (fileButtons[i].y+WIN/100*6);
			g.drawString(title, titleX, titleY);
			
		//Draws a divider line
			g.drawLine(fileButtons[i].x+WIN/50, titleY+WIN/100, fileButtons[i].x+fileButtons[i].width-WIN/50, titleY+WIN/100);
			
		//Changes the Font
			g.setFont(fileMenuButtonsText);
			
		//Draws the HP 
			String hp = "HP: "+saveFiles[i].HP+"/"+saveFiles[i].maxHP;
			int hpX = (fileButtons[i].x+(fileButtons[i].width/2))-(fMetricsText.stringWidth(hp)/2);
			int hpY = (titleY+WIN/20);
			g.drawString(hp, hpX, hpY);
			
		//Draws the location
			String loc = saveFiles[i].location;
			int locX = (fileButtons[i].x+(fileButtons[i].width/2))-(fMetricsText.stringWidth(loc)/2);
			int locY = (hpY+WIN/20);
			g.drawString(loc, locX, locY);
			
		//Draws the progress
			String prog = "0%";
			int progX = (fileButtons[i].x+(fileButtons[i].width/2))-(fMetricsText.stringWidth(prog)/2);
			int progY = (locY+WIN/20);
			g.drawString(prog, progX, progY);
			
		}
		
	}
	
	void drawOptionsMenu(Graphics2D g) {
		
		FontMetrics fontMetrics = g.getFontMetrics(fileMenuButtonsTitle);
		g.setFont(fileMenuButtonsTitle);
		g.setColor(White);
		
		
		String str1 = "Sorry,";
		int str1X = WIN/2-fontMetrics.stringWidth(str1)/2;
		int str1Y = (int)(WIN/30.0*13);
		
		String str2 = "I didn't make any options :/";
		int str2X = WIN/2-fontMetrics.stringWidth(str2)/2;
		int str2Y = (int)(WIN/30.0*15);
		
		g.drawString(str1, str1X, str1Y);
		g.drawString(str2, str2X, str2Y);
		
	}
	
//SYSTEM METHODS
	
	void GUISetup() {
		window = new JFrame(gameTitle);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		smPanel.addMouseListener(this);
		smPanel.addMouseMotionListener(this);
		window.add(smPanel);
		window.pack();
		window.setVisible(true);
	}
	
	void readFileMenuFileInformation() {
		
		String fileNames[] = {"SAVE FILE 1", "SAVE FILE 2", "SAVE FILE 3"};						// <---- The names of all of the text files
		
		for (int i=0; i<fileButtons.length; i++) {						// <---- Goes through all of the Save File buttons
			
		//Create the Buffered Reader and Read the file
			BufferedReader brFile = null;
			try {
				brFile = new BufferedReader (new FileReader(new File(fileNames[i])));			// <---- Loads the current Save File's Text File
			} catch (FileNotFoundException ee) {
				System.out.println("TEXT FILES NOT FOUND");
			}
			
		//Process the text
			while (true) {
				try {
					String s = brFile.readLine();
					if (s==null) break;
					saveFiles[i].text.add(s);		// <---- Reads the text file and stores it in the save file
				} catch (IOException e) {}
			}
			
		}
	}

	void setFileMenuFileInformation() {
		
		String[] currentStr;
		
		for (int i=0; i<fileButtons.length; i++) {
			
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
			if (mainButton1.hovering) {
				M1 = false;
				menu_CURRENT = menu_NEWGAME;
			}
			if (mainButton2.hovering) {
				M1 = false;
				menu_CURRENT = menu_LOADGAME;
			}
			if (mainButton3.hovering) {
				M1 = false;
				menu_CURRENT = menu_OPTIONS;
			}
			if (mainButton4.hovering) System.exit(0);
		}
		
	}
	
	void checkMouseOverBackButton() {
		
		if (mouseX>=backButton.x && mouseX<=backButton.x+backButton.width) {
			if (mouseY>=backButton.y && mouseY<=backButton.y+backButton.height) backButton.hovering = true;
			else backButton.hovering = false;
		}
		else backButton.hovering = false;
		
	}
	
	void checkBackButtonPressed() {
		
		if (M1 && backButton.hovering) {
			menu_CURRENT = menu_MAIN;
			M1 = false;
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
		
		if (M1) {
			
			for (int i=0; i<saveFiles.length; i++) {
				
				if (menu_CURRENT == menu_NEWGAME) {
					
					if (fileButtons[i].hovering) {
						
						if (!saveFiles[i].empty) {
							displayMessage = System.currentTimeMillis();
							displayCannotOverwriteFileMessage = true;
							continue;
						}
						
						Main game = new Main();
						game.loadVars("Default", "Spawn Point", 10, 10, 0);
						startMenuTimer.stop();
						game.startGame();
						window.setVisible(false);
						
					}
					
				}
				
				
				if (menu_CURRENT == menu_LOADGAME) {
					
					if (fileButtons[i].hovering) {
						
						if (saveFiles[i].empty) {
							displayMessage = System.currentTimeMillis();
							displayCannotLoadEmptyMessage = true;
							continue;
						}
						
						Main game = new Main();
						game.loadVars(saveFiles[i].name, saveFiles[i].location, saveFiles[i].maxHP, saveFiles[i].HP, saveFiles[i].enemiesKilled);
						startMenuTimer.stop();
						game.startGame();
						window.setVisible(false);
					}
					
				}
				
			}
			
		}
		
		
		if (displayCannotLoadEmptyMessage) {
			if (System.currentTimeMillis()>=displayMessage+750) {
				displayCannotLoadEmptyMessage = false;
			}
			
		}
		
		if (displayCannotOverwriteFileMessage) {
			if (System.currentTimeMillis()>=displayMessage+750) {
				displayCannotOverwriteFileMessage = false;
			}
			
		}
		
		
	}
		
//Mouse Inputs	
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton()==MouseEvent.BUTTON1) M1 = true;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton()==MouseEvent.BUTTON1) M1 = false;
	}

//UNUSED METHODS
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mouseClicked(MouseEvent arg0) {}
}
