package pack1;

public class Graph {

	int size;
	int[][] matrix;
	boolean[] visited;

	public Graph(int size) {

		this.size = size;
		this.matrix = new int[size][size];
		this.visited = new boolean[size];

		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				matrix[i][j] = -1;
	}

	public void addEdge(int src, int des) {

		if (this.matrix[src][des] == 1)
			return;

		this.matrix[src][des] = 1;
		this.matrix[des][src] = 1;
	}

	public void deleteEdge(int src, int des) {

		if (this.matrix[src][des] == -1)
			return;

		this.matrix[src][des] = -1;
		this.matrix[des][src] = -1;
	}

	public void printGraph() {

		for (int i = 0; i < size; i++)
			for (int j = 0; j < i; j++)
				if (this.matrix[i][j] == 1)
					System.out.println("Node " + j + " and node " + i + " are connected to each other.");
	}

	public static void main(String[] args) {

		Graph g = new Graph(5);
		g.addEdge(0, 1);
		g.addEdge(0, 4);
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(1, 4);
		g.addEdge(2, 3);
		g.addEdge(3, 4);

		g.printGraph();
	}
}
