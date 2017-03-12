/* Enter your code here. Read input from STDIN. Print output to STDOUT */
#include <cstdio>
#include <string>
#include <cstring>

using namespace std;

int d[102],con[102][102],dp[102][2];

inline void better(int &x,int y) {
	if (x < y) {
		x = y;
	}
}

void dfs(int x,int p) {
int i;
	dp[x][0] = -1;
	dp[x][1] = 0;
	for (i = 0; i < d[x];++i) {
		int y = con[x][i];
		if (y == p) {
			continue;
		}
		dfs(y,x);
		int even = -1,odd = -1;
		if (dp[x][0] >= 0) {
			if (dp[y][0] >= 0) {
				better(even,dp[x][0] + dp[y][0] + 1);
			}
			if (dp[y][1] >= 0) {
				better(odd,dp[x][0] + dp[y][1]);
			}
		}
		if (dp[x][1] >= 0) {
			if (dp[y][0] >= 0) {
				better(odd, dp[x][1] + dp[y][0] + 1);
			}
			if (dp[y][1] >= 0) {
				better(even,dp[x][1] + dp[y][1]);
			}
		}
		dp[x][0] = even;
		dp[x][1] = odd;

	}
}

int main() {
int n,m,x,y,i;
	scanf("%d%d",&n,&m);
	for (i = 0; i < m; ++i) {
		scanf("%d%d",&x,&y);
		--x;
		--y;
		con[x][d[x]++]=y;
		con[y][d[y]++]=x;
	}
	dfs(0,-1);
	printf("%d\n",dp[0][0]);
	return 0;
}