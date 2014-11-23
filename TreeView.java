package edu.csupomona.cs356.project2;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

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
	private void createTree() {
		root  = new DefaultMutableTreeNode("Root"/*new CompositeUserGroup("Root", "0")*/);
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
	
	
	//method(s) for adding users and groups to tree. A group will be a node, users children of the node.

}
