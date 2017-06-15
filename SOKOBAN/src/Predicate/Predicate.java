package Predicate;

public abstract class Predicate {

	
	protected String name;//the name of the predicate (wallAt,sokoAt,clearAt,boxAt,targetAt)
	protected String id; 
	protected String pos;	//("0,1")

	public Predicate(String name, String id, String pos) {
		this.name = name;
		this.id=id;
		this.pos = pos;
	}
	
	public abstract boolean isSatisfied(Predicate pr);
	public abstract boolean contradicts(Predicate pr);

	
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPos() {
		return pos;
	}
	
	public void setPos(String pos) {
		this.pos = pos;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
}
