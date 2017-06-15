package boot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Plannable.PredicateLevelBuilder;
import Plannable.SokobanPlannable;
import Predicate.Action;
import SearchLib.SearchAction;
import common.Level;
import model.calc.MyTextLevelLoader;
import stripsLib.Plannable;
import stripsLib.Planner;
import stripsLib.Strips;

public class mainForMileStone4 {

	public static void main(String[] args) {

		System.out.println(args[1]);

		MyTextLevelLoader lvlLoad = new MyTextLevelLoader();
		try {
			Level level = lvlLoad.loadLevel(new FileInputStream(args[0]));

			PredicateLevelBuilder plb = new PredicateLevelBuilder();

			List<Action> list = new LinkedList<>();
			Planner strips = new Strips();
			Plannable p = new SokobanPlannable(plb, level);
			list = strips.plan(p);
			if (list != null) {
				PrintWriter out;
				try {
					out = new PrintWriter(new FileWriter(args[1]));

					for (Action a : list) {
						ArrayList<SearchAction> arr = a.getTheRealMoves();
						for (SearchAction sa : arr) {
							System.out.println(sa.getAction());
							out.write(sa.getAction());
							out.write("\n");
						}

					}
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
