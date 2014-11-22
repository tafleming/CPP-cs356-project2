package edu.csupomona.cs356.project2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {
	
	private static final int width = 400;
	private static final int height = 100;

	public InfoPanel() {
		super();
		this.setPreferredSize(new Dimension(width, height));
		
		this.setLayout(new GridLayout(2,2,15,15));
		addButtons();
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	private void addButtons() {
		String[] strings = {"Show User Total", "Show Group Total", "Show Message Total", "Show Positive Percentage"};
		for (int i = 0; i < 4; i++) {
			this.add(new JButton(strings[i]));
		}
	}
}