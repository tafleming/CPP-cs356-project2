package edu.csupomona.cs356.project2;

import java.util.List;

public class UserGroup implements IUserGroup {

	private List<User> users;
	
	private String groupID;
	private String groupName;
	public UserGroup(String name, String id) {
		groupName = name;
		groupID = id;
	}
	/* (non-Javadoc)
	 * @see edu.csupomona.cs356.project2.IUserGroup#addUser()
	 */
	@Override
	public void addUser(){}
	/* (non-Javadoc)
	 * @see edu.csupomona.cs356.project2.IUserGroup#removeUser()
	 */
	@Override
	public void removeUser(){}
	
	@Override
	public String toString() {
		return groupName;
	}
	
}
