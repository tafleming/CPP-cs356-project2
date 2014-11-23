package edu.csupomona.cs356.project2;

public interface IVisitor {

	public void visit(TotalUsers totalUsers);
	
	public void visit(TotalGroups totalGroups);
	
	public void visit(TotalMessages totalMessages);
	
	public void visit(PositiveMessages positiveMessages);
	
}
