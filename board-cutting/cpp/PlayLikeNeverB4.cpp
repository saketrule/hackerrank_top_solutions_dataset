#include <cstdio>
#include <iostream>
#include <fstream>
#include <vector>
#include <list>
#include <map>
#include <set>
#include <queue>
#include <stack>
#include <bitset>
#include <algorithm>
#include <sstream>
#include <iomanip>
#include <cmath>
#include <cstdlib>
#include <cctype>
#include <cstring>
#include <string>
#include <ctime>
#include <cassert>
#include <utility>

using namespace std;

#define MOD 1000000007

int T, M, N, x;
vector<pair<int, int> > A;

int main() {
//	freopen("date.in", "r", stdin);
//	freopen("date.out","w", stdout);
	
	scanf("%d", &T);
	while(T--) {
        scanf("%d %d", &M, &N);
        
        A.clear();
        
        for(int i = 0; i < M - 1; i++) {
            scanf("%d", &x);
            A.push_back(make_pair(x, 0));
        }
        for(int i = 0; i < N - 1; i++) {
            scanf("%d", &x);
            A.push_back(make_pair(x, 1));
        }
        
        sort(A.rbegin(), A.rend());
        
        int f[] = { 1, 1 };
        long long ans = 0;
        for(int i = 0; i < N + M - 2; i++) {
            ans += (long long)f[1 - A[i].second] * A[i].first;
            ans %= MOD;
            f[ A[i].second ]++;
        }
        
        printf("%d\n", (int)ans);
	}
	
	return 0;
}
