import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static class Edge {
        int u;
        int v;
        int weight;
        int factor;
        
        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
            this.factor = u + v + weight;
        }
    }
    
    public static class UnionFind {
        int[] size;
        int[] root;
        int n;
        
        public UnionFind(int n) {
            this.n = n;
            this.size = new int[this.n];
            this.root = new int[this.n];
            
            for (int i = 1; i < this.n; i++) {
                this.size[i] = 1;
                this.root[i] = i;
            }
        }
        
        public int find(int i) {
            while (this.root[i] != i) i = this.root[i];
            return i;
        }
        
        public void union(int i, int j) {
            int p = this.find(i);
            int q = this.find(j);
            
            if (p != q) {
                if (this.size[p] > this.size[q]) {
                    this.root[q] = p;
                    this.size[p] += this.size[q];
                }
                else {
                    this.root[p] = q;
                    this.size[q] += this.size[p];
                }
            }
        }
        
        public boolean disjoint(int i, int j) {
            return this.find(i) != this.find(j);
        }
    }
    
    public static class Kruskal {
        UnionFind uf;
        PriorityQueue<Edge> pq;
        int sum;
        int s;
        
        public Kruskal(PriorityQueue<Edge> pq, int n, int s) {
            this.uf = new UnionFind(n);
            this.s = s;
            this.sum = 0;
            this.pq = pq;
        }
        
        public int weightMST() {
            if (this.sum != 0) return this.sum;
            
            while (this.pq.size() > 0) {
                Edge p = this.pq.poll();
                
                if (this.uf.disjoint(p.u, p.v)) {
                    this.uf.union(p.u, p.v);
                    this.sum += p.weight;
                }
            }
            
            return this.sum;
        }
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();
        Comparator<Edge> edgeComp = new Comparator<Edge>() {
            public int compare(Edge i, Edge j) {
                if (i.weight == j.weight) return i.factor - j.factor;
                return i.weight - j.weight;
            }
        };
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>(m+1, edgeComp);
        
        for (int i = 0; i < m; i++) pq.offer(new Edge(in.nextInt(), in.nextInt(), in.nextInt()));
        
        Kruskal k = new Kruskal(pq, n+1, in.nextInt());
        System.out.println(k.weightMST());
    }
}