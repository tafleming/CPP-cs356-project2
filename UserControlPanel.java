package edu.csupomona.cs356.project2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class UserControlPanel extends JPanel {

	private static final int width = 410;
	private static final int height = 200;
	private JButton user;
	private JButton group;
	private JButton userView;
	private JButton validateIDs;
	private JButton displayUpdate;
	private JTextField userID;
	private JTextField groupID;
	private static JTextArea statusMessage;
	private TreeView treeView;
	
	private static User currentUser;
	private static UserControlPanel instance;
	
	private UserControlPanel() {
		super();
		this.setPreferredSize(new Dimension(width, height));
		addButtons();
		addTextAreas();
		treeView = TreeView.getInstance();
		userView = new JButton("Open User View");
		this.add(userView);
		this.add(validateIDs);
		this.add(displayUpdate);
		this.add(statusMessage);
		addActionListenersToButtons();
		this.setLayout(new GridLayout(4, 2, 25, 25));
		// this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	public static UserControlPanel getInstance() {
		if (instance == null) {
			instance = new UserControlPanel();
		}
		
		return instance;
	}
	private void addButtons() {
		user = new JButton("Add User");
		group = new JButton("Add Group");
		validateIDs = new JButton("Validate IDs");
		displayUpdate = new JButton("Last Update");
		this.add(user);
		this.add(group);
	}

	private void addTextAreas() {
		userID = new JTextField("Enter new User ID");
		groupID = new JTextField("Enter new Group ID");
		userID.setBorder(BorderFactory.createLineBorder(Color.black));
		groupID.setBorder(BorderFactory.createLineBorder(Color.black));
		//a status message field that shows when at certain times what happened
		statusMessage = new JTextArea("Welcome to Mini-Twitter.");
		statusMessage.setEditable(false);
		statusMessage.setLineWrap(true);
		statusMessage.setWrapStyleWord(true);
		this.add(userID);
		this.add(groupID);
	}
	
	public static User getCurrentUser() {
		return currentUser;
	}
	
	private void addActionListenersToButtons() {
		userView.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				DefaultMutableTreeNode currentNode = getTreeSelection();
				Object obj = currentNode.getUserObject();
				//cant open a user view if a user is not selected
				if (obj instanceof IUserGroup) {
					statusMessage.setText("Select a user in the tree to open the user view.");
					return;
				}
				
				currentUser = (User) obj;
				UserViewPanel viewPanel = new UserViewPanel();
				//viewPanel.setUser((User) obj);
				//viewPanel.initializeLists();
				
				JFrame frame = new JFrame(obj.toString());
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.getContentPane().add(viewPanel.getPanel());
				frame.setResizable(false);
				frame.pack();
				frame.setVisible(true);		
			}
		});

		group.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String newGroupID = groupID.getText();
				if (newGroupID.length() > 2) {
					IUserGroup newGroup = new CompositeUserGroup(newGroupID);
					boolean success = AdminControlPanel.getInstance().addGroup(
							newGroup, statusMessage);
					if (success) {
						addObject(newGroup);
					}
				} else {
					statusMessage
							.setText("Group ID too short. Minimum 3 characters.");
				}
			}

		});

		user.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String newUserID = userID.getText();

				if (newUserID.length() > 2) {
					User newUser = new User(newUserID);
					//adds a user even if the user is not added to tree later. Bug!
					boolean success = AdminControlPanel.getInstance().addUser(
							newUser, statusMessage);
					if (success) {
						addObject(newUser);
					}
				} else {
					statusMessage
							.setText("User ID too short. Minimum 3 characters.");
				}
			}
		});
		
		userID.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				// clears the text when you click on the field
				userID.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
			}
		});
		
		groupID.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				// clears the text when you click on the field
				groupID.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
			}
		});
		
		validateIDs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(checkIDs()) {
					statusMessage.setText("There are invalid IDs among the users/groups.");
				} else {
					statusMessage.setText("There are no invalid IDs.");
				}
			}
			
		});
		
		displayUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			User lastUpdater = AdminControlPanel.getInstance().getUpdate();
			if (lastUpdater != null) {
				statusMessage.setText("Last Update: " + lastUpdater.getID());
			} else {
				statusMessage.setText("There have been no updates.");
			}
				
			}
			
		});
	}
	
	private boolean checkIDs() {
		ArrayList<User> users = (ArrayList<User>)AdminControlPanel.getInstance().getUsers();
		ArrayList<IUserGroup> groups = (ArrayList<IUserGroup>)AdminControlPanel.getInstance().getGroups();
		for (User user : users) {
			if (user.getID().contains(" ")) {
				return true;
				
			}
		}
		
		for (IUserGroup group : groups) {
			if (group.getID().contains(" ")) {
				return true;
				
			}
		}
		return false;
	}
	//parts of the following code were taken from the Java Swing Tutorials on JTrees.
	public void addObject(Object child) {
		
		DefaultMutableTreeNode parentNode = getTreeSelection();
		Object obj = parentNode.getUserObject();
		//this is just to make things easier(for me)
		if (obj instanceof User) {
			statusMessage.setText("Select a group to add a new user/group.");
			return;
		}
		if (child instanceof IUserGroup) {
			((IUserGroup) obj).addGroup((IUserGroup) child);
		} else {
			((IUserGroup) obj).addUser((User) child);
		}
		DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
		treeView.getModel().insertNodeInto(childNode, parentNode,
				parentNode.getChildCount());
		treeView.getTree().scrollPathToVisible(
				new TreePath(childNode.getPath()));

	}
	
	private DefaultMutableTreeNode getTreeSelection() {
		DefaultMutableTreeNode parentNode = null;
		TreePath parentPath = treeView.getTree().getSelectionPath();

		if (parentPath == null) {
			// There is no selection. Default to the root node. avoid null pointers
			parentNode = (DefaultMutableTreeNode) treeView.getModel().getRoot();
		} else {
			parentNode = (DefaultMutableTreeNode) (parentPath
					.getLastPathComponent());
		}
		
		return parentNode;
	}

	public static JTextArea getStatusField() {
		return statusMessage;
	}
}