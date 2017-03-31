#pragma comment(linker,"/STACK:100000000000,100000000000")

#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <algorithm>
#include <string>
#include <cstring>
#include <vector>
#include <cmath>
#include <map>
#include <stack>
#include <set>
#include <iomanip>
#include <queue>
#include <map>
#include <functional>
#include <list>
#include <sstream>
#include <ctime>
#include <climits>
#include <bitset>
#include <list>
#include <cassert>
#include <complex>

using namespace std;

/* Constants begin */
const long long inf = 2e18 + 7;
const long long mod = 1e9 + 7;
const double eps = 1e-7;
const double PI = 2*acos(0.0);
const double E = 2.71828;
/* Constants end */

/* Defines begin */
#define pb push_back
#define mp make_pair
#define ll long long
#define double long double
#define F first
#define S second
#define all(a) (a).begin(),(a).end()
#define forn(i,n) for (int (i)=0; (i)<(n); ++(i))
#define random (rand()<<16|rand())
#define sqr(x) (x)*(x)
#define base complex<double>
/* Defines end */

int n, a[100005];
int l, r;

int res, bst;

void go(int x){
  if(x > r || x < l) return;
  int mn = mod;
  forn(i, n) mn = min(mn, abs(x - a[i]));
  if(res < mn){
    res = mn;
    bst = x;
  }
}

void Solve(){
  scanf("%d", &n);
  forn(i, n) scanf("%d", a + i);
  scanf("%d %d", &l, &r);
  sort(a, a + n);
  res = -1;
  go(l);
  go(r);
  forn(i, n - 1){
    int m = (a[i] + a[i + 1]) / 2;
    go(m);
    go(m + 1);
  }
  printf("%d\n", bst);
}

int main(void){
  #ifdef nobik
    freopen("input.txt", "rt", stdin);
    freopen("output.txt", "wt", stdout);
  #endif
  Solve();
  return 0;
}
