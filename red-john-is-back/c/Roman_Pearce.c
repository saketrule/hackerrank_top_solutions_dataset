#include<stdio.h>

int prm[41]={0,0,0,0,1,2,2,3,4,4,6,8,9,11,15,19,24,32,42,53,68,91,119,155,204,269,354,462,615,816,1077,1432,1912,2543,3385,4522,6048,8078,10794,14475,19385};
int main()
{
	int t;
	scanf("%d",&t);
	while(t)
	{
		int n,ways,prime;
		scanf("%d",&n);
		prime=prm[n];
		printf("%d\n",prime);
		
		t--;
	}
	

	return 0;
}