#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

int main() {

    /* Enter your code here. Read input from STDIN. Print output to STDOUT */  
    int i,t,ind;
    int ans[21];
    int res[41] = {0,0,0,0,1,2,2,3,4,4,6,8,9,11,15,19,24,32,42,53,68,91,119,155,204,269,354,462,615,816,1077,1432,1912,2543,3385,4522,6048,8078,10794,14475,19385};
    scanf("%d",&t);
    for(i=0;i<t;i++)
    {
       scanf("%d",&ind);
        ans[i] = res[ind];
    }
    for(i=0;i<t;i++)
        printf("%d\n",ans[i]);
    return 0;
}
