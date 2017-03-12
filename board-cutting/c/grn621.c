#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
 int cmp(const void* a, const void* b)
     {
             return (*(int *)b-*(int *)a);    
     }
  int main() {
    int n,m,t,i;
    int v[1000000],h[1000000];
    long long int hc=0,vc=0,res;
    scanf("%d",&t);
    while(t--)
        {
        hc=0,vc=0;
        res=0;
         scanf("%d %d",&n,&m);
        for(i=0;i<(n-1);i++)
            scanf("%d",v+i);
        for(i=0;i<(m-1);i++)
            scanf("%d",h+i);
        qsort(v,n-1,sizeof(v[0]),cmp);
       qsort(h,m-1,sizeof(v[0]),cmp);
      /* printf("V[i]'s: \n");
        for(i=0;i<(n-1);i++)
            printf("%d",v[i]);
        printf("\nH[i]'s: \n");
        for(i=0;i<(m-1);i++)
            printf("%d",h[i]);
            */
        
      while(hc<(m-1) && vc <(n-1))
      {
          if(v[vc] > h[hc])
              {
              res=res+((v[vc]*(hc+1))%1000000007);
              res=res%1000000007;
                  vc++;
          }
          else if(v[vc] < h[hc])
              {
              res=res+((h[hc]*(vc+1))%1000000007);
              res=res%1000000007;
                  hc++;
              }
          else
              {
              if(vc<hc)
                  {
                  res=res+((h[hc]*(vc+1))%1000000007);
                  res=res%1000000007;
                  hc++;
                  
              }
              else 
                  {
                  res=res+((v[vc]*(hc+1))%1000000007);
                  res=res%1000000007;
                  vc++;
                  
              }
              
              
          }
                 
          
      }
        while(hc <(m-1))
            {
             res=res+((h[hc]*(vc+1))%1000000007);
              res=res%1000000007;
                  hc++;
        }
        while(vc<(n-1))
            {
              res+=((v[vc]*(hc+1))%1000000007);
              res=res%1000000007;
                  vc++;
        }
        
           printf("%lld\n",res);
      }
        
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */    
    return 0;
     }