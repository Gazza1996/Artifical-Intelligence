package ie.gmit.sw.ai;

import java.util.List;

public class BFSearch {

	// https://stackoverflow.com/questions/8224470/calculating-manhattan-distance
	private static volatile Search single_instance = null;

	public static synchronized Search getInstance() throws Exception {

		if (single_instance == null) {
			single_instance = new Search();
		}

		return single_instance;
	}

	public static List<searchNode> findPlayer(int spiderRow, int spiderCol) throws Exception {

		GameView.getInstance();

		int playerRow = GameView.getCurrentRow();
		int playerCol = GameView.getCurrentCol();

		GameView.getMaze();
		char[][] matrix = Maze.getMaze();

		return null;
	}

}