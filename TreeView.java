package edu.csupomona.cs356.project2;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TreeView extends JPanel {
	
	private static final int width = 200;
	private static final int height = 400;
	
	public TreeView() {
		super();
		this.setPreferredSize(new Dimension(width, height));
		this.add(new JButton());
	}
}
