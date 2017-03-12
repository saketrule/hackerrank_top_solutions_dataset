#include<stdio.h>
int cost[3000][3000],near[3000],n,z,m;

int prims(int k,int l)
{  int i,min_i,j,r,min,min_cost=0;
    
   for(i=1;i<=n;i++)
    {
     if(cost[k][i]>cost[l][i])
        {
            near[i]=l;
        }
        else
            near[i]=k;}
        near[k]=-1;near[l]=-1;
           z=1;
        for(i=3;i<=n;i++)
        {  min=1000000;
            for(j=1;j<=n;j++)
            {
                if(cost[j][near[j]]<min&&near[j]!=-1)
                {min=cost[j][near[j]];
                  min_i=j;
                }
            }
             min_cost=min_cost+min;
           
            z++;
            near[min_i]=-1;

            for(r=1;r<=n;r++)
            {
                if(cost[r][min_i]<cost[r][near[r]]&&near[r]!=-1)
               {
                 near[r]=min_i;
               }
            }
        }


 return min_cost;
}
int main()
{   int i,j,x=100000,k=0,l=0,s,a1,a2;
   
    scanf("%d",&n);

    scanf("%d",&m);
 for(i=1;i<=n;i++)
       for(j=1;j<=n;j++)
         cost[i][j]=100000;
  while(m--){
     scanf("%d%d",&a1,&a2);
            scanf("%d",&cost[a1][a2]);
            cost[a2][a1]=cost[a1][a2];
           
        }
    scanf("%d",&s);
   for(i=1;i<=n;i++)
       {
        if(x>cost[s][i]&&s!=i)
            {
                x=cost[s][i];
                k=s;l=i;
            }
       
   }
    x=x+prims(k,l);
    printf("%d",x);

return 0;
}
