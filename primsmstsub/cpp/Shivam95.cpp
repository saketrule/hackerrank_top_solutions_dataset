#include<bits/stdc++.h>
#define ll long long int
#define li long int
#define PB push_back
#define P push
#define MP make_pair
#define F first
#define S second 

using namespace std;
li parent[3001];

struct comp
{
	bool operator()(pair<ll,li> n1, pair<ll,li> n2)
	{
		return n1.F>n2.F;
	}
};


void solve()
{
	li n;
	cin>>n;
	vector<pair<li,li> >* v = new vector<pair<li,li> >[n+1];
	li m;
	cin>>m;
	for(li i = 0;i<m;i++)
	{
		li u,v1,w;
		cin>>u>>v1>>w;
		v[u].PB(MP(v1,w));
		v[v1].PB(MP(u,w));
	}
	ll inf = pow(10,9)+7;
	ll* val = new ll[3001];
	int* vis = new int[3001];
	priority_queue<pair<ll,li> ,vector<pair<ll,li> >,comp> pq;
	for(int i = 0;i<=3000;i++)
	{
		parent[i] = -1;
		val[i] = inf;
		vis[i] = -1;
	}
	//vector<li> dij;
	li f1;
	li f11;
	cin>>f1;
	f11 = f1;
	val[f1] = 0;parent[f1] = f1;//dij.PB(f1);
	ll f2 = 0;
	pq.P(MP(f2,f1));
	while(!pq.empty())
	{
		f1 = pq.top().S;
		pq.pop();
		if(vis[f1] == 1)
		continue;
		vis[f1] = 1;
		for(int i = 0;i<v[f1].size();i++)
	{
		if(vis[v[f1][i].F] == -1)
		{
		val[v[f1][i].F] = min(val[v[f1][i].F],(ll)v[f1][i].S);
		parent[v[f1][i].F] = f1;
		pq.P(MP(val[v[f1][i].F],v[f1][i].F));
	}
	else continue;
	}
	
}
ll ans = 0;
for(li i = 1;i<=n;i++)
{
	if(i==f11)
	continue;
	ll temp = -1;
	if(val[i] != inf)
	ans += val[i];
	//else cout<<temp<<" ";
}
cout<<ans<<endl;
}

int main()
{
//	int t;
//	cin>>t;
//	while(t>0)
//	{
		solve();
//		t--;
		//cout<<endl;
//	}
}