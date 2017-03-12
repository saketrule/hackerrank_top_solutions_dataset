#include <iostream>
#include <string>
#include <cstdio>
#include <algorithm>
#include <cmath>
using namespace std;


int main()
{
	int n;
	cin>>n;
	int ar[1005]={0},x,maxv=0;
	for (int i=0 ; i<n ; i++)
	{
		cin>>x;
		ar[x]++;
		maxv=max(maxv,x);
	}
	for (int i=0 ; i<= maxv ; i++)
	{
		if (n>n-ar[i])
			cout<<n<<endl;
		n-=ar[i];
	}
	return 0;
}