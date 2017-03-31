#include<cstdio>
#include<iostream>
#include<vector>
#include<algorithm>
#include<string>
#include<cstring>
using namespace std;

typedef long long LL;

int N, A[111], P, Q;
int ma, ans;
void update(int x) {
    int tmp = abs(A[0]-x);
    for (int i=0; i<N; i++) tmp = min(tmp, abs(A[i]-x));

    if (ma < tmp) { ma = tmp; ans = x; }
    if (ma == tmp) ans = min(ans, x);
}

int main() {
    scanf("%d", &N);
    for (int i=0; i<N; i++) scanf("%d", A+i);
    scanf("%d%d", &P, &Q);

    sort(A, A+N);
    ma = -1;

    update(P);
    update(Q);

    for (int i=0; i<N-1; i++) {
	int mid = (A[i]+A[i+1]) / 2;
	if (P <= mid && mid <= Q) update(mid);
    }

    printf("%d\n", ans);

    return 0;
}
