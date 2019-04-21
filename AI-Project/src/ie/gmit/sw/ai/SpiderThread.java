package ie.gmit.sw.ai;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;

public class SpiderThread extends Thread{

	ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
	
	// keep enemy pos
	private int[] pos = new int[2];
	private char val;
	
	private int venom = 10;
	private int strength = 0;
	
	SpiderThread(int[] p, char v, int s){
		
		// values for conditions
		this.pos = p;
		this.val= v;
		this.strength = s;
		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		
	}
	/*
	 * 
	 * 
	 * 
	 * 
	 */

	
	public String getSpidertype() {
		
		switch(val) {
		case '6':
			return "Black";
		case '7':
			return "Blue";
		case '8':
			return "Brown";
		case '9':
			return "Green";
		case ':':
			return "Grey";
		case ';':
			return "Orange";
		case '<':
			return "Red";
		case '=':
			return "Yellow";
		default:
			return "";
		}
	}
	/*
	 * 
	 * 
	 * 
	 */


	public void findPlayer() throws Exception {
		
		GameView.getInstance();
		GameView.getMaze();
		
		char[][] matrix = Maze.getMaze();
		
		searchNode node = null;

		move(pos[0], pos[1], node, val);
		
	}
    // make spiders move 
	public void move(int row, int col, searchNode node, char val) throws Exception {
		
		GameView.getInstance();
		
		int newRow = node.x;
		int newCol = node.y;
		
		GameView.setMaze(pos[0], pos[1], '\u0020');
		GameView.setMaze(newRow, newCol, val);

		pos[0] = newRow;
		pos[1]= newCol;
		
	}
	
	
	// getters/setters
	public int[] getPos() {
		return pos;
	}
	
	public void setPos(int[] pos) {
		this.pos = pos;
	}

	public char getVal() {
		return val;
	}


	public void setVal(char val) {
		this.val = val;
	}

	public int getVenom() {
		return venom;
	}

	public void setVenom(int venom) {
		this.venom = venom;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

}