#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

int main() {
    int i,j,k,le,b[3000],n,m,x,y,s,r,no,sum=0;
    int *a[3000];
    for(i=0;i<=3000;i++)
         a[i]=(int*)malloc(3000*(sizeof(int)));
    
scanf("%d%d",&n,&m);
    for(i=1;i<=n;i++){
        for(j=1;j<=n;j++)
        {
        a[i][j]=100001;
        }b[i]=0;}
    for(i=1;i<=m;i++)
        {
        scanf("%d%d%d",&x,&y,&r);
        a[x][y]=r;
        a[y][x]=r;
    }
    scanf("%d",&s);
    b[s]=-1;
    for(k=1;k<n;k++){le=100001;
    for(i=1;i<=n;i++)
        {
        if(b[i]==-1)
            {
            for(j=1;j<=n;j++)
                {
                if((a[i][j]<le)&&(b[j]==0)){
                    le=a[i][j];no=j;}
            }
        }
    }
                     sum=sum+le;
                    // printf("%d\n",le);
                     b[no]=-1;
                    }
    printf("%d",sum);
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */    
    return 0;
}
