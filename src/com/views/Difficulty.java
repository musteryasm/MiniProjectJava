package com.views;

import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;


public class Difficulty {

	public Difficulty(){
		
		final JFrame frame = new JFrame("Menu");	
		BoxLayout boxLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS); 
		
		Button easy = new Button("Easy");
		easy.setFont(new Font("Verdana", Font.BOLD, 32));
		
		Button medium = new Button("Medium");
		medium.setFont(new Font("Verdana", Font.BOLD, 32));
		
		Button hard = new Button("Hard");
		hard.setFont(new Font("Verdana", Font.BOLD, 32));
		
		frame.add(easy); 
		frame.add(medium);
		frame.add(hard);

		frame.setLayout(boxLayout);
		frame.setSize(500,500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);		
		
		easy.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{	
				frame.dispose();
				int easy = 10;
				new MazeFrame(easy);
			}
		});

		medium.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{	
				frame.dispose();
				int medium = 15;
				new MazeFrame(medium);
				
			}
		});
	
		hard.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{	
				frame.dispose();
				int hard = 20;
				new MazeFrame(hard);
			}
		});		
	}
}
