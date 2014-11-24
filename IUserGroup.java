package edu.csupomona.cs356.project2;

public interface IUserGroup {

	public abstract void addUser(User user);

	public abstract void removeUser(String id);

	public abstract String getID();

	public abstract void addGroup(IUserGroup group);

}