#include<stdio.h>

enum bool {false=0,true=1};

int combinations[41];
enum bool isPrime[217290];

void precompute_combinations(); 
void checkIsPrime();
int count_primesLessThanNo(int);

int main()
{

	precompute_combinations();
	checkIsPrime();
	int t,n,count;
	scanf("%d",&t);
	while(t--)
	{
		scanf("%d",&n);
		count = count_primesLessThanNo(combinations[n]);
		printf("%d\n",count);
	}
	return 0;
}

void precompute_combinations()
{
	int i;
	for(i=0;i<=3;i++)
		combinations[i] = 1;
	for(i=4;i<=40;i++)
		combinations[i] = combinations[i-1] + combinations[i-4];
}

void checkIsPrime()
{
	int i,j,temp,max = combinations[40];
	isPrime[0] = isPrime[1] = false;
	
	for(i=2;i<=max;i++)
		isPrime[i] = true;
	for(i=2;i<=max;i++)
	{
		j=2;
		temp = j*i;
		while(temp <= max)
		{
			isPrime[temp] = false;
			j++;
			temp = i*j;
		}
	}
}

int count_primesLessThanNo(int x)
{
	int count=0,i;
	for(i=2;i<=x;i++)
		if(isPrime[i])
			count++;
	return count;
}

