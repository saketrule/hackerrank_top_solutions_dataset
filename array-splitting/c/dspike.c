#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

int T;
int N;
int A[20000];


typedef struct _Case {
    int index;
    int size;
    long long sum;
    int level;
} Case;

Case stack[1000000];
int  stackIndex;


int solve()
{
    int res = 0;
    int i;
    long long sum = 0;
    long long tmp = 0;
    Case c;
    Case ctmp;

    stackIndex = 0;

    for (i = 0; i < N; ++i) {
        sum += A[i];
    }
    
    if (! sum) return N - 1;

    c.index = 0;
    c.size  = N;
    c.sum   = sum;
    c.level = 0;

    stack[stackIndex] = c;
    stackIndex += 1;

    while (stackIndex > 0) {
        c = stack[stackIndex - 1];
        stackIndex -= 1;

        // printf("From stack: index %d size %d level %d sum %lld\n", c.index, c.size, c.level, c.sum);

        if (c.level > res) {
            res = c.level;
        }
        if (c.size <= 1) {
            continue;
        }

        tmp = 0;
        for (i = c.index; i < c.index + c.size; ++i) {
            if (A[i] == 0) {
                continue;
            }
            tmp += A[i];
            c.sum -= A[i];
            if (tmp == c.sum) {
                break;
            }
        }
        if (i == c.index + c.size) {
            continue;
        }

        // left
        ctmp.index = c.index;
        ctmp.size  = i - c.index + 1;
        ctmp.sum   = c.sum;
        ctmp.level = c.level + 1;

        // printf("Left: index %d size %d level %d sum %lld\n", ctmp.index, ctmp.size, ctmp.level, ctmp.sum);
        if (ctmp.size >= 1) {
            stack[stackIndex] = ctmp;
            stackIndex += 1;
        }

        // right
        ctmp.index = i + 1;
        ctmp.size  = c.size - ctmp.size;
        ctmp.sum   = c.sum;
        ctmp.level = c.level + 1;

        // printf("Right: index %d size %d level %d sum %lld\n", ctmp.index, ctmp.size, ctmp.level, ctmp.sum);
        if (ctmp.size >= 1) {
            stack[stackIndex] = ctmp;
            stackIndex += 1;
        }
    }

    return res;
}


int main()
{
    int t;
    int n;
    int res = 0;

    scanf("%d", &T);
    for (t = 0; t < T; ++t) {
        scanf("%d", &N);
        for (n = 0; n < N; ++n) {
            scanf("%d", &A[n]);
        }

        res = solve();

        printf("%d\n", res);
    }

    return 0;
}

