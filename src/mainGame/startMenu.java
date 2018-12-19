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

import javax.swing.*;

public class startMenu implements KeyListener, MouseListener, MouseMotionListener {

// --------------------------------------	
// ---------- Global Variables ----------
// --------------------------------------
	
//Random Variables
	String gameTitle = "Action Game";
	
	boolean buttonHovers[] = {false, false, false, false};
	
//JFrame and JWindow Creations
	final static int WIN = 2000;
	static JFrame window;
	StartMenuPanel smPanel = new StartMenuPanel();
	
//Colors, Font, and Strokes
	Color White = new Color (255, 255, 255);
	Color Blue = new Color (0, 0, 255);
	Color Red = new Color (255, 0, 0);
	Color Green = new Color(0, 255, 0);
	Color Black = new Color (0, 0, 0);
	BasicStroke mainMenuButtonStroke = new BasicStroke(WIN/100);
	Font mainMenuButtons = new Font("Century", Font.BOLD, WIN/25);
	Font mainMenuTitle = new Font("Century", Font.BOLD, WIN/25*2);
	
//Start Menu Variables
	int menu_MAIN     = 1;
	int menu_NEWGAME  = 2;
	int menu_LOADGAME = 3;
	int menu_OPTIONS  = 4;
	int menu_CURRENT  = menu_MAIN;
	
//Menu Button Variables
	int buttonHeight = WIN/10;
	int buttonWidth = buttonHeight*3;
	int buttonArc = WIN/100;
	int leftButtonsX = WIN/20*3;
	int rightButtonsX = WIN/20*11;
	int topButtonsY = WIN/20*9;
	int bottomButtonsY = WIN/10*6;
	
//Input Variables
	int mouseX, mouseY;
	boolean M1 = false;
	
//Timers
	int tSpeed = 1;
	Timer startMenuTimer;
	
	public static void main(String[] args) {new startMenu();}
	
	startMenu() {
		GUISetup();
		
		startMenuTimer = new Timer(tSpeed, new StartMenuTimer());
		startMenuTimer.start();
	}
	
	void GUISetup(){
		window = new JFrame(gameTitle);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		smPanel.addMouseListener(this);
		smPanel.addMouseMotionListener(this);
		window.add(smPanel);
		window.pack();
		window.setVisible(true);
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
			}
			
		
		}
	
	}

