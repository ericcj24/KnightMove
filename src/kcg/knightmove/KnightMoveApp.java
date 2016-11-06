package kcg.knightmove;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * program actually contains the main algo to compute the all position
 * knight moves with 2 vowels contained, and n steps.
 *
 * @author J. Chen
 * */
public class KnightMoveApp {

	// number of moves specified by user
	private final int NUMBEROFMOVES;
	// vowel limit
	private final int VOWELLIMIT = 2;
	// positions on the board that contains a vowel
	private final List<KnightPosition> VOWELS = new ArrayList<KnightPosition>();
	// tell the program if user wants to print out all possible path while computing
	// the possible moves
	private boolean verbose = false;

	public KnightMoveApp(int numberOfMoves, boolean verbose) {
		NUMBEROFMOVES = numberOfMoves;
		VOWELS.add(new KnightPosition(0, 0));
		VOWELS.add(new KnightPosition(0, 4));
		VOWELS.add(new KnightPosition(1, 3));
		VOWELS.add(new KnightPosition(2, 4));

		this.verbose = verbose;
	}

	public void start() {

		System.out.println("starting KnightMoveApp with " + NUMBEROFMOVES + " moves");

		List<KnightPosition> grid = new ArrayList<KnightPosition>();
		grid.add(new KnightPosition(0,0));
		grid.add(new KnightPosition(0,1));
		grid.add(new KnightPosition(0,2));
		grid.add(new KnightPosition(0,3));
		grid.add(new KnightPosition(0,4));
		grid.add(new KnightPosition(1,0));
		grid.add(new KnightPosition(1,1));
		grid.add(new KnightPosition(1,2));
		grid.add(new KnightPosition(1,3));
		grid.add(new KnightPosition(1,4));
		grid.add(new KnightPosition(2,0));
		grid.add(new KnightPosition(2,1));
		grid.add(new KnightPosition(2,2));
		grid.add(new KnightPosition(2,3));
		grid.add(new KnightPosition(2,4));
		grid.add(new KnightPosition(3,1));
		grid.add(new KnightPosition(3,2));
		grid.add(new KnightPosition(3,3));


		List<KnightPosition> moves = new ArrayList<KnightPosition>();
		moves.add(new KnightPosition(1, -2));
		moves.add(new KnightPosition(1, 2));
		moves.add(new KnightPosition(2, -1));
		moves.add(new KnightPosition(2, 1));
		moves.add(new KnightPosition(-1, -2));
		moves.add(new KnightPosition(-1, 2));
		moves.add(new KnightPosition(-2, -1));
		moves.add(new KnightPosition(-2, 1));

		Map<KnightPosition, List<KnightPosition>> movesMap = new HashMap<KnightPosition, List<KnightPosition>>();
		buildMoveMap(movesMap, moves, grid);

		int vowelCount = 0;
		int lengthCount = 1;
		int permutationCount = 0;

		if (verbose) {
			LinkedList<KnightPosition> traceRecord = new LinkedList<KnightPosition>();
			for (KnightPosition p: grid) {
				traceRecord.addFirst(p);
				permutationCount += knightMove(p, grid, movesMap, traceRecord, vowelCount, lengthCount);
				traceRecord.removeLast();
			}
		} else {
			for (KnightPosition p: grid) {
				permutationCount += knightMove(p, grid, movesMap, vowelCount, lengthCount);
			}
		}

		System.out.println(permutationCount);
	}


	/**
	 *build up a map that represents all possible moves at a certain cell
	 *@param movesMap represents all possible moves at a certain cell
	 *@param moves all the possible ways knight could move in 8 directions
	 *@param grid the board
	 * */
	private void buildMoveMap(Map<KnightPosition, List<KnightPosition>> movesMap, List<KnightPosition> moves, List<KnightPosition> grid) {
		for (KnightPosition gridCell : grid) {
			List<KnightPosition> tempList = new ArrayList<KnightPosition>();

			movesMap.put(gridCell, tempList);
			for (KnightPosition move : moves) {
				KnightPosition temp = new KnightPosition(gridCell.xPosition() + move.xPosition(), gridCell.yPosition() + move.yPosition());

				if (validateMove(temp, grid)) {
					movesMap.get(gridCell).add(temp);
				}
			}
		}
	}

	/**
	 * validate if this position is within our board
	 * @param postion in question
	 * @param grid the board
	 * @return true if the proposed position is within the board
	 * */
	private boolean validateMove(KnightPosition position, List<KnightPosition> grid) {
		if (grid.contains(position)) {
	        return true;
	    } else {
	        return false;
	    }
	}

	/**
	 * a recursive call to find all possible ways knight could move
	 * within the vowel count, and length count
	 * this is a verbose edition, it will print out possible moves in
	 * terminal
	 * @param position current position
	 * @param grid the board
	 * @param movesMap all the moves possible at a certain position
	 * @param trace a stack like structure to help track with the path
	 * @param vowelCount in this program, it is 2
	 * @param lengthCount the length specified by user
	 * */
	private int knightMove(KnightPosition position,
			List<KnightPosition> grid,
			Map<KnightPosition, List<KnightPosition>> movesMap,
			LinkedList<KnightPosition> trace,
			int vowelCount,
			int lengthCount
			) {

		int permutationCount = 0;

		// check vowels
		if (VOWELS.contains(position)) {
			vowelCount ++;
			if (vowelCount > VOWELLIMIT) {
				vowelCount --;
				return 0;
			}
		}

		// reached user requested number
		if (lengthCount == NUMBEROFMOVES) {
			System.out.println(trace);
			return 1;
		} else {
			// all the available moves from this position
			for (KnightPosition p : movesMap.get(position)) {
				trace.addLast(p);
				permutationCount += knightMove(p, grid, movesMap, trace, vowelCount, ++lengthCount);
				trace.removeLast();
				lengthCount--;
			}
		}

		return permutationCount;
	}


	/**
	 * a recursive call to find all possible ways knight could move
	 * within the vowel count, and length count
	 * @param position current position
	 * @param grid the board
	 * @param movesMap all the moves possible at a certain position
	 * @param vowelCount in this program, it is 2
	 * @param lengthCount the length specified by user
	 * */
	private int knightMove(KnightPosition position,
			List<KnightPosition> grid,
			Map<KnightPosition, List<KnightPosition>> movesMap,
			int vowelCount,
			int lengthCount
			) {

		int permutationCount = 0;

		// check vowels
		if (VOWELS.contains(position)) {
			vowelCount ++;
			if (vowelCount > VOWELLIMIT) {
				vowelCount --;
				return 0;
			}
		}

		// reached user requested number
		if (lengthCount == NUMBEROFMOVES) {
			return 1;
		} else {
			// all the available moves from this position
			for (KnightPosition p : movesMap.get(position)) {
				permutationCount += knightMove(p, grid, movesMap, vowelCount, ++lengthCount);
				lengthCount--;
			}
		}

		return permutationCount;
	}

}