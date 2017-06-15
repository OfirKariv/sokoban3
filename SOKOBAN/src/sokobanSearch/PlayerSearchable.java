package sokobanSearch;

import java.util.ArrayList;
import java.util.List;

import SearchLib.SearchAction;
import SearchLib.State;
import common.Level;
import model.data.Floor;
import model.data.Position;
import model.data.Target;
import model.policy.MySokobanPolicy;

public class PlayerSearchable extends CommonSearchable {

	public PlayerSearchable(char[][] board, Position initPos, Position goalPos) {
		super(board, initPos, goalPos);

	}

	@Override
	public List<State<Position>> getAllPossibleStates(State<Position> s) {

		ArrayList<State<Position>> list = new ArrayList<State<Position>>();
		Position upPos, downPos, leftPos, rightPos;
		Position curPos = s.getState();
		final int x = curPos.getX();
		final int y = curPos.getY();

		upPos = new Position();
		upPos.setX(x - 1);
		upPos.setY(y);

		downPos = new Position();
		downPos.setX(x + 1);
		downPos.setY(y);

		leftPos = new Position();
		leftPos.setX(x);
		leftPos.setY(y - 1);

		rightPos = new Position();
		rightPos.setX(x);
		rightPos.setY(y + 1);

		if (isClear(upPos)) {
			State<Position> upState = new State<>();
			upState.setCameFrom(s);
			upState.setCost(s.getCost() + 1);
			upState.setState(upPos);
			upState.pushAction(new SearchAction("Move up"));
			list.add(upState);

		}

		if (isClear(downPos)) {
			State<Position> downState = new State<>();
			downState.setCameFrom(s);
			downState.setCost(s.getCost() + 1);
			downState.setState(downPos);
			downState.pushAction(new SearchAction("Move down"));

			list.add(downState);
		}

		if (isClear(leftPos)) {
			State<Position> leftState = new State<>();
			leftState.setCameFrom(s);
			leftState.setCost(s.getCost() + 1);
			leftState.setState(leftPos);
			leftState.pushAction(new SearchAction("Move left"));

			list.add(leftState);
		}

		if (isClear(rightPos)) {
			State<Position> rightState = new State<>();
			rightState.setCameFrom(s);
			rightState.setCost(s.getCost() + 1);
			rightState.setState(rightPos);
			rightState.pushAction(new SearchAction("Move right"));

			list.add(rightState);
		}

		if (!list.isEmpty())
			return list;
		return null;
	}

	public void createBlock(int x, int y) {
		board[x][y] = '@';

	}

	public void deleteBlock(int x, int y) {
		board[x][y] = ' ';

	}

}
