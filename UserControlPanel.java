package edu.csupomona.cs356.project2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class UserControlPanel extends JPanel {

	private static final int width = 400;
	private static final int height = 200;
	private JButton user;
	private JButton group;
	private JButton userView;
	private JTextArea userID;
	private JTextArea groupID;
	private TreeView treeView;

	public UserControlPanel() {
		super();
		this.setPreferredSize(new Dimension(width, height));
		addButtons();
		addTextAreas();
		treeView = TreeView.getInstance();
		userView = new JButton("Open User View");
		this.add(userView);
		addActionListenersToButtons();
		this.setLayout(new GridLayout(3, 2, 25, 25));
		// this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	private void addButtons() {
		user = new JButton("Add User");
		group = new JButton("Add Group");
		this.add(user);
		this.add(group);
	}

	private void addTextAreas() {
		userID = new JTextArea("displayID");
		groupID = new JTextArea("groupID");
		userID.setBorder(BorderFactory.createLineBorder(Color.black));
		groupID.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(userID);
		this.add(groupID);
	}

	private void addActionListenersToButtons() {
		userView.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame = new JFrame("User View");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.getContentPane().add(new UserViewPanel());
				frame.setResizable(false);
				frame.pack();
				frame.setVisible(true);
			}

		});

		group.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				addObject("test");
				//more control stuff here for a new group.
				//add a user or a group.
			}

		});
	}

	public void /* DefaultMutableTreeNode */addObject(Object child) {
		DefaultMutableTreeNode parentNode = null;
		TreePath parentPath = treeView.getTree().getSelectionPath();

		if (parentPath == null) {
			// There is no selection. Default to the root node.
			parentNode = (DefaultMutableTreeNode) treeView.getModel().getRoot();
		} else {
			parentNode = (DefaultMutableTreeNode) (parentPath
					.getLastPathComponent());
		}
		DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
		treeView.getModel().insertNodeInto(childNode, parentNode,
				parentNode.getChildCount());
		treeView.getTree().scrollPathToVisible(
				new TreePath(childNode.getPath()));
		// return //addObject(parentNode, child, true);

	}

	/*
	 * public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
	 * Object child, boolean shouldBeVisible) { DefaultMutableTreeNode childNode
	 * = new DefaultMutableTreeNode(child);
	 * 
	 * treeView.getModel().insertNodeInto(childNode, parent,
	 * parent.getChildCount());
	 * 
	 * if (shouldBeVisible) { treeView.getTree().scrollPathToVisible(new
	 * TreePath(childNode.getPath())); } return childNode; }
	 */
}