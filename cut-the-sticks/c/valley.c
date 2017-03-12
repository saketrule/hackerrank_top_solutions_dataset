#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

#define LLU unsigned int

int my_comp(const void *i1, const void *i2)
{
    LLU *p=(LLU *)i1;
    LLU *q=(LLU *)i2;
    if(*p > *q) return 1;
    else return -1;
}
int main() {

    LLU n, i, cur_sort_index, count;
    LLU cur_index = 0, sub;
    
    scanf("%u", &n);
    count = n;
    LLU *data = (LLU *)malloc(n*sizeof(LLU));
    LLU *sorted = (LLU *)malloc(n*sizeof(LLU));
    for(i=0;i<n;i++) {
        scanf("%u", &data[i]);
    }
    qsort(data, n, sizeof(LLU), my_comp);
    printf("%u\n", count);
    cur_index++;
    while(cur_index<n) {
        if (data[cur_index] != data[cur_index-1]){
            printf("%u\n", count-cur_index);
        }
        cur_index++;
    }
    
    //getch();
    return 0;
}
