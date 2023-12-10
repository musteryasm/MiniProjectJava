package com.views;

import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu {
	
	JFrame frame = new JFrame("Maze");
	
	public MainMenu(){
		
		BoxLayout boxLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS); 
		
		Button play = new Button("Play");
		play.setFont(new Font("Verdana", Font.BOLD, 32));
		
		Button instruction = new Button("Instructions");
		instruction.setFont(new Font("Verdana", Font.BOLD, 32));
		
		frame.add(play); 
		frame.add(instruction);

		frame.setLayout(boxLayout);
		frame.setSize(500,500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);		
		
		play.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{	
				frame.dispose();
				new Difficulty();
			}
		});
		
		instruction.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{	
				JFrame frame = new JFrame("Instructions");
				frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				
				JLabel textLabel = new JLabel("" +"<html>" +
						"1. Player starts as the blue character.<br> " +
						"2. Player must reach the red endpoint to win.<br> " +
						"3. Get coins while making your way through the maze. <br>" +
						"4. Achieve a higher score.<br>" +
						"" +
						"5. Move the character by using the 'W-A-S-D' keyboard or <br>" +
						"by using the standard arrow keys to move.<br>" +
						"" +
						"6. Mouse clicks can also be used on the on-screen <br>directional buttons as well. </html>");
				frame.getContentPane().add(textLabel, BorderLayout.CENTER);
				
				frame.setLocationRelativeTo(null);
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
}
