import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import SearchLib.Bfs;
import SearchLib.SearchAction;
import SearchLib.Searcher;
import common.Level;
import model.calc.MyTextLevelLoader;
import model.data.Position;
import sokobanSearch.BoxSearchable;
import sokobanSearch.CommonSearchable;

public class tester {

	public static void main(String[] args) {

		MyTextLevelLoader lvlLoad = new MyTextLevelLoader();

		try {
			Level level = lvlLoad.loadLevel(new FileInputStream("C:/Users/ofirk/Desktop/MAP5.txt"));
			Position init = new Position(2, 7);
			// Position init = level.getCharacters().get(0).getPosition();
			Position goal = new Position(3, 5);

			Searcher<Position> searcher = new Bfs<Position>();
			// or any Searchable
			// System.out.println(level.getCharMat()[9][9]);
			char[][] board = level.getCharMat();

			CommonSearchable searchable = new BoxSearchable(board);
			searchable.setPositions(init, goal);

			ArrayList<SearchAction> actions = searcher.search(searchable);
			// searchable.UpdateBoard(new Position(1, 2), new Position(2, 2),
			// new Position(1, 5));
			// searchable.printBoard();
			// see the actions
			if (actions == null)
				System.out.println("no path");
			else {
				for (SearchAction a : actions)
					System.out.println(a);
				System.out.println("# of nodes evaluated " + searcher.getNumberOfNodesEvaluated());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}