import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
class Edge{
    int from,to,weight;
    public Edge(int i,int j,int w){
        from=i;
        to=j;
        weight=w;
    }
}
class cmpr implements Comparator<Edge>{

	@Override
	public int compare(Edge o1, Edge o2) {
		// TODO Auto-generated method stub
		return o1.weight-o2.weight;
	}
    
}
class Graph{
    ArrayList<ArrayList<Edge>> adjList=new ArrayList<ArrayList<Edge>>();
    Queue<Edge> q=new PriorityQueue<Edge>(100001,new cmpr());
    int size;
    boolean visited[]=new boolean[100001];
    void init(int n){
    	size=n;
        for(int i=0;i<=n;i++){
            adjList.add(i,new ArrayList<Edge>());
            visited[i]=false;
        }
    }
    void addEdge(int x,int y,int weight){
        adjList.get(x).add(new Edge(x,y,weight));
        adjList.get(y).add(new Edge(y,x,weight));
        q.add(new Edge(x,y,weight));
        q.add(new Edge(y,x,weight));
    }
    int prims(int root){
    	visited[root]=true;
    	int num=1,cost=0;
        while(!q.isEmpty()){
        	Stack<Edge> s=new Stack<Edge>(); 
        	while(true){
            	Edge e=q.poll();
        		if(visited[e.from]){
        			if(!visited[e.to]){
        				visited[e.to]=true;
        				num++;
        				cost+=e.weight;
        				break;
        			}
            	}else s.add(e);
        	}
        	while(!s.isEmpty())
        		q.add(s.pop());
        	if(num==size)
        		return cost;	
        }
        return cost;
    }
}
public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int n,m;
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        m=sc.nextInt();
        Graph g=new Graph();
        g.init(n);
        for(int i=0;i<m;i++){
            int a,b,w;
            a=sc.nextInt();
            b=sc.nextInt();
            w=sc.nextInt();
            g.addEdge(a,b,w);
        }
        int root=sc.nextInt();
        System.out.println(g.prims(root));
    }
}