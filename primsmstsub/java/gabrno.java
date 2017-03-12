import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Node implements Comparable<Node>{
    int val, weight;
    
    Node(int val, int weight){
        this.val = val; this.weight = weight;
    }
    
    public int compareTo(Node x){
        return Integer.compare(this.weight, x.weight);
    }
}


class Edge implements Comparable<Edge>{
    int from, to, weight;
    
    Edge(int from, int to, int weight){
        this.from = from; this.to = to; this.weight = weight;
    }
    
    public int compareTo(Edge x){
        return Integer.compare(this.weight, x.weight);
    }
}

public class Solution {
    
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        
        int total = 0;
        ArrayList<ArrayList<Node>> edge = new ArrayList<ArrayList<Node>>(n+1);
        
        for(int i=0; i<n+1; i++) edge.add(new ArrayList<Node>(n+1));
        
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        
        while (m --> 0) {
            int x = sc.nextInt(), y = sc.nextInt(), weight = sc.nextInt();
            edge.get(x).add(new Node(y,weight));
            edge.get(y).add(new Node(x,weight));
        }
        
        int start = sc.nextInt();
        
        boolean[] book = new boolean[n+1];
        
        for (boolean b : book) {
            b = false;
        }
        
        
        for (Node n1 : edge.get(start)) {
            pq.add(new Edge(start,n1.val,n1.weight));
        }
        
        book[start] = true;
        
        
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            
           
            if (!book[e.to]) {
                book[e.to]=true;
                total += e.weight;
                
                for (Node n1 : edge.get(e.to)) {
                    pq.add(new Edge(e.to,n1.val,n1.weight));
                }
            }
            
            
        }
        
        System.out.println(total);
    }
}