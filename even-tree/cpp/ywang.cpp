/* Enter your code here. Read input from STDIN. Print output to STDOUT */
#include<iostream>
#include<cstdio>
#include<vector>
using namespace std;
/*
struct Edge
{
	int a,b;
	Edge(int a_,int b_):a(a_),b(b_){}
}E[110];
*/
int result = 0;
vector<int>E[105];
int nSon[105];
int getS(int id,int pare)
{
	int sum = 0;
	for(vector<int>::const_iterator itr = E[id].begin();itr!=E[id].end();++itr)
	{
		if(*itr == pare)
			continue;
		int t = getS(*itr,id);
		sum += t;
	}
	++sum;
	nSon[id] = sum;
	return sum;
}
void proc(int id,int pare)
{
	for(vector<int>::const_iterator itr = E[id].begin();itr!=E[id].end();++itr)
	{
		if(*itr == pare)
			continue;
		if(!(nSon[*itr] & 0x1))
			++result;
		proc(*itr,id);
	}
}

int main()
{
	int N,M;
	scanf("%d %d",&N,&M);
	for(int i=0;i<=N;++i)
		nSon[i] = 0;
	for(int i=0;i<M;++i)
	{
		int a,b;
		scanf("%d %d",&a,&b);
		E[a].push_back(b);
		E[b].push_back(a);
	}
	getS(1,0);
	proc(1,0);
	cout << result;
}
