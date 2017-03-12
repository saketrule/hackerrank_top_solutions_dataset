import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.Scanner;

public class Solution {

    public static int[] disAr;
	public static int[] visited;
	public static int weight = 0;
	public static int max = 999999999;
	public static int[] mSet;
	public static int index = 0;
	public static int ans = 0;
	public static int counterE = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		int[][] graph = new int[V][V];
		disAr = new int[V];
		visited = new int[V];
		mSet = new int[V];
		
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				graph[i][j] = -1;
			}
		}
		
		
		for (int i = 0; i < E; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int weight = sc.nextInt();
			if(weight < graph[start - 1][end - 1] ){
				graph[start - 1][end - 1] = weight;
				graph[end - 1][start - 1] = weight;
			}else if(graph[start - 1][end - 1] == -1){
				graph[start - 1][end - 1] = weight;
				graph[end - 1][start - 1] = weight;
			}
		}

		
		for (int i = 0; i < V; i++) {
			disAr[i] = max;
			visited[i] = 0;
		}
		
		prinmsTraversal(graph, 0);
		System.out.println(ans);
	}

	public static void prinmsTraversal(int[][] graph, int vertex) {
		if(counterE == graph.length-1)
			return;
		
		for (int i = 0; i < graph.length; i++) {
			int currentWeight = graph[vertex][i];
			if (currentWeight > -1) {
				if (visited[i] == 0 && disAr[i] > currentWeight) {
					disAr[i] = currentWeight;
				}
			}
		}
		//System.out.println(vertex+1);
		mSet[index] = vertex;
		visited[vertex] = 1;
		index++;
		counterE++;
		// find minimum of vertex
		int min = max;
		int nextVertex = -1;
		for (int i = 0; i < disAr.length; i++) {
			if (visited[i] == 0 && min > disAr[i]) {
				min = disAr[i];
				nextVertex = i;
			}
		}
		ans = ans  +  min;	
		//counterE++;
		prinmsTraversal(graph,nextVertex);
	}

}
