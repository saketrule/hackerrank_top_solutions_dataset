import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static class Edge {
        int v;
        int weight;
        
        public Edge(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
        
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Edge)) return false;
            Edge e = (Edge) o;
            if (this.v == e.v) return true;
            
            return false;
        }
        
        public int hashCode() {
            return this.v;
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();
        ArrayList<Edge>[] adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<Edge>();
        
        for (int i = 0; i < m; i++) {
            int u = in.nextInt(), v = in.nextInt(), r = in.nextInt();
            adj[u].add(new Edge(v, r));
            adj[v].add(new Edge(u, r));
        }
        
        Prim prim = new Prim(adj, in.nextInt());
        System.out.println(prim.sumWeights());
    }
    
    public static class Prim {
        int[] distance;
        boolean[] marked;
        PriorityQueue<Edge> pq;
        ArrayList<Edge>[] adj;
        int s;
        int sum;
        
        public Prim(ArrayList<Edge>[] adj, int s) {
            int n = adj.length;
            this.adj = adj;
            this.distance = new int[n];
            for (int i = 0; i < n; i++) this.distance[i] = -1;
            this.marked = new boolean[n];
            Comparator<Edge> edgeComp = new Comparator<Edge>() {
                public int compare(Edge i, Edge j) {
                    return i.weight - j.weight;
                }
            };
            this.pq = new PriorityQueue<Edge>(n, edgeComp);
            this.s = s;
        }
        
        public int sumWeights() {
            this.distance[this.s] = 0;
            this.pq.offer(new Edge(this.s, this.distance[this.s]));
            this.sum = 0;
            
            while (this.pq.size() > 0) {
                int u = this.pq.poll().v;
                this.marked[u] = true;
                this.sum += this.distance[u];
                //System.out.println(this.distance[u]);
                for (Edge e : this.adj[u]) {
                    int v = e.v;
                    if (!this.marked[v]) {
                        if (this.distance[v] == -1) {
                            this.distance[v] = e.weight;
                            this.pq.offer(new Edge(v, this.distance[v]));
                        }
                        else if (e.weight < this.distance[v]) {
                            this.pq.remove(new Edge(v, this.distance[v]));
                            this.distance[v] = e.weight;
                            this.pq.offer(new Edge(v, this.distance[v]));
                        }
                    }
                }
            }
            return this.sum;
        }
    }
}