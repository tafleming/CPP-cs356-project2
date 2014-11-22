package edu.csupomona.cs356.project2;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

public class TreeView extends JPanel {
	
	private static final int width = 200;
	private static final int height = 400;
	private JTree userTree;
	public TreeView() {
		super();
		this.setPreferredSize(new Dimension(width, height));
		createTree();
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	private void createTree() {
		DefaultMutableTreeNode root  = new DefaultMutableTreeNode("Top");
		userTree = new JTree(root);
		JScrollPane treeView = new JScrollPane(userTree);
		treeView.setPreferredSize(new Dimension(width - 2, height - 2));
		this.add(treeView);
	}
	
	//method(s) for adding users and groups to tree. A group will be a node, users children of the node.

}
