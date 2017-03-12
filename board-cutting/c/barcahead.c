#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

int x[1000001], y[1000001];
long long MOD = 1000000007;

int cmp (const void *a, const void *b) {
    return *(int *)b - *(int *)a;
}

int main() {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */
    int T, M, N;
    long long i, j;
    scanf("%d", &T);
    for (;T;T--) {
        scanf("%d%d", &M, &N);
        for (i=0; i<M-1; i++) scanf("%d", &x[i]);
        for (i=0; i<N-1; i++) scanf("%d", &y[i]);
        qsort(x, M-1, sizeof(x[0]), cmp);
        qsort(y, N-1, sizeof(y[0]), cmp);
        long long res = 0;
        for (i=0,j=0; i<M-1&&j<N-1;) {
            if (x[i] > y[j]) res = (res+(j+1)*x[i++])%MOD;
            else res = (res+(i+1)*y[j++])%MOD;
        }
        for (; i<M-1; i++) res = (res+N*x[i])%MOD;
        for (; j<N-1; j++) res = (res+M*y[j])%MOD;
        printf("%lld\n", res);
    }
    return 0;
}
