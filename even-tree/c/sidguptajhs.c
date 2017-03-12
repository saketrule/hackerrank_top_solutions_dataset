/* Enter your code here. Read input from STDIN. Print output to STDOUT */
#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#define MAX 100
int graph[MAX][MAX],V,E,cnt=0;
int status[MAX];

int solve(int loc,int parent)
{
	//printf("Here with -> %d\n",loc+1);
	int i,childCnt=0;
	status[loc]=1;
	for(i=0;i<V;i++)
	{
		if(status[i]==-1&&graph[loc][i]==1)
		{
			childCnt=childCnt+solve(i,loc);
		}
	}
	if(childCnt%2==1)
	{
		cnt++;
		if(parent!=-1)
		{	
			graph[i][parent]=0;
			graph[parent][i]=0;
		}
		//printf("Detached this !!! \n");
		return 0;
	}
	else
	{
		//printf("Returning %d\n",childCnt+1 );
		return 1+childCnt;
	}
}

int main()
{
	int i,p1,p2;
	FILE* fp=stdin;
	fscanf(fp,"%d",&V);
	fscanf(fp,"%d",&E);
	for(p1=0;p1<V;p1++)
	{
		status[p1]=-1;
		for(p2=0;p2<V;p2++)
		{
			graph[p1][p2]=0;
		}
	}
	for(i=0;i<E;i++)
	{
		fscanf(fp,"%d",&p1);
		fscanf(fp,"%d",&p2);
		graph[p1-1][p2-1]=1;
		graph[p2-1][p1-1]=1;
	}
	solve(0,-1);
	printf("%d\n",cnt-1);
	return 0;
}
