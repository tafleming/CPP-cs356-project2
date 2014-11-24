package edu.csupomona.cs356.project2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import java.util.ArrayList;


import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;

public class UserViewPanel extends JPanel {

	private static final int width = 300;
	private static final int height = 300;
	private JTextArea userIDArea;
	private JTextArea newMessage;
	private JButton postMessage;
	private JButton followUser;
	private JList<String> currentFollowings; // add to scrollpane
	private JList<String> newsFeed;
	private JScrollPane newsFeedPane;
	private JScrollPane followingsPane;
	private JScrollPane newMessagePane;
	private User currentUser;
	private DefaultListModel<String> listModelFollowings;
	private DefaultListModel<String> listModelNewsFeed;

	public UserViewPanel() {
		super();
		this.setPreferredSize(new Dimension(width, height));
		setUser(UserControlPanel.getCurrentUser());
		initializeLists();
		initializeComponents();
		addComponents();
		addActionListeners();
	}

	private void addComponents() {
		add(userIDArea);
		add(followUser);
		followingsPane = new JScrollPane(currentFollowings);
		followingsPane.setPreferredSize(new Dimension(280, 100));
		add(followingsPane);
		add(newMessage);
		newMessagePane = new JScrollPane(newMessage);
		newMessagePane.setFocusable(true);
		newMessagePane.setPreferredSize(new Dimension(150, 50));
		add(newMessagePane);
		add(postMessage);
		newsFeedPane = new JScrollPane(newsFeed);
		newsFeedPane.setPreferredSize(new Dimension(280, 100));
		add(newsFeedPane);

	}

	private void initializeComponents() {
		userIDArea = new JTextArea("User ID to follow");
		userIDArea.setBorder(BorderFactory.createLineBorder(Color.black));
		userIDArea.setPreferredSize(new Dimension(125, 20));
		newMessage = new JTextArea("Type message here");
		newMessage.setLineWrap(true);
		newMessage.setWrapStyleWord(true);

		postMessage = new JButton("Post Tweet");
		followUser = new JButton("Follow user");
	}

	protected void initializeLists() {
		listModelFollowings = new DefaultListModel<String>();
		listModelNewsFeed = new DefaultListModel<String>();
		currentFollowings = new JList<String>(listModelFollowings);
		//currentFollowings.setSelectedIndex(0);
		listModelFollowings.addElement("Current Followings: ");
		ArrayList<User> followings = (ArrayList<User>) currentUser
				.getFollowings();
		for (int i = 0; i < followings.size(); i++) {
			listModelFollowings.addElement(followings.get(i).getID());
		}

		newsFeed = new JList<String>(listModelNewsFeed);
		listModelNewsFeed.addElement("News Feed: ");
		ArrayList<String> userNewsFeed = (ArrayList<String>) currentUser.getNewsFeed();
		for (String str : userNewsFeed) {
			listModelNewsFeed.addElement(str);
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
				ArrayList<String> currentFeed = (ArrayList<String>) currentUser.getNewsFeed();
				listModelNewsFeed.addElement(currentFeed.get(currentFeed.size() - 1));
			}

		});

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
						//it seems the following lines of code should update the view. but they don't :(
						//the view is updated if a new window for the user is opened
						listModelFollowings.insertElementAt(userIDtoFollow, 1);
						currentFollowings.setSelectedIndex(1);
						currentFollowings.ensureIndexIsVisible(1);

					}
				}
			}
		});

	}

	public void setUser(User user) {
		currentUser = user;

	}
}
