package edu.csupomona.cs356.project2;

import javax.swing.JFrame;

public class Driver {
	
	public static void main (String[] args) {
		
		JFrame frame = new JFrame("Mini Twitter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(AdminControlPanel.getInstance());
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		
		
		
	}

}
