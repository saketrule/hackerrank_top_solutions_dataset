#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

int nextPrime(int last, int* primes) {
    int i, res = last;
    char pass = 0;
    while (!pass) {
        res++;
        pass = 1;
        for (i = 0; primes[i]*primes[i] <= res; ++i) {
            if (res % primes[i] == 0) {
                pass = 0;
                break;
            }
        }
    }
    return res;
}
int main() {
    int T, N, t, i, a_sz = 4, p_sz = 20, p_end = 2;
    int *arrangements, *primes, *result;
    scanf("%d", &T);
    arrangements = (int*)malloc(a_sz*sizeof(int));
    result = (int*)malloc(a_sz*sizeof(int));
    primes = (int*)malloc(p_sz*sizeof(int));
    arrangements[0] = 1, result[0] = 0; // N = 1
    arrangements[1] = 1, result[1] = 0;
    arrangements[2] = 1, result[2] = 0;
    arrangements[3] = 2, result[3] = 1; // N = 4
    primes[0] = 2;
    primes[1] = 3;
    for (t = 0; t < T; ++t) {
        scanf("%d", &N);
        if (a_sz < N) {
            arrangements = (int*)realloc(arrangements, N*sizeof(int));
            result = (int*)realloc(result, N*sizeof(int));
            for (i = a_sz; i < N; ++i) {
                arrangements[i] = arrangements[i-1] + arrangements[i-4];
                while (primes[p_end-1] <= arrangements[i]) {
                    if (p_end == p_sz) {
                        p_sz *= 2;
                        primes = (int*)realloc(primes, p_sz*sizeof(int));
                    }
                    primes[p_end] = nextPrime(primes[p_end-1], primes);
                    p_end++;
                }
                result[i] = p_end-1;
                //fprintf(stderr, "Computed values for %d: %d, %d\n", i+1, arrangements[i], result[i]);
            }
            a_sz = N;
        }
        printf("%d\n", result[N-1]);
    }
    return 0;
}
