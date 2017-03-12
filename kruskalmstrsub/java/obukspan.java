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
        int[] connected = new int[numNodes];
        int[] size = new int[numNodes];
        for (int i = 0; i < numNodes; i++) {
            connected[i] = i;
            size[i] = 1;
        }
        Queue<Edge> edges = new PriorityQueue<Edge>();
        for (int i = 0; i < numEdges; i++) {
            int s = in.nextInt() - 1;
            int t = in.nextInt() - 1;
            int w = in.nextInt();
            edges.add(new Edge(s, t, w));
        }
        
        Set<Integer> nodes = new HashSet<Integer>();
        int st = in.nextInt();
        int minWeight = 0;
        int edgeCount = 0;
        while (edgeCount < numNodes - 1) {
            Edge e = edges.remove();
            if (!(find(connected, e.source, e.target))) {
                minWeight += e.weight;
                edgeCount++;
                union(connected, size, e.source, e.target);
            }
        }
        System.out.println(minWeight);
    }
    
    private static int root(int[] ar, int i) {
        while (i != ar[i]) {
            ar[i] = ar[ar[i]];
            i = ar[i];
        }
        return i;
    }
    
    public static boolean find(int[] ar, int i, int j) {
        return root(ar, i) == root(ar, j);
    }
    
    public static void union(int[] ar, int[] size, int i, int j) {
        i = root(ar, i);
        j = root(ar, j);
        if (size[i] > size[j]) {
            ar[i] = ar[j];
            size[j] += size[i];
        }
        else {
            ar[j] = ar[i];
            size[i] += size[j];
        }
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
}