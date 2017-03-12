#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>



int matrix[3005][3005];
int flag[3005];
int distance[3005];

int main() {
    int N, M, S;
    int x, y, r;
    int i, j;
    int min, min_dis, sum;
    
    scanf("%d %d", &N, &M);
    for(i = 1; i <= N; i++)
        for(j = 1; j <= N; j++)
            matrix[i][j] = 1000000;
        
    for(i = 1; i <= N; i++)
        flag[i] = 0;
        
    for(i = 1; i <= M; i++)
    {
        scanf("%d %d %d", &x, &y, &r);
        if(matrix[x][y] > r)
        {
            matrix[x][y] = r;
            matrix[y][x] = r;
        }
        
    }
    scanf("%d", &S);
    flag[S] = 1;
    for(i = 1; i <= N; i++)
        distance[i] = matrix[S][i];
    
    sum = 0;
    for(i = 1; i < N; i++)
    {
        min_dis = 1000000;
        for(j = 1; j <= N; j++)
        {
            if(flag[j] == 0 && min_dis > distance[j])
            {
                min = j;
                min_dis = distance[j];
            }
        }
        flag[min] = 1;
        sum += min_dis;
        
        for(j = 1; j <= N; j++)
        {
            if(distance[j] > matrix[min][j])
                distance[j] = matrix[min][j];
        }
    }
    
    printf("%d\n", sum);
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */    
    return 0;
}
