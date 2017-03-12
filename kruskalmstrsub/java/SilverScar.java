import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        int N = Integer.parseInt(stdIn.next());
        int M = Integer.parseInt(stdIn.next());
        
        Edge[] graph = new Edge[M];
        int[] parent = new int[N];
        int mst=0;
        
        for(int i=0;i<N;i++){
            parent[i] = -1;
        }
        
        for(int i=0;i<M;i++){
            int v = Integer.parseInt(stdIn.next())-1;
            int w = Integer.parseInt(stdIn.next())-1;
            int wt = Integer.parseInt(stdIn.next());
            graph[i] = new Edge(v,w,wt);
        }
        
        quickSort(graph,0,M-1);
        
        for(int i=0;i<M;i++){
            int v1 = graph[i].v;
            int v2 = graph[i].w;
            if(find(parent,v1)!=find(parent,v2)){
                union(parent,v1,v2);
                mst+=graph[i].weight;
            }
        }
        
        System.out.println(mst);
        
    }
    
    static void quickSort(Edge[] ar, int l, int h) {
        if(l<h){
            int x = partition(ar,l,h);
            quickSort(ar,l,x-1);
            quickSort(ar,x+1,h);
        }
    }
    
    static int partition(Edge[] ar, int l, int h){
        int i=l-1;
        int p = ar[h].weight;
        int g = ar[h].gravity;
        for(int j=l;j<h;j++){
            if(ar[j].weight<p || (ar[j].weight==p && ar[j].gravity < g)){
                i++;
                swap(ar,i,j);
            }
        }
        swap(ar,i+1,h);
        return i+1;
    }
    
    static void swap(Edge[] ar, int a, int b){
        int v = ar[a].v;
        int w = ar[a].w;
        int weight = ar[a].weight;
        int gravity = ar[a].gravity;
        ar[a].v=ar[b].v;
        ar[a].w=ar[b].w;
        ar[a].weight=ar[b].weight;
        ar[a].gravity=ar[b].gravity;
        ar[b].v = v;
        ar[b].w = w;
        ar[b].weight = weight;
        ar[b].gravity = gravity;
    }
    
    public static int find(int[] parent, int v){
        if(parent[v]==-1)
            return v;
        else
            return find(parent,parent[v]);
    }
    
    public static void union(int[] parent, int v1, int v2){
        int x = find(parent, v1);
        int y = find(parent, v2);
        parent[x] = y;
    }
}

class Edge{
    int v;
    int w;
    int weight;
    int gravity;
    
    public Edge(int v, int w, int weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
        this.gravity = v+w+weight;
    }
}