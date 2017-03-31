#include <stdio.h>
#include <stdlib.h>
long long get_i(long long*a,long long num,int size);
long long med(long long*a,int size);
int max(int x,int y);
int solve(int idx,long long sum,int size,long long off);
int a[20000];
long long dp[20000];

int main(){
  int T,N,i;
  long long sum;
  scanf("%d",&T);
  while(T--){
    scanf("%d",&N);
    for(i=sum=0;i<N;i++){
      scanf("%d",a+i);
      if(!i)
        dp[i]=a[i];
      else
        dp[i]=dp[i-1]+a[i];
      sum+=a[i];
    }
    printf("%d\n",solve(0,sum,N,0));
  }
  return 0;
}
long long get_i(long long*a,long long num,int size){
  if(size==0)
    return 0;
  if(num>med(a,size))
    return get_i(&a[(size+1)>>1],num,size>>1)+((size+1)>>1);
  else
    return get_i(a,num,(size-1)>>1);
}
long long med(long long*a,int size){
  return a[(size-1)>>1];
}
int max(int x,int y){
  return (x>y)?x:y;
}
int solve(int idx,long long sum,int size,long long off){
  if(sum%2 || size<2)
    return 0;
  int i=get_i(&dp[idx],sum/2+off,size);
  if(i<size && dp[i+idx]==sum/2+off)
    return 1+max(solve(idx,sum/2,i+1,off),solve(idx+i+1,sum/2,size-i-1,off+sum/2));
  return 0;
}
