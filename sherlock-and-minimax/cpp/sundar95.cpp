#include<iostream>
#include<cstdio>
#include<vector>
#include<string>
#include<queue>
#include<map>
#include<set>
#include<algorithm>
#include<stack>
#include<cmath>
using namespace std;
#define f(i,a,b) for(i=a;i<b;i++)
#define rep(i,n) f(i,0,n)
#define pb push_back
#define ss second
#define ff first
#define vi vector<int>
#define vl vector<ll>
#define s(n) scanf("%d",&n)
#define ll long long
#define mp make_pair
#define PII pair <int ,int >
#define PLL pair<ll,ll>
#define inf 1000*1000*1000+5
#define v(a,size,value) vv a(size,value)
#define sz(a) a.size()
#define all(a) a.begin(),a.end()
#define tri pair < int , PII >
#define TRI(a,b,c) mp(a,mp(b,c))
#define xx ff
#define yy ss.ff
#define zz ss.ss
#define vii vector < PII >
#define vll vector< PLL >
#define viii vector < tri >
#define vs vector<string>
#define foreach(v,c) for( typeof((c).begin()) v = (c).begin();  v != (c).end(); ++v)
#define fill(a,v)  memset(a,v,sizeof a)
#define printall(a) rep(i,sz(a))cout<<a[i]<<endl;
 ll pow(ll num,ll power){if(power==1)return num;	ll t = pow(num,power/2);return power%2?t*t*num:t*t;}
int check(int);
int binarysearch(int len)//finds last 1 in 11111000
{int lo = 1,hi = len,mid,flag;while(lo<=hi){mid = hi+lo>>1;(flag = check(mid))?lo = mid+1:hi = mid-1;}return mid-1+flag;}
const int N = 105;
int n;int L,M;
int a[N];int value;
int check(int p)
{
	int i;
//	if(p<L || p>M)return 0;
	rep(i,n)
	{
		int cand1 = a[i] - p,cand2 = a[i] + p;
		int j;
		int temp1 = 0,temp2 = 0;temp1 = temp2 = 2*inf;
		if(cand1 <= M && cand1 >= L)
		{
			rep(j,n)
			{
				temp1 = min(temp1,abs(a[j] - cand1));
				//	temp2 = min(temp2,abs(a[i] - cand2));
	    	}
	    	if(temp1 == p ){value = cand1; return 1;}
	   }
	   if(cand2 <= M && cand2 >=L)
	   {
			rep(j,n)
			temp2 = min(temp2,abs(a[j]-cand2));
			if(temp2==p){value = cand2;return 1;}
	   }
   }
   return 0;
}
		
int main()
{
      ios::sync_with_stdio(false);
      int i,j,t;
      cin>>n;
      rep(i,n)cin>>a[i];
      cin>>L>>M;
	  int t1 = binarysearch(1000*1000*1000+5);
	  cout<<value;
	  cin>>i;
}

