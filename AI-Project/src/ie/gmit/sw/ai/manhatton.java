package ie.gmit.sw.ai;

import java.util.ArrayList;
//import java.util.Collection;
import java.util.List;
// manhatton distance heuristic search
// https://stackoverflow.com/questions/8224470/calculating-manhattan-distance

public class manhatton {
	
	public searchNode find(char[][] matrix, int row, int col) throws Exception {

		List<searchNode> queue = new ArrayList<searchNode>();
		// current position of spider on tiles
		searchNode currentNode = new searchNode(row, col);
		// get other tiles
		queue.addAll(getNeighbors(matrix, currentNode));

		for (searchNode n : queue) {

			if (getDistanceFromPlayer(n) < getDistanceFromPlayer(currentNode)) {
				currentNode = n;
			}

		}

		return currentNode;
	}

	public List<searchNode> getNeighbors(char[][] matrix, searchNode node) {

		List<searchNode> neighbors = new ArrayList<searchNode>();

		if (matrix[node.x - 1][node.y] == '\u0020')
			neighbors.add(new searchNode(node.x - 1, node.y));

		if (matrix[node.x + 1][node.y] == '\u0020')
			neighbors.add(new searchNode(node.x + 1, node.y));

		if (matrix[node.x][node.y - 1] == '\u0020')
			neighbors.add(new searchNode(node.x, node.y - 1));

		if (matrix[node.x - 1][node.y + 1] == '\u0020')
			neighbors.add(new searchNode(node.x, node.y + 1));

		return neighbors;

	}

	int getDistanceFromPlayer(searchNode currentNode) throws Exception {

		int x2 = 0;
		int y2 = 0;

		GameView.getInstance();

		// get players location
		x2 = GameView.getCurrentRow();
		y2 = GameView.getCurrentCol();

		// Using Manhattan distance to determine how far each spider if from the player
		return Math.abs(currentNode.x - x2) + Math.abs(currentNode.y - y2);

	}
	
	int getDistanceFromHidePosition(searchNode currentNode) throws Exception {

		int x2 = 0;
		int y2 = 0;

		// get players location
		y2 = 10;
		x2 = 10;

		// Using Manhattan distance to determine how far each spider if from the player
		return Math.abs(currentNode.x - x2) + Math.abs(currentNode.y - y2);

	}

}
