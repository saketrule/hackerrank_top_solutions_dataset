#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
 int _X[1000000],_Y[1000000];
 int comp (const void * elem1, const void * elem2)
{{
    int f = *((int*)elem1);
    int s = *((int*)elem2);
    if (f > s) return  -1;
    if (f < s) return 1;
    return 0;
}}
   void Test()
     {
    long long int M,N,sum=0,num_block_X=1, num_block_Y=1;
    int i,j;
    scanf("%lld %lld",&M,&N);
    for(int i =0; i<M-1; i++)
        scanf("%d",&_Y[i]);
    for(int i =0; i<N-1; i++)
        scanf("%d",&_X[i]);
      
      qsort(_X,N-1,sizeof(*_X),comp);
      qsort(_Y,M-1,sizeof(*_Y),comp);
    for(i=0,j=0;i<N-1 && j<M-1;)
        {
        if(_X[i]>_Y[j])
            {
            sum = (sum + (_X[i]*num_block_Y)%1000000007)%1000000007;
            num_block_X++;
            i++;
        }
        /*else if(_X[i] ==_Y[j])
              
              {
              if(i<N)
                  {
            sum = (sum + (_X[i]*num_block_Y)%1000000007)%1000000007;
            num_block_X++;
            i++;
        }
              else
                  {
            sum = (sum + (_Y[j]*num_block_X)%1000000007)%1000000007;
            num_block_Y++;
            j++;
        }
          }*/
        else
            {
            sum = (sum + (_Y[j]*num_block_X)%1000000007)%1000000007;
            num_block_Y++;
            j++;
        }
          
        
    }
       if(i==N-1 && j<M-1)
           { while(j<M-1){
           sum = (sum + (_Y[j]*num_block_X)%1000000007)%1000000007;
            num_block_Y++;
            j++;
            }
       }
       else if(j==M-1 && i<N-1)
           {
           while(i<N-1){
               sum = (sum + (_X[i]*num_block_Y)%1000000007)%1000000007;
            num_block_X++;
            i++;
           }
       }
    printf("%lld\n",sum);
    
}
  int main() {

    int T;
    scanf("%d",&T);
    while(T--)
        Test();
    
    return 0;
}
