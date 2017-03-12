#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

#define MaxN 250000

int main() {

    /* Enter your code here. Read input from STDIN. Print output to STDOUT */    

    int test, tests;
    int i, j, n, dp[45] = {0}, f[45] = {0};
    static int s[MaxN];
    dp[0] = 1;

    for (i = 0; i <= 40; i++)
    {
      dp[i+1] += dp[i];
      dp[i+4] += dp[i];
    }
    for (i = 2; i * i < MaxN; i++)
    {
      if (s[i] == 0)
      {
        for (j = 0; j <= 40; j++)
          if (i <= dp[j])
            f[j]++;
        for (j = i * i; j < MaxN; j += i)
          s[j] = 1;
      }
    }
    for (;i < MaxN; i++)
      if (s[i] == 0)
        for (j = 0; j <= 40; j++)
          if (i <= dp[j])
            f[j]++;

   
     
    scanf ("%d", &tests);
    for (test = 0; test < tests; test++)
    {
      scanf ("%d", &n);
      printf ("%d\n", f[n]);
    }
    return 0;
}