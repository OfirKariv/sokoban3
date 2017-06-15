package Predicate;
import java.util.HashSet;
import java.util.Set;

public class And extends PredicateReg {

	//to override contradict(And and)
	
	private HashSet<Predicate> complexPr;
	
	

	public And(Predicate...complexPr){
		super("And","","");
		if (complexPr!=null){
			this.complexPr=new HashSet<>();
			for(Predicate pr:complexPr)
				this.complexPr.add(pr);
			updateDescription();
		}
	}
	
	private void updateDescription(){
	pos="{";
		for(Predicate pr : complexPr){
			pos+=pr.toString()+" & ";
		}
		pos+="}";
	}
	
	@Override
	public boolean isSatisfied(Predicate pr) {
		for (Predicate p: complexPr)
			if (p.isSatisfied(pr))
				return true;
		return false;
	}

	public boolean isSatisfied(And and){
		for(Predicate pr : and.complexPr){
			if(!isSatisfied(pr))
				return false;
		}
		return true;
	}

	
	public void update(And effects){
		effects.complexPr.forEach((Predicate p)->complexPr.removeIf((Predicate pr)->p.contradicts(pr)));
		complexPr.addAll(effects.complexPr);
		updateDescription();
	}

	/////i  know way /////
	public void add(Predicate pr){
		if (complexPr==null)
			complexPr=new HashSet<>();
		this.complexPr.add(pr);
		updateDescription();
	}

	public Set<Predicate> getPredicates() {
		return complexPr;
	}
/*
	@Override
	public boolean contradicts(Predicate pr) {
		System.out.println("the contradicts in AND class is working--not good");
		return false;
	}
	*/

}
