#include<stdio.h>
#include<stdlib.h>

void partition(long long * , int , int );
void merge(long long * , int , int );
//void print(int * , int );

int main()
{
	int testcases;
	int m,n,i,j,visited_y,visited_x;
	long long  *y,*x;
	long long cost;
	scanf("%d",&testcases);
	while(testcases--)
	{
		visited_x = 0;
		visited_y = 0;
		cost = 0;

		scanf("%d %d",&m,&n);
		m = m-1;
		n = n-1;

		y = (long long  *) malloc(sizeof(long long ) * m);
		i = 0;
		while(i < m)
		{
			scanf("%lld",&y[i]);
			i++;
		}
		
		//print(y,m);
		
		x = (long long *) malloc(sizeof(long long) * n);
		i = 0;
		while(i < n)
		{
			scanf("%lld",&x[i]);
			i++;		
		}
			
		partition(y,0,m-1);
		partition(x,0,n-1);
		//print(y,m);
		//print(x,n);
		
		i = m-1; j = n-1; 
		while(i >= 0 && j >= 0)
		{
			if(y[i] > x[j])
			{
				cost = (cost + ((visited_x + 1) * y[i]))%1000000007;	
				visited_y++;
				i--;
			}
			else if(y[i] < x[j])
			{
				cost = (cost + ((visited_y + 1) * x[j]))%1000000007;
				visited_x++;
				j--;
			}	
			else
			{
				cost = (cost + ((visited_y + 1) * x[j]))%1000000007;
				visited_x++;
				j--;
			}
		}
		while(i>=0)
			cost = (cost + (visited_x + 1) * y[i--])%1000000007;
			
		while(j>=0)
			cost = (cost + (visited_y + 1) * x[j--])%1000000007;
		printf("%lld\n",cost);	
		free(y);	
		free(x);
	}

}

/*void print(int *a , int limit)
{
	int i = 0;
	printf("\n");
	for(;i < limit;i++)
		printf("%d " , a[i]); 	
	printf("\n");	
}
*/

void partition(long long * a , int low , int high)
{
	int mid = (low + high) / 2;
	if(low < high)
	{
		partition(a,low,mid);
		partition(a,mid+1,high);
		merge(a,low,high);
	}
}

void merge(long long *a , int low , int high)
{
	int b[high];
	int mid = (low + high)/2;
	int i = low;
	int j = mid + 1;
	int k = low;

	while(i <= mid && j <= high)
	{
		if(a[j] < a[i])
			b[k++] = a[j++];
		else
			b[k++] = a[i++];	
	}
	while(j<=high)
		b[k++] = a[j++];
	while(i<=mid)
		b[k++] = a[i++];

	for(i=low;i<=high;i++)
		a[i] = b[i];
}
