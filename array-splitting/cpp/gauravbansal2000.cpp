#include <bits/stdc++.h>

#define vi   vector<int>
#define pb   push_back
#define vvi  vector<vector<int> >
#define pii  pair<int,int>
#define ll   long long 
#define mp   make_pair
#define inf 0x7fffffff
double eps = 1e-9;
using namespace std;
int  arr[100000];
int recurse(int i,int j)
{
	if(j<=i)
	{
		return 0;
	}
	ll total=0;
	for(int k=i;k<=j;k++)
	{
		total+=arr[k];
	}
	if(total%2!=0)
	{
		return 0;
	}
	ll curr=0;
	for(int k=i;k<=j;k++)
	{
		curr+=arr[k];
		if(curr==total/2)
		{
			return max(recurse(i,k)+1,recurse(k+1,j)+1);
		}
	}
	return 0;

}
int main()
{
	//freopen("i.txt","r",stdin);
	std::ios::sync_with_stdio(false);
	int t;
	cin>>t;
	while(t--)
	{
		int n;
		cin>>n;
		for(int i=0;i<n;i++)
		{
			cin>>arr[i];
		}
		cout<<recurse(0,n-1)<<"\n";
	}
	return 0;
}