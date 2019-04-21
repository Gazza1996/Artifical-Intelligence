package ie.gmit.sw.ai;

import java.util.ArrayList;
//import java.util.Collection;
import java.util.List;

// https://stackoverflow.com/questions/8224470/calculating-manhattan-distance

public class manhatton {
	
	public searchNode find(char[][] matrix, int row, int col) throws Exception {

		List<searchNode> queue = new ArrayList<searchNode>();

		searchNode currentNode = new searchNode(row, col);

		queue.addAll(getNeighbors(matrix, currentNode));

		for (searchNode n : queue) {

			if (getDistance(n) < getDistance(currentNode)) {
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

	int getDistance(searchNode n) throws Exception {
		int x1 = 0;
		int y1 = 0;

		GameView.getInstance();

		// get players location
		x1 = GameView.getCurrentRow();
		y1 = GameView.getCurrentCol();

		// Using Manhattan distance to determine how far each spider if from the player
		return Math.abs(n.x - x1) + Math.abs(n.y - y1);
	}

}
