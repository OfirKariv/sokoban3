package sokobanSearch;

import java.util.ArrayList;
import java.util.List;

import SearchLib.Bfs;
import SearchLib.SearchAction;
import SearchLib.Searchable;
import SearchLib.Searcher;
import SearchLib.State;
import common.Level;
import model.data.Position;

public class BoxSearchable extends CommonSearchable {

	private Searcher<Position> searcher;
	private Position player;
	private PlayerSearchable playerSearchable = null;

	public BoxSearchable(char[][] board, Position initPos, Position goalPos) {

		super(board, initPos, goalPos);

		player = findPlayer();

	}

	public BoxSearchable(char[][] board) {
		super(board);

	}

	public Position getPlayer() {
		return player;
	}

	public void setPlayer(Position player) {

		this.player = player;

	}

	@Override
	public List<State<Position>> getAllPossibleStates(State<Position> s) {

		if (s.getCameFrom() == null)
			setPlayer(findPlayer());
		else
			setPlayer(s.getCameFrom().getState());

		ArrayList<State<Position>> list = new ArrayList<State<Position>>();
		State<Position> up = null, down = null, left = null, right = null;
		Position curPos = s.getState();
		final int x = curPos.getX();
		final int y = curPos.getY();

		up = getUpState(s, x, y);
		if (up != null) {
			list.add(up);
			up = null;
		}

		down = getDownState(s, x, y);
		if (down != null)
			list.add(down);
		down = null;

		left = getLeftState(s, x, y);
		if (left != null)
			list.add(left);
		left = null;

		right = getRightState(s, x, y);
		if (right != null)
			list.add(right);
		right = null;

		if (!list.isEmpty())
			return list;
		return null;
	}

	public State<Position> getUpState(State<Position> s, int x, int y) {
		searcher = new Bfs<>();
		Position upPos = new Position();
		upPos.setX(x - 1);
		upPos.setY(y);
		Position downPos = new Position();
		downPos.setX(x + 1);
		downPos.setY(y);
		if (isClear(upPos))
			if (player.equals(downPos)) {
				State<Position> upState = new State<>();
				upState.setCameFrom(s);
				upState.setCost(s.getCost() + 1);
				upState.setState(upPos);
				upState.pushActions(s.getAction());
				upState.pushAction(new SearchAction("Move up"));
				return upState;
			} else if (isClear(downPos)) {
				playerSearchable = new PlayerSearchable(board, player, downPos);

				playerSearchable.createBlock(x, y);
				ArrayList<SearchAction> list = searcher.search(playerSearchable);
				playerSearchable.deleteBlock(x, y);
				if (list != null) {
					State<Position> upState = new State<>();
					upState.setCameFrom(s);
					upState.setCost(s.getCost() + 1);
					upState.setState(upPos);
					upState.pushActions(s.getAction());
					upState.pushActions(list);
					upState.pushAction(new SearchAction("Move up"));

					return upState;

				}
				return null;
			}

		return null;

	}

	public State<Position> getDownState(State<Position> s, int x, int y) {
		searcher = new Bfs<>();
		Position downPos = new Position();
		downPos.setX(x + 1);
		downPos.setY(y);
		Position upPos = new Position();
		upPos.setX(x - 1);
		upPos.setY(y);
		if (isClear(downPos))

			if (player.equals(upPos)) {
				State<Position> downState = new State<>();
				downState.setCameFrom(s);
				downState.setCost(s.getCost() + 1);
				downState.setState(downPos);
				downState.pushActions(s.getAction());
				downState.pushAction(new SearchAction("Move down"));
				return downState;
			} else if (isClear(upPos)) {
				playerSearchable = new PlayerSearchable(board, player, upPos);
				playerSearchable.createBlock(x, y);
				ArrayList<SearchAction> list = searcher.search(playerSearchable);
				playerSearchable.deleteBlock(x, y);
				if (list != null) {
					State<Position> downState = new State<>();
					downState.setCameFrom(s);
					downState.setCost(s.getCost() + 1);
					downState.setState(downPos);
					downState.pushActions(s.getAction());
					downState.pushActions(list);
					downState.pushAction(new SearchAction("Move down"));

					return downState;

				}
				return null;
			}

		return null;
	}

	public State<Position> getLeftState(State<Position> s, int x, int y) {
		searcher = new Bfs<>();
		Position leftPos = new Position();
		leftPos.setX(x);
		leftPos.setY(y - 1);
		Position rightPos = new Position();
		rightPos.setX(x);
		rightPos.setY(y + 1);
		if (isClear(leftPos))

			if (player.equals(rightPos)) {
				State<Position> leftState = new State<>();
				leftState.setCameFrom(s);
				leftState.setCost(s.getCost() + 1);
				leftState.setState(leftPos);
				leftState.pushActions(s.getAction());
				leftState.pushAction(new SearchAction("Move left"));
				return leftState;
			} else if (isClear(rightPos)) {
				playerSearchable = new PlayerSearchable(board, player, rightPos);
				playerSearchable.createBlock(x, y);
				ArrayList<SearchAction> list = searcher.search(playerSearchable);
				playerSearchable.deleteBlock(x, y);
				if (list != null) {
					State<Position> leftState = new State<>();
					leftState.setCameFrom(s);
					leftState.setCost(s.getCost() + 1);
					leftState.setState(leftPos);
					leftState.pushActions(s.getAction());
					leftState.pushActions(list);
					leftState.pushAction(new SearchAction("Move left"));

					return leftState;

				}
				return null;
			}

		return null;
	}

	public State<Position> getRightState(State<Position> s, int x, int y) {
		searcher = new Bfs<>();
		Position rightPos = new Position();
		rightPos.setX(x);
		rightPos.setY(y + 1);
		Position leftPos = new Position();
		leftPos.setX(x);
		leftPos.setY(y - 1);
		if (isClear(rightPos))
			if (player.equals(leftPos)) {
				State<Position> rightState = new State<>();
				rightState.setCameFrom(s);
				rightState.setCost(s.getCost() + 1);
				rightState.setState(rightPos);
				rightState.pushActions(s.getAction());
				rightState.pushAction(new SearchAction("Move right"));
				return rightState;
			} else if (isClear(leftPos)) {
				playerSearchable = new PlayerSearchable(board, player, leftPos);
				playerSearchable.createBlock(x, y);
				ArrayList<SearchAction> list = searcher.search(playerSearchable);
				playerSearchable.deleteBlock(x, y);
				if (list != null) {
					State<Position> rightState = new State<>();
					rightState.setCameFrom(s);
					rightState.setCost(s.getCost() + 1);
					rightState.setState(rightPos);
					rightState.pushActions(s.getAction());
					rightState.pushActions(list);
					rightState.pushAction(new SearchAction("Move right"));

					return rightState;

				}
				return null;
			}

		return null;
	}

}
