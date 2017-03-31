#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

// return the best score of play
int play(const int * array, int total_sum, int left, int right, int depth)
{
    if (right - left < 2) {
        return depth;
    }
    
    if ((total_sum & 1) != 0) {
        return depth;
    }
    
    if (total_sum == 0) {
        return depth + right - left - 1;
    }
    
    int left_sum = 0;
    int i;
    for(i = left; i < right; i++) {
        left_sum += array[i];
        if (left_sum + left_sum == total_sum) {
            break;
        }
    }
    
    if (i >= right) {
        return depth;
    }
    
    int left_depth = play(array, left_sum, left, i + 1, depth + 1);
    int right_depth = play(array, left_sum, i + 1, right, depth + 1);
    
    if (left_depth < right_depth) {
        return right_depth;
    } else {
        return left_depth;
    }
}

void test()
{
    int * array = NULL;
    int length;

    scanf("%d", &length);
    array = (int *)malloc(length * sizeof(int));
    int total_sum = 0;
    for(int i = 0; i < length; i++) {
        scanf("%d", array + i);
        total_sum += array[i];
    }
    
    printf("%d\n", play(array, total_sum, 0, length, 0));
    free(array);
}

int main() {

    /* Enter your code here. Read input from STDIN. Print output to STDOUT */    
    int test_cases = 0;
    scanf("%d", &test_cases);
    for(int i = 0; i < test_cases; i++) {
        test();
    }
    return 0;
}
