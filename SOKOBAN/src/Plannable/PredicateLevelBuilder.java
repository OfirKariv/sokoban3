package Plannable;

import java.util.ArrayList;

import Predicate.And;
import Predicate.Predicate;
import Predicate.PredicateReg;
import Predicate.SokobanPredicate;

public class PredicateLevelBuilder {

	static int boxCount=0;
	static int targetCount=0;
	
	public static And getKB(char[][] level){
		And kb=new And(null);
		for(int i=0;i<level.length;i++){
			for(int j=0;j<level[0].length;j++){
				switch(level[i][j]){
				case '#':kb.add(new SokobanPredicate("wallAt", "wall", i+","+j));break;
				case ' ':kb.add(new SokobanPredicate("clearAt", "__", i+","+j));break;
				case 'A':kb.add(new SokobanPredicate("sokobanAt", "soko", i+","+j));break;
				case '@':boxCount++;kb.add(new SokobanPredicate("boxAt", "b"+boxCount, i+","+j));break;
				case 'O':targetCount++;kb.add(new SokobanPredicate("targetAt", "t"+targetCount, i+","+j));break;
				}
			}
		}
		
		return kb;
	}
	
	public static And getGoal(And kb){
		And goal=new And();
		for(Predicate p : kb.getPredicates()){
			if(((PredicateReg) p).getName().startsWith("tar")){
				goal.add(new SokobanPredicate("boxAt", "?", ((PredicateReg) p).getPos()));
			}
		}
		return goal;		
	}
	
	
	
	
}
