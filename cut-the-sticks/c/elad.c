#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

int compare(const void *a, const void *b) {
    return *(int *) a - *(int *) b;
}

int main() {

    /* Enter your code here. Read input from STDIN. Print output to STDOUT */    
    int n, i, arr[1000], currVal, currIndex;
    scanf("%d", &n);
    for (i = 0; i < n; i++)
        scanf("%d", &arr[i]);
    qsort(arr, n, sizeof(int), compare);
    for (i = 0; i < n;) {
        currIndex = i;
        currVal = arr[i];
        while (i < n && arr[i] == currVal)
            i++;
        printf("%d\n", n - currIndex);
    }
    return 0;
}
