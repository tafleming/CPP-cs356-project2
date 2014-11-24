package edu.csupomona.cs356.project2;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class TreeView extends JPanel {
	
	private static final int width = 200;
	private static final int height = 400;
	private static TreeView instance;
	private  JTree userTree;
	
	private DefaultMutableTreeNode root;
	private DefaultTreeModel treeModel;
	private TreeView() {
		super();
		this.setPreferredSize(new Dimension(width, height));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		createTree();
		
	}
	
	public static TreeView getInstance() {
		if (instance == null) {
			instance = new TreeView();
		}
		
		return instance;
	}
	
	public IUserGroup getRootGroup() {
		IUserGroup group = (IUserGroup) root.getUserObject();
		return group;
	}
	private void createTree() {
		root  = new DefaultMutableTreeNode(new CompositeUserGroup("Root"));
		treeModel = new DefaultTreeModel(root);
		
		userTree = new JTree(treeModel);
		userTree.setShowsRootHandles(true);
		JScrollPane treeView = new JScrollPane(userTree);
		treeView.setPreferredSize(new Dimension(width - 2, height - 2));
		this.add(treeView);
	}
	
	public JTree getTree() {
		
		return userTree;
	}
	
	public DefaultTreeModel getModel() {
		return treeModel;
	}

}
