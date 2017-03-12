#include<iostream>
using namespace std;
int num[1000000]={0},nnum=1,nto[300000];
int main()
{
	long long a[80];
	int i,j,t,n;
	a[0]=a[1]=a[2]=a[3]=1;
	for(i=4;i<50;++i)
		a[i]=a[i-1]+a[i-4];
	//cout<<a[39];
	num[0]=num[1]=0;
	num[2]=1;
	nto[0]=2;
	for(i=3;i<300000;++i)
	{
		for(j=0;j<nnum;++j)
			if(i%nto[j]==0)
				break;
		if(j==nnum)
		{
			nto[nnum]=i;
			nnum++;
			num[i]=num[i-1]+1;
		}
		else
			num[i]=num[i-1];
	}
	
	cin>>t;
	while(t>0)
	{
		cin>>n;
		cout<<num[a[n]]<<"\n";
		t--;
	}
	//cout<<a[39]<<"\n";

}