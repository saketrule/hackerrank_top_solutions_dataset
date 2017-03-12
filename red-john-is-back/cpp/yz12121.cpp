#include<iostream>
#include<string.h>
#include<vector>
using namespace std;

long long f[50];

#define MAXN 300000

bool isp[MAXN];
vector<int> p;


void init()
{
	memset(isp, true, sizeof(isp));
	for(long long i = 2; i < MAXN; ++i)
	{
		if(isp[i])
		{
			p.push_back(i);
			for(long long j = i*i; j < MAXN; j += i)
			    isp[j] = false;
		}
	}
	
	memset(f, 0, sizeof(f));
	f[0] = 1;
	for(int i = 1; i <= 40; ++i)
	{
		f[i] = f[i-1];
		if(i >= 4)
		    f[i] += f[i-4];
	}
}

int main()
{
	init();
	
	int T;
	cin>>T;
	while(T--)
	{
		int n;
		cin>>n;
		int ans = 0;
		for(int i = 0; i < p.size(); ++i)
		{
			if(f[n] < p[i])
			    break;
			ans++;
		}
		cout<<ans<<endl;
	}
	return 0;
}