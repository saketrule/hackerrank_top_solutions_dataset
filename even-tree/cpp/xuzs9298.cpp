/* Enter your code here. Read input from STDIN. Print output to STDOUT */
#include <stdio.h>
#include <iostream>

using namespace std;

int map[101][101];
int N,M,u,v;
int mask[101];
int subtree[101];
int dfs(int node)
{
	mask[node]=1;
	int result=0;
	for(int i=1;i<=N;i++)
	{
		if(map[node][i]==1&&mask[i]==0)
		{
			if(subtree[i]==0)
				subtree[i]=dfs(i);
			result +=subtree[i];
		}
	}
	result++;
	return result;
}


int main()
{
	
	cin >> N >> M;
	
	for(int i=0;i<101;i++)
		for(int j=0;j<101;j++)
			map[i][j] =0;
	while(M--)
	{
		cin >> u >> v;
		map[u][v]=1;
		map[v][u]=1;
	}
	
	for(int i=0;i<101;i++)
	{
		mask[i]=0;
		subtree[i]=0;
	}
	subtree[1]=dfs(1);
	int final_result =0;
	for(int i=2;i<=N;i++)
	{
		if(subtree[i]%2==0)
			final_result++;
	}
	cout << final_result << endl;

}