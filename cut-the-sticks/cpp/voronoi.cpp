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

#pragma comment(linker, ”/STACK:36777216“)

#define Pi 3.141592653589793
#define all(c) (c).begin(), (c).end()
typedef long long ll;

int ri() {
  int res; scanf("%d", &res); return res;
}

class timer {
public:
  timer() : t_(clock()) {}
  void start() { t_ = clock(); }
  float elapsed() { return float(clock() - t_) / CLOCKS_PER_SEC; }
private:
  clock_t t_;
};


int main() {
  //freopen("in.txt", "r", stdin);
  int n; cin >> n;
  map<int, int> mp;
  for (int i = 0; i < n; ++i) {
    int a; cin >> a;
    mp[a]++;
  }
  for (map<int, int>::iterator it = mp.begin(); it != mp.end(); ++it) {
    if (n) cout << n << endl;
    n -= it->second;
  }
  return 0;
}
