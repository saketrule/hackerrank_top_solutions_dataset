/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/
import java.util.Scanner;
public class Solution{
	public int ans=0;
	public int calc(int[][] edges,boolean[] visited,int cur,int N){
		visited[cur]=true;
		int connectedChildren=1;
		for(int i=1;i<=N;++i){
			if(!visited[i]&&edges[cur][i]==1){
				int children=calc(edges,visited,i,N);
				if(children==0||children%2!=0){
					connectedChildren+=children;
				}else{
					ans++;
				}
			}
		}
		return connectedChildren;
	}
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		int N=scanner.nextInt();
		int M=scanner.nextInt();
		int[][] edges = new int[N+1][N+1];
		for(int i=0;i<M;++i){
			int from=scanner.nextInt();
			int to=scanner.nextInt();
			edges[from][to]=1;
			edges[to][from]=1;
		}
		Solution solution = new Solution();
		solution.calc(edges,new boolean[N+1],1,N);
		System.out.println(solution.ans);
	}
}