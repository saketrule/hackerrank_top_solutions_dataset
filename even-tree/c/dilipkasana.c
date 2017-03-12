/* Enter your code here. Read input from STDIN. Print output to STDOUT */
/* Enter your code here. Read input from STDIN. Print output to STDOUT */
#include<stdio.h>
#define WHITE 0
#define GRAY 1
#define BLACK 2
int adj[100][100],n,color[100],num[100][100];

int number(int u)
	 {
	 int i,v,k=0;
	  color[u]=GRAY;
	  for(v=0;v<n;v++)
		  {
			  //printf("%d   %d",adj[u][v],color[v]);
			 if(adj[u][v]==1 && color[v]==WHITE)
				{
				 num[u][v]=number(v);
             //printf("%d ",num[u][v]);
				}
		  }
	  for(i=0;i<n;i++) if(num[u][i]!=-1) k=k+num[u][i];
	  color[u]=BLACK;
	  return k+1;

	 }

int main()
	 {
	  int edge,i,j,a,b;
	  scanf("%d%d",&n,&edge);
	  //printf("%d %d\n",n,edge);
	  for(i=0;i<n;i++)
			{
			 color[i]=WHITE;
			 for(j=0;j<n;j++)
				 {
					adj[i][j]=-1;                                   
					num[i][j]=-1;
				 }
			}                                                                  
	  for(i=0;i<edge;i++)
			{
			  scanf("%d%d",&a,&b);
			  //printf("%d %d\n",a,b);
			  adj[a-1][b-1]=adj[b-1][a-1]=1;
			}

	  for(i=0;i<n;i++)
			{
			 for(j=0;j<n;j++)
				  {
				  //printf("%d ",adj[i][j]);
				  }
				  //printf("%d ",color[i]);
				 // printf("\n");
			 }
			a=number(0);
			 //printf("%d",a);
			 a=0;
      for(i=0;i<n;i++)
			{
			 for(j=0;j<n;j++)
				  {
				  //printf("%d ",num[i][j]);
				  if(num[i][j]!=-1 && num[i][j]%2==0) a++;
				  }
				  //printf("%d ",color[i]);
				 //printf("\n");
			 }
			 printf("%d",a);
	  return 0;
         }
	 