#include <stdio.h>

int main(void)
{
	int n, i, A[1001] = {0};
	
	scanf("%d", &n);
	for (i = 0; i < n; ++i)
	{	
		int x;
		
		scanf("%d", &x);
		A[x]++;
	}
	for (i = 1; n > 0 && i < 1001; ++i)
	{
		if (A[i] > 0)	
		{
			printf("%d\n", n);
			n -= A[i];
		}
	}
	return 0;
}
