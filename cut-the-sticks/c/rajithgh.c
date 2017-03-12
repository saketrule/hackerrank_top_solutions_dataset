#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
#define LIMIT_N 1000
#define LIMIT_A 1000
int N;

int minimum (int array[]){
    
    int min = LIMIT_A;
    int i;
    for (i=0; i<N; i++){
        if (min>array[i] && array[i]!=0)
            min = array[i];
    }
    return min;
    
}

int isArrayEmpty (int array[]){
    
    int i;
    for (i=0; i<N; i++){
        if (array[i]!=0){
            return 0;
        }
    }
    return 1;
}

int main() {
    
    scanf("%d\n", &N);
    int i;
    int array[LIMIT_N]={0};
    for (i=0; i<N; i++){
        
        scanf("%d", &array[i]);
    }
    
    while (isArrayEmpty(array)==0){
        
        int min = minimum(array);
        int count = 0;
        for (i=0; i<N; i++){
            if (array[i]!=0){
                count++;
                array[i]-=min;
            }
        }
        printf("%d\n", count);
    }
    
    
    return 0;
}
