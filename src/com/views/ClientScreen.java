package com.views;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.UnknownHostException;

import com.network.Client;
import com.utils.UserInfo;


public class ClientScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private Client client;

	public static void main(String[] args)
	{
		try
		{
			ClientScreen frame = new ClientScreen();
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void sendMessage()
	{
		String message = textField.getText();
		try
		{
			client.sendMessage(UserInfo.user_name + " : " + message + "\n\n\n");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public ClientScreen() throws UnknownHostException, IOException
	{
		setTitle("CHATTING APPLICATION");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClientScreen.class.getResource("/Images/ii8.png")));
		setFont(new Font("Arial Black", Font.BOLD, 25));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setForeground(Color.BLACK);
		setBounds(100, 100, 1471, 820);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel contentPane_1 = new JPanel();
		contentPane_1.setBounds(0, 0, 1543, 793);
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(190,250,251));
		contentPane.add(contentPane_1);

		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(1012, 10, 502, 678);
		contentPane_1.add(scrollPane);

		textArea = new JTextArea();
		textArea.setFont(new Font("Arial", Font.PLAIN, 15));
		textArea.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(190,250,251)));
		textArea.setBounds(1012,10, 495, 673);
		scrollPane.setViewportView(textArea);

		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 15));
		textField.setBounds(1012, 697, 397, 55);
		contentPane_1.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Send");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setCursor(cursor);
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton.setBounds(1420, 697, 99, 55);
		contentPane_1.add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				sendMessage();
				textField.setText("");
			}
		});

		client = new Client(textArea);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Options");
		mnNewMenu.setFont(new Font("Arial", Font.PLAIN, 14));
		menuBar.add(mnNewMenu);

		JMenuItem logout = new JMenuItem("Sign Out");
		logout.setBackground(Color.WHITE);
		logout.setOpaque(true);
		logout.setHorizontalAlignment(SwingConstants.LEADING);
		logout.setFont(new Font("Arial", Font.PLAIN, 13));
		mnNewMenu.add(logout);
		logout.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});

		JLabel welcomeLabel = new JLabel("Welcome to mini games!");
		welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 40));
		welcomeLabel.setForeground(Color.BLACK);
		welcomeLabel.setBounds(250, 20, 600, 50);
		contentPane_1.add(welcomeLabel);

		ImageIcon imageIcon = new ImageIcon("src/com/views/snake.png");
		Image scaledImage = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		JLabel imageLabel = new JLabel(scaledIcon);
		imageLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Snake.main(null);
			}
		});
		imageLabel.setBounds(200,150,200,200);
		contentPane_1.add(imageLabel);

		JLabel lb1 = new JLabel("Snake Game");
		lb1.setFont(new Font("Arial", Font.PLAIN, 20));
		lb1.setForeground(Color.BLACK);
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		lb1.setBounds(200,350,200,50);
		contentPane_1.add(lb1);


		ImageIcon imageIcon2 = new ImageIcon("src/com/views/maze.png");
		Image scaledImage2 = imageIcon2.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
		JLabel imageLabel2 = new JLabel(scaledIcon2);
		imageLabel2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MazeGUI.main(null);
			}
		});
		imageLabel2.setBounds(600,150,200,200);
		contentPane_1.add(imageLabel2);

		JLabel lb2 = new JLabel("Maze Runner");
		lb2.setFont(new Font("Arial", Font.PLAIN, 20));
		lb2.setForeground(Color.BLACK);
		lb2.setHorizontalAlignment(SwingConstants.CENTER);
		lb2.setBounds(600,350,200,50);
		contentPane_1.add(lb2);


		ImageIcon imageIcon3 = new ImageIcon("src/com/views/flappy.png");
		Image scaledImage3 = imageIcon3.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon3 = new ImageIcon(scaledImage3);
		JLabel imageLabel3 = new JLabel(scaledIcon3);
		imageLabel3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FlappyBird.main(null);
			}
		});
		imageLabel3.setBounds(200,500,200,200);
		contentPane_1.add(imageLabel3);

		JLabel lb3 = new JLabel("Flappy Bird");
		lb3.setFont(new Font("Arial", Font.PLAIN, 20));
		lb3.setForeground(Color.BLACK);
		lb3.setHorizontalAlignment(SwingConstants.CENTER);
		lb3.setBounds(200,700,200,50);
		contentPane_1.add(lb3);

		ImageIcon imageIcon4 = new ImageIcon("src/com/views/piano.png");
		Image scaledImage4 = imageIcon4.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon4 = new ImageIcon(scaledImage4);
		JLabel imageLabel4 = new JLabel(scaledIcon4);
		imageLabel4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DontTouchTheWhiteTile.main(null);
			}
		});
		imageLabel4.setBounds(600,500,200,200);
		contentPane_1.add(imageLabel4);

		JLabel lb4 = new JLabel("Piano Tiles");
		lb4.setFont(new Font("Arial", Font.PLAIN, 20));
		lb4.setForeground(Color.BLACK);
		lb4.setHorizontalAlignment(SwingConstants.CENTER);
		lb4.setBounds(600,700,200,50);
		contentPane_1.add(lb4);
		setVisible(true);
	}
}