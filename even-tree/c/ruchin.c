/* Enter your code here. Read input from STDIN. Print output to STDOUT */
#include<stdio.h>

int isedge[100][100]={{0}};

int edge[10000][2];

int queue[10000],queuehead=0,queuetail=0,num_ele=0;
int marked[100]={0};

int n,m;
void remark()
{
	int i;
	for(i=0;i<100;i++)
		marked[i]=0;
}
void reset()
{
	queuehead=0;
	queuetail=0;
	num_ele=0;
}

void enqueue(int ele)
{
	if(queuetail==10000)
		queuetail=0;
	queue[queuetail] = ele;
	queuetail++;
	num_ele++;
}

int dequeue()
{
	int ele;
	ele = queue[queuehead];
	queuehead++;
	if(queuehead==10000)
		queuehead=0;
	num_ele--;
	return ele;
}

int check_even_forest(int u,int v)
{
	int num1=0,num2=0,vert,evencount=0,i,j;
	reset();
	remark();
	enqueue(u);
	marked[u] = 1;
	while(num_ele>0)
	{
		vert = dequeue();
		evencount++;
		for(i=0;i<n;i++)
		{
			if(isedge[vert][i]==1 && i!=v && marked[i]==0)
			{
				enqueue(i);
				marked[i]=1;
			}
		}
	}
	if(evencount%2!=0)
		return -1;
	else
		evencount=0;
	reset();
	remark();
	enqueue(v);
	marked[v]=1;
	while(num_ele>0)
	{
		vert = dequeue();
		evencount++;
		for(i=0;i<n;i++)
		{
			if(isedge[vert][i]==1 && i!=u && marked[i]==0)
			{
				enqueue(i);
				marked[i]=1;
			}
		}
	}
	if(evencount%2==0)
		return 1;

}

int main()
{
	int i,j,u,v,count=0;
	scanf("%d %d",&n,&m);
	for(i=0;i<m;i++)
	{
		scanf("%d %d",&u,&v);
		isedge[u-1][v-1] = 1;
		isedge[v-1][u-1] = 1;
		edge[i][0] = u-1;
		edge[i][1] = v-1;
	}
/*	printf("der\n");
	for(i=0;i<n;i++)
	{
		for(j=0;j<n;j++)
		{
			printf("%d ",isedge[i][j]);
		}
		printf("\n");
	}
	printf("der\n");*/
	for(i=0;i<m;i++)
	{
		if(check_even_forest(edge[i][0],edge[i][1])==1)
		{
			isedge[edge[i][0]][edge[i][1]] = 0;
			isedge[edge[i][1]][edge[i][0]] = 0;	
			count++;		
		}
	}
	printf("%d\n",count);
}