#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
int main() {
int n,i,j,t,min;
scanf("%d",&n);
int a[n],k=n,count;
for(i=0;i<n;i++)
    scanf("%d",&a[i]);
for(i=0;i<n-1;i++)
    for(j=i+1;j<n;j++){
    if(a[i]>a[j])
        {t=a[i];a[i]=a[j];a[j]=t;}
}
    printf("%d\n",k);
for(i=0;i<n;i++){
    count=0;
    for(j=i;j<n;j++){
    if(a[i]==a[j])
    count++;
    else
        {k=k-count;i=i+count-1;printf("%d\n",k);break;}
    }}
    return 0;
}
