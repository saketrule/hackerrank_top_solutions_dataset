import java.util.Scanner;

public class DijkstraAlgo {
	
	static int v;
	static int e;
	static int adjMat[][];
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int startVertex;
			v = sc.nextInt();
			e = sc.nextInt();
			adjMat = new int[v+1][v+1];
			for (int i = 0; i < v+1; i++) {
				for (int j = 0; j < v+1; j++) {
					adjMat[i][j] = -1;
				}
			}
			for (int j = 0; j <e; j++) {
				int v1 = sc.nextInt();
				int v2 = sc.nextInt();
				int w = sc.nextInt();
				adjMat[v1][v2] =(adjMat[v1][v2] == -1 || adjMat[v1][v2]>w)?w:adjMat[v1][v2];
				adjMat[v2][v1] = adjMat[v1][v2];
			}
			startVertex = sc.nextInt();
			Algo(startVertex);
	}
	
	public static void Algo(int startNode){
		int dist[] = new int[v+1];
		boolean[] visited  = new boolean[v+1];
		int sum = 0;
		for (int i = 0; i < dist.length; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[startNode] = 0;
		
		for (int i = 0; i < v; i++) {
			
			int u = min(dist, visited);
			
			visited[u] = true;
			for (int j = 0; j < v+1; j++) {
				if(adjMat[u][j]>-1 && !visited[j] && adjMat[u][j]<dist[j] && dist[u]!=Integer.MAX_VALUE){
					dist[j] = adjMat[u][j];
				}
			}
		}
		//print result
		for (int i = 1; i < v+1; i++) {
			sum+=dist[i];
		}
		System.out.println(sum);
	}
	
	public static int min(int[]dist, boolean[]visited){
		int min = (int)Double.POSITIVE_INFINITY, min_index = -1;
		for (int i = 0; i < v+1; i++) {
			if(!visited[i] && dist[i]<min){
				min = dist[i];
				min_index = i;
			}
		}
		return min_index;
	}

}
