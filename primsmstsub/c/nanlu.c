/*
You should use the statndard input/output

in order to receive a score properly.

Do not use file input and output

Please be very careful. 
*/

#include <stdio.h>
#define INF 999999999
long int Answer=INF;
#define MAX 3001
int G[MAX][MAX];
int I[MAX][MAX];
int V[MAX];
//int MST[100];
int N, E, S, D;
long int findmin()
{
	//int jan = 6359060;
	int minV;
	long int edge = INF;
	int i,j, k=0,l=0;
	for(i= 0; i<N; i++)
	{
		for(j=0; j<N; j++)
		{
			if(G[i][j] != -1)
			{
				if((edge > G[i][j]) && (V[i]^V[j]))
				{
					minV = i;
					edge = G[i][j];
					k=i; l=j;
				}
			}
		}
	}
	V[k]=1;
	V[l] =1;
	return edge;
	
}
void MST()
{
	int i;
	long int key =0;
	for(i=0; i<N-1; i++)
	{
		
		key += findmin();
		
	}
	Answer = key;
}

int main(void)
{
	//int T, test_case;
	int i, j, e, w;
	/*
	   The freopen function below opens input.txt file in read only mode, and afterward,
	   the program will read from input.txt file instead of standard(keyboard) input.
	   To test your program, you may save input data in input.txt file,
	   and use freopen function to read from the file when using scanf function.
	   You may remove the comment symbols(//) in the below statement and use it.
	   But before submission, you must remove the freopen function or rewrite comment symbols(//).
	 */
	 //freopen("input.txt", "r", stdin);

	/*
	   If you remove the statement below, your program's output may not be rocorded
	   when your program is terminated after the time limit.
	   For safety, please use setbuf(stdout, NULL); statement.
	 */
	setbuf(stdout, NULL);

	//scanf("%d", &T);
	//for(test_case = 0; test_case < T; test_case++)
	//{
		
		/////////////////////////////////////////////////////////////////////////////////////////////
		/*
		   Implement your algorithm here.
		   The answer to the case will be stored in variable Answer.
		 */
		/////////////////////////////////////////////////////////////////////////////////////////////
		Answer =INF;
		for(i=0; i< MAX; i++)
		{
			for(j=0; j< MAX; j++)
			{
				//I[i][j] =INF;
				G[i][j] =-1;
			}
			V[i]=0;
		}
		/*for(i=0; i< 1000; i++)
		{
			//for(j=0; j< 100; j++)
			{
				V[i]=0;
			}
		}*/
		scanf(" %d %d", &N, &E) ;
		for(e=0; e< E; e++)
		{
			scanf(" %d %d %d", &i , &j, &w);
			if(G[i-1][j-1] == -1)
			{
				G[i-1][j-1]= w;
				G[j-1][i-1]= w;
			}
			else if(G[i-1][j-1] > w)
			{
				G[i-1][j-1]= w;
				G[j-1][i-1]= w;
			}
			
		}
		//V[S-1] = 1;
		scanf("%d", &S);
		V[S-1] = 1;
		MST();
		//DFS(S-1,0);
		/*for(i=0; i< N; i++)
		{
			for(j=0;j<N;j++)
			V[j]=0;    
	
			DFS(i);
		}*/
		// Print the answer to standard output(screen).
//		printf("Case #%d\n", test_case+1);
		if(Answer !=  INF)
			printf("%d\n", Answer);
		else
			printf("-1\n");
	//}

	return 0;//Your program should return 0 on normal termination.
}
