/*
Minimal Spanning Tree using union find disjoint algorithm
*/
#include<iostream>
#include<queue>
#include<vector>
#include<map>
#include<algorithm>
using namespace std;
#define edge pair<int,int>
vector< pair<int,edge> > Graph,MST;
int parent[10001],m,n;
int findset(int x)
{
	if(x!=parent[x])
	{
		parent[x]=findset(parent[x]);
	}
	return parent[x];
}
void kruskal()
{
	sort(Graph.begin(),Graph.end());
	long long total=0;
	for(int i=0;i<m;i++)
	{
		//cout<<Graph[i].first<<" "<<Graph[i].second.second<<" "<<Graph[i].second.first<<endl;
		int pu=findset(Graph[i].second.first);
		int pv=findset(Graph[i].second.second);
		if(pu!=pv)
		{
			total+=Graph[i].first;
			MST.push_back(Graph[i]);
			parent[pu]=parent[pv];
		}
	}
	cout<<total<<endl;
}
int main()
{
	cin>>n>>m;
	for(int i=0;i<n;i++)
	{
		parent[i]=i;
	}
	Graph.clear();
	MST.clear();
	int p,q,r;
	for(int i=0;i<m;i++)
	{
		cin>>p>>q>>r;
		Graph.push_back(make_pair(r,edge(p,q)));
	}
    int s;
    cin>>s;
	kruskal();
	return 0;
}
