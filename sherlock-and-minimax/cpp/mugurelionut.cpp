#include <stdio.h>
#include <stdlib.h>
#include <algorithm>
#include <vector>

using namespace std;

vector<int> v;
int N, P, Q, i, ans, cans, c, d;

void Test(int M) {
	if (M < P || M > Q) return;
	int i;
	c = -1;
	for (i = 0; i < N; i++) {
		d = abs(M - v[i]);
		if (c < 0 || d < c) c = d;
	}
	if (cans < 0 || c > cans) {
		cans = c;
		ans = M;
	}
}

int main() {
	scanf("%d", &N);
	v.resize(N);
	for (i = 0; i < N; i++)
		scanf("%d", &(v[i]));
	scanf("%d %d", &P, &Q);
	sort(v.begin(), v.end());

	ans = 0; cans = -1;
	
	Test(P);
	Test(Q);
	for (i = 1; i < N; i++)
		Test((v[i - 1] + v[i]) / 2);
	
	printf("%d\n", ans);
	return 0;
}
