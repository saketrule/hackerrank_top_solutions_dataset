#include <stdio.h>
#include <stdlib.h>
/*
#define Int long long
 
#define Type long long
Int S = 1.1e9;
long long modmul( long long a, long long b, long long m ) 
{
  long long q = (long long)((long double)a * (long double)b / (long double)m);
  long long r = a * b - q * m;
  return (r + 5 * m) % m;
}
 
Type modpow(Type a, Type n, Type mod)
{
  Type b = 1;
  while(n > 0)
    {
      if (n & 1)
	b = modmul(b, a, mod);
      n >>= 1;
      a = modmul(a, a, mod);
    }
  return b;
}
 
char witness(Type a, Type n)
{
  Type u = n - 1;
  int t = 1, i;
  while(~u & 1)
    {
      t++;
      u >>= 1;
    }
  Type x0 = modpow(a, u, n);
  for(i = 0; i < t; i++)
    {
      Type x = modmul(x0, x0, n);
      if (x == 1 && x0 != 1 && x0 != n - 1)
	return 1;
      x0 = x;
    }
  return x0 != 1;
}
 
char isPrime(Type n, int rounds)
{
  if (n == 2)
    return 1;
  if (n < 2 || (~n & 1))
    return 0;
  while(rounds--)
    if (witness(rand() % (n - 1) + 1, n))
      return 0;
  return 1;
}

*/
long double fact( int p)
{
 long double facts = 1;
 int i;
 for( i = 1; i<= p; i++)
  facts = facts * i;
 return( facts);
}
 
long long int ncr ( int n, int r)
{
 return( fact( n) / (fact( r) * fact(n- r) ) ) ;
}

int main(){

  int top_value = 220000;
  int *array    = calloc( top_value + 1, sizeof(int));
  int i, prime, multiple;

  int ans[top_value+1];

  array[1] = 0;

  for(i=2; i <= top_value; ++i) 
    array[i] = 1;

  for(prime = 2; prime <= top_value; ++prime){
    if(array[prime])
      for(multiple = 2*prime; multiple <= top_value; multiple += prime)
	if(array[multiple]) {
	  array[multiple] = 0;
	}
  }
  int count = 0;
  for(i=1; i <= top_value; ++i){
    if (array[i]){
      count++;
    }
    ans[i] = count;
  }
  
  int n,t;
  scanf("%d",&t);
  while(t--){
    scanf("%d",&n);
    if(n>4){
      i = n/4;
      int j = n%4;
      long long int temp=0;
      for(i;i>0;i--,j+=4){
	temp += ncr(i+j,i);
      }
      temp++;
      printf("%d\n",ans[temp]);
    }
    else{
      switch(n){
      case 1:{
      }
      case 2:{
      }
      case 3:{
	printf("0\n");
	break;
      }
      case 4:{
	printf("1\n");
	break;
      }
      };
    }
  }
  return 0;
}

