#include<stdio.h>
#include<stdlib.h>

int r = 0;
int arr[128][128];
int n;
int ret = 0;

void printArray(){
  int i,j;
  for(i=1;i<=n;i++)
    {
      for(j=1;j<=n;j++)
        {
	  printf("%d\t",arr[i][j]);
        }
      printf("\n");
    }
}
 
void dfs(int *s,int u,int *q){
 
  int v;
  s[u]=1;
  for(v = 1;v <= n; v++)
    {
      if(arr[u][v] == 1 && s[v] == 0)
        {
	  int t = r;
	  //	  printf("r = %d for v = %d\n", r, v);
	  q[++r] = v;
	  dfs(s,v,q);
	  //	  printf("r = %d for v = %d\n", r, v);
	  if((r-t)%2 == 0)
	    ret++; //printf("Edge (%d, %d) can be removed.\n", u, v);
        }
    }
}
 
int main()
{
  int m, i, j;
  scanf("%d %d", &n, &m);

  int s[128], q[128];
  for(i=1; i<=n; i++){
    s[i] = 0;
    q[i] = 0;
    for(j=1; j<=n; j++)
      arr[i][j] = 0;
  }

  for(i=0; i<m; i++){
    int x, y;
    scanf("%d %d", &x, &y);
    arr[x][y] = 1;
    arr[y][x] = 1;
  }

  dfs(s, 1, q);
  printf("%d\n", ret);

  /*  for(i=1; i<=n; i++)
    printf("%d  ", q[i]);
  */

  return 0;
}
