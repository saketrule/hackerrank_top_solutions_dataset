/* Enter your code here. Read input from STDIN. Print output to STDOUT */#include<stdio.h>

void main()
{	int ans=0, tchild[100],parent[100],child[100],vert,edges;
	scanf("%d %d", &vert, &edges);
	 for(int i=0;i<vert; i++)
	{
		tchild[i]=0;
	}		
         for(int i=0;i<edges; i++)
	{
		scanf("%d %d", &child[i], &parent[i]);
	}
	
	for(int j=edges-1;j>=0; j--)
	{
		for(int k=0;k<edges;k++)
		{
			if (parent[k]==j+1)
                            tchild[j]=tchild[j]+1 + tchild[child[k]-1];
		}
	}
	for (int i=1;i<edges; i++)
	{
		if (tchild[i]%2==1)
		ans=ans+1;
	}
	printf("%d", ans);
	
}