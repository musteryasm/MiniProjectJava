package com.views;

import java.awt.event.*;
import javax.swing.*;


public class Player extends JPanel implements ActionListener{
	
	private int money=0;
	private int xPos=0;
	private int yPos=0;
	
	private final Board board;

	public Player(Board board){
		this.board = board;
		xPos = 1;
		yPos = 1;
	}	
	

	public void actionPerformed(ActionEvent e){
		repaint();
	}
	

	public class MyKeyListener extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			if(e.getKeyChar() == 'a') 	moveLeft(board);
			if(e.getKeyChar() == 'w') 	moveUp(board);
			if(e.getKeyChar() == 'd')	moveRight(board);
			if(e.getKeyChar() == 's')	moveDown(board);
		
		}
	}
	

	public void moveLeft(Board board){
		if((board.get(xPos-1, yPos) != '#') && (board.get(xPos-1, yPos) != '=')){
			board.set(xPos, yPos, 'O');
			if(board.get(xPos-=1, yPos) == '8')	Win();
			else if(board.get(xPos, yPos) == '+') money++;
			else 	board.set(xPos, yPos, 'X');
		}
	}
	

	public void moveRight(Board board){
		if((board.get(xPos+1, yPos) != '#') && (board.get(xPos+1, yPos) != '=')){
			board.set(xPos, yPos, 'O');
			if(board.get(xPos+=1, yPos) == '8')	Win();
			else if(board.get(xPos, yPos) == '+') money++;
			else	board.set(xPos, yPos, 'X');
		}
	}
	

	public void moveUp(Board board){
		if((board.get(xPos, yPos-1) != '#') && (board.get(xPos, yPos-1) != '=')){
			board.set(xPos, yPos, 'O');
			if(board.get(xPos, yPos-=1) == '8')	Win();
			else if(board.get(xPos, yPos) == '+') money++;
			else	board.set(xPos, yPos, 'X');
		}
	}
	

	public void moveDown(Board board){
		if((board.get(xPos, yPos+1) != '#') && (board.get(xPos, yPos+1) != '=')){
			board.set(xPos, yPos, 'O');
			if(board.get(xPos, yPos+=1) == '8') Win();
			else if(board.get(xPos, yPos) == '+') money++;
			else	board.set(xPos, yPos, 'X');
		}
	}

	public void Win(){
        new MazeFrame(20, money);        
	}	
}	
