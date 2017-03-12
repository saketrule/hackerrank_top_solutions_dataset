#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
int graph[10000][10000];
int s[10000];
long long int label[10000];
long long int sum = 0 ;
void initialize(int n)
    {
        int i , j  ; 
    
    for(i=0;i<n;i++)
        {
            label[i] = 99998;
            s[i] = 0;
        }
    for(i=0;i<n;i++)
        {
            for(j=0;j<n;j++)
                {
                    graph[i][j]  = -1;
                    if( i == j )
                        graph[i][j] = -2;
                }
         }
    sum = 0 ;
}
int empty(int n )
    {   
        int i ; 
        for(i=0;i<n;i++)
            {
                if( s[i] == 0)
                    return 0 ;
            }
        return 1;
    }
int find_min(int n)
    {
        int min = 99999 , i , ret = -1;
    
        for(i=0;i<n;i++)
            {
                if(min > label[i] && s[i] == 0)
                    {
                        min = label[i] ;
                        ret = i ;
                    }
            }
  //  printf("min=%d, i= %d\n",min,ret);
    return ret ;
    }
int prims(int n )
    {
    int v , i;
        while(!empty(n))
            {
                v = find_min(n);
               
                for(i=0;i<n;i++)
                    {
                        if( s[i] == 0 && graph[v][i]> -1 )
                            {
                                if( label[i] > graph[v][i])
                                  {
                                        label[i] = graph[v][i] ;
                                        //sum = sum + label[i] ;
                                  }
                            }
                    }
            
                s[v] = 1 ;
            if(label[v]!=99998)
                 sum = sum + label[v] ;
          /*  printf("\nv=%lld\n",label[v]);
            //    label[v]=99999;
                for(i=0;i<n;i++)
                    printf("%lld\t",label[i]);
            printf("\n"); */
            }
    
        return sum ;
    }
    
int main() {

   int t=1,n,e,st,i,j,a,b,c;
    //scanf("%d",&t);
    while(t--)
        {
            scanf("%d%d",&n,&e);
            
            initialize(n);
        
            for(i=0;i<e;i++)
                {
                    scanf("%d%d%d",&a,&b,&c);
                        if( graph[a-1][b-1] > c ||  graph[a-1][b-1] == -1){
                    graph[a-1][b-1] = c ;
                            graph[b-1][a-1] = c ;}
                }
        
            scanf("%d",&st);
            label[st-1] = 0;
            s[st-1] = 0 ;
        
            sum = prims(n);
            printf("%lld",sum );
            
        }
    return 0;
}
