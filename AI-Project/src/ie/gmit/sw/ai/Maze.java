package ie.gmit.sw.ai;

public class Maze {

	private static char[][] maze; // An array does not lend itself to the type of mazge generation alogs we use in
	// the labs. There are no "walls" to carve...

	public Maze(int dimension) {
		maze = new char[dimension][dimension];
		init();
		buildMaze();

		int featureNumber = (int) ((dimension * dimension) * 0.01); // Change this value to control the number of
																	// objects
		// extra objects. Will remove for now
		/*
		 * addFeature('\u0031', '0', featureNumber); //1 is a sword, 0 is a hedge
		 * addFeature('\u0032', '0', featureNumber); //2 is help, 0 is a hedge
		 * addFeature('\u0033', '0', featureNumber); //3 is a bomb, 0 is a hedge
		 * addFeature('\u0034', '0', featureNumber); //4 is a hydrogen bomb, 0 is a
		 * hedge
		 */
		// spiders
		featureNumber = (int) ((dimension * dimension) * 0.001); // Change this value to control the number of spiders
		addSpider('\u0036', '0', featureNumber); // 6 is a Black Spider, 0 is a hedge
		addSpider('\u0037', '0', featureNumber); // 7 is a Blue Spider, 0 is a hedge
		addSpider('\u0038', '0', featureNumber); // 8 is a Brown Spider, 0 is a hedge
		addSpider('\u0039', '0', featureNumber); // 9 is a Green Spider, 0 is a hedge
		addSpider('\u003A', '0', featureNumber); // : is a Grey Spider, 0 is a hedge
		addSpider('\u003B', '0', featureNumber); // ; is a Orange Spider, 0 is a hedge
		addSpider('\u003C', '0', featureNumber); // < is a Red Spider, 0 is a hedge
		addSpider('\u003D', '0', featureNumber); // = is a Yellow Spider, 0 is a hedge
	}

	private void init() {
		for (int row = 0; row < maze.length; row++) {
			for (int col = 0; col < maze[row].length; col++) {
				maze[row][col] = '0'; // Index 0 is a hedge...
			}
		}
	}

	private void addSpider(char feature, char replace, int number) {
		
		int counter = 0;
		
		while (counter < feature) { // Keep looping until feature number of items have been added
			// rows and cols
			int row = (int) (maze.length * Math.random());
			int col = (int) (maze[0].length * Math.random());

			if (maze[row][col] == replace) {
				maze[row][col] = feature;

				int[] pos = { row, col };
				
				// call thread class for spiders
				SpiderThread st = new SpiderThread(pos, feature);
				st.start();
				counter++;
			}
		}
	}

	// creates a random maze on build
	// need to add randomised exits
	
	private void buildMaze() {
		for (int row = 1; row < maze.length - 1; row++) {
			for (int col = 1; col < maze[row].length - 1; col++) {
				int num = (int) (Math.random() * 10);
				if (isRoom(row, col))
					continue;
				if (num > 5 && col + 1 < maze[row].length - 1) {
					maze[row][col + 1] = '\u0020'; // \u0020 = 0x20 = 32 (base 10) = SPACE
				} else {
					if (row + 1 < maze.length - 1)
						maze[row + 1][col] = '\u0020';
				}
			}
		}
	}

	private boolean isRoom(int row, int col) {
		return row > 1 && maze[row - 1][col] == '\u0020' && maze[row - 1][col + 1] == '\u0020';
	}

	public static char[][] getMaze() {
		return maze;
	}

	public char get(int row, int col) {
		return Maze.maze[row][col];
	}

	public void set(int row, int col, char c) {
		Maze.maze[row][col] = c;
	}

	public int size() {
		return Maze.maze.length;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int row = 0; row < maze.length; row++) {
			for (int col = 0; col < maze[row].length; col++) {
				sb.append(maze[row][col]);
				if (col < maze[row].length - 1)
					sb.append(",");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}