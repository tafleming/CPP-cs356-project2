package edu.csupomona.cs356.project2;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JTextArea;

public class InfoVisitor implements IVisitor {

	@Override
	public void visit(TotalUsers totalUsers) {
		ArrayList<User> users = (ArrayList<User>) AdminControlPanel.getInstance().getUsers();
		JTextArea statusMessage = UserControlPanel.getStatusField();
		statusMessage.setText("Total number of users is " + users.size());
	}

	@Override
	public void visit(TotalGroups totalGroups) {
		ArrayList<IUserGroup> groups = (ArrayList<IUserGroup>) AdminControlPanel.getInstance().getGroups();
		JTextArea statusMessage = UserControlPanel.getStatusField();
		statusMessage.setText("Total number of groups is " + groups.size());

	}

	@Override
	public void visit(TotalMessages totalMessages) {
		ArrayList<User> users = (ArrayList<User>) AdminControlPanel.getInstance().getUsers();
		JTextArea statusMessage = UserControlPanel.getStatusField();
		int total = 0;
		for (User user : users) {
			ArrayList<String> newsFeed = (ArrayList<String>) user.getNewsFeed();
			
			total += newsFeed.size();
		}
		
		statusMessage.setText("Total number of tweets is " + total);
	}

	@Override
	public void visit(PositiveMessages positiveMessages) {
		ArrayList<String> theWords = PositiveWordsFileReader.getInstance().getTheWords();
		ArrayList<User> users = (ArrayList<User>) AdminControlPanel.getInstance().getUsers();
		ArrayList<String> theMessages = new ArrayList<String>();
		JTextArea statusMessage = UserControlPanel.getStatusField();
		int total = 0;
		int totalSize = 0;
		for (User user : users) {
			//get all the newsFeeds
			ArrayList<String> newsFeed = (ArrayList<String>) user.getNewsFeed();
			totalSize += newsFeed.size();
			for (String str : newsFeed) {
					theMessages.add(str);
				}
			}
		//all the messages from all users should be in theMessages now
		//now to match up with theWords
		for (int i = 0; i < theWords.size(); i++) {
			for (int j = 0; j  < theMessages.size(); j++) {
				String currentMessage = theMessages.get(j);
				String currentWord = theWords.get(i);
				
				if(currentMessage.contains(currentWord)) {
					total++;
				}
			}
		}
		
		String pattern = "###.##";
		DecimalFormat formatter = new DecimalFormat(pattern);
		
		double percentage = ((1.0 * total) / totalSize) * 100;
		String formatted = formatter.format(percentage);
	
		statusMessage.setText("Total Percentage of positive words is " + formatted + "%");
	}

}
