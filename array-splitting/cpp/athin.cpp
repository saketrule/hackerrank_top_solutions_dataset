#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

int t,n,x,i;
long long a[1000007];

int DP(int x, int y) {
    if (x >= y) return 0;
    long long sum = a[y] - a[x-1];
    if (sum % 2 == 1) return 0;
    
    int base = x;
    int top = y;
    while (base != top) {
        int mid = (base + top) / 2;
        if (a[mid] - a[x-1] < sum / 2) base = mid + 1; else top = mid;
    }
    if (a[top] - a[x-1] != sum / 2) return 0;
    return 1 + max(DP(x,top),DP(top+1,y));
}

int main() {
    scanf("%d",&t);
    while (t--) {
        scanf("%d",&n);
        for (i=1 ; i<=n ; i++) {
            scanf("%d",&x);
            a[i] = a[i-1] + x;
        }
        printf("%d\n",DP(1,n));
    }
    return 0;
}
