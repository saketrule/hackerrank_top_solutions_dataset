#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

int mst[10000][3];
int edge[10000][3];
int component[10000];
int n , e  ;
long long int sum=0 ;
void print( int edge[][3] , int e);
void makeset()
    {
    int i = 0 ;
    
    for(i=0;i<n;i++)
        component[i]  = -1 ;        
     }

int find(int x)
    {
    
        if ( component[x] < 0)
            return x ;
         else
           return(find( component[x]));
    }

void union_f(int find_u , int find_v )
    {
        if(component[find_u] >= component[find_v])
            {
                    component[find_v] = component[find_u] + component[find_v];
                    component[find_u] = find_v;
            }
            else
             {
                    component[find_u] = component[find_v] + component[find_u];
                    component[find_v] = find_u;
            }
    }
void kruskal()
    {
        int u , v, w ,k , find_u , find_v,c=0,i;
    makeset();
    for(k=0;k<e;k++)
        {
            u = edge[k][0];
            v = edge[k][1];
            w = edge[k][2];
        
            find_u = find(u);
                
            find_v = find(v);
        
        //printf("\nfind_u= %d\tfind_v = %d\n",find_u , find_v);
        //for(i=0;i<n;i++)
        //    printf("%d\t",component[i]);
      //  printf("\n");
            if( find_u != find_v )
                {
                    union_f(find_u , find_v);
                    mst[c][0] = u ;
                    mst[c][1] = v ; 
                    mst[c][2] = w ;
                    c++ ;
                }
        }
   // printf("\n tst mst c= %d\n", c);
    //print(mst , c);
    
        
    for(k=0;k<c;k++)
        sum = sum + mst[k][2];
       
    
    }
void sort()
    {
        int i , j , k ;
    
        for(i=0;i<e-1;i++)
           {
                for(j=0 ; j < e-i-1  ; j++)
                    {
                       if( edge[j][2] > edge[j+1][2])
                           {
                                k = edge[j][0] ;
                                edge[j][0] = edge[j+1][0];
                                edge[j+1][0] = k;
                           
                                 k = edge[j][1] ;
                                edge[j][1] = edge[j+1][1];
                                edge[j+1][1] = k;
                           
                                 k = edge[j][2] ;
                                edge[j][2] = edge[j+1][2];
                                edge[j+1][2] = k;
                                                                        
                           }
                    else if(edge[j][2] == edge[j+1][2])
                        {
                            if( (edge[j][0] + edge[j][1] + edge[j][2]) >= (edge[j+1][0] + edge[j+1][1] + edge[j+1][2]) )
                             {
                                        k = edge[j][0] ;
                                edge[j][0] = edge[j+1][0];
                                edge[j+1][0] = k;
                           
                                 k = edge[j][1] ;
                                edge[j][1] = edge[j+1][1];
                                edge[j+1][1] = k;
                           
                                 k = edge[j][2] ;
                                edge[j][2] = edge[j+1][2];
                                edge[j+1][2] = k;
                                                      
                             }
                        }
                    }
        } 
    }
void print( int edge[][3] , int e)
{
    int i,j ;
    for(i=0;i<e;i++)
        {
            printf("%d\t%d\t%d\n",edge[i][0],edge[i][1],edge[i][2]);
        }
    
}
int main() {

    int i , j   ,a , b ,c,k=0;
    scanf("%d%d",&n,&e);
   
    for(i=0;i<e;i++)
        {
            scanf("%d%d%d",&a,&b,&c);
       
                        edge[k][0] = a-1;
                        edge[k][1] = b-1;
                        edge[k][2] = c;
                    k++;
                
        }
    //print();
    //printf("\n\n\n");
      sort();
    //print(edge , e);
    kruskal();
        
    printf("%lld",sum);
    return 0;
}
