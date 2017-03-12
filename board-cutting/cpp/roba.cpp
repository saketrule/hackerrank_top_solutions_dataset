#include <cstdio>
#include <vector>
#include <algorithm>
#include <iostream>
#include <functional>
using namespace std;

vector<pair<int,int> > lt;
const int MOD = (1e9)+7;

int main() {
  int T, m, n;
  scanf("%d",&T);
  while (T--) {
    scanf("%d%d",&m,&n);
    lt.clear();
    for (int i = 0 ; i < m-1 ; ++i) {
      int tmp;
      scanf("%d", &tmp);
      lt.push_back(make_pair(tmp, 0));
    }
    for (int i = 0 ; i < n-1 ; ++i) {
      int tmp;
      scanf("%d", &tmp);
      lt.push_back(make_pair(tmp, 1));
    }
    sort(lt.begin(), lt.end(), greater<pair<int,int> >());
    int c[2];
    c[0] = c[1] = 1;
    long long ans = 0;
    for (int i = 0 ; i < lt.size() ; ++i) {
      ans += ((long long)lt[i].first * c[1-lt[i].second]) % MOD;
      ans %= MOD;
      ++c[lt[i].second];
    }
    cout << ans % MOD << endl;
  }
  return 0;
}

