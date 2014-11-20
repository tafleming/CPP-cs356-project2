package edu.csupomona.cs356.project2;

import java.awt.Dimension;

import java.awt.GridLayout;

//import java.awt.FlowLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AdminControlPanel extends JPanel {

	private static AdminControlPanel instance = null;
	private static final int WIDTH = 600;
	private static final int HEIGHT = 400;
	
	private AdminControlPanel() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		this.add(new TreeView());
		this.setLayout(new GridLayout());
	}
	
	public static AdminControlPanel getInstance() {
		if (instance == null) {
			instance = new AdminControlPanel();
		}
		
		return instance;
	}
	
	public void getCounts(){}
}
