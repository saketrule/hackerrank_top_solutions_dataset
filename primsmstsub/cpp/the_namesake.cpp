#include <bits/stdc++.h>
 
using namespace std;
 
#define sz(x) int((x).size())
#define FOR(i,a,b) for(int (i) = (a); (i) <= (b); ++(i))
#define ROF(i,a,b) for(int (i) = (a); (i) >= (b); --(i))
#define rep(i,n) for (int (i) = 0; (i) < (n); ++(i))
#define fe(i,a) for (int (i) = 0; (i) < int((a).size()); ++(i))
#define C(a) memset((a),-1,sizeof(a))
#define inf 1000000000
#define pb push_back
#define ppb pop_back
#define all(c) (c).begin(), (c).end()
#define pi 2*acos(0.0)
#define sqr(a) (a)*(a)
#define mp(a,b) make_pair((a), (b))
#define X first
#define Y second
#define rz resize
#define endl '\n'

typedef vector<int> ivi;
typedef vector < ivi > ivvi;
typedef unsigned long long ull;
typedef long long int ll;
typedef vector< ll > vi;
typedef vector < vi > vvi;
typedef pair<int, int> pii;

int main ()
{
   ios_base::sync_with_stdio(0);
   cin.tie(0);
   int t,n,m,s,dis[3005],x,y,r,z=0,ans=0;
   vector < vector < pair< ll ,int > > > g; 
   t=1;
   ivi vis(3005);
   while(t--)
   {
   	cin>>n>>m;
   	priority_queue < pair<long long, int> > q;
   	g.rz(0);
   	C(dis);
   	g.rz(n+2);
   	rep(i,m)
   	{
   		cin>>x>>y>>r;
   		g[x].pb({y,r});
   		g[y].pb({x,r});
   	}
   	cin>>s;
   	q.push({0,s});
   	dis[s]=0;
   	FOR(i,1,n)
   	{
   		if(i!=s)
   		{
   		q.push(make_pair(-INT_MAX, i));
   			dis[i]=INT_MAX;
   		}
   	}
   	while(!q.empty())
   	{
   		int cur=q.top().Y;
   		ll curdis=-q.top().X;
   		q.pop();
   		if(vis[cur])continue;
   		vis[cur]=1;
   		z++;
   		ans+=curdis;
   		if(z==n)break;
   			
   		fe(i,g[cur])
   		{
   			int to=g[cur][i].X,w=g[cur][i].Y;
   			if(w < dis[to])
   			{
   				dis[to]=w;
   				q.push({-dis[to],to});
   				//z++;
   				//ans+=w;
   			}
   		}
   	}
   	
   	cout<<ans<<endl;
   }
	return 0;
} 