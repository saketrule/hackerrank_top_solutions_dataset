#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#include<unistd.h>

int number_of_division(long long int *A,long long int *B,long int range_first,long int range_secon,long int div_position,int count);
long long int sum_range(long long int *B,long int range_first,long int range_second);
int main(){
	int T,i,answer;
	static int count;
	long N,j,length,k,divider_position,range_first,range_second;
	long long int sum1,sum2;
	long long int *A,*B;
	long int **Dynamic_Matrix;
	clock_t begin,end;
	begin=clock();
	scanf("%d",&T);
	//printf("%d\n",T);
	for(i=0;i<T;i++){
		scanf("%ld",&N);
		//sleep(10);
		//printf("%ld\n",N);
		A=(long long int *)malloc(N*sizeof(long long int));
		B=(long long int *)malloc(N*sizeof(long long int));
		B[0]=0;
		for(j=0;j<N;j++){
			scanf	("%lld",&A[j]);
			if(j>0)
			{
				B[j]=B[j-1]+A[j];
				//printf("index=%ld,sum=%lld",j,B[j]);
			}else{
				B[0]=A[0];
			}
			//printf	("%lld ",A[j]);
		}
		
		//printf("\n");
		
		/*Dynamic_Matrix=(long int **)malloc(N * sizeof(long int *));
		for(j=0;j<N;j++){
			Dynamic_Matrix[j]=(long int *)malloc((N-j) * sizeof(long int ));
			//Dynamic_Matrix[j]=(long int *)malloc((N/2) * sizeof(long int ));
			Dynamic_Matrix[j][0]=A[j];
			//B = (long long int *)malloc(N * N * sizeof(long long int));
		}

		
		for(length=2;length<=N;length++){
			//printf("\nyahoo length %ld\n",length);
			for(k=0;k<N-length+1;k++){
				Dynamic_Matrix[k][length-1]=A[k+length-1]+Dynamic_Matrix[k][length-2];
				//printf("%lld ",Dynamic_Matrix[k][k+length-1]);
			}
			//printf("kahan");
		}*/
		//printf("yahan");		
		range_first=0;
		range_second=N-1;
		divider_position=1;
		answer=number_of_division(A,B,range_first,range_second,divider_position,0);
		
		//answer=0;
		//printf("answer=%d\n",answer);
		printf("%d\n",answer);
		free(A);
		free(B);
		/*
		for(j=0;j<N;j++){
			free(Dynamic_Matrix[j]);
		}
		free(Dynamic_Matrix);*/
		//printf("wohan");	
	}
	end=clock();
	//printf("\ntime=%f",(double)(end-begin)/ CLOCKS_PER_SEC);
	//printf("\n");
	return 0;
}

int number_of_division(long long int *A, long long int *dynamic_sum,long int range_first,long int range_second,long int div_position,int count){
	int k1,k2;
	long int j;
	//printf("\nfirst function div_position=%ld range_first=%ld range_second=%ld\n",div_position,range_first,range_second);
	if(div_position > range_second)
		return 0;
	//if(dynamic_sum[range_first][div_position-1-range_first]==dynamic_sum[div_position][range_second-div_position]){
	//printf("sum1=%lld",sum_range(dynamic_sum,range_first,div_position-1));
	//printf("sum2=%lld",sum_range(dynamic_sum,div_position,range_second));
	if(sum_range(dynamic_sum,range_first,div_position-1)==sum_range(dynamic_sum,div_position,range_second)){
		//printf("equal to range_first=%ld range_second=%ld div_position=%ld count=%d\n",range_first,range_second,div_position,count);
		if(range_second-range_first>1){
			k1=1+number_of_division(A,dynamic_sum,range_first,div_position-1,range_first+1,count+1);
			k2=1+number_of_division(A,dynamic_sum,div_position,range_second,div_position+1,count+1);
			if(k1>k2)
				return k1;
			else
				return k2;
		}else if((range_second-range_first)==1){
			if (A[range_first]==A[range_second]){
				return 1;
			}else{
				return 0;
			}
		}
	}else if//(dynamic_sum[range_first][div_position-1-range_first] > dynamic_sum[div_position][range_second-div_position])
		(sum_range(dynamic_sum,range_first,div_position-1)> sum_range(dynamic_sum,div_position,range_second))
	{
		return 0;
	}
	else if((div_position+1) <= range_second){
		//printf("recursion=%ld\n",div_position);
		j=div_position+1;
		if(A[j]==0){
			j++;
		}
		//k1=number_of_division(A,dynamic_sum,range_first,range_second,div_position+1,count);//
		k1=number_of_division(A,dynamic_sum,range_first,range_second,j,count);//
	}else if((div_position+1) > range_second){
		//printf("div_position %ld\n",div_position);
		return 0;
	}
	return k1;
}

long long int sum_range(long long int *B,long int range_first,long int range_second){
	
	if(range_first>0)
	{
		//printf("first=%ld second=%ld sum=%lld b[range_first]=%lld b[range_second]=%lld\n",range_first,range_second,B[range_second]-B[range_first-1],B[range_second],B[range_first]);
		return (B[range_second]-B[range_first-1]);
	}else{
		//printf("first=%ld second=%ld sum=%lld\n",range_first,range_second,B[range_second]);
		return (B[range_second]);
	}
}