//Start Menu Timer
	private class StartMenuTimer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			
			if (menu_CURRENT == menu_MAIN) {
				checkMouseOverButtons();
				checkButtonsPressed();
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
			if (buttonHovers[0]) g.setColor(ButtonOutline);
			else g.setColor(ButtonFill);
			g.fillRoundRect(leftButtonsX, topButtonsY, buttonWidth, buttonHeight, buttonArc, buttonArc);
			g.setColor(ButtonOutline);
			g.drawRoundRect(leftButtonsX, topButtonsY, buttonWidth, buttonHeight, buttonArc, buttonArc);
			
		//Draw Button #2
			if (buttonHovers[1]) g.setColor(ButtonOutline);
			else g.setColor(ButtonFill);
			g.fillRoundRect(rightButtonsX, topButtonsY, buttonWidth, buttonHeight, buttonArc, buttonArc);
			g.setColor(ButtonOutline);
			g.drawRoundRect(rightButtonsX, topButtonsY, buttonWidth, buttonHeight, buttonArc, buttonArc);
			
		//Draw Button #3
			if (buttonHovers[2]) g.setColor(ButtonOutline);
			else g.setColor(ButtonFill);
			g.fillRoundRect(leftButtonsX, bottomButtonsY, buttonWidth, buttonHeight, buttonArc, buttonArc);
			g.setColor(ButtonOutline);
			g.drawRoundRect(leftButtonsX, bottomButtonsY, buttonWidth, buttonHeight, buttonArc, buttonArc);
			
		//Draw Button #4
			if (buttonHovers[3]) g.setColor(ButtonOutline);
			else g.setColor(ButtonFill);
			g.fillRoundRect(rightButtonsX, bottomButtonsY, buttonWidth, buttonHeight, buttonArc, buttonArc);
			g.setColor(ButtonOutline);
			g.drawRoundRect(rightButtonsX, bottomButtonsY, buttonWidth, buttonHeight, buttonArc, buttonArc);
			
	//Draw Button Text
		g.setColor(White);	
		g.setFont(mainMenuButtons);
		FontMetrics fontMetrics = g2.getFontMetrics(mainMenuButtons);	
		
		String button1text = "NEW GAME";
		String button2text = "LOAD GAME";
		String button3text = "OPTIONS";
		String button4text = "EXIT";
		
		int buttons1textX = (leftButtonsX+buttonWidth/2)-(fontMetrics.stringWidth(button1text)/2);
		int buttons2textX = (rightButtonsX+buttonWidth/2)-(fontMetrics.stringWidth(button2text)/2);
		int buttons3textX = (leftButtonsX+buttonWidth/2)-(fontMetrics.stringWidth(button3text)/2);
		int buttons4textX = (rightButtonsX+buttonWidth/2)-(fontMetrics.stringWidth(button4text)/2);
		int topButtonsTextY = (topButtonsY+buttonHeight/2)+(fontMetrics.getAscent()/2)-5;
		int bottomButtonsTextY = (bottomButtonsY+buttonHeight/2)+(fontMetrics.getAscent()/2)-5;
		
		//Draw Button Text #1
			g.drawString(button1text, buttons1textX, topButtonsTextY);
			g.drawString(button2text, buttons2textX, topButtonsTextY);
			g.drawString(button3text, buttons3textX, bottomButtonsTextY);
			g.drawString(button4text, buttons4textX, bottomButtonsTextY);
		
	}
	
	void drawFileMenuButtons(Graphics g, Graphics2D g2) {
		
		g2.setStroke(mainMenuButtonStroke);
		Color ButtonOutline = new Color (140, 0, 0);
		Color ButtonFill = new Color (51, 0, 0);
		
		int ButtonsY = 400; 
		
		g.setColor(ButtonFill);
		g.fillRoundRect(0, ButtonsY, 500, 500, 10, 10);
		g.setColor(ButtonOutline);
		g.drawRoundRect(0, ButtonsY, 500, 500, 10, 10);
		
		
	}
	
	
	
	
	
//SYSTEM METHODS
	void checkMouseOverButtons() {

	//Left Buttons
		if (mouseX>=leftButtonsX && mouseX<=leftButtonsX+buttonWidth) {
			if (mouseY>=topButtonsY && mouseY<=topButtonsY+buttonHeight) buttonHovers[0] = true;
			else buttonHovers[0] = false;
			
			if (mouseY>=bottomButtonsY && mouseY<=bottomButtonsY+buttonHeight) buttonHovers[2] = true;
			else buttonHovers[2] = false;
		}
		
		else {
			buttonHovers[0] = false; 
			buttonHovers[2] = false;
		}
		
	//Right Buttons
		if (mouseX>=rightButtonsX && mouseX<=rightButtonsX+buttonWidth) {
			if (mouseY>=topButtonsY && mouseY<=topButtonsY+buttonHeight) buttonHovers[1] = true;
			else buttonHovers[1] = false;
			
			if (mouseY>=bottomButtonsY && mouseY<=bottomButtonsY+buttonHeight) buttonHovers[3] = true;
			else buttonHovers[3] = false;
		}
		
		else {
			buttonHovers[1] = false; 
			buttonHovers[3] = false;
		}
	}

	void checkButtonsPressed() {
		
		if (M1) {
			if (buttonHovers[0]) menu_CURRENT = menu_NEWGAME;
			if (buttonHovers[1]) menu_CURRENT = menu_LOADGAME;
			if (buttonHovers[2]) menu_CURRENT = menu_OPTIONS;
			if (buttonHovers[3]) System.exit(0);
		}
		
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
