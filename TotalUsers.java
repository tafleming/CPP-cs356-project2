package edu.csupomona.cs356.project2;

public class TotalUsers implements IVisitableElement {

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);

	}

}
