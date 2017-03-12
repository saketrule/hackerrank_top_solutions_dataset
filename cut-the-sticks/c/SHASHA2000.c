#include<stdio.h>
int main(){
	int n,i,x;
	int a[1001];
	for(i=0;i<1001;i++)
		a[i]=0;
	scanf("%d",&n);
	for(i=0;i<n;i++){
		scanf("%d",&x);
		a[x]++;
	}
	i=0;
	while(n>0){
		printf("%d\n",n);
		for(;a[i]==0&&i<1001;i++);
		n-=a[i];
		i++;
	}
	return 0;
}