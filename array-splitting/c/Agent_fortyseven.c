#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
#include <stdbool.h>
int A[16384+10];
long long int B[16384+10];    // B[i] = A[1]+A[2]+...+A[i]
// [l,r] => A[l]+A[l+1]+...+A[r]
// => A[1]+A[2]+...+A[r] - (A[1]+A[2]+...+A[l-1])
// = B[r]-B[l-1]
bool C[16394][16394];
#define Max(a,b) (a>b)?(a):(b)

int max_ct;

void fun(int l, int r, int ct){ // [l,r]
    C[l][r]=1;
    max_ct = Max(max_ct,ct);
    //printf("hwhehe %d %d %d\n",l,r,ct);
    if(l>=r){
        return;
    }
    for(int i=l;i<=r;++i){
        if(B[i]-B[l-1] == B[r]-B[i]){
            if(!C[l][i]) fun(l,i,ct+1);
            if(!C[i+1][r]) fun(i+1,r,ct+1);
            break;
        }
    }
}

int main() {
    int T;
    scanf("%d", &T);
    while(T--){
        int N,i,j;
        scanf("%d", &N);
        for(i=1;i<=N;++i) scanf("%d",A+i);
        B[0]=0;
        for(i=1;i<=N;++i)B[i]=B[i-1]+A[i];
        for(i=1;i<=N;++i){
            for(j=1;j<=N;++j)C[i][j]=0;
        }
        max_ct=0;
        fun(1,N,0);
        printf("%d\n",max_ct);
    }
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */    
    return 0;
}
