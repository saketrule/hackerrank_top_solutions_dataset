import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Edge implements Comparable<Edge>{
	    int from, to, weight;
	    
	    Edge(int from, int to, int weight){
	        this.from = from; this.to = to; this.weight = weight;
	    }
	    
	    public int compareTo(Edge x){
            int n = this.weight-x.weight;
            
            if (n == 0) {
                n = this.from+this.to-x.from-x.to;
            }
	        return n;
	    }
	}

public class Solution {
    static int[] inSet;
    
    static boolean check (Edge e) {
        int f = inSet[e.from], t = inSet[e.to];
        if (f == -1 && t == -1) return false;
        
        return f == t;
    }
    
    
    static void union (Edge e) {
        int f = inSet[e.from], t = inSet[e.to];
        if (f!=-1) {
            for (int j=1;j<inSet.length;j++) {
                if (inSet[j]==t) {
                    inSet[j]=f;
                }
            }
        }
    }
    
    static int notIn (Edge e) {
        return (inSet[e.from]==-1?1:0) + (inSet[e.to]==-1?1:0);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
	    int n = sc.nextInt(), m = sc.nextInt();
	        
	    int total = 0;
        
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        
        while (m --> 0) {
	            int x = sc.nextInt(), y = sc.nextInt(), weight = sc.nextInt();
	            pq.add(new Edge(x,y,weight));
	    }
        
        int start = sc.nextInt();
        
        inSet = new int[n+1];
        for (int i=0;i<n+1;i++) {
            inSet[i] = i;
        }
        
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            if (!check(e)) {
                total += e.weight;
                union(e);
            }
        }
        
        System.out.println(total);
    }
}