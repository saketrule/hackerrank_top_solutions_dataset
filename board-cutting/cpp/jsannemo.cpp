#include <iostream>
#include <vector>
#include <algorithm>
#include <functional>

using namespace std;

const int MOD = 1000000007;

int main(){
	int T;
	cin >> T;
	while (T--){
		int N, M;
		cin >> N >> M;
		N--; M--;
		vector<long long> x(N), y(M);
		for (int i = 0; i < N; ++i) cin >> x[i];
		for (int i = 0; i < M; ++i) cin >> y[i];
		sort(x.begin(), x.end(), greater<long long>());
		sort(y.begin(), y.end(), greater<long long>());
		long long ans = 0;
		int xs = 0, ys = 0;
		for (int i = 0; i < N + M; ++i){
			if (xs == N) ans = (ans + (N + 1) * y[ys++])%MOD;
			else if (ys == M) ans = (ans + (M + 1) * x[xs++]) % MOD;
			else {
				if (x[xs] < y[ys]){
					ans = (ans + (xs + 1) * y[ys++]) % MOD;
				} else {
					ans = (ans + (ys + 1) * x[xs++]) % MOD;
				}
			}
		}
		cout << ans << endl;
	}
}