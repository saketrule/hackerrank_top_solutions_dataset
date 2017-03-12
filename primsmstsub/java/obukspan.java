import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int numNodes = in.nextInt();
        int numEdges = in.nextInt();
        ArrayList<ArrayList<Edge>> graph = new ArrayList<ArrayList<Edge>>();
        for (int i = 0; i < numNodes; i++) {
            graph.add(new ArrayList<Edge>());
        }
        for (int i = 0; i < numEdges; i++) {
            int s = in.nextInt();
            int t = in.nextInt();
            int w = in.nextInt();
            Edge e1 = new Edge(s-1, t-1, w);
            Edge e2 = new Edge(t-1, s-1, w);
            graph.get(s-1).add(e1);
            graph.get(t-1).add(e2);
        }
        
        Queue<Edge> PQ = new PriorityQueue<Edge>();
        Set<Integer> visited = new HashSet<Integer>();
        int st = in.nextInt();
        for (Edge e: graph.get(st-1)) {
            PQ.add(e);
        }
        visited.add(st-1);
        int minWeight = 0;
        int edgeCount = 0;
        while (edgeCount < numNodes - 1 && !PQ.isEmpty()) {
            Edge e = PQ.remove();
            if ((visited.contains(e.source) && !visited.contains(e.target))) {
                minWeight += e.weight;
                edgeCount++;
                visited.add(e.target);
                for (Edge uv: graph.get(e.target)) {
                    PQ.add(uv);
                }
            }
        }
        System.out.println(minWeight);
    }
}

class Edge implements Comparable<Edge>{
    int source;
    int target;
    int weight;
    
    public Edge(int s, int t, int w) {
        source = s;
        target = t;
        weight = w;
    }
    
    public int compareTo(Edge o) {
        return weight - o.weight;
    }
    
    public String toString() {
        return source + " " + target;
    }
}