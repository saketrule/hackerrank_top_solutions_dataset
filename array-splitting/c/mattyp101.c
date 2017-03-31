#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

int max(int a, int b) {if(a>b)return a; else return b;}

int split(int a[], int n, unsigned long long sum, int index) {
        
    unsigned long long halfSum=0;
    int score=0;
    
    if(index==n-1) return 0;
    if(index==n-2 && a[index]==a[index+1]) return 1;
    
    if(sum%2==1) {
        return 0;
    }
    for(int i=index; i<n; i++) {
        if(sum%2==1) return 0;
        halfSum+=a[i];
        if(halfSum==sum/2) {
            return max(split(a,n,halfSum,i+1), split(a,i+1,halfSum,index))+1;
        }
        if(halfSum>sum/2) return 0;
    }
    return 0;
}

int main() {

    int t;
    scanf("%d", &t);
    for(int test=0; test<t; test++) {
        int n;
        scanf("%d", &n);
        int a[n];
        for(int i=0; i<n; i++) {
            scanf("%d",&a[i]);
        }
        unsigned long long sum=0;
        for(int i=0; i<n; i++) {
            sum+=a[i];
        }
        int score;
        if(sum==0) 
    		score = n-1;
    	else 
    		score = split(a,n,sum, 0);
        
        printf("%d\n",score);
    }
    return 0;
}