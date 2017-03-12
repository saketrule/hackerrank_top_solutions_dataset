#include <bits/stdc++.h>
#define ff first
#define ss second
#define sz size()
#define pb push_back
#define mp make_pair
#define pf push_front 
#define ff first
#define ss second
#define SET(a,b) memset(a,b,sizeof(a))
#define all(c) (c).begin(),(c).end() 
#define tr(c,i) for(typeof((c).begin() i = (c).begin(); i != (c).end(); i++) 
#define present(c,x) ((c).find(x) != (c).end()) 
#define cpresent(c,x) (find(all(c),x) != (c).end()) 
#define SL(n) scanf("%lld",&n)
#define PL(n) printf("%lld",n)
#define _ ios_base::sync_with_stdio(0);cin.tie(0)
using namespace std;
typedef long long int LL;
class node{
public:
	LL ss;
	LL length;
};
typedef vector<LL> VL;
typedef vector<int> VI; 
typedef vector<VL> VVL; 
typedef pair<LL,LL> PLL; 
typedef stack<node> st;
typedef queue<node> qu;
typedef pair<int ,int> II;
typedef vector<II> VII;

// map<string,string>::iterator it;
//stack: empty(),push(),pop()
//queue: empty(), push(), pop(), front()

class Edge{
public:
	LL from;
	LL to;
	LL weight;
};
bool ValueCmp( Edge const & a, Edge const & b)
{
    return a.weight < b.weight;
}
int main()
{
	LL n,m,i,j,counter=0;
	cin >> n >> m;
	LL myhouse[n];
	VL a[n];
	for(i=0;i<n;i++)
	{
		a[i].pb(i);
		myhouse[i]=i;
	}
	Edge edges[m];
	for(i=0;i<m;i++)
	{
		cin >> edges[i].from;
		cin >> edges[i].to >> edges[i].weight;
		edges[i].from--;
		edges[i].to--;
		
	}
	cin >> i;
	sort(edges,edges+m,ValueCmp);
	for(i=0;i<m;i++)
	{
		if(myhouse[edges[i].from]!=myhouse[edges[i].to])
		{
			counter+=edges[i].weight;
			//cout << counter << " ";
			if(a[myhouse[edges[i].from]].size()>=a[myhouse[edges[i].to]].size())
			{
				LL l=a[myhouse[edges[i].to]].size();
				LL z=myhouse[edges[i].to];
				//cout << l << " ";
				for(j=0;j<l;j++){
					a[myhouse[edges[i].from]].pb(a[z][j]);
					myhouse[a[z][j]]=myhouse[edges[i].from];
				}
				a[z].clear();
			}
			else
			{
				LL l=a[myhouse[edges[i].from]].size();
				LL z=myhouse[edges[i].from];
				//cout << l << " ";
				for(j=0;j<l;j++){
					a[myhouse[edges[i].to]].pb(a[z][j]);
					myhouse[a[z][j]]=myhouse[edges[i].to];
				}
				a[z].clear();                                                                                                       

			}

		}
		//cout << a[1].size() <<" "<<a[4].size()<< endl;;
		//for(j=0;j<n;j++)
		//	cout << myhouse[j] << " ";
		//cout << endl;
	}
	cout << counter << endl;


	return 0;
}

