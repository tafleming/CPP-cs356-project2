package edu.csupomona.cs356.project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

public class User extends Observable implements Observer  {

	private String id;
	private List<User> followers; //observers
	private List<User> followings; //observed
	
	private List<String> newsFeed;
	
	public User(String id) {
		this.id = id;
		followers = new ArrayList<User>();
		followings = new ArrayList<User>();
		newsFeed = new ArrayList<String>();
	}
	
	public String getID() {
		return id;
	}
	public boolean addFollowing(String id) {
		if (this.id.equals(id)) {
			JOptionPane.showMessageDialog(null, "You don't need to follow yourself!");
			return false;
		}
		User userToFollow = AdminControlPanel.getInstance().getUser(id);
		for (User user : followings) {
			if (user.getID().equals(id)) {
				JOptionPane.showMessageDialog(null, "You are already following that user.");
				return false;
			}
		}
		userToFollow.addObserver(this);
		followings.add(userToFollow);
		userToFollow.addFollower(this);
		
		return true;
	}
	
	public void addFollower(User user) {
		followers.add(user);
	}
	
	public void unfollow(String id) {
		deleteObserver(AdminControlPanel.getInstance().getUser(id));
	}
	
	public void postMessage(String msg) {
			setChanged();
			notifyObservers(msg);
			newsFeed.add(id + ": " + msg);
	}
	

	@Override
	public void update(Observable arg0, Object arg1) {
		newsFeed.add(((User) arg0).getID() + ": " + arg1.toString());
	}
	
	@Override
	public String toString() {
		return "User: " + id;
	}

	public List<User> getFollowings() {
		
		return followings;
	}

	public List<String> getNewsFeed() {
		return newsFeed;
	}
	
}
