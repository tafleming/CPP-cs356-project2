package edu.csupomona.cs356.project2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

public class User extends Observable implements Observer  {

	private String id;
	private List<User> followers; //observers
	private List<User> followings; //observed
	private final long date;
	private long lastUpdate;
	private List<String> newsFeed;
	
	public User(String id) {
		this.id = id;
		date = System.currentTimeMillis();
		lastUpdate = date; //initial update will be when user is created
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
			lastUpdate = System.currentTimeMillis();
			AdminControlPanel.getInstance().setUpdate(lastUpdate);
			setChanged();
			notifyObservers(msg);
			newsFeed.add(id + ": " + msg);
	}
	

	@Override
	public void update(Observable arg0, Object arg1) {
		//lastUpdate = System.currentTimeMillis();
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
	
	private String getFormattedTime(long time) {
		Date realDate = new Date(time);
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String stringDate = format.format(realDate);
		return stringDate;
	}
	public String getDate() {
		String stringDate = getFormattedTime(date);
		return stringDate;
	}
	
	public String getUpdate() {
		String stringDate = getFormattedTime(lastUpdate);
		return stringDate;
	}

	public long getUpdateLong() {
		
		return lastUpdate;
	}
}
