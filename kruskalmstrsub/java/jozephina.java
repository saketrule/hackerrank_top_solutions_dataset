import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
   class Edge implements Comparable<Edge>{
         public int u, v, w;
         public Edge(int u1, int v1, int w1) {
             u = u1;
             v = v1;
             w = w1;
         }
         public int compareTo(Edge e) {
             if (w != e.w) {
                 return w - e.w;
             } else if ( u + v != e.u + e.v) {
                 return u + v - e.u - e.v;
             } else 
                 return u - e.u;
         }
     }
    class Node {
        public int rank;
        public int parent;
        public Node (int rank, int parent) {
            this.rank = rank;
            this.parent = parent;
        }
    }
    class UnionFind {
        Node [] uf; 
        public UnionFind(int n) {
            uf = new Node[n];
            for (int i = 0; i < n; i ++) {
                uf[i] = new Node(1, i);
            }
        }
        int getParent(int i) {
            while (uf[i].parent != i) {
                i = uf[i].parent;
            }
            return i;
        }
        public boolean isJoint(int i, int j) {
            return getParent(i) == getParent(j);
        }
        public void union(int i, int j){
            Node pI = uf[getParent(i)];
            Node pJ = uf[getParent(j)];
            if (pI.equals(pJ)) {
                return;
            }
            if (pI.rank < pJ.rank) {
                pI.parent = pJ.parent;
                pJ.rank += pI.rank;
            } else {
                pJ.parent = pI.parent;
                pI.rank += pJ.rank;
            }
        }
    }
public class Solution {
  

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Set<Edge> eSet = new TreeSet<Edge>();
        while(m-- > 0) {
            eSet.add(new Edge(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt()));
        }
        int cnt = 0;
        int w = 0;
        UnionFind uf = new UnionFind(n);
        for (Edge e: eSet){
            if (!uf.isJoint(e.u, e.v)){
                w += e.w;
                cnt++;
                if (cnt == n - 1) {
                    System.out.println(w);
                }
                uf.union(e.u, e.v);
            }
        }
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}