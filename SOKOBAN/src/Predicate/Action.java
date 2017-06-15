package Predicate;

import java.util.ArrayList;

import SearchLib.SearchAction;

public class Action extends PredicateReg {
	// boolean state;
	// protected String sourcePos;
	private And preCondition;
	protected And effect;
	protected ArrayList<SearchAction> theRealMoves;
	protected String sourcePos;
	// name (move,push)
	// id=(box-b1,b2..., sokoban)

	public Action(String name, String id, String pos, ArrayList<SearchAction> theRealMoves, String sourcePos) {
		super(name, id, pos);
		this.theRealMoves = theRealMoves;
		this.sourcePos = sourcePos;
		setPreConditions();
		setEffects();

	}

	public void printRealMoves() {

		System.out.println("this is itereation");
		for (SearchAction a : theRealMoves)
			System.out.println(a);
		System.out.println("end of iteration");
	}

	public ArrayList<SearchAction> getTheRealMoves() {
		return theRealMoves;
	}

	@Override
	public boolean isSatisfied(Predicate pr) {

		return preCondition.isSatisfied(pr);

	}

	public void setEffects() {/////// maybe need to get a prdicate
		effect = new And();
		// System.out.println(" in set effect and the id is:"+this.id);

		if (this.id.startsWith("b")) {
			effect.add(new SokobanPredicate("boxOnTargetAt", id, pos));
			effect.add(new SokobanPredicate("clearAt", "", sourcePos));
			effect.add(new SokobanPredicate("sokobanAt", "soko", sokoPos()));

		} else {

		}
		// effect.add(new SokobanPredicate("boxAt", id, thelast move));

	}

	public String sokoPos() {
		int i = theRealMoves.size();

		String lastMove = theRealMoves.get(i - 1).getAction();

		switch (lastMove) {
		case "Move up": {

			return createNewPos(pos, "D");
		}

		case "Move down": {
			return createNewPos(pos, "U");
		}

		case "Move left": {
			return createNewPos(pos, "R");
		}

		case "Move right": {
			return createNewPos(pos, "L");

		}

		}
		return null;
	}

	public void setPreConditions() {
		preCondition = new And();
		preCondition.add(new SokobanPredicate("boxAt", id, sourcePos));

	}

	public String createNewPos(String pos, String direction) {/// the diraction
																/// could be "U"
																/// or "D" or
																/// "L" or "R"
		String newPos;
		String[] tempPosArray;
		tempPosArray = pos.split(",");

		if (direction.equals("U")) {

			Integer n = new Integer(tempPosArray[0]);

			n = n - 1;
			tempPosArray[0] = n.toString();

		}
		if (direction.equals("D")) {

			Integer n = new Integer(tempPosArray[0]);

			n = n + 1;
			tempPosArray[0] = n.toString();
		}
		if (direction.equals("R")) {
			Integer n = new Integer(tempPosArray[1]);

			n = n + 1;
			tempPosArray[1] = n.toString();
		}
		if (direction.equals("L")) {
			Integer n = new Integer(tempPosArray[1]);

			n = n - 1;
			tempPosArray[1] = n.toString();

		}

		newPos = (tempPosArray[0] + "," + tempPosArray[1]);
		return newPos;
	}

	public And getPreconditions() {
		// System.out.println(preCondition+"in getprecondition");
		return preCondition;
	}

	public And getEffect() {
		// System.out.println(effect);
		return effect;
	}

}
