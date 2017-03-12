/* Enter your code here. Read input from STDIN. Print output to STDOUT */
#include<stdio.h>
#include<malloc.h>
int eventree(int **,int,int,int *);
int main()
{
	int **a,**b,i,j,n,h,r;
	scanf("%d",&n);
	scanf("%d",&n);
	a=(int**)malloc(2*sizeof(int*));
	for(i=0;i<2;i++)
	a[i]=(int *)malloc(n*sizeof(int));
	b=(int**)malloc((n+1)*sizeof(int*));
	for(i=0;i<n+1;i++)
	b[i]=(int*)malloc((n+1)*sizeof(int));
	for(i=0;i<n;i++)
	{
	 scanf("%d",&a[0][i]);
	 scanf("%d",&a[1][i]);	
	}
	for(i=0;i<n+1;i++)
	{ 
		for(j=0;j<n+1;j++)
		b[i][j]=0;
	}
	for(i=0;i<n;i++)
	{
		r=a[0][i];
		h=a[1][i];
		b[h-1][r-1]=1;
	}
	h=0;
	eventree(b,0,n+1,&h);
	printf("%d",h-1);
	return 0;
}
int eventree(int **a,int x,int n,int *h)
{
	int i,k=0;
	for(i=0;i<n;i++)
	{
	   if(a[x][i]==1)
	   {
	   	k++;
   		k+=eventree(a,i,n,h);
   	   }
    }
   	 if((k%2)!=0)
   	   (*h)+=1;
 return k;
}
