package sokobanSearch;

import java.util.List;

import SearchLib.Searchable;
import SearchLib.State;
import common.Level;
import model.data.Position;

public abstract class CommonSearchable implements Searchable<Position> {

	protected char[][] board;
	// protected Level level;
	protected char[][] level;
	protected Position initPos;
	protected Position goalPos;

	public CommonSearchable(char[][] board, Position initPos, Position goalPos) {

		this.initPos = initPos;
		this.goalPos = goalPos;
		setBoard(board);

		clearInit();

	}

	public CommonSearchable(char[][] board) {
		setBoard(board);

	}

	public void setPositions(Position initPos, Position goalPos) {

		setInitPos(initPos);
		setGoalPos(goalPos);

	}

	public Position getInitPos() {
		return initPos;
	}

	public void setInitPos(Position initPos) {
		this.initPos = initPos;
	}

	public Position getGoalPos() {
		return goalPos;
	}

	public void setGoalPos(Position goalPos) {
		this.goalPos = goalPos;
	}

	public void setBoard(char[][] board) {

		this.board = new char[board.length][board[0].length];
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++) {

				this.board[i][j] = board[i][j];

			}

	}

	public void clearInit() {

		this.board[initPos.getX()][initPos.getY()] = ' ';
	}

	public void UpdateBoard(Position player, Position oldBox, Position newBox) {
		Position oldPlayer = findPlayer();
		board[oldPlayer.getX()][oldPlayer.getY()] = ' ';
		board[oldBox.getX()][oldBox.getY()] = ' ';
		board[player.getX()][player.getY()] = 'A';
		board[newBox.getX()][newBox.getY()] = '*';

	}

	public void printBoard() {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++)

				System.out.print(board[i][j]);
			System.out.println();
		}

	}

	@Override
	public State<Position> getInitialState() {

		State<Position> initState = new State<>();
		initState.setCost(0);

		Position current = initPos;
		initState.setState(current);

		return initState;

	}

	@Override
	public State<Position> getGoalState() {

		State<Position> goalState = new State<>();
		Position current = goalPos;
		goalState.setState(current);

		return goalState;

	}

	@Override
	public abstract List<State<Position>> getAllPossibleStates(State<Position> s);

	public boolean isClear(Position p) {

		int x = p.getX();
		int y = p.getY();
		int limitX = board.length - 1;
		int limitY = board[0].length - 1;

		if ((x < 0) || (x > limitX) || (y < 0) || (y > limitY))
			return false;

		if (board[x][y] == ' ' || board[x][y] == 'O' || board[x][y] == 'A')
			return true;

		return false;

	}

	public Position findPlayer() {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++)

				if (board[i][j] == 'A') {
					return (new Position(i, j));

				}

		}
		return null;

	}

}
