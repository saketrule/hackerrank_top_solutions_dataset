#include <stdio.h>
#define INF 1000000

int graph[3002][3002];
int edge[3002 * 3002][3];
int parent[3002];
long int sum = 0;
int temp[3002 * 3002][3];

void makeSet(int v1){
	parent[v1] = v1;
}

int findParent(int v1){

	while (parent[v1] != v1){
		v1 = parent[v1];
	}

	return v1;
}

void merge(int node[3002 * 3002][3], int l, int mid, int r){

	int i = l;
	int j = mid;
	int k = l;

	while (i < mid && j < r){
		if (node[i][2] < node[j][2]){
			temp[k][0] = node[i][0];
			temp[k][1] = node[i][1];
			temp[k++][2] = node[i][2];
			i++;
		}
		else if (node[i][2] > node[j][2]){
			temp[k][0] = node[j][0];
			temp[k][1] = node[j][1];
			temp[k++][2] = node[j][2];
			j++;
		}
		else{
			if ((node[j][0] + node[j][1] + node[j][2]) < (node[i][0] + node[i][1] + node[i][2])){
				temp[k][0] = node[j][0];
				temp[k][1] = node[j][1];
				temp[k++][2] = node[j][2];
				j++;
			}
			else{
				temp[k][0] = node[i][0];
				temp[k][1] = node[i][1];
				temp[k++][2] = node[i][2];
				i++;
			}
		}
	}

	while (i < mid){
		temp[k][0] = node[i][0];
		temp[k][1] = node[i][1];
		temp[k++][2] = node[i][2];
		i++;
	}

	while (j < r){
		temp[k][0] = node[j][0];
		temp[k][1] = node[j][1];
		temp[k++][2] = node[j][2];
		j++;
	}

	for (i = l; i < r; i++){
		node[i][0] = temp[i][0];
		node[i][1] = temp[i][1];
		node[i][2] = temp[i][2];
	}
}

void sort(int node[3002 * 3002][3], int l, int r){
	if ((r - l) < 2){
		return;
	}

	int mid = l + (r - l) / 2;

	sort(node, l, mid);
	sort(node, mid, r);

	merge(node, l, mid, r);
}

void unionS(int v1, int v2, int w){

	int a = findParent(v1);
	int b = findParent(v2);

	if (a == b){
		return;
	}
	sum += w;
	parent[a] = b;
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
			parent[j] = j;
		}

		for (i = 0; i<E; i++){
			int v1, v2, w;
			scanf("%d", &v1);
			scanf("%d", &v2);
			scanf("%d", &w);

			if (v2 < v1){
				int t = v1;
				v1 = v2;
				v2 = t;
			}

			if (graph[v1][v2] == -1 || graph[v1][v2]>w){
				graph[v1][v2] = w;
			}
		}

		int k = 0;
		for (i = 1; i <= V; i++){
			for (j = 1; j <= V; j++){
				if (graph[i][j]>-1){
					edge[k][0] = i;
					edge[k][1] = j;
					edge[k++][2] = graph[i][j];
				}
			}
		}

		int start;
		scanf("%d", &start);

		sort(edge, 0, k);

		for (i = 0; i < k; i++){
			unionS(edge[i][0], edge[i][1], edge[i][2]);
		}


		printf("%ld", sum);
	}
	return 0;
}