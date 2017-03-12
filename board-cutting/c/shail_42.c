#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
long long int mod=1000000007;
void merge(long long int arr[], long long int l, long long int m, long long int r)
{
    long long int i, j, k;
    long long int n1 = m - l + 1;
    long long int n2 =  r - m;
    long long int L[n1], R[n2];
    for(i = 0; i < n1; i++)
        L[i] = arr[l + i];
    for(j = 0; j <= n2; j++)
        R[j] = arr[m + 1+ j];
    i = 0;
    j = 0;
    k = l;
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k] = L[i];
            i++;
        }
        else {
            arr[k] = R[j];
            j++;
        }
        k++;
    }
    while (i < n1) {
        arr[k] = L[i];
        i++;
        k++;
    }
    while (j < n2) {
        arr[k] = R[j];
        j++;
        k++;
    }
}
void mergeSort(long long int arr[], long long int l, long long int r)
{
    if (l < r) {
        long long int m = l+(r-l)/2;
        mergeSort(arr, l, m);
        mergeSort(arr, m+1, r);
        merge(arr, l, m, r);
    }
}

int main() {
	long long int t,m,n,i,j;
	scanf("%lld",&t);
	while(t--)
	{
		long long int segv=1,segh=1;
		scanf("%lld%lld",&m,&n);
		long long int a[m],b[n];
		a[0]=-1;b[0]=-1;
		for(i=1;i<m;i++)
		scanf("%lld",&a[i]);
    	mergeSort(a, 1, m-1);
		for(i=1;i<n;i++)
		scanf("%lld",&b[i]);
		mergeSort(b, 1, n-1);
		i=m-1;j=n-1;
		long long int ans=0;
		while(1)
		{
			if(a[i]==-1 && b[j]==-1)
			break;
			if(a[i]>b[j]) {
				ans = (ans+(segh*a[i])%mod)%mod;
				i--;
				segv++;
			}
			else {
				ans = (ans+(segv*b[j])%mod)%mod;
				j--;
				segh++;
			}
		}
		printf("%lld\n",ans);
	}
	return 0;
}
