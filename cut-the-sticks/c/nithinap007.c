#include<stdio.h>
int main()
{
    int n,s,steps,z=0,i;
    int a[1000];
    scanf("%d",&n);
    steps=n;
    for(i=0;i<n;i++){
        scanf("%d",&a[i]);
    }
    while(steps!=0) {
       steps=0;
       s=findsmall(a,n);
       for(i=0;i<n;i++) {
           if(a[i]>0) {
               a[i]-=s;
               steps++;
           }
           else
               z++;                 
       }
       if(steps!=0)
       	printf("%d\n",steps);
    }
    //printf("1");
    return 0;    
}
int findsmall(int a[],int n)
{
    int small=9999,i;
    for(i=0;i<n;i++){
        if(a[i]>0) {
            if(a[i]<small)
                 small=a[i];       
        }
    }
    return small;
}
