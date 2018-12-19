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

public class Main implements KeyListener, MouseListener, MouseMotionListener {

// --------------------------------------	
// ---------- Global Variables ----------
// --------------------------------------
	
//Random Variables
	String gameTitle = "Action Game";
	
	boolean buttonHovers[] = {false, false, false, false};
	
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
	Font startMenuButtons = new Font("Century", Font.BOLD, 40);
	Font startMenuTitle = new Font("Century", Font.BOLD, 80);
	
//Menu Button Variables
	int buttonWidth = 300;
	int buttonHeight = 100;
	int buttonArc = 10;
	int leftButtonsX = 150;
	int rightButtonsX = 550;
	int topButtonsY = 450;
	int bottomButtonsY = 600;
	
//Input Variables
	int mouseX, mouseY;
	boolean M1 = false;
	
//Timers
	int tSpeed = 1;
	Timer startMenuTimer;
	
	public static void main(String[] args) {new Main();}
	
	Main() {
		GUISetup();
		
		
		
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
			
			BasicStroke ButtonStroke = new BasicStroke(10);
			g2.setStroke(ButtonStroke);
			
			drawButtons(g, g2);
			drawTitle(g, g2);
		
		}
	
	}

	private class StartMenuTimer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			checkMouseOverButtons();
			checkButtonsPressed();
			
			window.repaint();
		}
		
	}

	void drawTitle(Graphics g, Graphics2D g2) {
		FontMetrics fontMetrics = g2.getFontMetrics(startMenuTitle);
		g.setFont(startMenuTitle);
		g.setColor(White);	
		
		String title = "ASSASSINS";
		int titleX = WIN/2-fontMetrics.stringWidth(title)/2;
		
		g.drawString(title, titleX, 250);
		
	}
	
	void drawButtons(Graphics g, Graphics2D g2) {
		
	//Draw Background	
		g.setColor(Black);
		g.fillRect(0, 0, WIN, WIN);
		
	//Draw Buttons
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
		g.setFont(startMenuButtons);
		FontMetrics fontMetrics = g2.getFontMetrics(startMenuButtons);	
		
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
