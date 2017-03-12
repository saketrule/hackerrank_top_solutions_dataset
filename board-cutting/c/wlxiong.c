#include <stdio.h>
#include <stdlib.h>

long long y[1000002];
long long x[1000002];

int cmp_int(const void *a, const void *b) {
    return *(long long *)b - *(long long *)a;
}

int main() {
    long long T, M, N;
    scanf("%lld", &T);
    long long i, j, k;
    for (i = 0; i < T; i++) {
        scanf("%lld %lld", &M, &N);
        for (j = 0; j < M-1; j++) {
            scanf("%lld", &y[j]);
        }
        for (j = 0; j < N-1; j++) {
            scanf("%lld", &x[j]);
        }
        qsort(&x, N-1, sizeof(x[0]), cmp_int);
        qsort(&y, M-1, sizeof(y[0]), cmp_int);
        long long a = 0, b = 0, sx = 1, sy = 1, cost = 0;
        while (a < M-1 || b < N-1) {
            if (a < M-1 && (b >= N-1 || y[a] > x[b])) {
                // printf("a = %d y[a] = %d\n", a, y[a]);
                cost += sy * y[a] % 1000000007;
                cost = cost % 1000000007;
                a++;
                sx++;
            } else {
                // printf("b = %d x[b] = %d\n", b, x[b]);
                cost += sx * x[b] % 1000000007;
                cost = cost % 1000000007;
                b++;
                sy++;
            }
        }
        printf("%lld\n", cost);
    }


    return 0;
}