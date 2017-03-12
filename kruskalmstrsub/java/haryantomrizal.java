import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

@SuppressWarnings({"rawtypes","unchecked"})
public class Solution {

    public static class Edge<Node> implements Comparable<Edge> {
	private Node v;
	private Node w;
	private Double weight;

	public Edge(Node v, Node w, Double weight) {
		if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	public Double weight() {
		return weight;
	}

	public Node either() {
		return v;
	}

	public Node other(Node n) {
		if (v.equals(n)) return w;
		else if (w.equals(n)) return v;
		else throw new IllegalArgumentException(String.format("Illegal endpoint %s", n.toString()));
	}

	@Override
	public int compareTo(Edge other) {
		// return this.weight.compareTo(other.weight);
		if (this.weight() < other.weight()) return -1;
		else if (this.weight() > other.weight()) return +1;
//		else return 0;
		else return this.v != other.v || this.w != other.w ? 1 : 0;
	}

	public String toString() {
		return String.format("%d-%d %.5f", v, w, weight);
	}
}

    public static class EdgeWeightedGraph {

	private int V;
	private int E;
	private TreeSet<Edge<Integer>>[] adj;

	public EdgeWeightedGraph(int V) {
		if (V < 0) throw new IllegalArgumentException("V must be none-negative");
		this.V = V;
		this.adj = new TreeSet[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new TreeSet<Edge<Integer>>();
		}
	}

	public void validateNode(int v) {
		if (v < 0 || v >= this.V) throw new IndexOutOfBoundsException(String.format(
				"Invalid Node index %d. Must be in 0-%d", v, this.V - 1));
	}

	public void addEdge(int v, int w, double weight) {
		addEdge(new Edge<Integer>(v, w, weight));
	}

	public void addEdge(Edge<Integer> edge) {
		int v = edge.either();
		int w = edge.other(v);
		validateNode(v);
		validateNode(w);
		adj[v].add(edge);
		adj[w].add(edge);
		E++;
	}

	public int degree(int v) {
		validateNode(v);
		return adj[v].size();
	}

	public int V() {
		return this.V;
	}

	public int E() {
		return this.E;
	}

	public TreeSet<Edge<Integer>> adj(int v) {
		return adj[v];
	}
}

    public static class PrimsMST {

	private static class DistanceMetric implements Comparable<DistanceMetric> {
		private Integer key;
		private Double distance;

		public DistanceMetric(Integer key, Double distance) {
			this.key = key;
			this.distance = distance;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((key == null) ? 0 : key.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null) return false;
			if (getClass() != obj.getClass()) return false;
			DistanceMetric other = (DistanceMetric) obj;
			if (key == null) {
				if (other.key != null) return false;
			} else if (!key.equals(other.key)) return false;
			return true;
		}

		@Override
		public int compareTo(DistanceMetric other) {
			return distance.compareTo(other.distance);
		}
	}

	private static double INFINITY = -1;
	private boolean[] marked;
	private double[] distTo;
	private Edge[] edgeTo;

	public PrimsMST(EdgeWeightedGraph G, int s) {
		marked = new boolean[G.V()];
		distTo = new double[G.V()];
		edgeTo = new Edge[G.V()];
		for (int v = 0; v < G.V(); v++) distTo[v] = INFINITY;

		distTo[s] = 0;
		PriorityQueue<DistanceMetric> pq = new PriorityQueue<DistanceMetric>();
		pq.add(new DistanceMetric(s, distTo[s]));

		while (!pq.isEmpty()) {
			DistanceMetric V = pq.poll();
			distTo[V.key] = distTo[V.key] == INFINITY ? 0 : distTo[V.key];
			for (Edge<Integer> adj : G.adj(V.key)) {
				int w = adj.other(V.key);
				if (marked[w]) continue;
				if (distTo[w] == INFINITY || adj.weight() < distTo[w]) {
					distTo[w] = adj.weight();
					edgeTo[w] = adj;
					pq.add(new DistanceMetric(w, distTo[w]));
				}
			}
			marked[V.key] = true;
		}
	}

	public boolean hasPathTo(int v) {
		return marked[v];
	}

	public double distTo(int v) {
		return distTo[v];
	}

	public double weight() {
		double weight = 0;
		for (Edge adj : edgeTo) {
			if (adj != null) weight += adj.weight();
		}
		return weight;
	}
}
	public static void main(String[] args) {
		Scanner scan = new Scanner(new BufferedInputStream(System.in));
		int N = scan.nextInt();
		int M = scan.nextInt();
		EdgeWeightedGraph G = new EdgeWeightedGraph(N);
		for (int i = 0; i < M; i++) {
			int v = scan.nextInt();
			int w = scan.nextInt();
			double weight = scan.nextInt();
			G.addEdge(v - 1, w - 1, weight);
		}
		int s = scan.nextInt();
		PrimsMST primMST = new PrimsMST(G, s - 1);
		System.out.printf("%.0f\n", primMST.weight());

	}

}