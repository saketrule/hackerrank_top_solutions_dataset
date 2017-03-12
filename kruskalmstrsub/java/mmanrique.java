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
            nodes[i] = new Node(i + 1);
        }
        int edges = scanner.nextInt();
        List<Edge> edgesList = new LinkedList<>();
        for (int i = 0; i < edges; i++) {
            int from = scanner.nextInt() - 1;
            int to = scanner.nextInt() - 1;
            int weight = scanner.nextInt();
            Edge edge = new Edge(nodes[from], nodes[to], weight);
            edgesList.add(edge);
        }

        int start = scanner.nextInt() - 1;
        System.out.println(kruskal(nodes, edgesList));


    }

    private static int kruskal(Node[] nodes, List<Edge> edges) {
        int weight = 0;

        UnionFind uf = new UnionFind(nodes.length);
        PriorityQueue<Edge> pq = new PriorityQueue<>(2, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                int compare = Integer.compare(o1.weigth, o2.weigth);
                if (compare == 0) {
                    return Integer.compare(o1.getTotalCompare(), o2.getTotalCompare());
                }
                return compare;
            }
        });
        pq.addAll(edges);
        int edgesTaken = 0;
        while (!pq.isEmpty() && edgesTaken < nodes.length - 1) {
            Edge edge = pq.poll();
            Node from = edge.from;
            Node to = edge.to;
            if (!uf.conected(from.value - 1, to.value - 1)) {
                uf.union(from.value - 1, to.value - 1);
                weight += edge.weigth;
                edgesTaken++;
            }

        }
        return weight;

    }

    static class Node {
        int value;

        public Node(int value) {
            this.value = value;
        }


    }

    static class Edge {
        Node from;
        Node to;
        int weigth;

        public Edge(Node from, Node to, int weigth) {
            this.from = from;
            this.to = to;
            this.weigth = weigth;
        }

        public int getTotalCompare() {
            return from.value + weigth + to.value;
        }
    }

    static class UnionFind {
        int parents[];
        int rank[];

        public UnionFind(int size) {
            parents = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parents[i] = i;
            }
        }

        public int find(int index) {
            if (parents[index] == index) {
                return index;
            } else {
                return find(parents[index]);
            }
        }

        public void union(int indexA, int indexB) {
            int parentA = find(indexA);
            int parentB = find(indexB);
            if (parentA == parentB) {
                return;
            }

            if (rank[parentA] > rank[parentB]) {
                parents[parentB] = parentA;

            } else if (rank[parentA] < rank[parentB]) {
                parents[parentA] = parentB;
            } else {
                parents[parentA] = parentB;
                rank[parentB]++;
            }
        }

        public boolean conected(int indexA, int indexB) {
            int parentA = find(indexA);
            int parentB = find(indexB);
            return parentA == parentB;
        }


    }

}