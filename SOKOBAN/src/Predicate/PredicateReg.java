package Predicate;

public class PredicateReg extends Predicate {
	
	
	
	public PredicateReg(String name, String id, String pos) {
		super(name,id,pos);
	
	}
	
	
	
	@Override
	public boolean isSatisfied(Predicate pr) {// getting predicate from the knowlegebase and if its equals it return true
		
		return (name.equals(pr.name) && (id.equals(pr.id) || pr.id.equals("?")) && pos.equals(pr.pos));
	}
	
	
	public boolean contradicts(Predicate pr) {	//chek if predicate contradicts the knowledgebase 	
		return (name.equals(pr.name) && id.equals(pr.id) && !pos.equals(pr.pos));
	
	}
	
	@Override
	public int hashCode(){
		return (name+id+pos).hashCode();
	}
	
	@Override
	public String toString(){
		return name+"_"+id+"="+pos;
	}
	
	public boolean equals(Predicate pr){
		return (name.equals(pr.name) && id.equals(pr.id) && pos.equals(pr.pos));
	}
	
	public String getName(){
		return name;
	}
	public String getPos(){
		return pos;
	}
	public String getId(){
		return id;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
 