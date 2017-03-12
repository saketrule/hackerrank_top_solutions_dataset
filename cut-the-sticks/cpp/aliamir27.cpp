#include <algorithm>
#include <iostream>
#include <cstring>
#include <cstdlib>
#include <string>
#include <cstdio>
#include <vector>
#include <map>
#include <set>

using namespace std;

#define SIZE(A) (int((A).size()))
#define pb(A) push_back(A)
#define mp(A,B) make_pair(A,B)

int a[2000000];

int main() {
	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		scanf("%d", a+i);
	}
	sort(a, a+n);
	reverse(a, a+n);

	for (; n>0;) {
		int x = a[n-1];
		for (int i = n-1; i >= 0; i--) {
			a[i]-=x;
		}
		printf("%d\n", n);
		while (n>0 && !a[n-1]) n--;
	}

    return 0;
}

