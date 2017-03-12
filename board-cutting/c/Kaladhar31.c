#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
#include<malloc.h>

void quick_sort(long long int *X, long int first, long int last){
	long int pivot, i, j;
	long long int temp;
	if (first < last){
		pivot = first;
		i = first;
		j = last;
		while (i<j){
			while (X[i] >= X[pivot] && i<last)
				i++;
			while (X[j]<X[pivot])
				j--;
			if (i<j){
				temp = X[i];
				X[i] = X[j];
				X[j] = temp;
			}
		}

		temp = X[pivot];
		X[pivot] = X[j];
		X[j] = temp;
		quick_sort(X, first, j - 1);
		quick_sort(X, j + 1, last);
	}
}

int main() {
    // Cutting_boards.cpp : Defines the entry point for the console application.
	int T,i;
	long int R, C,row_seg,col_seg,j,k;
	long long int *R_C, *C_C,result;
	scanf("%d", &T);
	for (i = 0; i < T; i++){
		scanf("%ld%ld", &R, &C);
		result = 0;
		row_seg = 1;
		col_seg = 1;
		R_C = (long long int *)malloc(sizeof(long long int)*R);
		C_C = (long long int *)malloc(sizeof(long long int)*C);
		for (j = 0; j < R-1; j++){
			scanf("%lld", &R_C[j]);
		}
		quick_sort(R_C, 0, R - 2);
		for (j = 0; j < C-1; j++){
			scanf("%lld", &C_C[j]);
		}
		quick_sort(C_C, 0, C - 2);
		for (j = 0, k = 0; j < R-1 && k < C-1;){
			if (R_C[j] >= C_C[k]){
				result = result + R_C[j] * col_seg;
				j++;
				row_seg++;
			}
			else if (R_C[j] < C_C[k]){
				result = result + C_C[k] * row_seg;
				k++;
				col_seg++;
			}
			if (result >= 1000000007)
				result = result % 1000000007;
		}
		if (j != R - 1){
			for (; j < R - 1; j++){
				result = result + R_C[j] * col_seg;
				row_seg;
				if (result >= 1000000007)
					result = result % 1000000007;
			}
		}
		else if (k != C - 1){
			for (; k < C - 1; k++){
				result = result + C_C[k] * row_seg;
				col_seg++;
				if (result >= 1000000007)
					result = result % 1000000007;
			}
		}
		printf("%lld\n", result);
	}

    /* Enter your code here. Read input from STDIN. Print output to STDOUT */    
    return 0;
}
