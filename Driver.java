package edu.csupomona.cs356.project2;

import javax.swing.JFrame;

public class Driver {
	
	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Mini Twitter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(AdminControlPanel.getInstance());
		
		//not resizable so the GUI won't get messed up 
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}
	public static void main (String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	          createAndShowGUI();
	        }
	    });
		
	}

}
