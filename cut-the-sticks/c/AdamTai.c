#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

#define MAX_SIZE    1000

int main()
{
    int N;
    int A[MAX_SIZE];
    int i;
    int Temp;
    int Result;

    scanf("%d\n", &N);
    
    for(i = 0; i < N; i++)
    {
        scanf("%d ", &A[i]);
    }

    while(1)
    {
        Temp = 1000;
        Result = 0;
    
        for(i = 0; i < N; i++)
        {
            if((A[i] < Temp) && (A[i] != 0))
            {
                Temp = A[i];
            }
        }
        
        for(i = 0; i < N; i++)
        {
            if(A[i] != 0)
            {
                A[i] -= Temp;
                Result++;
            }
        }
        
        if(Result == 0)
        {
            return 0;
        }
        
        printf("%d\n", Result);
    }
    
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */    
    return 0;
}