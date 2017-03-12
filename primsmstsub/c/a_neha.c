#include <stdio.h>
#define INF 1000000
//#define scanf scanf_s
//#define printf printf_s

int graph[3002][3002];
int visited[3002];
int path[3002], totalCnt;

int findMin(int V){
	int i, index = -1, min = INF;

	for (i = 1; i <= V; i++){
		if (!visited[i] && min >= path[i]){
			min = path[i];
			index = i;
		}
	}
	return index;
}

void shortestPath(int V, int node){


	int i;
	visited[node] = 1;
	totalCnt++;

	if (totalCnt == V){
		return;
	}



	for (i = 1; i <= V; i++){
		if (graph[node][i]>-1 && !visited[i] && path[i]>(graph[node][i])){
			path[i] = graph[node][i];
		}
	}
	int n = findMin(V);
	/*if (n == -1){
	return;
	}*/
	shortestPath(V, n);
}

int main(){
	int T, tc, i, V, E, j;

	//scanf("%d", &T);
	T = 1;
	for (tc = 0; tc<T; tc++){
		scanf("%d", &V);
		scanf("%d", &E);

		for (j = 1; j <= V; j++){
			for (i = 1; i <= V; i++){
				graph[j][i] = -1;
			}
			path[j] = INF;
			visited[j] = 0;
		}
		totalCnt = 0;

		for (i = 0; i<E; i++){
			int v1, v2, w;
			scanf("%d", &v1);
			scanf("%d", &v2);
			scanf("%d", &w);


			if (graph[v1][v2] == -1 || graph[v1][v2]>w){
				graph[v1][v2] = w;
				graph[v2][v1] = w;
			}
		}

		int start;
		scanf("%d", &start);

		path[start] = 0;
		shortestPath(V, start);

		long int sum = 0;
		for (i = 1; i <= V; i++){
			if (path[i] == INF){
				path[i] = 0;
			}
			/*if (i != start){
			printf("%d ", path[i]);
			}*/
			sum += path[i];
		}
		printf("%ld", sum);
	}
	return 0;
}