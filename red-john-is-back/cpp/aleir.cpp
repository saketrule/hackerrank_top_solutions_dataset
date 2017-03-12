#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

const int MX = 45;
const int MXPR = 1000000;
bool criba[MXPR];
int primes[MXPR];
int ans[MX];
int main() {
    fill(criba,criba+MXPR,1);
    criba[0] = 0, criba[1] = 0;
    for(int i=0;i<MXPR;i++){
        if(criba[i]){
            for(int j=2*i;j<MXPR;j+=i){
                criba[j] = 0;
            }
        }
    }
    int nprimes = 0;
    for(int i=0;i<MXPR;i++){
        if(criba[i]) primes[nprimes]=i,nprimes++;
    }

    ans[1] = 1, ans[2] = 1, ans[3] = 1, ans[4] = 2;
    for(int i=5;i<MX;i++){
        ans[i] = ans[i-1]+ans[i-4];
    }
    int T,N,M,j;
    scanf("%d",&T);
    for(int i=0;i<T;i++){
        scanf("%d",&N);
        M = ans[N];
        for(j=0;j<nprimes && primes[j]<=M;j++){}
        //j--;
        printf("%d\n",j);
    }
    return 0;
}
