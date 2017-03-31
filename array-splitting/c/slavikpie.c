#include <stdio.h>
#include <stdlib.h>

#define ARR(x) (arr[(x)] - dx)
#define MAX(a, b) ((a) > (b) ? (a) : (b));

int
solve(long long *arr, int size, long long dx)
{
    if ((size == 1) || (ARR(size-1) % 2 != 0))
        return 0;

    long long s = ARR(size-1) / 2;
    for (int n = 0; n < size-1; n++) {
        if (ARR(n) == s) {
            int left = solve(arr, n+1, dx);
            int right = solve(arr+n+1, size-n-1, dx+s);

            return 1 + MAX(left, right);
        }
    }
    return 0;
}


int
main(int argc, char **argv)
{
    long long t;
    scanf("%lld", &t);

    for (int t_i = 0; t_i < t; t_i++) {
        int n;
        scanf("%i", &n);


        long long *arr = malloc(sizeof(long long) * n);
        scanf("%lld", &arr[0]);

        for (int n_i = 1; n_i < n; n_i++) {
            scanf("%lld", &arr[n_i]);
            arr[n_i] += arr[n_i-1];
        }

//        for (int n_i = 0; n_i < n; n_i++) {
//            printf("%lld ", arr[n_i]);
//        }
//        printf("\n");

        printf("%d\n", solve(arr, n, 0));
        free(arr);
    }

    return 0;
}
