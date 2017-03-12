#include<stdio.h>
int main()
{
	int n;
	scanf("%d",&n);
	int a[1002]={0};
	int i;
	for(i=0;i<n;i++)
	{
		int c;
		scanf("%d",&c);
		a[c]++;
	}
	int t=n;
	for(i=0;i<1001;i++)
	{
		if(a[i]>0)
		{
			printf("%d\n",t);
			t=t-a[i];
		}
	}
	return  0;
}