package edu.csupomona.cs356.project2;

import java.util.ArrayList;
import java.util.List;

public class UserGroup implements IUserGroup {

	private List<User> users;
	private String groupID;
	private String groupName;
	
	public UserGroup(String name, String id) {
		
		groupID = id;
		users = new ArrayList<User>();
	}
	
	@Override
	public void addUser(User user) {
		
	}
	
	@Override
	public void removeUser(String id){}
	
	@Override
	public String toString() {
		return "Group:" + groupID;
	}

	@Override
	public String getID() {
	
		return groupID;
	}

	@Override
	public void addGroup(IUserGroup group) {
		
	}
	
}
