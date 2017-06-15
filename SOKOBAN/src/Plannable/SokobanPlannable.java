package Plannable;

import java.util.ArrayList;
import java.util.Set;

import Predicate.Action;
import Predicate.And;
import Predicate.Predicate;
import Predicate.SokobanPredicate;
import SearchLib.Bfs;
import SearchLib.CommonSearcher;
import SearchLib.SearchAction;
import common.Level;
import model.data.Position;
import sokobanSearch.BoxSearchable;
import sokobanSearch.CommonSearchable;
import stripsLib.Plannable;

public class SokobanPlannable implements Plannable {

	private PredicateLevelBuilder stripsLevel;
	private Level level;
	private And kb;
	private And boxes;
	private CommonSearcher searcher;
	private CommonSearchable searchable;
	
	char[][] levelForTest={ {'#','#','#'},
							{'#','O','#'},
							{'#',' ','#'},
							{'#','@','#'},
							{'#',' ','#'},
							{'#','A','#'},
							{'#','#','#'}};/////for testing
	
	
	
	public SokobanPlannable(PredicateLevelBuilder stripsLevel, Level level) {////add Level level    this.level=level
	this.stripsLevel=stripsLevel;
	this.level = level;
	this.kb=stripsLevel.getKB(level.getCharMat());
	this.boxes=getBoxesFromKnowledgebase();
	searcher = new Bfs();
	searchable = new BoxSearchable(level.getCharMat());
	}
	
	@Override
	public And getGoal() {
		
		return stripsLevel.getGoal(kb);
		
	}

	@Override
	public And getKnowledgebase() {
	//	kb=stripsLevel.getKB(level.getCharMat());
	//	addToKB();
		return kb;
	}
	
	
	public And getBoxesFromKnowledgebase(){
		boxes = new And();
		for(Predicate p:kb.getPredicates())
			if (p.getName().equals("boxAt"))
				boxes.add(p);
		return boxes;
	}
	
	public void addToKB(){
		Set<Predicate> tempSet=	this.kb.getPredicates();
		And temp=getGoal();
		Predicate pr=null;
		for(Predicate p:tempSet){
			if ((p.getName().equals("boxAt")))
				if(!(p.getPos().equals(temp.getPos())))
					 pr=new SokobanPredicate("clearTargetAt",p.getId(),p.getPos());
					
				
		}
		kb.add(pr);
	}

//	@Override
//	public ArrayList<Action> getWAntedActions(Predicate top) {
//		
//		List <String> fromBfs=new ArrayList<>();//change to List <searceAction>
//		ArrayList <Action> actions=new ArrayList<Action>();
//		boxes=getBoxesFromKnowledgebase();
//		
//		if (top.getName().equals("boxAt"))////case the unsatisfied predicate is box
//			for(Predicate pr:boxes.getPredicates())//array of boxes
//				for (Predicate p:kb.getPredicates())//the KB
//					if (p.getPos().equals(pr.getPos())){
//						//if (fromBfs=bfs(pr.getPos(),top.getPos())){//// add top.getPosToPositionClass 
//						
//							for (String s:fromBfs)
//								actions.add(new Action(s, "soko",top.getPos()));///s.get string
//							//to update teh lkevek in searceablle
//								return actions;///return here because if we have one match is ok
//								
//					}
//		
//		return  actions;
//	}

	@Override
	public Action getWantedAction(Predicate top) {	// (sokoAt,soko1,1,3) example of unsatisfeid predicate
		
		
		ArrayList <SearchAction> fromSearch;//change to List <searceAction>
		Action action;
		boxes=getBoxesFromKnowledgebase();
		
		if (top.getName().equals("boxAt"))////case the unsatisfied predicate is box
			for(Predicate pr:boxes.getPredicates()){ //array of boxes
				for (Predicate p:kb.getPredicates())
					//the KB
					if (p.getPos().equals(pr.getPos())){
						
						searchable.setPositions(PosToPosition(pr.getPos()), PosToPosition(top.getPos()));	
						if((fromSearch = searcher.search(searchable))!=null){
	
						action=new Action("moveBox", pr.getId(), top.getPos(),fromSearch,pr.getPos());
					
							//to update the level in searceablle // source=pr.pos ,dest= top.pos
						
						searchable.UpdateBoard(PosToPosition(action.sokoPos()), PosToPosition(pr.getPos()), PosToPosition(top.getPos()));
						searchable.printBoard();
					fromSearch = null;
					
								return action;///return here because if we have one match is ok
						}
						}
						
		}
		return null;
		
		
		
		
		/*															//(boxAt, " ","2,1")
		if(top.getId().equals("?"))
			top.setId("b1");
		
		Action action=new Action("Move", top.getId(), top.getPos(), sourcePos);
		return action;
		*/
	}

	public String getPosFromKnowledgebase(String id){ //top.id
		Set<Predicate> tempSet=	this.kb.getPredicates();
		for(Predicate p:tempSet){
			if (p.getId().equals(id))
				return p.getPos();
		}
		
		return null;
	}
	
	public Position PosToPosition(String pos){
		//Position newPos;
		String[] tempPosArray;
		tempPosArray=pos.split(",");
		Integer x=new Integer(tempPosArray[0]);
		Integer y=new Integer(tempPosArray[1]);
		return new Position(x,y);
		}

	@Override
	public ArrayList<Action> getWAntedActions(Predicate top) {
		// TODO Auto-generated method stub
		return null;
	}

	
}