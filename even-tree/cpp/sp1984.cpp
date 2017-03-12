/* Enter your code here. Read input from STDIN. Print output to STDOUT */
#include <iostream>

using namespace std;

#define GRAPH_SIZE (100+1)

int N,M;
bool graph[GRAPH_SIZE][GRAPH_SIZE];
bool traverse[GRAPH_SIZE];
int count=0;

int dfs(int idx)
{
	int accu=1;
	int ret;
	if( traverse[idx] )
		return 0;

	traverse[idx]=true;

	for( int i=idx+1; i<=N; i++ )
	{
		if( traverse[i] || !graph[idx][i] ) continue;
		if( (ret=dfs(i))==0 )
		{
			count++;
		}
		accu+=ret;
	}
	if( (accu&0x1)==0 )
	{
		return 0;
	}
	else
	{
		return accu;
	}
}

int main(int argc,char** argv)
{
	int x,y;

	cin>>N>>M;

	for( int i=0; i<M; i++ )
	{
		cin>>x>>y;
		graph[x][y]=true;
		graph[y][x]=true;
	}

	dfs(1);

	cout<<count<<endl;
	return 0;
}