package mainGame;

import java.util.ArrayList;

public class SaveFiles {

//Variables
	boolean empty;
	String name, location;
	int maxHP, HP, enemiesKilled;
	ArrayList<String> text;
	
	SaveFiles(){
		this.empty = true;
		this.name = null;
		this.location = null;
		this.maxHP = 0;
		this.HP = 0;
		this.enemiesKilled = 0;
		this.text = new ArrayList<String>();
	}
	
}
