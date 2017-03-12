#include <bits/stdc++.h>
using namespace std;
#define pb push_back
#define X first
#define Y second
#define ll long long int
vector< pair < ll , pair < ll,ll > > > V;
ll n,m;
vector< ll > parent,k;
ll findp(ll a)
{
  if(parent[a]!=a)
  	parent[a]=findp(parent[parent[a]]);
  return parent [a];

}
ll  MST()
{
	ll i,j,weight=0,x,y;
	for(i=1;i<=n;i++)
	{
		parent[i]=i;
	}
	i=0;j=0;
	//cout<<"here\n";
	while(i<n-1)
	{ 
       x=findp(V[j].Y.X);
       y=findp(V[j].Y.Y);
      // cout<<x<<" "<<y<<endl;
       if(x==y)
       {
       	j++;
       	continue;
       }
       if(k[x]>k[y])
       {
       	parent[y]=x;
       }
       else if(k[y]>k[x])
       {
       	parent[x]=y;
       }
       else
       {
       	parent[y]=x;
       	k[x]++;
       }
        weight+=V[j].X;
        j++;i++;
	}

	return weight;
    
} 
bool cmp (pair < ll , pair < ll,ll > > a,pair < ll , pair < ll,ll > > b)
{
	if(a.X!=b.X)return a.X < b.X;
	return a.X +a.Y.X +a.Y.Y < b.X+b.Y.Y+a.Y.X;
}
int main()
{
  ll  x,y,w,s;
	//vector < pair < int, pair < int ,int > > V;
   cin>>n>>m;
   parent.resize(n+2);
   k.resize(n+2);
   for (int i = 0; i < m; ++i)
   {
   	cin>>x>>y>>w;
   	V.pb({w,{x,y}});
   }
   sort(V.begin(),V.end(),cmp);
   cin>>s;
   cout<<MST()<<endl;
   return 0;
}