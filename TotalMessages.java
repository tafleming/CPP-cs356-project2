package edu.csupomona.cs356.project2;

public class TotalMessages implements IVisitableElement {

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);

	}

}
