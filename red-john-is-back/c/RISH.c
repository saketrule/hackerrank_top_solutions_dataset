#include<stdio.h>
#include<math.h>
int arr[1000005]={0};
int main(){
	int i;
	for(i=2;i<1000;i++)
	{
		if(!arr[i]){
			int p=i*i;
			while(p<=1000000){
				arr[p]=1;
				p=p+i;
			}
		}
	}
	arr[0]=1;
	arr[1]=1;
	for(i=0;i<=1000000;i++)
	{
		if(!arr[i])
			arr[i]=arr[i-1]+1;
		else
			arr[i]=arr[i-1];
	}
	int dp[50]={0};
	dp[1]=dp[2]=dp[3]=1;
	dp[4]=2;
	for(i=5;i<=40;i++)
		dp[i]=dp[i-1]+dp[i-4];
	int t;
	scanf("%d",&t);
	while(t--){
		int n;
		scanf("%d",&n);
		printf("%d\n",arr[dp[n]]);
	}
	return 0;
}