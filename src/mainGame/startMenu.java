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
	Font mainMenuButtons = new Font("Century", Font.BOLD, WIN/25);
	Font mainMenuTitle = new Font("Century", Font.BOLD, WIN/100*8);
	Font fileMenuTitle = new Font("Century", Font.BOLD, WIN/100*6);
	
//Start Menu Variables
	int menu_MAIN     = 1;
	int menu_NEWGAME  = 2;
	int menu_LOADGAME = 3;
	int menu_OPTIONS  = 4;
	int menu_CURRENT  = menu_MAIN;
	
//Menu Menu Button Variables
	int main_buttonHeight = WIN/10;
	int main_buttonWidth = main_buttonHeight*3;
	int main_buttonArc = WIN/100;
	int main_leftButtonsX = WIN/20*3;
	int main_rightButtonsX = WIN/20*11;
	int main_topButtonsY = WIN/20*9;
	int main_bottomButtonsY = WIN/10*6;
	boolean main_button1_hovering = false,
			main_button2_hovering = false,
			main_button3_hovering = false,
			main_button4_hovering = false;
	
//File Menu Buttons
	int file_buttonWidth = 250;
	int file_buttonHeight = 500;
	int file_buttonArc = 10;
	int file_buttonsY = 400;
	int file_button2X = (WIN/2)-(file_buttonWidth/2);
	int file_button1X = file_button2X-file_buttonWidth-(WIN/20);
	int file_button3X = file_button2X+file_buttonWidth+(WIN/20);
	boolean file_button1_hovering = false,
			file_button2_hovering = false,
			file_button3_hovering = false;
	
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
				drawFileMenuTitle(g, g2, "START A NEW GAME");
			}
			
			if (menu_CURRENT == menu_LOADGAME) {
				drawFileMenuButtons(g, g2);
				drawFileMenuTitle(g, g2, "LOAD A SAVED GAME");
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
			if (main_button1_hovering) g.setColor(ButtonOutline);
			else g.setColor(ButtonFill);
			g.fillRoundRect(main_leftButtonsX, main_topButtonsY, main_buttonWidth, main_buttonHeight, main_buttonArc, main_buttonArc);
			g.setColor(ButtonOutline);
			g.drawRoundRect(main_leftButtonsX, main_topButtonsY, main_buttonWidth, main_buttonHeight, main_buttonArc, main_buttonArc);
			
		//Draw Button #2
			if (main_button2_hovering) g.setColor(ButtonOutline);
			else g.setColor(ButtonFill);
			g.fillRoundRect(main_rightButtonsX, main_topButtonsY, main_buttonWidth, main_buttonHeight, main_buttonArc, main_buttonArc);
			g.setColor(ButtonOutline);
			g.drawRoundRect(main_rightButtonsX, main_topButtonsY, main_buttonWidth, main_buttonHeight, main_buttonArc, main_buttonArc);
			
		//Draw Button #3
			if (main_button3_hovering) g.setColor(ButtonOutline);
			else g.setColor(ButtonFill);
			g.fillRoundRect(main_leftButtonsX, main_bottomButtonsY, main_buttonWidth, main_buttonHeight, main_buttonArc, main_buttonArc);
			g.setColor(ButtonOutline);
			g.drawRoundRect(main_leftButtonsX, main_bottomButtonsY, main_buttonWidth, main_buttonHeight, main_buttonArc, main_buttonArc);
			
		//Draw Button #4
			if (main_button4_hovering) g.setColor(ButtonOutline);
			else g.setColor(ButtonFill);
			g.fillRoundRect(main_rightButtonsX, main_bottomButtonsY, main_buttonWidth, main_buttonHeight, main_buttonArc, main_buttonArc);
			g.setColor(ButtonOutline);
			g.drawRoundRect(main_rightButtonsX, main_bottomButtonsY, main_buttonWidth, main_buttonHeight, main_buttonArc, main_buttonArc);
			
	//Draw Button Text
		g.setColor(White);	
		g.setFont(mainMenuButtons);
		FontMetrics fontMetrics = g2.getFontMetrics(mainMenuButtons);	
		
		String button1text = "NEW GAME";
		String button2text = "LOAD GAME";
		String button3text = "OPTIONS";
		String button4text = "EXIT";
		
		int buttons1textX = (main_leftButtonsX+main_buttonWidth/2)-(fontMetrics.stringWidth(button1text)/2);
		int buttons2textX = (main_rightButtonsX+main_buttonWidth/2)-(fontMetrics.stringWidth(button2text)/2);
		int buttons3textX = (main_leftButtonsX+main_buttonWidth/2)-(fontMetrics.stringWidth(button3text)/2);
		int buttons4textX = (main_rightButtonsX+main_buttonWidth/2)-(fontMetrics.stringWidth(button4text)/2);
		int topButtonsTextY = (main_topButtonsY+main_buttonHeight/2)+(fontMetrics.getAscent()/2)-5;
		int bottomButtonsTextY = (main_bottomButtonsY+main_buttonHeight/2)+(fontMetrics.getAscent()/2)-5;
		
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
		if (file_button1_hovering) g.setColor(ButtonOutline);
		else g.setColor(ButtonFill);
		g.fillRoundRect(file_button1X, file_buttonsY, file_buttonWidth, file_buttonHeight, file_buttonArc, file_buttonArc);
		g.setColor(ButtonOutline);
		g.drawRoundRect(file_button1X, file_buttonsY, file_buttonWidth, file_buttonHeight, file_buttonArc, file_buttonArc);
		
	//Button 2
		if (file_button2_hovering) g.setColor(ButtonOutline);
		else g.setColor(ButtonFill);
		g.fillRoundRect(file_button2X, file_buttonsY, file_buttonWidth, file_buttonHeight, file_buttonArc, file_buttonArc);
		g.setColor(ButtonOutline);
		g.drawRoundRect(file_button2X, file_buttonsY, file_buttonWidth, file_buttonHeight, file_buttonArc, file_buttonArc);
	
	//Button 3
		if (file_button3_hovering) g.setColor(ButtonOutline);
		else g.setColor(ButtonFill);
		g.fillRoundRect(file_button3X, file_buttonsY, file_buttonWidth, file_buttonHeight, file_buttonArc, file_buttonArc);
		g.setColor(ButtonOutline);
		g.drawRoundRect(file_button3X, file_buttonsY, file_buttonWidth, file_buttonHeight, file_buttonArc, file_buttonArc);
	
	}
	
	
