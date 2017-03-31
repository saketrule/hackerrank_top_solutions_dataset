#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

int max(int a,int b){
    return a>=b?a:b;
}

int find(long long int a[],int start,int end){
    if(end<=start)
        return 0;
    
    long long int sum=0;
    
    for(int j=start;j<=end;j++)
        sum += a[j];
    
    long long int num = sum/2;
    if(num==0 && sum!=0)
        return 0;
    int flag = 0,count = 0,l,r,i;
    
    sum = 0;
    for(i=start;i<=end;i++){
        sum += a[i];
        if(sum==num){
            flag = 1;
            break;
        }
    }
    
    if(flag==1){
        l = find(a,start,i);
        r = find(a,i+1,end);
        count = max(l,r);
        count++;
    }
    
    return count;
        
}

int main() {

    /* Enter your code here. Read input from STDIN. Print output to STDOUT */    
    int t;
    scanf("%d",&t);
    while(t--){
        int n;
        scanf("%d",&n);
        long long int a[n];
        for(int i=0;i<n;i++){
            scanf("%lld",&a[i]);
        }
        
        int count = find(a,0,n-1);
        
        printf("%d\n",count);
    }
    return 0;
}
