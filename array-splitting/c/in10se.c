#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

int binSearch(long long int a[], long long int s, long long int e, long long int p, long long int en){
    long long int mid = (s+e)/2;
    if(s <= e){
        if((a[mid]-p) == (en - a[mid]))
            return mid;
        else if((a[mid] - p) < (en - a[mid]))
            return binSearch(a, mid+1, e, p, en);
        else
            return binSearch(a, s, mid-1, p, en);
             

    }
    return -1;
    
}
long long int max(long long int a, long long int b){
    if(a>b)
        return a;
    return b;
}
long long int bs(long long int a[], long long int s, long long int e, long long int p){
    long long int count = 0;
    if(a[e] == 0)
        return e;
    if(s>e)
        return 0;
    long long int in = binSearch(a, s, e, p, a[e]);
    //printf("%lld\n", in);
    if(in == -1)
        return count;
    return (max(bs(a, in+1, e, a[in]), bs(a, s, in, p)) + 1);
    
    
}
int main() {
    int t;
    scanf("%d", &t);
    while(t--){
        long long int a[1000000], n, i;
        scanf("%lld", &n);
        for(i = 0; i < n; i++){
            scanf("%lld", &a[i]);
        }
        for(i = 1; i < n; i++){
            a[i] = a[i] + a[i-1];

        }
        long long int res;
        res = bs(a, 0, n-1, 0);
        printf("%lld\n", res);
    }
    
    return 0;
}
