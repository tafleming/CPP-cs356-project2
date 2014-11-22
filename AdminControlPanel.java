package edu.csupomona.cs356.project2;

import java.awt.Dimension;
import java.awt.GridLayout;


import java.awt.Insets;

import javax.swing.BoxLayout;
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
		this.add(new UserControlPanel());
		this.add(new InfoPanel());
		
		//using no layout manager for top level panel
		this.setLayout(null);
		this.getComponent(0).setBounds(0, 0, 200, 400);
		this.getComponent(1).setBounds(200, 0, 400, 200);
		this.getComponent(2).setBounds(200, 300, 400, 100);
		
	}
	
	public static AdminControlPanel getInstance() {
		if (instance == null) {
			instance = new AdminControlPanel();
		}
		
		return instance;
	}
	
	public void getCounts(){}
}
