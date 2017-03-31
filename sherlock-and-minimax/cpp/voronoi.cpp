#include <cassert>
#include <complex>
#include <cstddef>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <ctime>
#include <fstream>
#include <iostream>
#include <iterator>
#include <limits>
#include <numeric>
#include <sstream>
#include <utility>

#include <algorithm>
#include <bitset>
#include <list>
#include <map>
#include <queue>
#include <set>
#include <stack>
#include <string>
#include <vector>

#include <memory.h>
using namespace std;

#define Pi 3.141592653589793
#define all(c) (c).begin(), (c).end()
typedef long long ll;

int res = -1;
int val = -1;

void check(const vector<int>& vi, int v) {
  vector<int> tmp;
  for (int i = 0; i < vi.size(); ++i) {
    tmp.push_back(abs(vi[i] - v));
  }
  int mn = *min_element(all(tmp));
  if (val == -1 || mn > val) {
    res = v;
    val = mn;
  }
}

int main() {
#ifdef LOCAL_HOST
  freopen("in.txt", "r", stdin);
  //freopen("out.txt", "w", stdout);
#endif
  int n; cin >> n;
  vector<int> vi(n); for (int i = 0; i < n; ++i) cin >> vi[i];
  sort(all(vi));
  int p, q; cin >> p >> q;
  check(vi, p);
  check(vi, q);
  for (int i = 0; i < n - 1; ++i) {
    int val = (vi[i] + vi[i+1]) / 2;
    if (val >= p && val <= q)
      check(vi, val);
    if (val + 1 >= p && val + 1 <= q)
      check(vi, val + 1);
  }

  cout << res << endl;

  return 0;
}
