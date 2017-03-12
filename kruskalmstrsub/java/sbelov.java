import java.util.*;

public class SolutionKruskals {

  private static final class Node {
    private final int id;
    private Set<Node> reachableNodes = new HashSet<>();

    public Node(int id) {
      this.id = id;
      reachableNodes.add(this);
    }

    public Set<Node> getReachableNodes() {
      return reachableNodes;
    }

    public Node setReachableNodes(Set<Node> reachableNodes) {
      this.reachableNodes = reachableNodes;
      return this;
    }

    public int getId() {
      return id;
    }

    public int hashCode() {
      return id;
    }

    public boolean equals(Object other) {
      return other instanceof Node && ((Node)other).id == this.id;
    }

    @Override
    public String toString() {
      return "Node{" +
             "id=" + id +
             '}';
    }
  }

  private static final class Edge implements Comparable<Edge> {
    private final Node fromNode;
    private final Node toNode;
    private final int weight;

    public Edge(Node fromNode, Node toNode, int weight) {
      this.fromNode = fromNode;
      this.toNode = toNode;
      this.weight = weight;
    }

    public Node getFromNode() {
      return fromNode;
    }

    public Node getToNode() {
      return toNode;
    }

    public int getWeight() {
      return weight;
    }

    public int compareTo(Edge other) {
      if (this.weight != other.weight){
        return this.weight - other.weight;
      } else {
        return (this.weight + this.fromNode.getId() + this.toNode.getId()) - (other.weight + other.fromNode.getId() + other.toNode.getId());
      }
    }

    @Override
    public String toString() {
      return "Edge{" +
             "fromNode=" + fromNode +
             ", toNode=" + toNode +
             ", weight=" + weight +
             '}';
    }
  }

  private Node[] nodes;
  private List<Edge> edges;

  public SolutionKruskals(int nodeCount) {
    nodes = new Node[nodeCount];
    for (int i = 0; i < nodeCount; i++) {
      nodes[i] = new Node(i + 1);
    }
    edges = new ArrayList<Edge>();
  }

  public SolutionKruskals addEdge(int from, int to, int weight) {
    edges.add(new Edge(nodes[from - 1], nodes[to - 1], weight));
    return this;
  }

  public int getMstWeight(int startNodeIndex) {
    Queue<Edge> edgeQueue = new PriorityQueue<Edge>(edges);
    int weight = 0;
    while (!edgeQueue.isEmpty()) {
      Edge edge = edgeQueue.poll();
      Node fromNode = edge.getFromNode();
      Node toNode = edge.getToNode();
      if (!fromNode.getReachableNodes().contains(toNode) && !toNode.getReachableNodes().contains(fromNode)) {
        weight += edge.getWeight();
        Set<Node> reachableSet = fromNode.getReachableNodes();
        reachableSet.addAll(toNode.getReachableNodes());
        for (Node reachableNode : reachableSet) {
          reachableNode.setReachableNodes(reachableSet);
        }
      }
    }
    if (nodes[startNodeIndex - 1].getReachableNodes().size() != nodes.length) {
      throw new RuntimeException("unexpected");
    }
    return weight;
  }

  public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    Scanner scanner = new Scanner(System.in);

    int nodeCount = scanner.nextInt();
    int edgeCount = scanner.nextInt();
    SolutionKruskals solution = new SolutionKruskals(nodeCount);
    for (int i = 0; i < edgeCount; i++) {
      solution.addEdge(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
    }
    int startNode = scanner.nextInt();
    System.out.println(solution.getMstWeight(startNode));
  }
}