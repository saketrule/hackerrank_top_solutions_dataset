import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
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
            return this.weight - other.weight;
        }
        
    }

    private static final class Node {
        
        private int id;
        private List<Edge> edges = new LinkedList<Edge>();
        
        public Node(int id) {
            this.id = id;
        }
        
        public int getId() {
            return id;
        }
        
        public Node addEdge(Edge edge) {
            edges.add(edge);
            return this;
        }
        
        public List<Edge> getEdges() {
            return edges;
        }
        
        public int hashCode() {
            return id;
        }
        
        public boolean equals(Object other) {
            if (other instanceof Node && ((Node)other).id == this.id) {
                return true;
            }
            return false;
        }
    }
    
    
    private Node[] nodes;

    public Solution(int nodeCount) {
        nodes = new Node[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            nodes[i] = new Node(i + 1);
        }
    }
    
    public int findMstWeight(int startNodeIndex) {
        Node startNode = nodes[startNodeIndex - 1];
        Node currentNode = startNode;
        Set<Node> visitedNodes = new HashSet<Node>();
        int weight = 0;
        Queue<Edge> edgeQueue = new PriorityQueue<Edge>();
        visitedNodes.add(currentNode);
        while (visitedNodes.size() < nodes.length) {
            for (Edge edge : currentNode.getEdges()) {
                if (!visitedNodes.contains(edge.getFromNode()) || !visitedNodes.contains(edge.getToNode())) {
                    edgeQueue.add(edge);
                }
            }
            Node nextNode = null;
            while (nextNode == null) {
                Edge nextEdge = edgeQueue.poll();
                if (!visitedNodes.contains(nextEdge.getFromNode())) {
                    nextNode = nextEdge.getFromNode();
                    weight += nextEdge.getWeight();
                } else if (!visitedNodes.contains(nextEdge.getToNode())) {
                    nextNode = nextEdge.getToNode();
                    weight += nextEdge.getWeight();
                }
            }
            currentNode = nextNode;
            visitedNodes.add(currentNode);
        }
        return weight;
    }
    
    public Solution addEdge(int fromIndex, int toIndex, int weight) {
        Node fromNode = nodes[fromIndex - 1];
        Node toNode = nodes[toIndex - 1];
        fromNode.addEdge(new Edge(fromNode, toNode, weight));
        toNode.addEdge(new Edge(toNode, fromNode, weight));
        return this;
    }
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        int nodeCount = scanner.nextInt();
        int edgeCount = scanner.nextInt();
        Solution solution = new Solution(nodeCount);
        for (int i = 0; i < edgeCount; i++) {
            int fromIndex = scanner.nextInt();
            int toIndex = scanner.nextInt();
            int weight = scanner.nextInt();
            solution.addEdge(fromIndex, toIndex, weight);
        }
        int startNode = scanner.nextInt();
        System.out.println(solution.findMstWeight(startNode));
    }
}