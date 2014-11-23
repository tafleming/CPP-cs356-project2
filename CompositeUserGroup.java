package edu.csupomona.cs356.project2;

import java.util.List;

public class CompositeUserGroup implements IUserGroup {

	private List<User> users;
	private List<IUserGroup> childGroups;
	private String groupName;
	private String id;
	public CompositeUserGroup(String name, String id) {
		groupName = name;
		this.id = id;
	}
	
	@Override
	public void addUser() {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeUser() {
		// TODO Auto-generated method stub

	}
	
	public void addGroup() {
		
	}
	public User getUser(String id) {
		return new User("k", "k");
	}
	
	@Override
	public String toString() {
		return groupName;
	}
}
