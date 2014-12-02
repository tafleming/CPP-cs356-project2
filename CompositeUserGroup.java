package edu.csupomona.cs356.project2;

import java.util.ArrayList;
import java.util.List;

public class CompositeUserGroup implements IUserGroup {

	private List<User> users;
	private List<IUserGroup> childGroups;
	private final long date;
	private String id;
	public CompositeUserGroup( String id) {

		this.id = id;
		date = System.currentTimeMillis();
		users = new ArrayList<User>();
		childGroups = new ArrayList<IUserGroup>();
	}
	
	@Override
	public void addUser(User user) {
		users.add(user);
	}

	@Override
	public void removeUser(String id) {
		//didnt have time to implement this

	}
	
	public void addGroup(IUserGroup group) {
		childGroups.add(group);
	}
	
	
	@Override
	public String toString() {
		return "Group:" + id;
	}

	@Override
	public String getID() {
		
		return id;
	}
}
