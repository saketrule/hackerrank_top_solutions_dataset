#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

const int N = 1<<15;
int n;
int a[N];

int rec(int l, int r) {
  if (l == r) return 0;
  long long leftSum = 0, rightSum = 0;
  for (int i = l; i <= r; ++i) rightSum += a[i];
  for (int i = l; i <= r; ++i) {
    leftSum += a[i];
    rightSum -= a[i];
    if (leftSum == rightSum) return 1 + max(rec(l, i), rec(i+1,r));
    if (leftSum > rightSum) break;
  }
  return 0;
}

int main() {
  int t; cin >> t;
  while (t--) {
    cin >> n;
    for (int i = 0; i < n; ++i) cin >> a[i];
    cout << rec(0, n-1) << endl;
  }
  return 0;
}
