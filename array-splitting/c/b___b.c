#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
long long int  f(long long int  *arr,long long int  x,long long int  y){
    if(x==y){return 0;}
    long long int  a,b,s,i,p,q;
    s=0;
    for(i=x;i<=y;i++){s=s+arr[i];}
    a=0;
    for(i=x;i<=y;i++){
                      a=a+arr[i];
                      if(a*2==s){b=i;break;}
                      if((a*2)>s){return 0;}
                     }
    p=f(arr,x,b);
    q=f(arr,b+1,y);
    if(p>q){return p+1;}
    else return q+1;
}

int main() {
    long long int q,t;
    scanf("%lld",&t);
    for(q=0;q<t;q++)
        {
    long long int  i,a,n;
  scanf("%lld",&n);
    long long int arr[n];
    for(i=0;i<n;i++){scanf("%lld",&arr[i]);}
    a=f(arr,0,n-1);
    printf("%lld\n",a);
    }
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */    
    return 0;
}
