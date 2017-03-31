#include <stdio.h>
#include <algorithm>
#include <assert.h>
#include <set>
#include <map>
#include <complex>
#include <iostream>
#include <time.h>
#include <stack>
#include <stdlib.h>
#include <memory.h>
#include <bitset>
#include <math.h>
#include <string>
#include <string.h>
#include <queue>
#include <vector>

using namespace std;

const int MaxN = 1e5 + 10;
const int INF = 1e9;
const int MOD = 1e9 + 7;

int n, t, res;
int a[MaxN];
long long s[MaxN];

long long get(int l, int r) {
  return s[r] - (l > 0 ? s[l - 1] : 0);
}

int solve(int l, int r) {
  if (get(l, r) == 0) {
    return r - l;
  }
  if (l == r) {
    return 0;
  }
  if (get(l, r) % 2 == 1) {
    return 0;
  }
  long long f = get(l, r) / 2;
  int lbound = l, rbound = r - 1, re = -1;
  while (lbound <= rbound) {
    int pos = (lbound + rbound) / 2;
    if (get(l, pos) < f) {
      lbound = pos + 1;
    } else if (get(l, pos) > f) {
      rbound = pos - 1;
    } else {
      re = pos;
      break;
    }
  }
  if (re == -1) {
    return 0;
  }
  return 1 + max(solve(l, re), solve(re + 1, r));
}

int main() {
//  freopen("input.txt", "r", stdin);
  int t;
  scanf("%d", &t);
  while (t --> 0) {
    scanf("%d", &n);
    for (int i = 0; i < n; ++i) {
      scanf("%d", &a[i]);
      s[i] = a[i] + (i > 0 ? s[i - 1] : 0);
    }
    printf("%d\n", solve(0, n - 1));
  }
  return 0;
}
