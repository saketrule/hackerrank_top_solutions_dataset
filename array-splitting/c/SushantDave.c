#include <stdio.h>

unsigned long long int arr[32000];
unsigned long long int cum[32000];

int FindMid(int start, int end)
{
	int i;
	for(i = start; i < end;i++)
	{
		if((cum[end] - cum[start-1]) == 2*(cum[i] - cum[start-1]))
		{
			return i;
		}
	}
	return -1;
}

unsigned long long int MaxSplit(int start, int end)
{
	int i, sum = 0, mid;
	unsigned long long int h, l;

	if(start >= end)
		return 0;
	
	mid = FindMid(start, end);

	if(mid < 0)
		return 0;

	h = MaxSplit(start, mid);
	l = MaxSplit(mid + 1, end);

	if(h < l)
		return l + 1;
	else
		return h + 1;
}

int main() {

	int N, i, j, T;
	long long int sum = 0;
	//freopen("input.txt", "r", stdin);
	scanf("%d", &T);

	for(j = 0; j < T; j++)
	{
		scanf("%d", &N);

		arr[0] = 0;
		cum[0] = 0;

		for(i = 1; i <= N; i++)
		{
			scanf("%d", arr + i);
			cum[i] = arr[i] + cum[i-1];
		}

		sum = MaxSplit(1, N);

		printf("%llu\n", sum);
	}

    /* Enter your code here. Read input from STDIN. Print output to STDOUT */    
    return 0;
}
