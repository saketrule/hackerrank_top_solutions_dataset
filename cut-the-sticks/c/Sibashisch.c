#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

int main() {
    int N, A[1001]={0};
    int i, no, count;
    scanf ("%d", &N);
    for (i=0; i<N; i++) {
        scanf ("%d", &no);
        A[no] ++;
    }
    count = N;
    for (i=0; i<=1000; i++) {
        if (A[i]!=0) {
            printf ("%d\n", count);
            count -= A[i];
        }
    }
    return 0;
}
