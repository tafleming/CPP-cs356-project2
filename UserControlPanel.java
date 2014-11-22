package edu.csupomona.cs356.project2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class UserControlPanel extends JPanel {
	
	private static final int width = 400;
	private static final int height = 200;

	public UserControlPanel() {
		super();
		this.setPreferredSize(new Dimension(width, height));
		addButtons();
		addTextAreas();
		this.add(new JButton("Open User View"));
		this.setLayout(new GridLayout(3,2, 25, 25));
		//this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	private void addButtons() {
		JButton user = new JButton("Add User");
		JButton group = new JButton("Add Group");
		this.add(user);
		this.add(group);
	}
	
	private void addTextAreas(){
		JTextArea userID = new JTextArea("displayID");
		JTextArea groupID = new JTextArea("groupID");
		userID.setBorder(BorderFactory.createLineBorder(Color.black));
		groupID.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(userID);
		this.add(groupID);
	}
}