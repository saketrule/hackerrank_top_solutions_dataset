#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

int compare(const void *a, const void *b) {
    return *(long long int *) a - *(long long int *) b;
}

int main() {

    /* Enter your code here. Read input from STDIN. Print output to STDOUT */    
    long long int t, m, n, *x, *y, i, j, k, c;
    const long long int mod = pow(10, 9) + 7;
    x = malloc(pow(10, 6) * sizeof(long long int));
    y = malloc(pow(10, 6) * sizeof(long long int));
    scanf("%lld", &t);
    for (i = 0; i < t; i++) {
        scanf("%lld %lld", &m, &n);
        m--;
        n--;
        for (j = 0; j < m; j++)
            scanf("%lld", x + j);
        for (k = 0; k < n; k++)
            scanf("%lld", y + k);
        qsort(x, m, sizeof(long long int), compare);
        qsort(y, n, sizeof(long long int), compare);
        c = 0;
        for (j = m - 1, k = n - 1; j >= 0 || k >= 0;) {
            if (j >= 0 && (k < 0 || x[j] > y[k])) {
                c += x[j] * (n - k);
                j--;
            } else {
                c += y[k] * (m - j);
                k--;
            }
            c %= mod;
        }
        printf("%lld\n", c);
    }
    free(x);
    free(y);
    return 0;
}
