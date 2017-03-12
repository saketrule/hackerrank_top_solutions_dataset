import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(), M = scanner.nextInt();

        Map<Integer, List<Edge>> graph = new HashMap<Integer, List<Edge>>();
        PriorityQueue<Edge> queue = new PriorityQueue<Edge>(1, new Comparator<Edge>() {
            @Override
            public int compare(Edge edge, Edge t1) {
                if (edge.weight != t1.weight) {
                    return edge.weight - t1.weight;
                }
                return (edge.from + edge.to) - (t1.from + t1.to);
            }
        });

        for (int i = 0; i < M; i++) {
            int from = scanner.nextInt(), to = scanner.nextInt(), w = scanner.nextInt();
            if (!graph.containsKey(from)) {
                graph.put(from, new ArrayList<Edge>());
            }
            if (!graph.containsKey(to)) {
                graph.put(to, new ArrayList<Edge>());
            }
            Edge edge = new Edge(from, to, w);
            graph.get(from).add(edge);
            graph.get(to).add(edge);
            queue.add(edge);
        }
        int start = scanner.nextInt();

        UnionFind unionFind = new UnionFind(1, N);
        List<Edge> res = new ArrayList<Edge>();

        while (res.size() < N - 1) {
            while (!hasDifferentRoot(queue.peek(), unionFind)) {
                queue.poll();
            }
            Edge edge = queue.poll();
            //System.out.println(edge.from + " " + edge.to);
            res.add(edge);
            unionFind.union(edge.from, edge.to);
        }

        long sum = 0l;
        for (Edge edge : res) {
            sum += edge.weight;
        }
        System.out.println(sum);
    }

    private static boolean hasDifferentRoot(Edge edge, UnionFind unionFind) {
        int from = edge.from, to = edge.to;
        int par1 = unionFind.find(from), par2 = unionFind.find(to);

        return par1 != par2;
    }



    private static class Edge {
        int from;
        int to;
        int weight;
        public Edge(int f, int t, int w) {
            from = f;
            to = t;
            weight = w;
        }

    }


    private static class UnionFind {
        Map<Integer, Integer> dict;
        public UnionFind(int start, int end) {
            dict = new HashMap<Integer, Integer>();
            for (int i = start; i <= end; i++) {
                dict.put(i, -1);
            }
        }

        public int find(int node) {
            if (!dict.containsKey(node)) {
                throw new NoSuchElementException();
            }
            int par = dict.get(node);
            if (par == -1) {
                return node;
            }
            int root = find(par);
            dict.put(node, root);
            return root;
        }

        public void union(int node1, int node2) {
            if (!dict.containsKey(node1) || !dict.containsKey(node2)) {
                throw new NoSuchElementException();
            }
            int par1 = find(node1), par2 = find(node2);
            dict.put(par1, par2);
        }

    }

}