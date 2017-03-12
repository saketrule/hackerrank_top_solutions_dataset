#include<stdlib.h>
#include<stdio.h>


int main() {
	
	int t,n,i,j,k,l,ans,min,count;
	scanf("%d",&n);
	int a[n+1];
	for(j=1;j<=n;j++) {
		
			 scanf("%d",&a[j]);
		}
		/*
	for(j=1;j<=n;j++) {
		
			 printf("%d",a[j]);
		}	
	
		printf("\n");
	*/
	for(j=0;j<n;j++) {
	
		min = 9999;
		for(i=1;i<=n;i++) {
		
	
			if(a[i] <= min && (a[i] > 0)) {
				min = a[i];
			}
		}
		//printf("%d\t",min);
		count = 0;
		ans = 0;
		
		for(i=1;i<=n;i++) {
		
			a[i] = a[i] - min;
			if(a[i] >= 0) {
				count++;
			}
			if(a[i] > 0) {
				ans++;
			}
			
		}
		
		
		printf("%d\n",count);
		
		/*
		for(l=1;l<=n;l++) {
		
			 printf("%d",a[l]);
		}	
	
		printf("\n");
		
		*/
		if(ans == 0) {
		break;
		}
	
	}
	
	
	return 0;
}