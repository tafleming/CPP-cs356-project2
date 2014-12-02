package edu.csupomona.cs356.project2;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
//import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.sun.org.apache.xerces.internal.util.Status;

@SuppressWarnings("serial")
public class AdminControlPanel extends JPanel {

	private static AdminControlPanel instance = null;
	//keep track of all the users and groups for the analysis features
	private List<User> allUsers;
	private List<IUserGroup> allGroups;
	private long lastUpdate;
	private static final int WIDTH = 601;
	private static final int HEIGHT = 400;
	
	private AdminControlPanel() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		this.add(TreeView.getInstance());
		this.add(UserControlPanel.getInstance());
		this.add(new InfoPanel());
		
		//using no layout manager for top level panel to make life easier
		this.setLayout(null);
		this.getComponent(0).setBounds(0, 0, 200, 400);
		this.getComponent(1).setBounds(200, 0, 400, 200);
		this.getComponent(2).setBounds(200, 300, 400, 100);
		
		allUsers = new ArrayList<User>();
		allGroups = new ArrayList<IUserGroup>();
		addRoot();
		
	}
	
	public static AdminControlPanel getInstance() {
		if (instance == null) {
			instance = new AdminControlPanel();
		}
		
		return instance;
	}
	
	public void getCounts(){}
	
	public User getUser(String id) {
		for(int i = 0; i < allUsers.size(); i++) {
			if (allUsers.get(i).getID().equals(id)) {
				return allUsers.get(i);
			}
				
		}
		
		JOptionPane.showMessageDialog(null, "There is no user with that ID!");
	
		return null;
	}

	public boolean addUser(User newUser, JTextArea status) {
		for (int i =0; i < allUsers.size(); i++) {
			if (allUsers.get(i).getID().equals(newUser.getID())) {
				status.setText("A user with that ID already exists.");
				return false;
			}
		}
		allUsers.add(newUser);
		return true;
	}
	
	public boolean addGroup(IUserGroup group, JTextArea status) {
		for (int i = 0; i < allGroups.size(); i++) {
			if (allGroups.get(i).getID().equals(group.getID())) {
				status.setText("A group with that ID already exists.");
				return false;
			}
		}
		allGroups.add(group);
		return true;
		
	}
	
	private void addRoot() {
		allGroups.add(TreeView.getInstance().getRootGroup());
	}
	public List<User> getUsers() {
		return allUsers;
	}

	public List<IUserGroup> getGroups() {
		return allGroups;
	}

	public void setUpdate(long lastUpdate) {
		this.lastUpdate = lastUpdate;
		
	}
	
	public User getUpdate() {
		for (User user: allUsers) {
			if (lastUpdate == user.getUpdateLong()) {
				return user;
				
			}
		}
		return null;
	}
	
}
