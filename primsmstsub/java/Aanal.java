import java.math.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan=new Scanner(System.in);
        //int T=scan.nextInt();
        for(int t=0;t<1;t++)
            {
            int N=scan.nextInt();
            int M=scan.nextInt();
            Node[] nodes=new Node[N];
            for(int i=0;i<N;i++)
                {
                
                nodes[i]=new Node();
            }
            
            for(int i=0;i<M;i++)
                {
                int n1=scan.nextInt();
                int n2=scan.nextInt();
                int r=scan.nextInt();
                
                nodes[n1-1].neigh.add(new Edge(nodes[n2-1],r));
                nodes[n2-1].neigh.add(new Edge(nodes[n1-1],r));
                
                
                
            }
            
            int S=scan.nextInt();
            
            PriorityQueue<Edge> pq = new PriorityQueue<Edge>(10,new Comparator<Edge>(){
                public int compare(Edge n1,Edge n2)
                    {
                    if(n1.distance<=n2.distance)
                        return -1;
                    else
                        return 1;
                }
            });
            
            for(Edge e:nodes[S-1].neigh)
                if(!e.n.isProcessed)
                    pq.add(e);
           nodes[S-1].isProcessed=true;
            int weight=0;    
             
            while(!pq.isEmpty())
                {
                
                Edge e=pq.remove();
                if(!e.n.isProcessed)weight+=e.distance;
                
                e.n.isProcessed =true;
                
                
           //     System.out.println(e.distance+" is added");
               e.inQueue=false;
                
               for(Edge e1 : e.n.neigh)
                    {
                     //   System.out.println(e1.distance+" is listed");
                        if(!e1.inQueue && !e1.n.isProcessed)
                            {
                            pq.add(e1);
                     //       System.out.println(e1.distance+" is added to queue");
                            
                        }
                        
                    }
                }
            
            System.out.println(weight);
            }
            
            
        }
            
            
  }
    
    


class Node{
    int distance=Integer.MAX_VALUE;
    ArrayList<Edge> neigh=new ArrayList<Edge>();
    
    boolean isProcessed=false;
    
}

class Edge{
    Node n;
    int distance;
    boolean inQueue=false;
    
    public Edge(Node n1,int d)
        {
        n=n1;
        distance=d;
    }
}