package edu.csupomona.cs356.project2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		addActionListenersToButtons();
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	private void addButtons() {
		String[] strings = {"Show User Total", "Show Group Total", "Show Message Total", "Show Positive Percentage"};
		for (int i = 0; i < 4; i++) {
			this.add(new JButton(strings[i]));
		}
	}
	
	private void addActionListenersToButtons() {
		//user total
		JButton button1 = (JButton) this.getComponent(0);
		//grouptotal
		JButton button2 = (JButton) this.getComponent(1);
		//messagetotal
		JButton button3 = (JButton) this.getComponent(2);
		//positive
		JButton button4 = (JButton) this.getComponent(3);
		
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				TotalUsers total = new TotalUsers();
				InfoVisitor visitor = new InfoVisitor();
				visitor.visit(total);
			}
			
		});
		
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				TotalGroups total = new TotalGroups();
				InfoVisitor visitor = new InfoVisitor();
				visitor.visit(total);
			}
			
		});
		
		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				TotalMessages total = new TotalMessages();
				InfoVisitor visitor = new InfoVisitor();
				visitor.visit(total);
			}
			
		});
		
		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				PositiveMessages positive = new PositiveMessages();
				InfoVisitor visitor = new InfoVisitor();
				visitor.visit(positive);
			}
			
		});
	}
}