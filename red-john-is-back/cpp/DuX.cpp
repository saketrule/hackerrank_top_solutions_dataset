#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

int brprostih[555555];
bool prost[555555];

int main() {

    int i,j,n,tt,d[55];

    d[0]=1;
    for(i=1; i<=50; i++) {
        if (i<4) d[i]=1;
        else {
            d[i]=d[i-1]+d[i-4];
        }
        //printf("d[%d] = %d\n", i, d[i]);
    }

    for(i=0; i<=555333; i++) prost[i]=true;

    brprostih[1]=0;

    for(i=2; i<=555333; i++) {
        brprostih[i]=brprostih[i-1];
        if (prost[i]) brprostih[i]++;
        for(j=i+i; j<=555333; j+=i) prost[j]=false;
        //if (i<=100) printf("brprostih[%d] = %d\n", i, brprostih[i]);
    }

    scanf("%d", &tt);
    while(tt--) {
        scanf("%d", &n);
        printf("%d\n", brprostih[d[n]]);
    }

    return 0;
}
