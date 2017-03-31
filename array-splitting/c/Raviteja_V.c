#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

int part(int l,int r,int a[]){
    int suml=0,sumr=0,i,j;
    if(l<r){
    for(i=l;i<=r;i++){
        if(a[r]-a[i]==a[i]-a[l-1]){
            suml=part(l,i,a);
            sumr=part(i+1,r,a);
            if(suml>sumr)
                return suml+1;
            else
                return sumr+1;
        }
    }
    }
    return 0;
}
int main() {
    int t;
    scanf("%d",&t);
    while(t--){
        int n,i;
        scanf("%d",&n);
        int a[n+1];
        a[0]=0;
        for(i=1;i<=n;i++){
            scanf("%d",&a[i]);
            a[i]+=a[i-1];
        }
        printf("%d\n",part(1,n,a));
    }
    
    return 0;
}
