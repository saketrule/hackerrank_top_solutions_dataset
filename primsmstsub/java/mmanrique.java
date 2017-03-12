import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int vertex = scanner.nextInt();
        Node[] nodes = new Node[vertex];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node();
        }
        int edges = scanner.nextInt();
        for (int i = 0; i < edges; i++) {
            int from = scanner.nextInt() - 1;
            int to = scanner.nextInt() - 1;
            int weigth = scanner.nextInt();
            nodes[from].addAdjacent(nodes[to], weigth);
        }
        int start = scanner.nextInt() - 1;
        prim(nodes, start);
        int sum = 0;
        for (Node node : nodes) {
            sum += node.distanceToParent;
        }
        System.out.println(sum);

    }

    private static void prim(Node[] nodes, int start) {
        Node source = nodes[start];

        source.distanceToParent = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>(2, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.distanceToParent, o2.distanceToParent);
            }
        });
        pq.add(source);

        while (!pq.isEmpty()) {
            Node from = pq.poll();
            from.visited = true;
            for (Edge edge : from.adjacents) {
                Node to = edge.to;
                if (!to.visited) {
                    if (edge.weigth < to.distanceToParent) {
                        if (pq.contains(to)) {
                            pq.remove(to);
                        }
                        to.distanceToParent = edge.weigth;
                        pq.add(to);
                    }
                }
            }
        }

    }

    static class Node {
        boolean visited = false;
        int distanceToParent = Integer.MAX_VALUE;
        List<Edge> adjacents = new LinkedList<>();

        void addAdjacent(Node node, int weigth) {
            this.adjacents.add(new Edge(node, weigth));
            node.adjacents.add(new Edge(this, weigth));
        }

    }

    static class Edge {
        Node to;
        int weigth;

        public Edge(Node to, int weigth) {
            this.to = to;
            this.weigth = weigth;
        }
    }

}