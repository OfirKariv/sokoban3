import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import common.Level;
import model.calc.MyTextLevelLoader;
import model.data.Position;

public class testerTwo {

	public static void main(String[] args) {

		MyTextLevelLoader lvlLoad = new MyTextLevelLoader();

		try {
			Level level = lvlLoad.loadLevel(new FileInputStream("C:/Users/ofirk/Desktop/MAP2.txt"));
			Position init = new Position(1, 2);
			Position goal = new Position(1, 2);

			ArrayList<Integer> arr = new ArrayList<>();
			arr.add(1);
			arr.add(2);
			System.out.println(arr.get(arr.size() - 1));

			// CommonSearchable searchable = new Player(level, init, goal);
			// searchable.printBoard();
			int[][] mat = new int[5][5];
			System.out.println(mat.length);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
