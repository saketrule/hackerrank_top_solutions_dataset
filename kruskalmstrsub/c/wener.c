#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

int X[9000000], Y[9000000], R[9000000];
//int flag[9000000];
int flag_node[3005];

int main() {
    int N, M;
    int i, j;
    int S, sum, count;
    int min, min_dis;
    scanf("%d %d", &N, &M);
    for(i = 1; i <= M; i++)
    {
        //flag[i] = 0;
        scanf("%d %d %d", &X[i], &Y[i], &R[i]);
    }
    
    scanf("%d", &S);
    
    sum = 0;
    count = 0;
    for(i = 1; i <= N; i++)
        flag_node[i] = 0;
    
    flag_node[S] = 1;
    count = 1;
    
    while(count < N)
    {
        min_dis = 1000000;
        for(i = 1; i <= M; i++)
        {
            if(((flag_node[X[i]] + flag_node[Y[i]]) == 1) && min_dis > R[i])
            {
                min_dis = R[i];
                min = i;
            }
        }
        //printf("i=%d, x[i]=%d, y[i]=%d\n", min, X[min], Y[min]);
        //flag[min] = 1;
        sum += min_dis;
        if(flag_node[X[min]] == 0)
        {
            flag_node[X[min]] = 1;
            count++;
        }
        if(flag_node[Y[min]] == 0)
        {
            flag_node[Y[min]] = 1;
            count++;
        }
    }
    
    printf("%d\n", sum);
    
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */    
    return 0;
}
