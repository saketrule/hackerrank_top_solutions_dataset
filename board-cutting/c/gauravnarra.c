#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int comp(const void *elem1,const void *elem2){
	return ((*(long *)elem1 < *(long *)elem2)?1:-1);
	return 0;
}
long final_cost(long *arr,long cost,long x,long y,long limit){
	while(x < limit){
		cost += arr[x]*(y);
		x++;
	}
	return cost;
}
long check_cost(long cost){
	if(cost > (long)(pow(10,9)+7)){
		cost %= (long)(pow(10,9)+7);
	}
	return cost;
}

void get_min_cost(){
	long m,n;
	scanf("%ld %ld",&m,&n);
	long i,x[m],y[n],j=0;
	for(i=0;i<(m-1);i++)
		scanf("%ld",&x[i]);
	for(i=0;i<(n-1);i++)
		scanf("%ld",&y[i]);
	qsort((void *)x,m-1,sizeof(x[0]),comp);
	qsort((void *)y,n-1,sizeof(y[0]),comp);
	int k;
	long cost=0;
	i=0;
	j=0;
	while(i<(m-1) && j<(n-1)){
		if(x[i] > y[j]){
			cost += x[i]*(j+1);
			i++;
		}
		else if(x[i] < y[j]){
			cost += y[j]*(i+1);
			j++;
		}
		else{
			if(i>j){
				cost += x[i]*(j+1);
				i++;
			}
			else{
				cost += y[j]*(i+1);
				j++;
			}
		}
		cost = check_cost(cost);
	}
	if(j != (n-1))
		cost = final_cost(y,cost,j,i+1,n-1);
	if(i != (m-1))
		cost = final_cost(x,cost,i,j+1,m-1);
	cost = check_cost(cost);
	printf("%ld\n",cost);
}

int main(){

	int t;
	scanf("%d",&t);
	
	while(t>0){
		get_min_cost();
		t--;
	}

	//qsort (x, sizeof(x)/sizeof(*x), sizeof(*x), comp);

	return 0;
}