package edu.csupomona.cs356.project2;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class User extends Observable implements Observer  {

	private String id;
	private String name;
	private List<User> followers; //observers
	private List<User> followings; //observed
	private String groupId;
	//private String currentTWeet
	//private list tweethistory;
	public User(String name, String id) {
		this.name = name;
		this.id = id;
		
	}
	public void follow(String id) {
		addObserver(AdminControlPanel.getUser(id));
	}
	
	public void unfollow(String id) {
		deleteObserver(AdminControlPanel.getUser(id));
	}
	
	public void postMessage(String msg) {
		//currentTweet = msg
		//tweethistory.add(msg)
		//setChanged
		//notifyObservers(msg)
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		//newsFeed.add(arg0.getname + arg1(the message))
	}
	
	
}
