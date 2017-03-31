/*
 * 
 * File:   SherlockAndMiniMax.cpp
 * Author: Andy Y.F. Huang (azneye)
 * Created on May 31, 2014, 12:49:42 PM
 */

#include <algorithm>
#include <bitset>
#include <cassert>
#include <cctype>
#include <climits>
#include <cmath>
#include <cstddef>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <ctime>
#include <complex>
#include <deque>
#include <fstream>
#include <functional>
#include <iomanip>
#include <iostream>
#include <limits>
#include <list>
#include <map>
#include <numeric>
#include <queue>
#include <set>
#include <sstream>
#include <stack>
#include <string>
#include <utility>
#include <valarray>
#include <vector>

using namespace std;

namespace SherlockAndMiniMax {
static int N, P, Q, a[111];
int best, res;

void check(int M) {
  if (M < P || M > Q)
    return;
  int low = 1e9;
  for (int i = 0; i < N; i++)
    low = min(low, abs(a[i] - M));
  if (low > best) {
    best = low;
    res = 1e9;
  }
  if (low == best)
    res = min(res, M);
}

void solve(int test_num) {
  cin >> N;
  for (int i = 0; i < N; i++)
    cin >> a[i];
  cin >> P >> Q;
  best = -1, res = 1e9;
  check(P);
  check(Q);
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      check((a[i] + a[j]) / 2);
      check((a[i] + a[j] + 1) / 2);
    }
  }
  cout << res << endl;
}

void solve() {
#ifdef AZN
  freopen("input.txt", "r", stdin);
  freopen("output.txt", "w", stdout);
  freopen("azn.txt", "w", stderr);
#endif
  solve(1);
}
}

int main() {
  SherlockAndMiniMax::solve();
  return 0;
}
