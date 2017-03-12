import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PrimAlgorithmPQ {
	private static Scanner scanner;
	private static PriorityQueue<Node> priorityQueue;
	private static List<Node>[] adjacencyList;
	private static int[] minDistances;
	private static int[] bestPath;
	private static int vertexes, edges, startVertex;
	private static final int INFINITY = 131072;
	private static final int LIST_MAX_SIZE = 4096;
	private static final int MAX_CAPACITY = 9000000;

	public static void main(String[] args) {
		scanner = new Scanner(System.in);

		initializeAdjacencyList();
		initializeDistances();
		createPriorityQueue();
		readGraph();
		solvePrim();
	}

	public static void solvePrim() {
		Node startNode = new Node(startVertex, 0);
		minDistances[startVertex] = 0;
		priorityQueue.add(startNode);
		while (!priorityQueue.isEmpty()) {
			Node topNode = priorityQueue.poll();

			List<Node> currentList = adjacencyList[topNode.getVertex()];
			for (Node currentNode : currentList) {
				if (minDistances[currentNode.getVertex()] > currentNode
						.getWeight()
						&& bestPath[topNode.getVertex()] != currentNode
								.getVertex()) {
					minDistances[currentNode.getVertex()] = currentNode
							.getWeight();
					bestPath[currentNode.getVertex()] = topNode.getVertex();
					priorityQueue.add(new Node(currentNode.getVertex(),
							minDistances[currentNode.getVertex()]));
				}
			}
		}

		long ans = 0;
		for (int it = 1; it <= vertexes; it++) {
			if (it != startVertex) {
				ans += minDistances[it];
			}
		}
		System.out.println(ans);
	}

	public static void createPriorityQueue() {
		priorityQueue = new PriorityQueue<PrimAlgorithmPQ.Node>(MAX_CAPACITY,
				new Comparator<Node>() {
					@Override
					public int compare(Node first, Node second) {
						return first.getWeight().compareTo(second.getWeight());
					}
				});
	}

	public static void initializeDistances() {
		bestPath = new int[LIST_MAX_SIZE];
		minDistances = new int[LIST_MAX_SIZE];
		for (int it = 0; it < LIST_MAX_SIZE; it++) {
			minDistances[it] = INFINITY;
		}
	}

	public static void initializeAdjacencyList() {
		adjacencyList = new List[LIST_MAX_SIZE];
		for (int it = 0; it < LIST_MAX_SIZE; it++) {
			adjacencyList[it] = new ArrayList<Node>();
		}
	}

	public static void readGraph() {
		vertexes = scanner.nextInt();
		edges = scanner.nextInt();

		for (int it = 1; it <= edges; it++) {
			Integer firstVerex = scanner.nextInt();
			Integer secondVertex = scanner.nextInt();
			Integer weight = scanner.nextInt();
			Node node = new Node(secondVertex, weight);
			adjacencyList[firstVerex].add(node);
			node = new Node(firstVerex, weight);
			adjacencyList[secondVertex].add(node);
		}

		startVertex = scanner.nextInt();
	}

	public static class Node {
		private Integer vertex;
		private Integer weight;

		Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		public Integer getVertex() {
			return vertex;
		}

		public Integer getWeight() {
			return weight;
		}
	}
}
