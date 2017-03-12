#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
#include <stdint.h>

#undef DEBUG_ON

void quickSort( int32_t a[], int l, int r)
{
   int j;

   if( l < r )
   {
       j = partition( a, l, r);
       quickSort( a, l, j-1);
       quickSort( a, j+1, r);
   }
}

int partition( int32_t a[], int l, int r) {
   int pivot, i, j, t;
   pivot = a[l];
   i = l; j = r+1;

   while( 1)
   {
   	do ++i; while( a[i] <= pivot && i <= r );
   	do --j; while( a[j] > pivot );
   	if( i >= j ) break;
   	t = a[i]; a[i] = a[j]; a[j] = t;
   }
   t = a[l]; a[l] = a[j]; a[j] = t;
   return j;
}

int main() {

    uint64_t verticalBlocks, horizontalBlocks, minimalCost;
    uint32_t N, M, testCases;
    uint32_t i, j, k, z;
    uint32_t *myArrays[2];

    scanf("%d", &testCases);

    for(k = 0; k < testCases; k++)
    {
        verticalBlocks = 1;
        horizontalBlocks = 1;
        minimalCost = 0;

        scanf("%d", &M);
        scanf("%d", &N);

        uint32_t costY[M-1], costX[N-1];
        myArrays[0] = (uint32_t *)&costY[M-2];
        myArrays[1] = (uint32_t *)&costX[N-2];

        for(i = 0; i < M-1; i++)
            scanf("%d", &costY[i]);
        for(i = 0; i < N-1; i++)
            scanf("%d", &costX[i]);

        if(M > 2)
        {
            quickSort( costY, 0, M-2);
        }
        if(N > 2)
        {
            quickSort( costX, 0, N-2);
        }

        for(i = 0; i < M + N - 2; i++)
        {
#ifdef DEBUG_ON
            printf("myArrays[0]: %d\n", myArrays[0]);
            printf("myArrays[1]: %d\n", myArrays[1]);
            printf("&costY[0]: %d\n", &costY[0]);
            printf("&costX[0]: %d\n", &costX[0]);
            printf("&costY[M-1]: %d\n", &costY[M-1]);
            printf("&costX[N-1]: %d\n", &costX[N-1]);
#endif
            if(((myArrays[0] > (uint32_t *)&costY[-1]) && (*myArrays[0] > *myArrays[1])) ||
                (myArrays[1] == (uint32_t *)&costX[-1]))
            {
#ifdef DEBUG_ON
                printf("*myArray[0]: %d\n", *myArrays[0]);
#endif
                minimalCost += ((uint64_t)(*myArrays[0]) * (uint64_t)horizontalBlocks) % 1000000007;
                myArrays[0]-=1;
                verticalBlocks++;
            }
            else
            {
#ifdef DEBUG_ON
                printf("*myArray[1]: %d\n", *myArrays[1]);
#endif
                minimalCost += ((uint64_t)(*myArrays[1]) * (uint64_t)verticalBlocks) % 1000000007;
                myArrays[1]-=1;
                horizontalBlocks++;
            }
#ifdef DEBUG_ON
            printf("~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
#endif
            minimalCost %= 1000000007;
        }

        printf("%u\n",(uint32_t)minimalCost);
    }

    return 0;
}
