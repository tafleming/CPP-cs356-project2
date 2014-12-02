package edu.csupomona.cs356.project2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;

public class UserViewPanel extends Observable implements Observer {

	private static final int width = 300;
	private static final int height = 350;
	private JPanel panel;
	private JTextArea userIDArea;
	private JTextArea newMessage;
	private JButton postMessage;
	private JButton followUser;
	private JLabel dateLabel;
	private JLabel updateLabel;
	//private JList<String> currentFollowings; // add to scrollpane
	//private JList<String> newsFeed;
	private JScrollPane newsFeedPane;
	private JScrollPane followingsPane;
	private JScrollPane newMessagePane;
	private User currentUser;
	//private DefaultListModel<String> listModelFollowings;
	//private DefaultListModel<String> listModelNewsFeed;
	
	//simple text areas to replace the clunky lists.
	private JTextArea followingsArea;
	private JTextArea newsFeedArea;
	
	public UserViewPanel() {
		//super();
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(width, height));
		setUser(UserControlPanel.getCurrentUser());
		currentUser.addObserver(this);
		initializeTextAreas();
		initializeComponents();
		addOtherUserViews();
		addComponents();
		addActionListeners();
	}
	
	private void addComponents() {
		panel.add(userIDArea);
		panel.add(followUser);
		panel.add(dateLabel);
		panel.add(updateLabel);
		followingsPane = new JScrollPane(followingsArea);
		followingsPane.setPreferredSize(new Dimension(280, 100));
		panel.add(followingsPane);
		panel.add(newMessage);
		newMessagePane = new JScrollPane(newMessage);
		newMessagePane.setFocusable(true);
		newMessagePane.setPreferredSize(new Dimension(150, 50));
		panel.add(newMessagePane);
		panel.add(postMessage);
		newsFeedPane = new JScrollPane(newsFeedArea);
		newsFeedPane.setPreferredSize(new Dimension(280, 100));
		panel.add(newsFeedPane);

	}

	private void initializeComponents() {
		userIDArea = new JTextArea("User ID to follow");
		userIDArea.setBorder(BorderFactory.createLineBorder(Color.black));
		userIDArea.setPreferredSize(new Dimension(125, 20));
		newMessage = new JTextArea("Type message here");
		newMessage.setLineWrap(true);
		newMessage.setWrapStyleWord(true);
		dateLabel = new JLabel("Date created: " + currentUser.getDate());
		updateLabel = new JLabel("Last update: " + currentUser.getUpdate());
		postMessage = new JButton("Post Tweet");
		followUser = new JButton("Follow user");
	}
	
	//adds any open user views to this panel for observer
	private void addOtherUserViews() {
		ArrayList<User> followings = (ArrayList<User>) currentUser
				.getFollowings();
		for (User user : followings) {
			user.addObserver(this);
		}
	}
	private void initializeTextAreas() {
		followingsArea = new JTextArea();
		followingsArea.append("Current Followings: \n");
		newsFeedArea = new JTextArea();
		newsFeedArea.append("News Feed: \n");
		ArrayList<User> followings = (ArrayList<User>) currentUser
				.getFollowings();
		for (int i = 0; i < followings.size(); i++) {
			followingsArea.append("-" + followings.get(i).getID() + "\n");
		}
		
		ArrayList<String> userNewsFeed = (ArrayList<String>) currentUser.getNewsFeed();
		for (String str : userNewsFeed) {
			newsFeedArea.append(str + "\n");
		}
		
	}
	private void addActionListeners() {
		newMessage.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				// clears the text when you click on the field
				newMessage.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
			}
		});

		postMessage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String msg = newMessage.getText();
				//keep true to the real twitter limits
				if (msg.length() > 140) {
					JOptionPane.showMessageDialog(null,
							"Maximum message length is 140 characters.");
					return;
				}
				//dont let them post an empty message
				if (msg.length() < 1) {
					JOptionPane.showMessageDialog(null,
							"Please input a message to post.");
					return;
				}
				
				currentUser.postMessage(msg);
			}

		});
		
		userIDArea.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				// clears the text when you click on the field
				userIDArea.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
			}
		});
		
		final UserViewPanel thisPanel = this;
		followUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String userIDtoFollow = userIDArea.getText();
				if (AdminControlPanel.getInstance().getUser(userIDtoFollow) != null) {
					boolean success = currentUser.addFollowing(userIDtoFollow);
					if (success) {
						JOptionPane.showMessageDialog(null,
								"Now following the user: " + userIDtoFollow
										+ "!");
						followingsArea.append("-" + userIDtoFollow + "\n");
						User newFollowing = AdminControlPanel.getInstance().getUser(userIDtoFollow);
						newFollowing.addObserver(thisPanel);
					}
				}
			}
		});

	}

	public void setUser(User user) {
		currentUser = user;

	}
	
	public JPanel getPanel() {
		return panel;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		updateLabel.setText("Last Update: " + ((User)arg0).getUpdate());
		newsFeedArea.append(((User) arg0).getID() + ": " + ((String) arg1) + "\n");
		
	}
}
