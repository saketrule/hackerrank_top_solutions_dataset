/* Enter your code here. Read input from STDIN. Print output to STDOUT */
#include<stdio.h>

int main()
{
	int n,r,e,i,j,count,deg[100],last,weight[100],flag=0,a,b,temp;
	int arr[100][100];
	r=scanf("%d %d",&n,&e);
	for(i=0;i<n;i++)
	{
		for(j=0;j<n;j++)
		{
			arr[i][j]=0;
		}
		weight[i]=1;
	}

	for(i=0;i<e;i++)
	{
		r=scanf("%d %d",&a,&b);
		arr[a-1][b-1]=1;
		arr[b-1][a-1]=1;	
	}
	count=0;
	flag=0;
	for(i=0;i<n;i++)
	{
		deg[i]=0;	
		for(j=0;j<n;j++)
		{
			if(arr[i][j]==1)
			{
				deg[i]++;
			}
		}
	}
	
	while(1)
	{
		flag=0;
		for(i=0;i<n;i++)
		{
			if(deg[i]==1)
			{
				flag=1;
				last=i;
				for(j=0;j<n;j++)
				{
					if(arr[i][j]==1)
					{
						last=j;
					}
				}
				arr[i][last]=0;
				arr[last][i]=0;
				deg[last]--;
				temp=last;
				deg[i]=0;
				if(weight[i]%2==0)
				{
					count++;
				}
				else
					weight[last]+=weight[i];		
			}
		}	
	if(flag==0)
		break;

	}

if(weight[temp]%2==0)
	printf("%d\n",count);
else
	printf("0\n");
return 0;
}