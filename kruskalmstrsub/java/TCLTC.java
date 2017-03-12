import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static class Edge implements Comparable<Edge>{
        int x;
        int y;
        int weight;
        
        public Edge(int x,int y,int weight){
            this.x=x;
            this.y=y;
            this.weight=weight;
        }
        
        public int compareTo(Edge E){
            if (this.weight<E.weight){return -1;}
            else if (this.weight>E.weight){return 1;}
            else{
                return (this.x+this.y)-(E.x+E.y);
            }
        }
    }
    
    //get the representation element of x
    public static int getRepresent(int[] Route, int x){
        int val=Route[x];
        while (val!=x){
            x=val;
            val=Route[x];
        }
        return x;
    }
    
    //given x and y are in two groups (i.e. having two different representation elements)
    //make y's current representation element's representation element to x's current representation element
    public static void union(int[] Route, int x, int y){
        int x_root=getRepresent(Route,x);
        int y_root=getRepresent(Route,y);
        Route[y_root]=x_root;
    }
    
    //0-indexed
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in=new Scanner(System.in);
        int N=in.nextInt(); int M=in.nextInt();
        ArrayList<Edge> A=new ArrayList<Edge>();
        for (int e=0;e<M;e++){
            int x=in.nextInt()-1;int y=in.nextInt()-1;int w=in.nextInt();
            Edge E=new Edge(x,y,w);
            A.add(E);
        }
        Collections.sort(A);
        //create a routing table to implement union-set structure
        //The routing table 's index denote an element
        //its value denote the element's representation element
        int[] Route=new int[N];
        for (int i=0;i<N;i++){Route[i]=i;}
        
        //loop over each edge in order
        long output=0L;
        for (int e=0;e<M;e++){
            Edge E=A.get(e);
            int u=E.x;
            int v=E.y;
            int u_root=getRepresent(Route,u);
            int v_root=getRepresent(Route,v);
            if (u_root!=v_root){
                //if not in the same group, then union them 
                union(Route,u,v);
                //add the edge weight to the output
                output+=E.weight;
            }
            
        }
        System.out.println(output);
    }
}