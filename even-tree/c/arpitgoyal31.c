/* Enter your code here. Read input from STDIN. Print output to STDOUT */
#include<stdio.h>
void main()
{	int v,e,c[100],p[100],count[100],ans=0,x;
	scanf("%d %d",&v,&e);
	for (int i=0;i<v;i++)
		{
			count[i]=0;
		}
	for (int i=0;i<e;i++)
		{
			scanf("%d %d",&c[i],&p[i]);
		}
	for (int i=v-2;i>-1;i--)
	{
		for (int j=0;j<e;j++)
			{
				if(p[j]==i+1)
				count[i]=count[i]+1+count[(c[j]-1)];	
			}	
	}
	for (int i=1;i<e;i++)
		{
		if(count[i]%2==1)
		ans=ans+1;
		}
	
    printf("%d",ans);
}