// Hackerrank - Cutting Boards
// Dynamic Programming

#include <stdio.h>
#include <iostream>
#include <algorithm>
#define MAX 1000005
#define MOD 1000000007

using namespace std;
typedef long long LL;

int n, m;
LL x[MAX], y[MAX];

int main()
{
  int t;
  
  scanf("%d", &t); 
  while(t--)
  {
    scanf("%d %d", &n, &m);
    --n; --m;
    
    for(int i = 0; i < n; ++i) scanf("%lld", &x[i]);
    for(int i = 0; i < m; ++i) scanf("%lld", &y[i]);
    
    LL ans, s1 = 0, s2 = 0;
    for(int i = 0; i < n; ++i) s1 = (x[i] % MOD + s1 % MOD) % MOD;
    for(int i = 0; i < m; ++i) s2 = (y[i] % MOD + s2 % MOD) % MOD;
    ans = (s2 % MOD + s1 % MOD) % MOD;
    
    sort(x, x + n);
    sort(y, y + m);
    
    for(int i = n - 1, j = m - 1; i >= 0 && j >= 0;)
    {
      if(y[j] >= x[i])
      {
        ans = (ans % MOD + s1 % MOD) % MOD;
        s2 = ((s2 - y[j] + MOD) % MOD) % MOD;
        --j;
      }
      else
      {
        ans = (ans % MOD + s2 % MOD) % MOD;
        s1 = ((s1 - x[i] + MOD) % MOD) % MOD;
        --i;
      }
    }
    
    printf("%lld\n", ans);
  }
  
  return 0;
}