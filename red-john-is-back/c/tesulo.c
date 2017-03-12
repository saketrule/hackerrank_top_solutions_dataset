#include <stdio.h>

long long a[100],i,j,k,l,m,n,t,tt, p[10000000], b[100], P;

#define N 5000000

int main()
{

a[0] = 1;

for(i=1;i<50; i++) 
 {
 a[i] = a[i-1];
 if(i>=4) a[i] += a[i-4];
// printf("%lld -> %lld\n", i, a[i]);
 }

i = 2;
P = 0;
j = 0;

for(i=2;i<N;i++)
 if(p[i]==0)
  {
  
  while(i > a[j] && j < 45) {b[j] = P; j++;}
  
  for(k = i; k<N; k+=i) p[k] = i;
  P++;  
  }

//for(i=0;i<100;i++)  printf("%lld: %lld %lld\n", i, a[i], b[i]);

scanf("%lld",&t);

for(tt=0;tt<t;tt++)
 {
 scanf("%lld",&n);
 printf("%lld\n",b[n]);
 }

return 0;
}