//SYSTEM METHODS
	void checkMouseOverMainButtons() {

	//Left Buttons
		if (mouseX>=main_leftButtonsX && mouseX<=main_leftButtonsX+main_buttonWidth) {
			if (mouseY>=main_topButtonsY && mouseY<=main_topButtonsY+main_buttonHeight) main_button1_hovering = true;
			else main_button1_hovering = false;
			
			if (mouseY>=main_bottomButtonsY && mouseY<=main_bottomButtonsY+main_buttonHeight) main_button3_hovering = true;
			else main_button3_hovering = false;
		}
		
		else {
			main_button1_hovering = false; 
			main_button3_hovering = false;
		}
		
	//Right Buttons
		if (mouseX>=main_rightButtonsX && mouseX<=main_rightButtonsX+main_buttonWidth) {
			if (mouseY>=main_topButtonsY && mouseY<=main_topButtonsY+main_buttonHeight) main_button2_hovering = true;
			else main_button2_hovering = false;
			
			if (mouseY>=main_bottomButtonsY && mouseY<=main_bottomButtonsY+main_buttonHeight) main_button4_hovering = true;
			else main_button4_hovering = false;
		}
		
		else {
			main_button2_hovering = false; 
			main_button4_hovering = false;
		}
	}

	void checkMainButtonsPressed() {
		
		if (M1) {
			if (main_button1_hovering) menu_CURRENT = menu_NEWGAME;
			if (main_button2_hovering) menu_CURRENT = menu_LOADGAME;
			if (main_button3_hovering) menu_CURRENT = menu_OPTIONS;
			if (main_button4_hovering) System.exit(0);
		}
		
	}
	
	void checkMouseOverFileButtons() {
	
		if (mouseY>=file_buttonsY && mouseY<=file_buttonsY+file_buttonHeight) {
			
		//Button 1
			if (mouseX>=file_button1X && mouseX<=file_button1X+file_buttonWidth) {
				file_button1_hovering = true;
			}
			else file_button1_hovering = false;
			
		//Button 2	
			if (mouseX>=file_button2X && mouseX<=file_button2X+file_buttonWidth) {
				file_button2_hovering = true;
			}
			else file_button2_hovering = false;
			
		//Button 3
			if (mouseX>=file_button3X && mouseX<=file_button3X+file_buttonWidth) {
				file_button3_hovering = true;
			}
			else file_button3_hovering = false;
		}
		else {
			file_button1_hovering = false;
			file_button2_hovering = false;
			file_button3_hovering = false;
		}
		
	}
	
	void checkFileButtonsPressed() {
		
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
