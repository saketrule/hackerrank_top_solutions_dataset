import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    Scanner sc=new Scanner(System.in);
		//int T=sc.nextInt();
		//for(int t=0;t<T;t++){
			int V=sc.nextInt();
			int E=sc.nextInt();
			int[][] graph=new int[V][V];
            for(int u=0;u<V;u++){
                for(int v=0;v<V;v++){
                    graph[u][v]=-1;
                }
            }
			for(int i=0;i<E;i++){
				int u=sc.nextInt();
				int v=sc.nextInt();
				int w=sc.nextInt();
				if(graph[u-1][v-1]==-1)
					graph[u-1][v-1]=graph[v-1][u-1]=w;
				else if (w<graph[u-1][v-1])
					graph[u-1][v-1]=graph[v-1][u-1]=w;
			}
			int S=sc.nextInt();
			int[] dist= new int[V];
			boolean[] sptset= new boolean[V];
 
			for(int i=0;i<V;i++){
				dist[i]=Integer.MAX_VALUE;
			}
			dist[S-1]=0;
			for(int count=0;count<V;count++){
				int u=minset(dist,sptset);
				sptset[u]=true;
 
				for(int v=0;v<V;v++){
					if(!sptset[v] && graph[u][v]>-1 && dist[u]!=Integer.MAX_VALUE && dist[v]>graph[u][v])
					dist[v]=graph[u][v];
				}
			}
			int answer=0;
			for(int i=0;i<V;i++){
				answer+=dist[i];
			}
			System.out.println(answer);
		//}
 
	}
 
	private static int minset(int[] dist, boolean[] sptset){
		int min_index=0;
		int min=Integer.MAX_VALUE;
		for(int i=0;i<dist.length;i++){
			if(!sptset[i] && dist[i]<min){
				min=dist[i];
				min_index=i;
			}
		}
		return min_index;
	}
}