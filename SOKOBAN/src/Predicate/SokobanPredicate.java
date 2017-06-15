package Predicate;

public class SokobanPredicate extends PredicateReg {

	public SokobanPredicate(String name, String id, String pos) {
		super(name, id, pos);
	}

	@Override
	public boolean contradicts(Predicate pr) {
		return super.contradicts(pr) || (!id.equals(pr.id) && pos.equals(pr.pos));
	}

}
