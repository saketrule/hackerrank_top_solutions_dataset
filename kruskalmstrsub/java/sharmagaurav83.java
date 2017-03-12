import java.util.Scanner;


public class PrimsAlgo {

	/**
	 * @param args
	 */
	public static int [][] ar;
	public static int [] visited;
	public static int [] nodeDistance;
	public static int startNode, sum = 0, visitCount=0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int V, E, max = Integer.MAX_VALUE;
		V = sc.nextInt();
		E = sc.nextInt();
		
		ar = new int [V][V];
		visited = new int [V];
		nodeDistance = new int[V];
		
		for (int i = 0; i < V; i++) {			
			for (int j = 0; j < V; j++) {
				ar[i][j] = -1;
			}
		}
		
		for (int i = 1; i <= E; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int w = sc.nextInt();
			ar[a-1][b-1] = (ar[a-1][b-1] == -1 || ar[a-1][b-1]>w)?w:ar[a-1][b-1];
			ar[b-1][a-1] = ar[a-1][b-1];
		}
		
		startNode = sc.nextInt()-1;
		
		//Print adj matrix representation of graph;		
		/*for (int i = 0; i < V; i++) {			
			for (int j = 0; j < V; j++) {
				System.out.print(ar[i][j] + " ");
			}
			System.out.println();
		}
		*/
		for (int i = 0; i < V; i++) {
			if (i == startNode)
				nodeDistance[i] = 0;
			else
				nodeDistance[i] = max;
		}
		
		//started point is 0
		visited[startNode] = 1;	
		primMST(startNode);
		//System.out.println();
		System.out.println(sum);		
	}
	
	public static void primMST(int index){
		for (int i = 0; i < nodeDistance.length; i++) {
			if (ar[index][i] > -1 && nodeDistance[i] > ar[index][i] ){
				nodeDistance[i] = ar[index][i];
			}
		}
		
		int j = minDist();
		if (j == -1)
			return;
		
		visited[j] = 1;
		
		sum +=nodeDistance[j];
		primMST(j);
	}
	
	public static int minDist(){
		int minDistance = Integer.MAX_VALUE;
		int index = -1; 
		for (int i=0; i<nodeDistance.length; i++){
			if (minDistance>nodeDistance[i] && visited[i] == 0){
				minDistance = nodeDistance[i];
				index = i;				
			}
		}
		return index;
	}
}